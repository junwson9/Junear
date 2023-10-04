import * as React from 'react';
import FormGroup from '@mui/material/FormGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';

export default function CheckboxLabels({
  label,
  index,
  onChange,
}: {
  label: string;
  index: number;
  onChange: (index: number, checked: boolean) => void;
}) {
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

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const isChecked = event.target.checked;
    setChecked(isChecked);
    onChange(index, isChecked); // 상태 변경 시 부모 컴포넌트에 인덱스와 체크 상태를 전달
  };

  return (
    <FormGroup>
      <FormControlLabel
        control={<Checkbox checked={checked} onChange={handleChange} sx={checkboxStyle} />}
        style={labelStyle}
        label={label}
        labelPlacement="end"
      />
    </FormGroup>
  );
}
