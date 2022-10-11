<template>
    <div class="management-client-page">
        <NavbarClient />
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <VueChartPie />
                </div>
                <div class="col-md-6">
                    <VueChartLine />
                </div>
        </div>
        </div>
        <FooterClient />
    </div>
</template>

<script>
    import NavbarClient from './common/NavbarClient.vue';
    import FooterClient from './common/FooterClient.vue';
    import VueChartPie from './common/VueChartPie.vue';
    import VueChartLine from './common/VueChartLine.vue';
    export default {
        components: {
            NavbarClient,
            FooterClient,
            VueChartPie,
            VueChartLine
        }
    }
</script>