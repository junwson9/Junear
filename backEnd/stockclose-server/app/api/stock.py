import sched
from apscheduler.schedulers.background import BackgroundScheduler
from fastapi import Depends
from sqlalchemy.orm import Session
from datetime import datetime, timedelta
from db import SessionLocal, get_db
from models.stock import Cor
import FinanceDataReader as fdr
import logging
import logging.config

logging.config.fileConfig('log.ini')

logger = logging.getLogger(__name__)

# 주식 데이터 업데이트 함수
def get_stock_close(db: Session):
    try:
        logger.info("Stock Update Started")
        # Get corporation codes
        corporation_codes = db.query(Cor.corporation_code).all()
        corporation_codes = [code[0] for code in corporation_codes]

        # Get Date
        current_date = datetime.now()
        one_day = timedelta(days=1)
        previous_date = current_date - one_day

        li_close = []
        for i in corporation_codes:
            i = str(i)
            while len(i) < 6:
                i = '0' + i
            df = fdr.DataReader(i, previous_date, current_date)
            close = df.iloc[-1]['Close']
            li_close.append(close)

        for code, close in zip(corporation_codes[:len(li_close)], li_close):
            db_cor = db.query(Cor).filter(Cor.corporation_code == code).first()
            if db_cor:
                db_cor.stock_close = close

        db.commit()
        logger.info("Stock Update Completed")
    except Exception as e:
        logger.error(f"Error: {str(e)}")

sched = BackgroundScheduler(timezone="Asia/Seoul")

# APScheduler 작업 설정
@sched.scheduled_job('cron', hour='06', minute='00' , id='stock_update')
def scheduled_task():
    logger.info("Scheduled Task Started")
    db = SessionLocal()
    get_stock_close(db)
    db.close()
    logger.info("Scheduled Task Completed")

# 스케줄러 시작 함수
def start_scheduler():
    sched.start()
