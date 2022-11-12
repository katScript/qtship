<template>
    <div class="admin-management">
        <div class="container-fluid d-flex m-0 p-0">
            <NavbarLeftAdmin />
            <div class="content-managment">
                <h4 class="title-management">
                    Danh sách khách hàng
                </h4>
                <hr>
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

</style>