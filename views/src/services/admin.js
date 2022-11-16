import http from "@/utils/http";

const PATH = 'order/';

export const list = (params = {}) => http.get(PATH + 'all', { params });

