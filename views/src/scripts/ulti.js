const commonFunction = {
    redirect(url) { window.location.href = url; },
    getAllProvincesURL() { return 'https://vn-public-apis.fpo.vn/provinces/getAll?limit=-1'},
    getDistrictsByProvinceURL(code) { return 'https://vn-public-apis.fpo.vn/districts/getByProvince?provinceCode='+code+'&limit=-1'},
    getWardsByDistrictURL(code) { return 'https://vn-public-apis.fpo.vn/wards/getByDistrict?districtCode='+code+'&limit=-1'}
}

export { commonFunction };