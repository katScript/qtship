<template>
    <div class="management-client-page">
        <NavbarClient />
        <div class="container-fluid" style="width: 95%;">
            <div class="row">
                <div class="col-md-10">
                    <div class="overview-area">
                        <div class="row">
                            <div class="col-6">
                                <h4 class="title-management"><i class="fa-solid fa-chart-pie"></i> Tổng quan</h4>
                            </div>
                            <div class="col-6 d-flex justify-content-end">
                                <table>
                                    <tr>
                                        <td style="padding-right: 10px;">Lọc: </td>
                                        <td>
                                            <select class="form-select" id="select-box-filter-time"
                                                v-model="filterTime">
                                                <option value="today">Hôm nay</option>
                                                <option value="1week">1 tuần</option>
                                                <option value="1month">1 tháng</option>
                                                <option value="1year">1 năm</option>
                                                <option value="timeAbout">Tùy chỉnh</option>
                                            </select>
                                    <tr :class="classFilterTimeAbout">
                                        <td>
                                            <label for="start">Từ ngày:</label>
                                            <input type="date" id="from-time-filter" class="form-control"
                                                name="trip-start" value="" min="" max="">
                                        </td>
                                        <td>
                                            <label for="start">Đến ngày ngày:</label>
                                            <input type="date" id="to-time-filter" class="form-control"
                                                name="trip-start" value="" min="" max="">
                                        </td>
                                    </tr>
                                    </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <br />
                        <div class="border-div-management">
                            <br />
                            <div class="row text-center overview-total-data">
                                <div class="col-md-3">
                                    <h5>Tổng số đơn hàng</h5>
                                    <h2 id="number-orders">100</h2>
                                </div>
                                <div class="col-md-3">
                                    <h5>Tổng sản phẩm mới</h5>
                                    <h2 id="number-orders">20</h2>
                                </div>
                                <div class="col-md-3">
                                    <h5>Tổng sản phẩm</h5>
                                    <h2 id="number-orders">250</h2>
                                </div>
                                <div class="col-md-3">
                                    <h5>Tổng số kho</h5>
                                    <h2 id="number-orders">1</h2>
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
                        <div class="">
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
                        <br />
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
                                    <li class="list-group-item">Item ranking</li>
                                    <li class="list-group-item">Item ranking</li>
                                    <li class="list-group-item">Item ranking</li>
                                    <li class="list-group-item">Item ranking</li>
                                    <li class="list-group-item">Item ranking</li>
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
                                    <li class="list-group-item">Item ranking</li>
                                    <li class="list-group-item">Item ranking</li>
                                    <li class="list-group-item">Item ranking</li>
                                    <li class="list-group-item">Item ranking</li>
                                    <li class="list-group-item">Item ranking</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="subtab-area">
                        <div class="subtab-area-group">
                            <ul class="list-group">
                                <a href="#"
                                    class="list-group-item list-group-item-action list-group-page-qt title-group-item-qt">Quản
                                    lí đơn hàng</a>
                                <li class="list-group-item">
                                    <table>
                                        <tr>
                                            <td>
                                                <div class="dot"></div>
                                            </td>
                                            <td>Đang hoạt động</td>
                                        </tr>
                                    </table>
                                </li>
                            </ul>
                            <div class="list-group">
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Giao thành công <span class="number-item-span">(10ĐH)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Đã lấy/Đang giao <span class="number-item-span">(10ĐH)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Delay & Hủy lấy <span class="number-item-span">(10ĐH | 10ĐH)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Không giao được/Lưu kho <span class="number-item-span">(10ĐH)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Đơn đổi trả (Đơn hoàn) <span class="number-item-span">(10ĐH)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Delay do sai thông tin ĐH & chờ kiểm tra <span
                                        class="number-item-span">(10ĐH)</span> <i class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Chưa xử lý <span class="number-item-span">(10ĐH)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                            </div>
                        </div>
                        <div class="subtab-area-group">
                            <ul class="list-group">
                                <a href="#"
                                    class="list-group-item list-group-item-action list-group-page-qt title-group-item-qt">Quản
                                    lí kho và sản phẩm</a>
                                <li class="list-group-item">
                                    Kho: <select class="form-select" aria-label="Default select example"
                                        style="width: 200px; display: inline-block;">
                                        <option selected>Tất cả</option>
                                        <option value="1">Kho Thanh Xuân</option>
                                        <option value="2">Kho Mỹ Đình</option>
                                    </select>
                                </li>
                            </ul>
                            <div class="list-group">
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Nhập <span class="number-item-span">(+ 10SP)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Xuất <span class="number-item-span">(+ 10SP)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Tồn <span class="number-item-span">(+ 10SP)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Đơn hàng đã tạo/Sản phẩm <span class="number-item-span">(10ĐH | 12SP)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Đơn hàng chưa xử lí/Sản phẩm <span class="number-item-span">(10ĐH | 12SP)</span> <i
                                        class="fa-solid fa-chevron-right"></i>
                                </a>
                            </div>
                        </div>
                        <div class="subtab-area-group">
                            <ul class="list-group">
                                <a href="#"
                                    class="list-group-item list-group-item-action list-group-page-qt title-group-item-qt">Quản
                                    lí dòng tiền</a>
                                <li class="list-group-item">
                                    <table>
                                        <tr>
                                            <td>
                                                <div class="dot"></div>
                                            </td>
                                            <td>Đang hoạt động</td>
                                        </tr>
                                    </table>
                                </li>
                            </ul>
                            <div class="list-group">
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Tiền đang luân chuyển (0 VNĐ) <i class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Tổng thu (0 VNĐ) <i class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Tiền thu hộ (0 VNĐ) <i class="fa-solid fa-chevron-right"></i>
                                </a>
                                <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                                    Phí hoàn hàng <i class="fa-solid fa-chevron-right"></i>
                                </a>
                            </div>
                        </div>
                    </div>
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
        },
        data() {
            return {
                filterTime: 'today',
                classFilterTimeAbout: 'd-none'
            }
        },
        watch: {
            filterTime: {
                handler: function () { return this.filterTime == 'timeAbout' ? this.classFilterTimeAbout = 'd-contents' : this.classFilterTimeAbout = 'd-none'; },
            }
        },
        method: {
            debouncer(fn, delay) {
                var timeoutID = null
                return function () {
                    clearTimeout(timeoutID)
                    var args = arguments
                    var that = this
                    timeoutID = setTimeout(function () {
                        fn.apply(that, args)
                    }, delay)
                }
            }
        }
    }
</script>

<style scoped>
    .dot {
        display: block;
        width: 8px;
        height: 8px;
        border-radius: 50%;
        box-shadow: 0 0 0 3px transparentize($primary-medium, 0.8);
        background-color: #4cd137;
        margin-right: 6px;
    }
</style>