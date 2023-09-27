from sqlalchemy import Column, Integer, String, DateTime
from sqlalchemy.ext.declarative import declarative_base
from datetime import datetime
Base = declarative_base()

class News(Base):
    __tablename__ = 'news'

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(length=100), index=True)
    origin_url = Column(String(length=200))
    image_url = Column(String(length=200))
    industry_id = Column(Integer)
    times = Column(DateTime)
    media = Column(String(length=50))

    def __init__(self, title, origin_url, image_url, industry_id, times, media):
        self.title = title
        self.origin_url = origin_url
        self.image_url = image_url
        self.industry_id = industry_id
        self.times = times
        self.media = media
