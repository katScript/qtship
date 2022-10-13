<template>
    <div>
        <vue-apexchart width="500" type="pie" :options="chartOptions" :series="series"></vue-apexchart>
        <br />
        <br />
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Đơn thành công <a href="#" class="a-detail-right">Chi tiết</a></li>
            <li class="list-group-item">Đơn đang giao <a href="#" class="a-detail-right">Chi tiết</a></li>
            <li class="list-group-item">Đơn phát sinh <a href="#" class="a-detail-right">Chi tiết</a></li>
            <li class="list-group-item">Đơn hoàn <a href="#" class="a-detail-right">Chi tiết</a></li>
        </ul>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                series: [44, 55, 13, 43],
                chartOptions: {
                    chart: {
                        width: 380,
                        type: 'pie',
                    },
                    labels: ['Đơn thành công', 'Đơn đang giao', 'Đơn phát sinh', 'Đơn hủy'],
                    responsive: [{
                        breakpoint: 480,
                        options: {
                            chart: {
                                width: '100%'
                            },
                            legend: {
                                position: 'bottom'
                            }
                        }
                    }]
                },
            }
        }
    };
</script>

<style scoped>
    .a-detail-right {
        right: 0;
        position: absolute;
        text-decoration: none;
    }
</style>