import http from "@/utils/http";

const PATH = 'region/';

export const province = () => http.get(PATH + 'province');

export const district = (provinceId) => http.get(PATH + 'province/district/' + provinceId);

export const ward = (districtId) => http.get(PATH + 'province/ward/' + districtId);

