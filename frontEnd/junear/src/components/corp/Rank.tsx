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
  width?: number; // width 속성 추가
  height?: number; // height 속성 추가
}

function Rank({ rank, width, height }: RankProps) {
  // console.log('RANK  ' + rank);
  if (rank >= 0 && rank < 1) {
    return <C width={width} height={height} />; // width와 height 속성 전달
  } else if (rank >= 1 && rank < 2) {
    // console.log('CPlus');
    return <C_Plus width={width} height={height} />;
  } else if (rank >= 2 && rank < 3) {
    // console.log('B');
    return <B width={width} height={height} />;
  } else if (rank >= 3 && rank < 4) {
    // console.log('BPlus');
    return <B_Plus width={width} height={height} />;
  } else if (rank >= 4 && rank < 5) {
    // console.log('A');
    return <A width={width} height={height} />;
  } else if (rank >= 5 && rank < 6) {
    // console.log('APLUS');
    return <A_Plus width={width} height={height} />;
  } else {
    // console.log('S');
    return <S width={width} height={height} />;
  }
}

export default Rank;
