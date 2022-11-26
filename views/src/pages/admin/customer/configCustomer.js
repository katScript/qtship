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


export const fullUserColumns = [
  {
    title: "Mã KH",
    dataIndex: "customerId",
    key: "customerId",
  },
  {
    title: "Tên khách hàng",
    dataIndex: "fullName",
    key: "fullName",
  },
  {
    title: "Giới tính",
    dataIndex: "gender",
    key: "gender",
  },
  {
    title: "Ngày sinh",
    dataIndex: "dob",
    key: "dob",
  },
  {
    title: "Số điện thoại",
    dataIndex: "phone",
    key: "phone",
  },
  {
    title: "Email",
    dataIndex: "email",
    key: "email",
  },
  {
    title: "Ảnh CCCD / CMTND",
    dataIndex: "photo",
    key: "photo",
  },
];

export const fullAddressColumns = [
  {
    title: "Cửa hàng / Công ty",
    dataIndex: "companyName",
    key: "companyName",
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
    title: "Số nhà - Đường",
    dataIndex: "street",
    key: "street",
  },
];

export const fullBankColumns = [
  {
    title: "Tên chủ sở hữu",
    dataIndex: "holderName",
    key: "holderName",
  },
  {
    title: "Số thẻ",
    dataIndex: "cardNumber",
    key: "cardNumber",
  },
  {
    title: "Ngân hàng",
    dataIndex: "bank",
    key: "bank",
  },
  {
    title: "Địa chỉ",
    dataIndex: "address",
    key: "address",
  },
];
