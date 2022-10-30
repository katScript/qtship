<template>

</template>

<script>

  import { useCookies } from "vue3-cookies";
  // import { commonFunction } from '../scripts/ulti'

  export default {
    components: {
    },
    data() {
      return {};
    },

    setup() {
      const { cookies } = useCookies();
      return {
        cookies,
      };
    },

    // <data, methods...>

    mounted() {
    },
    watch: {},
    method: {},
  };
</script>

<style scoped>
  a {
    text-decoration: none;
  }

  table tr td {}

  table tr td a {
    /* font-size: 5px; */
    padding: 2px;
    margin: 0px 3px;
  }

  table tr td a i {
    font-size: 18px;
    padding: 2px 2px 2px 2px;
  }
</style>