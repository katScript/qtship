<template>
    <div class="row location-picker">
        <div class="col-md-4">
            <v-select v-model="ttpSelected" :options="ttpList" placeholder="Chọn Tỉnh/Thành phố"></v-select>
            <!-- binding data to param . Example : ttpSelected : {label: '', code: ''} -->
        </div>
        <div class="col-md-4">
            <v-select v-model="qhSelected" :options="qhList" placeholder="Chọn Quận/Huyện"> </v-select>
        </div>
        <div class="col-md-4">
            <v-select v-model="pxSelected" :options="pxList" placeholder="Chọn Phường/Xã"></v-select>
        </div>

        <div class="col-md-12">
            <br/>
            <input type="text" class="form-control input-form-register" id="input-address-detail-shop"
                            placeholder="Địa chỉ cụ thể: Số nhà, tên Đường,..." v-model="addressDetail">
        </div>
    </div>
</template>

<script>
    import { commonFunction } from '../../scripts/ulti'

    export default {
        components: {
        },
        data() {
            return {
                //{code: 'CA', country: 'Canada'}
                apiProvincesURL: '',
                apiDistrictsURL:'',
                apiWardsURL:'',
                ttpSelected:'',
                ttpSelectedBk:'',
                qhSelected: '',
                pxSelected: '',
                ttpList: [],
                qhList: [],
                pxList: [],
                addressDetail: ''
            }
        },
        mounted() {
            const self = this;
            
            // Provinces
            self.apiProvincesURL = commonFunction.apiProvincesURL;
            if(self.ttpSelected == '') {
                self.axios.get(self.apiProvincesURL).then((response) => {
                    let dataProvince = response.data.results;
                    for (let province in dataProvince) {
                        this.ttpList.push({ label: dataProvince[province].province_name, code: dataProvince[province].province_id });
                    }
                })
            }

        },
        updated(){
            // const self = this;
            // // Districts
            // if(self.ttpSelected != '') {
            //     self.apiDistrictsURL = commonFunction.apiProvincesURL + 'district/' + self.ttpSelected.code;
            //     self.axios.get(self.apiDistrictsURL).then((response) => {
            //         let dataDistricts = response.data.results;
            //         for (let district in dataDistricts) {
            //             self.qhList.push({ label: dataDistricts[district].district_name, code: dataDistricts[district].district_id });
            //         }
            //     })
            // }

            // //Wards
            // if(self.qhSelected != '') {
            //     self.apiWardsURL = commonFunction.apiProvincesURL + 'ward/' + self.qhSelected.code;
            //     self.axios.get(self.apiWardsURL).then((response) => {
            //         let dataWards = response.data.results;
            //         for (let ward in dataWards) {
            //             self.pxList.push({ label: dataWards[ward].ward_name, code: dataWards[ward].ward_id });
            //         }
            //     })
            // }
        },
        watch: {
            ttpSelected: {
                handler: function () {
                    this.qhList = [];
                    this.qhSelected = '';
                    this.apiDistrictsURL = commonFunction.apiProvincesURL + 'district/' + this.ttpSelected.code;
                    this.axios.get(this.apiDistrictsURL).then((response) => {
                        let dataDistricts = response.data.results;
                        for (let district in dataDistricts) {
                            this.qhList.push({ label: dataDistricts[district].district_name, code: dataDistricts[district].district_id });
                        }
                    })
                }
            },
            qhSelected: {
                handler: function() {
                    this.pxList = [];
                    this.pxSelected = '';
                    this.apiWardsURL = commonFunction.apiProvincesURL + 'ward/' + this.qhSelected.code;
                    this.axios.get(this.apiWardsURL).then((response) => {
                        let dataWards = response.data.results;
                        for (let ward in dataWards) {
                            this.pxList.push({ label: dataWards[ward].ward_name, code: dataWards[ward].ward_id });
                        }
                    })
                }
            }
        },
        methods: {

        }
    }
</script>

<style>

</style>