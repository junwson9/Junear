import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { useState } from 'react';
import { ReactComponent as Icon_loginPage } from '../../assets/image/login-logo.svg';
import { ReactComponent as IconKakaoLogin } from '../../assets/image/kakao-logo.svg';
import KakaoLogin from 'react-kakao-login';

function LoginPage() {
  const kakaoID = process.env.REACT_APP_KAKAO_CLIENT_ID;
  const googleID = process.env.REACT_APP_GOOGLE_CLIENT_ID;
  const [provider, setProvider] = useState('');
  const [idToken, setIdToken] = useState('');

  const kakaoSuccessHandler = (data: any) => {
    console.log('------카카오 로그인 성공---');
    setIdToken(data.response.id_token);
    console.log(idToken);
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
      <div>-----IDTOKEN-----{provider}</div>
      <div>{idToken}</div>
    </div>
  );
}

export default LoginPage;
