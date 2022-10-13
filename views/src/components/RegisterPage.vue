<template>
    <div class="register-page">
        <div class="container">
            <div class="div-container-register">
                <img src="../images/logo-branch.png" width="300" class="d-block mx-auto logo-branch-register" alt="">
                <form>
                    <div class="row form-register">
                        <h5>Thông tin khách hàng</h5>
                        <div class="col-md-8">
                            <input v-model="customer.companyName" type="text" class="form-control input-form-register"
                                id="input-register-name-shop" placeholder="Tên chi nhánh/cửa hàng/shop">
                        </div>
                        <div class="col-md-4">
                            <input v-model="customer.phone" type="text" class="form-control input-form-register"
                                id="input-register-phone-number-shop" placeholder="Số điện thoại">
                        </div>
                        <!-- line 2 -->
                        <div class="col-md-4">
                            <input v-model="customer.email" type="email" class="form-control input-form-register"
                                id="input-register-email-shop" placeholder="Địa chỉ email">
                        </div>
                        <div class="col-md-4">
                            <input v-model="password" type="password" class="form-control input-password-register"
                                id="input-register-password-shop" placeholder="Mật khẩu" style="margin-bottom: 28px;">
                        </div>
                        <div class="col-md-4">
                            <input v-model="confirmPassword" type="password"
                                class="form-control input-confirm-password-register"
                                id="input-register-confirm-password-shop" placeholder="Nhập lại mật khẩu"
                                style="margin-bottom: 28px;">
                        </div>
                        <!-- line 3 -->
                        <div class="col-md-12">
                            <LocationPicker @updateCustomerAddress="updateCustomerAddress" />
                        </div>

                        <br />
                        <h5>Thông tin đối soát ngân hàng</h5>
                        <BankPicker />
                        <br />
                        <div class="col-md-12">
                            <input type="checkbox" class="form-check-input" id="radio-accept-contract">
                            <label class="form-check-label" for="exampleCheck1" style="padding-left: 10px;"> Tôi đã đọc
                                và đồng ý với Chính sách bảo mật thông tin</label>
                        </div>
                        <br />
                        <div class="mb-3 text-center">
                            <br>
                            <br>
                            <button type="submit" class="btn btn-danger btn-login-qt btn-register-qt">Đăng kí</button>
                            <br>
                            <br>
                            <p>Bạn đã có tài khoản, <a href="/login-page" class="a-login-account">Đăng nhập ngay!</a>
                            </p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>



<script>
    import LocationPicker from './common/LocationPicker.vue';
    import BankPicker from './common/BankPicker.vue';
    export default {
        components: {
            LocationPicker,
            BankPicker
        },
        data() {
            return {
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
                }
            }
        },
        methods: {
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

            }
        },
    }
</script>