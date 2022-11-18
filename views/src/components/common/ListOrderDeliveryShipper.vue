<template>
  <div class="container-fluid list-order-shipper p-0">
    <br />
    <div class="row">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đơn đang giao
        <span>(Tổng số: 10 ĐH)</span>
      </h5>
      <div class="col-12">
        <div class="row d-flex justify-content-end">
          <div class="col-11 mb-1">
            <input
              type="text"
              class="form-control"
              placeholder="Mã ĐH hoặc Sđt người nhận"
            />
          </div>
          <div class="col-1">
            <button class="btn btn-danger">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </div>
        </div>
        <easy-data-table
          :headers="headersOrder"
          :items="listOrderDelivery"
          v-model:items-selected="itemsSelected"
        >
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
        { text: "Địa chỉ giao hàng", value: "address-receiver", width: 250 },
      ],
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
          "v1/shipper/order/assign/" +
          this.shipperModel.getData().id,
        commonFunction.configApi()
      )
      .then((response) => {
        // handle not found
        response.data.forEach((o) => {
          if (o.order.status == commonFunction.orderStatus.Delivery) {
            let order = new OrderData();
            order.setData(o.order);
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
    changeStatus: function () {
      // this.prepareListIdRequest();
      // axios
      //   .post(
      //     commonFunction.DOMAIN_URL + "v1/shipper/order/accept",
      //     {},
      //     commonFunction.configApi()
      //   )
      //   .then((response) => {
      //     console.log(response.data);
      //   })
      //   .catch((e) => {
      //     console.log(e);
      //   });
    },
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