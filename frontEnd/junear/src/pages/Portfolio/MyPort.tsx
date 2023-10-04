import { useState, useEffect } from 'react';
import { ReactComponent as Filter } from '../../assets/image/filter.svg';
import { ReactComponent as Plus } from '../../assets/image/plus.svg';
import axiosInstance from 'state/AxiosInterceptor';
import AbstractChart from 'components/portfolio/AbstractChart';
import DynamicRank from 'components/portfolio/DynamicRank';

interface ApiResponse {
  message: string;
  data: any;
}
function MyPort() {
  const [activeButton, setActiveButton] = useState('등급');
  const [portData, setPortData] = useState<any>(undefined);
  const [seriesForGrade, setseriesForGrade] = useState<any[]>([]);
  const [labelsForGrade, setlabelsForGrade] = useState<any[]>([]);
  const [seriesForAmount, setseriesForAmount] = useState<number[]>([]);
  const [labelsForAmount, setlabelsForAmount] = useState<string[]>([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get<ApiResponse>('/portfolio');
        setPortData(response.data.data);

        const corporationAssets = response.data.data.member_bundle.each_assets.map(
          (item: any) => item.corporation_asset,
        );
        const assetsLabels = response.data.data.member_bundle.each_assets.map((item: any) => item.corporation_name);
        setseriesForAmount(corporationAssets);
        setlabelsForAmount(assetsLabels);
        const eachRank = response.data.data.member_bundle.each_rank;
        const rankEntries = Object.entries(eachRank);
        const filteredRankEntries = rankEntries.filter(([key, value]) => value !== 0 && key !== 'total_count');
        const filteredEachRank = Object.fromEntries(filteredRankEntries);
        const corporationRanks = Object.keys(filteredEachRank);
        const gradeLabels = Object.values(filteredEachRank);
        setseriesForGrade(gradeLabels);
        setlabelsForGrade(corporationRanks);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    console.log(portData);

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
                {portData.assets_bundle.total_assets}
              </div>
              <div className="flex pl-[25px] pt-[25px] text-zinc-400">총 투자 금액</div>
              <div className="flex pl-[25px] pt-[5px] text-white">₩ {portData.assets_bundle.total_investment}</div>
              <div className="flex pl-[25px] pt-[25px] text-zinc-400">평가 손익</div>
              <div className="flex pl-[25px] pt-[5px] text-rose-500">
                ₩ {portData.assets_bundle.profit_and_loss} (
                {portData.assets_bundle.total_investment > portData.assets_bundle.total_assets ? '-' : '+'}
                {((portData.assets_bundle.profit_and_loss / portData.assets_bundle.total_investment) * 100).toFixed(2)}
                %)
              </div>
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
              <div className="absolute left-[-5px] mt-[25px] ">
                <AbstractChart
                  series={activeButton === '등급' ? seriesForGrade : seriesForAmount}
                  labels={activeButton === '등급' ? labelsForGrade : labelsForAmount}
                />
              </div>
            </div>
            <div className="h-[25px]"></div>
            <div className="h-[300px] bg-zinc-700 rounded-[20px]">
              <div className="pt-[25px] text-zinc-400">내 포트폴리오 지표</div>
              <div className="flex gap-[20px] justify-center mt-[50px]">
                <DynamicRank componentName={portData.member_bundle.portfolio_stability} />
                <DynamicRank componentName={portData.member_bundle.portfolio_growth} />
                <DynamicRank componentName={portData.member_bundle.portfolio_profitability} />
                <DynamicRank componentName={portData.member_bundle.portfolio_activity} />
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
