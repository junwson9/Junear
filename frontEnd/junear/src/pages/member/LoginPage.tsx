import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { useState } from 'react';
import { ReactComponent as Icon_loginPage } from '../../assets/image/login-logo.svg';
import KakaoLogin from 'react-kakao-login';

function LoginPage() {
  const kakaoID = process.env.REACT_APP_KAKAO_CLIENT_ID;
  const googleID = process.env.REACT_APP_GOOGLE_CLIENT_ID;
  const [provider, setProvider] = useState('');
  const [idToken, setIdToken] = useState('');

  const kakaoSuccessHandler = (data: any) => {
    console.log('------카카오 로그인 성공---');
    setIdToken(data.response.id_token);
    setProvider('카카오');
  };
  const kakaoFailHandler = (err: any) => {
    alert('카카오 로그인 실패');
    console.error(err);
  };
  const googleSuccessHandler = (data: any) => {
    console.log('-------구글 로그인 성공 -----');
    console.log(data);
    setIdToken(data.credential);
  };

  const googleFailHandler = () => {
    console.error('Google 로그인 실패');
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
      <div>
        <Icon_loginPage />
      </div>
      <h1>테스트 로그인 Project</h1>
      <KakaoLogin
        token={kakaoID || ''}
        onSuccess={kakaoSuccessHandler}
        onFail={kakaoFailHandler}
        render={(props) => (
          <div>
            <button onClick={props.onClick}>카카오 로그인</button>
          </div>
        )}
      />
      <br />
      <GoogleOAuthProvider clientId={googleID || ''}>
        <GoogleLogin onSuccess={googleSuccessHandler} onError={googleFailHandler} />
      </GoogleOAuthProvider>

      {/* <button onClick={() => googleLoign()}>구글 로그인</button> */}
      <hr />
      <div>-----IDTOKEN-----{provider}</div>
      <div>{idToken}</div>
    </div>
  );
}

export default LoginPage;
