import NewsTab from 'components/tab/NewsTab';
import NewsOne from 'components/news/NewsOne';
import './CustomScrollbar.css'; // 커스텀 스크롤바 스타일을 정의한 CSS 파일

function News() {
  return (
    <>
      <div className="col-span-3 mt-[50px]"></div>
      <div className="col-start-1 col-end-3 ml-[5px] mt-[20px]">
        <p className="text-white font-bold text-[28px] text-left">산업별 뉴스</p>
      </div>
      <div className="col-start-1 col-end-13 mt-[10px] h-[70px] bg-zinc-700 rounded-[15px] overflow-hidden">
        <div className="pt-[9px] items-center justify-center">
          <NewsTab />
        </div>
      </div>
      <div className="col-start-1 col-end-13 mt-[10px] mb-[20px] h-[557px] bg-zinc-700 rounded-[20px] overflow-auto">
        <NewsOne />
        <NewsOne />
        <NewsOne />
        <NewsOne />
        <NewsOne />
        <NewsOne />
      </div>
    </>
  );
}

export default News;
