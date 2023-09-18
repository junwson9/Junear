import './App.css';
import NavBar from 'components/common/NavBar';
import { Routes, Route } from 'react-router-dom';
import LoginPage from 'pages/member/LoginPage';
import MyPage from 'pages/member/MyPage';
import HomePage from 'pages/main/HomePage';
import CreatePort from 'pages/Portfolio/CreatePort';

function App() {
  return (
    <div className="App">
      <NavBar />
      <div className="grid grid-cols-12 gap-[25px] mx-[200px]">
        <Routes>
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/portfolio" element={<CreatePort />} />
        </Routes>
      </div>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </div>
  );
}

export default App;
