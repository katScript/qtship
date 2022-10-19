<template>
  <div id="chart">
    <apexchart
      type="bar"
      height="358"
      :options="chartOptions"
      :series="series"
    ></apexchart>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">
        Đơn thành công <a href="#" class="a-detail-right">Chi tiết</a>
      </li>
      <li class="list-group-item">
        Đơn đang giao <a href="#" class="a-detail-right">Chi tiết</a>
      </li>
      <li class="list-group-item">
        Đơn phát sinh <a href="#" class="a-detail-right">Chi tiết</a>
      </li>
      <li class="list-group-item">
        Đơn hoàn <a href="#" class="a-detail-right">Chi tiết</a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chartOptions: {
        chart: {
          id: "basic-bar",
          animations: {
            speed: 200,
          },
        },
        dataLabels: {
          enabled: false,
        },
        plotOptions: {
          bar: {
            distributed: true,
          },
        },
        xaxis: {
          categories: [1991, 1992, 1993, 1994],
        },
      },
      series: [
        {
          name: "VNĐ",
          data: [30, 40, 45, 30],
        },
      ],
    };
  },
};
</script>

<style scoped>
.a-detail-right {
  right: 0;
  position: absolute;
  text-decoration: none;
}
</style>