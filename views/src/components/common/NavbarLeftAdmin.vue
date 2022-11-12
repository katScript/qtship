<template>
    <div class="navbar-admin d-inline">
        <button class="btn btn-collapse-nav-left" :class="isShowNav ? 'hide' : 'show'"
            v-on:click="toggleCollapseNavbarLeft">
            <i class="fa-solid fa-caret-right"></i>
        </button>
        <div class="navbar-left" style="width: 250px" :class="isShowNav ? 'show' : 'hide'">
            <NavbarAdminItem @toggleCollapseNavbarLeft="toggleCollapseNavbarLeft" />
        </div>
    </div>
</template>

<script>
    import NavbarAdminItem from "@/components/common/NavbarAdminItem.vue";

    export default {
        components: {
            NavbarAdminItem,
        },

        data() {
            return {
                isShowNav: true,
            };
        },

        // <data, methods...>

        mounted() {

        },
        watch: {
        },
        methods: {
            toggleCollapseNavbarLeft() {
                this.isShowNav = !this.isShowNav;
            },
        },
    };
</script>