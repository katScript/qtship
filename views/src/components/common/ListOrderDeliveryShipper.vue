<template>
  <div class="container-fluid list-order-shipper p-0">
    <br />
    <div class="row">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đơn đang giao
        <span>(Tổng số: 10 ĐH)</span>
      </h5>
      <div class="col-12">
        <div class="popup-order" :class="isShowDetail ? 'show' : 'hide'">
          <PopupDetailOrderShipper :orderDetail="orderDetail" @isCloseDetail="isCloseDetail" />
        </div>
        <div class="row d-flex justify-content-end">
          <div class="col-11 mb-1">
            <input type="text" class="form-control" placeholder="Mã ĐH hoặc Sđt người nhận" />
          </div>
          <div class="col-1">
            <button class="btn btn-danger">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </div>
        </div>
        <easy-data-table :headers="headersOrder" :items="listOrderDelivery" v-model:items-selected="itemsSelected">
          <template #item-name-receiver="item">
            {{ item.shippingAddress.name }}
          </template>
          <template #item-phone-receiver="item">
            {{ item.shippingAddress.phone }}
          </template>
          <template #item-address-receiver="item">
            {{ buildAddressReceriver(item) }}
          </template>
          <template #item-btn-function="item">
            <div class="d-flex">
              <div class="row">
                <div class="col-12 m-1">
                  <input type="text" class="form-control" placeholder="Ghi chú" />
                </div>
                <div class="col-12 d-flex">
                  <button class="btn btn-info a-function a-detail m-1" data-bs-toggle="tooltip" data-bs-placement="top"
                    v-on:click="detail(item)" title="Chi tiết">
                    Chi tiết
                  </button>

                  <button class="btn btn-primary a-function m-1" data-bs-toggle="tooltip" data-bs-placement="top" :class="item.status == 'SUCCESS' ? 'hide' : 'show'"
                    v-on:click="success(item)" title="Đã giao hàng">
                    Đã giao hàng
                  </button>

                  <button class="btn btn-success a-function m-1" data-bs-toggle="tooltip" data-bs-placement="top" :class="item.status == 'SUCCESS' ? 'show' : 'hide'"
                    v-on:click="paymented(item)" title="Đã thanh toán">
                    Đã thanh toán
                  </button>

                  <button class="btn btn-warning a-function m-1" data-bs-toggle="tooltip" data-bs-placement="top" :class="item.status == 'SUCCESS' ? 'hide' : 'show'"
                    v-on:click="delay(item)" title="Delay giao">
                    Delay giao
                  </button>

                  <button class="btn btn-danger a-function m-1" data-bs-toggle="tooltip" data-bs-placement="top" :class="item.status == 'SUCCESS' ? 'hide' : 'show'"
                    v-on:click="occurred(item)" title="Phát sinh">
                    Phát sinh
                  </button>

                  <button class="btn btn-danger a-function m-1" data-bs-toggle="tooltip" data-bs-placement="top" :class="item.status == 'SUCCESS' ? 'hide' : 'show'"
                    v-on:click="returnn(item)" title="Đơn hoàn">
                    Đơn hoàn
                  </button>
                </div>
              </div>
            </div>
          </template>
        </easy-data-table>
      </div>
    </div>
  </div>
</template>

<script>
  import OrderData from "@/components/models/order/order-data";
  import ShipperData from "@/components/models/shipping/shipper-data";
  import PopupDetailOrderShipper from "@/components/common/PopupDetailOrderShipper.vue";

  import { commonFunction } from "@/scripts/ulti";
  import axios from "axios";

  export default {
    setup() {
      const shipperModel = new ShipperData();
      const orderModel = new OrderData();
      return {
        shipperModel,
        orderModel,
      };
    },
    components: {
      PopupDetailOrderShipper,
    },
    data() {
      return {
        listOrderDelivery: [],
        listOrderDeliveryBk: [],
        itemsSelected: [],
        headersOrder: [
          { text: "Mã ĐH", value: "orderCode", sortable: true },
          {
            text: "Người nhận",
            value: "name-receiver",
            sortable: true,
            width: 120,
          },
          { text: "SĐT nhận", value: "phone-receiver" },
          { text: "Trạng thái", value: "status" },
          { text: "Địa chỉ giao hàng", value: "address-receiver", width: 250 },
          { text: "Chức năng", value: "btn-function" },
        ],
        isShowDetail: false,
        orderDetail: {},
        action: ""
      };
    },
    watch: {},
    mounted() {
      this.shipperModel.setData(JSON.parse(commonFunction.getShipperStorage()));
      this.idRequest = this.shipperModel.getData().id;
      // get all order asigned shipper
      axios
        .get(
          commonFunction.DOMAIN_URL +
          "v1/order/all/shipper/" +
          this.shipperModel.getData().id,
          commonFunction.configApi()
        )
        .then((response) => {
          // handle not found
          response.data.content.forEach((o) => {
            if (o.status == commonFunction.orderStatus.Delivery || o.status == commonFunction.orderStatus.Success) {
              let order = new OrderData();
              order.setData(o);
              this.listOrderDelivery.push(order.getData());
              this.listOrderDeliveryBk.push(order.getData());
            }
          });
        })
        .catch((e) => {
          console.log(e);
        });
      // this.itemsSelected.forEach(item => {
      //     this.listOrderIdSelected.push(item.id);
      //   }
      // )
    },
    methods: {
      buildAddressReceriver(item) {
        return (
          item.shippingAddress.street +
          ", " +
          item.shippingAddress.ward +
          ", " +
          item.shippingAddress.district +
          ", " +
          item.shippingAddress.province
        );
      },
      filter: function () {
        this.listOrderDelivery = this.listOrderDeliveryBk;
        this.listOrderDelivery = this.listOrderDelivery.filter(
          (e) =>
            e.orderCode.includes(this.conditionFilter) ||
            e.shippingAddress.phone.includes(this.conditionFilter)
        );
      },
      success: function (item) {
        axios
          .post(
            commonFunction.DOMAIN_URL + "v1/order/update/status",
            {
              id: item.id,
              status: commonFunction.orderStatus.Success,
            },
            commonFunction.configApi()
          )
          .then((response) => {
            console.log(response.data);
            alert("SUCCESS: Đã chuyển trạng thái thành: Giao hàng thành công!");
            commonFunction.reloadPage();
          })
          .catch((e) => {
            console.log(e);
          });
      },
      paymented: function (item) {
        axios
          .post(
            commonFunction.DOMAIN_URL + "v1/order/update/status",
            {
              id: item.id,
              status: commonFunction.orderStatus.Payment,
            },
            commonFunction.configApi()
          )
          .then((response) => {
            console.log(response.data);
            alert("SUCCESS: Đã chuyển trạng thái thành: Đã thanh toán!");
            commonFunction.reloadPage();
          })
          .catch((e) => {
            console.log(e);
          });
      },
      delay: function (item) {
        axios
          .post(
            commonFunction.DOMAIN_URL + "v1/order/update/status",
            {
              id: item.id,
              status: commonFunction.orderStatus.Delay,
            },
            commonFunction.configApi()
          )
          .then((response) => {
            console.log(response.data);
            alert("SUCCESS: Đã chuyển trạng thái thành: Delay giao hàng!");
            commonFunction.reloadPage();
          })
          .catch((e) => {
            console.log(e);
          });
      },
      occurred: function (item) {
        axios
          .post(
            commonFunction.DOMAIN_URL + "v1/order/update/status",
            {
              id: item.id,
              status: commonFunction.orderStatus.Occurred,
            },
            commonFunction.configApi()
          )
          .then((response) => {
            console.log(response.data);
            alert("SUCCESS: Đã chuyển trạng thái thành: Phát sinh vấn đề!");
            commonFunction.reloadPage();
          })
          .catch((e) => {
            console.log(e);
          });
      },
      returnn: function (item) {
        axios
          .post(
            commonFunction.DOMAIN_URL + "v1/order/update/status",
            {
              id: item.id,
              status: commonFunction.orderStatus.Return,
            },
            commonFunction.configApi()
          )
          .then((response) => {
            console.log(response.data);
            alert("SUCCESS: Đã chuyển trạng thái thành: Đơn hoàn trả!");
            commonFunction.reloadPage();
          })
          .catch((e) => {
            console.log(e);
          });
      },
      detail: function (item) {
        this.orderDetail = item;
        this.isShowDetail = true;
      },
      isCloseDetail: function (value) {
        this.isShowDetail = value;
      },
      emitTabSelected: function (value) {
        this.$emit("emitTabSelected", value);
      }
    },
  };
</script>

<style scoped>
  @media screen and (max-width: 992px) {
    .btn-detail-order {
      font-size: 10px;
      padding-right: 1px;
      padding-left: 1px;
      width: 45px;
    }
  }
</style>