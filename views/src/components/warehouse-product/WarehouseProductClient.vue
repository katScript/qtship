<template>
  <div class="management-client-page">
    <div :class="isLoading ? 'show' : 'hide'"><ActionLoading /></div>
    <div>
      <PopupNotify
        :class="isShowNotify ? 'show' : 'hide'"
        @closePopupNotify="closePopupNotify"
        @updateTypeNotify="updateTypeNotify"
        :typeNotify="typeNotify"
        :dataNotify="dataNotify"
        :typeComponent="typeComponent"
        :configRequestApi="configRequestApi"
      />
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
              <br />
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
                    <small class="text-danger">{{
                      msgValidationFor.warehouse.name
                    }}</small>
                    <input
                      type="text"
                      class="form-control"
                      v-model="warehouseData.name"
                      placeholder="Tên kho/Tên cửa hàng"
                      required
                    />
                  </div>
                  <div class="form-group">
                    <label for="">Địa chỉ:</label>
                    <small class="text-danger">{{
                      msgValidationFor.warehouse.address
                    }}</small>
                    <input
                      type="text"
                      class="form-control"
                      v-model="warehouseData.address"
                      placeholder="Địa chỉ cụ thể"
                      required
                    />
                  </div>
                  <div class="form-group">
                    <label for="">Thông tin liên hệ:</label>
                    <small class="text-danger">{{
                      msgValidationFor.warehouse.phone
                    }}</small>
                    <input
                      type="text"
                      class="form-control"
                      v-model="warehouseData.phone"
                      placeholder="Số điện thoại,..."
                      required
                    />
                  </div>
                  <br />
                  <div class="d-flex justify-content-center">
                    <button
                      class="btn btn-success btn-function-crud m-auto"
                      v-on:click="createNewWarehouse"
                      v-if="!isUpdateWarehouseAction"
                    >
                      Tạo mới
                    </button>
                    <button
                      class="btn btn-success btn-function-crud m-auto"
                      v-on:click="updateWarehouse"
                      v-if="isUpdateWarehouseAction"
                    >
                      Cập nhật
                    </button>
                    <button
                      class="btn btn-dark btn-function-crud m-auto"
                      v-on:click="cancelUpdateWarehouse"
                      v-if="isUpdateWarehouseAction"
                    >
                      Hủy bỏ
                    </button>
                  </div>
                </div>
                <div class="col-md-6">
                  <easy-data-table
                    :headers="headersWarehouse"
                    :items="listWarehouseByCustomer"
                    table-class-name="easy-data-table-customize"
                  >
                    <template #item-btn-function="item">
                      <!-- #item-btn-function="item"  item: valua of row-->
                      <table class="w-100">
                        <tr>
                          <td>
                            <a
                              v-on:click="selectWareHouseForUpdate(item)"
                              class="btn btn-success a-function a-detail"
                              data-bs-toggle="tooltip"
                              data-bs-placement="top"
                              title="Cập nhật"
                              ><i class="fa-solid fa-file-pen"></i
                            ></a>
                          </td>
                          <td>
                            <a
                              v-on:click="deleteWareHouse(item)"
                              class="btn btn-danger a-function a-detail"
                              data-bs-toggle="tooltip"
                              data-bs-placement="top"
                              title="Xóa"
                              ><i class="fa-solid fa-trash"></i
                            ></a>
                          </td>
                        </tr>
                      </table>
                    </template>
                  </easy-data-table>
                </div>
              </div>
              <hr />

              <!-- Product -->
              <div class="create-product-management">
                <div class="col-12">
                  <h4>Tạo Sản phẩm mới</h4>
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <div class="form-group">
                      <label for="">Tên sản phẩm</label>
                      <small class="text-danger">
                        {{ msgValidationFor.product.name }}</small
                      >
                      <input
                        type="text"
                        class="form-control"
                        id=""
                        v-model="productData.name"
                        placeholder="Nhập tên sản phẩm"
                      />
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
                      <small class="text-danger">
                        {{ msgValidationFor.product.sku }}</small
                      >
                      <input
                        type="text"
                        class="form-control"
                        id=""
                        v-model="productData.sku"
                        placeholder="Mã SKU Sản phẩm"
                      />
                    </div>
                  </div>
                </div>
                <div class="col-12">
                  <div class="row">
                    <div class="col-sm-5">
                      <div class="form-group">
                        <label for="">Giá gốc (VNĐ)</label>
                        <small class="text-danger">
                          {{ msgValidationFor.product.basePrice }}</small
                        >
                        <input
                          type="text"
                          class="form-control"
                          id=""
                          v-model="productData.basePrice"
                          placeholder="Giá gốc sản phẩm"
                        />
                      </div>
                    </div>
                    <div class="col-sm-5">
                      <div class="form-group">
                        <label for="">Giá bán (VNĐ)</label>
                        <small class="text-danger">
                          {{ msgValidationFor.product.publicPrice }}</small
                        >
                        <input
                          type="text"
                          class="form-control"
                          id=""
                          v-model="productData.publicPrice"
                          placeholder="Giá bán sản phẩm"
                        />
                      </div>
                    </div>
                    <div class="col-sm-2">
                      <div class="form-group">
                        <label for="">Khối lượng (kg) </label>
                        <small class="text-danger">
                          {{ msgValidationFor.product.weight }}</small
                        >
                        <input
                          type="text"
                          class="form-control"
                          id=""
                          v-model="productData.weight"
                          placeholder="Khối lượng sản phẩm"
                        />
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
                              <small class="text-danger">
                                {{ msgValidationFor.product.qty }}</small
                              >
                              <input
                                type="text"
                                class="form-control"
                                v-model="productData.qty"
                                placeholder="Số lượng"
                              />
                            </div>
                          </div>
                          <div class="col-8">
                            <div class="form-group">
                              <label for="">Ảnh sản phẩm</label>
                              <small class="text-danger">
                                {{ msgValidationFor.product.image }}</small
                              >
                              <small
                                class="text-success"
                                v-if="productData.image"
                              >
                                Ảnh sản phẩm đã được tải lên trước đó!</small
                              >
                              <input
                                type="file"
                                class="form-control"
                                ref="productImgUpload"
                                v-on:click="loadingImgUpload = true"
                                v-on:change="handleFileUpload()"
                              />
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-12">
                        <div class="form-group">
                          <label for="">Mô tả</label>
                          <textarea
                            name=""
                            id=""
                            v-model="productData.description"
                            cols="30"
                            rows="5"
                            class="form-control"
                          ></textarea>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-3 d-flex justify-content-end">
                      <div
                        class="spinner-border m-auto"
                        role="status"
                        v-if="loadingImgUpload"
                      >
                        <span class="sr-only">Loading...</span>
                      </div>
                      <div class="img-product-upl m-auto" v-else>
                        <img
                          alt=""
                          width="200"
                          height="200"
                          class=""
                          v-if="!urlImgProductUpload && !productData.image"
                          src="@/images/img-default.jpg"
                        />
                        <img
                          alt=""
                          width="200"
                          height="200"
                          class=""
                          v-else-if="urlImgProductUpload"
                          :src="urlImgProductUpload"
                        />
                        <img
                          alt=""
                          width="200"
                          height="200"
                          class=""
                          v-else-if="productData.image"
                          :src="productData.image"
                        />
                      </div>
                    </div>
                  </div>
                  <br />
                </div>
                <div class="d-flex justify-content-center">
                  <button
                    class="btn btn-success btn-function-crud m-auto w-25"
                    v-if="!isUpdateProductAction"
                    v-on:click="createNewProduct"
                  >
                    Tạo mới
                  </button>
                  <button
                    class="btn btn-success btn-function-crud m-auto w-25"
                    v-if="isUpdateProductAction"
                    v-on:click="updateProduct"
                  >
                    Cập nhật
                  </button>
                  <button
                    class="btn btn-dark btn-function-crud m-auto w-25"
                    v-if="isUpdateProductAction"
                    v-on:click="cancelUpdateProduct"
                  >
                    Hủy bỏ
                  </button>
                </div>
                <br />
              </div>
              <hr />
              <div class="row d-flex justify-content-end">
                <h4>Danh sách sản phẩm</h4>
                <div class="col-md-4">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Nhập Tên sản phẩm hoặc Mã sản phẩm"
                  />
                </div>
                <div class="col-md-2">
                  <button class="btn btn-success w-100">Tìm kiếm</button>
                </div>
              </div>
            </div>
            <br />
            <div class="warehouse-product-management-area">
              <div class="row">
                <easy-data-table
                  :headers="headersProduct"
                  :items="listProductByCustomer"
                  table-class-name="easy-data-table-customize"
                >
                  <template #loading>
                    <img
                      src="@/images/loading-gif.gif"
                      style="width: 80px; height: 80px"
                    />
                  </template>
                  <template #item-createdAt="item">
                    {{ formatDateYYYYMMDD(item.createdAt) }}
                  </template>
                  <template #item-image="item">
                    <img
                      :src="item.data.image"
                      style="width: 80px; height: 80px"
                    />
                  </template>
                  <template #item-btn-function="item">
                    <!-- #item-btn-function="item"  item: valua of row-->
                    <table class="w-100">
                      <tr>
                        <td>
                          <a
                            v-on:click="selectProductForUpdate(item)"
                            class="btn btn-success a-function a-detail"
                            data-bs-toggle="tooltip"
                            data-bs-placement="top"
                            title="Cập nhật"
                            ><i class="fa-solid fa-file-pen"></i
                          ></a>
                        </td>
                        <td>
                          <a
                            v-on:click="deleteProduct(item)"
                            class="btn btn-danger a-function a-detail"
                            data-bs-toggle="tooltip"
                            data-bs-placement="top"
                            title="Xóa"
                            ><i class="fa-solid fa-trash"></i
                          ></a>
                        </td>
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

<script src="@/components/warehouse-product/js/warehouse-product.js"/>

<style scoped>
a {
  text-decoration: none;
}

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
