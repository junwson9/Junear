import SearchPlaceHolderSmall from './../../components/input/SearchPlaceHolderSmall';
import BookMark from 'components/common/Bookmark';
import { useState } from 'react';
import { ReactComponent as A_Plus } from '../../assets/image/A+rank.svg';
import Chart from 'components/corp/Chart';

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
        <div className="grid-rows-4 bg-zinc-700 rounded-[20px] mb-[25px] h-[480px]">
          <div className="flex">
            <div className="mr-auto mt-6 ml-8">
              <div className="text-[20px] text-white ">삼성전자</div>
              <div className="text-[12px] text-neutral-400">088980 | 제조</div>
            </div>
            <div className="ml-auto mt-8 mr-8">
              <BookMark isBookmarked={isBookmarked} onClick={handleBookmarkClick} />
            </div>
          </div>
          <div className="flex items-center justify-center mb-[50px]">
            <A_Plus width="180" height="180" />
          </div>
          <div className="mb-[40px] ">
            <div className="gap-[15px] flex items-center justify-center">
              <A_Plus width="120" height="120" />
              <A_Plus width="120" height="120" />
              <A_Plus width="120" height="120" />
              <A_Plus width="120" height="120" />
            </div>
            <div className="flex ">
              <div className="text-[16px] text-white mt-2 mx-auto">안정성</div>
              <div className="text-[16px] text-white mt-2 mx-auto">성장성</div>
              <div className="text-[16px] text-white mt-2 mx-auto">수익성</div>
              <div className="text-[16px] text-white mt-2 mx-auto">활동성</div>
            </div>
            {/* <div className="ml-4 mr-4">
              <A_Plus width="100" height="100" />
              <div className="text-[16px] text-white mt-2">안정성</div>
            </div> */}
          </div>
        </div>
        <div className="col-start-1 col-end-7 bg-zinc-700 rounded-[20px] h-[420px] ">
          <div className="text-[20px] text-white text-left pt-4 pl-8">삼성전자 주가정보</div>
          <div className="px-2 pt-2 pb-8">
            <Chart />
          </div>
        </div>
      </div>
      <div className="col-start-7 col-end-13 mt-[70px]">
        <div className="bg-zinc-700 mt-[25px] mb-[25px] rounded-[20px] h-[1010px]">
          <div className="text-[20px] text-white text-left pt-6 ml-8 mb-8">"제조" 산업 관련 뉴스</div>
          <div className="flex mx-4 mb-4">
            <img
              className="flex w-[120px] h-[120px] left-[25px] rounded-[10px]"
              src="https://via.placeholder.com/120x120"
            />
            <div className="mx-4 my-auto">
              <div className="flex top-[29px] text-white text-lg text-left">
                삼성전기 IT 기술력 전기차 시대 빛 본다, 장덕현 전장용 전자소자 사업 확대
              </div>
              <div className="mt-2 text-neutral-400 text-left">파이낸셜뉴스 | 1시간 전</div>
            </div>
          </div>

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
