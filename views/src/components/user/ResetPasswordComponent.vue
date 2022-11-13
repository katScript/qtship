<template>
    <div class="login-page">
        <div class="container-fluid">
            <div :class="isLoading ? 'show' : 'hide'">
                <ActionLoading />
            </div>
            <div class="row">
                <div class="col-md-6 h-100">
                    <img src="@/images/logo-branch.png" class="w-50 h-auto mx-auto d-block align-middle branch-logo"
                        alt="Branch logo" />
                    <br />
                </div>
                <div class="col-md-6 h-100">
                    <div class="form-forget-password w-50 h-auto mx-auto d-block align-middle">
                        <h5 class="">Nhập mật khẩu mới:</h5>
                        <hr>
                        <div class="step-forget">
                            <div class="mb-3 step-1">
                                <label for="input-account-login" class="form-label label-qt">Mật khẩu mới</label> <small
                                    class="text-danger">{{msgValidation.passwordReset}}</small>
                                <input type="text" class="form-control" id="input-username-forget"
                                    v-model="passwordReset" />
                                <label for="input-account-login" class="form-label label-qt">Nhập lại khẩu mới</label>
                                <input type="text" class="form-control" id="input-username-forget"
                                    v-model="confirmPasswordReset" />
                            </div>
                        </div>
                        <br>
                        <div class="mb-3 text-center">
                            <a href="/login-page" class="a-forget-password">Quay về Đăng nhập?</a>
                            <br />
                            <br />
                            <button type="submit" class="btn btn-danger btn-login-qt" v-on:click="reset()">
                                Xác nhận
                            </button>
                            <br />
                            <br />
                            <p>
                                Bạn chưa có tài khoản,
                                <a href="/register-page" class="a-register-account">Đăng ký ngay!</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import ActionLoading from "@/components/common/ActionLoading.vue";

    import axios from "axios";
    import { commonFunction } from "@/scripts/ulti";

    export default {
        components: {
            ActionLoading
        },
        data() {
            return {
                isLoading: false,
                msgValidation: {
                    passwordReset: ""
                },
                passwordReset: "",
                confirmPasswordReset: ""
            }
        },
        mounted() {

        },
        methods: {
            reset: function () {
                if (this.passwordReset == "" || this.confirmPasswordReset == "") {
                    this.msgValidation.passwordReset = "Vui lòng điền mật khẩu!";
                } else if ((!commonFunction.regexStringValidate(this.passwordReset, "PASSWORD"))) {
                    this.msgValidation.passwordReset = "Số điện thoại có tối đa 11 chữ số và không tồn tại chữ cái!";
                } else if (this.passwordReset != this.confirmPasswordReset) {
                    this.msgValidation.passwordReset = "Mật khẩu nhập lại không trùng khớp!";
                } else {
                    this.isLoading = true;
                    this.msgValidation.passwordReset = "";
                    axios.post(commonFunction.DOMAIN_URL + "v1/customer/auth/reset",
                        {
                            token: this.$route.params.token,
                            password: this.passwordReset
                        }
                    ).then((response) => {
                        console.log(response.data);
                        alert("SUCCESS: " + response.data.message + "- Thay đổi mật khẩu thành công! Quay lại đăng nhập!")
                            commonFunction.redirect("/login-page");
                            this.isLoading = false;
                    }).catch((error) => {
                        alert("ERROR: " + error.reponse.data.message + "- Đã có lỗi xảy ra. Vui lòng thử lại hoặc liên hệ với quản trị viên!");
                        console.log(error);
                        this.isLoading = false;
                    });
                }
            }
        },
    }
</script>

<style scoped>
    .form-forget-password {
        padding-top: 30vh;
    }
</style>