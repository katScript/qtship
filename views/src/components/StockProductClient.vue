<template>
  <div class="management-client-page">
    <NavbarClient />
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2">
          <NotficationClient />
        </div>
        <div class="col-md-8">
          <div class="stock-product-management">
            <div class="form-filter">
              <div class="row">
                <div class="col-md-9">
                  <h4 class="title-management">
                    <i class="fa-solid fa-clipboard-list"></i> Quản lí kho và
                    sản phẩm
                  </h4>
                </div>
                <div class="col-md-3 d-flex justify-content-end">
                  <button class="btn btn-outline-success">
                    Tạo Sản phẩm mới
                  </button>
                </div>
              </div>
              <br />
              <div class="row">
                <div class="col-md-6">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Nhập Tên sản phẩm hoặc Mã sản phẩm"
                  />
                </div>
                <div class="col-md-2">
                  <select
                    class="form-select"
                    aria-label="Default select example"
                  >
                    <option selected>- Lọc thứ tự -</option>
                    <option value="1">Bán chạy nhất</option>
                    <option value="2">Tên: A -> Z</option>
                    <option value="2">Tên: Z -> A</option>
                    <option value="2">Giá thấp đến cao</option>
                    <option value="3">Gia cao đến thấp</option>
                  </select>
                </div>
                <div class="col-md-2">
                  <select
                    class="form-select"
                    aria-label="Default select example"
                  >
                    <option selected>- Kho -</option>
                    <option value="1">Kho 1</option>
                    <option value="2">Kho 2</option>
                  </select>
                </div>
                <div class="col-md-2">
                  <button class="btn btn-success w-100">
                    <i class="fa-solid fa-magnifying-glass"></i> Tìm kiếm
                  </button>
                </div>
              </div>
            </div>
            <br />
            <div class="stock-product-management-area">
              <div class="row">
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th scope="col">Mã SP</th>
                      <th scope="col" class="w-50">Tên sản phẩm</th>
                      <th scope="col">Đã xuất</th>
                      <th scope="col">Tồn kho</th>
                      <th scope="col">Doanh thu</th>
                      <th scope="col">Chức năng</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th scope="row">1</th>
                      <td>Áo hodiee classic</td>
                      <td>12</td>
                      <td>24</td>
                      <td>10000</td>
                      <td>
                        <a
                          href=""
                          class="btn btn-primary a-function a-detail"
                          data-bs-toggle="tooltip"
                          data-bs-placement="top"
                          title="Chi tiết"
                          ><i class="fa-solid fa-circle-info"></i
                        ></a>
                        <a
                          href=""
                          class="btn btn-success a-function a-detail"
                          data-bs-toggle="tooltip"
                          data-bs-placement="top"
                          title="Cập nhật"
                          ><i class="fa-solid fa-file-pen"></i
                        ></a>
                        <a
                          href=""
                          class="btn btn-danger a-function a-detail"
                          data-bs-toggle="tooltip"
                          data-bs-placement="top"
                          title="Xóa"
                          ><i class="fa-solid fa-trash"></i
                        ></a>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-2">
          <ToolbarRight />
        </div>
      </div>
    </div>

    <br />
    <br /><br />
    <FooterClient />
  </div>
</template>

<script>
import NavbarClient from "./common/NavbarClient.vue";
import FooterClient from "./common/FooterClient.vue";
import ToolbarRight from "./common/ToolbarRight.vue";
import NotficationClient from "./common/NotficationClient.vue";

import { useCookies } from "vue3-cookies";
// import { commonFunction } from '../scripts/ulti'
import { debounce } from "vue-debounce";

export default {
  components: {
    NavbarClient,
    FooterClient,
    ToolbarRight,
    NotficationClient,
  },
  data() {
    return {
      orderCode: "",
      receiverPhonenumber: "",
      filterTime: "label",
      filterTimeDS: "label",
      classFilterTimeAbout: "d-none",
      classFilterTimeAboutDS: "d-none",
      countOrder: 3,
    };
  },

  setup() {
    const { cookies } = useCookies();
    return {
      cookies,
    };
  },

  // <data, methods...>

  mounted() {
    // let authenication_cookies = this.cookies.get("authenication_cookies");
    // if(authenication_cookies == null){
    //   commonFunction.redirect('/');
    // } else {
    //     commonFunction.redirect('/client/management');
    // }
  },
  watch: {
    filterTime: {
      handler: debounce(function () {
        return this.filterTime == "timeCOTimeAbout"
          ? (this.classFilterTimeAbout = "d-contents")
          : (this.classFilterTimeAbout = "d-none");
      }, 500),
    },
    filterTimeDS: {
      handler: function () {
        return this.filterTimeDS == "controlTimeAboutDS"
          ? (this.classFilterTimeAboutDS = "d-contents")
          : (this.classFilterTimeAboutDS = "d-none");
      },
    },
  },
  method: {
    resetFormSearch: debounce(function () {
      this.filterTime = "label";
      this.filterTimeDS = "label";
      this.classFilterTimeAbout = "d-none";
      this.classFilterTimeAboutDS = "d-none";
    }, 1000),
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
}
table tr td {
}

table tr td a {
  /* font-size: 5px; */
  padding: 2px;
  margin: 0px 3px;
}

table tr td a i {
  font-size: 18px;
  padding: 2px 2px 2px 2px;
}
</style>