import { AdvancedRealTimeChart } from 'react-ts-tradingview-widgets';
import { useEffect, useState } from 'react';

interface ChartProps {
  corporationCode: string;
}

function Chart({ corporationCode }: ChartProps) {
  const [symbol, setSymbol] = useState<string | undefined>(undefined);
  console.log(1111);
  useEffect(() => {
    const constructedSymbol = corporationCode;

    setSymbol(constructedSymbol);
  }, [corporationCode]); // corporationCode가 변경될 때에만 실행

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
