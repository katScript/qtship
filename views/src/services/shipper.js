import http from "@/utils/http";

const PATH = 'shipper/';

export const list = (params = {}) => http.get(PATH + 'all', { params });

export const listPaginate = (params = {}) => http.get(PATH + 'all/page', { params });

export const remove = (id) => http.remove(PATH + 'delete/' + id);

export const create = (params = {}) => http.post(PATH + 'auth/register', params);

export const save = (params = {}) => http.post(PATH + 'save', params);

