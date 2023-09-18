from fastapi import FastAPI
from db import init_db
from dotenv import load_dotenv

load_dotenv()

app = FastAPI()

init_db()

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)
