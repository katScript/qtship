import { useCookies } from "vue3-cookies";

const cookiesManager = useCookies();

const userCookies = {
  token: "gEZkA7zyDqT7ZcMVLVhA2dYdiNHmLQD4",
  username: "jgT7Un46hLk4jBzyTGEq6j634H34jmU8",
  id: "4qYbrSdRZSb2uYS4f58iBKmjRHFFaWmy",
  roles: "4DFkgFzNb6eHkWw7SLzGCGvTwNN49pk3"
}

export default {
  TOKEN: () => cookiesManager.cookies.get(userCookies.token),
  DOMAIN_URL: 'https://api.dcodetest.com/v1/',
  TYPE_ORDER_PENDING: 'PENDING',
  TYPE_ORDER_PROCESSING: 'PROCESSING',
  TYPE_ORDER_DONE: 'DONE',
  TYPE_ORDER_OCCURRED: 'OCCURRED',
  TYPE_ORDER_CANCEL: 'CANCEL',
  TYPE_ORDER_RETURN: 'RETURN',
  TYPE_SHIPPING: [
    { name: 'Giao hàng thường', value: 'STANDARD' },
    { name: 'Giao hàng 24h', value: 'FAST' },
    { name: 'Giao hàng 1h', value: 'SUPER' },
  ],
  PROCESSING_TYPE: [
    { name: 'Đang giao hàng', value: 'DELIVERY' },
    { name: 'Đơn delay, chưa thể giao ngay', value: 'DELAY' },
    { name: 'Đang kiểm tra lại', value: 'RECHECKING' },
    { name: 'Lưu kho', value: 'STORAGE' },
    { name: 'Đơn bàn giao cho shipper', value: 'TRANSFER_SHIPPER' },
    { name: 'Shipper nhận đơn', value: 'SHIPPER_CONFIRMED' },
  ],
  ALL_TYPE: [
    { name: 'Đơn hàng chờ xử lý', value: 'PENDING' },
    { name: 'Đang giao hàng', value: 'DELIVERY' },
    { name: 'Đơn delay, chưa thể giao ngay', value: 'DELAY' },
    { name: 'Đang kiểm tra lại', value: 'RECHECKING' },
    { name: 'Lưu kho', value: 'STORAGE' },
    { name: 'Đơn bàn giao cho shipper', value: 'TRANSFER_SHIPPER' },
    { name: 'Shipper nhận đơn', value: 'SHIPPER_CONFIRMED' },
    { name: 'Đơn hàng đã xử lý', value: 'DONE' },
    { name: 'Đơn hàng phát sinh', value: 'OCCURRED' },
    { name: 'Đơn hàng bị hủy', value: 'CANCEL' },
    { name: 'Đơn hàng trả lại', value: 'RETURN' },
  ],
  TIME_CREATE: [
    { name: 'Trong hôm nay', value: 'timeCOToday' },
    { name: 'Trong một tuần', value: 'timeCO1week' },
    { name: 'Trong một tháng', value: 'timeCO1month' },
    { name: 'Trong một năm', value: 'timeCO1year' },
    // { name: 'Tất cả', value: 'timeCOAll' },
    { name: 'Tùy chỉnh', value: 'timeCOTimeAbout' },
  ],
  RETURN_TYPE: [
    { name: 'Lưu kho chờ shop check', value: 'SAVE_WARE_WAITING_CHECK' },
    { name: 'Không được hoàn', value: 'CAN_NOT_RETURN' },
    { name: 'Không lưu kho , trả shop ngay', value: 'DON_NOT_SAVE_RETURN_NOW' },
  ],
}