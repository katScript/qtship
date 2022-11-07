<template>
  <div>
    <vue-apexchart
      width="500"
      type="pie"
      :options="chartOptions"
      :series="series"
    ></vue-apexchart>
    <br />
    <br />
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
  props: [
    "numberOrderDelivery",
    "numberOrderSuccess",
    "numberOrderCancel",
    "numberOrderOccurred",
  ],
  data() {
    return {
      series: [],
      chartOptions: {
        chart: {
          width: 380,
          type: "pie",
        },
        labels: ["Đơn thành công", "Đơn đang giao", "Đơn phát sinh", "Đơn hủy"],
        responsive: [
          {
            breakpoint: 480,
            options: {
              chart: {
                width: "100%",
              },
              legend: {
                position: "bottom",
              },
            },
          },
        ],
      },
    };
  },
  mounted() {},
  watch: {
    numberOrderCancel: {
      handler: function () {
        this.applyDataChart();
      },
    },
    numberOrderSuccess: {
      handler: function () {
        this.applyDataChart();
      },
    },
    numberOrderOccurred: {
      handler: function () {
        this.applyDataChart();
      },
    },
    numberOrderDelivery: {
      handler: function () {
        this.applyDataChart();
      },
    },
  },
  methods: {
    applyDataChart() {
      this.series = [
        this.numberOrderSuccess,
        this.numberOrderDelivery,
        this.numberOrderOccurred,
        this.numberOrderCancel,
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
</style>