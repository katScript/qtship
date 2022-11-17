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
}