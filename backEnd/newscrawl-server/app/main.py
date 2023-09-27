from fastapi import FastAPI
from api.news import scrape_and_store_news_scheduler
from db import init_db
from dotenv import load_dotenv
from apscheduler.schedulers.background import BackgroundScheduler

load_dotenv()

app = FastAPI()

# Initialize the database
init_db()

# # Include the news router
# app.include_router(scrape_and_store_news.router)

if __name__ == "__main__":
    scheduler = BackgroundScheduler()
    scheduler.start()

    # Add a job to the scheduler that runs every hour
    scheduler.add_job(scrape_and_store_news_scheduler, 'interval', hours = 1)
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)
