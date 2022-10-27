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
      <!-- group 1-->
      <a
        class="list-group-item list-group-item-action group-title"
        v-on:click="isShowItemNav1 = !isShowItemNav1"
      >
        Quản lí Đơn hàng
      </a>
      <div class="list-group" :class="isShowItemNav1 ? 'show' : 'hide'">
        <a href="#" class="list-group-item list-group-item-action"
          >Đơn hàng thành công</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Đơn hàng đang giao</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Quản lí Đơn hàng delay/Hoàn</a
        >
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
          >Dapibus ac facilisis in</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Morbi leo risus</a
        >
        <a href="#" class="list-group-item list-group-item-action"
          >Porta ac consectetur ac</a
        >
        <!-- group 2-->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isShowItemNav1: false,
      isShowItemNav2: false,
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
</style>