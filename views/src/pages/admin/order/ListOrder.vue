<script setup>
import { list, updateStatus } from "@/services/admin";
import { ref, watch } from "vue";
import { useRoute } from 'vue-router';
import { EyeOutlined, EditOutlined, DeleteOutlined } from "@ant-design/icons-vue";
import { message } from 'ant-design-vue';
import common from "@/utils/common";

const route = useRoute();

const listOrder = ref([]);

const getListOrder = async () => {
  if (route.query?.status) {
    const { data } = await list({ status: route.query.status });
    listOrder.value = data;
  } else {
    const { data } = await list();
    listOrder.value = data;
  }
}

// const setStatus = async (record, code) => {
//   await updateStatus([{ ...record, status: code }]);
//   message.success('Xóa đơn hàng thành công');
// }

const setStatus = async (id, code) => {
  await updateStatus({ id: id, status: code });
  message.success('Xóa đơn hàng thành công');
  getListOrder();
}
//
getListOrder();
watch(() => route.query?.status, () => getListOrder());

const columns = [
  {
    title: 'Mã ĐH',
    dataIndex: 'customerCode',
    key: 'customerCode',
  },
  {
    title: 'Tên người nhận',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: 'SĐT nhận',
    dataIndex: 'phone',
    key: 'phone',
  },
  {
    title: 'Trạng thái ĐH',
    dataIndex: 'status',
    key: 'shipping_status',
  },
  {
    title: 'Dịch vụ giao hàng',
    dataIndex: 'shippingType',
    key: 'shipping_type',
  },
  {
    title: 'Mã người giao hàng',
    dataIndex: 'shipper_code',
    key: 'shipper_code',
  },
  {
    title: 'Tên người giao hàng',
    dataIndex: 'shipper_name',
    key: 'shipper_name',
  },
  {
    title: 'Tổng giá trị ĐH (VNĐ)',
    dataIndex: 'subtotal',
    key: 'sub_total',
  },
  {
    title: 'Ngày tạo đơn',
    dataIndex: 'createdAt',
    key: 'created_at',
  },
  {
    title: 'Chức năng',
    dataIndex: 'action',
    key: 'action',
    width: '200px',
  }
]
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">Tất cả đơn hàng</div>
    <a-table :dataSource="listOrder" :columns="columns" class="border">
      <template #bodyCell="{ column, record, text }">
        <template v-if="column.key === 'name'">
          {{ record.shippingAddress.name }}
        </template>
        <template v-if="column.key === 'phone'">
          {{ record.shippingAddress.phone }}
        </template>
        <template v-if="column.key === 'shipping_status'">
          {{ text }}
        </template>
        <template v-if="column.key === 'shipping_type'">
          {{ text }}
        </template>
        <template v-if="column.key === 'shipper_code'">
          {{ record.shipper.shipperCode }}
        </template>
        <template v-if="column.key === 'shipper_name'">
          {{ record.shipper.fullName }}
        </template>
        <template v-if="column.key === 'action'">
          <div class="d-flex justify-content-between">
            <button type="button" class="btn btn-primary"><EyeOutlined /></button>
            <button type="button" class="btn btn-secondary"><EditOutlined /></button>
            <a-popconfirm
              title="Bạn có muốn xóa đơn hàng này không ?"
              ok-text="Có"
              cancel-text="Không"
              @confirm="setStatus(record.id, common.typeOrderCancel)"
            >
            <button type="button" class="btn btn-danger"><DeleteOutlined /></button>
          </a-popconfirm>
          </div>
        </template>
      </template>
    </a-table>
  </div>
</template>