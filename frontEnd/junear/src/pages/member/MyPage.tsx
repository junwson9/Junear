import Profile from 'components/member/Profile';
import Category from 'components/member/Category';
import CategoryButton from './../../components/button/CategoryButton';

function MyPage() {
  return (
    <>
      <div className="col-span-3 mt-[55px]">
        <Profile />
      </div>
      <div className="col-start-1 col-end-4 ml-[20px] mt-[20px]">
        <p className="text-white font-bold text-[28px] text-left">카테고리</p>
      </div>
      <div className="col-start-4 col-end-12 ml-[20px] mt-[20px]">
        <p className="text-white font-bold text-[28px] text-left">내 관심기업</p>
      </div>
      <div className="col-start-1 col-end-4 mt-[10px] mb-[20px] h-[557px] bg-zinc-700 rounded-[20px]">
        <div className="justify-center items-center">
          <Category />
        </div>
      </div>
      <div className="col-start-4 col-end-13 mt-[10px] mb-[20px] h-[557px] bg-zinc-700 rounded-[20px] "></div>

      {/* <div className=" text-white text-[28px] font-bold ">카테고리 필터</div> */}
      {/* <div className="mt-[40px] col-start-1 col-end-4">
        <Category />
      </div> */}
    </>
  );
}

export default MyPage;
