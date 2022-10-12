<template>
    <div class="container navbar-client">
        <nav class="navbar navbar-expand-lg justify-content-between">
            <a href="/client/management">
                <img class="navbar-brand" src="../../images/logo-branch.png" width="100" />
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse d-flex justify-content-between" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="#"><i class="fa-solid fa-chart-pie"></i> Tổng quan <span
                                class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="#"><i class="fa-solid fa-clipboard-list"></i> Đơn hàng</a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="#"><i class="fa-solid fa-boxes-stacked"></i> QL sản phẩm</a>
                    </li>
                    <li class="nav-item nav-item-qt">
                        <a class="nav-link" href="#"><i class="fa-solid fa-money-bill-transfer"></i> QL dòng tiền</a>
                    </li>
                    <li class="nav-item nav-item-qt nav-item-qt-button">
                        <a class="nav-link" href="#"><i class="fa-solid fa-circle-plus"></i> Tạo đơn hàng</a>
                    </li>
                </ul>
                <div class="form-inline">
                    <li class="nav-item nav-item-login">
                        <a href=""><i class="fa-solid fa-user-gear"></i> <span class="span-username">DuongPH</span></a>
                    </li>
                </div>
            </div>
        </nav>
    </div>
</template>

<script>
    export default {
        components: {
        }
    }
</script>