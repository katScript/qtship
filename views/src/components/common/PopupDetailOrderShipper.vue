<template>
  <div class="popup-detail-order-shipper w-100 mb-1">
    <button class="btn btn-close-popup text-danger" v-on:click="closePopup">
      <i class="fa-solid fa-xmark"></i>
    </button>
    <div class="row order-detail">
      <div class="col-md-6">
        <h5>Thông tin đơn hàng</h5>
        <label for="">Mã đơn hàng: </label> <span>{{ orderItem.orderCode }}</span>
        <br />
        <label for="">Phương thức giao hàng: </label> <span>{{ orderItem.shippingType }}</span>
        <br />
        <label for="">Trường hợp hoàn hàng: </label> <span>{{ orderItem.returnCode }}</span>
        <br />
        <label for="">Sản phẩm: </label> <br />
        <span v-for="(item, index) in orderItem.products" :key="index"
          >{{ index + 1 }}. {{ item.name }} - SL: {{ item.qty }} <br
        /></span>
        <br />
        <label for="">Trọng lượng đơn hàng: </label> <span>{{ orderItem.totalWeight }}</span>
        <br />
        <label for="">Ngày tạo đơn: </label> <span>{{ orderItem.createdAt }}</span>
        <br />
        <label for="">Ghi chú: </label> <span>{{ orderItem.note }}</span>
        <br />
        <label for="">Tổng tiền: </label>
        <span>
          <h4>{{ orderItem.subtotal }} VNĐ</h4></span
        >
      </div>
      <div class="col-md-6">
        <h5>Thông tin người gửi</h5>
        <label for="">Tên: </label> <span>{{ orderItem.senderName }}</span>
        <br />
        <label for="">SĐT: </label> <span>{{ orderItem.senderPhone }}</span>
        <br />
        <label for="">Đ/c: </label> <span>{{ orderItem.senderAddress }}</span>
        <br /><br />
        <h5>Thông tin người nhận</h5>
        <label for="">Tên: </label> <span>{{ orderItem.shippingAddress.name }}</span>
        <br />
        <label for="">SĐT: </label> <span>{{ orderItem.shippingAddress.phone }}</span>
        <br />
        <label for="">Đ/c: </label> <span>{{ buildAddressInfo(orderItem.shippingAddress) }}</span>
      </div>
    </div>
  </div>
  <hr />
</template>

<script>
export default {
  props: ["orderDetail"],
  data() {
    return {
      orderItem: {
        shippingAddress: {},
      },
    };
  },
  mounted() {
  },
  updated() {
    this.orderItem = this.orderDetail;
  },
  methods: {
    closePopup: function () {
      this.$emit("isCloseDetail", false);
    },
    buildAddressInfo: function (item) {
      return (
        item.street +
        ", " +
        item.ward +
        ", " +
        item.district +
        ", " +
        item.province
      );
    },
  },
};
</script>

<style scoped>
.popup-detail-order-shipper {
  height: fit-content;
}

.show {
  display: block;
}

.hide {
  display: none;
}

.btn-close-popup {
  font-size: 30px;
  float: right;
}
</style>