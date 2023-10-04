import SearchPlaceHolder from 'components/input/SearchPlaceHolder';

import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function CorpSearch() {
  const [searchResults, setSearchResults] = useState<any[]>([]);
  const Navigate = useNavigate();
  const handleSearchResultsChange = (results: any[]) => {
    setSearchResults(results);
  };
  console.log(searchResults);

  const navigateToDetailPage = (corp_id: number) => {
    // 클릭한 기업 페이지로 이동
    Navigate(`/corporation/${corp_id}`);
  };
  return (
    <>
      <div className="mt-[50px]"></div>
      <div className="col-start-1 col-end-13 ml-[5px] mt-[100px]">
        <div className="text-white text-[28px] text-left text-center">원하시는 기업을 검색해 주세요.</div>
      </div>
      <div className="col-start-1 col-end-13 flex relative justify-center">
        <SearchPlaceHolder onSearchResultsChange={handleSearchResultsChange} />
      </div>
      <div className="col-start-1 col-end-13 flex justify-center ">
        <div className="w-[376px] h-[422px] bg-zinc-700 rounded-[10px] border-b border-neutral-400 overflow-auto">
          <div className=" left-[17px] top-[27px] flex-col justify-start items-start gap-7 inline-flex">
            <div className="">
              {searchResults.map((result, index) => (
                <div
                  className="m-2 text-[16px] text-white cursor-pointer hover:bg-gray-800"
                  key={index}
                  onClick={() => navigateToDetailPage(result.corporation_id)}
                  style={{}}
                >
                  {result.name}
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
      <div>
        {/* {searchResults.map((result, index) => (
          <div key={index}>{result.name}</div>
        ))} */}
      </div>
    </>
  );
}

export default CorpSearch;
