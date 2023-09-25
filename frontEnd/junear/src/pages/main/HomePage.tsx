import { useEffect, useState } from 'react';
import Card from '../../components/main/Card';
import axiosInstance from 'state/AxiosInterceptor';
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
      <div className="col-start-1 col-end-5 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]"></div>
      <div className="col-start-5 col-end-9 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]"></div>
      <div className="col-start-9 col-end-13 mt-[30px]  h-[332px] bg-zinc-700 rounded-[20px]"></div>
      <div className="col-span-12 mt-[30px]">
        <p className="text-white font-bold text-[28px] text-left nowrap">오늘의 억만장자 명언</p>
      </div>
      {millionaireData.map((item, index) => (
        <div key={index} className={`col-start-${1 + (index % 4)} col-end-${5 + (index % 4)} h-[350px] mt-[30px]`}>
          <Card
            front={
              <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                <img src={item.image_url} alt={item.name} />
                <p>{item.name}</p>
              </div>
            }
            back={
              <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                Back
              </div>
            }
          />
        </div>
      ))}

      <div className="col-span-12 mt-[200px]"></div>
    </>
  );
}
export default HomePage;
