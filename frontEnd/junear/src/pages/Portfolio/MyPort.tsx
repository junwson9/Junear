import { useState } from 'react';
import { ReactComponent as Filter } from '../../assets/image/filter.svg';
import { ReactComponent as Plus } from '../../assets/image/plus.svg';

function MyPort() {
  const [activeButton, setActiveButton] = useState('등급');

  const handleButtonClick = (name: string) => {
    setActiveButton(name);
  };
  return (
    <>
      <div className="col-start-1 col-end-7 h-[850px] mt-[100px]">
        <div className="h-[275px] bg-zinc-700 rounded-[20px]">
          <div className="flex pl-[25px] pt-[25px] text-zinc-400">총 자산</div>
          <div className="flex pl-[25px] pt-[5px] text-white text-2xl font-medium">₩ 212,435,341,786,453</div>
          <div className="flex pl-[25px] pt-[25px] text-zinc-400">총 투자 금액</div>
          <div className="flex pl-[25px] pt-[5px] text-white">₩ 23,423,454,363</div>
          <div className="flex pl-[25px] pt-[25px] text-zinc-400">평가 손익</div>
          <div className="flex pl-[25px] pt-[5px] text-rose-500">₩ 23,423,454 (2345%)</div>
        </div>
        <div className="h-[25px]"></div>
        <div className="h-[300px] bg-zinc-700 rounded-[20px]">
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
          {/* 여기에 차트가 들어가야함 등급일땐 등급관한거 금액일땐 금액관한거 랜더링 */}
        </div>
        <div className="h-[25px]"></div>
        <div className="h-[300px] bg-zinc-700 rounded-[20px]"></div>
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
  );
}
export default MyPort;
