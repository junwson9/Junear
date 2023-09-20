export default function NewsOne() {
  return (
    <div className="w-[1036px] h-[140px] mt-[10px] pb-5 flex-col justify-start items-start inline-flex">
      <div className="w-[817px] h-[120px] relative">
        <div className="w-[120px] h-[120px] left-[25px] top-0 absolute bg-zinc-300 rounded-[10px]" />
        <img
          className="w-[120px] h-[120px] left-[25px] top-0 absolute rounded-[10px]"
          src="https://via.placeholder.com/120x120"
        />
        <div className="w-[630px] left-[170px] top-[29px] absolute text-white text-lg font-normal font-['Noto Sans KR']">
          삼성전기 IT 기술력 전기차 시대 빛 본다, 장덕현 전장용 전자소자 사업 확대
        </div>
        <div className="w-[330px] h-11 left-[125px] top-[76px] absolute text-neutral-400 text-base font-normal font-['Noto Sans KR'] leading-[30px]">
          파이낸셜뉴스 | 1시간 전
        </div>
      </div>
    </div>
  );
}
