from fastapi import FastAPI
from api.stock import start_scheduler,get_stock_close
from apscheduler.schedulers.background import BackgroundScheduler
import logging
import logging.config
from db import SessionLocal

logging.config.fileConfig('log.ini')

logger = logging.getLogger(__name__)


app = FastAPI()

start_scheduler()

@app.get("/test")
def test():
    logger.info("test1")
    logger.info("Stock Task Started")
    db = SessionLocal
    get_stock_close(db)
    db.close()
    logger.info("Stock Task Completed")
    return

if __name__ == "__main__":
    import uvicorn
    # Run the FastAPI application using uvicorn in the main thread
    uvicorn.run(app, host="0.0.0.0", port=8000)
