// A_PLUS.tsx

import React from 'react';

interface APlusProps {
  rank: number; // 랭크 값을 받아오도록 수정
}

const APlus: React.FC<APlusProps> = ({ rank }) => {
  // rank 값에 따라 다른 내용을 표시
  let content;
  switch (rank) {
    case 0:
      content = '컴포넌트 내용 0';
      break;
    case 1:
      content = '컴포넌트 내용 1';
      break;
    // 다른 랭크에 따른 내용 추가
    default:
      content = '기본 컴포넌트 내용';
  }

  return <div>{content}</div>;
};

export default APlus;
