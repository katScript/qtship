<template>
  <div class="navbar-right-admin">
    <div class="branch-logo d-inline">
      <img src="../../images/logo-branch.png" alt="Logo branch" width="150" />
      <button
        class="btn btn-collapse-nav-left text-rotate"
        v-on:click="toggleCollapseNavbarLeft"
      >
        Thu gọn
      </button>
    </div>
    <div class="list-item-nav" width="250">
      <!-- group 0-->
      <a
        class="list-group-item list-group-item-action group-title"
        style="font-size: 15px"
      >
        <i class="fa-solid fa-user"></i> User: <span>duongph</span>
        <p class="" style="font-size: 12px; color: #4cd137" disable>
          <i class="fa-solid fa-circle" style="font-size: 6px"></i> Đang hoạt
          động
        </p>
      </a>

      <!-- group 1-->
      <a
        class="list-group-item list-group-item-action group-title"
        v-on:click="isShowItemNav1 = !isShowItemNav1"
      >
        Quản lí Đơn hàng
      </a>
      <div class="list-group" :class="isShowItemNav1 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action">Tất cả</a>
        <a href="#" class="list-group-item list-group-item-action"
          >Đơn hàng đang xử lí</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Đơn đã xử lí</a
        >
      </div>

      <!-- group 1-->
      <a
        class="list-group-item list-group-item-action group-title"
        v-on:click="isShowItemNav7 = !isShowItemNav7"
      >
        Tạo đơn hàng tại bưu cục
      </a>
      <div class="list-group" :class="isShowItemNav7 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action">Tạo mới</a>
      </div>
      
      <!-- group 2-->
      <a
        class="list-group-item list-group-item-action group-title"
        v-on:click="isShowItemNav2 = !isShowItemNav2"
      >
        Khách hàng
      </a>
      <div class="list-group" :class="isShowItemNav2 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action"
          >Thông tin Khách hàng</a
        >
      </div>

      <!-- group 5-->
      <a
        class="list-group-item list-group-item-action group-title"
        v-on:click="isShowItemNav5 = !isShowItemNav5"
      >
        Đối soát dòng tiền
      </a>
      <div class="list-group" :class="isShowItemNav5 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action"
          >Phiên đối soát</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Lịch sử đối soát</a
        >
      </div>

      <!-- group 6-->
      <a
        class="list-group-item list-group-item-action group-title"
        v-on:click="isShowItemNav6 = !isShowItemNav6"
      >
        Danh sách các loại phí
      </a>
      <div class="list-group" :class="isShowItemNav6 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action">Tất cả</a>
        <a href="#" class="list-group-item list-group-item-action"
          >Tạo mã khuyến mại</a
        >
      </div>

      <!-- group 4-->
      <a
        class="list-group-item list-group-item-action group-title"
        v-on:click="isShowItemNav4 = !isShowItemNav4"
      >
        Quản lí bưu cục
      </a>
      <div class="list-group" :class="isShowItemNav4 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action"
          >Danh sách bưu cục/chi nhánh</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Quản lí Chức vụ</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Thông tin Nhân viên</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Thông tin Shipper</a
        >
      </div>

      <!-- group dx-->
      <a
        class="
          list-group-item list-group-item-action
          group-title
          bg-dark
          text-white
          item-sign-out
        "
        style="font-size: 15px"
        v-on:click="isShowItemNav4 = !isShowItemNav4"
      >
        <i class="fa-solid fa-power-off"></i> Đăng xuất
      </a>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isShowItemNav1: true,
      isShowItemNav2: false,
      isShowItemNav3: false,
      isShowItemNav4: false,
      isShowItemNav5: false,
      isShowItemNav6: false,
      isShowItemNav7: false,
    };
  },
  methods: {
    toggleCollapseNavbarLeft() {
      this.$emit("toggleCollapseNavbarLeft");
    },
  },
};
</script>

<style scoped>
.btn-collapse-nav-left {
  background-color: #bf1e2d;
  color: #ffffff;
  float: right;
  border-left: none;
  position: relative;
  margin-right: -104px;
  margin-top: 60px;
}
.navbar-right-admin {
  border-right: 3px solid #bf1e2d;
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
  font-size: 12px;
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
}

.item-sign-out:hover {
  color: #bf1e2d !important;
}
.list-group-item-action:hover {
  padding-left: 23px;
  transition: 0.3s;
}
</style>