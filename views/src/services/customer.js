import http from "@/utils/http";

const PATH = 'customer/';

export const list = (params = {}) => http.get(PATH + 'all', { params });

export const remove = (id) => http.remove(PATH + 'delete/' + id);

export const update = (params = {}) => http.post(PATH + 'save', params);