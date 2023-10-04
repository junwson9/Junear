import { ReactComponent as Srank } from '../../assets/image/Srank.svg';
import { ReactComponent as Aplusrank } from '../../assets/image/A+rank.svg';
import { ReactComponent as Arank } from '../../assets/image/Arank.svg';
import { ReactComponent as Bplusrank } from '../../assets/image/B+rank.svg';
import { ReactComponent as Brank } from '../../assets/image/Brank.svg';
import { ReactComponent as Cplusrank } from '../../assets/image/C+rank.svg';
import { ReactComponent as Crank } from '../../assets/image/Crank.svg';
function DynamicRank({ componentName }: { componentName: any }) {
  const componentMapping: any = {
    s: Srank,
    aPlus: Aplusrank,
    a: Arank,
    bPlus: Bplusrank,
    b: Brank,
    cPlus: Cplusrank,
    c: Crank,
  };
  const Component = componentMapping[componentName];
  return (
    <>
      <Component />
    </>
  );
}
export default DynamicRank;
