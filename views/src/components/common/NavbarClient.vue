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
                        <a class="nav-link" href="/client/management"><i class="fa-solid fa-chart-pie"></i> Tổng quan</a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/client/orders"><i class="fa-solid fa-clipboard-list"></i> Đơn hàng</a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/client/stock-product"><i class="fa-solid fa-boxes-stacked"></i> QL sản phẩm</a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/client/management-cash"><i class="fa-solid fa-money-bill-transfer"></i> QL dòng tiền</a>
                    </li>
                    <li class="nav-item nav-item-qt nav-item-qt-button">
                        <a class="nav-link" href="/client/orders/create"><i class="fa-solid fa-circle-plus"></i> Tạo đơn hàng</a>
                    </li>
                </ul>
            </div>
            <div class="collapse navbar-collapse" id="navbarSupportedContent" v-else-if="isShipper">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="/shipper/management"><i class="fa-solid fa-home"></i> Trang chủ</a>
                    </li>
                    <li class="nav-item nav-item-qt d-flex">
                        <a class="nav-link" href="/shipper/account-info"><i class="fa-solid fa-user"></i> Shipper1</a><a class="nav-link mt-1" style="font-size: 12px; color: #4cd137;" disable><i class="fa-solid fa-circle" style="font-size: 8px"></i> Đang hoạt động</a>
                    </li>
                    <li class="nav-item nav-item-qt d-flex">
                        <a class="nav-link" href=""><i class="fa-solid fa-right-from-bracket"></i> Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>

<script>
    export default {
        components: {
        },
        props: ['isShipper']
    }
</script>