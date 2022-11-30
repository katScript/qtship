import {commonFunction} from "@/scripts/ulti";

export const columns = [
  {
    title: "Mã ĐH",
    dataIndex: "orderCode",
    key: "orderCode",
  },
  {
    title: "Tên người nhận",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "SĐT nhận",
    dataIndex: "phone",
    key: "phone",
  },
  {
    title: "Trạng thái ĐH",
    dataIndex: "status",
    key: "shipping_status",
  },
  {
    title: "Dịch vụ giao hàng",
    dataIndex: "shippingType",
    key: "shipping_type",
  },
  // {
  //   title: 'Mã Shipper',
  //   dataIndex: 'shipper_code',
  //   key: 'shipper_code',
  // },
  // {
  //   title: 'Tên Shipper',
  //   dataIndex: 'shipper_name',
  //   key: 'shipper_name',
  // },
  {
    title: "Tổng giá trị ĐH (VNĐ)",
    dataIndex: "subtotal",
    key: "sub_total",
  },
  {
    title: "Ngày tạo đơn",
    dataIndex: "createdAt",
    key: "created_at",
  },
  {
    title: "",
    dataIndex: "action",
    key: "action",
  },
];

export const dataSample = {
  senderName: "",
  senderPhone: "",
  senderAddress: "",
  shippingType: "",
  shippingTime: "",
  shipPayer: true,
  note: "",
  coupon: "",
  returnCode: "",
  warehouse: "",
  shippingAddressName: "",
  shippingAddressPhone: "",
  shippingAddressProvince: "",
  shippingAddressProvinceId: "",
  shippingAddressDistrict: "",
  shippingAddressDistrictId: "",
  shippingAddressWard: "",
  shippingAddressWardId: "",
  shippingAddressStreet: "",
  products: [],
};

export const handleResetData = (data) => {
  data.senderName = "";
  data.senderPhone = "";
  data.senderAddress = "";
  data.shippingType = "";
  data.shippingTime = "";
  data.shipPayer = true;
  data.note = "";
  data.coupon = "";
  data.returnCode = "";
  data.warehouse = "";
  data.shippingAddressName = "";
  data.shippingAddressPhone = "";
  data.shippingAddressProvince = "";
  data.shippingAddressProvinceId = "";
  data.shippingAddressDistrict = "";
  data.shippingAddressDistrictId = "";
  data.shippingAddressWard = "";
  data.shippingAddressWardId = "";
  data.shippingAddressStreet = "";
  data.products = [];
};

export const handleSetData = (data, input) => {
  data.senderName = input.senderName;
  data.senderPhone = input.senderPhone;
  data.senderAddress = input.senderAddress;
  data.shippingType = input.shippingType;
  data.shippingTime = input.shippingTime;
  data.shipPayer = input.shipPayer;
  data.note = input.note;
  data.coupon = input.coupon;
  data.returnCode = input.returnCode;
  data.warehouse = input.warehouse;
  data.shippingAddressName = input.shippingAddressName;
  data.shippingAddressPhone = input.shippingAddressPhone;
  data.shippingAddressProvince = input.shippingAddressProvince;
  data.shippingAddressProvinceId = input.shippingAddressProvinceId;
  data.shippingAddressDistrict = input.shippingAddressDistrict;
  data.shippingAddressDistrictId = input.shippingAddressDistrictId;
  data.shippingAddressWard = input.shippingAddressWard;
  data.shippingAddressWardId = input.shippingAddressWardId;
  data.shippingAddressStreet = input.shippingAddressStreet;
  data.products = input.products;
};

export const requiredData = [
  "senderName",
  "senderPhone",
  "senderAddress",
  "shippingType",
  "shipPayer",
  "returnCode",
  "shippingAddressName",
  "shippingAddressPhone",
  "shippingAddressProvinceId",
  "shippingAddressDistrictId",
  "shippingAddressWardId",
];

export const omitKey = [
  "shippingAddressName",
  "shippingAddressPhone",
  "shippingAddressProvince",
  "shippingAddressProvinceId",
  "shippingAddressDistrict",
  "shippingAddressDistrictId",
  "shippingAddressWard",
  "shippingAddressWardId",
  "shippingAddressStreet",
];

export const processCustomerDataSample = (data, customerStorage) => {
  if (customerStorage) {
    customerStorage = JSON.parse(commonFunction.getCustomerStorage());
    data.senderAddress = `${customerStorage.addressSet[0].street}, ${customerStorage.addressSet[0].ward}, ${customerStorage.addressSet[0].district}, ${customerStorage.addressSet[0].province}`;
    data.senderPhone = customerStorage.phone;
    data.senderName = customerStorage.companyName;
  }

  data.products = [];
}
