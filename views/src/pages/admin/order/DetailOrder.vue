<script setup>
import { defineProps, defineEmits } from 'vue';

defineProps({
  openDetail: {
    type: Boolean,
    default: false
  },
  detail: {
    type: Object,
    required: true
  }
})

const emits = defineEmits(['on-close-detail'])

const handleClose = () => {
  emits('on-close-detail')
}
</script>
<template>
  <a-modal :visible="openDetail" title="Chi tiết đơn hàng" width="80%" style="top: 20px;" @cancel="handleClose">
    <div class="content">
      <div class="border">
        <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin đơn hàng</div>
        <div class="p-3">
          <div class="">
            <span class="font-weight-bold">Tổng tiền: </span>{{ detail?.subtotal }}
          </div>
          <div class="">
            <span class="font-weight-bold">Tổng khối lượng: </span>{{ detail?.totalWeight }}
          </div>
          <div class="">
            <span class="font-weight-bold">Trạng thái: </span>{{ detail?.status }}
          </div>
          <div class="">
            <span class="font-weight-bold">Ghi chú: </span>{{ detail?.note }}
          </div>
          <div class="">
            <span class="font-weight-bold">Phản hồi: </span>{{ detail?.feedback }}
          </div>
          <div class="">
            <span class="font-weight-bold">Mã giảm giá: </span>{{ detail?.coupon }}
          </div>
          <div class="">
            <span class="font-weight-bold">Phí vận chuyển: </span>{{ detail?.shippingFee }}
          </div>
          <div class="">
            <span class="font-weight-bold">Ngày tạo : </span>{{ detail?.createdAt }}
          </div>
          <div class="">
            <span class="font-weight-bold">Ngày cập nhật: </span>{{ detail?.updatedAt }}
          </div>
        </div>
      </div>
      <a-row type="flex" class="my-4" :gutter="[10, 10]" align="top">
        <a-col :span="8">
          <div class="border">
            <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin gửi</div>
            <div class="p-3">
              <div><span class="font-weight-bold">Mã đơn hàng: </span>{{ detail?.orderCode }}</div>
              <div><span class="font-weight-bold">Mã khách hàng: </span>{{ detail?.customerCode }}</div>
              <div><span class="font-weight-bold">Tên người gửi: </span>{{ detail?.senderName }}</div>
              <div><span class="font-weight-bold">Số điện thoại: </span>{{ detail?.senderPhone }}</div>
              <div><span class="font-weight-bold">Địa chỉ: </span>{{ detail?.senderAddress }}</div>
            </div>
          </div>
        </a-col>
        <a-col :span="8">
          <div class="border">
            <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin giao hàng</div>
            <div class="p-3">
              <div><span class="font-weight-bold">Mã shipper: </span>{{ detail?.shipper?.shipperCode }}</div>
              <div><span class="font-weight-bold">Tên shipper: </span>{{ detail?.shipper?.fullName }}</div>
              <div><span class="font-weight-bold">Số điện thoại: </span>{{ detail?.shipper?.phone }}</div>
              <div><span class="font-weight-bold">Địa chỉ email: </span>{{ detail?.shipper?.email }}</div>
              <div><span class="font-weight-bold">Dịch vụ giao hàng: </span>{{ detail?.shippingType }}</div>
            </div>
          </div>
        </a-col>
        <a-col :span="8">
          <div class="border">
            <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin nhận</div>
            <div class="p-3">
              <div><span class="font-weight-bold">Tên người nhận: </span>{{ detail?.shippingAddress?.name }}</div>
              <div><span class="font-weight-bold">Số điện thoại: </span>{{ detail?.shippingAddress?.phone }}</div>
              <div><span class="font-weight-bold">Tỉnh / thành phố: </span>{{ detail?.shippingAddress?.province }}</div>
              <div><span class="font-weight-bold">Quận / huyện: </span>{{ detail?.shippingAddress?.district }}</div>
              <div><span class="font-weight-bold">Đường - xã / phường: </span>{{ detail?.shippingAddress?.street }} - {{
                  detail?.shippingAddress?.ward
              }}</div>
            </div>
          </div>
        </a-col>
      </a-row>

      <div class="border">
        <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin sản phẩm</div>
        <div class="p-3">
          <a-list item-layout="vertical" :data-source="detail?.products">
            <template #renderItem="{ item }">
              <a-list-item key="item.title">
                <template #extra>
                  <img width="200" :src="item?.image" />
                </template>
                <a-list-item-meta>
                  <template #title>
                    <div class="fs-6">{{ item?.name }}</div>
                  </template>
                </a-list-item-meta>
                <div class="">
                  <span class="font-weight-bold">Số lượng: </span>{{ item?.qty }}
                </div>
                <div class="">
                  <span class="font-weight-bold">Giá tiền: </span>{{ item?.price }}
                </div>
                <div class="">
                  <span class="font-weight-bold">Khối lượng: </span>{{ item?.weight }}
                </div>
                <div class="">
                  <span class="font-weight-bold">Tổng giá tiền: </span>{{ item?.qty * item?.price }}
                </div>
                <div class="">
                  <span class="font-weight-bold">Tổng khối lượng: </span>{{ item?.qty * item?.weight }}
                </div>
              </a-list-item>
            </template>
          </a-list>
        </div>
      </div>
    </div>
    <template #footer>
      <a-button type="primary" @click="handleClose">Đóng</a-button>
    </template>
  </a-modal>
</template>

<style scoped>
.content {
  height: 70vh;
  overflow-y: scroll;
  overflow-x: hidden;
}
</style>