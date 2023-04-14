import http from "@/utils/http";

const PATH = "office/";

export const officeList = (params = {}) => http.get(PATH + "all", { params });

export const listPaginate = (params = {}) =>
  http.get(PATH + "all/page", { params });

export const remove = (id) => http.remove(PATH + "delete/" + id);

export const save = (params = {}) => http.post(PATH + "save", params);

export const price = (params = {}) => http.post(PATH + "price/import", params);

export const priceSample = (params = {}) => http.get(PATH + "price/price/sample", params);

export const areaSample = (params = {}) => http.get(PATH + "price/area/sample", params);