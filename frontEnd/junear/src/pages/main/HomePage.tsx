import { useEffect, useState } from 'react';
import Card from '../../components/main/Card';
import axiosInstance from 'state/AxiosInterceptor';
import CardSlider from 'components/main/Carousel';
import DynamicRank from 'components/portfolio/DynamicRank';
import { useNavigate } from 'react-router-dom';
import AbstractChart from 'components/portfolio/AbstractChart';
interface Millionaire {
  name: string;
  image_url: string;
  phrase: string;
}
interface ApiResponse {
  message: string;
  data: Millionaire[];
}
function HomePage() {
  const navigate = useNavigate();
  const ACCESS_TOKEN = localStorage.getItem('access_token');
  const [millionaireData, setMillionaireData] = useState<Millionaire[]>([]);
  const [mainPortData, setMainPortData] = useState<string | null>(null);
  const [name, setName] = useState<string>('');
  const [seriesForAmount, setseriesForAmount] = useState<number[]>([]);
  const [labelsForAmount, setlabelsForAmount] = useState<string[]>([]);

  const goPort = () => {
    navigate('/portfolio');
  };
  const goLogin = () => {
    navigate('/login');
  };
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get<ApiResponse>('/billionaire/today');
        setMillionaireData(response.data.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);
  useEffect(() => {
    const fetchPortData = async () => {
      try {
        const response = await axiosInstance.get('/portfolio');
        console.log(response.data.data.member_bundle.total_portfolio_rank);
        setMainPortData(response.data.data.member_bundle.total_portfolio_rank);
        const corporationAssets = response.data.data.member_bundle.each_assets.map(
          (item: any) => item.corporation_asset,
        );
        const assetsLabels = response.data.data.member_bundle.each_assets.map((item: any) => item.corporation_name);
        setseriesForAmount(corporationAssets);
        setlabelsForAmount(assetsLabels);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchPortData();
  }, []);
  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axiosInstance.get('/member/info');
        // console.log(response);
        setName(response.data.data.name);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchUserData();
  }, []);
  return (
    <>
      <>
        <div className="col-span-3 mt-[125px]">
          <p className="text-white font-bold text-[28px] text-left">내 포트폴리오</p>
        </div>
        {ACCESS_TOKEN ? (
          mainPortData && mainPortData !== 'not value' ? (
            <>
              <div className="col-start-1 col-end-5 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]">
                <p className=" text-white text-[20px] mt-[30px]">{name}님의 포트폴리오 등급은</p>
                <div className="flex mt-[50px] justify-center">
                  <DynamicRank componentName={mainPortData} />
                </div>
              </div>
              <div className="col-start-5 col-end-13 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]">
                <div className="mt-8 ml-5">
                  <AbstractChart series={seriesForAmount} labels={labelsForAmount} />
                </div>
              </div>
            </>
          ) : (
            <>
              <div className="relative col-start-1 col-end-13 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]">
                <p className=" text-white text-[20px] mt-[50px]">보유하신 포트폴리오가 없습니다.</p>
                <button
                  className="absolute text-white bg-teal-500 rounded-[20px] p-[14px] w-[250px] whitespace-nowrap left-[440px] top-[150px]"
                  onClick={goPort}
                >
                  포트폴리오 생성하러가기
                </button>
              </div>
            </>
          )
        ) : (
          <>
            <div className="relative col-start-1 col-end-13 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]">
              <p className=" text-white text-[20px] mt-[50px]">로그인이 필요한 서비스입니다.</p>
              <button
                className="absolute text-white bg-teal-500 rounded-[20px] p-[14px] w-[250px] whitespace-nowrap left-[440px] top-[150px]"
                onClick={goLogin}
              >
                로그인 하러가기
              </button>
            </div>
          </>
        )}

        <div className="col-span-3 mt-[30px]">
          <p className="text-white font-bold text-[28px] text-left">최신 뉴스</p>
        </div>
        <div className="col-span-12 mt-[30px] h-[350px]">
          <CardSlider />
        </div>
        <div className="col-span-12 mt-[30px]">
          <p className="text-white font-bold text-[28px] text-left nowrap">오늘의 억만장자 명언</p>
        </div>
        <div className="col-span-12 flex justify-between">
          {millionaireData.map((item, index) => (
            <div
              key={index}
              className={`col-start-${1 + index * 4} col-end-${5 + index * 4} h-[350px]  w-[300px] mt-[30px]`}
            >
              <Card
                front={
                  <div
                    className=" group relative hover:shadow-lg"
                    style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}
                  >
                    <img src={item.image_url} alt={item.name} className="h-[350px] w-[300px] rounded-[20px]" />
                    <div className="absolute top-[150px] left-0 w-full h-full flex justify-center items-center opacity-0 group-hover:opacity-100 transition-opacity duration-300 ease-in-out ">
                      {item.name}
                    </div>
                  </div>
                }
                back={
                  <div
                    className="px-4"
                    style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}
                  >
                    <div>
                      <p>{item.phrase}</p>
                      <p className="mt-[30px]">-{item.name}-</p>
                    </div>
                  </div>
                }
              />
            </div>
          ))}
        </div>
        <div className="col-span-12 mt-[200px]"></div>
      </>
    </>
  );
}
export default HomePage;
