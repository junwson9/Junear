import { ReactComponent as SearchIcon } from '../../assets/image/search-icon.svg';
import { useState, useEffect } from 'react';
import axios from 'axios';

function SearchPlaceHolder() {
  const API_URL = process.env.REACT_APP_API_URL;
  const [searchTerm, setSearchTerm] = useState('');
  const [searchResults, setSearchResults] = useState([]);

  // 검색어가 변경될 때마다 서버에 요청을 보내는 로직
  useEffect(() => {
    // 검색어가 비어있지 않은 경우에만 요청을 보냅니다.
    if (searchTerm.trim() !== '') {
      const KeyWord = axios
        .get(`${API_URL}/corporation/search?keyword=<keyword>`)
        .then((response) => {
          // 검색 결과를 상태에 저장합니다.
          setSearchResults(response.data);
        })
        .catch((error) => {
          console.error('검색 요청 오류:', error);
        });
    } else {
      // 검색어가 비어있는 경우 검색 결과를 초기화합니다.
      setSearchResults([]);
    }
  }, [searchTerm]); // searchTerm이 변경될 때만 useEffect가 실행됩니다.

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newSearchTerm = event.target.value;
    setSearchTerm(newSearchTerm);
  };

  return (
    <>
      <div className="w-[376px] h-[60px] bg-zinc-700 rounded-[10px] border-b border-neutral-400 flex items-center">
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

      {/* 검색 결과를 여기에 표시할 수 있습니다. */}
      <div>
        {/* {searchResults.map((result, index) => (
          <div key={index}>{result.name}</div>
        ))} */}
      </div>
    </>
  );
}

export default SearchPlaceHolder;
