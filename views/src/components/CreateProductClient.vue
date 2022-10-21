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
            <div class="col-12">
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
            <div class="col-12">
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
                <div class="col-6">
                  <div class="form-group">
                    <label for="">Ảnh sản phẩm</label>
                    <input type="file" class="form-control" />
                  </div>
                </div>
                <div class="col-6">
                  <img src="#" alt="" width="150" height="150" />
                </div>
              </div>
              <br />
            </div>
            <div class="col-12">
              <div class="form-group">
                <label for="">Mô tả</label>
                <textarea
                  name=""
                  id=""
                  cols="30"
                  rows="10"
                  class="form-control"
                ></textarea>
              </div>
            </div>
            <div class="col-12">
              <div class="form-group">
                <label for="">Ghi chú</label>
                <textarea
                  name=""
                  id=""
                  cols="30"
                  rows="3"
                  class="form-control"
                ></textarea>
              </div>
            </div>
            <br />
            <div class="col-12">
              <div class="row d-flex justify-content-center">
                <div class="col-4">
                  <button class="btn btn-success w-100">Tạo mới</button>
                </div>
              </div>
            </div>
          </div>
          <!-- Modal -->
          
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
    // let authenication_cookies = this.cookies.get("authenication_cookies");
    // if(authenication_cookies == null){
    //   commonFunction.redirect('/');
    // } else {
    //     commonFunction.redirect('/client/management');
    // }
  },
  watch: {},
  method: {},
};
</script>

<style scoped>
</style>