<template>
  <div class="admin-management">
    <div class="container-fluid d-flex m-0 p-0">
      <button class="btn btn-collapse-nav-left" :class="isShowNav ? 'hide' : 'show'"
        v-on:click="toggleCollapseNavbarLeft">
        <div class="text-rotate">Thanh công cụ</div>
      </button>
      <div class="navbar-left" style="width: 250px" :class="isShowNav ? 'show' : 'hide'">
        <NavbarLeftAdmin @toggleCollapseNavbarLeft="toggleCollapseNavbarLeft" />
      </div>
      <div class="content-management w-100">
        <br />
        <BoxDataOverview />
        <hr />
        <div class="row">
          <div class="col-md-4">
            <h5>Hiệu suất giao hàng</h5>
            <DeliveryPerformanceChart />
          </div>
          <div class="col-md-4">
            <OrderTypeAdminChart />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import NavbarLeftAdmin from "./common/NavbarLeftAdmin.vue";
  import BoxDataOverview from "./common/BoxDataOverview.vue";
  import DeliveryPerformanceChart from "./common/DeliveryPerformanceChart.vue";
  import OrderTypeAdminChart from "./common/OrderTypeAdminChart.vue";

  import { useCookies } from "vue3-cookies";
  import { commonFunction } from '../scripts/ulti'

  export default {
    setup() {
      const { cookies } = useCookies();
      return {
        cookies,
      };
    },
    data() {
      return {
        isShowNav: true,
      };
    },
    components: {
      NavbarLeftAdmin,
      BoxDataOverview,
      DeliveryPerformanceChart,
      OrderTypeAdminChart,
    },
    mounted() {
      let authenication_cookies = this.cookies.get(commonFunction.userCookies.username);
      if (authenication_cookies == null) {
        commonFunction.redirect('/');
      }
    },
    methods: {
      toggleCollapseNavbarLeft() {
        this.isShowNav = !this.isShowNav;
      },
    },
  };
</script>

<style scoped>
  * {
    overflow: hidden;
  }

  .show {
    display: block;
  }

  .hide {
    display: none;
  }

  .content-management {
    margin-left: 40px;
  }

  .btn-collapse-nav-left {
    background-color: #bf1e2d;
    color: #ffff;
    width: 50px;
    height: 100vh;
    position: absolute;
    margin-right: 10px;
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