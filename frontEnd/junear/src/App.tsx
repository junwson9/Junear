import './App.css';
import NavBar from 'components/common/NavBar';
import { Routes, Route } from 'react-router-dom';
import LoginPage from 'pages/member/LoginPage';
import HomePage from 'pages/main/HomePage';
import CreatePort from 'pages/Portfolio/CreatePort';

function App() {
  return (
    <div className="App">
      <NavBar />
      <div className="grid grid-cols-12 gap-[25px] mx-[200px]">
        <Routes>
          <Route path="/home" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/portfolio" element={<CreatePort />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
