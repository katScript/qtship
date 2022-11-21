<script setup>
import { list, updateStatus, transferShipper } from "@/services/admin";
import { list as listShipper } from "@/services/shipper";
import { ref, watch, reactive, computed } from "vue";
import { useRoute } from "vue-router";
import { EyeOutlined, EditOutlined, DeleteOutlined } from "@ant-design/icons-vue";
import { message } from "ant-design-vue";
import common from "@/utils/common";
import FilterOrder from "@/pages/admin/components/FilterOrder.vue"
import DetailOrder from "@/pages/admin/order/DetailOrder.vue"
import { useStore } from "vuex";
import { columns } from "./configOrder";
import moment from "moment";

const store = useStore();
const route = useRoute();

const listOrder = ref([]);
const shipper = ref('');
const listAllShipper = ref([]);
const shipperShow = ref(false);
const listSelectOrder = ref([]);
const openDetail = ref(false);
const detail = ref({});
const filtersSet = ref({});
const pagination = reactive({
  total: null,
  current: null,
  pageSize: null,
})
const rowSelection = ref({
  onSelect: (record, selected, selectedRows) => {
    listSelectOrder.value = selectedRows.map(x => x.id);
    console.log(listSelectOrder.value, record, selected);
  },
  onSelectAll: (selected, selectedRows) => {
    listSelectOrder.value = selectedRows.map(x => x.id);
    console.log(listSelectOrder.value, selected);
  },
  getCheckboxProps: record => ({
    disabled: !common.PROCESSING_TYPE.find(x => x.value == record.status),
  }),
});
const getTitle = computed(() => {
  const title = {
    PENDING: 'Đơn hàng chờ xử lý',
    PROCESSING: 'Đơn hàng đang xử lý',
    DONE: 'Đơn hàng đã xử lý',
    OCCURRED: 'Đơn hàng phát sinh',
    CANCEL: 'Đơn hàng bị hủy',
  }
  return route.query?.status ? title[route.query?.status] : 'Tất cả đơn hàng';
});
//
const handleTransferShipper = async () => {
  const data = listSelectOrder.value.map(order => ({ orderId: order, shipperId: shipper.value }));
  await transferShipper(data);
  message.success('Chuyển giao hàng cho shipper thành công');
  listSelectOrder.value = [];
  handleResetShipper();
  getListOrder();
}
const handleResetShipper = () => {
  shipper.value = '';
  shipperShow.value = false;
}
const getListShipper = async () => {
  const { data } = await listShipper();
  listAllShipper.value = data;
}
const getListOrder = async (filters = {}) => {
  store.dispatch('setLoading', true);
  if (route.query?.status) {
    const { data } = await list({
      ...filters,
      status: route.query.status != common.TYPE_ORDER_PROCESSING ? route.query.status : filters.status,
    });
    listOrder.value = data.content.map(item => ({ key: item.id + Math.random(), ...item }));
    pagination.total = data.totalElements;
    pagination.pageSize = data.size;
    pagination.current = data.number + 1;

  } else {
    const { data } = await list(filters);
    listOrder.value = data.content.map(item => ({ key: item.id + Math.random(), ...item }));
    pagination.total = data.totalElements;
    pagination.pageSize = data.size;
    pagination.current = data.number + 1;
  }
  store.dispatch('setLoading', false);
}
const filterOption = (input, option) => {
  return option.fullName.toLowerCase().indexOf(input.toLowerCase()) >= 0;
};
const setStatus = async (id, code) => {
  await updateStatus({ id: id, status: code });
  message.success('Xóa đơn hàng thành công');
  getListOrder();
}
const handleShowDetail = (record) => {
  openDetail.value = true;
  detail.value = record;
}
const handleCloseDetail = () => {
  openDetail.value = false;
  detail.value = {};
}
const handleChangePage = (page) => {
  getListOrder({ page: page.current - 1, ...filtersSet.value})
}
const handleFind = (value) => {
  const filters = {
    code: value.orderCode ? 'ORD' + value.orderCode : null,
    phone: value.orderPhone || null,
    status: value.orderStatus || null,
    type: value.orderType || null,
    from: null,
    to: null,
  };
  switch (value.orderTime) {
    case 'timeCOToday': 
      filters.from = moment().startOf('day').format('YYYY-MM-DD HH:mm:ss');
      filters.to = moment().endOf('day').format('YYYY-MM-DD HH:mm:ss');
      break;
    case 'timeCO1week': 
      filters.from = moment().startOf('week').format('YYYY-MM-DD HH:mm:ss');
      filters.to = moment().endOf('week').format('YYYY-MM-DD HH:mm:ss');
      break;
    case 'timeCO1month': 
      filters.from = moment().startOf('month').format('YYYY-MM-DD HH:mm:ss');
      filters.to = moment().endOf('month').format('YYYY-MM-DD HH:mm:ss');
      break;
    case 'timeCO1year': 
      filters.from = moment().startOf('year').format('YYYY-MM-DD HH:mm:ss');
      filters.to = moment().endOf('year').format('YYYY-MM-DD HH:mm:ss');
      break;
    // case 'timeCOAll': 
    //   filters.from = moment().startOf('day').format('YYYY-MM-DD HH:mm:ss');
    //   filters.to = moment().endOf('day').format('YYYY-MM-DD HH:mm:ss');
    //   break;
    case 'timeCOTimeAbout': 
      filters.from = moment(value.orderTimeAbout[0].$d).startOf('day').format('YYYY-MM-DD HH:mm:ss');
      filters.to = moment(value.orderTimeAbout[1].$d).endOf('day').format('YYYY-MM-DD HH:mm:ss');
      break;

  }
  delete filters.orderTimeAbout;
  for (const property in filters) {
    if (!filters[property]) {
      delete filters[property];
    }
  }
  filtersSet.value = filters;
  getListOrder(filtersSet.value);
}
//
getListOrder();
getListShipper();
watch(() => route.query?.status, () => getListOrder());

</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">{{ getTitle }}</div>
    <FilterOrder :status="route.query?.status" @on-handle-find="handleFind" />
    <a-popover v-model:visible="shipperShow" title="Chuyển giao hàng" @click="handleResetShipper" trigger="click"
      v-if="!!listSelectOrder.length">
      <template #content>
        <a-select
          v-model:value="shipper"
          style="width: 250px"
          show-search
          :filter-option="filterOption"
          placeholder="Select a person"
          :options="listAllShipper.map(shipper => ({...shipper, label: shipper.fullName, value: shipper.id }))"
        />
        <a-button type="primary" class="ms-3" :disabled="!shipper" @click="handleTransferShipper">
          Giao hàng
        </a-button>
      </template>
      <a-button type="primary" class="mb-3">
        Chuyển giao hàng
      </a-button>
    </a-popover>
    <a-table :dataSource="listOrder" :row-selection="rowSelection" bordered :pagination="pagination"
      @change="handleChangePage" :columns="columns" class="border">
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
            <button type="button" @click="handleShowDetail(record)" class="btn btn-primary">
              <EyeOutlined />
            </button>
            <button type="button" class="btn btn-secondary">
              <EditOutlined />
            </button>
            <a-popconfirm title="Bạn có muốn xóa đơn hàng này không ?" ok-text="Có" cancel-text="Không"
              @confirm="setStatus(record.id, common.TYPE_ORDER_CANCEL)">
              <button type="button" class="btn btn-danger">
                <DeleteOutlined />
              </button>
            </a-popconfirm>
          </div>
        </template>
      </template>
    </a-table>
  </div>
  <DetailOrder :openDetail="openDetail" @on-close-detail="handleCloseDetail" :detail="detail" />
</template>