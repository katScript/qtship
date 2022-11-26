<script setup>
import { ref, reactive } from "vue";
import { listPaginate, remove } from "@/services/shipper";
import { useStore } from "vuex";
import { columns } from "./configShipper";
import {
  EyeOutlined,
  EyeInvisibleOutlined,
  DeleteOutlined,
} from "@ant-design/icons-vue";
import FilterShipper from "@/pages/admin/components/FilterShipper.vue";
import { message } from "ant-design-vue";

const store = useStore();

const listShipper = ref([]);
const pagination = reactive({
  total: null,
  current: null,
  pageSize: null,
});
const expandRow = ref([]);
const filtersSet = ref({});

const handleGetListShipper = async (filters = {}) => {
  store.dispatch("setLoading", true);
  const { data } = await listPaginate(filters);
  listShipper.value = data.content.map((item) => ({
    ...item,
    key: item.id + Math.random(),
  }));
  pagination.total = data.totalElements;
  pagination.pageSize = data.size;
  pagination.current = data.number + 1;
  store.dispatch("setLoading", false);
};

const handleRemove = async (id) => {
  store.dispatch("setLoading", true);
  await remove(id);
  message.success("Xóa khách hàng thành công !");
  store.dispatch("setLoading", false);
  handleGetListShipper();
};

const handleChangePage = (page) => {
  handleGetListShipper({ page: page.current - 1 });
};
const handleShowDetail = (record) => {
  if (!record) {
    expandRow.value = [];
  }
  expandRow.value = [record];
};
const handleFind = (value) => {
  filtersSet.value = value;
  handleGetListShipper(filtersSet.value);
};
//
handleGetListShipper();
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      Danh sách người giao hàng
    </div>
    <FilterShipper @on-handle-find="handleFind" />
    <a-table
      :dataSource="listShipper"
      bordered
      :pagination="pagination"
      @change="handleChangePage"
      :columns="columns"
      expandRowByClick
      class="border"
      :expandedRowKeys="expandRow"
    >
      <template #expandIcon="{ record }">
        <div class="d-flex gap-3">
          <button
            v-if="record.key != expandRow"
            type="button"
            class="btn btn-primary"
            @click="handleShowDetail(record.key)"
          >
            <EyeOutlined style="transform: translate(0px, -3px)" />
          </button>
          <button
            v-else
            type="button"
            class="btn btn-outline-primary"
            @click="handleShowDetail(null)"
          >
            <EyeInvisibleOutlined style="transform: translate(0px, -3px)" />
          </button>
          <a-popconfirm
            width="100"
            title="Bạn có muốn xóa khách hàng này không ?"
            ok-text="Có"
            cancel-text="Không"
            @confirm="handleRemove(record.id)"
          >
            <button type="button" class="btn btn-danger">
              <DeleteOutlined style="transform: translate(0px, -3px)" />
            </button>
          </a-popconfirm>
        </div>
      </template>
      <template #expandedRowRender="{ record }">
        <a-row type="flex" class="my-4" :gutter="[10, 10]" align="top">
          <a-col :span="24" class="align-self-stretch">
            <div class="border h-100">
              <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">
                Thông tin người giao hàng
              </div>
              <div class="p-3">
                <div>
                  <span class="font-weight-bold">Mã người giao hàng: </span
                  >{{ record?.shipperCode }}
                </div>
                <div>
                  <span class="font-weight-bold">Tên người giao hàng: </span
                  >{{ record?.fullName }}
                </div>
                <div>
                  <span class="font-weight-bold"
                    >Tài khoản người giao hàng: </span
                  >{{ record?.username }}
                </div>
                <div>
                  <span class="font-weight-bold">Số điện thoại: </span
                  >{{ record?.phone }}
                </div>
                <div>
                  <span class="font-weight-bold">Địa chỉ email: </span
                  >{{ record?.email }}
                </div>
                <div>
                  <span class="font-weight-bold">Địa chỉ: </span
                  >{{ record?.address }}
                </div>
                <div>
                  <span class="font-weight-bold">Địa chỉ hiện tại: </span
                  >{{ record?.currentAddress }}
                </div>
              </div>
            </div>
          </a-col>
        </a-row>
      </template>
    </a-table>
  </div>
</template>
