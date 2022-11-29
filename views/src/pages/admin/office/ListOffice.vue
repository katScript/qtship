<script setup>
import { ref, reactive } from "vue";
import { listPaginate, remove } from "@/services/office";
import { useStore } from "vuex";
import { columns } from "./configOffice";
import {
  EyeOutlined,
  EyeInvisibleOutlined,
  DeleteOutlined,
  EditOutlined
} from "@ant-design/icons-vue";
import FilterOffice from "@/pages/admin/components/FilterOffice.vue";
import DetailOffice from "@/pages/admin/office/DetailOffice.vue";
import { message } from "ant-design-vue";

const store = useStore();

const listOffice = ref([]);
const pagination = reactive({
  total: null,
  current: null,
  pageSize: null,
});
const detail = reactive({
  show: false,
  data: {},
  action: "",
});
const expandRow = ref([]);
const filtersSet = ref({});

const handleGetListOffice = async (filters = {}) => {
  store.dispatch("setLoading", true);
  const { data } = await listPaginate(filters);
  listOffice.value = data.content.map((item) => ({
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
  message.success("Xóa bưu cục thành công !");
  store.dispatch("setLoading", false);
  handleGetListOffice();
};

const handleChangePage = (page) => {
  handleGetListOffice({ page: page.current - 1 });
};
const handleShowDetail = (record) => {
  if (!record) {
    expandRow.value = [];
  }
  expandRow.value = [record];
};
const handleFind = (value) => {
  for (const property in value) {
    if (!value[property]) {
      delete value[property];
    }
  }
  filtersSet.value = value;
  handleGetListOffice(filtersSet.value);
};
const handleDetail = (record, type) => {
  detail.data = record;
  detail.action = type;
  detail.show = true;
};
const handleCloseDetail = (value) => {
  detail.data = {};
  detail.action = "";
  detail.show = false;
  if (value == true) {
    handleGetListOffice();
  }
};
//
handleGetListOffice();
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      Quản lý bưu cục
    </div>
    <FilterOffice @on-handle-find="handleFind" />
     <a-button type="primary" class="my-3" @click="handleDetail(null, 'create')"
      >Thêm bưu cục mới</a-button
    >
    <a-table
      :dataSource="listOffice"
      bordered
      :pagination="pagination"
      @change="handleChangePage"
      :columns="columns"
      expandRowByClick
      :scroll="{ x: 500 }"
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
          <button
            type="button"
            @click="handleDetail(record, 'update')"
            class="btn btn-secondary"
          >
            <EditOutlined style="transform: translate(0px, -3px)" />
          </button>
          <a-popconfirm
            width="100"
            title="Bạn có muốn xóa bưu cục này không ?"
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
                Thông tin bưu cục
              </div>
              <div class="p-3">
                <div>
                  <span class="font-weight-bold">Tên bưu cục: </span
                  >{{ record?.name }}
                </div>
                <div>
                  <span class="font-weight-bold"
                    >Tỉnh / thành phố: </span
                  >{{ record?.province }}
                </div>
                <div>
                  <span class="font-weight-bold"
                    >Quận / huyện: </span
                  >{{ record?.district }}
                </div>
                <div>
                  <span class="font-weight-bold"
                    >Xã / phường: </span
                  >{{ record?.ward }}
                </div>
                <div>
                  <span class="font-weight-bold"
                    >Số nhà - đường: </span
                  >{{ record?.street }}
                </div>
                <div>
                  <span class="font-weight-bold"
                    >Số điện thoạit: </span
                  >{{ record?.phone }}
                </div>
                <div>
                  <span class="font-weight-bold">Ngày tạo: </span
                  >{{ record?.createdAt }}
                </div>
                <div>
                  <span class="font-weight-bold">Ngày cập nhật: </span
                  >{{ record?.updatedAt }}
                </div>
              </div>
            </div>
          </a-col>
        </a-row>
      </template>
    </a-table>
  </div>
  <DetailOffice @on-close-detail="handleCloseDetail" :detail="detail" />
</template>
