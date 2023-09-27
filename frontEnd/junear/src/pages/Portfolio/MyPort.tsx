import { useState, useEffect } from 'react';
import { ReactComponent as Filter } from '../../assets/image/filter.svg';
import { ReactComponent as Plus } from '../../assets/image/plus.svg';
import { ReactComponent as Srank } from '../../assets/image/Srank.svg';
import axiosInstance from 'state/AxiosInterceptor';
import AbstractChart from 'components/portfolio/AbstractChart';
interface Portfolio {
  assets_bundle: {
    total_assets: number;
    total_investment: number;
    profit_and_loss: number;
  };
  portfolio_bundle: Array<{
    corporation_id: number;
    corporation_name: string;
    industry_type: string;
    corporation_code: number;
    total_rank: number; // 등급으로 바꾸기 고려(server)
    stock_close: number; // 주식 종가

    stability_score: number; // 등급으로 바꾸기 고려(server)
    growth_score: number; // 등급으로 바꾸기 고려(server)
    profitability_score: number; // 등급으로 바꾸기 고려(server)
    activity_score: number; // 등급으로 바꾸기 고려(server)

    stock_count: number;
    average_price: number;
  }>;
  member_bundle: {
    total_portfolio_rank: number; // 등급으로 바꾸기 고려(server)
    each_rank: {
      S: number;
      'A+': number;
      // ... 등등 모든 등급에 대한 프로퍼티
      C: number;
    };
    total_assets: number;
    each_assets: Array<{
      corporation_name: string;
      corporation_asset: number;
    }>;
    portfolio_stability_score: number; // 등급으로 바꾸기 고려(server)
    portfolio_growth_score: number; // 등급으로 바꾸기 고려(server)
    portfolio_profitability_score: number; // 등급으로 바꾸기 고려(server)
    portfolio_activity_score: number; // 등급으로 바꾸기 고려(server)
  };
}
interface ApiResponse {
  message: string;
  data: Portfolio;
}
function MyPort() {
  const [activeButton, setActiveButton] = useState('등급');
  const [portData, setPortData] = useState<Portfolio | undefined>(undefined);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get<ApiResponse>('/portfolio?memberId=4');
        setPortData(response.data.data);
        console.log(portData);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);
  const handleButtonClick = (name: string) => {
    setActiveButton(name);
  };
  return (
    <>
      {portData && (
        <>
          <div className="col-start-1 col-end-7 h-[850px] mt-[100px]">
            <div className="h-[275px] bg-zinc-700 rounded-[20px]">
              <div className="flex pl-[25px] pt-[25px] text-zinc-400">총 자산</div>
              <div className="flex pl-[25px] pt-[5px] text-white text-2xl font-medium">
                {portData.member_bundle.total_assets}
              </div>
              <div className="flex pl-[25px] pt-[25px] text-zinc-400">총 투자 금액</div>
              <div className="flex pl-[25px] pt-[5px] text-white">₩ 23,423,454,363</div>
              <div className="flex pl-[25px] pt-[25px] text-zinc-400">평가 손익</div>
              <div className="flex pl-[25px] pt-[5px] text-rose-500">₩ 23,423,454 (2345%)</div>
            </div>
            <div className="h-[25px]"></div>
            <div className="relative h-[300px] bg-zinc-700 rounded-[20px]">
              <div className="flex pt-[25px] pl-[25px] gap-[5px]">
                <button
                  className={`w-[76px] h-[27px] pl-[27px] pr-[26px] rounded-lg justify-center items-center inline-flex whitespace-nowrap text-white ${
                    activeButton === '등급' ? 'bg-teal-500' : 'bg-gray-800'
                  }`}
                  onClick={() => handleButtonClick('등급')}
                >
                  등급
                </button>
                <button
                  className={`w-[76px] h-[27px] pl-[27px] pr-[26px] rounded-lg justify-center items-center inline-flex whitespace-nowrap text-white ${
                    activeButton === '금액' ? 'bg-teal-500' : 'bg-gray-800'
                  }`}
                  onClick={() => handleButtonClick('금액')}
                >
                  금액
                </button>
              </div>
              <div className="absolute left-[-15px] mt-[25px] ">
                <AbstractChart />
              </div>
              {/* 여기에 차트가 들어가야함 등급일땐 등급관한거 금액일땐 금액관한거 랜더링 */}
            </div>
            <div className="h-[25px]"></div>
            <div className="h-[300px] bg-zinc-700 rounded-[20px]">
              <div className="pt-[25px] text-zinc-400">내 포트폴리오 지표</div>
              <div className="flex gap-[20px] justify-center mt-[50px]">
                <Srank />
                <Srank />
                <Srank />
                <Srank />
              </div>
              <div className="flex gap-[92px] text-white justify-center mt-[10px]">
                <span>안정성</span>
                <span>성장성</span>
                <span>수익성</span>
                <span>활동성</span>
              </div>
            </div>
          </div>
          <div className="col-start-7 col-end-13 h-[925px] mt-[100px] bg-zinc-700 rounded-[20px]">
            <div className="flex content-center justify-end gap-[15px] pr-[25px] mt-[25px]">
              <button>
                <Plus />
              </button>
              <button>
                <Filter />
              </button>
            </div>
          </div>
        </>
      )}
    </>
  );
}
export default MyPort;
