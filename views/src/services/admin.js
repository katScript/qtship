import http from "@/utils/http";

const PATH = 'order/';

export const list = (params = {}) => http.get(PATH + 'all', { params });

export const updateStatus = (params = {}) => http.post(PATH + 'update/status', params);