from pydantic import BaseModel
from sqlalchemy import Column, Integer, String, DateTime
from datetime import datetime
from db import Base

class News(Base):
    __tablename__ = "news"

    id = Column(Integer, primary_key=True, index=True)
    news_title = Column(String, index=True)
    news_url = Column(String, unique=True, index=True)
    news_image_url = Column(String)
    news_type = Column(String, index=True)
    news_times = Column(DateTime)
    news_media = Column(String)

class NewsCreate(BaseModel):
    news_title: str
    news_url: str
    news_image_url: str = None
    news_type: str
    news_times: datetime
    news_media: str
