import Profile from 'components/member/Profile';
import { useRecoilState } from 'recoil';
import Category from 'components/member/Category';
import axiosInstance from './../../state/AxiosInterceptor';
import { ProfileImageState } from '../../recoil/atom';
import { useEffect, useState } from 'react';

import queryString from 'query-string';

function MyPage() {
  const [bookmark, setBookmark] = useState<number[]>([]);
  const [bookmarks, setBookmarks] = useState<number[]>([]);
  const [name, setName] = useState<string>('');
  const [profileImg, setProfileImg] = useRecoilState(ProfileImageState);

  console.log(bookmark);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get('/member/info');
        // console.log(response);
        setName(response.data.data.name);
        setProfileImg(response.data.data.profile_image);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(`/bookmark`);
        console.log(response);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      <div className="col-span-3 mt-[100px]">
        <Profile name={name} profileImg={profileImg} />
      </div>
      <div className="col-start-1 col-end-5 ml-[20px] mt-[20px]">
        <p className="text-white font-bold text-[28px] text-left">카테고리</p>
      </div>
      <div className="col-start-5 col-end-13 ml-[20px] mt-[20px]">
        <p className="text-white font-bold text-[28px] text-left">내 관심기업</p>
      </div>
      <div className="col-start-1 col-end-5 mt-[5px] mb-[20px] h-[557px] bg-zinc-700 rounded-[20px]">
        <div className="flex justify-center items-center">
          <Category selectedIndex={bookmarks} onSelectedIndexesChange={setBookmark} />
        </div>
      </div>
      <div className="col-start-5 col-end-13 mt-[5px] mb-[20px] h-[557px] bg-zinc-700 rounded-[20px] "></div>
    </>
  );
}

export default MyPage;
