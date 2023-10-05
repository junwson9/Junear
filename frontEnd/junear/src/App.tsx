import './App.css';
import NavBar from 'components/common/NavBar';
import { Routes, Route, useLocation } from 'react-router-dom'; // useLocation 추가
import LoginPage from 'pages/member/LoginPage';
import MyPage from 'pages/member/MyPage';
import HomePage from 'pages/main/HomePage';
import CreatePort from 'pages/Portfolio/CreatePort';
import MyPort from 'pages/Portfolio/MyPort';
import News from 'pages/news/new';
import CorpSearch from 'pages/corp/CorpSearch';
import CorpDetail from 'pages/corp/CorpDetail';
import AddPort from 'pages/Portfolio/AddPort';

function App() {
  const location = useLocation(); // 현재 경로를 가져오기 위해 useLocation 사용

  // 특정 경로에서만 NavBar를 표시하도록 조건 설정
  const showNavBar = location.pathname !== '/login';

  return (
    <div className="App">
      {showNavBar && <NavBar />}
      <div className="grid grid-cols-12 gap-[25px] mx-[200px]">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/portfolio" element={<CreatePort />} />
          <Route path="/my-portfolio" element={<MyPort />} />
          <Route path="/news" element={<News />} />
          <Route path="/corporation-search" element={<CorpSearch />} />
          <Route path="/corporation/:corp" element={<CorpDetail />} />
          <Route path="/add-portfolio" element={<AddPort />} />
        </Routes>
      </div>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </div>
  );
}

export default App;
