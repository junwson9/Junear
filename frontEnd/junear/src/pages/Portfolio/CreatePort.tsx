function CreatePort() {
  return (
    <>
      <div className="col-span-12 text-white mt-[125px] text-[28px] text-center">보유중인 주식을 추가해 주십시오.</div>
      <div className="col-span-12 mt-[5px]"></div>
      <div className="col-start-1 col-end-6 h-[420px] bg-zinc-700 rounded-[20px]"></div>
      <div className="col-start-8 col-end-13 h-[420px] bg-zinc-700 rounded-[20px]"></div>
      <div className="col-span-12 mt-[5px]"></div>
      <div className="relative col-span-12">
        <button className="absolute text-white bg-teal-500 rounded-[20px] p-[14px] w-[250px] whitespace-nowrap left-[430px]">
          포트폴리오 만들기
        </button>
      </div>

      <div className="col-span-12 mt-[80px]"></div>
    </>
  );
}
export default CreatePort;
