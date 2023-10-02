from fastapi import FastAPI
from apscheduler.schedulers.background import BackgroundScheduler
from api.news import start_scheduler
from datetime import datetime, timedelta

app = FastAPI()

start_scheduler()

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)