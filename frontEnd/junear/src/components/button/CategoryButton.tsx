import * as React from 'react';
import LoadingButton from '@mui/lab/LoadingButton';
import Box from '@mui/material/Box';
import axiosInstance from './../../state/AxiosInterceptor';
import { useEffect } from 'react';

interface CategoryButtonProps {
  selectedIndex: number[];
}

export default function CategoryButton({ selectedIndex }: CategoryButtonProps) {
  const [loading, setLoading] = React.useState(false);
  // console.log(selectedIndex);

  const handleClick = async () => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
    }, 500);
    try {
      const bookmarks = { industry: selectedIndex };
      const queryStringParams = `industry=${bookmarks.industry.join(',')}`;
      const response = await axiosInstance.get(`/bookmark?${queryStringParams}`);
      // const response = await axiosInstance.get(`/bookmark`);
      console.log(response);
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
