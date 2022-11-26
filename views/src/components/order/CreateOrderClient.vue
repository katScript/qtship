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
                        <CreateOrder />
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
    import CreateOrder from "@/pages/admin/order/CreateOrder.vue";
    import NavbarClient from "@/components/common/NavbarClient.vue";
    import FooterClient from "@/components/common/FooterClient.vue";
    import ToolbarRight from "@/components/common/ToolbarRight.vue";
    import NotificationClient from "@/components/common/NotficationClient.vue";
    export default {
        components: {
            CreateOrder,
            NavbarClient,
            FooterClient,
            ToolbarRight,
            NotificationClient
        }
    }
</script>
<style src="./css/create-order.css" />