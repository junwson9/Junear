import React from 'react';
import { ReactComponent as S } from '../../assets/image/Srank.svg';
import { ReactComponent as A_Plus } from '../../assets/image/A+rank.svg';
import { ReactComponent as A } from '../../assets/image/Arank.svg';
import { ReactComponent as B_Plus } from '../../assets/image/B+rank.svg';
import { ReactComponent as B } from '../../assets/image/Brank.svg';
import { ReactComponent as C_Plus } from '../../assets/image/C+rank.svg';
import { ReactComponent as C } from '../../assets/image/Crank.svg';

interface RankProps {
  rank: number;
}

const Rank: React.FC<RankProps> = ({ rank }) => {
  console.log('RANK  ' + rank);

  if (rank < 1) {
    return <C />;
  }

  if (rank < 2) {
    console.log('CPlus');
    return <C_Plus />;
  }

  if (rank < 3) {
    console.log('B');
    return <B />;
  }

  if (rank < 4) {
    console.log('BPlus');
    return <B_Plus />;
  }

  if (rank < 5) {
    console.log('A');
    return <A />;
  }

  if (rank < 6) {
    console.log('APLUS');
    return <A_Plus />;
  }

  // 기본적으로 S 아이콘을 반환합니다.
  console.log('S');
  return <S />;
};

export default Rank;
