import NewsTab from 'components/tab/NewsTab';
import NewsOne from 'components/news/NewsOne';
import './CustomScrollbar.css'; // 커스텀 스크롤바 스타일을 정의한 CSS 파일
import * as React from 'react';

function News() {
  const [dataList, setDataList] = React.useState<any[]>([]);
  // console.log(dataList);
  return (
    <>
      <div className="col-span-3 mt-[50px]"></div>
      <div className="col-start-1 col-end-3 ml-[5px] mt-[20px]">
        <p className="text-white font-bold text-[28px] text-left">산업별 뉴스</p>
      </div>
      <div className="col-start-1 col-end-13 mt-[10px] h-[70px] bg-zinc-700 rounded-[15px] overflow-hidden">
        <div className="pt-[9px] items-center justify-center">
          <NewsTab dataList={dataList} onDataListChange={setDataList} />
        </div>
      </div>
      <div className="col-start-1 col-end-13 mt-[10px] h-[70%] mb-[20px] bg-zinc-700 rounded-[20px] overflow-auto">
        {dataList.map((data, index) => (
          <NewsOne key={index} data={data} />
        ))}
      </div>
    </>
  );
}

export default News;
