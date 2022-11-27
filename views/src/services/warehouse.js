import http from "@/utils/http";

const PATH = 'warehouse/';

export const listWarehouse = (customerId) => http.get(PATH + 'all/customer/' + customerId);