import React, { useEffect } from 'react';
import axios from 'axios';
import { useCookies } from 'react-cookie';

function Auth() {
  const [, setCookie] = useCookies(); // 쿠키를 설정하기 위한 hook을 가져옵니다.

  useEffect(() => {
    // 로그인 또는 회원가입 API를 호출하여 토큰을 받아옵니다.
    axios
      .post<{ accessToken: string; refreshToken: string }>('member/oauth', {})
      .then((response) => {
        const { accessToken, refreshToken } = response.data;

        // Access 토큰과 Refresh 토큰을 react-cookie를 사용하여 쿠키에 저장합니다.
        setCookie('access_token', accessToken, {
          secure: true, // HTTPS 환경에서만 사용
          httpOnly: true,
          sameSite: 'strict', // 쿠키 SameSite 설정 (필요에 따라 변경 가능)
        });

        setCookie('refresh_token', refreshToken, {
          secure: true, // HTTPS 환경에서만 사용
          httpOnly: true,
          sameSite: 'strict', // 쿠키 SameSite 설정 (필요에 따라 변경 가능)
        });
      })
      .catch((error) => {
        console.error('로그인 또는 회원가입에 실패했습니다.', error);
      });
  }, [setCookie]);
}
export default Auth;
