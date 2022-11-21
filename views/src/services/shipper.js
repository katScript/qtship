import http from "@/utils/http";

const PATH = 'shipper/';

export const list = (params = {}) => http.get(PATH + 'all', { params });