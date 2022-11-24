<template>
  <div class="container-fluid list-order-shipper p-0">
    <br />
    <div class="row">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đã nhận
        <span>(Tổng số: 10 ĐH)</span>
      </h5>
      <div class="col-12">
        <!-- <div class="popup-order" :class=" isShowDetail ? 'show' : 'hide'">
          <PopupDetailOrderShipper :orderDetail="orderDetail" @isCloseDetail="isCloseDetail" />
        </div> -->
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
        <easy-data-table :headers="headersOrder" :items="listOrderConfirmed" v-model:items-selected="itemsSelected">
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
              <!-- <button class="btn btn-primary a-function a-detail m-1" data-bs-toggle="tooltip" data-bs-placement="top"
                v-on:click="detail(item)" title="Chi tiết">Chi tiết</button> -->

              <button class="btn btn-danger a-function m-1" data-bs-toggle="tooltip" data-bs-placement="top"
                v-on:click="delivery(item)" title="Đang giao hàng">Đang giao hàng</button>
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
  // import PopupDetailOrderShipper from "@/components/common/PopupDetailOrderShipper.vue";

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
      // PopupDetailOrderShipper
    },
    data() {
      return {
        listOrderConfirmed: [],
        listOrderConfirmedBk: [],
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
      };
    },
    watch: {},
    async mounted() {
      this.shipperModel.setData(JSON.parse(commonFunction.getShipperStorage()));
      this.idRequest = this.shipperModel.getData().id;
      // get all order asigned shipper
      await axios
        .get(
          commonFunction.DOMAIN_URL +
          "v1/order/all/shipper/" +
          this.shipperModel.getData().id,
          commonFunction.configApi()
        )
        .then((response) => {
          // handle not found
          response.data.content.forEach((o) => {
            if (o.status == commonFunction.orderStatus.ShipperConfirm) {
              let order = new OrderData();
              order.setData(o);
              this.listOrderConfirmed.push(order.getData());
              this.listOrderConfirmedBk.push(order.getData());
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
        this.listOrderConfirmed = this.listOrderConfirmedBk;
        this.listOrderConfirmed = this.listOrderConfirmed.filter(
          (e) =>
            e.orderCode.includes(this.conditionFilter) ||
            e.shippingAddress.phone.includes(this.conditionFilter)
        );
      },
      delivery: function (item) {
        axios
          .post(
            commonFunction.DOMAIN_URL + "v1/order/update/status",
            [{
              "id": item.id,
              "status": commonFunction.orderStatus.Delivery
            }],
            commonFunction.configApi()
          )
          .then((response) => {
            console.log(response.data);
            alert("SUCCESS: Đã chuyển trạng thái thành: Đang giao hàng!");
            commonFunction.reloadPage();
          })
          .catch((e) => {
            console.log(e);
            alert("ERROR: Đã có lỗi xảy ra! Vui lòng thử lại hoặc liên hệ với quản trị viên.");
          });
      },
      detail: function (item) {
        this.orderDetail = item;
        this.isShowDetail = true;
      },
      // isCloseDetail: function (value) {
      //   this.isShowDetail = value;
      // }
      emitTabSelected: function(value) {
        this.$emit("emitTabSelected",value);
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


  /* popup */
</style>