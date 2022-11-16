<script setup>
const dataSource = [
  {
    key: '1',
    name: 'Mike',
    age: 32,
    address: '10 Downing Street',
  },
  {
    key: '2',
    name: 'John',
    age: 42,
    address: '10 Downing Street',
  },
]

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
  <div class="px-4 ">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">Tất cả đơn hàng</div>
    <a-table :dataSource="dataSource" :columns="columns" class="border">
      <template #bodyCell="{ column, text }">
        <template v-if="column.dataIndex === 'name'">
          <a href="javascript:;">{{ text }}</a>
        </template>
      </template>
    </a-table>
  </div>
</template>