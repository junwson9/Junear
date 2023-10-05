import React from 'react';
import ReactApexChart from 'react-apexcharts';

interface AbstractChartProps {
  series: number[];
  labels: string[];
}
const AbstractChart: React.FC<AbstractChartProps> = ({ series, labels }) => {
  const options: any = {
    chart: {
      type: 'bar',
      width: 300,
    },

    legend: {
      position: 'right',
      labels: {
        colors: '#fff', // 글자색을 흰색으로 설정
      },
      itemMargin: {
        horizontal: 5,
        vertical: 5,
      },
      offsetY: -15,
      offsetX: -25,
      horizontalAlign: 'left',
    },
    plotOptions: {
      bar: {
        dataLabels: {
          enabled: false, // 데이터 레이블 숨기기
        },
        donut: {
          labels: {
            show: true,
            name: {
              fontFamily: 'Noto Sans KR',
              color: '#fff',
            },
            value: {
              color: '#fff',
            },
          },
        },
      },
    },
    dataLabels: {
      enabled: false,
    },
    colors: ['#142459', '#176ba0', '#19aade', '#1ac9e6', '#6dfdd2', '#1de4bd'],
    labels: labels,
  };

  return (
    <div id="chart">
      <ReactApexChart options={options} series={series} type="donut" width={420} />
    </div>
  );
};

export default AbstractChart;
