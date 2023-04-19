<script setup>
import { list, updateStatus, transferShipper } from "@/services/order";
import { list as listShipper } from "@/services/shipper";
import { ref, watch, reactive, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import {
  EyeOutlined,
  EditOutlined,
  DeleteOutlined,
} from "@ant-design/icons-vue";
import { message } from "ant-design-vue";
import common from "@/utils/common";
import FilterOrder from "@/pages/admin/components/FilterOrder.vue";
import DetailOrder from "@/pages/admin/order/DetailOrder.vue";
import { useStore } from "vuex";
import { columns } from "./configOrder";
import moment from "moment";

const store = useStore();
const route = useRoute();
const router = useRouter();

const listOrder = ref([]);
const shipper = ref("");
const status = ref("");
const listAllShipper = ref([]);
const shipperShow = ref(false);
const statusShow = ref(false);
const listSelectOrder = ref([]);
const openDetail = ref(false);
const detail = ref({});
const filtersSet = ref({});
const pagination = reactive({
  total: null,
  current: null,
  pageSize: null,
});
const rowSelection = ref({
  onSelect: (record, selected, selectedRows) => {
    listSelectOrder.value = selectedRows.map((x) => {
      if (x.id) {
        return x.id;
      }
    });
  },
  onSelectAll: (selected, selectedRows) => {
    listSelectOrder.value = selectedRows.map((x) => {
      if (x.id) {
        return x.id;
      }
    });
  },
});
const getTitle = computed(() => {
  const title = {
    PENDING: "Đơn hàng chờ xử lý",
    PROCESSING: "Đơn hàng đang xử lý",
    DONE: "Đơn hàng đã xử lý",
    OCCURRED: "Đơn hàng phát sinh",
    CANCEL: "Đơn hàng bị hủy",
    RETURN: "Đơn hàng trả lại",
  };
  return route.query?.status ? title[route.query?.status] : "Tất cả đơn hàng";
});
watch(
  () => route.query?.status,
  () => {
    listSelectOrder.value = [];
  }
);
//
const handleTransferShipper = async () => {
  const data = listSelectOrder.value.map((order) => ({
    orderId: order,
    shipperId: shipper.value,
    //test trong ngày
    shippingTime: moment(new Date()).format("YYYY-MM-DD HH:mm:ss"),
    //assign cho shipper ngày mai giao
    // shippingTime: moment(new Date()).add(1, 'days').format("YYYY-MM-DD HH:mm:ss")
  }));
  await transferShipper(data);
  message.success("Chuyển giao hàng cho shipper thành công");
  listSelectOrder.value = [];
  handleResetShipper();
  getListOrder();
};
const handleChangeStatus = async () => {
  const data = listSelectOrder.value.map((order) => {
    if (order != null) {
      return {
        id: order,
        status: status.value,
        description: "",
      };
    }
  });
  await updateStatus(data);
  message.success("Chuyển trạng thái đơn hàng thành công");
  listSelectOrder.value = [];
  handleResetStatus();
  getListOrder();
};
const handleResetShipper = () => {
  shipper.value = "";
  shipperShow.value = false;
};
const handleResetStatus = () => {
  status.value = "";
  statusShow.value = false;
};
const getListShipper = async () => {
  const { data } = await listShipper();
  listAllShipper.value = data;
};
const getListOrder = async (filters = {}) => {
  store.dispatch("setLoading", true);
  if (route.query?.status) {
    const { data } = await list({
      ...filters,
      status:
        route.query.status != common.TYPE_ORDER_PROCESSING
          ? route.query.status
          : filters.status,
    });
    listOrder.value = data.content.map((item) => ({
      key: item.id + Math.random(),
      ...item,
    }));
    pagination.total = data.totalElements;
    pagination.pageSize = data.size;
    pagination.current = data.number + 1;
  } else {
    const { data } = await list(filters);
    listOrder.value = data.content.map((item) => ({
      key: item.id + Math.random(),
      ...item,
    }));
    pagination.total = data.totalElements;
    pagination.pageSize = data.size;
    pagination.current = data.number + 1;
  }
  store.dispatch("setLoading", false);
};
const filterOption = (input, option) => {
  return option.fullName.toLowerCase().indexOf(input.toLowerCase()) >= 0;
};
const setStatus = async (id, code) => {
  await updateStatus({ id: id, status: code });
  message.success("Xóa đơn hàng thành công");
  getListOrder();
};
const handleShowDetail = (record) => {
  openDetail.value = true;
  detail.value = record;
};
const handleCloseDetail = () => {
  openDetail.value = false;
  detail.value = {};
};
const handleChangePage = (page) => {
  getListOrder({ page: page.current - 1, ...filtersSet.value });
};
const handleFind = (value) => {
  const filters = {
    code: value.orderCode ? "ORD" + value.orderCode : null,
    phone: value.orderPhone || null,
    status: value.orderStatus || null,
    type: value.orderType || null,
    from: null,
    to: null,
  };
  switch (value.orderTime) {
    case "timeCOToday":
      filters.from = moment().startOf("day").format("YYYY-MM-DD HH:mm:ss");
      filters.to = moment().endOf("day").format("YYYY-MM-DD HH:mm:ss");
      break;
    case "timeCO1week":
      filters.from = moment().startOf("week").format("YYYY-MM-DD HH:mm:ss");
      filters.to = moment().endOf("week").format("YYYY-MM-DD HH:mm:ss");
      break;
    case "timeCO1month":
      filters.from = moment().startOf("month").format("YYYY-MM-DD HH:mm:ss");
      filters.to = moment().endOf("month").format("YYYY-MM-DD HH:mm:ss");
      break;
    case "timeCO1year":
      filters.from = moment().startOf("year").format("YYYY-MM-DD HH:mm:ss");
      filters.to = moment().endOf("year").format("YYYY-MM-DD HH:mm:ss");
      break;
    case "timeCOTimeAbout":
      filters.from = moment(value.orderTimeAbout[0].$d)
        .startOf("day")
        .format("YYYY-MM-DD HH:mm:ss");
      filters.to = moment(value.orderTimeAbout[1].$d)
        .endOf("day")
        .format("YYYY-MM-DD HH:mm:ss");
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
};
const shippingTypeStyle = (value) => {
  if (!value) {
    return "";
  }
  const type = common.TYPE_SHIPPING.find((x) => x.value == value);
  return `<div style="${type.style}">${type.value}</div>`;
};
const shippingStatusStyle = (value) => {
  if (!value) {
    return "";
  }
  const type = common.ALL_TYPE.find((x) => x.value == value);
  if (!type) {
    return "";
  }
  return `<div style="${type.style}; flex">${type.name}</div>`;
};
//
getListOrder();
getListShipper();
watch(
  () => route.query?.status,
  () => getListOrder()
);
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      {{ getTitle }}
    </div>
    <FilterOrder :status="route.query?.status" @on-handle-find="handleFind" />
    <a-popover
      v-model:visible="shipperShow"
      title="Chuyển giao hàng"
      @click="handleResetShipper"
      trigger="click"
      v-if="
        !!listSelectOrder.length &&
        route.query.status == common.TYPE_ORDER_PROCESSING
      "
    >
      <template #content>
        <a-select
          v-model:value="shipper"
          style="width: 250px"
          show-search
          :filter-option="filterOption"
          placeholder="Select a person"
          :options="
            listAllShipper.map((shipper) => ({
              ...shipper,
              label: shipper.fullName,
              value: shipper.id,
            }))
          "
        />
        <a-button
          type="primary"
          class="ms-3"
          :disabled="!shipper"
          @click="handleTransferShipper"
        >
          Giao hàng
        </a-button>
      </template>
      <a-button type="primary" class="mb-3"> Chuyển giao hàng </a-button>
    </a-popover>
    <a-popover
      v-model:visible="statusShow"
      title="Chuyển trạng thái đơn hàng"
      @click="handleResetStatus"
      trigger="click"
      v-if="
        !!listSelectOrder.length &&
        route.query.status == common.TYPE_ORDER_PENDING
      "
    >
      <template #content>
        <a-select v-model:value="status" style="width: 250px">
          <a-select-option :value="common.TYPE_ORDER_ADMIN_CONFIRMED"
            >Xác nhận
          </a-select-option>
          <a-select-option :value="common.TYPE_ORDER_CANCEL"
            >Hủy</a-select-option
          >
        </a-select>
        <a-button
          type="primary"
          class="ms-3"
          :disabled="!status"
          @click="handleChangeStatus"
        >
          Chuyển trạng thái
        </a-button>
      </template>
      <a-button type="primary" class="mb-3">
        Chuyển trạng thái đơn hàng
      </a-button>
    </a-popover>
    <a-table
      :dataSource="listOrder"
      :row-selection="rowSelection"
      bordered
      :scroll="{ x: 500 }"
      :pagination="pagination"
      @change="handleChangePage"
      :columns="columns"
      class="border"
    >
      <template #bodyCell="{ column, record, text }">
        <template v-if="column.key === 'name'">
          {{ record.shippingAddress.name }}
        </template>
        <template v-if="column.key === 'phone'">
          {{ record.shippingAddress.phone }}
        </template>
        <template v-if="column.key === 'shipping_status'">
          <span class="text-center" v-html="shippingStatusStyle(text)"></span>
        </template>
        <template v-if="column.key === 'shipping_type'">
          <span class="text-center" v-html="shippingTypeStyle(text)"></span>
        </template>
        <template v-if="column.key === 'shipper_code'">
          {{ record.shipper.shipperCode }}
        </template>
        <template v-if="column.key === 'shipper_name'">
          {{ record.shipper.fullName }}
        </template>
        <template v-if="column.key === 'action'">
          <div class="d-flex justify-content-between">
            <button
              type="button"
              @click="handleShowDetail(record)"
              class="btn btn-primary"
            >
              <EyeOutlined style="transform: translate(0px, -3px)" />
            </button>
            <button
              type="button"
              @click="router.push('/admin/order/' + record.id)"
              class="btn btn-secondary"
            >
              <EditOutlined style="transform: translate(0px, -3px)" />
            </button>
            <a-popconfirm
              title="Bạn có muốn xóa đơn hàng này không ?"
              ok-text="Có"
              cancel-text="Không"
              @confirm="setStatus(record.id, common.TYPE_ORDER_CANCEL)"
            >
              <button type="button" class="btn btn-danger">
                <DeleteOutlined style="transform: translate(0px, -3px)" />
              </button>
            </a-popconfirm>
          </div>
        </template>
      </template>
    </a-table>
  </div>
  <DetailOrder
    :openDetail="openDetail"
    @on-close-detail="handleCloseDetail"
    :detail="detail"
  />
</template>
