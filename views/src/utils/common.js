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
  SIGNOUT: () => {
    cookiesManager.cookies.remove(userCookies.token);
    cookiesManager.cookies.remove(userCookies.username);
    cookiesManager.cookies.remove(userCookies.id);
    cookiesManager.cookies.remove(userCookies.roles);
  },
  DOMAIN_URL: 'https://api.dcodetest.com/v1/',
  TYPE_ORDER_PENDING: 'PENDING',
  TYPE_ORDER_PROCESSING: 'PROCESSING',
  TYPE_ORDER_PAYMENTED: 'PAYMENTED',
  TYPE_ORDER_SUCCESS: 'SUCCESS',
  TYPE_ORDER_DONE: 'DONE',
  TYPE_ORDER_CHECKING: 'CHECKING',
  TYPE_ORDER_OCCURRED: 'OCCURRED',
  TYPE_ORDER_CANCEL: 'CANCEL',
  TYPE_ORDER_RETURN: 'RETURN',
  TYPE_ORDER_STORAGE: 'STORAGE',
  TYPE_ORDER_ADMIN_CONFIRM: 'ADMIN_CONFIRMED',
  TYPE_SHIPPING: [
    { name: 'Giao hàng thường', value: 'STANDARD', style: 'color: white; background: green;' },
    { name: 'Giao hàng 24h', value: 'FAST', style: 'color: white; background: orange;' },
    { name: 'Giao hàng 1h', value: 'SUPER', style: 'color: white; background: red;' },
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
    { name: 'Đơn hàng chờ xử lý', value: 'PENDING', style: 'color: #ff9933; border: 1px solid #ff9933;' },
    { name: 'Đã xác nhận', value: 'ADMIN_CONFIRMED', style: 'color: #27ae60; border: 1px solid #27ae60;' },
    { name: 'Đang giao hàng', value: 'DELIVERY', style: 'color: #663300; border: 1px solid #663300;' },
    { name: 'Đơn delay, chưa thể giao ngay', value: 'DELAY', style: 'color: #0099cc; border: 1px solid #0099cc;' },
    { name: 'Đang kiểm tra lại', value: 'RECHECKING', style: 'color: #8585e0; border: 1px solid ##8585e0;' },
    { name: 'Lưu kho', value: 'STORAGE', style: 'color: #3333cc; border: 1px solid #3333cc;' },
    { name: 'Đơn bàn giao cho shipper', value: 'TRANSFER_SHIPPER', style: 'color: #3d3d29; border: 1px solid #3d3d29;' },
    { name: 'Shipper nhận đơn', value: 'SHIPPER_CONFIRMED', style: 'color: #999966; border: 1px solid #999966;' },
    { name: 'Đơn hàng đã xử lý', value: 'DONE', style: 'color: #99cc00; border: 1px solid #99cc00;' },
    { name: 'Đơn hàng phát sinh', value: 'OCCURRED', style: 'color: #ff9900; border: 1px solid #ff9900;' },
    { name: 'Đơn hàng bị hủy', value: 'CANCEL', style: 'color: #AA0000; border: 1px solid #AA0000;' },
    { name: 'Đơn hàng trả lại', value: 'RETURN', style: 'color: #ff0000; border: 1px solid #ff0000;' },
    { name: 'Đã giao hàng', value: 'SUCCESS', style: 'color: #27ae60; border: 1px solid #27ae60;' },
    { name: 'Đã thanh toán', value: 'PAYMENTED', style: 'color: #27ae60; border: 1px solid #27ae60;' },
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