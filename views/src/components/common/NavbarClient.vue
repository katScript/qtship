<template>
    <div class="container navbar-client">
        <nav class="navbar navbar-expand-lg justify-content-between">
            <a href="/">
                <img class="navbar-brand" src="../../images/logo-branch.png" width="100" />
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa-solid fa-plus"></i>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent" v-if="!isShipper">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/customer/management"><i class="fa-solid fa-chart-pie"></i> Tổng
                            quan</a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/customer/orders"><i class="fa-solid fa-clipboard-list"></i> Đơn
                            hàng</a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/customer/warehouse-product"><i class="fa-solid fa-boxes-stacked"></i> QL
                            kho & sản phẩm</a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/customer/management-liquidity"><i
                                class="fa-solid fa-money-bill-transfer"></i> QL dòng tiền</a>
                    </li>
                    <li class="nav-item nav-item-qt nav-item-qt-button">
                        <a class="nav-link" href="/customer/order/create"><i class="fa-solid fa-circle-plus"></i> Tạo đơn
                            hàng</a>
                    </li>
                </ul>
            </div>
            <div class="collapse navbar-collapse" id="navbarSupportedContent" v-else-if="isShipper">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/shipper/management"><i class="fa-solid fa-home"></i> Trang chủ</a>
                    </li>
                    <li class="nav-item nav-item-qt d-flex">
                        <a class="nav-link" href="/shipper/account-info"><i class="fa-solid fa-user"></i> {{usernameSession}}</a><a
                            class="nav-link mt-1" style="font-size: 12px; color: #4cd137;" disable><i
                                class="fa-solid fa-circle" style="font-size: 8px"></i> Đang hoạt động</a>
                    </li>
                    <li class="nav-item nav-item-qt d-flex">
                        <a class="nav-link" href="#" v-on:click="signOut()"><i
                                class="fa-solid fa-right-from-bracket"></i> Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>

<script>
    import { useCookies } from "vue3-cookies";
    import { commonFunction } from '@/scripts/ulti'
    export default {
        setup() {
            const { cookies } = useCookies();
            return { cookies };
        },
        data() {
            return {
                usernameSession: ""
            }
        },
        mounted() {
            this.usernameSession = this.cookies.get("authenication_cookies");
        },
        components: {
        },
        props: ['isShipper'],
        methods: {
            signOut: function () {
                commonFunction.signOut();
            },
        },
    }
</script>