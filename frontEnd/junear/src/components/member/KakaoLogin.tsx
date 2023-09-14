import { useNavigate } from 'react-router-dom';

function KakaoLoginPage() {
  const navigate = useNavigate();
  return (
    <div>
      카카오 로그인 결과
      <button onClick={() => navigate('/')}>뒤로가기</button>
    </div>
  );
}

export default KakaoLoginPage;
