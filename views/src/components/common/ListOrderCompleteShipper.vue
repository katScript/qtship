<template>
  <div class="container-fluid list-order-shipper p-0">
    <br />
    <div class="row">
      <div class="col-4">
        <label for="" class="label-control">Loại đơn hàng: </label>
      </div>
      <div class="col-8">
        <select class="form-select" aria-label="Default select example" v-model="conditionFilter">
          <option value="ALL">Tất cả</option>
          <option value="SUCCESS">Đã giao</option>
          <option value="PAYMENTED">Đã thanh toán</option>
          <option value="DELAY">Delay giao hàng</option>
          <option value="OCCURRED">Đơn phát sinh</option>
          <option value="RETURN">Đơn hoàn</option>
        </select>
      </div>
    </div>
    <div class="row">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đơn đã xử lí
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
          :items="listOrderComplete"
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
      listOrderComplete: [],
      listOrderCompleteBk: [],
      itemsSelected: [],
      conditionFilter: 'ALL',
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
        { text: "Trạng thái", value: "status" },
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
          if (this.conditionFilterStatus(o.order.status)) {
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
      this.listOrderComplete = this.listOrderCompleteBk;
      this.listOrderComplete = this.listOrderComplete.filter(
        (e) =>
          e.orderCode.includes(this.conditionFilter) ||
          e.shippingAddress.phone.includes(this.conditionFilter)
      );
      if(this.conditionFilter == 'ALL') {
        this.listOrderComplete = this.listOrderCompleteBk;
      }
    },
    conditionFilterStatus: function (status) {
      if (
        status == commonFunction.orderStatus.Payment ||
        status == commonFunction.orderStatus.Delay ||
        status == commonFunction.orderStatus.Occurred ||
        status == commonFunction.orderStatus.Return
      ) {
        return true;
      }
      return false;
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