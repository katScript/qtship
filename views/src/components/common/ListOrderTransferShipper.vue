<template>
  <div class="container-fluid list-order-transfer p-0">
    <br />
    <div class="row">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Bàn giao cho Shipper
        <span>(Tổng số: {{ listOrderTransfer.length }})</span>
      </h5>
      <div class="col-12">
        <div class="row">
          <div class="col-sm-10">
            <div class="row">
              <div class="col-11">
                <input type="text" class="form-control mb-1" placeholder="Mã ĐH hoặc Sđt người nhận"
                  v-model="conditionFilter" />
              </div>
              <div class="col-1">
                <button class="btn btn-outline-danger" v-on:click="filter">
                  <i class="fa-solid fa-magnifying-glass"></i>
                </button>
              </div>
            </div>
          </div>
          <div class="col-sm-2 mb-1">
            <button class="btn btn-danger" style="float: right" v-on:click="accessOrder()">
              Nhận giao đơn
            </button>
          </div>
        </div>
      </div>
      <easy-data-table :headers="headersOrder" :items="listOrderTransfer" v-model:items-selected="itemsSelected">
        <template #item-name-receiver="item">
          {{ item.shippingAddress.name }}
        </template>
        <template #item-phone-receiver="item">
          {{ item.shippingAddress.phone }}
        </template>
        <template #item-address-receiver="item">
          {{ buildAddressReceriver(item) }}
        </template>
      </easy-data-table>
    </div>
  </div>
</template>

<script>
  import OrderData from "@/components/models/order/order-data";
  import ShipperData from "@/components/models/shipping/shipper-data";

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
    data() {
      return {
        idRequest: 0,
        listOrderTransfer: [],
        listOrderTransferBk: [],
        listOrderIdSelected: [],
        headersOrder: [
          { text: "Mã ĐH", value: "orderCode", sortable: true },
          {
            text: "Người nhận",
            value: "name-receiver",
            sortable: true,
            width: 120,
          },
          { text: "SĐT nhận", value: "phone-receiver" },
          { text: "Địa chỉ giao hàng", value: "address-receiver", width: 250 },
        ],
        itemsSelected: [],
        conditionFilter: "",
      };
    },
    mounted() {
      this.shipperModel.setData(JSON.parse(commonFunction.getShipperStorage()));
      this.idRequest = this.shipperModel.getData().id;
      // get all order asigned shipper
      axios
        .get(
          commonFunction.DOMAIN_URL +
          "v1/shipper/order/assign/" +
          this.shipperModel.getData().id,
          commonFunction.configApi()
        )
        .then((response) => {
          // handle not found
          response.data.forEach((o) => {
            if (o.order.status == commonFunction.orderStatus.TransferShipper) {
              let order = new OrderData();
              order.setData(o.order);
              this.listOrderTransfer.push(order.getData());
              this.listOrderTransferBk.push(order.getData());
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
    updated() { },
    watch: {
      itemsSelected: {
        handler: function () { },
      },
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
        this.listOrderTransfer = this.listOrderTransferBk;
        this.listOrderTransfer = this.listOrderTransfer.filter(
          (e) =>
            e.orderCode.includes(this.conditionFilter) ||
            e.shippingAddress.phone.includes(this.conditionFilter)
        );
      },
      accessOrder: function () {
        this.prepareListIdRequest();
        axios
          .post(
            commonFunction.DOMAIN_URL + "v1/shipper/order/accept",
            this.listOrderIdSelected,
            commonFunction.configApi()
          )
          .then((response) => {
            console.log(response.data);
            alert("SUCCESS: " + response.data.message + " - Shipper nhận đơn thành công!");
            commonFunction.reloadPage();
          })
          .catch((e) => {
            console.log(e);
            alert("ERROR: Có lỗi xảy ra! Vui lòng thử lại hoặc liên hệ với quản trị viên!");
            commonFunction.reloadPage();
          });
      },
      prepareListIdRequest: function() {
        this.listOrderIdSelected = [];
        this.itemsSelected.forEach(element => {
          this.listOrderIdSelected.push(element.id);
        });
      }
    },
  };
</script>

<style scoped>
  @media screen and (max-width: 992px) {

    .btn-accept-order-all,
    .btn-accept-order {
      font-size: 10px;
      padding-right: 1px;
      padding-left: 1px;
    }
  }
</style>