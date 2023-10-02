import { AdvancedRealTimeChart } from 'react-ts-tradingview-widgets';
import { useEffect, useState } from 'react';
interface ChartProps {
  corporationCode: string; // corporationCode prop 정의
}

function Chart({ corporationCode }: ChartProps) {
  const [symbol, setSymbol] = useState<string | undefined>(undefined);

  // corporationCode가 변경될 때마다 symbol을 업데이트
  useEffect(() => {
    // corporationCode를 사용하여 symbol을 구성
    // 예: '005930'을 corporationCode에 대체
    const constructedSymbol = corporationCode; // 수정 필요

    // symbol 상태 변수를 업데이트
    setSymbol(constructedSymbol);
  }, [corporationCode]);
  return (
    <AdvancedRealTimeChart
      theme="dark"
      symbol={symbol}
      autosize
      timezone="Asia/Seoul"
      enabled_features={['control_bar', 'timeframes_toolbar']}
    ></AdvancedRealTimeChart>
  );
}

export default Chart;
