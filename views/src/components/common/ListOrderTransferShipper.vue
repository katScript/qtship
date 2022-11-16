<template>
  <div class="container-fluid list-order-transfer p-0">
    <br />
    <div class="row">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Bàn giao cho Shipper
        <span>(Tổng số: 10 ĐH)</span>
      </h5>
      <div class="col-12 mb-1">
        <div class="row">
          <div class="col-sm-10 mb-1">
            <div class="row">
              <div class="col-10">
                <input type="text" class="form-control" placeholder="Mã ĐH hoặc Sđt người nhận"
                  v-model="conditionFilter" />
              </div>
              <div class="col-2">
                <button class="btn btn-outline-danger" v-on:click="filter">
                  <i class="fa-solid fa-magnifying-glass"></i>
                </button>
              </div>
            </div>
          </div>
          <div class="col-sm-2 mb-1">
            <button class="btn btn-danger" style="float: right;">Nhận giao đơn</button>
          </div>
        </div>
      </div>
      <easy-data-table :headers="headersOrder" :items="listOrderTransfer" v-model:items-selected="itemsSelected">
        <template #item-name-receiver="item">
          {{ item.orderItem[0].shippingAddress.name }}
        </template>
        <template #item-phone-receiver="item">
          {{ item.orderItem[0].shippingAddress.phone }}
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

  import { commonFunction } from '@/scripts/ulti';
  // import axios from "axios";

  export default {
    props: ["listOrderData"],
    setup() {
      const shipperModel = new ShipperData();
      const orderModel = new OrderData();
      return {
        shipperModel,
        orderModel
      }
    },
    data() {
      return {
        idRequest: 0,
        listOrderTransfer: [],
        listOrderTransferBk: [],
        headersOrder: [
          { text: "Mã ĐH", value: "orderCode", sortable: true },
          { text: "Người nhận", value: "name-receiver", sortable: true,  width: 120},
          { text: "SĐT nhận", value: "phone-receiver" },
          { text: "Địa chỉ giao hàng", value: "address-receiver" ,  width: 250},
        ],
        itemsSelected: [],
        conditionFilter: ""
      }
    },
    mounted() {
      this.shipperModel.setData(
        JSON.parse(commonFunction.getShipperStorage())
      );
      this.idRequest = this.shipperModel.getData().id;
    },
    updated() {
      this.listOrderTransfer = this.listOrderData;
      this.listOrderTransferBk = this.listOrderData;
    },
    watch: {
    },
    methods: {
      buildAddressReceriver(item) {
        return item.orderItem[0].shippingAddress.street
          + ", " +
          item.orderItem[0].shippingAddress.ward
          + ", " +
          item.orderItem[0].shippingAddress.district
          + ", " +
          item.orderItem[0].shippingAddress.province;
      },
      filter: function () {
        this.listOrderTransfer = this.listOrderTransferBk;
        this.listOrderTransfer = this.listOrderTransfer.filter(
          e => e.orderCode.includes(this.conditionFilter)
            || e.orderItem[0].shippingAddress.phone.includes(this.conditionFilter));
      },
    },
  }
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