<template>
    <div class="register-page">
        <div class="container">
            <div class="div-container-register">
                <img src="../images/logo-branch.png" width="300" class="d-block mx-auto logo-branch-register" alt="">
                <div class="row form-register">
                    <h5>Thông tin khách hàng</h5>
                    <div class="col-md-8">
                        <small class="text-danger">{{msgValidationFor.customer.companyName}}</small>
                        <input v-model="customer.companyName" type="text" class="form-control input-form-register"
                            id="input-register-name-shop" placeholder="Tên chi nhánh/cửa hàng/shop">
                    </div>
                    <div class="col-md-4">
                        <small class="text-danger">{{msgValidationFor.customer.phone}}</small>
                        <input v-model="customer.phone" type="text" class="form-control input-form-register"
                            id="input-register-phone-number-shop" placeholder="Số điện thoại">
                    </div>
                    <!-- line 2 -->
                    <div class="col-md-4">
                        <small class="text-danger">{{msgValidationFor.customer.email}}</small>
                        <input v-model="customer.email" type="email" class="form-control input-form-register"
                            id="input-register-email-shop" placeholder="Địa chỉ email">
                    </div>
                    <div class="col-md-4">
                        <small class="text-danger">{{msgValidationFor.password}}</small>
                        <input v-model="password" type="password" class="form-control input-password-register"
                            id="input-register-password-shop" placeholder="Mật khẩu" style="margin-bottom: 28px;">
                    </div>
                    <div class="col-md-4">
                        <small class="text-danger">{{msgValidationFor.confirmPassword}}</small>
                        <input v-model="confirmPassword" type="password"
                            class="form-control input-confirm-password-register"
                            id="input-register-confirm-password-shop" placeholder="Nhập lại mật khẩu"
                            style="margin-bottom: 28px;">
                    </div>
                    <!-- line 3 -->
                    <div class="col-md-12">
                        <LocationPicker @updateCustomerAddress="updateCustomerAddress"
                            :msgValidationFor="msgValidationFor" />
                    </div>

                    <br />
                    <h5>Thông tin đối soát ngân hàng</h5>
                    <BankPicker @updateForControl="updateForControl" :msgValidationFor="msgValidationFor" />
                    <br />
                    <div class="col-md-12">
                        <small class="text-danger">{{ msgValidationFor.isAccepted }}</small>
                        <br>
                        <input v-model="isAccepted" type="checkbox" class="form-check-input" id="radio-accept-contract">
                        <label class="form-check-label" for="exampleCheck1" style="padding-left: 10px;"> Tôi đã đọc
                            và đồng ý với Chính sách bảo mật thông tin</label>
                    </div>
                    <br />
                    <div class="mb-3 text-center">
                        <br>
                        <br>
                        <button v-on:click="register()" class="btn btn-danger btn-login-qt btn-register-qt">Đăng
                            kí</button>
                        <br>
                        <br>
                        <p>Bạn đã có tài khoản, <a href="/login-page" class="a-login-account">Đăng nhập ngay!</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>



<script>
    import LocationPicker from './common/LocationPicker.vue';
    import BankPicker from './common/BankPicker.vue';
    import { commonFunction } from "../scripts/ulti";
    import axios from "axios";
    export default {
        components: {
            LocationPicker,
            BankPicker
        },
        data() {
            return {
                isValid: 0,
                isAccepted: false,
                username: "",
                password: "",
                confirmPassword: "",
                customer: {
                    fullName: "",
                    phone: "",
                    companyName: "",
                    email: ""
                },
                customerAddress: {
                    province: "",
                    provinceId: "",
                    district: "",
                    districtId: "",
                    ward: "",
                    wardId: "",
                    street: ""
                },
                forControl: {
                    holderName: "",
                    cardNumber: "",
                    bank: "",
                    address: ""
                },
                msgValidationFor: {
                    password: "",
                    confirmPassword: "",
                    customer: {
                        phone: "",
                        companyName: "",
                        email: ""
                    },
                    customerAddress: {
                        provinceId: "",
                        districtId: "",
                        wardId: "",
                        street: ""
                    },
                    forControl: {
                        holderName: "",
                        cardNumber: "",
                        bank: "",
                        address: ""
                    },
                }
            }
        },
        updated() {
            this.customer.fullName = this.customer.companyName;
            this.username = this.customer.email;
        },
        watch: {
        },
        methods: {
            register: function () {
                this.isValid = 0;
                this.validationForm();
                if (this.isValid > 0) {
                    // alert('Đăng kí không thành công! Vui lòng kiểm tra lại thông tin!');
                } else {
                    axios
                        .post(commonFunction.DOMAIN_URL + "v1/customer/auth/register", {
                            username: this.customer.email,
                            password: this.password,
                            customer: {
                                fullName: this.customer.companyName,
                                phone: this.customer.phone,
                                companyName: this.customer.companyName,
                                email: this.customer.email
                            },
                            customerAddress: {
                                province: this.customerAddress.province,
                                provinceId: this.customerAddress.provinceId,
                                district: this.customerAddress.district,
                                districtId: this.customerAddress.districtId,
                                ward: this.customerAddress.ward,
                                wardId: this.customerAddress.wardId,
                                street: this.customerAddress.street
                            },
                            forControl: {
                                holderName: this.forControl.holderName,
                                cardNumber: this.forControl.cardNumber,
                                bank: this.forControl.bank,
                                address: this.forControl.address
                            }
                        })
                        .then((response) => {
                            let resData = response;
                            if (resData.status == 200) {
                                console.log(resData);
                                alert('Đăng kí thành công! Chuyển tới đăng nhập!');
                                commonFunction.redirect("/login-page");
                            }
                        })
                        .catch((error) => {
                            alert('ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!')
                            console.log(error);
                        });
                }
            },
            updateCustomerAddress(data, type) {
                switch (type) {
                    case 'PROVINCE':
                        this.customerAddress.provinceId = data.code;
                        this.customerAddress.province = data.label;
                        break;
                    case 'DISTRICT':
                        this.customerAddress.districtId = data.code;
                        this.customerAddress.district = data.label;
                        break;
                    case 'WARD':
                        this.customerAddress.wardId = data.code;
                        this.customerAddress.ward = data.label;
                        break;
                    case 'STREET':
                        this.customerAddress.street = data;
                        break;
                    default:
                        break;
                }

            },
            updateForControl(data, type) {
                switch (type) {
                    case 'HOLDER':
                        this.forControl.holderName = data;
                        break;
                    case 'CARDNUMBER':
                        this.forControl.cardNumber = data;
                        break;
                    case 'BANK':
                        this.forControl.bank = data.code;
                        break;
                    case 'ADDRESS':
                        this.forControl.address = data;
                        break;
                    default:
                }
            },
            validationForm() {
                if (this.customer.companyName == "") {
                    this.msgValidationFor.customer.companyName = "Vui lòng điền Tên chi nhánh/cửa hàng/shop!";
                    this.isValid += 1;
                }
                if (this.customer.phone == "") {
                    this.msgValidationFor.customer.phone = "Vui lòng điền thông tin số điện thoại!";
                    this.isValid += 1;
                }
                if (!commonFunction.regexStringValidate(this.customer.phone, "PHONE")) {
                    this.msgValidationFor.customer.phone = "Số điện thoại có tối đa 11 chữ số và không tồn tại chữ cái!";
                    this.isValid += 1;
                }
                if (this.customer.email == "") {
                    this.msgValidationFor.customer.email = "Vui lòng điền thông tin email!";
                    this.isValid += 1;
                }
                if (!commonFunction.regexStringValidate(this.customer.email, "EMAIL")) {
                    this.msgValidationFor.customer.email = "Email không hợp lệ! (Ex: myemail@domain.xyz,...)";
                    this.isValid += 1;
                }
                if (this.password == "") {
                    this.msgValidationFor.password = "Vui lòng điền mật khẩu";
                    this.isValid += 1;
                }
                if (!commonFunction.regexStringValidate(this.password, "PASSWORD")) {
                    this.msgValidationFor.password = "Mật khẩu từ 7 đến 15 ký tự trong đó có ít nhất một chữ số và một ký tự đặc biệt!";
                    this.isValid += 1;
                }
                if (this.confirmPassword != this.password) {
                    this.msgValidationFor.confirmPassword = "Mật khẩu nhập lại không khớp!";
                    this.isValid += 1;
                }
                if (this.customerAddress.provinceId == "") {
                    this.isValid += 1;
                    this.msgValidationFor.customerAddress.provinceId = "Vui lòng chọn Tỉnh/thành phố!";
                }
                if (this.customerAddress.districtId == "") {
                    this.isValid += 1;
                    this.msgValidationFor.customerAddress.districtId = "Vui lòng chọn Quận/Huyện!";
                }
                if (this.customerAddress.wardId == "") {
                    this.isValid += 1;
                    this.msgValidationFor.customerAddress.wardId = "Vui lòng chọn Phường/Xã!";
                }
                if (this.customerAddress.street == "") {
                    this.isValid += 1;
                    this.msgValidationFor.customerAddress.street = "Vui lòng điền địa chỉ chi tiết!";
                }
                if (this.forControl.holderName == "") {
                    this.isValid += 1;
                    this.msgValidationFor.forControl.holderName = "Vui lòng điền Tên chủ tài khoản!";
                }
                if (this.forControl.cardNumber == "") {
                    this.isValid += 1;
                    this.msgValidationFor.forControl.cardNumber = "Vui lòng điền số tài khoản ngân hàng!";
                }
                if (this.forControl.bank == "") {
                    this.isValid += 1;
                    this.msgValidationFor.forControl.bank = "Vui lòng chọn Ngân hàng!";
                }
                if (!this.isAccepted) {
                    this.isValid += 1;
                    this.msgValidationFor.isAccepted = "Vui lòng đồng ý với Chính sách bảo mật thông tin!"
                }
            },
            resetValidationForm() {
                this.msgValidationFor = {
                    password: "",
                    confirmPassword: "",
                    customer: {
                        phone: "",
                        companyName: "",
                        email: ""
                    },
                    customerAddress: {
                        provinceId: "",
                        districtId: "",
                        wardId: "",
                        street: ""
                    },
                    forControl: {
                        holderName: "",
                        cardNumber: "",
                        bank: "",
                        address: ""
                    },
                }
            }
        },
    }
</script>
<style scoped>
    .msg-notify-invalid {
        color: red;
    }
</style>