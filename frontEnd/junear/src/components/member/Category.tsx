import CheckboxLabels from 'components/checkbox/CheckBox';
import CategoryButton from 'components/button/CategoryButton';
import { useState } from 'react';
import { useEffect } from 'react';
interface CategoryProps {
  selectedIndex: number[];
  onSelectedIndexesChange: (indexes: number[]) => void;
  onCategoryData: (data: any) => void; // 추가
}

function Category({ selectedIndex, onSelectedIndexesChange, onCategoryData }: CategoryProps) {
  const category_str1 = [
    '전체보기',
    '전기,전자',
    '건설업',
    '운수장비',
    '의료정밀',
    '섬유,의복',
    '통신업',
    '제조업',
    '의약품',
    '서비스업',
  ];
  const category_str2 = [
    '화학',
    '비금속광물',
    '금융업',
    '음식료품',
    '종이,목재',
    '기계',
    '철강및금속',
    '운수창고',
    '유통업',
    '전기가스업',
  ];
  const [selectedIndexes, setSelectedIndexes] = useState<number[]>(selectedIndex);
  const [categoryData, setCategoryData] = useState<any>(null);
  // console.log(categoryData);
  const handleCategoryData = (data: any) => {
    setCategoryData(data);
    console.log(categoryData);
    onCategoryData(data);
  };

  // selectedIndex가 변경될 때 selectedIndexes를 업데이트
  useEffect(() => {
    setSelectedIndexes(selectedIndex);
  }, [selectedIndex]);

  const handleCheckboxChange = (index: number, checked: boolean) => {
    if (checked) {
      setSelectedIndexes((prevIndexes) => [...prevIndexes, index]);
    } else {
      setSelectedIndexes((prevIndexes) => prevIndexes.filter((i) => i !== index));
    }
    onSelectedIndexesChange(selectedIndexes);
    console.log(selectedIndexes);
  };
  return (
    <div>
      <div className="flex">
        <div className="flex-1 mt-8 mr-8">
          {category_str1.map((category, index) => (
            <div className="flex-1 " key={index}>
              <CheckboxLabels label={category} index={index} onChange={handleCheckboxChange} />
            </div>
          ))}
        </div>
        <div className="flex-2 mt-8">
          {category_str2.map((category, index) => (
            <div className="flex-2" key={index}>
              <CheckboxLabels label={category} index={index + 10} onChange={handleCheckboxChange} />
            </div>
          ))}
        </div>
      </div>
      <div className="top-[20px] relative">
        <CategoryButton selectedIndex={selectedIndexes} onCategoryData={handleCategoryData} />
      </div>
    </div>
  );
}

export default Category;
