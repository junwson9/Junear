import AddPortInfo from 'components/modal/AddPortInfo';
import { useState } from 'react';

function CreatePort() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [corpName, setCorpName] = useState<string>('');
  const [searchResults, setSearchResults] = useState<string[]>([]);
  // 각 검색 결과 항목에 대한 모달 데이터를 배열로 관리합니다.
  const [modalData, setModalData] = useState<{ name: string; quantity: string; averagePrice: string }[]>(
    Array(searchResults.length).fill({ name: '', quantity: '0', averagePrice: '0' }),
  );
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

  const handleModalBackgroundClick = (e: React.MouseEvent) => {
    if (e.target === e.currentTarget) {
      closeModal();
    }
  };

  const handleCorpChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCorpName(e.target.value);
  };

  const handleInputKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      console.log('Entered corpName:', corpName);
      const results = ['검색 결과 1', '검색 결과 2', '검색 결과 3'];
      setSearchResults(results);
      setCorpName('');
    }
  };

  // 모달에서 데이터를 추가할 때 선택한 검색 결과 항목의 인덱스를 사용하여 모달 데이터를 업데이트합니다.
  const handleModalData = (data: { quantity: string; averagePrice: string }) => {
    if (selectedResultIndex !== null) {
      // 모달 데이터를 추가하면서 해당 데이터를 목록의 선택한 인덱스에 위치시킵니다.
      setModalData((prevModalData) => {
        const updatedModalData = [...prevModalData];
        updatedModalData.splice(selectedResultIndex, 1, {
          name: selectedResultName || '', // 이름 추가
          ...data,
        });
        return updatedModalData;
      });
      setSearchResults([]);
    }
  };

  return (
    <>
      <div className="col-span-12 text-white mt-[125px] text-[28px] text-center">보유중인 주식을 추가해 주십시오.</div>
      <div className="col-span-12 mt-[5px]"></div>
      <div className="col-start-1 col-end-6 h-[420px] bg-zinc-700 rounded-[20px]">
        <input
          type="text"
          placeholder="기업을 입력하세요."
          value={corpName}
          onChange={handleCorpChange}
          onKeyDown={handleInputKeyDown}
        />
        {searchResults.map((result, index) => (
          <div className="pt-[25px] pb-[25px] border-b-2" key={index} onClick={() => openModal(index)}>
            {result}
            <div>{modalData[index]?.quantity}</div>
            <div>{modalData[index]?.averagePrice}</div>
          </div>
        ))}
      </div>
      <div className="col-start-8 col-end-13 h-[420px] bg-zinc-700 rounded-[20px]">
        {modalData.map((data, index) => (
          <div className="pt-[25px] pb-[25px] border-b-2" key={index}>
            {data.name} {/* 기업 이름 표시 */}
            <div>{data.quantity}</div>
            <div>{data.averagePrice}</div>
          </div>
        ))}
      </div>
      <div className="col-span-12 mt-[5px]"></div>
      <div className="relative col-span-12">
        <button className="absolute text-white bg-teal-500 rounded-[20px] p-[14px] w-[250px] whitespace-nowrap left-[430px]">
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
