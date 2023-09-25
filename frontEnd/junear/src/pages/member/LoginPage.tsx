import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { ReactComponent as Icon_loginPage } from '../../assets/image/login-logo.svg';
import { ReactComponent as IconKakaoLogin } from '../../assets/image/kakao-logo.svg';
import KakaoLogin from 'react-kakao-login';
import { useCookies } from 'react-cookie';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
// Kakao 로그인 응답 타입

function LoginPage() {
  const kakaoID = process.env.REACT_APP_KAKAO_CLIENT_ID;
  const googleID = process.env.REACT_APP_GOOGLE_CLIENT_ID;
  const [Cookie, setCookie] = useCookies(); // 쿠키를 설정하기 위한 hook을 가져옵니다.
  // console.log(API_URL);
  const navigate = useNavigate();
  const kakaoSuccessHandler = (data: any) => {
    // console.log('------카카오 로그인 성공---');

    const requestData = {
      id_token: data.response.id_token,
      oauth_provider: 'KAKAO',
    };
    axios
      .post<{ access_token: string; refresh_token: string }>('https://dev.junear.cloud/api/member/oauth', requestData)
      .then((response: any) => {
        const { access_token, refresh_token } = response.data.data;
        console.log('data', response.data.data);
        // Access 토큰과 Refresh 토큰을 react-cookie를 사용하여 쿠키에 저장합니다.
        setCookie('access_token', access_token, {
          secure: true, // HTTPS 환경에서만 사용
          httpOnly: true,
          sameSite: 'strict', // 쿠키 SameSite 설정 (필요에 따라 변경 가능)
        });

        setCookie('refresh_token', refresh_token, {
          secure: true, // HTTPS 환경에서만 사용
          httpOnly: true,
          sameSite: 'strict', // 쿠키 SameSite 설정 (필요에 따라 변경 가능)
        });
        navigate('/home');
      })
      .catch((error: any) => {
        console.error('로그인 또는 회원가입에 실패했습니다.', error);
      });
    console.log(Cookie);
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
        console.log('data', response.data.data);
        // Access 토큰과 Refresh 토큰을 react-cookie를 사용하여 쿠키에 저장합니다.
        setCookie('access_token', access_token, {
          secure: true, // HTTPS 환경에서만 사용
          httpOnly: true,
          sameSite: 'strict', // 쿠키 SameSite 설정 (필요에 따라 변경 가능)
        });

        setCookie('refresh_token', refresh_token, {
          secure: true, // HTTPS 환경에서만 사용
          httpOnly: true,
          sameSite: 'strict', // 쿠키 SameSite 설정 (필요에 따라 변경 가능)
        });
        console.log(1111);
        navigate('/home');
      })
      .catch((error: any) => {
        console.error('로그인 또는 회원가입에 실패했습니다.', error);
      });
    console.log(Cookie);
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
