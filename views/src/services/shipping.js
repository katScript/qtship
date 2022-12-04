import http from "@/utils/http";

const PATH = 'shipping/';

export const getShippingFee = (params = {}) => http.post(PATH + 'fee/calculate/', params);