// CustomGoogleLoginButton.js

import React from 'react';
import { ReactComponent as IconGoogleLogin } from '../../assets/image/google-icon.svg';

interface CustomGoogleLoginButtonProps {
  onClick: () => void; // onClick 프로퍼티의 타입을 명시합니다.
}

function CustomGoogleLoginButton({ onClick }: CustomGoogleLoginButtonProps) {
  return (
    <div onClick={onClick} style={{ cursor: 'pointer' }}>
      <IconGoogleLogin />
    </div>
  );
}

export default CustomGoogleLoginButton;
