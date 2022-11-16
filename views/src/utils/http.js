import axios from "axios";
import common from "./common";

const accessToken = common.TOKEN || null;

const API = common.DOMAIN_URL;

const getHeader = (hasFile = false) => {
  return {
    "Authorization": `Bearer ${accessToken}`,
    "Content-Type": hasFile ? "multipart/form-data" : "application/json"
  };
};


const handleError = (error) => {
  if (error?.response?.status === 401) {
    localStorage.removeItem("token");
  }
  throw error;
};


const get = (endPoint, options = {}) => 
  axios
    .get(API + endPoint, { headers: getHeader(), ...options })
    .catch(handleError);


const post = (endPoint, data = {}) =>
  axios
    .post(API + endPoint, JSON.stringify(data), { headers: getHeader(data instanceof FormData) })
    .catch(handleError);

const put = (endPoint, data = {}) =>
  axios
    .put(API + endPoint, JSON.stringify(data), { headers: getHeader() })
    .catch(handleError);

const remove = (endPoint) =>
  axios
    .delete(API + endPoint, { headers: getHeader() })
    .catch(handleError);

export default {
  get,
  post,
  remove,
  put,
};
