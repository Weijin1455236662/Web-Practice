import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import VueFusionCharts from 'vue-fusioncharts';
import FusionCharts from 'fusioncharts/core';
// import Column2D from 'fusioncharts/fusioncharts.charts';
import gantt from 'fusioncharts/viz/gantt'
import FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import Charts from "fusioncharts/fusioncharts.charts";

Vue.use(VueFusionCharts, FusionCharts, FusionTheme,gantt);
// Resolves charts dependency
Charts(FusionCharts);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
