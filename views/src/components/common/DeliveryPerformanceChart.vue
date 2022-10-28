<template>
  <div class="delivery-performence-chart">
    <div id="chart">
      <vue-apexchart
        type="donut"
        :options="chartOptions"
        :series="series"
         width="400"
      ></vue-apexchart>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      series: [11,89],
      chartOptions: {
        chart: {
          type: "donut",
        },
        labels: ['','Giao thành công'],
        colors:['#ffffff', '#bf1e2d'],
        responsive: [
          {
            breakpoint: 480,
            options: {
              chart: {
                width: 200,
              },
              legend: {
                position: "bottom",
              },
            },
          },
        ],
      },
    }
  },
};
</script>