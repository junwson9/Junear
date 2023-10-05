import * as React from 'react';
import LoadingButton from '@mui/lab/LoadingButton';
import Box from '@mui/material/Box';
import axiosInstance from './../../state/AxiosInterceptor';
import { useEffect } from 'react';

interface CategoryButtonProps {
  selectedIndex: number[];
  onCategoryData: (data: any) => void;
}

export default function CategoryButton({ selectedIndex, onCategoryData }: CategoryButtonProps) {
  const [loading, setLoading] = React.useState(false);
  // console.log(selectedIndex);

  const handleClick = async () => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
    }, 500);
    try {
      let responseData = null;
      if (!selectedIndex.includes(0)) {
        const bookmarks = { industry: selectedIndex };
        const queryStringParams = `industryIds=${bookmarks.industry.join(',')}`;
        const response = await axiosInstance.get(`/bookmark?${queryStringParams}`);
        responseData = response.data.data;
      } else {
        const response = await axiosInstance.get(`/bookmark`);
        responseData = response.data.data;
      }

      // 데이터를 상위 컴포넌트로 전달
      onCategoryData(responseData);

      console.log(responseData);
      console.log(111);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };
  return (
    <div>
      <Box
        sx={{
          '& > button': {
            width: '95px',
            height: '34px',
            m: 3,
            fontFamily: 'Noto Sans KR',
            fontSize: '16px',
            borderRadius: '8px',
            color: 'white',
            backgroundColor: '#00ADB5',
            borderColor: '#00ADB5',
            '&:hover': { borderColor: 'white' },
          },
        }}
      >
        <LoadingButton size="small" onClick={handleClick} loading={loading} variant="outlined">
          <span>필터 적용</span>
        </LoadingButton>
      </Box>
    </div>
  );
}
