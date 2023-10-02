// A_PLUS.tsx

import React from 'react';
import { ReactComponent as S } from '../../assets/image/Srank.svg';
import { ReactComponent as A_Plus } from '../../assets/image/A+rank.svg';
import { ReactComponent as A } from '../../assets/image/Arank.svg';
import { ReactComponent as B_Plus } from '../../assets/image/B+rank.svg';
import { ReactComponent as B } from '../../assets/image/Brank.svg';
import { ReactComponent as C_Plus } from '../../assets/image/C+rank.svg';
import { ReactComponent as C } from '../../assets/image/Crank.svg';

interface RankProps {
  rank: number; // 랭크 값을 받아오도록 수정
}

const Rank: React.FC<RankProps> = ({ rank }) => {
  // rank 값에 따라 다른 내용을 표시
  console.log('RANK  ' + rank);
  if (rank >= 0 && rank < 1) {
    return <C />;
  } else if (rank >= 1 && rank < 2) {
    console.log('CPlus');
    return <C_Plus />;
  } else if (rank >= 2 && rank < 3) {
    console.log('B');
    return <B />;
  } else if (rank >= 3 && rank < 4) {
    console.log('BPlus');
    return <B_Plus />;
  } else if (rank >= 4 && rank < 5) {
    console.log('A');
    return <A />;
  } else if (rank >= 5 && rank < 6) {
    console.log('APLUS');
    return <A_Plus />;
  } else {
    return <S />;
  }
};

export default Rank;
