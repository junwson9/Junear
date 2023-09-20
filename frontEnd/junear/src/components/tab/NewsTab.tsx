import * as React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';

export default function NewsTab() {
  const [value, setValue] = React.useState(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  return (
    <Box sx={{ maxWidth: { xs: 1200, sm: 1000 }, color: 'white', fontFamily: 'Noto Sans KR' }}>
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
        <Tab
          label="Item One"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Two"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Three"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Four"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Five"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Six"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Seven"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Seven"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Seven"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Seven"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
        <Tab
          label="Item Seven"
          sx={{
            color: 'white', // 흰색 글자색
            fontFamily: 'Noto Sans KR', // 글꼴
            '&.Mui-selected': {
              color: '#00ADB5', // 선택된 탭의 글자색 초록색
            },
          }}
        />
      </Tabs>
    </Box>
  );
}
