import Profile from 'components/member/Profile';
import { useRecoilState } from 'recoil';
import Category from 'components/member/Category';
import axiosInstance from './../../state/AxiosInterceptor';
import { ProfileImageState } from '../../recoil/atom';
import { useEffect, useState } from 'react';
import { ReactComponent as BookMarkIconClick } from '../../assets/image/bookmark-click.svg';
import { useNavigate } from 'react-router-dom';
import queryString from 'query-string';
interface BookmarkItem {
  corporation_id: number;
  corporation_name: string;
  industry_type: string;
}
function MyPage() {
  const [bookmark, setBookmark] = useState<number[]>([]);
  const [bookmarks, setBookmarks] = useState<number[]>([]);
  const [bookmark_lst, setBookmark_lst] = useState([]);
  const [name, setName] = useState<string>('');
  const [profileImg, setProfileImg] = useRecoilState(ProfileImageState);
  console.log(bookmark_lst);
  const navigate = useNavigate();
  // console.log(bookmark);
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
        console.log(response.data.data);
        setBookmark_lst(response.data.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);
  const handleCategoryData = (data: any) => {
    setBookmark_lst(data);
    console.log(bookmark_lst);
  };
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
          <Category selectedIndex={bookmarks} onSelectedIndexesChange={setBookmark} onCategoryData={setBookmark_lst} />
        </div>
      </div>
      <div className="grid grid-cols-2 col-start-5 col-end-13 mt-[5px] mb-[20px] h-[557px] bg-zinc-700 rounded-[20px] overflow-auto">
        {/* 왼쪽 칼럼 */}
        <div className="col-start-1 col-end-2 mt-5">
          <div className="mt-[5px] mb-[20px] bg-zinc-700 rounded-bl-[20px]">
            {bookmark_lst.map(
              (item: BookmarkItem, index) =>
                index % 2 === 0 && (
                  <div key={index} className="flex mt-5 ml-10">
                    <div className="mr-3">
                      <BookMarkIconClick />
                    </div>
                    <div
                      className="cursor-pointer"
                      style={{ whiteSpace: 'pre', color: 'white', fontSize: '16px' }}
                      onClick={() => {
                        navigate(`/corporation/${item.corporation_id}`);
                      }}
                    >
                      {item.corporation_name}{' '}
                    </div>
                    <div style={{ color: 'gray', fontSize: '16px' }}> | {item.industry_type}</div>
                  </div>
                ),
            )}
          </div>
        </div>

        {/* 오른쪽 칼럼 */}
        <div className="col-start-2 col-end-3 mt-5">
          <div className="mt-[5px] mb-[20px] bg-zinc-700 rounded-br-[20px] ">
            {bookmark_lst.map(
              (item: BookmarkItem, index) =>
                index % 2 !== 0 && (
                  <div key={index} className="flex mt-5 ml-10">
                    <div className="mr-3">
                      <BookMarkIconClick />
                    </div>
                    <div
                      className="cursor-pointer"
                      style={{ whiteSpace: 'pre', color: 'white', fontSize: '16px' }}
                      onClick={() => {
                        navigate(`/corporation/${item.corporation_id}`);
                      }}
                    >
                      {item.corporation_name}{' '}
                    </div>
                    <div style={{ color: 'gray', fontSize: '16px' }}> | {item.industry_type}</div>
                  </div>
                ),
            )}
          </div>
        </div>
      </div>
    </>
  );
}

export default MyPage;
