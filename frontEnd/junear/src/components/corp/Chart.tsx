import { AdvancedRealTimeChart } from 'react-ts-tradingview-widgets';

function Chart() {
  return (
    <AdvancedRealTimeChart
      theme="dark"
      symbol="005930"
      autosize
      timezone="Asia/Seoul"
      enabled_features={['control_bar', 'timeframes_toolbar']}
    ></AdvancedRealTimeChart>
  );
}

export default Chart;
