import http from "@/utils/http";

const PATH = 'product/';

export const listProduct = (customerId) => http.get(PATH + 'customer/' + customerId);