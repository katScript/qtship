<script setup>
import { list } from "@/services/admin";
import { ref, watch } from "vue";
import { useRoute } from 'vue-router';

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

//
getListOrder();
watch(() => route.query?.status, () => getListOrder());

const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: 'Age',
    dataIndex: 'age',
    key: 'age',
  },
  {
    title: 'Address',
    dataIndex: 'address',
    key: 'address',
  },
]
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">Tất cả đơn hàng</div>
    <a-table :dataSource="listOrder" :columns="columns" class="border">
      <template #bodyCell="{ column, text }">
        <template v-if="column.dataIndex === 'name'">
          <a href="javascript:;">{{ text }}</a>
        </template>
      </template>
    </a-table>
  </div>
</template>