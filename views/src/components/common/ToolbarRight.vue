<template>
    <div class="subtab-area" style="height: 100vh">
        <div class="subtab-area-group">
            <ul class="list-group">
                <a href="#" class="
            list-group-item list-group-item-action list-group-page-qt
            title-group-item-qt
          "><i class="fa-solid fa-user" style="float: left; padding-right: 10px"></i>
                    {{ usernameSession }}
                    <i class="fa-solid fa-circle" style="color: #4cd137; font-size: 10px; padding-top: 3px"></i></a>
            </ul>
            <div class="list-group">
                <a href="/customer/account-info" class="list-group-item list-group-item-action list-group-page-qt">
                    Quản lí thông tin tài khoản <i class="fa-solid fa-chevron-right"></i>
                </a>
            </div>
        </div>
        <div class="subtab-area-group">
            <ul class="list-group">
                <a href="#" class="
            list-group-item list-group-item-action list-group-page-qt
            title-group-item-qt
          ">Quản lí đơn hàng</a>
            </ul>
            <div class="list-group">
                <a href="/customer/orders/success" class="list-group-item list-group-item-action list-group-page-qt">
                    Giao thành công <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
                <a href="/customer/orders/delivery" class="list-group-item list-group-item-action list-group-page-qt">
                    Đã lấy/Đang giao <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
                <a href="/customer/orders/delay" class="list-group-item list-group-item-action list-group-page-qt">
                    Delay <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
                <a href="/customer/orders/cancel" class="list-group-item list-group-item-action list-group-page-qt">
                    Đơn hủy <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
                <a href="/customer/orders/occurred" class="list-group-item list-group-item-action list-group-page-qt">
                    Không giao được/Lưu kho <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
                <a href="/customer/orders/return" class="list-group-item list-group-item-action list-group-page-qt">
                    Đơn đổi trả (Đơn hoàn) <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
                <a href="/customer/orders/delay" class="list-group-item list-group-item-action list-group-page-qt">
                    Delay do sai thông tin ĐH & chờ kiểm tra
                    <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
                <a href="/customer/orders/updated" class="list-group-item list-group-item-action list-group-page-qt">
                    Đã chỉnh sửa thông tin <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
                <a href="/customer/orders/waiting_confirm" class="list-group-item list-group-item-action list-group-page-qt">
                    Chưa xử lý <span class="number-item-span"></span>
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
            </div>
        </div>
        <!-- <div class="subtab-area-group">
            <ul class="list-group">
                <a href="#" class="
            list-group-item list-group-item-action list-group-page-qt
            title-group-item-qt
          ">Quản lí kho và sản phẩm</a>
            </ul>
        </div>
        <div class="subtab-area-group">
            <ul class="list-group">
                <a class="
            list-group-item list-group-item-action list-group-page-qt
            title-group-item-qt
          ">Quản lí dòng tiền</a>
            </ul>
        </div> -->
        <div class="subtab-area-group">
            <ul class="list-group"></ul>
            <div class="list-group">
                <a href="#" v-on:click="signOut()" class="list-group-item list-group-item-action list-group-page-qt"
                   style="color: red; font-weight: bold">
                    Đăng xuất <i class="fa-solid fa-chevron-right"></i>
                </a>
            </div>
        </div>
    </div>
</template>

<script>
import {commonFunction} from "@/scripts/ulti";
import CustomerData from "@/components/models/customer/customer-data";

export default {
    setup() {
        let customerModel = new CustomerData();
        return {
            customerModel
        }
    },
    data() {
        return {
            usernameSession: ""
        };
    },
    mounted() {
        let auth = commonFunction.getCookies(commonFunction.userCookies.username);
        if (auth == null) {
            commonFunction.redirect("/");
        }
        
        this.customerModel.setData(
            JSON.parse(commonFunction.getCustomerStorage())
        );

        this.usernameSession = this.customerModel.getData().fullName;
    },
    methods: {
        signOut: function () {
            commonFunction.signOut();
        },
    },
};
</script>