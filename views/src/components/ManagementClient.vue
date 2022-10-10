<template>
    <div class="management-client-page">
        <NavbarClient />

        <FooterClient />
    </div>
</template>

<script>
    import NavbarClient from './common/NavbarClient.vue';
    import FooterClient from './common/FooterClient.vue';
    export default {
        components: {
            NavbarClient,
            FooterClient
        }
    }
</script>