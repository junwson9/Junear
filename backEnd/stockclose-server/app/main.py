from fastapi import FastAPI
from api.stock import get_stock_close
from db import init_db
from dotenv import load_dotenv

load_dotenv()

app = FastAPI()

# Initialize the database
init_db()

# # Include the news router
# app.include_router(scrape_and_store_news.router)

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)
