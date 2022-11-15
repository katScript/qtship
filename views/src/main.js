import { createApp } from 'vue';
import App from '@/components/App.vue';
import router from '@/router/router';
import VueSelect from 'vue-select';
import axios from 'axios';
import VueAxios from 'vue-axios';
import VueApexCharts from 'vue3-apexcharts';
import { vue3Debounce } from 'vue-debounce';
import store from './store';
import Vue3EasyDataTable from 'vue3-easy-data-table';
import VueMoment from 'vue-moment';
import Vue3Barcode from 'vue3-barcode'
import Antd from "ant-design-vue";
// import QRCodeVue3 from "qrcode-vue3";
import { Timeline, TimelineTitle, TimelineItem } from 'vue3-timeline'

import "vue-select/dist/vue-select.css";
import 'vue3-easy-data-table/dist/style.css';
import 'vue3-timeline/dist/style.css';
import 'ant-design-vue/dist/antd.css';

const app = createApp(App);

app.use(router);

app.component('v-select', VueSelect)
app.use(VueAxios, axios)
app.use(VueApexCharts)
app.component('vue-apexchart', VueApexCharts)
app.component('easy-data-table', Vue3EasyDataTable);
app.component('vue-barcode', Vue3Barcode);
// app.component('vue-qrcode', QRCodeVue3);
app.component('vue-timeline', Timeline);
app.component('vue-timeline-title', TimelineTitle);
app.component('vue-timeline-item', TimelineItem);

TimelineItem
app.use(store);
app.use('moment', VueMoment);
app.directive('debounce', vue3Debounce({ lock: true }))
app.use(Antd)
app.mount('#app')
