<template>
  <div class="navbar-left-admin">
    <div class="branch-logo d-inline">
      <table class="w-100">
        <tr>
          <td><a href="/admin/management"><img src="@/images/logo-branch.png" alt="Logo branch" width="150" /></a></td>
          <td>
            <button class="btn btn-collapse-nav-left-small" v-on:click="toggleCollapseNavbarLeft">
              <i class="fa-solid fa-caret-left"></i>
            </button>
          </td>
        </tr>
      </table>
    </div>
    <div class="list-item-nav">
      <!-- group 0-->
      <a class="list-group-item list-group-item-action group-title" style="font-size: 13px">
        <i class="fa-solid fa-user"></i>: <span>{{usernameSession}}</span>
        <p class="" style="font-size: 12px; color: #4cd137" disable>
          <i class="fa-solid fa-circle" style="font-size: 6px"></i> Đang hoạt
          động
        </p>
      </a>

      <a href="/admin/management" class="list-group-item list-group-item-action group-title">
        Trang chủ
      </a>

      <!-- group 1-->
      <a class="list-group-item list-group-item-action group-title" v-on:click="isShowItemNav1 = !isShowItemNav1">
        Quản lí Đơn hàng
      </a>
      <div class="list-group" :class="isShowItemNav1 ? 'show' : 'hide'">
        <a href="/admin/management/order/ALL" class="list-group-item list-group-item-action">Tất cả</a>
        <a href="/admin/management/order/DELIVERY" class="list-group-item list-group-item-action">Đơn hàng đang xử lí</a>
        <a href="/admin/management/order/SUCCESS" class="list-group-item list-group-item-action">Đơn đã xử lí</a>
        <a href="/admin/management/order/DELAY" class="list-group-item list-group-item-action">Đơn phát sinh</a>
        <a href="/admin/management/order/CANCEL" class="list-group-item list-group-item-action">Đơn hủy</a>
      </div>

      <!-- group 1-->
      <a class="list-group-item list-group-item-action group-title" v-on:click="isShowItemNav7 = !isShowItemNav7">
        Tạo đơn hàng tại bưu cục
      </a>
      <div class="list-group" :class="isShowItemNav7 ? 'show' : 'hide'">
        <a href="/admin/management/create-order" class="list-group-item list-group-item-action">Tạo mới</a>
      </div>

      <!-- group 2-->
      <a class="list-group-item list-group-item-action group-title" v-on:click="isShowItemNav2 = !isShowItemNav2">
        Khách hàng
      </a>
      <div class="list-group" :class="isShowItemNav2 ? 'show' : 'hide'">
        <a href="/admin/management/customer" class="list-group-item list-group-item-action">Thông tin Khách hàng</a>
      </div>

      <!-- group 5-->
      <a class="list-group-item list-group-item-action group-title" v-on:click="isShowItemNav5 = !isShowItemNav5">
        Đối soát dòng tiền
      </a>
      <div class="list-group" :class="isShowItemNav5 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action">Phiên đối soát</a>
        <a href="#" class="list-group-item list-group-item-action">Lịch sử đối soát</a>
      </div>

      <!-- group 6-->
      <a class="list-group-item list-group-item-action group-title" v-on:click="isShowItemNav6 = !isShowItemNav6">
        Các loại phí và mã KM
      </a>
      <div class="list-group" :class="isShowItemNav6 ? 'show' : 'hide'">
        <a href="/admin/management/fee-code" class="list-group-item list-group-item-action">Tất cả</a>
      </div>

      <!-- group 4-->
      <a class="list-group-item list-group-item-action group-title" v-on:click="isShowItemNav4 = !isShowItemNav4">
        Quản lí bưu cục
      </a>
      <div class="list-group" :class="isShowItemNav4 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action">Danh sách bưu cục/chi nhánh</a>
        <a href="#" class="list-group-item list-group-item-action">Quản lí Chức vụ</a>
        <a href="#" class="list-group-item list-group-item-action">Thông tin Nhân viên</a>
        <a href="#" class="list-group-item list-group-item-action">Thông tin Shipper</a>
      </div>


      <!-- group dx-->
      <a class="
          list-group-item list-group-item-action
          group-title
          bg-dark
          text-white
          item-sign-out
        " style="font-size: 15px" v-on:click="signOut()">
        <i class="fa-solid fa-power-off"></i> Đăng xuất
      </a>
    </div>
  </div>
</template>

<script>
  import { useCookies } from "vue3-cookies";
  import { commonFunction } from '../../scripts/ulti'
  export default {
    setup() {
      const { cookies } = useCookies();
      return { cookies };
    },
    data() {
      return {
        isShowItemNav1: false,
        isShowItemNav2: false,
        isShowItemNav3: false,
        isShowItemNav4: false,
        isShowItemNav5: false,
        isShowItemNav6: false,
        isShowItemNav7: false,
        usernameSession: ""
      };
    },
    mounted() {
      this.usernameSession = this.cookies.get("authenication_cookies");
    },
    methods: {
      toggleCollapseNavbarLeft() {
        this.$emit("toggleCollapseNavbarLeft");
      },
      signOut: function () {
        this.cookies.remove("authenication_cookies");
        this.cookies.remove("accesstoken_cookies");
        this.cookies.remove("authenrole_cookies");
        commonFunction.redirect("/");
      },
    },
  };
</script>

<style scoped>
  .btn-collapse-nav-left-small {
    background-color: #bf1e2d;
    color: #ffffff;
    float: right;
    border-radius: 0 !important;
    height: 50px;
    border-bottom-left-radius: 25px !important;
    border-top-left-radius: 25px !important;
  }

  .navbar-left-admin {
    border-right: 3px solid #bf1e2d;
    height: 100vh;
    position: fixed;
    width: 200px;
  }

  .group-title {
    background-color: #bf1e2d;
    color: #ffffff;
    font-weight: 500;
    cursor: pointer;
    /* border-inline: 1px solid; */
  }

  .list-group-item {
    border-radius: 0px;
    font-size: 13px;
    width: 100.1%;
  }

  .show {
    display: inline;
  }

  .hide {
    display: none;
  }

  .text-rotate {
    width: max-content;
    -moz-transform: translateX(-50%) translateY(-50%) rotate(90deg);
    -webkit-transform: translateX(-50%) translateY(-50%) rotate(90deg);
    transform: translateX(-50%) translateY(-50%) rotate(90deg);
  }

  .item-sign-out {
    padding-top: 15px;
    position: absolute;
    bottom: 0;
  }

  .item-sign-out:hover {
    color: #bf1e2d !important;
  }

  .list-group-item-action:hover {
    padding-left: 23px;
    transition: 0.3s;
  }
</style>