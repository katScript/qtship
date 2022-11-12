<template>
  <div id="chart">
    <apexchart
      type="bar"
      height="358"
      :options="chartOptions"
      :series="series"
      style="width: 80%"
    ></apexchart>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chartOptions: {
        chart: {
          id: "basic-bar",
          width: '100%',
          animations: {
            speed: 200,
          },
        },
        dataLabels: {
          enabled: false,
        },
        plotOptions: {
          bar: {
            columnWidth: '30px',
            distributed: true,
          },
        },
        xaxis: {
          categories: ["Đơn thành công", "Đơn đang giao", "Đơn phát sinh", "Đơn hoàn"],
        },
      },
      series: [
        {
          name: "đơn",
          data: [30, 40, 45, 30],
        },
      ],
    };
  },
};
</script>

<style scoped>
</style>