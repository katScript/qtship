<template>
  <div class="management-client-page">
    <NavbarClient :isShipper="isShipper" />
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="row">
            <div class="col-md-12">
              <h6 class="text-danger">
                <i class="fa-solid fa-briefcase"></i> Phiên làm việc:
                <span class="text-dark">{{ currentDate }}</span>
              </h6>
            </div>
          </div>
        </div>
        <hr />
        <div class="col-12">
          <div class="row">
            <div class="col-6 text-center">
              <h3>Tổng số đơn hàng:</h3>
              <h1>100</h1>
            </div>
            <div class="col-6">
              <ul class="list-group">
                <li class="list-group-item border-0 border-bottom">
                  Số đơn đã lấy: <span>12</span>
                </li>
                <li class="list-group-item border-0 border-bottom">
                  Số đơn đang giao: <span>12</span>
                </li>
                <li class="list-group-item border-0 border-bottom">
                  Số đơn đã giao/thanh toán: <span>12</span>
                </li>
                <li class="list-group-item border-0 border-bottom">
                  Số đơn hoàn trả: <span>12</span>
                </li>
                <li class="list-group-item border-0 border-bottom">
                  Số đơn delay: <span>12</span>
                </li>
              </ul>
            </div>
          </div>
          <hr />
          <div class="row">
            <h5>Tổng giá trị đơn </h5>
            <div class="col-sm-6">
              <div class="row">
                <div class="col-12">
                  <b>Đã bàn giao:</b>
                  <br />
                  <small>(*) Tổng giá trị các đơn hàng đã nhận bàn giao</small>
                </div>
                <div class="col-12">
                  <h3>10.500.000 <span style="font-size: 15px">(VNĐ)</span></h3>
                </div>
              </div>
            </div>
            <div class="col-sm-6">
              <div class="row">
                <div class="col-12">
                  <b>Đơn đã xử lí:</b>
                  <br />
                  <small>(*) Tổng giá trị các đơn hàng đã giao và thu COD</small>
                </div>
                <div class="col-12">
                  <h3>6.500.000 <span style="font-size: 15px">(VNĐ)</span></h3>
                </div>
              </div>
            </div>
          </div>
          <hr />
        </div>
      </div>
      <div class="row">
        <h4>Danh sách đơn hàng</h4>
        <ul class="nav nav-tabs">
          <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#home">Chờ bàn giao</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#contact">Đã bàn giao</a>
          </li>
        </ul>

        <div class="">
          <div class="tab-content">
            <div role="tabpanel" class="tab-pane fade in active show" id="home">
              <ListOrderTransferShipper />
            </div>
            <div role="tabpanel" class="tab-pane fade" id="contact">
              <ListOrderShipper />
            </div>
          </div>
        </div>
      </div>
    </div>

    <br />
    <br /><br />
    <FooterClient />
  </div>
</template>

<style src="./css/shipper-order.css"></style>
<script src="@/components/shipper/js/shipper-order.js"></script>