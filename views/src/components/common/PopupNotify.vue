<template>
  <div class="pop-up-notify">
    <table class="h-100">
      <tr>
        <td>
          <div class="content-notify p1">
            <div class="row">
              <div class="col-12 d-flex justify-content-end">
                <button class="btn" v-on:click="closePopupNotify">
                  <i class="fa-solid fa-rectangle-xmark"></i>
                </button>
              </div>
            </div>
            <div class="row">
              <!-- <div class="col-12 text-center" v-if="typeNotify == 'SUCCESS'">
                                <br>
                                <img src="../../images/success-svg.svg" width="80" alt="Action successfully">
                                <br><br>
                                <h4>Thành công!</h4>
                                <hr>
                                <button class="btn btn-success" v-on:click="reloadPage()">Hoàn thành</button>
                            </div>

                            <div class="col-12 text-center" v-else-if="typeNotify == 'FAIL'">
                                <br>
                                <img src="../../images/fail-svg.svg" width="80" alt="Action fail!">
                                <br><br>
                                <h4>Đã có lỗi xảy ra!</h4>
                                <hr>
                                <button class="btn btn-danger" v-on:click="reloadPage()">Chấp nhận</button>
                            </div> -->

              <div class="col-12 text-center" v-if="typeNotify == 'DELETE'">
                <img
                  src="../../images/warning-svg.svg"
                  class="text-danger"
                  width="80"
                  alt="Warning Action"
                />
                <br /><br />
                <h5>Bạn có chắc chắn muốn xóa {{ dataNotify.name }} ?</h5>
                <hr />
                <button
                  class="btn btn-warning"
                  v-on:click="confirmDelete(typeComponent, dataNotify)"
                >
                  Xác nhận xóa
                </button>
              </div>
              <div
                class="col-12 text-center"
                v-else-if="typeNotify == 'CANCEL'"
              >
                <br />
                <img
                  src="../../images/warning-svg.svg"
                  class="text-danger"
                  width="80"
                  alt="Warning Action"
                />
                <br /><br />
                <h5>Bạn có chắc chắn muốn hủy {{ dataNotify.name }} ?</h5>
                <hr />
                <button
                  class="btn btn-warning"
                  v-on:click="confirmDelete(typeComponent, dataNotify)"
                >
                  Xác nhận hủy
                </button>
              </div>
            </div>
          </div>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from "axios";
import { commonFunction } from "../../scripts/ulti";

export default {
  props: ["typeNotify", "dataNotify", "typeComponent", "configRequestApi"],
  data() {
    return {};
  },
  methods: {
    closePopupNotify() {
      this.$emit("closePopupNotify");
    },
    confirmDelete(typeComponent, dataNotify) {
      if (typeComponent == "WAREHOUSE") {
        axios
          .delete(
            commonFunction.DOMAIN_URL + "v1/warehouse/delete",
            {
              headers: {
                Authorization: this.configRequestApi.headers.Authorization,
              },
              data: {
                customerId: dataNotify.idRequest,
                id: dataNotify.data.data.id,
              },
            },
            this.configRequestApi
          )
          .then((response) => {
            if (response.status == 200) {
              alert(`"SUCCESS: Xóa ${dataNotify.data.data.name} thành công!"`);
              commonFunction.reloadPage();
            } else {
              alert(
                `"FAIL: Xóa ${dataNotify.data.data.name} không thành công!"`
              );
              commonFunction.reloadPage();
            }
          })
          .catch((e) => {
            alert(
              "ERROR: Có lỗi xảy ra! Vui lòng thử lại hoặc liên hệ với quản trị viên!"
            );
            console.log(e);
            commonFunction.reloadPage();
          });
      } else if (typeComponent == "PRODUCT") {
        axios
          .delete(commonFunction.DOMAIN_URL + "v1/product/delete", {
            headers: {
              Authorization: this.configRequestApi.headers.Authorization,
            },
            data: {
              customerId: dataNotify.idRequest,
              id: dataNotify.data.data.id,
            },
          })
          .then((response) => {
            if (response.status == 200) {
              alert(`"SUCCESS: Xóa ${dataNotify.data.data.name} thành công!"`);
              commonFunction.reloadPage();
            } else {
              alert(`"FAIL: Xóa ${dataNotify.data.data.name} không thành công!"`);
              commonFunction.reloadPage();
            }
          })
          .catch((e) => {
            alert(
              "ERROR: Có lỗi xảy ra! Vui lòng thử lại hoặc liên hệ với quản trị viên!"
            );
            console.log(e);
            commonFunction.reloadPage();
          });
      } else if (typeComponent == "ORDER") {
        axios
          .post(commonFunction.DOMAIN_URL + "v1/order/update/status", {
              id: dataNotify.data.id,
              status: commonFunction.orderStatus.Cancel
            },
            this.configRequestApi
          )
          .then((response) => {
            if (response.status == 200) {
              alert(`"SUCCESS: Hủy ${dataNotify.name} thành công!"`);
              commonFunction.reloadPage();
            } else {
              alert(`"FAIL: Hủy ${dataNotify.name} không thành công!"`);
              commonFunction.reloadPage();
            }
          })
          .catch((e) => {
            alert(
              "ERROR: Có lỗi xảy ra! Vui lòng thử lại hoặc liên hệ với quản trị viên!"
            );
            console.log(e);
            commonFunction.reloadPage();
          });
      }
    },
    reloadPage() {
      commonFunction.reloadPage();
    },
  },
};
</script>