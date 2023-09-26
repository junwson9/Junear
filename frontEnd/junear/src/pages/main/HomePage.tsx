import { useEffect, useState } from 'react';
import Card from '../../components/main/Card';
import axiosInstance from 'state/AxiosInterceptor';
import CardSlider from 'components/main/Carousel';
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
  const [millionaireData, setMillionaireData] = useState<Millionaire[]>([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get<ApiResponse>('/billionaire/today');
        setMillionaireData(response.data.data);
        console.log(millionaireData);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);
  return (
    <>
      <div className="col-span-3 mt-[125px]">
        <p className="text-white font-bold text-[28px] text-left">내 포트폴리오</p>
      </div>
      <div className="col-start-1 col-end-5 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]"></div>

      <div className="col-start-5 col-end-13 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]"></div>
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
  );
}
export default HomePage;
