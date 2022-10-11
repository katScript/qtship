<template>
    <div id="chart">
        <apexchart type="line" height="358" :options="chartOptions" :series="series"></apexchart>
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
                series: [{
                        name: "Đơn thành công",
                        data: [28, 29, 13, 36, 32, 12, 13]
                    },
                    {
                        name: "Đơn đang giao",
                        data: [23, 10, 39, 21, 44, 31, 51]
                    },
                    {
                        name: "Đơn phát sinh",
                        data: [15, 11, 14, 9, 5, 20, 13]
                    },
                    {
                        name: "Đơn hoàn",
                        data: [3, 5, 1, 5, 12, 7, 10]
                    }
                ],
                chartOptions: {
                    chart: {
                        width: '100%',
                        height: 'auto',
                        type: 'line',
                        dropShadow: {
                            enabled: false,
                            color: '#000',
                            top: 18,
                            left: 7,
                            blur: 10,
                            opacity: 0.2
                        },
                        toolbar: {
                            show: false
                        }
                    },
                    colors: ['rgb(0, 143, 251)', 'rgb(0, 227, 150)','rgb(254, 176, 25)','rgb(255, 69, 96)'],
                    dataLabels: {
                        enabled: true,
                    },
                    stroke: {
                        curve: 'smooth'
                    },
                    title: {
                        text: '',
                        align: ''
                    },
                    grid: {
                        borderColor: '#e7e7e7',
                        row: {
                            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                            opacity: 0.5
                        },
                    },
                    markers: {
                        size: 1
                    },
                    xaxis: {
                        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                        title: {
                            text: 'Month'
                        }
                    },
                    yaxis: {
                        title: {
                            text: 'VNĐ'
                        },
                        min: 0,
                        max: 100
                    },
                    legend: {
                        position: 'top',
                        horizontalAlign: 'right',
                        floating: true,
                        offsetY: -25,
                        offsetX: -5
                    }
                },
            }
        }
    };
</script>

<style scoped>
    .a-detail-right{
        right: 0;
        position: absolute;
        text-decoration: none;
    }
</style>