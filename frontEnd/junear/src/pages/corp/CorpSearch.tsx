import SearchPlaceHolder from 'components/input/SearchPlaceHolder';

function CorpSearch() {
  return (
    <>
      <div className="col-span-3 mt-[50px]"></div>
      <div className="col-start-1 col-end-13 ml-[5px] mt-[100px] mb-[20px]">
        <div className="text-white text-[28px] text-left text-center">원하시는 기업을 검색해 주세요.</div>
      </div>
      <div className="col-start-1 col-end-13 h-[79px] flex relative justify-center">
        <SearchPlaceHolder />
      </div>
    </>
  );
}

export default CorpSearch;
