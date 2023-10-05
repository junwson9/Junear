import React from 'react';
import { ReactComponent as BookMarkIcon } from '../../assets/image/bookmark-noclick.svg';
import { ReactComponent as BookMarkIconClick } from '../../assets/image/bookmark-click.svg';

type BookMarkProps = {
  isBookmarked: boolean;
  onClick: () => void;
};

function BookMark({ isBookmarked, onClick }: BookMarkProps) {
  console.log(isBookmarked);
  return isBookmarked ? <BookMarkIconClick onClick={onClick} /> : <BookMarkIcon onClick={onClick} />;
}

export default BookMark;
