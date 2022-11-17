<template>
  <div class="container-fluid  list-order-shipper p-0">
    <br>
    <div class="row">
      <div class="col-4">
        <label for="" class="label-control">Loại đơn hàng: </label>
      </div>
      <div class="col-8">
        <select class="form-select" aria-label="Default select example">
          <option value="ALL">Tất cả</option>
          <option value="SUCCESS">Đã giao</option>
          <option value="PAYMENTED">Đã thanh toán</option>
          <option value="DELAY">Delay giao hàng</option>
          <option value="OCCURRED">Đơn phát sinh</option>
          <option value="RETURN">Đơn hoàn</option>
        </select>
      </div>
    </div>
    <div class="row">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đơn đã xử lí
        <span>(Tổng số: 10 ĐH)</span>
      </h5>
      <div class="col-12">
        <div class="row d-flex justify-content-end">
          <div class="col-11 mb-1">
            <input
              type="text"
              class="form-control"
              placeholder="Mã ĐH hoặc Sđt người nhận"
            />
          </div>
          <div class="col-1">
            <button class="btn btn-danger">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </div>
        </div>
        <easy-data-table :headers="headersOrder" :items="listOrderComplete" v-model:items-selected="itemsSelected">
          <template #item-name-receiver="item">
            {{ item.orderItem[0].shippingAddress.name }}
          </template>
          <template #item-phone-receiver="item">
            {{ item.orderItem[0].shippingAddress.phone }}
          </template>
          <template #item-address-receiver="item">
            {{ buildAddressReceriver(item) }}
          </template>
        </easy-data-table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      listOrderComplete: [],
        listOrderCompleteBk: [],
        itemsSelected: [],
        headersOrder: [
          { text: "Mã ĐH", value: "orderCode", sortable: true },
          { text: "Người nhận", value: "name-receiver", sortable: true,  width: 120},
          { text: "SĐT nhận", value: "phone-receiver" },
          { text: "Địa chỉ giao hàng", value: "address-receiver" ,  width: 250},
        ],
    };
  },
  watch: {
  },
};
</script>

<style scoped>
    @media screen and (max-width: 992px) {
        .btn-detail-order {
            font-size: 10px;
            padding-right: 1px;
            padding-left: 1px;
            width: 45px;
        }
    }
</style>