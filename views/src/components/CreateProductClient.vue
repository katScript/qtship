<template>
  <div class="management-client-page">
    <NavbarClient />
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2">
          <NotficationClient />
        </div>
        <div class="col-md-8">
          <div class="create-product-management">
            <div class="col-12">
              <h4>Tạo Sản phẩm mới</h4>
            </div>
            <div class="row">
              <div class="col-sm-8">
                <div class="form-group">
                  <label for="">Tên sản phẩm</label>
                  <input
                    type="text"
                    class="form-control"
                    id=""
                    placeholder="Nhập tên sản phẩm"
                  />
                </div>
                <br />
              </div>
              <div class="col-sm-4">
                <div class="form-group">
                  <label for="">Danh mục sản phẩm</label>
                  <input
                    type="text"
                    class="form-control"
                    id=""
                    placeholder="Danh mục sản phẩm: Đồ tiêu dùng, Dụng cụ nhà bếp, Đồ chơi trẻ em ..."
                  />
                </div>
                <br />
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
                          <input
                            type="text"
                            class="form-control"
                            id=""
                            placeholder="Giá gốc sản phẩm"
                          />
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
                          <input
                            type="text"
                            class="form-control"
                            id=""
                            placeholder="Giá bán sản phẩm"
                          />
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
                          <input
                            type="text"
                            class="form-control"
                            id=""
                            placeholder="Khối lượng sản phẩm"
                          />
                        </td>
                        <td>(kg)</td>
                      </tr>
                    </table>
                  </div>
                </div>
              </div>
              <br />
            </div>
            <div class="col-12">
              <div class="row">
                <div class="col-9">
                  <div class="form-group">
                    <label for="">Ảnh sản phẩm</label>
                    <input type="file" class="form-control" />
                  </div>
                  <div class="col-12">
                    <div class="form-group">
                      <label for="">Ghi chú</label>
                      <textarea
                        name=""
                        id=""
                        cols="30"
                        rows="2"
                        class="form-control"
                      ></textarea>
                    </div>
                  </div>
                  <div class="col-12">
                    <div class="form-group">
                      <label for="">Mô tả</label>
                      <textarea
                        name=""
                        id=""
                        cols="30"
                        rows="5"
                        class="form-control"
                      ></textarea>
                    </div>
                  </div>
                </div>
                <div class="col-3">
                  <img src="#" alt="" width="300" height="300" />
                </div>
              </div>
              <br />
            </div>
            <br />
            <div class="col-12">
              <div class="row d-flex justify-content-center">
                <div class="col-4">
                  <button class="btn btn-success w-100">Tạo mới</button>
                </div>
              </div>
            </div>
            <br />
          </div>
          <div class="list-product">
            <div class="row">
              <h4>Danh sách sản phẩm</h4>
              <br />
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Mã SP</th>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Danh mục</th>
                    <th scope="col">Giá gốc</th>
                    <th scope="col">Giá bán</th>
                    <th scope="col">Khối lượng</th>
                    <th scope="col">Ghi chú</th>
                    <th scope="col">Mô tả</th>
                    <th scope="col">Chức năng</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">1</th>
                    <td><img src="" alt="" width="80" height="80" /> Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                    <td>@mdo</td>
                    <td>Otto</td>
                    <td>
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

export default {
  components: {
    NavbarClient,
    FooterClient,
    ToolbarRight,
    NotficationClient,
  },
  data() {
    return {};
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
      if (authenication_cookies == null) {
        commonFunction.redirect('/');
      }
  },
  watch: {},
  method: {},
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