<template>
  <div class="order-detail" :class="isDisplayBill ? 'show' : 'hide'">
    <!-- detail order -->
    <div class="order-detail-area m-auto p-2 hide">
      <div class="title text-center">
        <h4>Chi tiết đơn hàng OR000001</h4>
        <label for="">Ngày tạo: </label> <small>2022-11-09 00:00:00</small>
        <hr>
      </div>
      <div class="content-oder-detail">
        <h6>Thông tin người gửi: </h6>
        <div class="ps-3">
          <small><label for="">Người gửi: </label> Duong Pham</small>
          <br>
          <small><label for="">Địa chỉ: </label> Ha Noi, Ha Noi, Viet Nam</small>
          <br>
          <small><label for="">Số điện thoại: </label> 0987654321</small>
          <br>
        </div>
        <br>

        <h6>Thông tin người nhận: </h6>
        <div class="ps-3">
          <small><label for="">Người nhận: </label> Tú Quang</small>
          <br>
          <small><label for="">Địa chỉ: </label> Ha Noi, Ha Noi, Viet Nam</small>
          <br>
          <small><label for="">Số điện thoại: </label> 0123456789</small>
          <br>
        </div>
        <br>


        <h6>Thông tin đơn hàng: </h6>
        <div class="ps-3">
          <label for="">Sản phẩm: </label>
          <div class="products ps-2">
            <small><label for="">1. </label> Giày vans - SL: 1</small>
            <br>
            <small><label for="">2. </label> Áo Hoodie - SL: 1</small>
            <br>
          </div>

          <small><label for="">Ghi chú: </label> Giao giờ hành chính</small>
          <br>
          <small><label for="">Mã giảm giá đã áp dụng: </label> MGG001-MPVC</small>
          <br>
          <small><label for="">Phí giao hàng: </label> MGG001-MPVC</small>
          <br>
          <hr>
          <p><label for="">Tổng giá trị đơn hàng: </label> 120.000 VNĐ</p>
          <br>
        </div>
        <br>

      </div>
    </div>



    <!-- bill -->
    <div class="bill-order-area">
      <div class="bill-order-content" id="bill-content">
        <div class="content">
          <div class="header d-flex">
            <div class="col-6 d-inline">
              <div class="border-bottom border-dark w-100">
                <img src="@/images/logo-branch.png" class="" alt="" height="106" />
              </div>
            </div>
            <div class="col-6 d-inline text-center">
              <div class="border-bottom border-dark w-100">
                <GenBarCode />
                Mã Đơn hàng: OR000001
              </div>
            </div>
          </div>
          <div class="sender-receiver d-flex">
            <div class="col-6 d-inline">
              <div class="border-bottom border-dark w-100 p-1">
                <label for="">Từ:</label> <small>Kho Giày Mỹ Đình</small>
                <br />
                <small>Địa chỉ: Mỹ Đình, Mỹ Đình, Hà Nội</small>
                <br />
                <small>SĐT: 0987654321</small>
                <br />
                <br />
                <br />
              </div>
            </div>
            <div class="col-6 d-inline">
              <div class="border-bottom border-dark w-100 p-1">
                <label for="">Đến: Tu NQ</label>
                <br />
                <small>Địa chỉ: Bắc Ninh, Bắc Ninh, Hà Nội</small>
                <br />
                <small>SĐT: 0123456789</small>
                <br />
                <br />
                <br />
              </div>
            </div>
          </div>
          <div class="sender-receiver d-flex">
            <div class="col-8 d-inline">
              <div class="border-bottom border-dark w-100 p-1">
                <label for="">Nội dung hàng:</label>
                <br />
                <small>1. Giày Vans Basic</small> <small>SL: 1</small>
                <br />
                <small>1. Áo Hoodie</small> <small>SL: 1</small>
                <br /><br /><br /><br /><br /><br /><br />
                <label for="">Lưu ý: </label> <small>Đơn hàng không được hoàn trả</small>
              </div>
            </div>
            <div class="col-4 d-inline">
              <div class="border-bottom border-dark w-100 p-2">
                <QRCodeVue3 value="OR000001" :width="128" :height="128" :qrOptions="{
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
                  <h5>2022/11/10 00:00:00</h5>
                </div>
              </div>
            </div>
          </div>
          <div class="note-order d-flex">
            <div class="col-12 p-1 border-bottom border-dark">
              <label for="">Ghi chú: </label> <small>Giao giờ hành chính</small>
            </div>
          </div>
          <div class="footer d-flex p-1">
            <div class="col-6 d-inline">
              <label for="">Tiền thu Người nhận:</label>
              <br />
              <h2>125.000 VNĐ</h2>
              <br />
            </div>
            <div class="col-6 d-inline">
              <div class="col-12">
                <label for="">Trọng lượng tối đa: </label> <small>500g</small>
              </div>
              <div class="col-12 text-center">
                <label for="">Chứ kí người nhận</label>
                <br><br><br><br><br>
              </div>
            </div>
          </div>
          <div class="note-order d-flex">
            <div class="col-12 p-1 border-top border-dark">
              <label for="">Chỉ dẫn giao hàng: </label> <small>Không đồng kiểm. Chuyển hoàn sau 3 lần phát. Lưu kho tối
                đa
                5 ngày</small>
            </div>
          </div>
        </div>
      </div>
    </div>
    <button class="btn btn-close-popup-bill" v-on:click="closePopUpBillOrder(false)"><i
        class="fa-solid fa-xmark text-white f-1"></i></button>
  </div>
</template>

<script src="@/components/order/js/print-bill.js" />

<style scoped>
  .order-detail {
    width: 100vw;
    height: 100vh;
    position: fixed;
    background-color: rgba(0, 0, 0, 0.356);
    z-index: 100;
  }

  .order-detail-area,
  .bill-order-content {
    width: 500px;
    height: 750px;
    background-color: #ffff;
    margin-top: 5px;
  }

  .bill-order-content {
    float: left;
  }

  .content {
    margin: auto;
    width: 97%;
    border: 1px solid black;
    height: 740px;
    position: relative;
    top: 5px;
  }

  .btn-close-popup-bill {
    float: left;
  }

  .show {
    display: block;
  }

  .hide {
    display: none;
  }
</style>