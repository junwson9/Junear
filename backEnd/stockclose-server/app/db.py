from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from dotenv import load_dotenv
import os
from fastapi import HTTPException

load_dotenv()

DB_USER = os.getenv("db_user")
DB_PASSWORD = os.getenv("db_password")
DB_HOST = os.getenv("db_host")
DB_PORT = os.getenv("db_port")
DB_NAME = os.getenv("db_name")

# Database connection
DATABASE_URL = f"mysql+mysqlconnector://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}"

# Create the database engine
engine = create_engine(DATABASE_URL)

# Create a SessionLocal class to handle database sessions
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

def init_db():
    try:
        Base.metadata.create_all(bind=engine)
        print("Database tables initialized.")
    except Exception as e:
        raise HTTPException(status_code=500, detail="Database initialization error")

def get_db():
	db = SessionLocal()
	try:
		yield db # DB 연결 성공한 경우, DB 세션 시작
	finally:
		db.close()
		# db 세션이 시작된 후, API 호출이 마무리되면 DB 세션을 닫아준다.

Base = declarative_base()