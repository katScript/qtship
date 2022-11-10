<template>
  <div class="bill-order-area">
    <div class="bill-order-content" id="bill-content">
      <div class="content">
        <div class="header d-flex">
          <div class="col-6 d-inline">
            <div class="border-bottom border-dark w-100">
              <img
                src="@/images/logo-branch.png"
                class=""
                alt=""
                height="106"
              />
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
              <QRCodeVue3
                value="OR000001"
                :width="128"
                :height="128"
                :qrOptions="{
                  typeNumber: '0',
                  mode: 'Byte',
                  errorCorrectionLevel: 'Q',
                }"
                :imageOptions="{
                  hideBackgroundDots: true,
                  imageSize: 0.4,
                  margin: 0,
                }"
                :dotsOptions="{ type: 'square', color: '#000000' }"
                :cornersSquareOptions="{ type: 'square', color: '#0e013c' }"
              />
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
            <h1>125.000 VNĐ</h1>
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
            <label for="">Chỉ dẫn giao hàng: </label> <small>Không đồng kiểm. Chuyển hoàn sau 3 lần phát. Lưu kho tối đa 5 ngày</small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="@/components/order/js/print-bill.js"/>

<style scoped>
.bill-order-content {
  width: 500px;
  height: 750px;
  background-color: #ffff;
  margin-top: 5px;
}
.content {
  margin: auto;
  width: 97%;
  border: 1px solid black;
  height: 740px;
  position: relative;
  top: 5px;
}
</style>