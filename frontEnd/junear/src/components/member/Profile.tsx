type ProfileProps = {
  name: string;
  profileImg: string;
};

function Profile({ name, profileImg }: ProfileProps) {
  // console.log(profileImg);
  return (
    <div>
      <div className="w-[700px] h-[157px] relative">
        <div className="w-[157px] h-[157px] left-0 top-0 absolute bg-zinc-300 rounded-full">
          {profileImg && <img src={profileImg} alt="프로필 이미지" className="w-full h-full rounded-full" />}
        </div>
        <div className="left-[211px] top-[58px] absolute text-center text-white text-3xl ">안녕하세요 {name}님!</div>
      </div>
    </div>
  );
}

export default Profile;
