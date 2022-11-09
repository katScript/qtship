<template>
  <div class="management-client-page">
    <div>
      <PopupNotify
        :class="isShowNotify ? 'show' : 'hide'"
        @closePopupNotify="closePopupNotify"
        @updateTypeNotify="updateTypeNotify"
        :typeNotify="typeNotify"
        :dataNotify="dataNotify"
        :typeComponent="typeComponent"
        :configRequestApi="configRequestApi"
      />
    </div>
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
                  <input
                    v-model="filterOderCondition.orderId"
                    type="text"
                    class="form-control form-search-control"
                    id=""
                    placeholder="Mã đơn hàng"
                  />
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <input
                    v-model="filterOderCondition.phoneReceiver"
                    type="text"
                    class="form-control form-search-control"
                    id=""
                    placeholder="Số điện thoại người nhận"
                  />
                </div>
                <div class="col-12 col-sm-6 col-md-2 form-group">
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="filterOderCondition.orderStatus"
                  >
                    <option value="" disabled hidden selected>
                      Trạng thái đơn hàng
                    </option>
                    <option value="WAITINGTAKE">Chờ lấy</option>
                    <option value="TAKED">Đã lấy</option>
                    <option value="DELIVERING">Đang giao</option>
                    <option value="DELAYDELIVERY">Delay giao hàng</option>
                    <option value="SUCCESS">Giao thành công</option>
                    <option value="PAYMENT">Đã thanh toán</option>
                    <option value="RETURN">Đơn hoàn</option>

                    <!-- <option value="DOISOAT">Đã đối soát</option> -->
                    <option value="CANCEL">Đã hủy</option>
                  </select>
                </div>
                <div class="col-12 col-sm-6 col-md-4 form-group">
                  <table class="w-100">
                    <tr>
                      <td>
                        <select
                          class="form-select"
                          id="select-box-filter-time"
                          v-model="filterTime"
                        >
                          <option value="" hidden>Thời gian tạo đơn</option>
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
                              v-model="filterOderCondition.timeFrom"
                            />
                          </td>
                          <td>
                            <label for="start">Đến ngày ngày:</label>
                            <input
                              type="date"
                              id="to-time-filter"
                              class="form-control"
                              v-model="filterOderCondition.timeTo"
                            />
                          </td>
                        </tr>
                      </td>
                    </tr>
                  </table>
                </div>
                <!-- <div class="col-12 col-sm-6 col-md-3 form-group">
                  <table class="w-100">
                    <tr>
                      <td>
                        <select class="form-select" id="select-box-filter-time" v-model="filterTimeDS">
                          <option value="" hidden>
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
                        <input type="date" id="from-time-filter" class="form-control" name=""
                          v-model="filterOderCondition.timeDSFrom" />
                      </td>
                      <td>
                        <label for="start">Đến ngày ngày:</label>
                        <input type="date" id="to-time-filter" class="form-control" name=""
                          v-model="filterOderCondition.timeDSTo" />
                      </td>
                    </tr>
                    </td>
                    </tr>
                  </table>
                </div> -->
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="filterOderCondition.shippingType"
                  >
                    <option value="" disabled hidden selected>
                      Dịch vụ giao hàng
                    </option>
                    <option
                      v-for="(type, index) in listTypeShipping"
                      :key="index"
                    >
                      {{ generateCodeToText(type) }}
                    </option>
                  </select>
                </div>
                <!-- <div class="col-12 col-sm-6 col-md-3 form-group">
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
                </div> -->
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <div class="row">
                    <div class="col-8">
                      <button class="btn btn-danger" v-on:click="filterOrder">
                        <i class="fa-sharp fa-solid fa-magnifying-glass"></i>
                        Tìm kiếm đơn hàng
                      </button>
                    </div>
                    <div class="col-4">
                      <button
                        class="btn btn-secondary"
                        v-on:click="resetFormSearch()"
                        style="margin: 0px -5px"
                      >
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
              <easy-data-table
                :headers="headersOrder"
                :items="listOrderByCustomer"
                table-class-name="easy-data-table-customize"
              >
                <!-- #item-btn-function="item"  item: valua of row-->
                <template #item-name-receiver="item">
                  {{ item.orderItem[0].shippingAddress.name }}
                </template>
                <template #item-phone-receiver="item">
                  {{ item.orderItem[0].shippingAddress.phone }}
                </template>
                <template #item-btn-function-order="item">
                  <table class="w-100">
                    <tr>
                      <td>
                        <a
                          href=""
                          class="btn btn-primary a-function a-detail"
                          data-bs-toggle="tooltip"
                          data-bs-placement="top"
                          title="Chi tiết đơn hàng"
                          ><i class="fa-solid fa-circle-info"></i
                        ></a>
                      </td>
                      <td>
                        <a
                          :href="'/client/orders/create#_' + item.id"
                          class="btn btn-success a-function a-detail"
                          data-bs-toggle="tooltip"
                          data-bs-placement="top"
                          title="Cập nhật đơn hàng"
                          ><i class="fa-solid fa-file-pen"></i
                        ></a>
                      </td>
                      <td>
                        <a
                          href=""
                          class="btn btn-dark a-function a-detail"
                          data-bs-toggle="tooltip"
                          data-bs-placement="top"
                          title="Lịch sử cập nhật"
                          ><i class="fa-solid fa-clock-rotate-left"></i
                        ></a>
                      </td>
                      <td>
                        <a
                          v-on:click="cancelOrder(item)"
                          class="btn btn-danger a-function a-detail"
                          title="Hủy đơn hàng"
                        >
                          <i class="fa-solid fa-trash"></i
                        ></a>
                      </td>
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
import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotficationClient from "@/components/common/NotficationClient.vue";
import ActionLoading from "@/components/common/ActionLoading.vue";
import PopupNotify from "@/components/common/PopupNotify.vue";

import moment from "moment";
import { useCookies } from "vue3-cookies";
import { commonFunction } from "@/scripts/ulti";
import { debounce } from "vue-debounce";
import axios from "axios";

export default {
  components: {
    NavbarClient,
    FooterClient,
    ToolbarRight,
    NotficationClient,
    ActionLoading,
    PopupNotify,
  },
  data() {
    return {
      orderCode: "",
      receiverPhonenumber: "",
      filterTime: "",
      // filterTimeDS: "",
      classFilterTimeAbout: "d-none",
      // classFilterTimeAboutDS: "d-none",
      typeNotify: "",
      typeComponent: "",
      isShowNotify: false,
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
        { text: "Ngày tạo đơn", value: "createdAt", sortable: true },
        { text: "Chức năng", value: "btn-function-order" },
      ],
      dataNotify: {
        name: "",
        idRequest: 0,
        data: {},
      },
      listTypeShipping: {},
      filterOderCondition: {
        orderId: "",
        phoneReceiver: "",
        orderStatus: "",
        timeFrom: "",
        timeTo: "",
        // timeDSFrom: "",
        // timeDSTo: "",
        shippingType: "",
      },
      listOrderByCustomerBk: {},
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
    this.listTypeShipping = commonFunction.typeShipping;
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
        commonFunction.DOMAIN_URL + "v1/order/all/customer/" + this.idRequest,
        this.configRequestApi
      )
      .then((response) => {
        let respronseData = response.data;
        this.listOrderByCustomer = respronseData;
        this.listOrderByCustomerBk = respronseData;
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
    // filterTimeDS: {
    //   handler: function () {
    //     return this.filterTimeDS == "controlTimeAboutDS"
    //       ? (this.classFilterTimeAboutDS = "d-contents")
    //       : (this.classFilterTimeAboutDS = "d-none");
    //   },
    // },
  },
  methods: {
    resetFormSearch: function () {
      this.filterTime = "";
      // this.filterTimeDS = "";
      this.classFilterTimeAbout = "d-none";
      // this.classFilterTimeAboutDS = "d-none";
      this.filterOder = {
        orderId: "",
        phoneReceiver: "",
        orderStatus: "",
        timeFrom: "",
        timeTo: "",
        // timeDSFrom: "",
        // timeDSTo: "",
        shippingType: "",
      };
      this.listOrderByCustomer = this.listOrderByCustomerBk;
    },
    cancelOrder: function (item) {
      this.typeNotify = commonFunction.typeNotifyCancel;
      this.typeComponent = "ORDER";
      this.dataNotify = {
        name: item.id,
        idRequest: this.idRequest,
        data: {
          id: item.id.replaceAll("0", ""),
          orderSelected: item,
        },
      };
      this.isShowNotify = true;
      console.log(item);
    },
    genUrlUpdateOrder(item) {
      return "/client/orders/create#" + item.id;
    },
    closePopupNotify: function () {
      this.isShowNotify = false;
      this.typeNotify = "";
      this.typeComponent = "";
      this.dataNotify = {
        name: "",
        idRequest: 0,
        data: {},
      };
    },
    updateTypeNotify: (type) => (this.typeNotify = type),
    generateCodeToText: function (text) {
      return commonFunction.generateCodeToText(text);
    },
    filterOrder: function () {
      this.listOrderByCustomer = this.listOrderByCustomerBk;
      this.listOrderByCustomer = this.listOrderByCustomer.filter(
        (o) =>
          o.id ==
            (this.filterOderCondition.orderId != ""
              ? this.filterOderCondition.orderId
              : o.id) &&
          o.orderItem[0].shippingAddress.phone ==
            (this.filterOderCondition.phoneReceiver != ""
              ? this.filterOder.phoneReceiver
              : o.orderItem[0].shippingAddress.phone) &&
          o.status ==
            (this.filterOderCondition.orderStatus != ""
              ? this.filterOderCondition.orderStatus
              : o.status) &&
          o.shippingType ==
            (this.filterOderCondition.shippingType != ""
              ? this.filterOderCondition.shippingType
              : o.shippingType)
      );
      this.conditionFilterByTimeCreate(this.listOrderByCustomer);
      // this.conditionFilterByTimeDS(this.listOrderByCustomer);
    },
    // moment().subtract(1, "weeks").format("YYYY-MM-DD HH:MM:SS");
    conditionFilterByTimeCreate(listOrderByCustomer) {
      if (this.filterTime == "timeCOToday") {
        this.listOrderByCustomer = listOrderByCustomer.filter(
          (o) =>
            this.fomartDateYYYYMMDD(o.createdAt) ==
            this.fomartDateYYYYMMDD(new Date())
        );
      } else if (this.filterTime == "timeCO1week") {
        this.listOrderByCustomer = listOrderByCustomer.filter(
          (o) =>
            this.fomartDateYYYYMMDD(o.createdAt) >=
            this.fomartDateYYYYMMDD(moment().subtract(1, "weeks"))
        );
      } else if (this.filterTime == "timeCO1month") {
        this.listOrderByCustomer = listOrderByCustomer.filter(
          (o) =>
            this.fomartDateYYYYMMDD(o.createdAt) >=
            this.fomartDateYYYYMMDD(moment().subtract(1, "months"))
        );
      } else if (this.filterTime == "timeCO1year") {
        this.listOrderByCustomer = listOrderByCustomer.filter(
          (o) =>
            this.fomartDateYYYYMMDD(o.createdAt) >=
            this.fomartDateYYYYMMDD(moment().subtract(1, "years"))
        );
      } else if (this.filterTime == "timeCOAll") {
        console.log(this.filterTime);
      } else if (this.filterTime == "timeCOTimeAbout") {
        this.listOrderByCustomer = listOrderByCustomer.filter(
          (o) =>
            this.fomartDateYYYYMMDD(o.createdAt) >=
              this.fomartDateYYYYMMDD(this.filterOderCondition.timeFrom) &&
            this.fomartDateYYYYMMDD(o.createdAt) <=
              this.fomartDateYYYYMMDD(this.filterOderCondition.timeTo)
        );
      } else {
        console.log("DEFAULT");
      }
      console.log(this.listOrderByCustomer.lenth);
    },
    // conditionFilterByTimeDS(listOrderByCustomer) {
    //   if (this.filterTimeDS == "controlTimeToday") {
    //     this.listOrderByCustomer = listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) == this.fomartDateYYYYMMDD(new Date()));
    //   } else if (this.filterTimeDS == "controlTime1week") {
    //     this.listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) >= this.fomartDateYYYYMMDD(moment().subtract(1, "weeks")));
    //   } else if (this.filterTimeDS == "controlTime1month") {
    //     this.listOrderByCustomer = listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) >= this.fomartDateYYYYMMDD(moment().subtract(1, "months")));
    //   } else if (this.filterTimeDS == "controlTime1year") {
    //     this.listOrderByCustomer = listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) >= this.fomartDateYYYYMMDD(moment().subtract(1, "years")));
    //   } else if (this.filterTimeDS == "controlTimeAll") {
    //     console.log(this.filterTimeDS);
    //   } else if (this.filterTimeDS == "controlTimeAboutDS") {
    //     this.listOrderByCustomer = listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) >= this.fomartDateYYYYMMDD(moment(this.filterOderCondition.timeDSFrom))
    //       && this.fomartDateYYYYMMDD(o.createdAt) <= this.fomartDateYYYYMMDD(moment(this.filterOderCondition.timeDSTo)));
    //   } else {
    //     console.log("DEFAULT");
    //   }
    //   console.log(this.listOrderByCustomer.lenth);
    // },
    fomartDateYYYYMMDD(date) {
      return moment(date).format("YYYY-MM-DD");
    },
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

table tr td {
}

table tr td a {
  /* font-size: 5px; */
  padding: 2px;
}

table tr td a i {
  font-size: 18px;
  padding: 2px 2px 2px 2px;
}
</style>