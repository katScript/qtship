<template>
  <div class="delivery-performence-chart">
    <div id="chart">
      <apexchart type="radialBar" height="350" :options="chartOptions" :series="series"></apexchart>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {

        series: [70],
        chartOptions: {
          chart: {
            height: 350,
            type: 'radialBar',
          },
          
          plotOptions: {
            radialBar: {
              hollow: {
                size: '70%',
              },
            },
          },
          fill:{
            // colors: ['#198754']
          },
          labels: ['Thành công'],
          
        },
      }
    },
  };
</script>