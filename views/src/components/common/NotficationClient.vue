<template>
    <div class="notifycation-client">
        <ul class="list-group">
            <a v-on:click="isDisplay = !isDisplay" class="
                list-group-item list-group-item-action list-group-page-qt
                title-group-item-qt
              "><i class="fa-solid fa-bell" style="float: left; padding-right: 10px"></i>
                Thông báo</a>
        </ul>
        <div class="list-group" :class="!isDisplay ? 'hide' : 'show'">
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt bg-unread">
                <b>Hệ thống: </b> Vui lòng cập nhật đầy đủ thông tin tài khoản của bạn!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt bg-unread">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt bg-unread">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
                <b>Hệ thống: </b> Đơn hàng <b>MH123123</b> đã được xác nhận!
            </a>

        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                isDisplay : false,
            }
        },
    }
</script>

<style scoped>
    .list-group {
        border-radius: 0 !important;
    }
    .bg-unread{
        background-color: #dff9fb;
    }
    .hide{
        display: none;
    }
    .show{
        display: inline;
    }
</style>