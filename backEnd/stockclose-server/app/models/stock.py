from sqlalchemy import Column, Integer, String, DECIMAL
from db import Base

class Cor(Base):
    __tablename__ = "corporation"

    corporation_id = Column(Integer, primary_key=True, index=True)
    industry_id = Column(Integer, index=True)
    corporation_code = Column(Integer, index=True)
    name = Column(String(50), index= True)
    stability_rank = Column(Integer, index=True)
    growth_rank = Column(Integer, index=True)
    profitability_rank = Column(Integer, index=True)
    activity_rank = Column(Integer, index=True)
    total_rank = Column(DECIMAL(precision=5, scale=2))
    stock_close = Column(Integer, index=True)

    