from fastapi import APIRouter
from sqlalchemy.orm import Session
from db import SessionLocal, engine
from dotenv import load_dotenv
from datetime import datetime, timedelta
from apscheduler.schedulers.background import BackgroundScheduler
import FinanceDataReader as fdr
from models.stock import Cor

router = APIRouter()

load_dotenv()

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
        df = fdr.DataReader(i,previous_date,current_date)
        close = df.iloc[-1]['Close']
        li_close.append(close)

    for code, close in zip(corporation_codes[:len(li_close)],li_close):
        db_cor = db.query(Cor).filter(Cor.corporation_code == code).first()
        if db_cor:
            db_cor.stock_close = close
    db.commit()

    return

# Automatic Scheduler
# scheduler.add_job(scrape_and_store_news_scheduler, 'interval', hours = 1)
scheduler.add_job(get_stock_close, 'cron', hour = '06', minute='00', args=[SessionLocal()])

# Start the scheduler
scheduler.start()