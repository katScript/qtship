<template>
  <div class="admin-management">
    <div class="container-fluid d-flex m-0 p-0">
      <NavbarLeftAdmin />
      <div class="content-management w-100">
        <br />
        <BoxDataOverview />
        <hr />
        <div class="row">
          <div class="col-md-12">
            <h5>Tổng thu nhập theo thời gian</h5>
            <VueAreaCashCharts />
          </div>
        </div>
        <br><br><br>
        <div class="row">
          <div class="col-md-4">
            <h5>Hiệu suất giao hàng</h5>
            <VueDeliveryPerformanceChart />
            <br>
            <span>Số lượng đơn giao thành công: <b>1000</b></span>
            <br>
            <span>Số lượng đơn giao không thành công: <b>200</b></span>
          </div>
          <div class="col-md-4">
            <h5>Tổng số đơn hàng tháng 11/2022</h5>
            <VueOrderTypeAdminChart />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="@/components/admin/js/admin-management" />

<style scoped>
  *{
    overflow-x: hidden;
    overflow-y: hidden;
  }
</style>