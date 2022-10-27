<template>
  <div class="container management-account-shipper">
    <NavbarClient :isShipper="isShipper" />
    <h5>Thông tin tài khoản</h5>
    <small class="text-danger"
      ><i
        >(*) Bạn không có quyền tự thay đổi thông tin tài khoản! Mọi thay đổi và
        thắc mắc vui lòng liên hệ với quản trị viên!</i
      ></small
    >
    <hr />
    <div class="row">
      <div class="col-md-2">
        <img src="" alt="" width="200" height="200" style="border: 50%" />
      </div>
      <div class="col-md-10">
        <div class="form-group">
          <label for="">Mã Shipper</label>
          <p>SHN001001</p>
        </div>
        <div class="form-group">
          <label for="">Họ và tên</label>
          <p>Shipper 1</p>
        </div>
        <div class="form-group">
          <label for="">Email address</label>
          <p>shipper1@gmail.com</p>
        </div>
        <div class="form-group">
          <label for="">Số điện thoại</label>
          <p>09876543211</p>
        </div>
        <div class="form-group">
          <label for="">Địa chỉ thường trú</label>
          <p>Ha Noi, Ha Noi, Viet Nam</p>
        </div>
        <div class="form-group">
          <label for="">Địa chỉ hiện tại/tạm trú</label>
          <p>Ha Noi, Ha Noi, Viet Nam</p>
        </div>
        <div class="form-group">
          <label for="">Thời gian bắt đầu</label>
          <p>20/12/2020</p>
        </div>
        <br />
        <a href="/shipper/management" class="btn btn-outline-danger"
          ><i class="fa-solid fa-angle-left"></i> Quay lại</a
        >
      </div>
    </div>
  </div>
</template>

<script>
import NavbarClient from "./common/NavbarClient.vue";

import { useCookies } from "vue3-cookies";
// import { commonFunction } from '../scripts/ulti'

export default {
  setup() {
    const { cookies } = useCookies();
    return {
      cookies,
    };
  },
  components: {
    NavbarClient,
  },
  data() {
    return {
      isShipper: true,
    };
  },
  mounted() {
    // let authenication_cookies = this.cookies.get("authenication_cookies");
    // if(authenication_cookies == null){
    //   commonFunction.redirect('/');
    // } else {
    //     commonFunction.redirect('/client/management');
    // }
  },
};
</script>

<style scoped>
label {
  font-weight: 600;
}
</style>