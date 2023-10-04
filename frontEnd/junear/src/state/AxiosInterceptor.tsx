import axios, { AxiosInstance } from 'axios';

const API_URL = process.env.REACT_APP_API_URL;

const axiosInstance: AxiosInstance = axios.create({
  baseURL: `${API_URL}`,
  withCredentials: true,
});
async function Reissue() {
  try {
    // 리프레시 토큰을 로컬 스토리지에서 가져옵니다.
    const refreshToken = localStorage.getItem('refresh_token');

    if (!refreshToken) {
      // 리프레시 토큰이 없는 경우, 사용자를 로그아웃 처리하거나 다른 조치를 취합니다.
      // 예를 들어, 로그인 페이지로 리디렉션하거나 사용자에게 다시 로그인하라는 메시지를 표시할 수 있습니다.
      // 또는 로그인 페이지로 이동하는 등의 사용자 지정 로직을 구현합니다.
      return;
    }

    // 리프레시 토큰을 사용하여 새로운 엑세스 토큰을 요청합니다.
    const response = await axiosInstance.post('/refresh-token-endpoint', {
      refreshToken: refreshToken,
    });

    // 새로운 엑세스 토큰을 받았을 경우, 이를 로컬 스토리지에 저장합니다.
    if (response.data.access_token) {
      localStorage.setItem('access_token', response.data.access_token);
    }
  } catch (error) {
    // 리프레시 토큰을 사용한 엑세스 토큰 재발급 요청이 실패한 경우
    // 여기서 에러 처리 로직을 구현할 수 있습니다.
    console.error('Error refreshing access token:', error);

    // 예를 들어, 사용자를 로그아웃 처리하거나 다른 조치를 취합니다.
    // 또는 로그인 페이지로 이동하는 등의 사용자 지정 로직을 구현합니다.
  }
}
// Request Interceptor
axiosInstance.interceptors.request.use(
  (config) => {
    const access_token = localStorage.getItem('access_token');
    // console.log(access_token);

    if (access_token) {
      config.headers.Authorization = `Bearer ${access_token}`;
    }
    return config;
  },
  (error) => {
    const originalRequest = error.config;
    console.log(error);
    // 서버에서 401 에러(토큰 만료)를 받은 경우
    if (error.response && error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      // 엑세스 토큰 재발급을 시도합니다.
      // await Reissue();

      // 새로운 엑세스 토큰을 받았으면 이전 요청을 다시 시도합니다.
      originalRequest.headers['Authorization'] = 'Bearer ' + localStorage.getItem('access_token');

      return axiosInstance(originalRequest);
    }
    return Promise.reject(error);
  },
);

export default axiosInstance;
