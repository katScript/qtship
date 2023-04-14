<template>
    <div class="management-client-page">
        <NavbarClient />
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <NotificationClient />
                </div>
                <div class="col-md-8">
                    <div class="create-order-area">
                        <FormOrder />
                    </div>
                </div>
                <div class="col-md-2">
                    <ToolbarRight />
                </div>
            </div>
        </div>

        <br />
        <br /><br />
        <FooterClient />
    </div>
</template>
<script>
    // import FormCreateOrder from "@/components/order/FormCreateOrder.vue";
    import FormOrder from "@/components/order/FormOrder.vue";
    import NavbarClient from "@/components/common/NavbarClient.vue";
    import FooterClient from "@/components/common/FooterClient.vue";
    import ToolbarRight from "@/components/common/ToolbarRight.vue";
    import NotificationClient from "@/components/common/NotficationClient.vue";
    import { commonFunction } from "@/scripts/ulti";

    export default {
        components: {
            FormOrder,
            NavbarClient,
            FooterClient,
            ToolbarRight,
            NotificationClient
        },
        setup() {
            let role = commonFunction.getCookies(commonFunction.userCookies.roles),
            auth = commonFunction.getCookies(commonFunction.userCookies.username);  
            return {
                role,
                auth
            }
        },
        mounted() {
            if (this.auth == null || this.role !== "customer") {
                commonFunction.redirect("/");
            }
        },

    }
</script>
<style src="./css/create-order.css" />