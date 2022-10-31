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
                                        <input type="text" class="form-control" id=""
                                            placeholder="Số điện thoại người nhận"
                                            v-model="formDataOrder.orderItemResponse[0].shippingAddressResponse.phone" />
                                        <br />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-user"></i></div>
                                    <div class="form-group col-11">
                                        <input type="text" class="form-control" id="" placeholder="Tên người nhận"
                                            v-model="formDataOrder.orderItemResponse[0].shippingAddressResponse.receiverName" />
                                        <br />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-1">
                                        <i class="fa-solid fa-location-dot"></i>
                                    </div>
                                    <div class="col-11">
                                        <LocationPicker @updateCustomerAddress="updateCustomerAddress"
                                            :msgValidationFor="msgValidationFor" />
                                    </div>
                                </div>
                                <hr />
                                <h5>Thông tin người gửi</h5>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-phone"></i></div>
                                    <div class="form-group col-11">
                                        <input type="text" class="form-control" id=""
                                            placeholder="Số điện thoại người gửi" v-model="formDataOrder.senderPhone" />
                                        <br />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-user"></i></div>
                                    <div class="form-group col-11">
                                        <input type="text" class="form-control" id="" placeholder="Thông tin người gửi"
                                            v-model="formDataOrder.senderName" />
                                        <br />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-1">
                                        <i class="fa-solid fa-location-dot"></i>
                                    </div>
                                    <div class="col-11">
                                        <input type="text" class="form-control" id="" placeholder="Địa chỉ người gửi"
                                            v-model="formDataOrder.senderAddress" />
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
                                        <select class="form-select" aria-label="Default select example">
                                            <option selected>Chọn phương thức giao hàng</option>
                                            <option value="1">Standard</option>
                                            <option value="2">Fast</option>
                                            <option value="3">Super</option>
                                        </select>
                                        <br />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="flexRadioDefault"
                                            id="flexRadioDefault1" v-on:click="isSelectedTypeLH = 'GHTBC'" />
                                        <label class="form-check-label" for="flexRadioDefault1">
                                            Gửi hàng tại bưu cục
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="flexRadioDefault"
                                            id="flexRadioDefault2" v-on:click="isSelectedTypeLH = 'LHTN'" />
                                        <label class="form-check-label" for="flexRadioDefault2">
                                            Lấy hàng tận nơi
                                        </label>
                                    </div>
                                </div>
                                <div :class="isSelectedTypeLH == 'LHTN' ? 'show' : 'hide'">
                                    <div class="form-group row">
                                        <div class="col-4">
                                            <label for="" class="label-control">Địa điểm lấy hàng</label>
                                        </div>
                                        <div class="col-8">
                                            <select class="form-select" aria-label="Default select example"
                                                v-model="formDataOrder.warehouseId">
                                                <option :value="w.id" v-for="(w, index) in listWarehouseByCustomer"
                                                    :key="index">{{w.name}}</option>
                                            </select>
                                            <br />
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-4">
                                            <label for="" class="label-control">Thời gian lấy hàng/bưu cục nhận
                                                hàng</label>
                                        </div>
                                        <div class="col-8">
                                            <div class="row">
                                                <div class="col-8">
                                                    <input type="date" id="datetimepicker-take-order"
                                                        class="form-control" name="" v-model="shippingDateDDMMYYYY" />
                                                </div>
                                                <div class="col-4">
                                                    <input type="time" id="datetimepicker-take-order"
                                                        class="form-control" name="" v-model="shippingDateHHMMSS" />
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
                                    <div class="col-6"><a href="/client/stock/create-product"
                                            class="btn btn-outline-success" style="float: right;">Danh sách sản phẩm</a>
                                    </div>
                                </div>
                                <br>
                                <div class="list-product-in-order" v-for="(ps,index) in productSelected" :key="index">
                                    <div class="row">
                                        <div class="col-11">
                                            <div class="row">
                                                <div class="col-2"><img src="" alt="" class="mb-1" width="80"
                                                        height="80"></div>
                                                <div class="col-10">
                                                    <div class="row">
                                                        <div class="col-10">
                                                            <select class="form-select"
                                                                aria-label="Default select example"
                                                                v-model="productSelected[index]">
                                                                <!-- index 0 is default -->
                                                                <option :value="p"
                                                                    v-for="(p, index) in listProductByCustomer"
                                                                    :key="index">{{p.name}}</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-2">
                                                            <input type="number" class="form-control mb-1"
                                                                placeholder="SL">
                                                        </div>
                                                        <div class="col-6">
                                                            <input type="text" class="form-control"
                                                                v-model="productSelected[index].weight"
                                                                placeholder="Trọng lượng (kg)">
                                                        </div>
                                                        <div class="col-6">
                                                            <input type="text" class="form-control"
                                                                v-model="productSelected[index].publicPrice"
                                                                placeholder="Giá bán (VNĐ)">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-1">
                                            <button class="btn btn-outline-success"
                                                v-on:click="addItemProductList(index)"><i
                                                    class="fa-solid fa-plus plus-order-number"></i></button>
                                            <button class="btn btn-outline-danger"
                                                :class="productSelected.length > 1 ? 'show' : 'hide'"
                                                v-on:click="removeItemProductList(productSelected[index])"><i
                                                    class="fa-solid fa-minus minus-order-number"></i></button>
                                        </div>
                                    </div>
                                    <hr>
                                </div>

                                <div class="row">
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="Trọng lượng đơn hàng"
                                            v-model="formDataOrder.shippingFee">
                                        <br>
                                        <input type="text" class="form-control" placeholder="Tiền thu hộ"
                                            v-model="formDataOrder.subtotal">
                                        <br>
                                        <input type="text" class="form-control" placeholder="Giá trị hàng"
                                            v-model="formDataOrder.subtotal">
                                        <br>
                                    </div>
                                    <div class="col-sm-3">
                                        <img src="" alt="" width="150" height="150">
                                    </div>
                                    <div class="col-sm-8">
                                        Phí ship: <span class="feeShip">0 đ</span>
                                        <br>
                                    </div>
                                    <div class="col-sm-4">
                                        <select class="form-select" aria-label="Default select example"
                                            v-model="formDataOrder.shippingFee">
                                            <option value="1">Shop trả ship</option>
                                            <option value="2">Người nhận trả ship</option>
                                        </select>
                                        <br>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="">Ghi chú đơn hàng</label>
                                            <textarea type="" class="form-control" id="" aria-describedby="emailHelp"
                                                v-model="formDataOrder.note" placeholder="Nhập ghi chú của đơn hàng" />
                                            <br>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="row">
                                            <div class="col-8">
                                                <input type="text" class="form-control" placeholder="Mã giảm giá">
                                            </div>
                                            <div class="col-4">
                                                <button class="btn btn-danger w-100"
                                                    v-on:click="isDisplayListVourcer = !isDisplayListVourcer">{{
                                                    !isDisplayListVourcer
                                                    ? 'Thêm mã giảm giá' : 'Đóng'
                                                    }}</button>
                                            </div>
                                        </div>

                                        <div class="row" :class="isDisplayListVourcer ? 'show' : 'hide'">
                                            <div class="col-12">
                                                <div class="list-group">
                                                    <a href="#" class="list-group-item list-group-item-action">
                                                        <div class="row">
                                                            <div class="col-1 mt-1"><i
                                                                    class="fa-solid fa-ticket text-danger"
                                                                    style="font-size: 24px;"></i></div>
                                                            <div class="col-3 mt-1">MPVC001-VC</div>
                                                            <div class="col-5 mt-1">Miễn phí phí vận chuyển</div>
                                                            <div class="col-3"><button
                                                                    class="btn btn-outline-danger w-100">Áp
                                                                    dụng</button></div>
                                                        </div>
                                                    </a>
                                                    <a href="#" class="list-group-item list-group-item-action">
                                                        <div class="row">
                                                            <div class="col-1 mt-1"><i
                                                                    class="fa-solid fa-ticket text-danger"
                                                                    style="font-size: 24px;"></i></div>
                                                            <div class="col-3 mt-1">MPVC001-VC</div>
                                                            <div class="col-5 mt-1">Miễn phí phí vận chuyển</div>
                                                            <div class="col-3"><button
                                                                    class="btn btn-outline-danger w-100">Áp
                                                                    dụng</button></div>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                    </div>
                                    <div class="col-sm-4">
                                        <label for="" class="label-control"><span style="color: red;">(*)</span> TH Hoàn
                                            hàng: </label>
                                        <br>
                                    </div>
                                    <div class="col-sm-8">
                                        <select class="form-select" aria-label="Default select example">
                                            <option value="1">Lưu kho chờ check</option>
                                            <option value="2">Không nhận hàng trả</option>
                                            <option value="3">Không lưu kho - Hoàn ngay</option>
                                        </select>
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-md-6">

                            </div>
                            <div class="col-md-6">
                                <input v-model="isAccepted" type="checkbox" class="form-check-input"
                                    id="radio-accept-contract">
                                <label class="form-check-label" for="exampleCheck1" style="padding-left: 10px;"> Tôi đã
                                    đọc
                                    và đồng ý với Chính sách bảo mật thông tin</label>
                                <br>
                                <button class="btn btn-success w-100" style="font-size: 18px;"><i
                                        class="fa-solid fa-up-right-from-square"></i> Đăng đơn hàng</button>
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
                numberProduct: 3,
                productSelected: [
                    { sku: "default", weight: "", publicPrice: "" }
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
                shippingDateDDMMYYYY: "",
                shippingDateHHMMSS: "",
                formDataOrder: {
                    id: "",
                    customerId: "",
                    senderName: "",
                    senderPhone: "",
                    senderAddress: "",
                    note: "",
                    status: "",
                    feedback: "",
                    notification: "",
                    shippingFee: "",
                    coupon: "",
                    warehouseId: "",
                    shippingType: "",
                    shippingTime: "",
                    subtotal: "",
                    orderItemResponse: [
                        {
                            price: "",
                            shippingAddressResponse: {
                                name: "",
                                phone: "",
                                province: "",
                                district: "",
                                ward: "",
                                street: "",
                                provinceId: "",
                                districtId: "",
                                wardId: ""
                            },
                            products: [
                            ]
                        }
                    ]
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

            //customer detail
            axios
                .get(commonFunction.DOMAIN_URL + "v1/customer/detail/" + this.idRequest, this.configRequestApi)
                .then((response) => {
                    let respronseData = response.data;
                    this.formDataOrder.senderName = respronseData.customer.companyName + " - " + respronseData.customer.fullName;
                    this.formDataOrder.senderPhone = respronseData.customer.phone;
                    this.formDataOrder.senderAddress = respronseData.customer.addressSet[0].street + ", " + respronseData.customer.addressSet[0].ward + ", " + respronseData.customer.addressSet[0].district + ", " + respronseData.customer.addressSet[0].province;
                }).catch((e) => { console.log(e) })

            //product
            axios
                .get(commonFunction.DOMAIN_URL + "v1/product/customer/" + this.idRequest, this.configRequestApi)
                .then((response) => {
                    let respronseData = response.data;
                    this.listProductByCustomer = respronseData;
                }).catch((e) => { console.log(e) })
        },
        updated() {
            // this.formDataOrder.priceTotal = this.productSelected.reduce(function (sum, p) {
            //     return sum + p.publicPrice;
            // }, 0);
            // this.formDataOrder.weightTotal = this.productSelected.reduce(function (sum, p) {
            //     return sum + p.weight;
            // }, 0);
        },
        watch: {
        },
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
                this.formDataOrder.orderItemResponse[0].province = this.customerAddress.province;
                this.formDataOrder.orderItemResponse[0].district = this.customerAddress.district;
                this.formDataOrder.orderItemResponse[0].ward = this.customerAddress.ward;
                this.formDataOrder.orderItemResponse[0].street = this.customerAddress.street;
                this.formDataOrder.orderItemResponse[0].provinceId = this.customerAddress.provinceId;
                this.formDataOrder.orderItemResponse[0].districtId = this.customerAddress.districtId;
                this.formDataOrder.orderItemResponse[0].wardId = this.customerAddress.wardId;
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
            addItemProductList(index) {
                console.log(index);

                if (this.productSelected[index].sku != "default") {
                    this.productSelected.push({ sku: "default", weight: "", publicPrice: "" });
                }
            },
            removeItemProductList(product) {
                if (product) {
                    this.productSelected = this.productSelected.filter(p => p.sku !== product.sku);
                }
            }
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