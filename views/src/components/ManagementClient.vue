<template>
    <div class="management-client-page">
        <NavbarClient />
        <div class="container-fluid" style="padding: 0 100px 0 100px">
            <div class="row">
                <div class="col-6">
                    <h4 class="title-management"><i class="fa-solid fa-chart-pie"></i> Tổng quan</h4>
                </div>
                <div class="col-6 d-flex justify-content-end">
                    <table>
                        <tr>
                            <td style="padding-right: 10px;">Lọc: </td>
                            <td>
                                <select name="cars" id="cars">
                                    <option value="today">Hôm nay</option>
                                    <option value="1week">1 tuần trước</option>
                                    <option value="1month">1 tháng trước</option>
                                    <option value="1year">1 năm trước</option>
                                    <option value="all">Tất cả</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <br />
            <div class="border-div-management">
                <br />
                <div class="row text-center">
                    <div class="col-md-6">
                        <h5>Tổng số đơn hàng</h5>
                        <h2 id="number-orders">100</h2>
                    </div>
                    <div class="col-md-6">
                        <h5>Tổng sản phẩm mới</h5>
                        <h3 id="number-orders">50</h3>
                    </div>
                </div>
                <br />
                <div class="row text-center">
                    <div class="col-md-3 number-order-div">
                        <h6>Đơn thành công</h6>
                        <h4 id="number-orders">10</h4>
                        <p>ĐH: <span id="number-order-success">1</span></p>
                        <p>SP: <span id="number-prod-success">1</span></p>
                        <p>CoD: <span id="number-cod-success">1</span></p>
                    </div>
                    <div class="col-md-3 number-order-div">
                        <h6>Đơn đang giao</h6>
                        <h4 id="number-orders">20</h4>
                        <p>ĐH: <span id="number-order-processing">1</span></p>
                        <p>SP: <span id="number-prod-processing">1</span></p>
                        <p>CoD: <span id="number-cod-processing">1</span></p>
                    </div>
                    <div class="col-md-3 number-order-div">
                        <h6>Đơn phát sinh</h6>
                        <h4 id="number-orders">10</h4>
                        <p>ĐH: <span id="number-order-incurred">1</span></p>
                        <p>SP: <span id="number-prod-incurred">1</span></p>
                        <p>CoD: <span id="number-cod-incurred">1</span></p>
                    </div>
                    <div class="col-md-3 number-order-div">
                        <h6>Đơn hủy</h6>
                        <h4 id="number-orders">10</h4>
                        <p>ĐH: <span id="number-order-cancel">1</span></p>
                        <p>SP: <span id="number-prod-cancel">1</span></p>
                        <p>CoD: <span id="number-cod-cancel">1</span></p>
                    </div>
                </div>
                <br />
            </div>
            <br /><br />
            <div class="border-div-management">
                <br />
                <div class="row chart-management">
                    <div class="col-md-6">
                        <h5>Tổng quan đơn hàng</h5>
                        <br />
                        <VueChartPie />
                    </div>
                    <div class="col-md-6">
                        <h5>Tổng quan dòng tiền</h5>
                        <br />
                        <VueChartLine />
                    </div>
                </div>
                <br />
            </div>
            <br /><br />
            <div class="row list-management">
                <div class="col-md-6 list-top-product">
                    <div class="row">
                        <div class="col-6">
                            <h5>Top sản phẩm</h5>
                        </div>
                        <div class="col-6 d-flex justify-content-end">
                            <table>
                                <tr>
                                    <td style="padding-right: 10px;">Lọc: </td>
                                    <td>
                                        <select name="cars" id="cars">
                                            <option value="today">Bán chạy nhất</option>
                                            <option value="1week">Bán ít nhất</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div> 
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Morbi leo risus</li>
                        <li class="list-group-item">Porta ac consectetur ac</li>
                        <li class="list-group-item">Vestibulum at eros</li>
                      </ul>
                </div>
                <div class="col-md-6 list-top-provinces">
                    <div class="row">
                        <div class="col-6">
                            <h5>Top khu vực</h5>
                        </div>
                        <div class="col-6 d-flex justify-content-end">
                            <table>
                                <tr>
                                    <td style="padding-right: 10px;">Lọc: </td>
                                    <td>
                                        <select name="cars" id="cars">
                                            <option value="today">Bán chạy nhất</option>
                                            <option value="1week">Bán ít nhất</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div> 
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Morbi leo risus</li>
                        <li class="list-group-item">Porta ac consectetur ac</li>
                        <li class="list-group-item">Vestibulum at eros</li>
                      </ul>
                </div>
            </div> 
        </div>
        <br />
        <br /><br />
        <FooterClient />
    </div>
</template>

<script>
    import NavbarClient from './common/NavbarClient.vue';
    import FooterClient from './common/FooterClient.vue';
    import VueChartPie from './common/VueChartPie.vue';
    import VueChartLine from './common/VueChartLine.vue';
    export default {
        components: {
            NavbarClient,
            FooterClient,
            VueChartPie,
            VueChartLine
        }
    }
</script>