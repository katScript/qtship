<template>
  <!-- bill -->
  <div class="bill-order-area">
    <div class="bill-order-content" id="bill-content">
      <div class="content">
        <div class="header d-flex">
          <div class="col-5 d-inline">
            <div class="  w-100 p-1">
              <img src="@/images/logo-branch.png" class="" alt="" width="163" />
            </div>
          </div>
          <div class="col-7 d-inline text-center">
            <div class="  w-100 pt-1">
              <GenBarCode :orderCode="orderData.orderCode" />
              <small>Mã Đơn hàng: {{ orderData.orderCode }}</small>
            </div>
          </div>
        </div>
        <div class="line-hr"></div>
        <div class="sender-receiver d-flex">
          <div class="col-6 d-inline">
            <div class="  w-100 p-1">
              <label for="">Từ:</label>
              <small>{{ orderData.senderName }}</small>
              <br />
              <small>Đ/c: {{ orderData.senderAddress }}</small>
              <br />
              <small>SĐT: {{ orderData.senderPhone }}</small>
            </div>
          </div>
          <div class="col-6 d-inline">
            <div class="  w-100 p-1">
              <label for="">Đến: </label>
              <small>{{ orderData.shippingAddress.name }}</small>
              <br />
              <small>Đ/c:
                {{
                orderData.shippingAddress.street +
                ", " +
                orderData.shippingAddress.ward +
                ", " +
                orderData.shippingAddress.district +
                ", " +
                orderData.shippingAddress.province
                }}
              </small>
              <br />
              <small>SĐT: {{ orderData.shippingAddress.phone }}</small>
            </div>
          </div>
        </div>
        <div class="line-hr"></div>
        <div class="sender-receiver d-flex">
          <div class="col-8 d-inline">
            <div class="  w-100 p-1">
              <label for="">Nội dung đơn hàng:</label>
              <br />
              <small class="ps-1" v-for="(item, index) in orderData.products" :key="index"><label for="">{{ index + 1
                  }}. </label>
                {{ item.product.name }} - SL: {{ item.qty }} <br></small> 
              <br />
              <label for="">Lưu ý: </label> <small> {{ orderData.note }}</small>
            </div>
          </div>
          <div class="col-4 d-inline">
            <div class="  w-100 p-2">
              <QRCodeVue3 :value="orderData.orderCode" :width="125" :height="125" :qrOptions="{
                  typeNumber: '0',
                  mode: 'Byte',
                  errorCorrectionLevel: 'Q',
                }" :imageOptions="{
                  hideBackgroundDots: true,
                  imageSize: 0.4,
                  margin: 0,
                }" :dotsOptions="{ type: 'square', color: '#000000' }"
                :cornersSquareOptions="{ type: 'square', color: '#0e013c' }" />
              <br />
              <div class="date-take-order w-100 text-left">
                <label for="">Ngày đặt hàng: </label>
                <h5>{{ orderData.createdAt }}</h5>
              </div>
            </div>
          </div>
        </div>
        <div class="line-hr"></div>
        <div class="note-order d-flex">
          <div class="col-12 p-1  ">
            <label for="">Ghi chú: </label>
            <small>Giao giờ hành chính</small>
          </div>
        </div>
        <div class="line-hr"></div>
        <div class="footer d-flex p-1">
          <div class="col-6 d-inline">
            <label for="">Tiền thu Người nhận:</label>
            <br />
            <h2>{{ orderData.subtotal }} VNĐ</h2>
            <br />
          </div>
          <div class="col-6 d-inline">
            <div class="col-12">
              <label for="">Trọng lượng đơn hàng: </label> <small>{{orderData.totalWeight}}</small>
            </div>
            <div class="col-12 text-center">
              <label for="">Chứ kí người nhận</label>
              <br /><br /><br /><br /><br />
            </div>
          </div>
        </div>
        <div class="line-hr"></div>
        <div class="note-order d-flex">
          <div class="col-12 p-1">
            <label for="">Chỉ dẫn giao hàng: </label> <small> {{ orderData.returnCode }}</small>
          </div>
        </div>
      </div>
    </div>
  </div>
  <button class="btn btn-close-popup-bill" v-on:click="displayBill">
    <i class="fa-solid fa-xmark text-white f-1"></i>
  </button>
</template>

<script>
  import GenBarCode from "@/components/common/GenBarCode.vue";
  import QRCodeVue3 from "qrcode-vue3";
  export default {
    props: ["orderData"],
    components: {
      GenBarCode,
      QRCodeVue3,
    },
    methods: {
      displayBill() {
        this.$emit("displayBill", false);
      },
    },
  };
</script>

<style scoped>
  .bill-order-content {
    width: 450px;
    height: 735px;
    background-color: #ffff;
    margin-top: 5px;
    font-size: 14px;
  }

  .bill-order-content {
    float: left;
  }

  .content {
    margin: auto;
    width: 97%;
    border: 2px solid black;
    height: 725px;
    position: relative;
    top: 5px;
  }

  .btn-close-popup-bill {
    float: left;
  }
  .line-hr {
    margin: 0;
    height: 1px;
    border: 0.5px black solid;
    /* background-color: black; */
  }
</style>