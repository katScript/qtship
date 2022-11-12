<template>
    <div class="admin-management">
        <div class="container-fluid d-flex m-0 p-0">
            <button class="btn btn-collapse-nav-left" :class="isShowNav ? 'hide' : 'show'"
                v-on:click="toggleCollapseNavbarLeft">
                <i class="fa-solid fa-caret-right"></i>
            </button>
            <div class="navbar-left" style="width: 250px" :class="isShowNav ? 'show' : 'hide'">
                <NavbarLeftAdmin @toggleCollapseNavbarLeft="toggleCollapseNavbarLeft" />
            </div>
            <div class="content-managment">
                <h1>Create order</h1>
            </div>
        </div>
    </div>
</template>

<script src="@/components/admin/create-order/js/create-order" />

<style scoped>

</style>