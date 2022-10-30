<template>
  <div class="login-page">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-6 h-100">
          <img src="../images/logo-branch.png" class="w-50 h-auto mx-auto d-block align-middle branch-logo"
            alt="Branch logo" />
          <br />
        </div>
        <div class="col-md-6">
          <div class="div-form-login">
            <div class="w-75 mx-auto">
              <div class="mb-3">
                <label for="input-account-login" class="form-label label-qt">Email đăng nhập</label>
                <small class="notify-message">{{ msgError }}</small>
                <input type="text" class="form-control" id="input-account-login" v-model="username" required />
              </div>
              <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label label-qt">Password</label>
                <input type="password" class="form-control" id="input-password-login" v-model="password" required />
              </div>
              <br>
              <div class="mb-3 text-center">
                <a href="#" class="a-forget-password">Bạn quên mật khẩu?</a>
                <br />
                <br />
                <button type="" class="btn btn-danger btn-login-qt" v-on:click="login()">
                  Đăng nhập
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
  </div>
</template>

<script>
  import { useCookies } from "vue3-cookies";
  import { commonFunction } from "../scripts/ulti";

  import axios from "axios";
  export default {
    setup() {
      const { cookies } = useCookies();
      return {
        cookies
      };
    },
    components: {},
    data() {
      return {
        username: "",
        password: "",
        msgError: "",
        isValid: 0,
        loading: false,
      };
    },
    computed: {
    },
    mounted() {
    },
    methods: {
      changeTypeFlag(flag) {
        this.$store.dispatch('changeTypeFlagLogin', flag)
      },
      login: function () {
        this.isValid = 0;
        this.loading = true;
        this.validationForm();
        if (this.isValid == 0) {
          axios
            .post(commonFunction.DOMAIN_URL + "v1/customer/auth/login", {
              username: this.username,
              password: this.password,
            })
            .then((response) => {
              let resData = response.data;
              console.log(resData);
              if (
                resData.accessToken
              ) {
                let authenCookie = resData.username;
                let authenRole = resData.roles;
                let tokenCookie = resData.accessToken;
                let idRequest = resData.id;

                this.cookies.set(
                  "accesstoken_cookies",
                  tokenCookie,
                  commonFunction.EXPIRED_TIME_COOKIES
                );
                this.cookies.set(
                  "idrequest_cookies",
                  idRequest,
                  commonFunction.EXPIRED_TIME_COOKIES
                );
                this.cookies.set(
                  "authenrole_cookies",
                  authenRole,
                  commonFunction.EXPIRED_TIME_COOKIES
                );
                this.cookies.set(
                  "authenication_cookies",
                  authenCookie,
                  commonFunction.EXPIRED_TIME_COOKIES
                );
                commonFunction.redirect("/");
              } else {
                commonFunction.redirect("/login-page");
              }
            })
            .catch((error) => {
              alert("Thông tin đăng nhập không chính xác. Vui lòng thử lại!");
              console.log(error);
            });
        } else {
          alert("Vui lòng điền đầy đủ thông tin đăng nhập!");
        }
      },
      validationForm: function () {
        this.errorValidation = [];
        if (this.username == "" && !this.username) {
          this.isValid += 1;
          // if(!regexStringValidate(this.username, commonFunction.typeEmail)) {
          //     this.validationUsername.push("Email không đúng định dạng(Ex: example@example.com,...");
          // }
        }
        if (this.password == "" && !this.password) {
          this.isValid += 1;
          // if(!regexStringValidate(this.username, commonFunction.typePassword)) {
          //     this.validationUsername.push("Mật khẩu từ 7 đến 15 ký tự trong đó có ít nhất một chữ số và một ký tự đặc biệt");
          // }
        }
      },
      resetValidation: function () { },
    },
  };
</script>