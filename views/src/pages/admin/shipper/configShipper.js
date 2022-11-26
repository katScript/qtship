import { Table } from 'ant-design-vue';

export const columns = [
  {
    title: "Mã Shipper",
    dataIndex: "shipperCode",
    key: "shipperCode",
  },
  {
    title: "Tên Shipper",
    dataIndex: "fullName",
    key: "fullName",
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
    title: "Ngày tạo",
    dataIndex: "createdAt",
    key: "createdAt",
  },
  {
    title: "Cập nhật",
    dataIndex: "updatedAt",
    key: "updatedAt",
  },
  Table.EXPAND_COLUMN,
];


export const fullColumns = [
  {
    title: "Mã Shipper",
    dataIndex: "shipperCode",
    key: "shipperCode",
  },
  {
    title: "Tên Shipper",
    dataIndex: "fullName",
    key: "fullName",
  },
  {
    title: "Địa chỉ",
    dataIndex: "address",
    key: "address",
  },
  {
    title: "Địa chỉ hiện tại",
    dataIndex: "currentAddress",
    key: "currentAddress",
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
];