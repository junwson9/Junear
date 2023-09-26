import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { ReactComponent as Icon_loginPage } from '../../assets/image/login-logo.svg';
import { ReactComponent as IconKakaoLogin } from '../../assets/image/kakao-logo.svg';
import KakaoLogin from 'react-kakao-login';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
// Kakao 로그인 응답 타입

function LoginPage() {
  const kakaoID = process.env.REACT_APP_KAKAO_CLIENT_ID;
  const googleID = process.env.REACT_APP_GOOGLE_CLIENT_ID;
  const navigate = useNavigate();
  const kakaoSuccessHandler = (data: any) => {
    const requestData = {
      id_token: data.response.id_token,
      oauth_provider: 'KAKAO',
    };
    axios
      .post<{ access_token: string; refresh_token: string }>('https://dev.junear.cloud/api/member/oauth', requestData)
      .then((response: any) => {
        const { access_token, refresh_token } = response.data.data;
        console.log('data', response.data.data);
        localStorage.setItem('access_token', access_token);
        localStorage.setItem('refresh_token', refresh_token);
        navigate('/');
      })
      .catch((error: any) => {
        console.error('로그인 또는 회원가입에 실패했습니다.', error);
      });
  };
  const kakaoFailHandler = (err: any) => {
    alert('카카오 로그인 실패');
    console.error(err);
  };
  const googleSuccessHandler = (data: any) => {
    console.log('-------구글 로그인 성공 -----');
    console.log(data);
    const requestData = {
      id_token: data.credential,
      oauth_provider: 'GOOGLE',
    };
    axios
      .post<{ access_token: string; refresh_token: string }>('https://dev.junear.cloud/api/member/oauth', requestData)
      .then((response: any) => {
        const { access_token, refresh_token } = response.data.data;
        localStorage.setItem('access_token', access_token);
        localStorage.setItem('refresh_token', refresh_token);
        navigate('/');
      })
      .catch((error: any) => {
        console.error('로그인 또는 회원가입에 실패했습니다.', error);
      });
  };

  const googleFailHandler = () => {
    console.error('Google 로그인 실패');
  };

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100vh',
      }}
    >
      <div>
        <Icon_loginPage />
      </div>
      <KakaoLogin
        token={kakaoID || ''}
        onSuccess={kakaoSuccessHandler}
        onFail={kakaoFailHandler}
        render={(props) => (
          <div>
            {/* <button onClick={props.onClick}>카카오 로그인</button> */}
            <IconKakaoLogin onClick={props.onClick} />
          </div>
        )}
      />
      <br />
      <GoogleOAuthProvider clientId={googleID || ''}>
        <div>
          <GoogleLogin onSuccess={googleSuccessHandler} onError={googleFailHandler} />
        </div>
      </GoogleOAuthProvider>
    </div>
  );
}

export default LoginPage;
