import http from "@/utils/http";

const PATH = 'coupon/';

export const listActive = (params = {}) => http.get(PATH + 'active/all', { params });
