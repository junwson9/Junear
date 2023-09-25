import { useState } from 'react';
import { motion } from 'framer-motion';
import styled from '@emotion/styled';

interface CardProps {
  front: JSX.Element;
  back: JSX.Element;
}

const Flipper = styled(motion.div)`
  position: relative;
  width: 100%;
  height: 100%;
  perspective: 1000px;
  transform-style: preserve-3d;
`;

const CardSide = styled.div<{ isFlipped?: boolean }>`
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: white;
  border-radius: 20px;
  backface-visibility: hidden;

  transform: ${(props) => (props.isFlipped ? 'rotateY(180deg)' : 'rotateY(0deg)')};
`;

export default function Card({ front, back }: CardProps) {
  const [flipped, setFlipped] = useState(true);

  const flipCard = () => {
    setFlipped(!flipped);
  };

  return (
    <Flipper
      onClick={flipCard}
      whileHover={{ scale: 1.2 }}
      animate={{ rotateY: !flipped ? '180deg' : '0deg' }}
      transition={{
        duration: 0.6,
        ease: [0.45, 0.05, 0.55, 0.95],
      }}
    >
      <CardSide isFlipped={false}>{front}</CardSide>

      <CardSide isFlipped={true}>{back}</CardSide>
    </Flipper>
  );
}
