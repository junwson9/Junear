import './App.css';
import NavBar from 'components/NavBar';
import { Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from 'pages/member/LoginPage';
import MyPage from 'pages/member/MyPage';

function App() {
  return (
    <div className="App">
      <NavBar />
      <div className="grid grid-cols-12 gap-[25px] mx-[200px]">
        <Routes>
          <Route path="/mypage" element={<MyPage />} />
        </Routes>
      </div>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </div>
  );
}

export default App;
