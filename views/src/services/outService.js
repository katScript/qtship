import axios from "axios";

const URI = 'https://vapi.vnappmob.com/api/province/';

export const province = () => axios.get(URI);

export const district = (provinceId) => axios.get(URI + 'district/' + provinceId);

export const ward = (districtId) => axios.get(URI + 'ward/' + districtId);




