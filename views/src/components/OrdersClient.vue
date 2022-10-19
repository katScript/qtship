<template>
  <div class="management-client-page">
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
                  <button class="btn btn-danger w-75">
                    <i class="fa-sharp fa-solid fa-magnifying-glass"></i> Tìm
                    kiếm đơn hàng
                  </button>
                  <button type="" class="btn btn-secondary w-23" style="float: right" v-on:click="resetFormSearch()">
                    <i class="fa-solid fa-eraser"></i> Reset
                  </button>
                </div>
              </div>
            </div>
            <div class="order-list-data">
              <br />
              <h5>
                Số đơn hàng:
                <span class="count-orders-filtered">{{ countOrder }}</span> ĐH
              </h5>
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th scope="col">Mã đơn hàng</th>
                    <th scope="col">Mã khách hàng/Số điện thoại</th>
                    <th scope="col">Khách hàng</th>
                    <th scope="col">Trạng thái đơn hàng</th>
                    <th scope="col">Dịch vụ giao hàng</th>
                    <th scope="col">Đối soát</th>
                    <th scope="col">Hóa đơn</th>
                    <th scope="col">Chức năng</th>
                  </tr>
                </thead>
                <tbody v-if="countOrder == 0">
                  <tr>
                    <td colspan="7" class="text-center">
                      <img src="../images/emptyList.png" alt="" />
                      <br />
                      Không có đơn hàng <a href="">Tạo đơn hàng!</a>
                    </td>
                  </tr>
                </tbody>
                <tbody v-else>
                  <tr>
                    <td>11112</td>
                    <td>123123123</td>
                    <td>duongph</td>
                    <td>Đang giao</td>
                    <td>Economy</td>
                    <td>2022/10/10 00:00</td>
                    <td>Đã xuất hóa đơn</td>
                    <td>
                      <a href="" class="btn btn-primary a-function a-detail" data-bs-toggle="tooltip"
                        data-bs-placement="top" title="Chi tiết đơn hàng"><i class="fa-solid fa-circle-info"></i></a>
                      <a href="" class="btn btn-success a-function a-detail" data-bs-toggle="tooltip"
                        data-bs-placement="top" title="Cập nhật đơn hàng"><i class="fa-solid fa-file-pen"></i></a>
                      <a href="" class="btn btn-dark a-function a-detail" data-bs-toggle="tooltip"
                        data-bs-placement="top" title="Lịch sử cập nhật"><i
                          class="fa-solid fa-clock-rotate-left"></i></a>
                      <a href="" class="btn btn-danger a-function a-detail" data-bs-toggle="tooltip"
                        data-bs-placement="top" title="Hủy đơn hàng"><i class="fa-solid fa-trash"></i></a>
                    </td>
                  </tr>
                </tbody>
              </table>
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

  import { useCookies } from "vue3-cookies";
  // import { commonFunction } from '../scripts/ulti'
  import { debounce } from "vue-debounce";

  export default {
    components: {
      NavbarClient,
      FooterClient,
      ToolbarRight,
      NotficationClient
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
      // let authenication_cookies = this.cookies.get("authenication_cookies");
      // if(authenication_cookies == null){
      //   commonFunction.redirect('/');
      // } else {
      //     commonFunction.redirect('/client/management');
      // }
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
    },
  };
</script>

<style scoped>
  .form-search-control {
    margin-bottom: 10px;
  }

  a {
    text-decoration: none;
  }
</style>