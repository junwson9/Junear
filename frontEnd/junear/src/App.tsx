import './App.css';
import NavBar from 'components/NavBar';
import { Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from 'pages/member/LoginPage';

function App() {
  return (
    <div className="App">
      <NavBar />
      <Routes>
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </div>
  );
}

export default App;
