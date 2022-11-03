<template>
  <div class="management-client-page">
    <NavbarClient />
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2">
          <NotficationClient />
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
            <br />
            <div class="border-div-management">
              <br />
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
                  <h5>Tổng sản phẩm</h5>
                  <h2 id="number-orders">{{ totalProductAll }}</h2>
                </div>
                <div class="col-3">
                  <h5>Tổng số kho</h5>
                  <h2 id="number-orders">1</h2>
                </div>
              </div>
              <br />
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
                  <br /><br />
                </div>
                <div class="col-md-6">
                  <h5>Tổng quan dòng tiền</h5>
                  <br />
                  <VueChartColumn />
                  <br /><br />
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
                        <td style="padding-right: 10px">Lọc:</td>
                        <td>
                          <select name="cars" id="cars">
                            <option value="bcn">Bán chạy nhất</option>
                            <option value="bit">Bán ít nhất</option>
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
                <br />
              </div>
              <div class="col-md-6 list-top-provinces">
                <div class="row">
                  <div class="col-6">
                    <h5>Top khu vực</h5>
                  </div>
                  <div class="col-6 d-flex justify-content-end">
                    <table>
                      <tr>
                        <td style="padding-right: 10px">Lọc:</td>
                        <td>
                          <select name="" id="">
                            <option value="bcn">Bán chạy nhất</option>
                            <option value="bit">Bán ít nhất</option>
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
                <br />
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-2">
          <ToolbarRight />
        </div>
      </div>
    </div>

    <br />
    <br /><br />
    <FooterClient />
  </div>
</template>

<script>
import NavbarClient from "./common/NavbarClient.vue";
import FooterClient from "./common/FooterClient.vue";
import VueChartPie from "./common/VueChartPie.vue";
import VueChartColumn from "./common/VueChartColumn.vue";
import ToolbarRight from "./common/ToolbarRight.vue";
import NotficationClient from "./common/NotficationClient.vue";
import axios from "axios";

import moment from "moment";
import { useCookies } from "vue3-cookies";
import { commonFunction } from "../scripts/ulti";

export default {
  components: {
    NavbarClient,
    FooterClient,
    VueChartPie,
    VueChartColumn,
    ToolbarRight,
    NotficationClient,
  },
  data() {
    return {
      filterTime: "today",
      classFilterTimeAbout: "d-none",
      customerInfo: null,
      filterTimeFrom: "",
      filterTimeTo: "",
      numberOrderSuccess: 0,
      numberOrderDelivery: 0,
      numberOrderOccurred: 0,
      numberOrderCancel: 0,
      idRequest: "",
      configRequestApi: {},
      listOrderByCustomer: [],
      listProductByCustomer: [],
      totalOrderByDate: 0,
      totalProductByDate: 0,
      totalProductAll: 0,
      dateFilter: "",
    };
  },

  setup() {
    const { cookies } = useCookies();
    return { cookies };
  },
  // <data, methods...>

  mounted() {
    const self = this;
    let authenication_cookies = self.cookies.get("authenication_cookies");
    let accesstoken_cookies = self.cookies.get("accesstoken_cookies");
    self.idRequest = self.cookies.get("idrequest_cookies");
    self.dateFilter = moment().format("YYYY-MM-DD HH:MM:SS");

    if (authenication_cookies == null) {
      commonFunction.redirect("/");
    }

    self.configRequestApi = {
      headers: { Authorization: "Bearer " + accesstoken_cookies },
    };

    //customer
    axios
      .get(
        commonFunction.DOMAIN_URL + "v1/customer/detail/" + self.idRequest,
        self.configRequestApi
      )
      .then((response) => {
        self.customerInfo = response.data.customer;
        localStorage.setItem("id_customer_request", self.customerInfo.id);
      })
      .catch((e) => {
        console.log(e);
      });

    //product
    axios
      .get(
        commonFunction.DOMAIN_URL + "v1/product/customer/" + self.idRequest,
        self.configRequestApi
      )
      .then((response) => {
        let respronseData = response.data;
        self.listProductByCustomer = respronseData;
        self.totalProductByDate = self.listProductByCustomer.filter(
          (e) =>
            moment(e.createdAt).format("YYYY-MM-DD") ==
            moment(self.dateFilter).format("YYYY-MM-DD")
        ).length;
        self.totalProductAll = self.listProductByCustomer.length;
      })
      .catch((e) => {
        console.log(e);
      });

    //orders
    axios
      .get(
        commonFunction.DOMAIN_URL + "v1/order/all/customer/" + self.idRequest,
        self.configRequestApi
      )
      .then((response) => {
        let respronseData = response.data;
        self.listOrderByCustomer = respronseData;
        self.totalOrderByDate = self.listOrderByCustomer.filter(
          (e) =>
            moment(e.createdAt).format("YYYY-MM-DD") ==
            moment(self.dateFilter).format("YYYY-MM-DD")
        ).length;

        self.numberOrderSuccess = self.listOrderByCustomer.filter(
          (e) =>
            e.status == commonFunction.typeOrderSuccess &&
            moment(e.createdAt).format("YYYY-MM-DD") ==
              moment(self.dateFilter).format("YYYY-MM-DD")
        ).length;
        self.numberOrderDelivery = self.listOrderByCustomer.filter(
          (e) =>
            e.status == commonFunction.typeOrderDelivery &&
            moment(e.createdAt).format("YYYY-MM-DD") ==
              moment(self.dateFilter).format("YYYY-MM-DD")
        ).length;
        self.numberOrderOccurred = self.listOrderByCustomer.filter(
          (e) =>
            e.status == commonFunction.typeOrderOccurred &&
            moment(e.createdAt).format("YYYY-MM-DD") ==
              moment(self.dateFilter).format("YYYY-MM-DD")
        ).length;
        self.numberOrderCancel = self.listOrderByCustomer.filter(
          (e) =>
            e.status == commonFunction.typeOrderCancel &&
            moment(e.createdAt).format("YYYY-MM-DD") ==
              moment(self.dateFilter).format("YYYY-MM-DD")
        ).length;
      })
      .catch((e) => {
        console.log(e);
      });
  },
  watch: {
    filterTime: {
      handler: function () {
        const self = this;
        self.filterTime == "timeAbout"
          ? (self.classFilterTimeAbout = "d-contents")
          : (self.classFilterTimeAbout = "d-none");

        if (self.filterTime == "today") {
          self.dateFilter = moment().format("YYYY-MM-DD HH:MM:SS");
          self.totalOrderByDate = self.listOrderByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") ==
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.numberOrderSuccess = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderSuccess &&
              moment(e.createdAt).format("YYYY-MM-DD") ==
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderDelivery = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderDelivery &&
              moment(e.createdAt).format("YYYY-MM-DD") ==
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderOccurred = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderOccurred &&
              moment(e.createdAt).format("YYYY-MM-DD") ==
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderCancel = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderCancel &&
              moment(e.createdAt).format("YYYY-MM-DD") ==
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.totalProductByDate = self.listProductByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") ==
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
        } else if (self.filterTime == "1week") {
          self.dateFilter = moment()
            .subtract(1, "weeks")
            .format("YYYY-MM-DD HH:MM:SS");

          self.totalOrderByDate = self.listOrderByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") >=
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.numberOrderSuccess = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderSuccess &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderDelivery = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderDelivery &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderOccurred = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderOccurred &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderCancel = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderCancel &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.totalProductByDate = self.listProductByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") >=
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
        } else if (self.filterTime == "1month") {
          self.dateFilter = moment()
            .subtract(1, "months")
            .format("YYYY-MM-DD HH:MM:SS");

          self.totalOrderByDate = self.listOrderByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") >=
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.numberOrderSuccess = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderSuccess &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderDelivery = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderDelivery &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderOccurred = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderOccurred &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderCancel = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderCancel &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.totalProductByDate = self.listProductByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") >=
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
        } else if (self.filterTime == "1year") {
          self.dateFilter = moment()
            .subtract(1, "years")
            .format("YYYY-MM-DD HH:MM:SS");

          self.totalOrderByDate = self.listOrderByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") >=
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.numberOrderSuccess = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderSuccess &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderDelivery = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderDelivery &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderOccurred = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderOccurred &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderCancel = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderCancel &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.totalProductByDate = self.listProductByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") >=
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
        } else if (self.filterTime == "timeAbout") {
          self.filterTimeFrom = moment(self.filterTimeFrom).format(
            "YYYY-MM-DD HH:MM:SS"
          );
          self.filterTimeTo = moment(self.filterTimeTo).format(
            "YYYY-MM-DD HH:MM:SS"
          );
        } else {
          self.dateFilter = moment().format("YYYY-MM-DD HH:MM:SS");
          self.totalOrderByDate = self.listOrderByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") >=
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.numberOrderSuccess = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderSuccess &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderDelivery = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderDelivery &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderOccurred = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderOccurred &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
          self.numberOrderCancel = self.listOrderByCustomer.filter(
            (e) =>
              e.status == commonFunction.typeOrderCancel &&
              moment(e.createdAt).format("YYYY-MM-DD") >=
                moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;

          self.totalProductByDate = self.listProductByCustomer.filter(
            (e) =>
              moment(e.createdAt).format("YYYY-MM-DD") >=
              moment(this.dateFilter).format("YYYY-MM-DD")
          ).length;
        }
      },
    },
    filterTimeTo: {
      handler: function () {
        this.totalOrderByDate = this.listOrderByCustomer.filter(
          (e) =>
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") >=
              this.filterTimeFrom &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") <=
              this.filterTimeTo
        ).length;
        this.numberOrderSuccess = this.listOrderByCustomer.filter(
          (e) =>
            e.status == commonFunction.typeOrderSuccess &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") >=
              this.filterTimeFrom &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") <=
              this.filterTimeTo
        ).length;
        this.numberOrderDelivery = this.listOrderByCustomer.filter(
          (e) =>
            e.status == commonFunction.typeOrderDelivery &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") >=
              this.filterTimeFrom &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") <=
              this.filterTimeTo
        ).length;
        this.numberOrderOccurred = this.listOrderByCustomer.filter(
          (e) =>
            e.status == commonFunction.typeOrderOccurred &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") >=
              this.filterTimeFrom &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") <=
              this.filterTimeTo
        ).length;
        this.numberOrderCancel = this.listOrderByCustomer.filter(
          (e) =>
            e.status == commonFunction.typeOrderCancel &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") >=
              this.filterTimeFrom &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") <=
              this.filterTimeTo
        ).length;
        this.totalProductByDate = this.listProductByCustomer.filter(
          (e) =>
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") >=
              this.filterTimeFrom &&
            moment(e.createdAt).format("YYYY-MM-DD HH:MM:SS") <=
              this.filterTimeTo
        ).length;
      },
    },
  },
  method: {
    debouncer(fn, delay) {
      var timeoutID = null;
      return function () {
        clearTimeout(timeoutID);
        var args = arguments;
        var that = this;
        timeoutID = setTimeout(function () {
          fn.apply(that, args);
        }, delay);
      };
    },
  },
};
</script>

<style scoped>
</style>