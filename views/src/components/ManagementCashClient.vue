<template>
  <div class="management-client-page">
    <NavbarClient />
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2">
          <NotficationClient />
        </div>
        <div class="col-md-8">
          <div class="management-cash">
            <div class="row">
              <h4>
                <i class="fa-solid fa-money-bill-transfer"></i> Quản lí dòng
                tiền
              </h4>
              <div class="col-12">
                Tổng số tiền shop sẽ nhận được trong lần đối soát tới là: 0 đ
                (Số dư chốt)
                <ul>
                  <li>(2) PP_PM: Cuối tháng thanh toán</li>
                  <li>
                    (3) CC-Cash: Người Nhận thanh toán
                  </li>
                  <li>
                    (4) PP - Cash: Người gửi thanh toán
                  </li>
                  <li>
                    (5) Tiền công nợ: Tiền shop nợ trong quá trình giao/nhận hàng
                  </li>
                  <li>
                    (6) Tiền giảm từ Mã khuyến mại
                  </li>
                </ul>
              </div>
              <div class="col-12">
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th scope="col">Tiền thu hộ</th>
                      <th scope="col">PP_PM (2)</th>
                      <th scope="col">CC-Cash (3)</th>
                      <th scope="col">PP - Cash (4)</th>
                      <th scope="col">Tiền đối soát</th>
                      <th scope="col">Tiền công nợ (5)</th>
                      <th scope="col">Tiền Khuyến Mãi (6)</th>
                      <th scope="col">Chức năng</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <hr />
            <div class="row">
              <div class="col-md-3">
                <h5>Lịch sử đối soát</h5>
              </div>
              <div class="col-md-9">
                <div class="row">
                  <div class="col-md-4">
                    <input type="text" class="form-control" placeholder="Mã đơn hàng" />
                  </div>
                  <div class="col-md-4">
                    <input type="date" name="" id="" class="form-control w-100" />
                  </div>
                  <div class="col-md-4">
                    <button class="btn btn-success w-100">
                      <i class="fa-solid fa-magnifying-glass"></i> Tìm kiếm
                    </button>
                  </div>
                </div>
              </div>
              <div class="col-12">
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th scope="col">Tiền thu hộ</th>
                      <th scope="col">PP_PM (2)</th>
                      <th scope="col">CC-Cash (3)</th>
                      <th scope="col">PP - Cash (4)</th>
                      <th scope="col">Tiền đối soát</th>
                      <th scope="col">Tiền công nợ</th>
                      <th scope="col">Tiền Khuyến Mãi</th>
                      <th scope="col">Chức năng</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                    </tr>
                  </tbody>
                </table>
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
  import ToolbarRight from "./common/ToolbarRight.vue";
  import NotficationClient from "./common/NotficationClient.vue";

  import { useCookies } from "vue3-cookies";
  import { commonFunction } from '../scripts/ulti'

  export default {
    components: {
      NavbarClient,
      FooterClient,
      ToolbarRight,
      NotficationClient,
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
      let authenication_cookies = this.cookies.get(commonFunction.userCookies.username);
      if (authenication_cookies == null) {
        commonFunction.redirect('/');
      }
    },
    watch: {},
    method: {},
  };
</script>

<style scoped>
  ul li {
    list-style: none;
  }
</style>