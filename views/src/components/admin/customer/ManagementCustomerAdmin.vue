<template>
    <div class="admin-management">
        <div class="container-fluid d-flex m-0 p-0">
            <NavbarLeftAdmin />
            <div class="content-managment">
                <h4 class="title-management">
                    Danh sách khách hàng
                </h4>
                <span><b>Tổng số: </b> 1000</span>
                <hr>
                <div class="form-filter-customer">
                    <div class="row">
                        <div class="col-md-2">
                            <input type="text" class="form-control" placeholder="Mã Khách hàng">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" placeholder="Tên Khách hàng">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" placeholder="Tên Công ty">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" placeholder="Email">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" placeholder="Số điện thoại">
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-danger me-1">Tìm kiếm</button> <button class="btn btn-secondary">Reset</button>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="table-list-customer">
                    <easy-data-table :headers="hearderCustomer" :items="customerList"
                        table-class-name="easy-data-table-customize">
                    </easy-data-table>
                    <br>
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
                customerList: [],
                hearderCustomer: [
                    { text: "Mã KH", value: "customerId", sortable: true },
                    { text: "Tên Khách hàng", value: "fullName", sortable: true },
                    { text: "Shop/Cửa hàng/Công ty", value: "companyName", sortable: true },
                    { text: "SĐT", value: "phone" },
                    { text: "Email", value: "email"},
                    { text: "Đường", value: "email"},
                    { text: "Xã/Phường/Thị trấn", value: "ward"},
                    { text: "Quận/Huyện", value: "district"},
                    { text: "Tỉnh/Thành phố", value: "province"},
                    { text: "Ngày tạo", value: "createdAt"},
                    { text: "Cập nhật", value: "updatedAt"},
                    { text: "Chức năng", value: "btn-function-order" },
                ],
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

</style>