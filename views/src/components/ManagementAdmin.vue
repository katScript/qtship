<template>
  <div class="admin-management">
    <div class="container-fluid d-flex m-0 p-0">
      <button
        class="btn btn-collapse-nav-left"
        :class="isShowNav ? 'hide' : 'show'"
        v-on:click="toggleCollapseNavbarLeft"
      >
        <div class="text-rotate">Thanh công cụ</div>
      </button>
      <div
        class="navbar-left"
        style="width: 250px"
        :class="isShowNav ? 'show' : 'hide'"
      >
        <NavbarLeftAdmin @toggleCollapseNavbarLeft="toggleCollapseNavbarLeft" />
      </div>
      <div class="content-management w-100">
        <br />
        <h3 class="title-managenent-type text-danger">
          <i class="fa-solid fa-sitemap"></i> Quản lí ...
        </h3>
      </div>
    </div>
  </div>
</template>

<script>
import NavbarLeftAdmin from "./common/NavbarLeftAdmin.vue";

export default {
  data() {
    return {
      isShowNav: true,
    };
  },
  components: {
    NavbarLeftAdmin,
  },
  methods: {
    toggleCollapseNavbarLeft() {
      this.isShowNav = !this.isShowNav;
    },
  },
};
</script>

<style scoped>
.show {
  display: block;
}
.hide {
  display: none;
}

.btn-collapse-nav-left {
  background-color: #bf1e2d;
  color: #ffff;
  width: 50px;
  height: 100vh;
  position: absolute;
}
.title-managenent-type {
  margin-left: 60px;
}
.text-rotate {
  width: max-content;
  -moz-transform: translateX(-50%) translateY(-50%) rotate(90deg);
  -webkit-transform: translateX(-50%) translateY(-50%) rotate(90deg);
  transform: translateX(-50%) translateY(-50%) rotate(90deg);
}
</style>