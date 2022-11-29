import http from "@/utils/http";

const PATH = 'customer/';

export const list = (params = {}) => http.get(PATH + 'all', { params });

export const remove = (id) => http.remove(PATH + 'delete/' + id);

export const update = (params = {}) => http.post(PATH + 'save', params);

export const saveAddress = (id, params = {}) => http.post(PATH + 'address/save/customerId/' + id, params);

export const saveForConltrol = (id, params = {}) => http.post(PATH + 'forControl/save/customerId/' + id, params);