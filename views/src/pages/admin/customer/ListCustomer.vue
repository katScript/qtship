<script setup>
import { ref, reactive } from "vue";
import { list, remove } from "@/services/customer";
import { useStore } from "vuex";
import {
  columns,
} from "./configCustomer";
import {
  EyeOutlined,
  EyeInvisibleOutlined,
  DeleteOutlined,
  EditOutlined,
} from "@ant-design/icons-vue";
import FilterCustomer from "@/pages/admin/components/FilterCustomer.vue";
import DetailCustomer from "@/pages/admin/customer/DetailCustomer.vue";
import { message } from "ant-design-vue";

const store = useStore();

const listCustomer = ref([]);
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

const handleGetListCustomer = async (filters = {}) => {
  store.dispatch("setLoading", true);
  const { data } = await list(filters);
  listCustomer.value = data.content.map((item) => ({
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
  handleGetListCustomer();
};

const handleChangePage = (page) => {
  handleGetListCustomer({ page: page.current - 1 });
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
  handleGetListCustomer(filtersSet.value);
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
    handleGetListCustomer();
  }
};
//
handleGetListCustomer();
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      Danh sách khách hàng
    </div>
    <FilterCustomer @on-handle-find="handleFind" />
    <!-- <a-button type="primary" class="my-3" @click="handleDetail(null, 'create')"
      >Thêm khách hàng mới</a-button
    > -->
    <a-table
      :dataSource="listCustomer"
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
          <button
            type="button"
            @click="handleDetail(record, 'update')"
            class="btn btn-secondary"
          >
            <EditOutlined style="transform: translate(0px, -3px)" />
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
          <a-col :span="8" class="align-self-stretch">
            <div class="border h-100">
              <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">
                Thông tin khách hàng
              </div>
              <div class="p-3">
                <div>
                  <span class="font-weight-bold">Mã khách hàng: </span
                  >{{ record?.customerId }}
                </div>
                <div>
                  <span class="font-weight-bold">Tên khách hàng: </span
                  >{{ record?.fullName }}
                </div>
                <div>
                  <span class="font-weight-bold">Giới tính: </span
                  >{{ record?.gender }}
                </div>
                <div>
                  <span class="font-weight-bold">Ngày sinh: </span
                  >{{ record?.dob }}
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
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <span class="font-weight-bold">Ảnh CCCD / CMTND: </span>
                    <div>
                      <a-image
                        :height="50"
                        :width="50"
                        :src="record.cidFront"
                      />
                      <a-image
                        class="ms-2"
                        :height="50"
                        :width="50"
                        :src="record.cidBack"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </a-col>
          <a-col :span="8" class="align-self-stretch">
            <div class="border h-100">
              <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">
                Cửa hàng / công ty
              </div>
              <div class="p-3">
                <div>
                  <span class="font-weight-bold">Tên cửa hàng / công ty : </span
                  >{{ record?.companyName }}
                </div>
                <div>
                  <span class="font-weight-bold">Tỉnh / thành phố: </span
                  >{{ record?.addressSet[0]?.province }}
                </div>
                <div>
                  <span class="font-weight-bold">Quận / huyện: </span
                  >{{ record?.addressSet[0]?.district }}
                </div>
                <div>
                  <span class="font-weight-bold">Xã / phường </span
                  >{{ record?.addressSet[0]?.ward }}
                </div>
                <div>
                  <span class="font-weight-bold">Số nhà - Đường: </span
                  >{{ record?.addressSet[0]?.street }}
                </div>
              </div>
            </div>
          </a-col>
          <a-col :span="8" class="align-self-stretch">
            <div class="border h-100">
              <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">
                Thông tin ngân hàng
              </div>
              <div class="p-3">
                <div>
                  <span class="font-weight-bold">Tên chủ sở hữu: </span
                  >{{ record.forControls[0]?.holderName }}
                </div>
                <div>
                  <span class="font-weight-bold">Số thẻ: </span
                  >{{ record.forControls[0]?.cardNumber }}
                </div>
                <div>
                  <span class="font-weight-bold">Ngân hàng: </span
                  >{{ record.forControls[0]?.bank }}
                </div>
                <div>
                  <span class="font-weight-bold">Địa chỉ: </span
                  >{{ record.forControls[0]?.address }}
                </div>
              </div>
            </div>
          </a-col>
        </a-row>
      </template>
    </a-table>
  </div>
  <DetailCustomer @on-close-detail="handleCloseDetail" :detail="detail" />
</template>
