import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import installElementPlus from './plugins/element'
// import VCharts from 'v-charts'
// import 'v-charts/lib/style.css'
import * as echarts from 'echarts'
import store from './store'
// import 'v-charts/lib/index.js'
//import './css/style.css'
// createApp(App).use(ElementPlus).mount("#app")

//创建vue实例
const vapp = createApp(App)
//设置反向代理，前端请求默认发送到 http://localhost:8443/personmis
const axios = require('axios')//使用 axios 来完成 ajax 请求。
//全局注册，之后可在其他组件中通过 this.$axios 发送数据
axios.defaults.baseURL = 'http://localhost:8081/zf'
//axios挂载到vue实例
vapp.config.globalProperties.$axios = axios
//阻止显示生产模式的消息
vapp.config.productionTip = false
installElementPlus(vapp)
// vapp.prototype.$echarts=
vapp.use(echarts).use(store).use(router).mount('#app')