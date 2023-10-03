import AddPortInfo from 'components/modal/AddPortInfo';
import { useState } from 'react';
import SearchPlaceHolder from 'components/input/SearchPlaceHolder';
import axiosInstance from 'state/AxiosInterceptor';
import { log } from 'console';

function CreatePort() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [searchResults, setSearchResults] = useState<any[]>([]);
  // 각 검색 결과 항목에 대한 모달 데이터를 배열로 관리합니다.
  const [modalData, setModalData] = useState<any[]>([]);
  const [selectedResultIndex, setSelectedResultIndex] = useState<number | null>(null);
  const [selectedResultName, setSelectedResultName] = useState<string | null>(null);

  const openModal = (index: number) => {
    setIsModalOpen(true);
    // 모달을 열 때 선택한 검색 결과 항목의 인덱스와 이름을 설정합니다.
    setSelectedResultIndex(index);
    setSelectedResultName(searchResults[index]);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };
  const handleSearchResultsChange = (results: any[]) => {
    setSearchResults(results);
  };
  const handleModalBackgroundClick = (e: React.MouseEvent) => {
    if (e.target === e.currentTarget) {
      closeModal();
    }
  };

  // 모달에서 데이터를 추가할 때 선택한 검색 결과 항목의 인덱스를 사용하여 모달 데이터를 업데이트합니다.
  const handleModalData = (data: { quantity: string; averagePrice: string }) => {
    if (selectedResultIndex !== null) {
      // 모달 데이터를 업데이트할 때 해당 인덱스의 요소를 직접 수정합니다.
      setModalData((prevModalData) => {
        const updatedModalData = [...prevModalData];
        updatedModalData[selectedResultIndex] = {
          name: selectedResultName || '', // 이름 추가
          ...data,
        };
        return updatedModalData;
      });
    }
  };

  const createPort = async () => {
    try {
      const requestData = {
        data: modalData.map((item) => ({
          corporation_id: item.name.corporation_id,
          stock_count: item.quantity,
          average_price: item.averagePrice,
        })),
      };
      const response = await axiosInstance.post('/portfolio/init', requestData);
      console.log(response);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  return (
    <>
      <div className="col-span-12 text-white mt-[125px] text-[28px] text-center">보유중인 주식을 추가해 주십시오.</div>
      <div className="col-span-12 mt-[5px]"></div>
      <div
        className="col-start-1 col-end-6 h-[420px] bg-zinc-700 rounded-[20px]"
        style={{ overflow: 'auto', maxHeight: '420px' }}
      >
        <SearchPlaceHolder onSearchResultsChange={handleSearchResultsChange} />
        {searchResults.map((result, index) => (
          <div
            className="m-2 text-[16px] pt-[20px] pb-[20px] text-white cursor-pointer
          hover:bg-gray-800"
            key={index}
            onClick={() => openModal(index)}
          >
            {result.name}
          </div>
        ))}
      </div>
      <div className="col-start-8 col-end-13 h-[420px] bg-zinc-700 rounded-[20px]">
        {modalData.map((data, index) => (
          <div className="pt-[25px] pb-[25px] border-b-2" key={index}>
            {data.name.name}
            <div>{data.quantity}</div>
            <div>{data.averagePrice}</div>
          </div>
        ))}
      </div>
      <div className="col-span-12 mt-[5px]"></div>
      <div className="relative col-span-12">
        <button
          className="absolute text-white bg-teal-500 rounded-[20px] p-[14px] w-[250px] whitespace-nowrap left-[430px]"
          onClick={createPort}
        >
          포트폴리오 생성하기
        </button>
      </div>

      <div className="col-span-12 mt-[80px]"></div>
      {isModalOpen && (
        <div
          className="fixed top-0 left-0 w-screen h-screen flex items-center justify-center bg-black bg-opacity-50"
          onClick={handleModalBackgroundClick}
        >
          {/* 모달에 선택한 검색 결과 항목의 모달 데이터와 업데이트 함수를 전달합니다. */}
          <AddPortInfo onClose={closeModal} onAdd={handleModalData} />
        </div>
      )}
    </>
  );
}

export default CreatePort;
