import * as React from 'react';
import FormGroup from '@mui/material/FormGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';

export default function CheckboxLabels() {
  const [checked, setChecked] = React.useState(false);
  const labelStyle = {
    fontFamily: 'Noto Sans KR', // 원하는 글꼴로 변경
    color: 'white', // 원하는 색상으로 변경
  };

  const checkboxStyle = {
    color: 'white', // 체크 박스 색상
    '&.Mui-checked': {
      color: 'white', // 체크되었을 때 색상
    },
    '&.Mui-checked .MuiSvgIcon-root': {
      borderColor: 'white', // 체크 박스 테두리 색상
    },
  };

  const handleChange = (event: any) => {
    setChecked(event.target.checked);
  };

  return (
    <FormGroup>
      <FormControlLabel
        control={<Checkbox checked={checked} onChange={handleChange} sx={checkboxStyle} />}
        style={labelStyle}
        label="카테고리"
        labelPlacement="end"
      />
    </FormGroup>
  );
}
