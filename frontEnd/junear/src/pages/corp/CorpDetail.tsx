import SearchPlaceHolderSmall from './../../components/input/SearchPlaceHolderSmall';
import BookMark from 'components/common/Bookmark';
import React, { useState } from 'react';
import { ReactComponent as A_Plus } from '../../assets/image/A+rank.svg';

function CorpDetail() {
  const [isBookmarked, setIsBookmarked] = useState(false);

  const handleBookmarkClick = () => {
    setIsBookmarked(!isBookmarked);
  };

  return (
    <>
      <div className="col-start-1 col-end-7 mt-[70px]">
        <div className="col-start-1 col-end-7 mt-[25px] mb-[25px] flex ">
          <SearchPlaceHolderSmall />
        </div>
        <div className="grid grid-rows-3 flex col-start-1 col-end-7 bg-zinc-700 rounded-[20px] mb-[25px] h-[480px]">
          <div className="flex">
            <div className="mr-auto mt-6 ml-8">
              <div className="text-[20px] text-white ">삼성전자</div>
              <div className="text-[12px] text-neutral-400">088980 | 제조</div>
            </div>
            <div className="ml-auto mt-8 mr-8">
              <BookMark isBookmarked={isBookmarked} onClick={handleBookmarkClick} />
            </div>
          </div>
          <div className="flex items-center justify-center mb-[100px]">
            <A_Plus width="180" height="180" />
          </div>
          <div className="flex gap-[5px] items-center justify-center mb-[40px] ">
            <div className="ml-4 mr-4">
              <A_Plus />
              <div className="text-[16px] text-white mt-2">안정성</div>
            </div>
            <div className="mr-4">
              <A_Plus />
              <div className="text-[16px] text-white mt-2">성장성</div>
            </div>
            <div className="mr-4">
              <A_Plus />
              <div className="text-[16px] text-white mt-2">수익성</div>
            </div>
            <div className="mr-4">
              <A_Plus />
              <div className="text-[16px] text-white mt-2">활동성</div>
            </div>
          </div>
        </div>
        <div className="col-start-1 col-end-7 bg-zinc-700 rounded-[20px] mb-[25px] h-[420px] mb-8">
          <div className="text-[20px] text-white text-left pt-6 ml-8">"삼성전자의" 주가정보</div>
        </div>
      </div>
      <div className="col-start-7 col-end-13 mt-[70px]">
        <div className="col-start-7 col-end-13 bg-zinc-700 mt-[25px] mb-[25px] rounded-[20px] h-[1010px]">
          <div className="text-[20px] text-white text-left pt-6 ml-8 mb-8">"제조" 산업 관련 뉴스</div>
          <div className="w-[580px] h-[120px] relative">
            <div className="w-[120px] h-[120px] left-[25px] absolute bg-zinc-300 rounded-[10px]" />
            <img
              className="w-[120px] h-[120px] left-[25px] absolute rounded-[10px]"
              src="https://via.placeholder.com/120x120"
            />
            <div className="w-[393px] left-[170px] top-[29px] absolute text-white text-lg text-left">
              삼성전기 IT 기술력 전기차 시대 빛 본다, 장덕현 전장용 전자소자 사업 확대
            </div>
            <div className="w-[330px] h-11 left-[170px] top-[76px] absolute text-neutral-400 text-left leading-[30px]">
              파이낸셜뉴스 | 1시간 전
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default CorpDetail;
