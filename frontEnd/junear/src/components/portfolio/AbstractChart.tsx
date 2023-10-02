import React from 'react';
import ReactApexChart from 'react-apexcharts';

const AbstractChart: React.FC = () => {
  const series = [4, 55, 41, 17, 15, 34, 53, 53, 53, 11];

  const options: any = {
    chart: {
      type: 'donut',
    },
    responsive: [
      {
        breakpoint: 480,
        options: {
          chart: {
            width: 500,
          },
          plotOptions: {
            // plotOptions 추가
            pie: {
              dataLabels: {
                enabled: false,
              },
            },
          },
          legend: {
            position: 'bottom',
          },
        },
      },
    ],
    legend: {
      labels: {
        colors: '#fff', // 글자색을 흰색으로 설정
      },
      itemMargin: {
        horizontal: 5,
        vertical: 5,
      },
      offsetY: -15,
      offsetX: -25,
    },
    plotOptions: {
      pie: {
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
  };

  return (
    <div id="chart">
      <ReactApexChart options={options} series={series} type="donut" width={'350px'} />
    </div>
  );
};

export default AbstractChart;
