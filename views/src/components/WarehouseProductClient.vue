<template>
  <div class="management-client-page">
    <NavbarClient />
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2">
          <NotficationClient />
        </div>
        <div class="col-md-8">
          <div class="warehouse-product-management">
            <div class="form-filter">
              <div class="row">
                <div class="col-md-9">
                  <h4 class="title-management">
                    <i class="fa-solid fa-clipboard-list"></i> Quản lí kho và
                    sản phẩm
                  </h4>
                </div>
                <div class="col-md-3 d-flex justify-content-end">
                  <a href="/client/warehouse/create-product" class="btn btn-outline-success">
                    Tạo Sản phẩm mới
                  </a>
                </div>
              </div>
              <br />
              <div class="row">
                <h5>Kho</h5>
                <div class="col-md-6">
                  <div class="form-group">
                    <label for="">Tên kho:</label>
                    <input type="text" class="form-control" v-model="formDataWarehouse.name"
                      placeholder="Tên kho/Tên cửa hàng" required />
                  </div>
                  <div class="form-group">
                    <label for="">Địa chỉ:</label>
                    <input type="text" class="form-control" v-model="formDataWarehouse.address"
                      placeholder="Địa chỉ cụ thể" required />
                  </div>
                  <div class="form-group">
                    <label for="">Contact:</label>
                    <input type="text" class="form-control" v-model="formDataWarehouse.phone"
                      placeholder="Số điện thoại,..." required />
                  </div>
                  <br />
                  <button class="btn btn-success" v-on:click="createNewWarehouse">Tạo kho mới</button>
                </div>
                <div class="col-md-6">
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Tên kho</th>
                        <th scope="col">Địa chỉ</th>
                        <th scope="col">Contact</th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(warehouse,index) in listwarehouseByCustomer" :key="index">
                        <td>{{warehouse.name}}</td>
                        <td>{{warehouse.address}}</td>
                        <td>{{warehouse.phone}}</td>
                        <td>
                          <a href="" class="btn btn-success" data-bs-toggle="tooltip" data-bs-placement="top"
                            title="Cập nhật"><i class="fa-solid fa-file-pen"></i></a>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <hr />
              <div class="row">
                <h5>Sản phẩm</h5>
                <div class="col-md-6">
                  <input type="text" class="form-control" placeholder="Nhập Tên sản phẩm hoặc Mã sản phẩm" />
                </div>
                <div class="col-md-2">
                  <select class="form-select" aria-label="Default select example">
                    <option selected>- Lọc thứ tự -</option>
                    <option value="1">Bán chạy nhất</option>
                    <option value="2">Tên: A -> Z</option>
                    <option value="2">Tên: Z -> A</option>
                    <option value="2">Giá thấp đến cao</option>
                    <option value="3">Gia cao đến thấp</option>
                  </select>
                </div>
                <div class="col-md-2">
                  <select class="form-select" aria-label="Default select example">
                    <option v-for="(warehouse,index) in listwarehouseByCustomer" :key="index" :value="warehouse.id">
                      {{warehouse.name}}</option>
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
            <div class="warehouse-product-management-area">
              <div class="row">
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th scope="col">Mã SP</th>
                      <th scope="col" class="w-50">Tên sản phẩm</th>
                      <th scope="col">Giá gốc</th>
                      <th scope="col">Giá bán</th>
                      <th scope="col">Khối lượng</th>
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
                        <a href="" class="btn btn-primary a-function a-detail" data-bs-toggle="tooltip"
                          data-bs-placement="top" title="Chi tiết"><i class="fa-solid fa-circle-info"></i></a>
                        <a href="" class="btn btn-success a-function a-detail" data-bs-toggle="tooltip"
                          data-bs-placement="top" title="Cập nhật"><i class="fa-solid fa-file-pen"></i></a>
                        <a href="" class="btn btn-danger a-function a-detail" data-bs-toggle="tooltip"
                          data-bs-placement="top" title="Xóa"><i class="fa-solid fa-trash"></i></a>
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
  import { commonFunction } from '../scripts/ulti'
  import { debounce } from "vue-debounce";
  import axios from "axios";

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
        listwarehouseByCustomer: [],
        idRequest: 0,
        formDataWarehouse: {
          name: '',
          address: '',
          phone: ''
        },
        configRequestApi: {},
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
      let authenication_cookies = this.cookies.get("authenication_cookies");
      this.idRequest = localStorage.getItem("id_customer_request");
      let accesstoken_cookies = this.cookies.get("accesstoken_cookies");
      if (authenication_cookies == null) {
        commonFunction.redirect('/');
      }

      this.configRequestApi = {
        headers: { Authorization: 'Bearer ' + accesstoken_cookies }
      };
      axios
        .get(commonFunction.DOMAIN_URL + "v1/warehouse/all/customer/" + this.idRequest, this.configRequestApi)
        .then((response) => {
          let respronseData = response.data;
          respronseData.forEach(element => {
            this.listwarehouseByCustomer.push(element);
          });
        }).catch((e) => { console.log(e) })
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
    methods: {
      createNewWarehouse: function () {
        axios
          .post(commonFunction.DOMAIN_URL + "v1/warehouse/save/" + this.idRequest,
            {
              customerId: this.idRequest,
              name: this.formDataWarehouse.name,
              address: this.formDataWarehouse.address,
              phone: this.formDataWarehouse.phone
            }, this.configRequestApi)
          .then((response) => {
            if (response.status == 200) {
              alert("Đăng kí thành công - "+ response.data.message)
            } else {
              alert("Fail! Đăng kí không thành công! Vui lòng thử lại!");
            }
          }).catch((e) => { 
            alert('ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!')
            console.log(e) ;
          })
      },
    }
  };
</script>

<style scoped>
  a {
    text-decoration: none;
  }

  table tr td {}

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