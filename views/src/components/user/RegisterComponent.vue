<template>
    <div class="register-page">
        <div class="container">
            <div class="div-container-register">
                <img src="@/images/logo-branch.png" width="300" class="d-block mx-auto logo-branch-register" alt="">
                <div class="row form-register">
                    <h5>Thông tin khách hàng</h5>
                    <div class="col-md-8">
                        <small class="text-danger">{{ customerData.errors.customer.companyName }}</small>
                        <input v-model="customerData.data.customer.companyName" type="text"
                               class="form-control input-form-register"
                               id="input-register-name-shop" placeholder="Tên chi nhánh/cửa hàng/shop">
                    </div>
                    <div class="col-md-4">
                        <small class="text-danger">{{ customerData.errors.customer.phone }}</small>
                        <input v-model="customerData.data.customer.phone" type="text"
                               class="form-control input-form-register"
                               id="input-register-phone-number-shop" placeholder="Số điện thoại">
                    </div>
                    <!-- line 2 -->
                    <div class="col-md-4">
                        <small class="text-danger">{{ customerData.errors.customer.email }}</small>
                        <input v-model="customerData.data.customer.email" type="email"
                               class="form-control input-form-register"
                               id="input-register-email-shop" placeholder="Địa chỉ email">
                    </div>
                    <div class="col-md-4">
                        <small class="text-danger">{{ customerData.errors.password }}</small>
                        <input v-model="customerData.data.password" type="password"
                               class="form-control input-password-register"
                               id="input-register-password-shop" placeholder="Mật khẩu" style="margin-bottom: 28px;">
                    </div>
                    <div class="col-md-4">
                        <small class="text-danger">{{ customerData.errors.confirmPassword }}</small>
                        <input v-model="customerData.data.confirmPassword" type="password"
                               class="form-control input-confirm-password-register"
                               id="input-register-confirm-password-shop" placeholder="Nhập lại mật khẩu"
                               style="margin-bottom: 28px;">
                    </div>
                    <!-- line 3 -->
                    <div class="col-md-12">
                        <LocationPicker @updateAddress="updateAddress"
                                        :dataValidate="customerData.errors.customerAddress"
                                        ref="locationPicker"
                        />
                    </div>

                    <br/>
                    <h5>Thông tin đối soát ngân hàng</h5>
                    <BankPicker
                        @updateForControl="updateForControl"
                        :msgValidationFor="customerData.errors"
                    />
                    <br/>
                    <div class="col-md-12">
                        <small class="text-danger">{{ customerData.errors.isAccepted }}</small>
                        <br>
                        <input v-model="isAccepted" type="checkbox" class="form-check-input" id="radio-accept-contract">
                        <label class="form-check-label" for="exampleCheck1" style="padding-left: 10px;"> Tôi đã đọc và
                            đồng ý với Chính sách bảo mật thông tin</label>
                    </div>
                    <br/>
                    <div class="mb-3 text-center p-3">
                        <button v-on:click="register()" class="btn btn-danger btn-login-qt btn-register-qt">Đăng kí
                        </button>
                        <p>Bạn đã có tài khoản, <a href="/login-page" class="a-login-account">Đăng nhập ngay!</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script src="@/components/user/js/register"></script>
<style scoped>
.msg-notify-invalid {
    color: red;
}
</style>