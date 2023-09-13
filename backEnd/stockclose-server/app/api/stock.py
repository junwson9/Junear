from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from db import SessionLocal, engine
from dotenv import load_dotenv
from bs4 import BeautifulSoup
import requests
import pandas as pd
from datetime import datetime, timedelta
from pydantic import BaseModel
from apscheduler.schedulers.background import BackgroundScheduler
import re
import FinanceDataReader as fdr
import time
from models.stock import Cor

router = APIRouter()

# Load environment variables from .env file
load_dotenv()

# Define the NewsCreate model for request data validation
# class NewsCreate(BaseModel):
#     news_title: str
#     news_url: str
#     news_image_url: str = None
#     news_type: str
#     news_times: datetime
#     news_media: str

# Function to get the database session
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

#Function to initialize the database tables
def init_db():
    Cor.metadata.create_all(bind=engine,checkfirst=False)

# Create a scheduler instance
scheduler = BackgroundScheduler(timezone = 'Asia/Seoul')

# Define a function to be scheduled
def get_stock_close(db: Session):

    time1 = time.time()
    corporation_codes = db.query(Cor.corporation_code).all()
    corporation_codes = [code[0] for code in corporation_codes]

    corporation_codes = corporation_codes[:5]
    corporation_codes = [20,37270,37560,37710,39130]
    # df_table = pd.DataFrame()
    current_date = datetime.now()
    one_day = timedelta(days=1)
    previous_date = current_date - one_day
    # year = str(previous_date.year)
    # month = str(previous_date.month)
    # day = str(previous_date.day)

    li_close = []
    for i in corporation_codes:
        cnt += 1
        i = str(i)
        while len(i) < 6:
            i = '0' + i
        df = fdr.DataReader(i,previous_date,current_date)
        # print(df)
        close = df.iloc[-1]['Close']
        li_close.append(close)
    # print("processing")
    # print(li_close)

    # df_table['corporation_id'] = list(range(0,len(corporation_codes)))
    # df_table['industry_id'] = list(range(0,len(corporation_codes)))
    # df_table['corporation_code'] = corporation_codes
    # df_table['name'] = ['0'] * len(corporation_codes)
    # df_table['stability_rank'] = [0] * len(corporation_codes)
    # df_table['growth_rank'] = [0] * len(corporation_codes)
    # df_table['profitability_rank'] = [0] * len(corporation_codes)
    # df_table['activity_rank'] = [0] * len(corporation_codes)
    # df_table['total_rank'] = [0] * len(corporation_codes)
    # df_table['stock_close'] = li_close
    # df_table['stock_close'] = [0] * len(corporation_codes)
    # print(22321)
    # df_table = df_table.iloc[:5]
    # print(df_table)
    #df_table.to_sql('corporation',engine, if_exists='replace',index=False)
    # print(1)
    # print(df_table[0])

    time2 = time.time()

    # print(time2-time1)

    for code, close in zip(corporation_codes[:len(li_close)],li_close):
        db_cor = db.query(Cor).filter(Cor.corporation_code == code).first()
        if db_cor:
            db_cor.stock_close = close
    db.commit()
    # df_table.to_sql('stock', engine, if_exists='replace', index=False)

    # print("1111")
    # engine.dispose()

    return

# Add a job to the scheduler that runs every hour
# scheduler.add_job(scrape_and_store_news_scheduler, 'interval', hours = 1)

# scheduler.add_job(get_stock_close, 'cron', hour = '6', minute='00')
scheduler.add_job(get_stock_close, 'cron', hour = '16', minute='21', args=[SessionLocal()])

# Start the scheduler
scheduler.start()

# Function to get a list of news from the database
# def get_news_list(db: Session, skip: int = 0, limit: int = 10):
#     news = db.query(models.News).offset(skip).limit(limit).all()
#     return news

# @router.get("/news/", response_model=list[NewsCreate])
# def read_news(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
#     return get_news_list(db, skip, limit)