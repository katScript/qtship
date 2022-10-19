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
                                            placeholder="Số điện thoại người nhận" />
                                        <br />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-user"></i></div>
                                    <div class="form-group col-11">
                                        <input type="text" class="form-control" id="" placeholder="Tên người nhận" />
                                        <br />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-1">
                                        <i class="fa-solid fa-location-dot"></i>
                                    </div>
                                    <div class="col-11">
                                        <LocationPicker :updateCustomerAddress="updateCustomerAddress"
                                            :msgValidationFor="msgValidationFor" />
                                    </div>
                                </div>
                                <hr />
                                <h5>Thông tin người gửi</h5>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-phone"></i></div>
                                    <div class="form-group col-11">
                                        <input type="text" class="form-control" id=""
                                            placeholder="Số điện thoại người gửi" />
                                        <br />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-user"></i></div>
                                    <div class="form-group col-11">
                                        <input type="text" class="form-control" id=""
                                            placeholder="Thông tin người gửi" />
                                        <br />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-1">
                                        <i class="fa-solid fa-location-dot"></i>
                                    </div>
                                    <div class="col-11">
                                        <input type="text" class="form-control" id="" placeholder="Địa chỉ người gửi" />
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
                                            <select class="form-select" aria-label="Default select example">
                                                <option value="1">Stock 1</option>
                                                <option value="2">Stock 2</option>
                                                <option value="3">Stock 3</option>
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
                                                        class="form-control" name="" />
                                                </div>
                                                <div class="col-4">
                                                    <input type="time" id="datetimepicker-take-order"
                                                        class="form-control" name="" />
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
                                    <div class="col-6"><button class="btn btn-outline-success"
                                            style="float: right;">Danh sách sản phẩm</button></div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-11">
                                        <div class="row">
                                            <div class="col-2"><img src="" alt="" width="100" height="100"></div>
                                            <div class="col-10">
                                                <div class="col-12">
                                                    <select class="form-select" aria-label="Default select example">
                                                        <option selected>Chọn sản phẩm</option>
                                                        <option>Chọn sản phẩm</option>
                                                    </select>
                                                </div>
                                                <div class="col-12">
                                                    Line 2
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-1">
                                        <button  class="btn btn-outline-success"><i class="fa-solid fa-plus"></i></button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-11">
                                        <div class="row">
                                            <div class="col-2"><img src="" alt="" width="100" height="100"></div>
                                            <div class="col-10">
                                                <div class="col-12">
                                                    <select class="form-select" aria-label="Default select example">
                                                        <option selected>Chọn sản phẩm</option>
                                                        <option>Chọn sản phẩm</option>
                                                    </select>
                                                </div>
                                                <div class="col-12">
                                                    Line 2
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-1">
                                        <button  class="btn btn-outline-success"><i class="fa-solid fa-minus"></i></button>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="Trọng lượng đơn hàng">
                                        <br>
                                        <input type="text" class="form-control" placeholder="Tiền thu hộ">
                                        <br>
                                        <input type="text" class="form-control" placeholder="Giá trị hàng">
                                        <br>
                                    </div>
                                    <div class="col-sm-3">
                                        <img src="" alt="" width="150"  height="150">
                                    </div>
                                </div>
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
    // import { commonFunction } from "../scripts/ulti";

    import { useCookies } from "vue3-cookies";
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
</style>