import http from "@/utils/http";

const PATH = 'san-pham/';

export const list = (params = {}) => http.get(PATH, { params });

export const detail = (id, params = {}) => http.get(PATH + id, { params });
