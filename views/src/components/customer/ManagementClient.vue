<template>
    <div class="management-client-page">
        <div :class="isLoading ? 'show' : 'hide'">
            <ActionLoading/>
        </div>
        <NavbarClient/>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <NotificationClient/>
                </div>
                <div class="col-md-8">
                    <div class="overview-area">
                        <div class="row">
                            <div class="col-6">
                                <h4 class="title-management">
                                    <i class="fa-solid fa-chart-pie"></i> Tổng quan
                                </h4>
                            </div>
                            <div class="col-6 d-flex justify-content-end">
                                <table>
                                    <tr>
                                        <td style="padding-right: 10px">Lọc:</td>
                                        <td>
                                            <select
                                                class="form-select"
                                                id="select-box-filter-time"
                                                v-model="filterTime"
                                            >
                                                <option value="all">Tất cả</option>
                                                <option value="today">Hôm nay</option>
                                                <option value="1week">1 tuần</option>
                                                <option value="1month">1 tháng</option>
                                                <option value="1year">1 năm</option>
                                                <option value="timeAbout">Tùy chỉnh</option>
                                            </select>
                                    <tr :class="classFilterTimeAbout">
                                        <td>
                                            <label for="start">Từ ngày:</label>
                                            <input
                                                type="date"
                                                id="from-time-filter"
                                                class="form-control"
                                                name="trip-start"
                                                v-model="filterTimeFrom"
                                            />
                                        </td>
                                        <td>
                                            <label for="start">Đến ngày ngày:</label>
                                            <small class="text-danger">{{
                                                    msgValidationTimeFromTo
                                                }}</small>
                                            <input
                                                type="date"
                                                id="to-time-filter"
                                                class="form-control"
                                                name="trip-start"
                                                v-model="filterTimeTo"
                                            />
                                        </td>
                                    </tr>
                                    </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <br/>
                        <div class="border-div-management">
                            <br/>
                            <div class="row text-center overview-total-data">
                                <div class="col-3">
                                    <h5>Đơn hàng mới</h5>
                                    <h2 id="number-orders">{{ totalOrderByDate }}</h2>
                                </div>
                                <div class="col-3">
                                    <h5>Sản phẩm mới</h5>
                                    <h2 id="number-orders">{{ totalProductByDate }}</h2>
                                </div>
                                <div class="col-3">
                                    <h5>Tổng số đơn hàng</h5>
                                    <h2 id="number-orders">{{ totalOrderAll }}</h2>
                                </div>
                                <div class="col-3">
                                    <h5>Tổng sản phẩm</h5>
                                    <h2 id="number-orders">{{ totalProductAll }}</h2>
                                </div>
                            </div>
                            <br/>
                            <div class="row text-center">
                                <div class="col-6 col-md-3 number-order-div">
                                    <h6>Đơn thành công</h6>
                                    <h4 id="number-orders">{{ numberOrderSuccess }}</h4>
                                </div>
                                <div class="col-6 col-md-3 number-order-div">
                                    <h6>Đơn đang giao</h6>
                                    <h4 id="number-orders">{{ numberOrderDelivery }}</h4>
                                </div>
                                <div class="col-6 col-md-3 number-order-div">
                                    <h6>Đơn phát sinh</h6>
                                    <h4 id="number-orders">{{ numberOrderOccurred }}</h4>
                                </div>
                                <div class="col-6 col-md-3 number-order-div">
                                    <h6>Đơn hủy</h6>
                                    <h4 id="number-orders">{{ numberOrderCancel }}</h4>
                                </div>
                            </div>
                            <br/>
                        </div>
                        <br/><br/>
                        <div class="">
                            <br/>
                            <div class="row chart-management">
                                <div class="col-md-6">
                                    <h5>Tổng quan đơn hàng</h5>
                                    <br/>
                                    <VueChartPie
                                        :numberOrderSuccess="numberOrderSuccess"
                                        :numberOrderCancel="numberOrderCancel"
                                        :numberOrderOccurred="numberOrderOccurred"
                                        :numberOrderDelivery="numberOrderDelivery"
                                    />
                                    <br/><br/>
                                </div>
                                <div class="col-md-6">
                                    <h5>Tổng quan dòng tiền</h5>
                                    <br/>
                                    <VueChartColumn
                                        :totalPriceOrderSuccess="totalPriceOrderSuccess"
                                        :totalPriceOrderCancel="totalPriceOrderCancel"
                                        :totalPriceOrderOccurred="totalPriceOrderOccurred"
                                        :totalPriceOrderDelivery="totalPriceOrderDelivery"
                                    />
                                    <br/><br/>
                                </div>
                            </div>
                            <br/>
                        </div>
                        <br/>
                    </div>
                </div>
                <div class="col-md-2">
                    <ToolbarRight/>
                </div>
            </div>
        </div>

        <br/>
        <br/><br/>
        <FooterClient/>
    </div>
</template>

<script src="@/components/customer/js/management"/>

<style scoped>
.show {
    display: block;
}

.hide {
    display: none;
}
</style>