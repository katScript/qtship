import http from "@/utils/http";

const PATH = 'admin/config/';

export const scope = (scope, params = {}) => http.get(PATH + 'scope/' + scope, { params });

export const save = (params = {}) => http.post(PATH + 'save', params);
