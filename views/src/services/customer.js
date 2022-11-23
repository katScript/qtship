import http from "@/utils/http";

const PATH = 'customer/';

export const list = (params = {}) => http.get(PATH + 'all', { params });