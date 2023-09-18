import './App.css';
import NavBar from 'components/common/NavBar';
import { Routes, Route } from 'react-router-dom';
import LoginPage from 'pages/member/LoginPage';
import MyPage from 'pages/member/MyPage';
import HomePage from 'pages/main/HomePage';
import CreatePort from 'pages/Portfolio/CreatePort';
import MyPort from 'pages/Portfolio/MyPort';

function App() {
  return (
    <div className="App">
      <div>
        <NavBar />
        <div className="grid grid-cols-12 gap-[25px] mx-[200px]">
          <Routes>
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/home" element={<HomePage />} />
            <Route path="/portfolio" element={<CreatePort />} />
            <Route path="/my-portfolio" element={<MyPort />} />
          </Routes>
        </div>
      </div>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </div>
  );
}

export default App;
