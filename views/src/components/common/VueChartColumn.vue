<template>
  <div id="chart">
    <apexchart type="bar" height="358" :options="chartOptions" :series="series"></apexchart>
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
        Đơn hủy <a href="#" class="a-detail-right">Chi tiết</a>
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    props: [
      "totalPriceOrderSuccess",
      "totalPriceOrderDelivery",
      "totalPriceOrderOccurred",
      "totalPriceOrderCancel",
    ],
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
            categories: [
              "Đơn thành công",
              "Đơn đang giao",
              "Đơn phát sinh",
              "Đơn hủy",
            ],
          },
        },
        series: [
          {
            name: "VNĐ",
            data: [],
          },
        ],
      };
    },
    watch: {
      totalPriceOrderSuccess: {
        handler: function () {
          this.applyDataChart();
        },
      },
      totalPriceOrderDelivery: {
        handler: function () {
          this.applyDataChart();
        },
      },
      totalPriceOrderOccurred: {
        handler: function () {
          this.applyDataChart();
        },
      },
      totalPriceOrderCancel: {
        handler: function () {
          this.applyDataChart();
        },
      },
    },
    methods: {
      applyDataChart() {
        this.series[0].data = [
          this.totalPriceOrderSuccess,
          this.totalPriceOrderDelivery,
          this.totalPriceOrderOccurred,
          this.totalPriceOrderCancel,
        ];
      },
    },
  };
</script>

<style scoped>
  .a-detail-right {
    right: 0;
    position: absolute;
    text-decoration: none;
  }

  * {
    overflow: hidden;
  }
</style>