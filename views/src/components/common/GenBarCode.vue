<template>
  <vue-barcode :value="orderCode" :width="2" :height="39"></vue-barcode>
</template>

<script>
export default {
  props: ['orderCode']
}
</script>