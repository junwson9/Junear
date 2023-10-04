import React from 'react';
import { ReactComponent as BookMarkIcon } from '../../assets/image/bookmark-noclick.svg';
import { ReactComponent as BookMarkIconClick } from '../../assets/image/bookmark-click.svg';

type BookMarkProps = {
  isBookmarked: boolean;
  onClick: () => void;
};

function BookMark({ isBookmarked, onClick }: BookMarkProps) {
  // isBookmarked 값에 따라 아이콘을 표시하거나 숨깁니다.

  return isBookmarked ? <BookMarkIconClick onClick={onClick} /> : <BookMarkIcon onClick={onClick} />;
}

export default BookMark;
