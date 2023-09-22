import CheckboxLabels from 'components/checkbox/CheckBox';
import CategoryButton from 'components/button/CategoryButton';

function Category() {
  return (
    <div>
      <div className="flex">
        <div className="flex-1 mt-8 mr-8">
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
        <div className="flex-2 mt-8">
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
      <div className="top-[20px] relative">
        <CategoryButton />
      </div>
    </div>
  );
}

export default Category;
