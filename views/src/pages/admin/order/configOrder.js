export const columns = [
  {
    title: 'Mã ĐH',
    dataIndex: 'customerCode',
    key: 'customerCode',
  },
  {
    title: 'Tên người nhận',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: 'SĐT nhận',
    dataIndex: 'phone',
    key: 'phone',
  },
  {
    title: 'Trạng thái ĐH',
    dataIndex: 'status',
    key: 'shipping_status',
  },
  {
    title: 'Dịch vụ giao hàng',
    dataIndex: 'shippingType',
    key: 'shipping_type',
  },
  {
    title: 'Mã Shipper',
    dataIndex: 'shipper_code',
    key: 'shipper_code',
  },
  {
    title: 'Tên Shipper',
    dataIndex: 'shipper_name',
    key: 'shipper_name',
  },
  {
    title: 'Tổng giá trị ĐH (VNĐ)',
    dataIndex: 'subtotal',
    key: 'sub_total',
  },
  {
    title: 'Ngày tạo đơn',
    dataIndex: 'createdAt',
    key: 'created_at',
  },
  {
    title: 'Chức năng',
    dataIndex: 'action',
    key: 'action',
    width: '200px',
  }
];