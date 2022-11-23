<script setup>
import { ref, reactive, } from "vue";
import { list } from "@/services/customer";
import { useStore } from "vuex";
import { columns } from "./configCustomer";
import DetailCustomer from "./DetailCustomer.vue";
import { EyeOutlined, } from "@ant-design/icons-vue";
import FilterCustomer from "@/pages/admin/components/FilterCustomer.vue"

const store = useStore();

const listCustomer = ref([]);
const pagination = reactive({
  total: null,
  current: null,
  pageSize: null,
});
const openDetail = ref(false);
const detail = ref({});
const filtersSet = ref({});

const getCustomer = async (filters = {}) => {
  store.dispatch('setLoading', true);
  const { data } = await list(filters);
  listCustomer.value = data.content;
  pagination.total = data.totalElements;
  pagination.pageSize = data.size;
  pagination.current = data.number + 1;
  store.dispatch('setLoading', false);
}

const handleChangePage = (page) => {
  getCustomer({ page: page.current - 1 })
}

const handleTextAddress = (data) => {
  const result = [];

  if (data.street) {
    result.push(data.street);
  }
  if (data.ward) {
    result.push(data.ward);
  }
  if (data.district) {
    result.push(data.district);
  }
  if (data.province) {
    result.push(data.province);
  }

  return result.join(' - ');
}
const handleShowDetail = (record) => {
  openDetail.value = true;
  detail.value = record;
}
const handleCloseDetail = () => {
  openDetail.value = false;
  detail.value = {};
}
const handleFind = (value) => {
  // const filters = {
  //   code: value.orderCode ? 'ORD' + value.orderCode : null,
  //   phone: value.orderPhone || null,
  //   status: value.orderStatus || null,
  //   type: value.orderType || null,
  //   from: null,
  //   to: null,
  // };
  // switch (value.orderTime) {
  //   case 'timeCOToday':
  //     filters.from = moment().startOf('day').format('YYYY-MM-DD HH:mm:ss');
  //     filters.to = moment().endOf('day').format('YYYY-MM-DD HH:mm:ss');
  //     break;
  //   case 'timeCO1week':
  //     filters.from = moment().startOf('week').format('YYYY-MM-DD HH:mm:ss');
  //     filters.to = moment().endOf('week').format('YYYY-MM-DD HH:mm:ss');
  //     break;
  //   case 'timeCO1month':
  //     filters.from = moment().startOf('month').format('YYYY-MM-DD HH:mm:ss');
  //     filters.to = moment().endOf('month').format('YYYY-MM-DD HH:mm:ss');
  //     break;
  //   case 'timeCO1year':
  //     filters.from = moment().startOf('year').format('YYYY-MM-DD HH:mm:ss');
  //     filters.to = moment().endOf('year').format('YYYY-MM-DD HH:mm:ss');
  //     break;
  //   // case 'timeCOAll': 
  //   //   filters.from = moment().startOf('day').format('YYYY-MM-DD HH:mm:ss');
  //   //   filters.to = moment().endOf('day').format('YYYY-MM-DD HH:mm:ss');
  //   //   break;
  //   case 'timeCOTimeAbout':
  //     filters.from = moment(value.orderTimeAbout[0].$d).startOf('day').format('YYYY-MM-DD HH:mm:ss');
  //     filters.to = moment(value.orderTimeAbout[1].$d).endOf('day').format('YYYY-MM-DD HH:mm:ss');
  //     break;

  // }
  // delete filters.orderTimeAbout;
  // for (const property in filters) {
  //   if (!filters[property]) {
  //     delete filters[property];
  //   }
  // }
  filtersSet.value = value;
  getCustomer(filtersSet.value);
}
getCustomer();
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">Danh sách khách hàng</div>
    <FilterCustomer @on-handle-find="handleFind" />
    <a-table :dataSource="listCustomer" bordered :pagination="pagination"
      @change="handleChangePage" :columns="columns" class="border">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'address'">
          {{ handleTextAddress(record.addressSet[0]) }}
        </template>
        <template v-if="column.key === 'action'">
          <div class="d-flex justify-content-between">
            <button type="button" @click="handleShowDetail(record)" class="btn btn-primary">
              <EyeOutlined />
            </button>
            <!-- <button type="button" class="btn btn-secondary">
              <EditOutlined />
            </button> -->
            <!-- <a-popconfirm title="Bạn có muốn xóa đơn hàng này không ?" ok-text="Có" cancel-text="Không"
              @confirm="setStatus(record.id, common.TYPE_ORDER_CANCEL)">
              <button type="button" class="btn btn-danger">
                <DeleteOutlined />
              </button>
            </a-popconfirm> -->
          </div>
        </template>
      </template>
    </a-table>
  </div>
  <DetailCustomer :openDetail="openDetail" @on-close-detail="handleCloseDetail" :detail="detail" />
</template>