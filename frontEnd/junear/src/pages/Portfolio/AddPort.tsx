import AddPortInfo from 'components/modal/AddPortInfo';
import { useState } from 'react';
import CreatePortSearch from 'components/input/CreatePortSearch';
import axiosInstance from 'state/AxiosInterceptor';
import { useNavigate } from 'react-router-dom';
import { ReactComponent as Delete } from '../../assets/image/delete.svg';
function AddPort() {
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [searchResults, setSearchResults] = useState<any[]>([]);
  const [modalData, setModalData] = useState<any[]>([]);
  const [selectedResultIndex, setSelectedResultIndex] = useState<number | null>(null);
  const [selectedResultName, setSelectedResultName] = useState<string | null>(null);
  const openModal = (index: number) => {
    setIsModalOpen(true);
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

  const handleModalData = (data: { quantity: string; averagePrice: string }) => {
    if (selectedResultIndex !== null && selectedResultName !== null) {
      setModalData((prevModalData) => {
        // 빈 슬롯을 제거한 배열을 생성
        const updatedModalData = prevModalData.filter(Boolean);

        updatedModalData[selectedResultIndex] = {
          name: selectedResultName, // 이름 추가
          ...data,
        };

        return updatedModalData;
      });
    }
  };
  const handleDeleteClick = (indexToDelete: number) => {
    setModalData((prevModalData) => {
      const updatedModalData = prevModalData.filter((_, index) => index !== indexToDelete);
      return updatedModalData;
    });
  };
  const createPort = async () => {
    try {
      if (selectedResultIndex !== null) {
        const requestData = {
          corporation_id: modalData[selectedResultIndex]?.name?.corporation_id,
          stock_count: Number(modalData[selectedResultIndex]?.quantity),
          average_price: Number(modalData[selectedResultIndex]?.averagePrice),
        };
        const response = await axiosInstance.post('/portfolio', requestData);
        console.log(response);
        navigate('/my-portfolio');
      } else {
        console.error('selectedResultIndex is null');
      }
    } catch (error) {
      console.log(modalData);
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
        <CreatePortSearch onSearchResultsChange={handleSearchResultsChange} />
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
          <div className="relative m-2 text-[16px] pt-[20px] pb-[20px] text-white" key={index}>
            <div className="flex">
              <div className="text-left">
                {data.name.name}
                <div style={{ color: '#B0B2B5' }}>{data.name.industry_type}</div>
              </div>
              <div className="flex text-left pl-[115px]">
                <div className="absolute left-[200px]">
                  <div>수량</div>
                  <div>평단가</div>
                </div>
                <div className="absolute left-[270px]">
                  <div>{data.quantity}</div>
                  <div>{data.averagePrice}</div>
                </div>
              </div>
              <Delete
                className="absolute right-[20px] top-[27px] cursor-pointer"
                onClick={() => handleDeleteClick(index)}
              />
            </div>
          </div>
        ))}
      </div>
      <div className="col-span-12 mt-[5px]"></div>
      <div className="relative col-span-12">
        <button
          className="absolute text-white bg-teal-500 rounded-[20px] p-[14px] w-[250px] whitespace-nowrap left-[430px]"
          onClick={createPort}
        >
          포트폴리오 추가하기
        </button>
      </div>

      <div className="col-span-12 mt-[80px]"></div>
      {isModalOpen && (
        <div
          className="fixed top-0 left-0 w-screen h-screen flex items-center justify-center bg-black bg-opacity-50"
          onClick={handleModalBackgroundClick}
        >
          <AddPortInfo onClose={closeModal} onAdd={handleModalData} />
        </div>
      )}
    </>
  );
}

export default AddPort;
