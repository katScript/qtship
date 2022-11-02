<template>
  <div class="management-client-page">
    <div :class="isLoading ? 'show' : 'hide'">
      <ActionLoading />
    </div>
    <NavbarClient />
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2">
          <NotficationClient />
        </div>
        <div class="col-md-8">
          <div class="orders-overview-area">
            <div class="orders-filter-form">
              <div class="row">
                <div class="col-12">
                  <h4 class="title-management">
                    <i class="fa-solid fa-clipboard-list"></i> Quản lí đơn hàng
                  </h4>
                  <br />
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <input type="text" class="form-control form-search-control" id="" placeholder="Mã đơn hàng" />
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <input type="text" class="form-control form-search-control" id=""
                    placeholder="Số điện thoại người nhận" />
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select class="form-select" aria-label="Default select example">
                    <option value="label" disabled hidden selected>
                      Trạng thái đơn hàng
                    </option>
                    <option value="odrWaitingtaked">Chờ lấy</option>
                    <option value="odrTaked">Đã lấy</option>
                    <option value="odrDelivering">Đang giao</option>
                    <option value="odrDeliveryPending">
                      Delay giao hàng
                    </option>
                    <option value="odrsuccess">Giao thành công</option>
                    <option value="odrPaymented">Đã thanh toán</option>
                    <option value="odrCanceled">Đơn hoàn</option>

                    <option value="odrTaked">Đã đối soát</option>
                    <option value="odrCanceled">Đã hủy</option>
                  </select>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <table class="w-100">
                    <tr>
                      <td>
                        <select class="form-select" id="select-box-filter-time" v-model="filterTime">
                          <option value="label" hidden>
                            Thời gian tạo đơn
                          </option>
                          <option value="timeCOToday">Hôm nay</option>
                          <option value="timeCO1week">1 tuần</option>
                          <option value="timeCO1month">1 tháng</option>
                          <option value="timeCO1year">1 năm</option>
                          <option value="timeCOAll">Tất cả</option>
                          <option value="timeCOTimeAbout">Tùy chỉnh</option>
                        </select>
                    <tr :class="classFilterTimeAbout">
                      <td>
                        <label for="start">Từ ngày:</label>
                        <input type="date" id="from-time-filter" class="form-control" name="" value="" min="" max="" />
                      </td>
                      <td>
                        <label for="start">Đến ngày ngày:</label>
                        <input type="date" id="to-time-filter" class="form-control" name="" value="" min="" max="" />
                      </td>
                    </tr>
                    </td>
                    </tr>
                  </table>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <table class="w-100">
                    <tr>
                      <td>
                        <select class="form-select" id="select-box-filter-time" v-model="filterTimeDS">
                          <option value="label" hidden>
                            Thời gian đối soát
                          </option>
                          <option value="controlTimeToday">Hôm nay</option>
                          <option value="controlTime1week">1 tuần</option>
                          <option value="controlTime1month">1 tháng</option>
                          <option value="controlTime1year">1 năm</option>
                          <option value="controlTimeAll">Tất cả</option>
                          <option value="controlTimeAboutDS">
                            Tùy chỉnh
                          </option>
                        </select>
                    <tr :class="classFilterTimeAboutDS">
                      <td>
                        <label for="start">Từ ngày:</label>
                        <input type="date" id="from-time-filter" class="form-control" name="" value="" min="" max="" />
                      </td>
                      <td>
                        <label for="start">Đến ngày ngày:</label>
                        <input type="date" id="to-time-filter" class="form-control" name="" value="" min="" max="" />
                      </td>
                    </tr>
                    </td>
                    </tr>
                  </table>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select class="form-select" aria-label="Default select example">
                    <option value="label" disabled hidden selected>
                      Dịch vụ giao hàng
                    </option>
                    <option value="deliveryServiceEco">Economy</option>
                    <option value="deliveryServiceFast">
                      Giao hàng nhanh
                    </option>
                    <option value="deliveryServiceCoD">CoD</option>
                    <option value="deliveryServiceEco24h">Fast 24h</option>
                  </select>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select class="form-select" aria-label="Default select example">
                    <option value="label" disabled hidden selected>
                      Trạng thái hóa đơn
                    </option>
                    <option value="invoiceIssued">Đã xuất hóa đơn</option>
                    <option value="invoiceUnissued">Chưa xuất hóa đơn</option>
                    <option value="invoiceUnissued">
                      Không xuất hóa đơn
                    </option>
                  </select>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <div class="row">
                    <div class="col-8">
                      <button class="btn btn-danger">
                        <i class="fa-sharp fa-solid fa-magnifying-glass"></i> Tìm
                        kiếm đơn hàng
                      </button>
                    </div>
                    <div class="col-4">
                      <button class="btn btn-secondary" v-on:click="resetFormSearch()" style="margin: 0px -5px">
                        <i class="fa-solid fa-eraser"></i> Reset
                      </button>
                    </div>
                  </div>

                </div>
              </div>
            </div>
            <div class="order-list-data">
              <br />
              <h5>
                Số đơn hàng:
                <span class="count-orders-filtered">{{ countOrder }}</span> ĐH
              </h5>
              <easy-data-table :headers="headersOrder" :items="listOrderByCustomer"
                table-class-name="easy-data-table-customize">
                <!-- #item-btn-function="item"  item: valua of row-->
                <template #item-name-receiver="item">
                  {{item.orderItemResponse[0].shippingAddressResponse.name}}
                </template>
                <template #item-phone-receiver="item">
                  {{item.orderItemResponse[0].shippingAddressResponse.phone}}
                </template>
                <template #item-btn-function="item">
                  <table class="w-100">
                    <tr>
                      <td><a href="" class="btn btn-primary a-function a-detail" data-bs-toggle="tooltip"
                        data-bs-placement="top" title="Chi tiết đơn hàng"><i class="fa-solid fa-circle-info"></i></a></td>
                      <td><a :href="'/client/orders/create#_' + item.id" class="btn btn-success a-function a-detail" data-bs-toggle="tooltip"
                        data-bs-placement="top" title="Cập nhật đơn hàng"><i class="fa-solid fa-file-pen"></i></a><input type="hidden" v-model="item.id"></td>
                      <td><a href="" class="btn btn-dark a-function a-detail" data-bs-toggle="tooltip"
                        data-bs-placement="top" title="Lịch sử cập nhật"><i
                          class="fa-solid fa-clock-rotate-left"></i></a></td>
                      <td><a href="" class="btn btn-danger a-function a-detail" data-bs-toggle="tooltip"
                        data-bs-placement="top" title="Hủy đơn hàng"><i class="fa-solid fa-trash"></i></a></td>
                    </tr>
                  </table>
                </template>
              </easy-data-table>
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
  import ToolbarRight from "./common/ToolbarRight.vue";
  import NotficationClient from "./common/NotficationClient.vue";
  import ActionLoading from "./common/ActionLoading.vue";

  import { useCookies } from "vue3-cookies";
  import { commonFunction } from '../scripts/ulti'
  import { debounce } from "vue-debounce";
  import axios from "axios";

  export default {
    components: {
      NavbarClient,
      FooterClient,
      ToolbarRight,
      NotficationClient,
      ActionLoading
    },
    data() {
      return {
        orderCode: "",
        receiverPhonenumber: "",
        filterTime: "label",
        filterTimeDS: "label",
        classFilterTimeAbout: "d-none",
        classFilterTimeAboutDS: "d-none",
        countOrder: 3,
        idRequest: "",
        isLoading: false,
        configRequestApi: {},
        listOrderByCustomer: [],
        headersOrder: [
          { text: "Mã ĐH", value: "id", sortable: true },
          { text: "Tên người nhận", value: "name-receiver", sortable: true },
          { text: "SĐT nhận", value: "phone-receiver" },
          { text: "Trạng thái ĐH", value: "status", sortable: true },
          { text: "Dịch vụ giao hàng", value: "shippingType", sortable: true },
          { text: "Tổng giá trị ĐH (VNĐ)", value: "subtotal", sortable: true },
          { text: "Ngày tạo đơn", value: "", sortable: true },
          { text: "Chức năng", value: "btn-function" },
        ],
      };
    },

    setup() {
      const { cookies } = useCookies();
      return {
        cookies,
      };
    },

    // <data, methods...>

    mounted() {
      let authenication_cookies = this.cookies.get("authenication_cookies");
      this.idRequest = localStorage.getItem("id_customer_request");
      let accesstoken_cookies = this.cookies.get("accesstoken_cookies");
      if (authenication_cookies == null) {
        commonFunction.redirect("/");
      }

      this.configRequestApi = {
        headers: { Authorization: "Bearer " + accesstoken_cookies },
      };

      axios
        .get(
          commonFunction.DOMAIN_URL +
          "v1/order/all/customer/" +
          this.idRequest,
          this.configRequestApi
        )
        .then((response) => {
          let respronseData = response.data;
          this.listOrderByCustomer = respronseData;
        })
        .catch((e) => {
          console.log(e);
        });

    },
    watch: {
      filterTime: {
        handler: debounce(function () {
          return this.filterTime == "timeCOTimeAbout"
            ? (this.classFilterTimeAbout = "d-contents")
            : (this.classFilterTimeAbout = "d-none");
        }, 500),
      },
      filterTimeDS: {
        handler: function () {
          return this.filterTimeDS == "controlTimeAboutDS"
            ? (this.classFilterTimeAboutDS = "d-contents")
            : (this.classFilterTimeAboutDS = "d-none");
        },
      },
    },
    method: {
      resetFormSearch: debounce(function () {
        this.filterTime = "label";
        this.filterTimeDS = "label";
        this.classFilterTimeAbout = "d-none";
        this.classFilterTimeAboutDS = "d-none";
      }, 1000),
      genUrlUpdateOrder(item) { return "/client/orders/create#" + item.id },
    },
  };
</script>

<style scoped>
  .show {
    display: block;
  }

  .hide {
    display: none;
  }

  .form-search-control {
    margin-bottom: 10px;
  }

  a {
    text-decoration: none;
  }

  table tr td {}

  table tr td a {
    /* font-size: 5px; */
    padding: 2px;
  }

  table tr td a i {
    font-size: 18px;
    padding: 2px 2px 2px 2px;
  }
</style>