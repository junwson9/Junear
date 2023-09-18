import * as React from 'react';
import LoadingButton from '@mui/lab/LoadingButton';
import Box from '@mui/material/Box';

export default function CategoryButton() {
  const [loading, setLoading] = React.useState(false);
  function handleClick() {
    setLoading(true); // 버튼 클릭 시 로딩 상태를 true로 설정합니다.
    setTimeout(() => {
      // 1초 후에 로딩 상태를 false로 변경합니다.
      setLoading(false);
    }, 1000); // 1초(1000밀리초) 후에 변경합니다.
  }

  return (
    <div>
      {/* <FormControlLabel
        sx={{
          display: 'block',
        }}
        control={<Switch checked={loading} onChange={() => setLoading(!loading)} name="loading" color="primary" />}
        label="Loading"
      /> */}
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
