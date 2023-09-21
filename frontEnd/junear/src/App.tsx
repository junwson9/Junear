import './App.css';
import NavBar from 'components/common/NavBar';
import { Routes, Route } from 'react-router-dom';
import LoginPage from 'pages/member/LoginPage';
import MyPage from 'pages/member/MyPage';
import HomePage from 'pages/main/HomePage';
import CreatePort from 'pages/Portfolio/CreatePort';
import MyPort from 'pages/Portfolio/MyPort';
import News from 'pages/news/new';
import CorpSearch from 'pages/corp/CorpSearch';
import CorpDetail from 'pages/corp/CorpDetail';

function App() {
  return (
    <div className="App">
      <NavBar />
      <div className="grid grid-cols-12 gap-[25px] mx-[200px]">
        <Routes>
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/portfolio" element={<CreatePort />} />
          <Route path="/my-portfolio" element={<MyPort />} />
          <Route path="/news" element={<News />} />
          <Route path="/corperation-search" element={<CorpSearch />} />
          <Route path="/corperation/:corp" element={<CorpDetail />} />
        </Routes>
      </div>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </div>
  );
}

export default App;
