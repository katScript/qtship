import { Table } from 'ant-design-vue';

export const columns = [
  {
    title: "Tên bưu cục",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Tỉnh / thành phố",
    dataIndex: "province",
    key: "province",
  },
  {
    title: "Quận / huyện",
    dataIndex: "district",
    key: "district",
  },
  {
    title: "Xã / phường",
    dataIndex: "ward",
    key: "ward",
  },
  {
    title: "Số nhà - đường",
    dataIndex: "street",
    key: "street",
  },
  {
    title: "SĐT",
    dataIndex: "phone",
    key: "phone",
  },
  // {
  //   title: 'Ngày tạo',
  //   dataIndex: 'createdAt',
  //   key: 'createdAt',
  // },
  // {
  //   title: 'Cập nhật',
  //   dataIndex: 'updatedAt',
  //   key: 'updatedAt',
  // },
  Table.EXPAND_COLUMN,
];