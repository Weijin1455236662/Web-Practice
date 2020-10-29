import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import VueFusionCharts from 'vue-fusioncharts';
import FusionCharts from 'fusioncharts/core';
import gantt from 'fusioncharts/viz/gantt'
import angulargauge from 'fusioncharts/viz/angulargauge'
import msbar2d from 'fusioncharts/viz/msbar2d'
import FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import Charts from "fusioncharts/fusioncharts.charts";
import './assets/icon/iconfont-embedded.css'

Vue.use(VueFusionCharts, FusionCharts, FusionTheme,gantt, angulargauge, msbar2d);
// Resolves charts dependency
Charts(FusionCharts);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
