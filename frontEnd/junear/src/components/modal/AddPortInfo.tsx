import { useState } from 'react';

function AddPortInfo({
  onClose,
  onAdd,
}: {
  onClose: () => void;
  onAdd: (data: { quantity: string; averagePrice: string }) => void;
}) {
  const [quantity, setQuantity] = useState<string>('');
  const [averagePrice, setAveragePrice] = useState<string>('');

  const handleQuantityChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuantity(e.target.value);
  };

  const handleAveragePriceChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAveragePrice(e.target.value);
  };

  const handleAddClick = () => {
    // 입력된 수량과 평단가를 한 객체로 전달
    const data = { quantity, averagePrice };
    onAdd(data);

    // 입력값 초기화
    setQuantity('');
    setAveragePrice('');
    onClose();
  };

  return (
    <div className="w-[250px] h-[250px] relative">
      <div className="w-[250px] h-[250px] left-0 top-0 absolute bg-zinc-700 rounded-[20px] shadow" />
      <div className="left-[39px] top-[26px] absolute text-center text-zinc-100 text-base font-normal font-['Noto Sans KR']">
        수량과 구매하신 평단가를 <br />
        입력해주세요.
      </div>
      <div className="left-[31px] top-[95px] absolute text-center text-zinc-100 text-base font-normal font-['Noto Sans KR']">
        수량
      </div>
      <div className="left-[31px] top-[138px] absolute text-center text-zinc-100 text-base font-normal font-['Noto Sans KR']">
        평단가
      </div>
      <input
        type="text"
        placeholder="수량을 입력하세요."
        className="left-[86px] top-[95px] w-[140px] absolute text-right text-zinc-100 text-base font-normal font-['Noto Sans KR'] bg-zinc-700 focus:outline-none"
        value={quantity}
        onChange={handleQuantityChange}
      />
      <input
        type="text"
        placeholder="평단가를 입력하세요."
        className="left-[86px] top-[138px] w-[140px] absolute text-right text-zinc-100 text-base font-normal font-['Noto Sans KR'] bg-zinc-700 focus:outline-none"
        value={averagePrice}
        onChange={handleAveragePriceChange}
      />
      <button
        className="w-[76px] h-[27px] pl-4 pr-[15px] left-[31px] top-[184px] absolute bg-teal-500 rounded-lg justify-center items-center inline-flex"
        onClick={handleAddClick}
      >
        <div className="text-center text-white text-xs font-normal font-['Noto Sans KR']">추가하기</div>
      </button>
      <button
        className="w-[76px] h-[27px] pl-[27px] pr-[26px] left-[143px] top-[184px] absolute bg-neutral-400 rounded-lg justify-center items-center inline-flex"
        onClick={onClose}
      >
        <div className="text-center text-white text-xs font-normal font-['Noto Sans KR']">취소</div>
      </button>
    </div>
  );
}

export default AddPortInfo;
