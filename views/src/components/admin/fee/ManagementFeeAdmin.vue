<template>
    <div class="admin-management">
        <div class="container-fluid d-flex m-0 p-0">
            <NavbarLeftAdmin />
            <div class="content-managment">
                <h4 class="title-management">
                    Quản lí các loại phí
                </h4>
                <div class="management-fees">
                    <div class="row">
                        <div class="col-md-4">
                            <label for=""></label>
                        </div>
                        <div class="col-md-8">

                        </div>
                    </div>
                </div>
                <hr>
                <h4 class="title-management">
                    Mã khuyến mại
                </h4>
                <div class="management-fees">
                    <div class="row">
                        <div class="col-md-4">
                            <label for="">Mã: </label>
                            <input type="text" class="form-control" name="" id="" placeholder="">

                            <label for="">Loại: </label>
                            <select name="" class="form-control" id="">
                                <option value="">Trừ thẳng</option>
                                <option value="">Phẩn trăm (%)</option>
                            </select>

                            <label for="">Giá trị: </label>
                            <input type="text" class="form-control" name="" id="" placeholder="">

                            <label for="">Mô tả: </label>
                            <textarea type="text" class="form-control" name="" id=""></textarea>

                            <div class="row">
                                <label for="">Thời gian hiệu lực </label>
                                <div class="col-md-6 d-flex">
                                    <p class="me-1">Từ: </p>
                                    <input type="date" class="form-control">
                                </div>
                                <div class="col-md-6 d-flex">
                                    <p class="me-1">Đến: </p>
                                    <input type="date" class="form-control">
                                </div>
                            </div>
                            <br>
                            <center>
                                <button class="btn btn-danger m-auto">Tạo mới</button>
                            </center>
                        </div>
                        <div class="col-md-8">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarLeftAdmin from "@/components/common/NavbarLeftAdmin.vue";

    // import moment from "moment";
    // import { commonFunction } from "@/scripts/ulti";
    // import axios from "axios";
    import { useCookies } from "vue3-cookies";

    export default {
        components: {
            NavbarLeftAdmin,
        },
        setup() {
            const { cookies } = useCookies();
            return {
                cookies,
            };
        },

        data() {
            return {
            };
        },

        // <data, methods...>

        mounted() {

        },
        watch: {
        },
    };
</script>

<style scoped>
    * {
    }
</style>