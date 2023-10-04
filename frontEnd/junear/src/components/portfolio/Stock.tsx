import DynamicRank from './DynamicRank';

function Stock({ item }: { item: any }) {
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

          <div className="w-[63px] h-[22px] left-0 top-[55px] absolute rounded-[10px] justify-start items-center gap-[5px] inline-flex">
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
        </div>
      </div>
    </div>
  );
}
export default Stock;
