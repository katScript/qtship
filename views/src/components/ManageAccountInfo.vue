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
                                <p>U0010001</p>
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="">Họ và tên</label>
                                <input type="text" class="form-control" id=" " value="Duong Pham Huu">
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="">Email</label>
                                <p>duongph@gmail.com</p>
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="">Số điện thoại</label>
                                <input type="text" class="form-control" id=" " value="123123123">
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-9 form-group">
                                        <label for="">Mật khẩu</label>
                                        <p>*********</p>
                                    </div>
                                    <div class="col-3 d-flex justify-content-end mt-4">
                                        <button class="btn btn-outline-danger" :class="!isDisplayFormChangePass ? 'show' : 'hide'" v-on:click="isDisplayFormChangePass = !isDisplayFormChangePass">Đổi mật khẩu</button>
                                        <button class="btn btn-danger" :class="isDisplayFormChangePass ? 'show' : 'hide'" v-on:click="isDisplayFormChangePass = !isDisplayFormChangePass">Hủy bỏ</button>
                                    </div>
                                    <div class="col-9 form-change-passwword" :class="isDisplayFormChangePass ? 'show' : 'hide'">
                                        <input type="password" class="form-control" id=" " placeholder="Mật khẩu cũ"
                                            style="margin-bottom: 3px;">
                                        <input type="password" class="form-control" id=" " placeholder="Mật khẩu mới"
                                            value="" style="margin-bottom: 3px">
                                        <input type="password" class="form-control" id=" "
                                            placeholder="Xác nhận mật khẩu mới" style="margin-bottom: 3px">
                                        <button class="btn btn-danger">Xác nhận đổi</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <br><br>

                                <h5>Giấy tờ xác minh</h5>
                                <p>Chứng minh nhân dân</p>
                                <div class="row">
                                    <div class="col-md-6 form-group">
                                        <label for="">Mặt trước</label>
                                        <input type="file" class="form-control" id="" placeholder="CMND mặt trước">
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label for="">Mặt sau</label>
                                        <input type="file" class="form-control" id=" " value="CMND mặt sau">
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
    import NavbarClient from "./common/NavbarClient.vue";
    import FooterClient from "./common/FooterClient.vue";
    import ToolbarRight from "./common/ToolbarRight.vue";
    import NotficationClient from "./common/NotficationClient.vue";

    import { useCookies } from "vue3-cookies";
    // import { commonFunction } from '../scripts/ulti'

    export default {
        components: {
            NavbarClient,
            FooterClient,
            ToolbarRight,
            NotficationClient
        },
        data() {
            return {
                isDisplayFormChangePass: false
            };
        },

        setup() {
            const { cookies } = useCookies();
            return { cookies };
        },

        // <data, methods...>

        mounted() {
            // let authenication_cookies = this.cookies.get("authenication_cookies");
            // if(authenication_cookies == null){
            //   commonFunction.redirect('/login-page');
            // } else {
            //     commonFunction.redirect('/client/management');
            // }
        },
        watch: {
        },
        method: {
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