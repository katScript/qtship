<template>
  <div class="management-client-page">
    <NavbarClient />
    <div class="container-fluid" style="width: 95%">
      <div class="row">
        <div class="col-md-10">
          <div class="orders-overview-area">
            <form>
              <div class="row">
                <div class="col-12">
                  <h4 class="title-management">
                    <i class="fa-solid fa-clipboard-list"></i> Quản lí đơn hàng
                  </h4>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <input
                    type="text"
                    class="form-control form-search-control"
                    id=""
                    placeholder="Mã đơn hàng"
                  />
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <input
                    type="text"
                    class="form-control form-search-control"
                    id=""
                    placeholder="Số điện thoại người nhận"
                  />
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select
                    class="form-select"
                    aria-label="Default select example"
                  >
                    <option value="label" disabled hidden selected>
                      Trạng thái đơn hàng
                    </option>
                    <option value="odrsuccess">
                      Giao thành công/Đã thanh toán
                    </option>
                    <option value="odrDelivering">Đang giao</option>
                    <option value="odrTaked">Đã lấy hàng/Nhập kho</option>
                    <option value="odrCanceled">Đơn hoàn</option>
                    <option value="odrDeliveryPending">Delay giao hàng</option>
                    <option value="odrTaked">Đã đối soát</option>
                    <option value="odrCanceled">Đã hủy</option>
                  </select>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <table class="w-100">
                    <tr>
                      <td>
                        <select
                          class="form-select"
                          id="select-box-filter-time"
                          v-model="filterTime"
                        >
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
                            <input
                              type="date"
                              id="from-time-filter"
                              class="form-control"
                              name="trip-start"
                              value=""
                              min=""
                              max=""
                            />
                          </td>
                          <td>
                            <label for="start">Đến ngày ngày:</label>
                            <input
                              type="date"
                              id="to-time-filter"
                              class="form-control"
                              name="trip-start"
                              value=""
                              min=""
                              max=""
                            />
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
                        <select
                          class="form-select"
                          id="select-box-filter-time"
                          v-model="filterTimeDS"
                        >
                          <option value="label" hidden>
                            Thời gian đối soát
                          </option>
                          <option value="controlTimeToday">Hôm nay</option>
                          <option value="controlTime1week">1 tuần</option>
                          <option value="controlTime1month">1 tháng</option>
                          <option value="controlTime1year">1 năm</option>
                          <option value="controlTimeAll">Tất cả</option>
                          <option value="controlTimeAboutDS">Tùy chỉnh</option>
                        </select>
                        <tr :class="classFilterTimeAboutDS">
                          <td>
                            <label for="start">Từ ngày:</label>
                            <input
                              type="date"
                              id="from-time-filter"
                              class="form-control"
                              name="trip-start"
                              value=""
                              min=""
                              max=""
                            />
                          </td>
                          <td>
                            <label for="start">Đến ngày ngày:</label>
                            <input
                              type="date"
                              id="to-time-filter"
                              class="form-control"
                              name="trip-start"
                              value=""
                              min=""
                              max=""
                            />
                          </td>
                        </tr>
                      </td>
                    </tr>
                  </table>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select
                    class="form-select"
                    aria-label="Default select example"
                  >
                    <option value="label" disabled hidden selected>
                      Dịch vụ giao hàng
                    </option>
                    <option value="deliveryServiceEco">Economy</option>
                    <option value="deliveryServiceFast">Giao hàng nhanh</option>
                    <option value="deliveryServiceCoD">CoD</option>
                    <option value="deliveryServiceEco24h">Fast 24h</option>
                  </select>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select
                    class="form-select"
                    aria-label="Default select example"
                  >
                    <option value="label" disabled hidden selected>
                      Trạng thái hóa đơn
                    </option>
                    <option value="invoiceIssued">Đã xuất hóa đơn</option>
                    <option value="invoiceUnissued">Chưa xuất hóa đơn</option>
                  </select>
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <button class="btn btn-danger w-75">
                    <i class="fa-sharp fa-solid fa-magnifying-glass"></i> Tìm
                    kiếm đơn hàng
                  </button>
                  <button type="reset" class="btn btn-secondary w-23" style="float: right;">
                    <i class="fa-solid fa-eraser"></i> Reset
                  </button>
                </div>
              </div>
            </form>
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

import { useCookies } from "vue3-cookies";
// import { commonFunction } from '../scripts/ulti'

export default {
  components: {
    NavbarClient,
    FooterClient,
    ToolbarRight,
  },
  data() {
    return {
      filterTime: "label",
      filterTimeDS: "label",
      classFilterTimeAbout: "d-none",
      classFilterTimeAboutDS: "d-none",
    };
  },

  setup() {
    const { cookies } = useCookies();
    return { cookies };
  },

  // <data, methods...>

  mounted() {
    // let authenication_cookies = this.cookies.get("authenication_cookies");
    // if(authenication_cookies == null){
    //   commonFunction.redirect('/login-page');
    // } else {
    //     commonFunction.redirect('/client/management');
    // }
  },
  watch: {
    filterTime: {
      handler: function () {
        return this.filterTime == "timeCOTimeAbout"
          ? (this.classFilterTimeAbout = "d-contents")
          : (this.classFilterTimeAbout = "d-none");
      },
    },
    filterTimeDS: {
      handler: function () {
        return this.filterTimeDS == "controlTimeAboutDS"
          ? (this.classFilterTimeAboutDS = "d-contents")
          : (this.classFilterTimeAboutDS = "d-none");
      },
    },
  },
  method: {},
};
</script>

<style scoped>
.form-search-control {
  margin-bottom: 10px;
}
</style>