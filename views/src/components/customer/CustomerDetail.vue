<template>
    <div class="management-client-page">
        <NavbarClient />
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <NotficationClient />
                </div>
                <div class="col-md-8">
                    <div class="management-account-info">
                        <div class="row">
                            <div class="col-12">
                                <h4><i class="fa-solid fa-user"></i> Thông tin tài khoản</h4>
                                <br>
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="">Mã người dùng</label>
                                <p>{{ customerData.data.customerId }}</p>
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="">Họ và tên</label>
                                <input type="text" class="form-control" id=" " v-model="customerData.data.fullName">
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="">Email</label>
                                <p>{{customerData.data.email}}</p>
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="">Số điện thoại</label>
                                <input type="text" class="form-control" id=" " v-model="customerData.data.phone">
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-9 form-group">
                                        <label for="">Mật khẩu</label>
                                        <p>*********</p>
                                    </div>
                                    <div class="col-3 d-flex justify-content-end mt-4">
                                        <button class="btn btn-outline-danger"
                                            :class="!isDisplayFormChangePass ? 'show' : 'hide'"
                                            v-on:click="isDisplayFormChangePass = !isDisplayFormChangePass">Đổi mật
                                            khẩu</button>
                                        <button class="btn btn-danger"
                                            :class="isDisplayFormChangePass ? 'show' : 'hide'"
                                            v-on:click="cancelChangePassword">Hủy
                                            bỏ</button>
                                    </div>
                                    <div class="col-9 form-change-passwword"
                                        :class="isDisplayFormChangePass ? 'show' : 'hide'">
                                        <small class="text-danger">{{msgValidate.newPassword}}</small>
                                        <input type="password" class="form-control" id=" " placeholder="Mật khẩu cũ"
                                            v-model="passwordObj.oldPassword" style="margin-bottom: 3px;">
                                        <input type="password" class="form-control" id=" " placeholder="Mật khẩu mới"
                                            v-model="passwordObj.newPassword" style="margin-bottom: 3px">
                                        <input type="password" class="form-control" id=" "
                                            v-model="passwordObj.confirmNewPassword" placeholder="Xác nhận mật khẩu mới"
                                            style="margin-bottom: 3px">
                                        <button class="btn btn-danger" v-on:click="changePassword">Xác nhận đổi</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <br><br>
                                <h5>Giấy tờ xác minh</h5>
                                <p>Chứng minh nhân dân</p>
                                <div class="row">
                                    <div class="col-md-6 form-group" v-if="!customerData.data.cidFront">
                                        <label for="">Mặt trước</label>
                                        <input type="file" ref="cidFrontImgUpload" class="form-control"
                                            placeholder="CMND mặt trước">
                                    </div>
                                    <div class="col-md-6 form-group" v-if="!customerData.data.cidBack">
                                        <label for="">Mặt sau</label>
                                        <input type="file" ref="cidBackImgUpload" class="form-control"
                                            placeholder="CMND mặt sau">
                                    </div>
                                    <div class="col-md-6 form-group" v-if="customerData.data.cidFront">
                                        <span> <i>(*) Để thay đổi hình ảnh CMND/CCCD, bạn cần liên hệ
                                                bộ phận CSKH. Để được hỗ trợ, gửi mail tới cskh@ghtk.vn hoặc Chat với
                                                CSKH</i></span>
                                        <label for="">Mặt trước</label>
                                        <img src="@/images/img-default.jpg" alt="CMND mặt trước">
                                    </div>
                                    <div class="col-md-6 form-group" v-if="customerData.data.cidBack">
                                        <label for="">Mặt sau</label>
                                        <img src="@/images/img-default.jpg" alt="CMND mặt sau">
                                    </div>
                                </div>
                                <br>
                                <br>
                            </div>

                            <div class="d-flex justify-content-center">
                                <button class="btn btn-success"><i class="fa-solid fa-floppy-disk"></i> Lưu thông
                                    tin</button>
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
    import NavbarClient from "@/components/common/NavbarClient.vue";
    import FooterClient from "@/components/common/FooterClient.vue";
    import ToolbarRight from "@/components/common/ToolbarRight.vue";
    import NotficationClient from "@/components/common/NotficationClient.vue";
    import CustomerData from "@/components/models/customer/customer-data";

    import { useCookies } from "vue3-cookies";
    import { commonFunction } from '@/scripts/ulti'
    import axios from "axios";

    export default {
        setup() {
            let auth = commonFunction.getCookies(commonFunction.userCookies.username),
                role = commonFunction.getCookies(commonFunction.userCookies.roles),
                id = commonFunction.getCookies(commonFunction.userCookies.id),
                token = commonFunction.getCookies(commonFunction.userCookies.token);
            const { cookies } = useCookies();
            const customerModel = new CustomerData();
            return {
                customerModel,
                cookies,
                auth,
                role,
                id,
                token,
            };
        },

        components: {
            NavbarClient,
            FooterClient,
            ToolbarRight,
            NotficationClient
        },
        data() {
            return {
                isDisplayFormChangePass: false,
                customerData: {},
                passwordObj: {
                    oldPassword: "",
                    newPassword: "",
                    confirmNewPassword: ""
                },
                msgValidate: {
                    newPassword: ""
                },
                configRequestApi: {},
            };
        },

        // <data, methods...>

        created() {
            this.customerModel.setData(
                JSON.parse(commonFunction.getCustomerStorage())
            );

            this.customerData = this.customerModel;

            let customerId = this.customerModel.getData().id;
            console.log(customerId);

            // can update
            if (this.auth == null || this.role !== "customer") {
                commonFunction.redirect("/");
            }

            this.configRequestApi = {
                headers: { Authorization: "Bearer " + this.token },
            };
        },
        watch: {
        },
        methods: {
            changePassword: function () {
                if (this.passwordObj.newPassword == "" || this.passwordObj.confirmNewPassword == "" || this.passwordObj.oldPassword == "") {
                    this.msgValidate.newPassword = "Vui lòng điền đầy đủ thông tin!"
                } else if (this.passwordObj.newPassword != this.passwordObj.confirmNewPassword) {
                    this.msgValidate.newPassword = "Mật khẩu mới và Xác nhận mật khẩu mới không khớp!"
                } else {
                    axios
                        .post(
                            commonFunction.DOMAIN_URL + "api/auth/reset/password",
                            {
                                username: this.customerData.data.userName,
                                password: this.passwordObj.oldPassword,
                                newPassword: this.passwordObj.newPassword
                            },
                            this.configRequestApi
                        )
                        .then((response) => {
                            this.isLoading = false;
                            alert("SUCCESS: " + response.data.message + " - Đổi mật khẩu thành công!");
                            commonFunction.signOut();
                        })
                        .catch((e) => {
                            this.isLoading = false;
                            this.msgValidate.newPassword = "Đổi mật khẩu không thành công! Vui lòng kiểm tra lại thông tin!"
                            console.log(e);
                        });
                }
            },
            cancelChangePassword: function() {
                this.passwordObj = {
                    oldPassword: "",
                    newPassword: "",
                    confirmNewPassword: ""
                },
                this.msgValidate = {
                    newPassword: ""
                },
                this.isDisplayFormChangePass = false;
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
</style>