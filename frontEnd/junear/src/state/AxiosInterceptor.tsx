import axios, { AxiosInstance } from 'axios';

const API_URL = process.env.REACT_APP_API_URL;

const axiosInstance: AxiosInstance = axios.create({
  baseURL: `${API_URL}`,
  withCredentials: true,
});

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
    return Promise.reject(error);
  },
);

export default axiosInstance;
