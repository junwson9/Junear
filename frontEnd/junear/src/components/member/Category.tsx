import CheckboxLabels from 'components/checkbox/CheckBox';
import CategoryButton from 'components/button/CategoryButton';

function Category() {
  return (
    <div>
      <div className="relative top-[50px] left-[20px]">
        <CheckboxLabels />
        <CheckboxLabels />
        <CheckboxLabels />
        <CheckboxLabels />
        <CheckboxLabels />
        <CheckboxLabels />
        <CheckboxLabels />
        <CheckboxLabels />
        <CheckboxLabels />
        <CheckboxLabels />
        <div className="w-[127.77px] h-[390px] left-[152.23px] top-0 absolute flex-col justify-start items-start inline-flex">
          <CheckboxLabels />
          <CheckboxLabels />
          <CheckboxLabels />
          <CheckboxLabels />
          <CheckboxLabels />
          <CheckboxLabels />
          <CheckboxLabels />
          <CheckboxLabels />
          <CheckboxLabels />
          <CheckboxLabels />
        </div>
      </div>
      <div className="top-[40px] relative">
        <CategoryButton />
      </div>
    </div>
  );
}

export default Category;
