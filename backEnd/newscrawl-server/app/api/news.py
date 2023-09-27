import sched
from apscheduler.schedulers.background import BackgroundScheduler
from datetime import datetime,timedelta
import requests
import pandas as pd
import requests
from bs4 import BeautifulSoup
import re
import time
from db import SessionLocal, engine, init_db
from sqlalchemy.orm import sessionmaker
from models.news import News
import logging
import logging.config

logging.config.fileConfig('log.ini')

logger = logging.getLogger(__name__)

sched = BackgroundScheduler(timezone="Asia/Seoul")

@sched.scheduled_job('interval',hours=1,id='news_crawl')
def scrape_and_store_news_scheduler():
    # DB 실행
    init_db()
    
    keywords = [
        '전기,전자', '건설업', '운수장비', '의료정밀', '섬유,의복',
        '통신업', '제조업', '의약품', '서비스업', '화학', '비금속광물',
        '금융업', '음식료품', '종이,목재', '기계', '철강및금속', '운수창고', '유통업', '전기가스업'
    ]

    end_date = datetime.now().strftime("%Y%m%d")
    start_date = (datetime.now() - timedelta(days=3)).strftime("%Y%m%d")
    df_tot = pd.DataFrame()
    j = 1
    for i in keywords:
        search_query = i

        url = f"https://search.naver.com/search.naver?where=news&sm=tab_jum&query={search_query}&sort=RELEVANT&ds={start_date}&de={end_date}"
        headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36"}
        response = requests.get(url, headers=headers)
        soup = BeautifulSoup(response.text, 'lxml')

        if response.status_code == 200:
            logger.info(f"News Crawl {search_query} Started")
            df = pd.DataFrame()
            news_data = []
            soup = BeautifulSoup(response.text, 'html.parser')

            # "group_news" div 태그 찾기
            group_news_div = soup.find('div', 'group_news')
            news_medias = group_news_div.find_all('a','info press')
            news_times = group_news_div.find_all('span','info')
            news_image_urls = group_news_div.find_all('div', 'news_wrap api_ani_send')

            news_medias_list = []
            news_times_list = []
            news_image_urls_res = []

            for news_image_url in news_image_urls:
                test = news_image_url.find('a','dsc_thumb')
                if test:
                    image = test.find('img','thumb api_get')
                    image = image['data-lazysrc']
                    news_image_urls_res.append(image)
                else:
                    news_image_urls_res.append(None)

            for news_media in news_medias:
                x = news_media.text.strip()
                news_medias_list.append(x)

            for news_time in news_times:
                x = news_time.text.strip()
                if x[-1] != '전':
                    if x[0:4] == '2023':
                        news_times_list.append(x)
                    else:
                        pass
                else:
                    news_times_list.append(x)

            # 3일전 / 2시간 전 형식 datetime으로 변환
            current_date = datetime.now()
            converted_dates = []
            d = news_times_list

            for date_str in d:
                if '전' in date_str:  # "3일 전" 또는 "3시간 전" 형식인 경우
                    date_split = date_str.split()
                    match = re.search(r'\d+', date_str)  # 숫자 패턴 선언
                    if match:
                        time_value = int(match.group())  # 숫자만 추출, 정수로 변환
                    
                    # 시간 단위 추출
                    match = re.search(r'(분|시간|일)', date_split[0])  # "분","시간","일" 패턴 찾기
                    if match:
                        time_unit = match.group()  # 패턴 추출
                        if time_unit == '일':
                            converted_date = current_date - timedelta(days=time_value)
                        elif time_unit == '시간':
                            converted_date = current_date - timedelta(hours=time_value)
                        elif time_unit == '분':
                            converted_date = current_date - timedelta(minutes=time_value)
                        else:
                            # 예외 사항
                            converted_date = None

                        converted_dates.append(converted_date)
                else:  # "2023.09.01."과 같은 형식인 경우
                    try:
                        converted_date = datetime.strptime(date_str, "%Y.%m.%d.")
                        converted_dates.append(converted_date)
                    except ValueError:
                        # 날짜 형식 예외 사항 : 없음
                        pass

            news_times_list = converted_dates

            # 뉴스 리스트를 포함한 하위 요소 추출
            news_items = group_news_div.find_all('a','news_tit')
            for news_item in news_items:
                # 기사 제목 추출
                news_title = news_item.text.strip()
                # 기사 URL 추출
                news_url = news_item['href']
                # 결과를 딕셔너리로 저장하고 리스트에 추가
                news_item_data = {
                    'title': news_title,
                    'url': news_url
                    # 'image_url': news_image_url
                }
                news_data.append(news_item_data)
            
            news_titles = []
            news_urls = []

            # 추출된 뉴스 데이터 리스트에 append
            for news_item in news_data:
                news_titles.append(news_item['title'])
                news_urls.append(news_item['url'])


            # 데이터 프레임으로 정리
            df['title'] = news_titles
            df['origin_url'] = news_urls
            df['image_url'] = news_image_urls_res
            df['industry_id'] = [j] * 10
            df['times'] = news_times_list
            df['media'] = news_medias_list

            df_tot = pd.concat([df_tot,df], ignore_index=True)
            
            time.sleep(10)

            j += 1
            logger.info(f"News Crawl {search_query} Completed")
        else:
            logger.error(f"News Crawl {search_query} Failed / {response.status_code}")

    logger.info("DB Update Started")
    db = SessionLocal()
    for index, row in df_tot.iterrows():
        news_item = News(
            title=row['title'],
            origin_url=row['origin_url'],
            image_url=row['image_url'],
            industry_id=row['industry_id'],
            times=row['times'],
            media=row['media']
        )
        db.add(news_item)
    
    db.commit()
    
    db.close()
    logger.info("DB Update Completed")
    return

def start_scheduler():
    sched.start()
