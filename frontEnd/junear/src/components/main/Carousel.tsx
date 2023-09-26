import { useState, useEffect } from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import News from './News';
import axiosInstance from 'state/AxiosInterceptor';

interface News {
  industry: string;
  title: string;
  origin_url: string;
  image_url: string;
  times: string;
  media: string;
}

interface ApiResponse {
  message: string;
  data: News[];
}
const CardSlider = () => {
  const [newsData, setNewsData] = useState<News[]>([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get<ApiResponse>('/news/recent');
        setNewsData(response.data.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);
  const settings = {
    infinite: true,
    slidesToShow: 4,
    slidesToScroll: 4,
    arrows: false,
    autoplay: true,
    autoplaySpeed: 5000,
  };
  return (
    <div style={{ width: '100%', height: '100%' }}>
      <Slider {...settings}>
        {newsData.map((news, index) => (
          <News
            key={index}
            image_url={news.image_url}
            industry={news.industry}
            title={news.title}
            origin_url={news.origin_url}
            times={news.times}
            media={news.media}
          />
        ))}
      </Slider>
    </div>
  );
};

export default CardSlider;
