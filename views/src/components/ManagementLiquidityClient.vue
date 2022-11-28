<template>
  <div class="management-client-page">
    <div :class="listLiquidityBk.length == 0 ? 'show' : 'hide'">
      <ActionLoading />
    </div>
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
                    (4) PP-Cash: Người gửi thanh toán
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
                <h5>Phiên đối soát gần nhất: {{lastLiquidity.createdAt}}</h5>
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th scope="col">Tiền thu hộ</th>
                      <th scope="col">PP_PM (2)</th>
                      <th scope="col">CC-Cash (3)</th>
                      <th scope="col">PP-Cash (4)</th>
                      <th scope="col">Tiền đối soát</th>
                      <th scope="col">Tiền công nợ (5)</th>
                      <th scope="col">Tiền Khuyến Mãi (6)</th>
                      <th scope="col">Ngày đối soát</th>
                    </tr>
                  </thead>
                  <tbody v-if="lastLiquidity">
                    <tr>
                      <td>{{lastLiquidity.moneyReceivable}}</td>
                      <td>{{lastLiquidity.ppPm}}</td>
                      <td>{{lastLiquidity.ccCash}}</td>
                      <td>{{lastLiquidity.ppCash}}</td>
                      <td>{{lastLiquidity.ppCash}}</td>
                      <td>{{lastLiquidity.receivable}}</td>
                      <td>{{lastLiquidity.couponCash}}</td>
                      <td>{{lastLiquidity.createdAt}}</td>
                    </tr>
                  </tbody>
                  <tbody v-else>
                    <tr>
                      <td colspan="8" class="text-center">
                        <span></span>
                        <p>Chưa có phiên đối soát</p>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <hr />
            <div class="row">
              <div class="">
                <h5>Lịch sử đối soát</h5>
              </div>
              <div class="col-md-12">
                <span class="text-danger">{{msgValidFilter}}</span>
                <div class="row">
                  <div class="col-md-5 d-flex">
                    <span class="mt-2 me-1">Từ: </span>
                    <input type="datetime-local" name="" id="" class="form-control w-100" v-model="dateFilter.from" />
                  </div>
                  <div class="col-md-5 d-flex">
                    <span class="mt-2 me-1">Đến: </span>
                    <input type="datetime-local" name="" id="" class="form-control w-100" v-model="dateFilter.to" />
                  </div>
                  <div class="col-md-2 d-flex">
                    <button class="btn btn-success w-100 me-1" v-on:click="filterLiquidity()">
                      Tìm kiếm
                    </button>
                    <button class="btn btn-secondary w-100" v-on:click="resetFilter()">
                      Bỏ lọc
                    </button>
                  </div>
                </div>
              </div>
              <div class="col-12 mt-1" v-if="listLiquidity.length > 0">
                <easy-data-table :headers="headers" :items="listLiquidity" table-class-name="easy-data-table-customize">
                </easy-data-table>
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
  import Liquidity from "@/components/models/liquidity/liquidity";
  import CustomerData from "@/components/models/customer/customer-data";
  import ActionLoading from "@/components/common/ActionLoading.vue";

  import { useCookies } from "vue3-cookies";
  import { commonFunction } from '../scripts/ulti'
  import axios from "axios";
  import moment from "moment";

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
        listLiquidity: [],
        listLiquidityBk: [],
        lastLiquidity: {},
        dateFilter: {
          from: "",
          to: ""
        },
        msgValidFilter: "",
        headers: [
          { text: "Tiền thu hộ", value: "moneyReceivable" },
          { text: "PP_PM (2)", value: "ppPm" },
          { text: "CC-Cash (3)", value: "ccCash" },
          { text: "PP-Cash (4)", value: "ppCash" },
          { text: "Tiền đối soát", value: "forControl" },
          { text: "Tiền công nợ (5)", value: "receivable" },
          { text: "Tiền Khuyến Mãi (6)", value: "couponCash" },
          { text: "Ngày đối soát", value: "createdAt", sortable: true },
        ],
      };
    },

    setup() {
      const { cookies } = useCookies();
      let liquidityModel = new Liquidity(),
        customerModel = new CustomerData();

      return {
        cookies,
        liquidityModel,
        customerModel
      };
    },

    // <data, methods...>

    async mounted() {
      let auth = commonFunction.getCookies(commonFunction.userCookies.username),
        role = commonFunction.getCookies(commonFunction.userCookies.roles),
        token = commonFunction.getCookies(commonFunction.userCookies.token);

      if (auth == null && role !== "shipper") {
        commonFunction.redirect("/");
      }

      this.configRequestApi = {
        headers: { Authorization: "Bearer " + token },
      };

      this.customerModel.setData(
        JSON.parse(commonFunction.getCustomerStorage())
      );

      let customerId = this.customerModel.getData().id;

      this.idRequest = customerId;

      //warehouse
      await axios.get(
        commonFunction.DOMAIN_URL +
        "v1/liquidity/all/customer/" +
        this.idRequest,
        this.configRequestApi
      )
        .then((response) => {
          response.data.forEach(element => {
            let liquidity = new Liquidity();
            liquidity.setData(element);
            this.listLiquidity.push(liquidity);
            this.listLiquidityBk.push(liquidity);
          });
          this.lastLiquidity = this.listLiquidityBk[0];
        })
        .catch((e) => {
          console.log(e);
        });


    },
    watch: {},
    methods: {
      filterLiquidity: function () {
        let dateFromFormat = this.dateFilter.from == "" ? moment(new Date()).format("YYYY-MM-DD hh:mm:ss") : moment(this.dateFilter.from).format("YYYY-MM-DD hh:mm:ss");
        let dateToFormat = this.dateFilter.to == "" ? moment(new Date()).format("YYYY-MM-DD hh:mm:ss") : moment(this.dateFilter.to).format("YYYY-MM-DD hh:mm:ss");
        this.listLiquidity = this.listLiquidityBk;
        if (dateFromFormat >= dateToFormat) {
          this.msgValidFilter = "Điều kiện tìm kiếm không hợp lệ!"
        } else {
          this.msgValidFilter = "";
          this.listLiquidity = this.listLiquidity.filter(item =>
            item.createdAt >= dateFromFormat
            && item.createdAt <= dateToFormat
          )
        }
      },
      resetFilter: function () {
        this.listLiquidity = this.listLiquidityBk;
        this.msgValidFilter = "";
        this.dateFilter = {
          from: "",
          to: ""
        }
      }
    },
  };
</script>

<style scoped>
  ul li {
    list-style: none;
  }
</style>