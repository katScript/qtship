<template>
  <div class="container management-account-shipper">
    <NavbarClient :isShipper="isShipper"/>
    <h5>Thông tin tài khoản</h5>
    <small class="text-danger"><i>(*) Bạn không có quyền tự thay đổi thông tin tài khoản! Mọi thay đổi và
      thắc mắc vui lòng liên hệ với quản trị viên!</i></small>
    <hr/>
    <div class="row">
      <div class="col-md-2">
        <img src="" alt="" width="200" height="200" style="border: 50%"/>
      </div>
      <div class="col-md-10">
        <div class="form-group">
          <label for="">Mã Shipper</label>
          <p>{{ shipperData.shipperCode }}</p>
        </div>
        <div class="form-group">
          <label for="">Họ và tên</label>
          <p>{{ shipperData.fullName }}</p>
        </div>
        <div class="form-group">
          <label for="">Email address</label>
          <p>{{ shipperData.email }}</p>
        </div>
        <div class="form-group">
          <label for="">Số điện thoại</label>
          <p>{{ shipperData.phone }}</p>
        </div>
        <div class="form-group">
          <label for="">Địa chỉ thường trú</label>
          <p>{{ shipperData.address }}</p>
        </div>
        <div class="form-group">
          <label for="">Địa chỉ hiện tại/tạm trú</label>
          <p>{{ shipperData.currentAddress }}</p>
        </div>
        <div class="form-group">
          <label for="">Thời gian bắt đầu</label>
          <p>{{ shipperData.createdAt }}</p>
        </div>
        <br/>
        <a href="/shipper/management" class="btn btn-outline-danger"><i class="fa-solid fa-angle-left"></i> Quay lại</a>
      </div>
    </div>
  </div>
</template>

<style src="./css/shipper-detail.css"/>
<script src="@/components/shipper/js/shipper-detail"/>