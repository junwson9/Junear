import SearchPlaceHolderSmall from './../../components/input/SearchPlaceHolderSmall';
import BookMark from 'components/common/Bookmark';
import { useState, useEffect } from 'react';
import Chart from 'components/corp/Chart';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import NewsOne from 'components/news/NewsOne';
import Rank from 'components/corp/Rank';

interface CorpData {
  corporation_id: number;
  industry_id: number;
  industry_type: string;
  corporation_code: string;
  name: string;
  activity_rank: number;
  growth_rank: number;
  profitability_rank: number;
  stability_rank: number;
  stock_close: number;
  total_rank: number;
}
function CorpDetail() {
  const [isBookmarked, setIsBookmarked] = useState(false);
  const API_URL = process.env.REACT_APP_API_URL;
  const { corp } = useParams();
  const [corpData, setCorpData] = useState<CorpData | null>(null);
  const [newsData, setNewsData] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const handleBookmarkClick = () => {
    setIsBookmarked(!isBookmarked);
  };
  console.log(isBookmarked);
  console.log(corpData);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${API_URL}/corporation/${corp}`);
        const corp_data: CorpData = response.data.data; // CorpData 타입으로 설정
        setCorpData(corp_data);

        if (corp_data) {
          const query = `?industry_id=${corp_data.industry_id}`;
          const newsResponse = await axios.get(`${API_URL}/news/${query}`);
          setNewsData(newsResponse.data.data); // 뉴스 데이터를 setNewsData로 설정
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        // 데이터 로딩이 완료되면 isLoading를 false로 설정
        setIsLoading(false);
      }
    };

    fetchData();
  }, [corp]);

  if (!corpData) {
    return <div>잘못된 경로입니다.</div>;
  }

  return (
    <>
      <div className="col-start-1 col-end-7 mt-[70px]">
        <div className="col-start-1 col-end-7 mt-[25px] mb-[25px] flex ">
          <SearchPlaceHolderSmall />
        </div>
        <div className="grid-rows-4 bg-zinc-700 rounded-[20px] mb-[25px] h-[480px]">
          <div className="flex">
            <div className="mr-auto mt-6 ml-8">
              <div className="text-[20px] text-white ">{corpData.name}</div>
              <div className="text-[12px] text-neutral-400">
                {corpData.corporation_code} | {corpData.industry_type}
              </div>
            </div>
            <div className="ml-auto mt-8 mr-8">
              <BookMark isBookmarked={isBookmarked} onClick={handleBookmarkClick} />
            </div>
          </div>
          <div className="flex items-center justify-center mb-[50px]">
            <Rank rank={corpData.total_rank} />
          </div>
          <div className="mb-[40px] ">
            <div className="gap-[15px] flex items-center justify-center">
              <Rank rank={corpData.stability_rank} />
              <Rank rank={corpData.growth_rank} />
              <Rank rank={corpData.profitability_rank} />
              <Rank rank={corpData.activity_rank} />
            </div>
            <div className="flex ">
              <div className="text-[16px] text-white mt-2 mx-auto">안정성</div>
              <div className="text-[16px] text-white mt-2 mx-auto">성장성</div>
              <div className="text-[16px] text-white mt-2 mx-auto">수익성</div>
              <div className="text-[16px] text-white mt-2 mx-auto">활동성</div>
            </div>
          </div>
        </div>
        {isLoading ? (
          <div className="loading-spinner">로딩 중...</div>
        ) : (
          // 로딩이 완료되면 Chart를 렌더링
          <div className="col-start-1 col-end-7 bg-zinc-700 rounded-[20px] h-[420px] ">
            <div className="text-[20px] text-white text-left pt-4 pl-8">{corpData.name} 주가정보</div>
            <div className="px-2 pt-2 pb-8">
              <Chart corporationCode={corpData.corporation_code} />
            </div>
          </div>
        )}
      </div>
      <div className="col-start-7 col-end-13 mt-[70px]">
        <div className="bg-zinc-700 mt-[25px] mb-[25px] rounded-[20px] h-[1010px] overflow-auto">
          <div className="text-[20px] text-white text-left pt-6 ml-8 mb-8">{corpData.industry_type} 관련 뉴스</div>
          {newsData.map((data, index) => (
            <NewsOne key={index} data={data} />
          ))}
          {/* <img
              className="flex w-[120px] h-[120px] left-[25px] rounded-[10px]"
              src="https://via.placeholder.com/120x120"
            />
            <div className="mx-4 my-auto">
              <div className="flex top-[29px] text-white text-lg text-left">
                삼성전기 IT 기술력 전기차 시대 빛 본다, 장덕현 전장용 전자소자 사업 확대
              </div>
              <div className="mt-2 text-neutral-400 text-left">파이낸셜뉴스 | 1시간 전</div>
            </div> */}

          {/* {newsItems.map((item, index) => (
      <div className="flex mx-4 mb-4" key={index}>
        <img
          className="flex w-[120px] h-[120px] left-[25px] rounded-[10px]"
          src={item.imageSrc}
          alt={`News ${index}`}
        />
        <div className="mx-4 my-auto">
          <div className="flex top-[29px] text-white text-lg text-left">
            {item.title}
          </div>
          <div className="mt-2 text-neutral-400 text-left">{item.time}</div>
        </div>
      </div>
    ))} */}
        </div>
      </div>
    </>
  );
}

export default CorpDetail;
