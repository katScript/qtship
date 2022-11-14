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
                        <h5 class="">Quên mật khẩu:</h5>
                        <hr>
                        <div class="step-forget">
                            <div class="mb-3 step-1">
                                <label for="input-account-login" class="form-label label-qt">Email đăng nhập</label>
                                <small class="text-danger">{{msgValidation.username}}</small>
                                <input type="text" class="form-control" id="input-username-forget" v-model="username" />
                            </div>
                        </div>
                        <br>
                        <div class="mb-3 text-center">
                            <a href="/login-page" class="a-forget-password">Quay về Đăng nhập?</a>
                            <br />
                            <br />
                            <button type="submit" class="btn btn-danger btn-login-qt" v-on:click="forget()">
                                Gửi yêu cầu
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
                    username: ""
                },
                username: "",
                token: ""
            }
        },
        mounted() {

        },
        methods: {
            forget: function () {
                if (this.username == "") {
                    this.msgValidation.username = "Vui lòng điền thông tin tài khoản!";
                } else {
                    this.isLoading = true;
                    this.msgValidation.username = "";
                    axios.post(commonFunction.DOMAIN_URL + "api/auth/forget",
                        { username: this.username }
                    ).then((response) => {
                        this.isLoading = false;
                        this.token = response.data.accessToken;
                        //send maill
                        this.isLoading = true;
                        axios.post(commonFunction.DOMAIN_URL + "v1/email/auth/customer/reset",
                            {
                                token: this.token,
                                url: commonFunction.ORIGIN_URL+"/reset-password/",
                                email: this.username
                            }
                        ).then((response) => {
                            console.log(response.data);
                            alert("SUCCESS: " + response.data.message + " - Email đã được gửi. Vui lòng kiểm tra email của bạn!");
                            this.isLoading = false;
                        }).catch((error) => {
                            console.log(error);
                            alert("ERROR: " + error.response.data.message + " - Gửi mail không thành công. Vui lòng kiểm tra và thử lại hoặc liên hệ với quản trị viên!");
                            this.isLoading = false;
                        });

                    }).catch((error) => {
                        alert("ERROR: " + error.response.data.message + " - Thông tin tài khoản không tồn tại!");
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