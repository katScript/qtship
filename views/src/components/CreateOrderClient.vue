<template>
  <div class="management-client-page">
    <NavbarClient />
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2">
          <NotficationClient />
        </div>
        <div class="col-md-8">
          <div class="create-order-area">
            <div class="row">
              <div class="col-12">
                <h4 class="title-management">
                  <i class="fa-solid fa-circle-plus"></i> Tạo mới đơn hàng
                </h4>
                <br />
              </div>
              <div class="col-md-6 info-reciver-senter">
                <h5>Thông tin người nhận</h5>
                <div class="row">
                  <div class="col-1"><i class="fa-solid fa-phone"></i></div>
                  <div class="form-group col-11">
                    <input
                      type="text"
                      class="form-control"
                      id=""
                      placeholder="Số điện thoại người nhận"
                      v-model="formDataOrder.orderItem[0].shippingAddress.phone"
                    />
                    <br />
                  </div>
                </div>
                <div class="row">
                  <div class="col-1"><i class="fa-solid fa-user"></i></div>
                  <div class="form-group col-11">
                    <input
                      type="text"
                      class="form-control"
                      id=""
                      placeholder="Tên người nhận"
                      v-model="
                        formDataOrder.orderItem[0].shippingAddress.receiverName
                      "
                    />
                    <br />
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-1">
                    <i class="fa-solid fa-location-dot"></i>
                  </div>
                  <div class="col-11">
                    <LocationPicker
                      @updateCustomerAddress="updateCustomerAddress"
                      :msgValidationFor="msgValidationFor"
                    />
                  </div>
                </div>
                <hr />
                <h5>Thông tin người gửi</h5>
                <div class="row">
                  <div class="col-1"><i class="fa-solid fa-phone"></i></div>
                  <div class="form-group col-11">
                    <input
                      type="text"
                      class="form-control"
                      id=""
                      placeholder="Số điện thoại người gửi"
                      v-model="formDataOrder.senderPhone"
                    />
                    <br />
                  </div>
                </div>
                <div class="row">
                  <div class="col-1"><i class="fa-solid fa-user"></i></div>
                  <div class="form-group col-11">
                    <input
                      type="text"
                      class="form-control"
                      id=""
                      placeholder="Thông tin người gửi"
                      v-model="formDataOrder.senderName"
                    />
                    <br />
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-1">
                    <i class="fa-solid fa-location-dot"></i>
                  </div>
                  <div class="col-11">
                    <input
                      type="text"
                      class="form-control"
                      id=""
                      placeholder="Địa chỉ người gửi"
                      v-model="formDataOrder.senderAddress"
                    />
                    <br />
                  </div>
                </div>
                <hr />
                <h5>Phương thức giao & nhận hàng</h5>
                <div class="form-group row">
                  <div class="col-1">
                    <i class="fa-solid fa-truck-fast"></i>
                  </div>
                  <div class="col-11">
                    <select
                      class="form-select"
                      aria-label="Default select example"
                      v-model="formDataOrder.shippingType"
                    >
                      <option
                        v-for="(type, index) in listTypeShipping"
                        :key="index"
                      >
                        {{ generateCodeToText(type) }}
                      </option>
                    </select>
                    <br />
                  </div>
                </div>
                <div class="form-group row">
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="flexRadioDefault"
                      id="flexRadioDefault1"
                      v-on:click="isSelectedTypeLH = 'GHTBC'"
                    />
                    <label class="form-check-label" for="flexRadioDefault1">
                      Gửi hàng tại bưu cục
                    </label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="flexRadioDefault"
                      id="flexRadioDefault2"
                      v-on:click="isSelectedTypeLH = 'LHTN'"
                    />
                    <label class="form-check-label" for="flexRadioDefault2">
                      Lấy hàng tận nơi
                    </label>
                  </div>
                </div>
                <div :class="isSelectedTypeLH == 'LHTN' ? 'show' : 'hide'">
                  <div class="form-group row">
                    <div class="col-4">
                      <label for="" class="label-control"
                        >Địa điểm lấy hàng</label
                      >
                    </div>
                    <div class="col-8">
                      <select
                        class="form-select"
                        aria-label="Default select example"
                        v-model="formDataOrder.warehouseId"
                      >
                        <option
                          :value="w.id"
                          v-for="(w, index) in listWarehouseByCustomer"
                          :key="index"
                        >
                          {{ w.name }}
                        </option>
                      </select>
                      <br />
                    </div>
                  </div>
                  <div class="form-group row">
                    <div class="col-4">
                      <label for="" class="label-control"
                        >Thời gian lấy hàng/bưu cục nhận hàng</label
                      >
                    </div>
                    <div class="col-8">
                      <div class="row">
                        <div class="col-8">
                          <input
                            type="date"
                            id="datetimepicker-take-order"
                            class="form-control"
                            name=""
                            v-model="shippingDateFullDate"
                          />
                        </div>
                        <div class="col-4">
                          <input
                            type="time"
                            id="datetimepicker-take-order"
                            class="form-control"
                            name=""
                            v-model="shippingDateHHMM"
                          />
                        </div>
                      </div>
                      <br />
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6 info-order-detail">
                <div class="row">
                  <div class="col-6">
                    <h5>Thông tin sản phẩm</h5>
                  </div>
                  <div class="col-6">
                    <a
                      href="/client/stock/create-product"
                      class="btn btn-outline-success"
                      style="float: right"
                      >Danh sách sản phẩm</a
                    >
                  </div>
                </div>
                <br />
                <div
                  class="list-product-in-order"
                  v-for="(ps, index) in productSelected"
                  :key="index"
                >
                  <div class="row">
                    <div class="col-11">
                      <div class="row">
                        <div class="col-2">
                          <img
                            src=""
                            alt=""
                            class="mb-1"
                            width="80"
                            height="80"
                          />
                        </div>
                        <div class="col-10">
                          <div class="row">
                            <div class="col-10">
                              <select
                                class="form-select"
                                aria-label="Default select example"
                                v-model="productSelected[index]"
                              >
                                <!-- index 0 is default -->
                                <option
                                  :value="p"
                                  v-for="(p, index) in listProductByCustomer"
                                  :key="index"
                                >
                                  {{ p.name }}
                                </option>
                              </select>
                            </div>
                            <div class="col-2">
                              <input
                                type="number"
                                class="form-control mb-1"
                                v-model="
                                  formDataOrder.orderItem[0].products[index].qty
                                "
                                placeholder="SL"
                              />
                            </div>
                            <div class="col-6">
                              <input
                                type="text"
                                class="form-control product-select-weight"
                                v-model="productSelected[index].weight"
                                placeholder="Trọng lượng (kg)"
                                disabled
                                readonly
                              />
                            </div>
                            <div class="col-6">
                              <input
                                type="text"
                                class="form-control product-select-public-price"
                                v-model="productSelected[index].publicPrice"
                                placeholder="Giá bán (VNĐ)"
                                disabled
                                readonly
                              />
                            </div>
                            <input
                              type="hidden"
                              class="product-selected-id"
                              v-model="productSelected[index].id"
                              disabled
                              readonly
                            />
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="col-1">
                      <button
                        class="btn btn-outline-success"
                        v-on:click="addItemProductList(index)"
                      >
                        <i class="fa-solid fa-plus plus-order-number"></i>
                      </button>
                      <button
                        class="btn btn-outline-danger"
                        :class="productSelected.length > 1 ? 'show' : 'hide'"
                        v-on:click="
                          removeItemProductList(productSelected[index])
                        "
                      >
                        <i class="fa-solid fa-minus minus-order-number"></i>
                      </button>
                    </div>
                  </div>
                  <hr />
                </div>

                <div class="row">
                  <div class="col-sm-8">
                    <div class="row">
                      <div class="col-sm-4">
                        Phí ship: <span class="feeShip">0 đ</span>
                        <br />
                      </div>
                      <div class="col-sm-8">
                        <select
                          class="form-select"
                          aria-label="Default select example"
                          v-model="formDataOrder.shippingFee"
                        >
                          <option value="true">Shop trả ship</option>
                          <option value="false">Người nhận trả ship</option>
                        </select>
                      </div>
                      <div class="col-12">
                        <div class="form-group">
                          <label for="">Ghi chú đơn hàng</label>
                          <textarea
                            type=""
                            class="form-control"
                            id=""
                            aria-describedby="emailHelp"
                            v-model="formDataOrder.note"
                            placeholder="Nhập ghi chú của đơn hàng"
                          />
                          <br />
                        </div>
                      </div>
                    </div>
                    <br />
                  </div>
                  <div class="col-sm-4">
                    <img src="" alt="" width="150" height="150" />
                  </div>
                  <div class="col-12">
                    <div class="row">
                      <div class="col-8">
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Mã giảm giá"
                          v-model="formDataOrder.coupon"
                        />
                      </div>
                      <div class="col-4">
                        <button
                          class="btn btn-danger w-100"
                          v-on:click="
                            isDisplayListVourcer = !isDisplayListVourcer
                          "
                        >
                          {{
                            !isDisplayListVourcer ? "Thêm mã giảm giá" : "Đóng"
                          }}
                        </button>
                      </div>
                    </div>

                    <div
                      class="row"
                      :class="isDisplayListVourcer ? 'show' : 'hide'"
                    >
                      <div class="col-12">
                        <div class="list-group">
                          <a
                            href="#"
                            class="list-group-item list-group-item-action"
                          >
                            <div class="row">
                              <div class="col-1 mt-1">
                                <i
                                  class="fa-solid fa-ticket text-danger"
                                  style="font-size: 24px"
                                ></i>
                              </div>
                              <div class="col-3 mt-1">MPVC001-VC</div>
                              <div class="col-5 mt-1">
                                Miễn phí phí vận chuyển
                              </div>
                              <div class="col-3">
                                <button
                                  class="btn btn-outline-danger w-100"
                                  v-on:click="applySaleCode('MPVC001-VC')"
                                >
                                  Áp dụng
                                </button>
                              </div>
                            </div>
                          </a>
                          <a
                            href="#"
                            class="list-group-item list-group-item-action"
                          >
                            <div class="row">
                              <div class="col-1 mt-1">
                                <i
                                  class="fa-solid fa-ticket text-danger"
                                  style="font-size: 24px"
                                ></i>
                              </div>
                              <div class="col-3 mt-1">MPVC002-VC</div>
                              <div class="col-5 mt-1">
                                Miễn phí phí vận chuyển
                              </div>
                              <div class="col-3">
                                <button
                                  class="btn btn-outline-danger w-100"
                                  v-on:click="applySaleCode('MPVC002-VC')"
                                >
                                  Áp dụng
                                </button>
                              </div>
                            </div>
                          </a>
                        </div>
                      </div>
                    </div>
                    <br />
                  </div>
                  <div class="col-sm-4">
                    <label for="" class="label-control"
                      ><span style="color: red">(*)</span> TH Hoàn hàng:
                    </label>
                    <br />
                  </div>
                  <div class="col-sm-8">
                    <select
                      class="form-select"
                      aria-label="Default select example"
                      v-model="this.formDataOrder.return"
                    >
                      <option
                        :value="item"
                        v-for="(item, index) in listTypeReturnOrder"
                        :key="index"
                      >
                        {{ generateCodeToText(item) }}
                      </option>
                    </select>
                    <br />
                  </div>
                </div>
              </div>
            </div>
            <hr />
            <div class="row">
              <div class="col-md-6"></div>
              <div class="col-md-6">
                <input
                  v-model="isAccepted"
                  type="checkbox"
                  class="form-check-input"
                  id="radio-accept-contract"
                />
                <label
                  class="form-check-label"
                  for="exampleCheck1"
                  style="padding-left: 10px"
                >
                  Tôi đã đọc và đồng ý với Chính sách bảo mật thông tin</label
                >
                <br />
                <button
                  class="btn btn-success w-100"
                  style="font-size: 18px"
                  v-on:click="createOrder"
                >
                  <i class="fa-solid fa-up-right-from-square"></i> Đăng đơn hàng
                </button>
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
import LocationPicker from "./common/LocationPicker.vue";

import { commonFunction } from "../scripts/ulti";
import { useCookies } from "vue3-cookies";
import axios from "axios";
// import $ from "jquery"

export default {
  components: {
    NavbarClient,
    FooterClient,
    ToolbarRight,
    NotficationClient,
    LocationPicker,
  },
  data() {
    return {
      isAccepted: false,
      isDisplayListVourcer: false,
      idRequest: "",
      numberProduct: 3,
      productSelected: [
        { sku: "default", weight: "", publicPrice: "", qty: "" },
      ],
      listProducts: [],
      customerAddress: {
        province: "",
        provinceId: "",
        district: "",
        districtId: "",
        ward: "",
        wardId: "",
        street: "",
      },
      msgValidationFor: {
        customerAddress: {
          provinceId: "",
          districtId: "",
          wardId: "",
          street: "",
        },
      },
      isSelectedTypeLH: "",
      configRequestApi: {},
      listWarehouseByCustomer: [],
      listProductByCustomer: [],
      shippingDateFullDate: "",
      shippingDateHHMM: "",
      formDataOrder: {
        id: "",
        customerId: "",
        senderName: "",
        senderPhone: "",
        senderAddress: "",
        note: "",
        feedback: "",
        status: "",
        notification: "",
        shippingFee: true,
        coupon: "",
        warehouseId: "",
        shippingType: "",
        shippingTime: "",
        return: "",
        orderItem: [
          {
            shippingAddress: {
              receiverName: "",
              phone: "",
              province: "",
              district: "",
              ward: "",
              street: "",
              provinceId: "",
              districtId: "",
              wardId: "",
            },
            products: [{ productId: "", qty: "" }],
          },
        ],
      },
      listTypeReturnOrder: {},
      listTypeShipping: {},
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
    this.listTypeShipping = commonFunction.typeShipping;
    this.listTypeReturnOrder = commonFunction.typeReturnOrder;
    let authenication_cookies = this.cookies.get("authenication_cookies");
    this.idRequest = localStorage.getItem("id_customer_request");
    let accesstoken_cookies = this.cookies.get("accesstoken_cookies");
    if (authenication_cookies == null) {
      commonFunction.redirect("/");
    }

    commonFunction.disableInput(".product-select-weight");
    commonFunction.disableInput(".product-select-public-price");

    this.configRequestApi = {
      headers: { Authorization: "Bearer " + accesstoken_cookies },
    };

    //warehouse
    axios
      .get(
        commonFunction.DOMAIN_URL +
          "v1/warehouse/all/customer/" +
          this.idRequest,
        this.configRequestApi
      )
      .then((response) => {
        let respronseData = response.data;
        this.listWarehouseByCustomer = respronseData;
      })
      .catch((e) => {
        console.log(e);
      });

    //customer detail
    axios
      .get(
        commonFunction.DOMAIN_URL + "v1/customer/detail/" + this.idRequest,
        this.configRequestApi
      )
      .then((response) => {
        let respronseData = response.data;
        this.formDataOrder.senderName =
          respronseData.customer.companyName +
          " - " +
          respronseData.customer.fullName;
        this.formDataOrder.senderPhone = respronseData.customer.phone;
        this.formDataOrder.senderAddress =
          respronseData.customer.addressSet[0].street +
          ", " +
          respronseData.customer.addressSet[0].ward +
          ", " +
          respronseData.customer.addressSet[0].district +
          ", " +
          respronseData.customer.addressSet[0].province;
      })
      .catch((e) => {
        console.log(e);
      });

    //product
    axios
      .get(
        commonFunction.DOMAIN_URL + "v1/product/customer/" + this.idRequest,
        this.configRequestApi
      )
      .then((response) => {
        let respronseData = response.data;
        this.listProductByCustomer = respronseData;
      })
      .catch((e) => {
        console.log(e);
      });
  },
  updated() {
    // this.formDataOrder.priceTotal = this.productSelected.reduce(function (sum, p) {
    //     return sum + p.publicPrice;
    // }, 0);
    // this.formDataOrder.weightTotal = this.productSelected.reduce(function (sum, p) {
    //     return sum + p.weight;
    // }, 0);
  },
  watch: {},
  methods: {
    updateCustomerAddress(data, type) {
      switch (type) {
        case "PROVINCE":
          this.customerAddress.provinceId = data.code;
          this.customerAddress.province = data.label;
          break;
        case "DISTRICT":
          this.customerAddress.districtId = data.code;
          this.customerAddress.district = data.label;
          break;
        case "WARD":
          this.customerAddress.wardId = data.code;
          this.customerAddress.ward = data.label;
          break;
        case "STREET":
          this.customerAddress.street = data;
          break;
        default:
          break;
      }

      //mapping data to formData
      this.formDataOrder.orderItem[0].shippingAddress.province =
        this.customerAddress.province;
      this.formDataOrder.orderItem[0].shippingAddress.district =
        this.customerAddress.district;
      this.formDataOrder.orderItem[0].shippingAddress.ward =
        this.customerAddress.ward;
      this.formDataOrder.orderItem[0].shippingAddress.street =
        this.customerAddress.street;
      this.formDataOrder.orderItem[0].shippingAddress.provinceId =
        this.customerAddress.provinceId;
      this.formDataOrder.orderItem[0].shippingAddress.districtId =
        this.customerAddress.districtId;
      this.formDataOrder.orderItem[0].shippingAddress.wardId =
        this.customerAddress.wardId;
    },
    validationForm() {
      if (this.customerAddress.provinceId == "") {
        this.isValid += 1;
        this.msgValidationFor.customerAddress.provinceId =
          "Vui lòng chọn Tỉnh/thành phố!";
      }
      if (this.customerAddress.districtId == "") {
        this.isValid += 1;
        this.msgValidationFor.customerAddress.districtId =
          "Vui lòng chọn Quận/Huyện!";
      }
      if (this.customerAddress.wardId == "") {
        this.isValid += 1;
        this.msgValidationFor.customerAddress.wardId =
          "Vui lòng chọn Phường/Xã!";
      }
      if (this.customerAddress.street == "") {
        this.isValid += 1;
        this.msgValidationFor.customerAddress.street =
          "Vui lòng điền địa chỉ chi tiết!";
      }
    },
    addItemProductList: function (index) {
      console.log(index);

      if (this.productSelected[index].sku != "default") {
        this.productSelected.push({
          sku: "default",
          weight: "",
          publicPrice: "",
          qty: "",
        });

        this.formDataOrder.orderItem[0].products.push({
          productId: "",
          qty: "",
        });
      }
    },
    removeItemProductList: function (product) {
      if (product) {
        this.productSelected = this.productSelected.filter(
          (p) => p.sku !== product.sku
        );
      }
    },
    generateCodeToText: function (text) {
      return commonFunction.generateCodeToText(text);
    },
    applySaleCode: function (code) {
      this.formDataOrder.coupon = code;
    },
    //order
    createOrder: function () {
      //mapping id product
      this.productSelected.forEach((element, index) => {
        this.formDataOrder.orderItem[0].products[index].productId = element.id;
      });

      let bodyRequest = {
        customerId: this.idRequest,
        senderName: this.formDataOrder.senderName,
        senderPhone: this.formDataOrder.senderPhone,
        senderAddress: this.formDataOrder.senderAddress,
        note: this.formDataOrder.note,
        status: "default",
        feedback: this.formDataOrder.feedback,
        notification: false,
        shippingFee: this.formDataOrder.shippingFee,
        coupon: this.formDataOrder.coupon,
        warehouseId: this.formDataOrder.warehouseId,
        shippingType: this.formDataOrder.shippingType,
        shippingDate: "",
        orderItem: [
          {
            shippingAddress: this.formDataOrder.orderItem[0].shippingAddress,
            products: this.formDataOrder.orderItem[0].products,
          },
        ],
      };

      if ((this.isSelectedTypeLH == "LHTN")) {
        bodyRequest.shippingDate =
          this.shippingDateFullDate + " " + this.shippingDateHHMM + ":00";
      }
      console.log(bodyRequest);
      axios
        .post(
          commonFunction.DOMAIN_URL + "v1/order/save",
          bodyRequest,
          this.configRequestApi
        )
        .then((response) => {
          if (response.status == 200) {
            alert("SUCCESS: Tạo mới thành công - " + response.data.message);
            commonFunction.reloadPage();
          } else {
            alert("FAIL: Tạo mới không thành công! Vui lòng thử lại!");
          }
        })
        .catch((e) => {
          alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
          console.log(e);
        });
    },
  },
};
</script>
<style scoped>
a {
  text-decoration: none;
}

.hide {
  display: none;
}

.show {
  display: inline;
}

.vs__search,
.vs__search:focus {
  margin: 7px 0px;
}

.vs__dropdown-toggle {
  width: 105% !important;
}
</style>