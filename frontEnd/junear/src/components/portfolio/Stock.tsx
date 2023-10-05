import { useState, useEffect, useRef } from 'react';
import DynamicRank from './DynamicRank';
import { ReactComponent as More } from 'assets/image/more.svg';
import axiosInstance from 'state/AxiosInterceptor';
import UpdatePortInfo from 'components/modal/UpdatePortInfo';

function Stock({ item, updatePortData }: { item: any; updatePortData: () => void }) {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const closeModal = () => {
    setIsModalOpen(false);
  };

  const handleModalBackgroundClick = (e: React.MouseEvent) => {
    if (e.target === e.currentTarget) {
      closeModal();
    }
  };
  const openModal = () => {
    setIsDropdownOpen(false);
    setIsModalOpen(true);
  };

  const dropdownRef = useRef<HTMLDivElement | null>(null);
  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };
  useEffect(() => {
    const handleClickOutside = (event: any) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsDropdownOpen(false);
      }
    };

    window.addEventListener('mousedown', handleClickOutside);
    return () => {
      window.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);
  const handleDelete = () => {
    setIsDropdownOpen(false);
    axiosInstance
      .delete(`/portfolio/${item.portfolio_id}`)
      .then((response) => {
        updatePortData();
        console.log(response);
      })
      .catch((error) => {
        console.error('삭제 실패', error);
      });
  };
  return (
    <div className=" h-[178.27px] relative border-2 rounded-[20px] border-gray-600">
      <div className=" h-[178.27px] left-0 top-0 absolute">
        <div className="w-[129px] h-[23px] left-[293px] top-[60px] absolute rounded-[10px]" />
        <div className="w-[236px] h-[79px] left-[229px] top-[56px] absolute">
          <div className="absolute top-[-30px] right-[300px]">
            <DynamicRank componentName={item.total_rank_string} />
          </div>
          <div className="left-0 top-0 absolute text-center text-zinc-100 text-base font-normal font-['Noto Sans KR']">
            {item.name}
          </div>

          <div className="w-[70px] h-[22px] left-0 top-[55px] absolute rounded-[10px] justify-start items-center gap-[5px] inline-flex">
            <div className="text-center text-zinc-100 text-xs font-normal font-['Noto Sans KR']">수량</div>
            <div className="w-2.5 text-center text-zinc-100 text-xs font-normal font-['Noto Sans KR']">
              {item.stock_count}
            </div>
          </div>
          <div className="w-[167px] h-[26px] left-[69px] top-[53px] absolute rounded-[10px] justify-start items-center gap-[5px] inline-flex">
            <div className="text-center text-zinc-100 text-xs font-normal font-['Noto Sans KR']">자산가치</div>
            <div className="text-center text-zinc-100 text-xs font-normal font-['Noto Sans KR']">
              {(item.stock_close * item.stock_count).toLocaleString()}
            </div>
          </div>
          <div className="whitespace-nowrap h-[26px] left-[200px] top-[53px] absolute rounded-[10px] justify-start items-center gap-[5px] inline-flex">
            <div className="text-center text-zinc-100 text-xs font-normal font-['Noto Sans KR']">
              {item.industry_type}
            </div>
          </div>
          <button onClick={toggleDropdown}>
            <More className="absolute top-[-30px] right-[-15px] cursor-pointer" />
          </button>
          {isDropdownOpen && (
            <div
              ref={dropdownRef}
              className="absolute mt-2 top-[-30px] right-[-18px] bg-white border border-gray-300 rounded shadow-md z-50"
            >
              <ul>
                <li className="px-4 py-2 hover:bg-blue-100 cursor-pointer" onClick={openModal}>
                  수정하기
                </li>
                <li className="px-4 py-2 hover:bg-blue-100 cursor-pointer" onClick={handleDelete}>
                  삭제하기
                </li>
              </ul>
            </div>
          )}
        </div>
      </div>
      {isModalOpen && (
        <div
          className="fixed top-0 left-0 w-screen h-screen flex items-center justify-center bg-black bg-opacity-50 z-50"
          onClick={handleModalBackgroundClick}
        >
          <UpdatePortInfo onClose={closeModal} item={item} updatePortData={updatePortData} />
        </div>
      )}
    </div>
  );
}
export default Stock;
