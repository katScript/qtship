<template>
  <div class="management-client-page">
    <div>
      <PopupNotify :class="isShowNotify ? 'show' : 'hide'" @closePopupNotify="closePopupNotify"
        @updateTypeNotify="updateTypeNotify" :typeNotify="typeNotify" :dataNotify="dataNotify"
        :typeComponent="typeComponent" :configRequestApi="configRequestApi" />
    </div>
    <NavbarClient />
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2">
          <NotficationClient />
        </div>
        <div class="col-md-8">
          <div class="warehouse-product-management">
            <div class="form-filter">
              <br>
              <div class="row">
                <div class="col-12">
                  <h4 class="title-management">
                    <i class="fa-solid fa-clipboard-list"></i> Quản lí kho và
                    sản phẩm
                  </h4>
                </div>
              </div>
              <br />
              <div class="row">
                <h4>Kho</h4>
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
                    <label for="">Thông tin liên hệ:</label>
                    <input type="text" class="form-control" v-model="formDataWarehouse.phone"
                      placeholder="Số điện thoại,..." required />
                  </div>
                  <br />
                  <div class="d-flex justify-content-center">
                    <button class="btn btn-success btn-function-crud m-auto" v-on:click="createNewWarehouse"
                      v-if="!isUpdateWarehouseAction">Tạo mới</button>
                    <button class="btn btn-success btn-function-crud m-auto" v-on:click="updateWarehouse"
                      v-if="isUpdateWarehouseAction">Cập nhật</button>
                    <button class="btn btn-dark btn-function-crud m-auto" v-on:click="cancelUpdateWarehouse"
                      v-if="isUpdateWarehouseAction">Hủy
                      bỏ</button>
                  </div>
                </div>
                <div class="col-md-6">
                  <easy-data-table :headers="headersWarehouse" :items="listWarehouseByCustomer"
                    table-class-name="easy-data-table-customize">
                    <template #item-btn-function="item">
                      <!-- #item-btn-function="item"  item: valua of row-->
                      <table class="w-100">
                        <tr>
                          <td><a v-on:click="selectWareHouseForUpdate(item)" class="btn btn-success a-function a-detail"
                              data-bs-toggle="tooltip" data-bs-placement="top" title="Cập nhật"><i
                                class="fa-solid fa-file-pen"></i></a>
                          </td>
                          <td> <a v-on:click="deleteWareHouse(item)" class="btn btn-danger a-function a-detail"
                              data-bs-toggle="tooltip" data-bs-placement="top" title="Xóa"><i
                                class="fa-solid fa-trash"></i></a></td>
                        </tr>
                      </table>
                    </template>
                  </easy-data-table>
                </div>
              </div>
              <hr />
              <div class="create-product-management">
                <div class="col-12">
                  <h4>Tạo Sản phẩm mới</h4>
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <div class="form-group">
                      <label for="">Tên sản phẩm</label>
                      <input type="text" class="form-control" id="" v-model="formDataProduct.name"
                        placeholder="Nhập tên sản phẩm" />
                    </div>
                  </div>
                  <!-- <div class="col-sm-4">
                    <div class="form-group">
                      <label for="">Danh mục sản phẩm</label>
                      <input type="text" class="form-control" id=""
                        placeholder="Danh mục sản phẩm: Đồ tiêu dùng, Dụng cụ nhà bếp, Đồ chơi trẻ em ..." />
                    </div>
                  </div> -->
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label for="">SKU</label>
                      <input type="text" class="form-control" id="" v-model="formDataProduct.sku"
                        placeholder="Mã SKU Sản phẩm" />
                    </div>
                  </div>
                </div>
                <div class="col-12">
                  <div class="row">
                    <div class="col-sm-5">
                      <div class="form-group">
                        <label for="">Giá gốc</label>
                        <table class="w-100">
                          <tr>
                            <td>
                              <input type="text" class="form-control" id="" v-model="formDataProduct.basePrice"
                                placeholder="Giá gốc sản phẩm" />
                            </td>
                            <td>(VNĐ)</td>
                          </tr>
                        </table>
                      </div>
                    </div>
                    <div class="col-sm-5">
                      <div class="form-group">
                        <label for="">Giá bán</label>
                        <table class="w-100">
                          <tr>
                            <td>
                              <input type="text" class="form-control" id="" v-model="formDataProduct.publicPrice"
                                placeholder="Giá bán sản phẩm" />
                            </td>
                            <td>(VNĐ)</td>
                          </tr>
                        </table>
                      </div>
                    </div>
                    <div class="col-sm-2">
                      <div class="form-group">
                        <label for="">Khối lượng</label>
                        <table class="w-100">
                          <tr>
                            <td>
                              <input type="text" class="form-control" id="" v-model="formDataProduct.weight"
                                placeholder="Khối lượng sản phẩm" />
                            </td>
                            <td>(kg)</td>
                          </tr>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-12">
                  <div class="row">
                    <div class="col-md-9">
                      <div class="col-12">
                        <div class="row">
                          <div class="col-4">
                            <div class="form-group">
                              <label for="">Số lượng</label>
                              <input type="text" class="form-control" v-model="formDataProduct.qty"
                                placeholder="Số lượng" />
                            </div>
                          </div>
                          <div class="col-8">
                            <div class="form-group">
                              <label for="">Ảnh sản phẩm</label>
                              <input type="file" class="form-control" />
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-12">
                        <div class="form-group">
                          <label for="">Mô tả</label>
                          <textarea name="" id="" v-model="formDataProduct.description" cols="30" rows="5"
                            class="form-control"></textarea>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-3 d-flex justify-content-end">
                      <img src="#" alt="" width="200" height="200" class="m-auto" />
                    </div>
                  </div>
                  <br />
                </div>
                <div class="d-flex justify-content-center">
                  <button class="btn btn-success btn-function-crud m-auto w-25" v-if="!isUpdateProductAction" v-on:click="createNewProduct">Tạo
                    mới</button>
                  <button class="btn btn-success btn-function-crud m-auto w-25" v-if="isUpdateProductAction" v-on:click="updateProduct">Cập
                    nhật</button>
                  <button class="btn btn-dark btn-function-crud m-auto w-25" v-if="isUpdateProductAction"
                    v-on:click="cancelUpdateProduct">Hủy bỏ</button>
                </div>
                <br />
              </div>
              <hr />
              <div class="row d-flex justify-content-end">
                <h4>Danh sách sản phẩm</h4>
                <div class="col-md-4 ">
                  <input type="text" class="form-control" placeholder="Nhập Tên sản phẩm hoặc Mã sản phẩm" />
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
                <easy-data-table :headers="headersProduct" :items="listProductByCustomer"
                  table-class-name="easy-data-table-customize">
                  <template #item-btn-function="item">
                    <!-- #item-btn-function="item"  item: valua of row-->
                    <table class="w-100">
                      <tr>
                        <td><a v-on:click="selectProductForUpdate(item)" class="btn btn-success a-function a-detail"
                            data-bs-toggle="tooltip" data-bs-placement="top" title="Cập nhật"><i
                              class="fa-solid fa-file-pen"></i></a>
                        </td>
                        <td> <a v-on:click="deleteProduct(item)" class="btn btn-danger a-function a-detail"
                            data-bs-toggle="tooltip" data-bs-placement="top" title="Xóa"><i
                              class="fa-solid fa-trash"></i></a></td>
                      </tr>
                    </table>
                  </template>
                </easy-data-table>
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
  import PopupNotify from "./common/PopupNotify.vue";


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
      PopupNotify,
    },
    data() {
      return {
        filterTime: "label",
        filterTimeDS: "label",
        classFilterTimeAbout: "d-none",
        classFilterTimeAboutDS: "d-none",
        headersWarehouse: [
          { text: "Tên kho", value: "name", sortable: true },
          { text: "Địa chỉ", value: "address" },
          { text: "TT liên hệ", value: "phone" },
          { text: "Chức năng", value: "btn-function", }
        ],
        headersProduct: [
          { text: "SKU", value: "sku", sortable: true },
          { text: "Tên Sản phẩm", value: "name", sortable: true },
          { text: "Số lượng", value: "qty", sortable: true },
          { text: "Trọng lượng(kg)", value: "weight", sortable: true },
          { text: "Giá gốc (VNĐ)", value: "basePrice", sortable: true },
          { text: "Giá bán (VNĐ)", value: "publicPrice", sortable: true },
          { text: "Mô tả", value: "description" },
          { text: "Chức năng", value: "btn-function", }
        ],
        listWarehouseByCustomer: [],
        listProductByCustomer: [],
        idRequest: 0,
        formDataProduct: {
          id: "",
          sku: "",
          qty: 0,
          name: "",
          weight: 0.0,
          basePrice: 0,
          publicPrice: 0,
          description: ""
        },
        formDataWarehouse: {
          id: '',
          name: '',
          address: '',
          phone: ''
        },
        configRequestApi: {},
        isUpdateWarehouseAction: false,
        isUpdateProductAction: false,
        isShowNotify: false,
        typeNotify: '',
        typeComponent: '',
        dataNotify: {
          name: '',
          idRequest: 0,
          data: {}
        }
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

      //warehouse
      axios
        .get(commonFunction.DOMAIN_URL + "v1/warehouse/all/customer/" + this.idRequest, this.configRequestApi)
        .then((response) => {
          let respronseData = response.data;
          this.listWarehouseByCustomer = respronseData;
        }).catch((e) => { console.log(e) })


      //product
      axios
        .get(commonFunction.DOMAIN_URL + "v1/product/customer/" + this.idRequest, this.configRequestApi)
        .then((response) => {
          let respronseData = response.data;
          this.listProductByCustomer = respronseData;
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
      //warehouse
      createNewWarehouse: function () {
        axios
          .post(commonFunction.DOMAIN_URL + "v1/warehouse/save",
            {
              customerId: this.idRequest,
              name: this.formDataWarehouse.name,
              address: this.formDataWarehouse.address,
              phone: this.formDataWarehouse.phone
            }, this.configRequestApi)
          .then((response) => {
            if (response.status == 200) {
              alert("SUCCESS: Tạo mới thành công - " + response.data.message)
              commonFunction.reloadPage();
            } else {
              alert("FAIL: Đăng kí không thành công! Vui lòng thử lại!");
            }
          }).catch((e) => {
            alert('ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!')
            console.log(e);
          })
      },
      updateWarehouse: function () {
        axios
          .post(commonFunction.DOMAIN_URL + "v1/warehouse/save",
            {
              id: this.formDataWarehouse.id,
              customerId: this.idRequest,
              name: this.formDataWarehouse.name,
              address: this.formDataWarehouse.address,
              phone: this.formDataWarehouse.phone
            }, this.configRequestApi)
          .then((response) => {
            if (response.status == 200) {
              alert("SUCCESS: Cập nhật thành công - " + response.data.message)
              commonFunction.reloadPage();
            } else {
              alert("FAIL: Cập nhật không thành công! Vui lòng thử lại!");
            }
          }).catch((e) => {
            alert('ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!')
            console.log(e);
          })
      },
      selectWareHouseForUpdate: function (item) {
        this.formDataWarehouse = {
          id: item.id,
          name: item.name,
          address: item.address,
          phone: item.phone
        }
        this.isUpdateWarehouseAction = true;
      },
      cancelUpdateWarehouse: function () {
        this.formDataWarehouse = {
          id: '',
          name: '',
          address: '',
          phone: ''
        }
        this.isUpdateWarehouseAction = false;
      },
      deleteWareHouse: function (item) {
        this.typeNotify = commonFunction.typeNotifyDelete;
        this.typeComponent = 'WAREHOUSE';
        this.dataNotify = {
          name: item.name,
          idRequest: this.idRequest,
          data: {
            id: item.id
          }
        }
        this.isShowNotify = true;
      },
      updateTypeNotify: (type) => this.typeNotify = type,
      closePopupNotify: function () {
        this.isShowNotify = false;
        this.typeNotify = '';
        this.typeComponent = '';
        this.dataNotify = {
          name: '',
          idRequest: 0,
          data: {}
        }
      },

      //product
      createNewProduct: function () {
        axios
          .post(commonFunction.DOMAIN_URL + "v1/product/save",
            {
              customerId: this.idRequest,
              sku: this.formDataProduct.sku,
              qty: this.formDataProduct.qty,
              name: this.formDataProduct.name,
              weight: this.formDataProduct.weight,
              basePrice: this.formDataProduct.basePrice,
              publicPrice: this.formDataProduct.publicPrice,
              description: this.formDataProduct.description
            }, this.configRequestApi)
          .then((response) => {
            if (response.status == 200) {
              alert("SUCCESS: Tạo mới thành công - " + response.data.message)
              commonFunction.reloadPage();
            } else {
              alert("FAIL: Đăng kí không thành công! Vui lòng thử lại!");
            }
          }).catch((e) => {
            alert('ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!')
            console.log(e);
          })
      },
      updateProduct: function () {
        axios
          .post(commonFunction.DOMAIN_URL + "v1/product/save",
            {
              id: this.formDataProduct.id,
              customerId: this.idRequest,
              sku: this.formDataProduct.sku,
              qty: this.formDataProduct.qty,
              name: this.formDataProduct.name,
              weight: this.formDataProduct.weight,
              basePrice: this.formDataProduct.basePrice,
              publicPrice: this.formDataProduct.publicPrice,
              description: this.formDataProduct.description
            }, this.configRequestApi)
          .then((response) => {
            if (response.status == 200) {
              alert("SUCCESS: Cập nhật thành công - " + response.data.message)
              commonFunction.reloadPage();
            } else {
              alert("FAIL: Cập nhật không thành công! Vui lòng thử lại!");
            }
          }).catch((e) => {
            alert('ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!')
            console.log(e);
          })
      },
      selectProductForUpdate: function (item) {
        this.formDataProduct = {
          id: item.id,
          sku: item.sku,
          qty: item.qty,
          name: item.name,
          weight: item.weight,
          basePrice: item.basePrice,
          publicPrice: item.publicPrice,
          description: item.description
        }
        this.isUpdateProductAction = true;
      },
      cancelUpdateProduct: function () {
        this.formDataProduct = {
          sku: "",
          qty: 0,
          name: "",
          weight: 0.0,
          basePrice: 0,
          publicPrice: 0,
          description: ""
        }
        this.isUpdateProductAction = false;
      },
      deleteProduct: function (item) {
        this.typeNotify = commonFunction.typeNotifyDelete;
        this.typeComponent = 'PRODUCT';
        this.dataNotify = {
          name: item.name,
          idRequest: this.idRequest,
          data: {
            id: item.id
          }
        }
        this.isShowNotify = true;
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

  .easy-data-table-customize {
    --easy-table-header-font-size: 14px;
    --easy-table-body-row-font-size: 14px;
  }

  table tr td a i {
    font-size: 16px;
    padding: 2px 2px 2px 2px;
  }

  .btn-function-crud {
    margin-right: 5px;
    margin-bottom: 5px;
  }

  .show {
    display: block;
  }

  .hide {
    display: none;
  }
</style>