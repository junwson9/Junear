from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from db import SessionLocal, engine
from models.news import News as models
from dotenv import load_dotenv
from bs4 import BeautifulSoup
import requests
import pandas as pd
from datetime import datetime, timedelta
from pydantic import BaseModel
from apscheduler.schedulers.background import BackgroundScheduler
import re
import time

router = APIRouter()

# Load environment variables from .env file
load_dotenv()

# Define the NewsCreate model for request data validation
class NewsCreate(BaseModel):
    news_title: str
    news_url: str
    news_image_url: str = None
    news_type: str
    news_times: datetime
    news_media: str

# Function to scrape news and store it in the database
def scrape_and_store_news(db: Session, news_item: NewsCreate):
    table_name = 'news'

    keywords = [
        '전기,전자', '건설업', '운수장비', '의료정밀', '섬유,의복',
        '통신업', '제조업', '의약품', '서비스업', '화학', '비금속광물',
        '금융업', '음식료품', '종이,목재', '기계', '철강및금속', '운수창고', '유통업', '전기가스업'
    ]

    end_date = datetime.now().strftime("%Y%m%d")
    start_date = (datetime.now() - timedelta(days=3)).strftime("%Y%m%d")
    df_tot = pd.DataFrame()

    for i in keywords:
        search_query = i

        url = f"https://search.naver.com/search.naver?where=news&sm=tab_jum&query={search_query}&sort=RELEVANT&ds={start_date}&de={end_date}"
        headers = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36"}

        response = requests.get(url, headers=headers)
        soup = BeautifulSoup(response.text, 'lxml')

        if response.status_code == 200:
            df = pd.DataFrame()
            news_data = []
            news_type = [search_query] * 10
            # BeautifulSoup을 사용하여 HTML 파싱을 수행합니다.
            soup = BeautifulSoup(response.text, 'html.parser')

            # "group_news" div 태그를 찾습니다.
            group_news_div = soup.find('div', 'group_news')
            news_medias = group_news_div.find_all('a','info press')
            news_times = group_news_div.find_all('span','info')

            news_medias_list = []
            news_times_list = []
            
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
            
            current_date = datetime.now()

            # 변환된 결과를 저장할 리스트를 초기화합니다.
            converted_dates = []
            d = news_times_list

            # 주어진 문자열 리스트를 순회하면서 datetime으로 변환합니다.
            for date_str in d:
                if '전' in date_str:  # "3일 전" 또는 "3시간 전" 형식인 경우
                    date_split = date_str.split()
                    match = re.search(r'\d+', date_str)  # 숫자 패턴을 찾습니다.
                    if match:
                        time_value = int(match.group())  # 숫자를 추출하고 정수로 변환합니다.
                    print(time_value)  # 출력: 3
                    # 시간 단위와 값 추출

                    match = re.search(r'(시간|일)', date_split[0])  # "시간" 또는 "일" 패턴을 찾습니다.
                    if match:
                        time_unit = match.group()  # "시간" 또는 "일"을 추출합니다.
                        print(time_unit)  # 출력: "시간"

                    if time_unit == '일':
                        converted_date = current_date - timedelta(days=time_value)
                    elif time_unit == '시간':
                        converted_date = current_date - timedelta(hours=time_value)
                    else:
                        # 처리하지 않은 다른 시간 단위에 대한 로직을 추가하세요.
                        # 예를 들어, '분' 또는 '주' 등을 처리할 수 있습니다.
                        converted_date = None

                    if converted_date:
                        converted_dates.append(converted_date)
                else:  # "2023.09.01."과 같은 형식인 경우
                    try:
                        converted_date = datetime.strptime(date_str, "%Y.%m.%d.")
                        converted_dates.append(converted_date)
                    except ValueError:
                        # 날짜 형식이 잘못된 경우 처리할 수 있습니다.
                        pass

            news_times_list = converted_dates

            # 뉴스 리스트를 포함한 하위 요소들을 가져옵니다.
            news_items = group_news_div.find_all('a','news_tit')
            # print("=====")
            # print(news_items)
            for news_item in news_items:
                # 기사 제목 추출
                news_title = news_item.text.strip()

                # 기사 URL 추출
                news_url = news_item['href']

                # 이미지 데이터 추출
                news_image = news_item.find_previous('img', class_='thumb api_get')
                if news_image:
                    news_image_url = news_image.get('data-lazysrc')
                else:
                    news_image_url = None

                # 결과를 딕셔너리로 저장하고 리스트에 추가합니다.
                news_item_data = {
                    'title': news_title,
                    'url': news_url,
                    'image_url': news_image_url
                }
                news_data.append(news_item_data)
            
            news_titles = []
            news_urls = []
            news_image_urls = []
            # 추출된 뉴스 데이터 출력 또는 활용
            for news_item in news_data:
                news_titles.append(news_item['title'])
                news_urls.append(news_item['url'])
                news_image_urls.append(news_item['image_url'])

            df['news_title'] = news_titles
            df['news_url'] = news_urls
            df['news_image_url'] = news_image_urls
            df['news_type'] = [search_query] * 10
            df['news_times'] = news_times_list
            df['news_media'] = news_medias_list

            df_tot = pd.concat([df_tot,df], ignore_index=True)
            # print(len(df))
            # print(df)
            

        else:
            print("웹 페이지에 접근할 수 없습니다. HTTP 상태 코드:", response.status_code)

    # print(df_tot)
    # print(len(df_tot))

    df_tot.to_sql(table_name, engine, if_exists='replace', index=False)

    engine.dispose()

    # # Create a new News record and add it to the database
    # db_news = models.News(**news_item.dict())
    # db.add(db_news)
    # db.commit()
    # db.refresh(db_news)

    return

# Function to get the database session
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# Function to initialize the database tables
def init_db():
    models.Base.metadata.create_all(bind=engine)

# Create a scheduler instance
scheduler = BackgroundScheduler()

# Define a function to be scheduled
def scrape_and_store_news_scheduler():
    table_name = 'news'
    print(table_name)

    keywords = [
        '전기,전자', '건설업', '운수장비', '의료정밀', '섬유,의복',
        '통신업', '제조업', '의약품', '서비스업', '화학', '비금속광물',
        '금융업', '음식료품', '종이,목재', '기계', '철강및금속', '운수창고', '유통업', '전기가스업'
    ]

    end_date = datetime.now().strftime("%Y%m%d")
    start_date = (datetime.now() - timedelta(days=3)).strftime("%Y%m%d")
    df_tot = pd.DataFrame()

    for i in keywords[:10]:
        search_query = i

        url = f"https://search.naver.com/search.naver?where=news&sm=tab_jum&query={search_query}&sort=RELEVANT&ds={start_date}&de={end_date}"
        headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36"}

        response = requests.get(url, headers=headers)
        soup = BeautifulSoup(response.text, 'lxml')

        if response.status_code == 200:
            df = pd.DataFrame()
            news_data = []
            news_type = [search_query] * 10
            # BeautifulSoup을 사용하여 HTML 파싱을 수행합니다.
            soup = BeautifulSoup(response.text, 'html.parser')

            # "group_news" div 태그를 찾습니다.
            group_news_div = soup.find('div', 'group_news')
            news_medias = group_news_div.find_all('a','info press')
            news_times = group_news_div.find_all('span','info')

            news_medias_list = []
            news_times_list = []
            
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

            current_date = datetime.now()

            # 변환된 결과를 저장할 리스트를 초기화합니다.
            converted_dates = []
            d = news_times_list

            # 주어진 문자열 리스트를 순회하면서 datetime으로 변환합니다.
            for date_str in d:
                if '전' in date_str:  # "3일 전" 또는 "3시간 전" 형식인 경우
                    date_split = date_str.split()
                    match = re.search(r'\d+', date_str)  # 숫자 패턴을 찾습니다.
                    if match:
                        time_value = int(match.group())  # 숫자를 추출하고 정수로 변환합니다.
                    # 시간 단위와 값 추출

                    match = re.search(r'(시간|일)', date_split[0])  # "시간" 또는 "일" 패턴을 찾습니다.
                    if match:
                        time_unit = match.group()  # "시간" 또는 "일"을 추출합니다.

                    if time_unit == '일':
                        converted_date = current_date - timedelta(days=time_value)
                    elif time_unit == '시간':
                        converted_date = current_date - timedelta(hours=time_value)
                    else:
                        # 처리하지 않은 다른 시간 단위에 대한 로직을 추가하세요.
                        # 예를 들어, '분' 또는 '주' 등을 처리할 수 있습니다.
                        converted_date = None

                    if converted_date:
                        converted_dates.append(converted_date)
                else:  # "2023.09.01."과 같은 형식인 경우
                    try:
                        converted_date = datetime.strptime(date_str, "%Y.%m.%d.")
                        converted_dates.append(converted_date)
                    except ValueError:
                        # 날짜 형식이 잘못된 경우 처리할 수 있습니다.
                        pass

            news_times_list = converted_dates

            # 뉴스 리스트를 포함한 하위 요소들을 가져옵니다.
            news_items = group_news_div.find_all('a','news_tit')
            # print("=====")
            # print(news_items)
            for news_item in news_items:
                # 기사 제목 추출
                news_title = news_item.text.strip()

                # 기사 URL 추출
                news_url = news_item['href']

                # 이미지 데이터 추출
                news_image = news_item.find_previous('img', class_='thumb api_get')
                if news_image:
                    news_image_url = news_image.get('data-lazysrc')
                else:
                    news_image_url = None

                # 결과를 딕셔너리로 저장하고 리스트에 추가합니다.
                news_item_data = {
                    'title': news_title,
                    'url': news_url,
                    'image_url': news_image_url
                }
                news_data.append(news_item_data)
            
            news_titles = []
            news_urls = []
            news_image_urls = []
            # 추출된 뉴스 데이터 출력 또는 활용
            for news_item in news_data:
                news_titles.append(news_item['title'])
                news_urls.append(news_item['url'])
                news_image_urls.append(news_item['image_url'])

            df['news_title'] = news_titles
            df['news_url'] = news_urls
            df['news_image_url'] = news_image_urls
            df['news_type'] = [search_query] * 10
            df['news_times'] = news_times_list
            df['news_media'] = news_medias_list

            df_tot = pd.concat([df_tot,df], ignore_index=True)
            # print(len(df))
            # print(df)
            time.sleep(0.3)
        else:
            print(url)
            print("웹 페이지에 접근할 수 없습니다. HTTP 상태 코드:", response.status_code)

    # print(df_tot)
    # print(len(df_tot))

    df_tot.to_sql(table_name, engine, if_exists='replace', index=False)
    print("db 저장됨")
    engine.dispose()

    # # Create a new News record and add it to the database
    # db_news = models.News(**news_item.dict())
    # db.add(db_news)
    # db.commit()
    # db.refresh(db_news)
    return



# scheduler.add_job(scrape_and_store_news_scheduler, 'interval', minutes = 5)

# Start the scheduler


@router.post("/news/", response_model=NewsCreate)
def create_news(news_item: NewsCreate, db: Session = Depends(get_db)):
    return scrape_and_store_news(db, news_item)

# Function to get a list of news from the database
def get_news_list(db: Session, skip: int = 0, limit: int = 10):
    news = db.query(models.News).offset(skip).limit(limit).all()
    return news

@router.get("/news/", response_model=list[NewsCreate])
def read_news(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
    return get_news_list(db, skip, limit)