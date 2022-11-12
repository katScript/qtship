<template>
    <div class="admin-management">
        <div class="container-fluid d-flex m-0 p-0">
            <NavbarLeftAdmin />
            <div class="content-managment">
                <h4 class="title-management">
                    Danh sách khách hàng
                </h4>
                <span><b>Tổng số: </b> {{countCustomers}}</span>
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
                            <button class="btn btn-danger me-1">Tìm kiếm</button> <button
                                class="btn btn-secondary">Reset</button>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="table-list-customer">
                    <easy-data-table :headers="hearderCustomer" :items="customerList"
                        table-class-name="easy-data-table-customize">
                        <template #item-street="item">
                            {{ item.addressSet[0].street }}
                        </template>
                        <template #item-ward="item">
                            {{ item.addressSet[0].ward }}
                        </template>
                        <template #item-district="item">
                            {{ item.addressSet[0].district }}
                        </template>
                        <template #item-province="item">
                            {{ item.addressSet[0].province }}
                        </template>
                        <template #item-createdAt="item">
                            {{ item.createdAt }}
                        </template>
                        <template #item-updatedAt="item">
                            {{ item.updatedAt }}
                        </template>
                        <template #item-btn-function-customer-management="">
                            <div class="d-flex">
                                <a class="btn btn-outline-primary a-function a-detail me-1" data-bs-toggle="tooltip"
                                    data-bs-placement="top" title="Chi tiết"><i class="fa-solid fa-circle-info"></i></a>

                                <a class="btn btn-outline-success a-function a-detail me-1" data-bs-toggle="tooltip"
                                    data-bs-placement="top" title="Cập nhật"><i class="fa-solid fa-file-pen"></i></a>

                                <a class="btn btn-danger a-function a-detail me-1" title="Dừng hoạt động">
                                    <i class="fa-solid fa-trash"></i></a>
                            </div>
                        </template>
                    </easy-data-table>
                    <br>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
    import NavbarLeftAdmin from "@/components/common/NavbarLeftAdmin.vue";
    import CustomerData from "@/components/models/customer/customer-data";

    // import moment from "moment";
    import { commonFunction } from "@/scripts/ulti";
    import axios from "axios";
    import { useCookies } from "vue3-cookies";

    export default {
        components: {
            NavbarLeftAdmin
        },
        setup() {
            const { cookies } = useCookies();
            const customerModel = new CustomerData();
            return {
                cookies,
                customerModel
            };
        },

        data() {
            return {
                customerList: [],
                countCustomers: 0,
                hearderCustomer: [
                    { text: "Mã KH", value: "customerId", sortable: true },
                    { text: "Tên Khách hàng", value: "fullName", sortable: true },
                    { text: "Shop/Cửa hàng/Công ty", value: "companyName", sortable: true },
                    { text: "SĐT", value: "phone" },
                    { text: "Email", value: "email" },
                    { text: "Đường", value: "street" },
                    { text: "Xã/Phường/Thị trấn", value: "ward" },
                    { text: "Quận/Huyện", value: "district" },
                    { text: "Tỉnh/Thành phố", value: "province" },
                    { text: "Ngày tạo", value: "createdAt" },
                    { text: "Cập nhật", value: "updatedAt" },
                    { text: "Chức năng", value: "btn-function-customer-management" },
                ],
            };
        },

        // <data, methods...>

        mounted() {
            // Get customer
            let
                // auth = commonFunction.getCookies(commonFunction.userCookies.username),
                // role = commonFunction.getCookies(commonFunction.userCookies.roles),
                id = commonFunction.getCookies(commonFunction.userCookies.id),
                token = commonFunction.getCookies(commonFunction.userCookies.token);

            // if (auth == null && role !== "customer") {
            //     commonFunction.redirect("/");
            // }

            this.configRequestApi = {
                headers: { Authorization: "Bearer " + token },
            };

            this.idRequest = id;

            axios
                .get(
                    commonFunction.DOMAIN_URL + "v1/customer/all",
                    this.configRequestApi
                )
                .then((response) => {
                    let respronseData = response.data;
                    respronseData.forEach(element => {
                        let newCustomer = new CustomerData();
                        newCustomer.setData(element);
                        this.customerList.push(newCustomer.getData());
                    });
                    this.countCustomers = this.customerList.length;
                })
                .catch((e) => {
                    console.log(e);
                });
        },
        watch: {
        },
    };
</script>

<style scoped>
    * {
        overflow-x: hidden;
    }
</style>