import {commonFunction} from "@/scripts/ulti";
import dayjs from "dayjs";

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
  takenTime: "",
  shipPayer: true,
  note: "",
  coupon: "",
  returnCode: "",
  warehouse: {
    id: null
  },
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

export const getOrderFormData = () => {
  return {
    senderInfo: {
      name: '',
      address: '',
      phone: ''
    },
    shippingInfo: {
      name: '',
      phone: '',
      province: '',
      provinceId: null,
      district: '',
      districtId: null,
      ward: '',
      wardId: null,
      street: ''
    },
    shippingType: {
      type: '',
      warehouseId: '',
      time: '',
      objPay: true
    },
    otherInfo: {
      note: '',
      coupon: '',
      returnCode: ''
    },
    productSelector: {
      id: null,
      name: '',
      qty: null,
      weight: null,
      price: null
    },
    productList: {}
  }
}

export const getOrderPayload = () => {
  return {
    id: null,
    customerId: null,
    senderName: null,
    senderPhone: null,
    senderAddress: null,
    note: null,
    status: null,
    feedback: null,
    notification: false,
    shipPayer: false,
    coupon: null,
    officeId: null,
    warehouse: {
      id: null
    },
    shippingAddress: {
      id: null,
      name: null,
      phone: null,
      province: null,
      provinceId: null,
      district: null,
      districtId: null,
      ward: null,
      wardId: null,
      street: null
    },
    shippingType: null,
    takenTime: null,
    returnCode: null,
    products: []
  }
}

export const getOrderProductPayload = () => {
  return {
    id: null,
    product: {
      id: null
    },
    name: null,
    qty: null,
    price: null,
    weight: null,
    longPackage: null,
    widthPackage: null,
    heightPackage: null,
    specialType: false
  };
}

export const resetOrderFormData = (data) => {
  setOrderFormData(data, getOrderFormData());
}

export const setOrderFormData = (data, obj) => {
  const value = JSON.parse(JSON.stringify(obj));

  data.senderInfo.name = value.senderInfo.name;
  data.senderInfo.address = value.senderInfo.address;
  data.senderInfo.phone = value.senderInfo.phone;

  data.shippingInfo.name = value.shippingInfo.name;
  data.shippingInfo.phone = value.shippingInfo.phone;
  data.shippingInfo.province = value.shippingInfo.province;
  data.shippingInfo.provinceId = value.shippingInfo.provinceId;
  data.shippingInfo.district = value.shippingInfo.district;
  data.shippingInfo.districtId = value.shippingInfo.districtId;
  data.shippingInfo.ward = value.shippingInfo.ward;
  data.shippingInfo.wardId = value.shippingInfo.wardId;
  data.shippingInfo.street = value.shippingInfo.street;

  data.shippingType.type = value.shippingType.type;
  data.shippingType.warehouseId = value.shippingType.warehouseId;
  data.shippingType.time = value.shippingType.time === null || value.shippingType.time === '' ? '' : dayjs(value.shippingType.time);
  data.shippingType.objPay = value.shippingType.objPay;

  data.otherInfo.note = value.otherInfo.note;
  data.otherInfo.coupon = value.otherInfo.coupon;
  data.otherInfo.returnCode = value.otherInfo.returnCode;

  data.productSelector.name = value.productSelector.name;
  data.productSelector.qty = value.productSelector.qty;
  data.productSelector.weight = value.productSelector.weight;
  data.productSelector.price = value.productSelector.price;

  data.productList = value.productList;
}

export const setOrderProductData = (data, obj) => {
  const value = JSON.parse(JSON.stringify(obj));

  data.name = value.name;
  data.qty = value.qty;
  data.weight = value.weight;
  data.price = value.price;
}

export const jAndTOrder = {
  eccompanyid: "CUSMODEL",
  customerid: "084LC012345",
  txlogisticid: null,
  ordertype: 1,
  servicetype: 1,
  selfAddress: 1,
  partsign: "0",
  sender: {
    name: null,
    phone: null,
    mobile: null,
    prov: null,
    city: null,
    area: null,
    address: null
  },
  receiver: {
    name: null,
    phone: null,
    mobile: null,
    prov: null,
    city: null,
    area: null,
    address: null
  },
  createordertime: null,
  sendstarttime: null,
  sendendtime: null,
  paytype: null,
  itemsvalue: null,
  goodsvalue: null,
  isInsured: null,
  items: [],
  weight: null,
  volume: null,
  remark: null
}

export const jAndTItem = {
  itemname: null,
  englishName: null,
  number: null,
  itemvalue: null,
  desc: null
}

export const jAndTBody = {
  logistics_interface: {},
  data_digest: "ODUyNGFkZmY2ZWNiM2M5MjFiNTljMDU2MjI3OTEzM2E=",
  msg_type: "ORDERCREATE",
  eccompanyid: "CUSMODEL"
}