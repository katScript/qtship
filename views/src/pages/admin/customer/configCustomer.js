import { Table } from 'ant-design-vue';

export const columns = [
  {
    title: "Mã KH",
    dataIndex: "customerId",
    key: "customerId",
  },
  {
    title: "Tên KH",
    dataIndex: "fullName",
    key: "fullName",
  },
  {
    title: "Cửa hàng / Công ty",
    dataIndex: "companyName",
    key: "companyName",
  },
  {
    title: "SĐT",
    dataIndex: "phone",
    key: "phone",
  },
  {
    title: "Email",
    dataIndex: "email",
    key: "email",
  },
  {
    title: 'Ngày tạo',
    dataIndex: 'createdAt',
    key: 'createdAt',
  },
  {
    title: 'Cập nhật',
    dataIndex: 'updatedAt',
    key: 'updatedAt',
  },
  Table.EXPAND_COLUMN,
];