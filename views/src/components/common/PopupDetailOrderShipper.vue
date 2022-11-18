<template>
    <div class="popup-detail-order-shipper w-100 mb-1">
        <button class="btn btn-close-popup text-danger" v-on:click="closePopup"><i class="fa-solid fa-xmark"></i></button>
        <div class="order-detail">
            <label for="">Mã đơn hàng: </label> <span>{{orderDetail.orderCode}}</span>
        </div>
    </div>
</template>

<script>
export default {
    props: ['orderDetail'],
    data (){
        return {

        }
    },
    methods : {
        closePopup: function() {
            this.$emit('isCloseDetail', false);
        }
    }
}
</script>

<style scoped>
  .popup-detail-order-shipper{
    height: 400px;
  }

  .show {
    display: block;
  }

  .hide {
    display: none;
  }

  .btn-close-popup {
    font-size: 30px;
    float: right;
  }
</style>