import * as React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import { useEffect } from 'react';
import axiosInstance from 'state/AxiosInterceptor';
import axios from 'axios';

interface NewsTabProps {
  dataList: any[]; // 데이터 리스트의 타입에 맞게 수정하세요
  onDataListChange: React.Dispatch<React.SetStateAction<any[]>>;
}

export default function NewsTab({ dataList, onDataListChange }: NewsTabProps) {
  const [value, setValue] = React.useState(0);
  const API_URL = process.env.REACT_APP_API_URL;
  // console.log(data);
  const category_str = [
    '전기,전자',
    '건설업',
    '운수장비',
    '의료정밀',
    '섬유,의복',
    '통신업',
    '제조업',
    '의약품',
    '서비스업',
    '화학',
    '비금속광물',
    '금융업',
    '음식료품',
    '종이,목재',
    '기계',
    '철강및금속',
    '운수창고',
    '유통업',
    '전기가스업',
  ];
  useEffect(() => {
    const fetchData = async () => {
      try {
        const query = `?industry_id=${value + 1}`;
        const response = await axios.get(`${API_URL}/news/${query}`);
        // console.log(response);
        // console.log(response.data.data);
        const dataList = response.data.data;
        onDataListChange(dataList); // 데이터 업데이트를 상위 컴포넌트로 전달
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, [value]);
  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    // newValue 값을 검증하여 0부터 18 사이의 값으로 제한합니다.
    if (newValue >= 0 && newValue < category_str.length) {
      setValue(newValue);
    }
  };

  return (
    <Box sx={{ color: 'white', fontFamily: 'Noto Sans KR' }}>
      <Tabs
        value={value}
        onChange={handleChange}
        variant="scrollable"
        scrollButtons
        allowScrollButtonsMobile
        aria-label="scrollable force tabs example"
        sx={{
          '& .MuiTabs-indicator': {
            backgroundColor: '#00ADB5', // indicator의 색상을 변경
          },
        }}
      >
        {category_str.map((label, index) => (
          <Tab
            key={index}
            label={label}
            sx={{
              color: 'white',
              fontFamily: 'Noto Sans KR',
              '&.Mui-selected': {
                color: '#00ADB5',
              },
            }}
          />
        ))}
      </Tabs>
    </Box>
  );
}
