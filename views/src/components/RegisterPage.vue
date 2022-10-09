<template>
    <div class="register-page">
        <div class="container">
            <div class="div-container-register">
                <img src="../images/logo-branch.png" width="300" class="d-block mx-auto logo-branch-register" alt="">
                <div class="row form-register">
                    <h5>Thông tin khách hàng</h5>
                    <div class="col-md-8">
                        <input type="text" class="form-control input-form-register" id="input-register-name-shop"
                            placeholder="Tên chi nhánh/cửa hàng/shop">
                    </div>
                    <div class="col-md-4">
                        <input type="text" class="form-control input-form-register"
                            id="input-register-phone-number-shop" placeholder="Số điện thoại">
                    </div>
                    <!-- line 2 -->
                    <div class="col-md-4">
                        <input type="email" class="form-control input-form-register" id="input-register-name-shop"
                            placeholder="Địa chỉ email">
                    </div>
                    <div class="col-md-4">
                        <input type="password" class="form-control input-password-register"
                            id="input-register-name-shop" placeholder="Mật khẩu">
                    </div>
                    <div class="col-md-4">
                        <input type="password" class="form-control input-confirm-password-register"
                            id="input-register-name-shop" placeholder="Nhập lại mật khẩu">
                    </div>
                    <!-- line 3 -->
                    <LocationPicker />
                </div>
            </div>
        </div>
    </div>
</template>



<script>
    import LocationPicker from './common/LocationPicker.vue';
    export default {
        components: {
            LocationPicker
        }
    }
</script>