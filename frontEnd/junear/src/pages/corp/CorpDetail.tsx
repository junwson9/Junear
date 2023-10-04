import SearchPlaceHolder from './../../components/input/SearchPlaceHolderDetail';
import BookMark from 'components/common/Bookmark';
import { useState, useEffect } from 'react';
import Chart from 'components/corp/Chart';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import NewsOne from 'components/news/NewsOne';
import Rank from 'components/corp/Rank';
import { useNavigate } from 'react-router-dom';
import axiosInstance from 'state/AxiosInterceptor';

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
  isBookmarked: boolean;
}
function CorpDetail() {
  const [isBookmarked, setIsBookmarked] = useState(false);
  const API_URL = process.env.REACT_APP_API_URL;
  const { corp } = useParams();
  const [corpData, setCorpData] = useState<CorpData | null>(null);
  const [newsData, setNewsData] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [isLogined, setIsLogined] = useState(false);

  const handleBookmarkClick = async () => {
    try {
      if (isBookmarked) {
        await axiosInstance.delete(`${API_URL}/bookmark/${corp}`);
      } else {
        await axiosInstance.post(`${API_URL}/bookmark/${corp}`);
      }
      setIsBookmarked(!isBookmarked);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const [searchResults, setSearchResults] = useState<any[]>([]);
  const Navigate = useNavigate();
  const handleSearchResultsChange = (results: any[]) => {
    setSearchResults(results);
  };

  const navigateToDetailPage = (corp_id: number) => {
    Navigate(`/corporation/${corp_id}`);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const access_token = localStorage.getItem('access_token');
        if (access_token) {
          const response = await axiosInstance.get(`${API_URL}/corporation/auth/${corp}`);
          const corp_data: CorpData = response.data.data; // CorpData 타입으로 설정
          setCorpData(corp_data);
          setIsLogined(true);
          const book = corp_data.isBookmarked;
          console.log(book);
          setIsBookmarked(book);
          console.log(corp_data);
          if (corp_data) {
            const query = `?industry_id=${corp_data.industry_id}`;
            const newsResponse = await axios.get(`${API_URL}/news/${query}`);
            setNewsData(newsResponse.data.data); // 뉴스 데이터를 setNewsData로 설정
          }
        } else {
          const response = await axios.get(`${API_URL}/corporation/${corp}`);
          const corp_data: CorpData = response.data.data; // CorpData 타입으로 설정
          setCorpData(corp_data);
          console.log(corp_data);
          if (corp_data) {
            const query = `?industry_id=${corp_data.industry_id}`;
            const newsResponse = await axios.get(`${API_URL}/news/${query}`);
            setNewsData(newsResponse.data.data); // 뉴스 데이터를 setNewsData로 설정
          }
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        // 데이터 로딩이 완료되면 isLoading를 false로 설정
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

  if (!corpData) {
    return <div>잘못된 경로입니다.</div>;
  }

  return (
    <>
      <div className="col-start-1 col-end-7 mt-[70px]">
        {/* <div className="col-start-1 col-end-7 mt-[25px] mb-[25px] flex ">
          <SearchPlaceHolder onSearchResultsChange={handleSearchResultsChange} />
        </div>

        {searchResults.map((result, index) => (
          <div
            className="m-2 text-[16px] text-white cursor-pointer hover:bg-gray-800"
            key={index}
            onClick={() => navigateToDetailPage(result.corporation_id)}
            style={{}}
          >
            {result.name}
          </div>
        ))} */}
        <div className="grid-rows-4 bg-zinc-700 rounded-[20px] mb-[25px] h-[480px] mt-[27px]">
          <div className="flex">
            <div className="mr-auto mt-6 ml-8">
              <div className="text-[20px] text-white ">{corpData.name}</div>
              <div className="text-[12px] text-neutral-400">
                {corpData.corporation_code} | {corpData.industry_type}
              </div>
            </div>
            <div className="ml-auto mt-8 mr-8">
              {isLogined && <BookMark isBookmarked={isBookmarked} onClick={handleBookmarkClick} />}
            </div>
          </div>
          <div className="flex items-center justify-center mb-[50px]">
            <Rank rank={corpData.total_rank} width={200} height={200} />
          </div>
          <div className="mb-[40px] ">
            <div className="gap-[15px] flex items-center justify-center">
              <Rank rank={corpData.stability_rank} width={116} height={116} />
              <Rank rank={corpData.growth_rank} width={116} height={116} />
              <Rank rank={corpData.profitability_rank} width={116} height={116} />
              <Rank rank={corpData.activity_rank} width={116} height={116} />
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
        <div className="bg-zinc-700 mt-[25px] mb-[25px] rounded-[20px] h-[930px] overflow-auto">
          <div className="text-[20px] text-white text-left pt-6 ml-8 mb-8">{corpData.industry_type} 관련 뉴스</div>
          {newsData.map((data, index) => (
            <NewsOne key={index} data={data} />
          ))}
        </div>
      </div>
    </>
  );
}

export default CorpDetail;
