<template>
  <div class="management-client-page">
    <NavbarClient :isShipper="isShipper" />
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="row">
            <div class="col-md-12">
              <h6 class="text-danger">
                <i class="fa-solid fa-briefcase"></i> Phiên làm việc:
                <span class="text-dark">{{ currentDate }}</span>
              </h6>
            </div>
          </div>
        </div>
        <hr />
        <div class="col-12">
          <div class="row">
            <div class="col-6 text-center">
              <h3>Tổng số đơn hàng:</h3>
              <h1>100</h1>
            </div>
            <div class="col-6">
              <ul class="list-group">
                <li class="list-group-item border-0 border-bottom">
                  Số đơn đã lấy: <span>12</span>
                </li>
                <li class="list-group-item border-0 border-bottom">
                  Số đơn đang giao: <span>12</span>
                </li>
                <li class="list-group-item border-0 border-bottom">
                  Số đơn đã giao/thanh toán: <span>12</span>
                </li>
                <li class="list-group-item border-0 border-bottom">
                  Số đơn hoàn trả: <span>12</span>
                </li>
                <li class="list-group-item border-0 border-bottom">
                  Số đơn delay: <span>12</span>
                </li>
              </ul>
            </div>
          </div>
          <hr />
          <div class="row">
            <h5>Dòng tiền</h5>
            <div class="col-6">
              Tổng giá trị đơn:
              <br />
              <small>(*) Tổng giá trị các đơn hàng đã giao và thu COD</small>
            </div>
            <div class="col-6">
              <h3>10.500.000 <span style="font-size: 15px">(VNĐ)</span></h3>
            </div>
          </div>
          <hr />
        </div>
      </div>
      <div class="row">
        <div class="col-4">
          <label for="" class="label-control">Loại đơn hàng: </label>
        </div>
        <div class="col-8">
          <select
            class="form-select"
            aria-label="Default select example"
            v-model="typeOrderFilter"
          >
            <option value="0">Tất cả</option>
            <option value="1">Đã lấy hàng</option>
            <option value="2">Đang giao</option>
            <option value="3">Đã giao/Đã thanh toán</option>
            <option value="4">Delay giao hàng</option>
            <option value="5">Hoàn hàng</option>
            <option value="6">Khác...</option>
          </select>
        </div>
      </div>
      <!-- ALL -->
      <div class="row" v-if="typeOdersListDisplay == 'ALL'">
        <h5><i class="fa-solid fa-play"></i> Đơn hàng: Đang giao <span>(Tổng số: 10 ĐH)</span></h5>
        <div class="col-12">
          <div class="row d-flex justify-content-end">
            <div class="col-10">
              <input
                type="text"
                class="form-control"
                placeholder="Mã ĐH hoặc Sđt người nhận"
              />
            </div>
            <div class="col-2">
              <button class="btn btn-danger">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">Mã ĐH</th>
                <th scope="col">Người nhận</th>
                <th scope="col">Sđt người nhận</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">MD0001003</th>
                <td>John</td>
                <td>0987612312</td>
                <td>
                  <button class="btn btn-outline-danger">Chi tiết</button>
                </td>
              </tr>
              <tr>
                <td>Địa chỉ:</td>
                <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
              </tr>
            </tbody>
            <tbody>
              <tr>
                <th scope="row">MD0001002</th>
                <td>Mark</td>
                <td>0987654321</td>
                <td>
                  <button class="btn btn-outline-danger">Chi tiết</button>
                </td>
              </tr>
              <tr>
                <td>Địa chỉ:</td>
                <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- TAKED -->
      <div class="row" v-else-if="typeOdersListDisplay == 'TAKED'">
        <h5><i class="fa-solid fa-play"></i> Đơn hàng: Đã lấy hàng <span>(Tổng số: 10 ĐH)</span></h5>
        <div class="col-12">
          <div class="row d-flex justify-content-end">
            <div class="col-10">
              <input
                type="text"
                class="form-control"
                placeholder="Mã ĐH hoặc Sđt người nhận"
              />
            </div>
            <div class="col-2">
              <button class="btn btn-danger">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">Mã ĐH</th>
                <th scope="col">Người nhận</th>
                <th scope="col">Sđt người nhận</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">MD0002134</th>
                <td>John</td>
                <td>0987612312</td>
                <td>
                  <button class="btn btn-outline-danger">Chi tiết</button>
                </td>
              </tr>
              <tr>
                <td>Địa chỉ:</td>
                <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- DELIVERING -->
      <div class="row" v-else-if="typeOdersListDisplay == 'DELIVERING'">
        <h5><i class="fa-solid fa-play"></i> Đơn hàng: Đang giao <span>(Tổng số: 10 ĐH)</span></h5>
        <div class="col-12">
          <div class="row d-flex justify-content-end">
            <div class="col-10">
              <input
                type="text"
                class="form-control"
                placeholder="Mã ĐH hoặc Sđt người nhận"
              />
            </div>
            <div class="col-2">
              <button class="btn btn-danger">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">Mã ĐH</th>
                <th scope="col">Người nhận</th>
                <th scope="col">Sđt người nhận</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">MD0002134</th>
                <td>John</td>
                <td>0987612312</td>
                <td>
                  <button class="btn btn-outline-danger">Chi tiết</button>
                </td>
              </tr>
              <tr>
                <td>Địa chỉ:</td>
                <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- DELIVERED -->
      <div class="row" v-else-if="typeOdersListDisplay == 'DELIVERED'">
        <h5><i class="fa-solid fa-play"></i> Đơn hàng: Đã giao và thanh toán COD <span>(Tổng số: 10 ĐH)</span></h5>
        <div class="col-12">
          <div class="row d-flex justify-content-end">
            <div class="col-10">
              <input
                type="text"
                class="form-control"
                placeholder="Mã ĐH hoặc Sđt người nhận"
              />
            </div>
            <div class="col-2">
              <button class="btn btn-danger">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">Mã ĐH</th>
                <th scope="col">Người nhận</th>
                <th scope="col">Sđt người nhận</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">MD0000004</th>
                <td>John</td>
                <td>0987612312</td>
                <td>
                  <button class="btn btn-outline-danger">Chi tiết</button>
                </td>
              </tr>
              <tr>
                <td>Địa chỉ:</td>
                <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- DELAYDELIVERY -->
      <div class="row" v-else-if="typeOdersListDisplay == 'DELAYDELIVERY'">
        <h5><i class="fa-solid fa-play"></i> Đơn hàng: Delay giao hàng <span>(Tổng số: 10 ĐH)</span></h5>
        <div class="col-12">
          <div class="row d-flex justify-content-end">
            <div class="col-10">
              <input
                type="text"
                class="form-control"
                placeholder="Mã ĐH hoặc Sđt người nhận"
              />
            </div>
            <div class="col-2">
              <button class="btn btn-danger">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">Mã ĐH</th>
                <th scope="col">Người nhận</th>
                <th scope="col">Sđt người nhận</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">MD0002221</th>
                <td>John</td>
                <td>0987612312</td>
                <td>
                  <button class="btn btn-outline-danger">Chi tiết</button>
                </td>
              </tr>
              <tr>
                <td>Địa chỉ:</td>
                <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- TORETURN -->
      <div class="row" v-else-if="typeOdersListDisplay == 'TORETURN'">
        <h5><i class="fa-solid fa-play"></i> Đơn hàng: Hoàn trả <span>(Tổng số: 10 ĐH)</span></h5>
        <div class="col-12">
          <div class="row d-flex justify-content-end">
            <div class="col-10">
              <input
                type="text"
                class="form-control"
                placeholder="Mã ĐH hoặc Sđt người nhận"
              />
            </div>
            <div class="col-2">
              <button class="btn btn-danger">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">Mã ĐH</th>
                <th scope="col">Người nhận</th>
                <th scope="col">Sđt người nhận</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">MD0001112</th>
                <td>John</td>
                <td>0987612312</td>
                <td>
                  <button class="btn btn-outline-danger">Chi tiết</button>
                </td>
              </tr>
              <tr>
                <td>Địa chỉ:</td>
                <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- ORTHER -->
      <div class="row" v-else-if="typeOdersListDisplay == 'ORTHER'">
        <h5><i class="fa-solid fa-play"></i> Đơn hàng: Khác <span>(Tổng số: 10 ĐH)</span></h5>
        <div class="col-12">
          <div class="row d-flex justify-content-end">
            <div class="col-10">
              <input
                type="text"
                class="form-control"
                placeholder="Mã ĐH hoặc Sđt người nhận"
              />
            </div>
            <div class="col-2">
              <button class="btn btn-danger">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">Mã ĐH</th>
                <th scope="col">Người nhận</th>
                <th scope="col">Sđt người nhận</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">MD0004312</th>
                <td>John</td>
                <td>0987612312</td>
                <td>
                  <button class="btn btn-outline-danger">Chi tiết</button>
                </td>
              </tr>
              <tr>
                <td>Địa chỉ:</td>
                <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
              </tr>
            </tbody>
          </table>
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

import { useCookies } from "vue3-cookies";
// import { commonFunction } from '../scripts/ulti'

export default {
  components: {
    NavbarClient,
    FooterClient,
  },
  data() {
    return {
      isShipper: true,
      currentDate: "",
      typeOrderFilter: 0,
      typeOdersListDisplay: "ALL",
    };
  },

  setup() {
    const { cookies } = useCookies();
    return {
      cookies,
    };
  },

  // <data, methods...>
  created() {},
  mounted() {
    let today = new Date();
    this.currentDate =
      String(today.getDate()).padStart(2, "0") +
      "/" +
      String(today.getMonth() + 1).padStart(2, "0") +
      "/" +
      today.getFullYear();
    // let authenication_cookies = this.cookies.get("authenication_cookies");
    // if(authenication_cookies == null){
    //   commonFunction.redirect('/');
    // } else {
    //     commonFunction.redirect('/client/management');
    // }
  },
  watch: {
    typeOrderFilter: {
      handler: function () {
        switch (this.typeOrderFilter) {
          case "0":
            this.typeOdersListDisplay = "ALL";
            break;
          case "1":
            this.typeOdersListDisplay = "TAKED";
            break;
          case "2":
            this.typeOdersListDisplay = "DELIVERING";
            break;
          case "3":
            this.typeOdersListDisplay = "DELIVERED";
            break;
          case "4":
            this.typeOdersListDisplay = "DELAYDELIVERY";
            break;
          case "5":
            this.typeOdersListDisplay = "TORETURN";
            break;
          case "6":
            this.typeOdersListDisplay = "ORTHER";
            break;
          default:
            this.typeOdersListDisplay = "ALL";
            break;
        }
      },
    },
  },
  method: {},
};
</script>

<style scoped>
ul li {
  list-style: none;
}
</style>