import { ReactComponent as SearchIcon } from '../../assets/image/search-icon.svg';
import { useState, useEffect } from 'react';
import axios from 'axios';

interface SearchPlaceHolderProps {
  onSearchResultsChange: (results: any[]) => void; // 검색 결과 변경 핸들러 추가
}

function SearchPlaceHolder({ onSearchResultsChange }: SearchPlaceHolderProps) {
  const API_URL = process.env.REACT_APP_API_URL;
  const [searchTerm, setSearchTerm] = useState('');
  const [searchResults, setSearchResults] = useState([]);

  // 검색어가 변경될 때마다 서버에 요청을 보내는 로직
  useEffect(() => {
    // 검색어가 비어있지 않은 경우에만 요청
    if (searchTerm.trim() !== '') {
      // console.log(searchTerm);
      const KeyWord = axios
        .get(`${API_URL}/corporation/search?keyword=${searchTerm}`)
        .then((response) => {
          setSearchResults(response.data.data);
          // console.log(response.data);
          onSearchResultsChange(response.data.data);
        })
        .catch((error) => {
          console.error('검색 요청 오류:', error);
        });
    } else {
      // 검색어가 비어있는 경우 검색 결과를 초기화
      console.log(111);
      setSearchResults([]);
      console.log(searchResults);
    }
  }, [searchTerm]);

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newSearchTerm = event.target.value;
    setSearchTerm(newSearchTerm);
  };

  return (
    <>
      <div className="w-[376px] bg-zinc-700 rounded-[10px] border-b border-neutral-400 items-center">
        <div className="flex mt-1 items-center">
          <input
            type="text"
            placeholder="회사명을 입력해 주세요"
            className="text-neutral-400 text-base text-left pl-4 outline-none bg-transparent w-full"
            value={searchTerm}
            onChange={handleInputChange}
          />
          <div className="p-4 justify-center">
            <SearchIcon />
          </div>
        </div>
      </div>
    </>
  );
}

export default SearchPlaceHolder;
