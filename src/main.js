import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import installElementPlus from './plugins/element'
// import VueMarkdown from 'vue-markdown'
// import leMarkdownEditor from 'le-markdown-editor'
// import VCharts from 'v-charts'
// import 'v-charts/lib/style.css'
import * as echarts from 'echarts'
import 'echarts/lib/chart/map'
import store from './store'

// import VueMarkdownEditor from '@kangc/v-md-editor';
// import '@kangc/v-md-editor/lib/style/base-editor.css';
// import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
// import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

import VMdEditor from '@kangc/v-md-editor/lib/codemirror-editor';
import '@kangc/v-md-editor/lib/style/codemirror-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
import Codemirror from 'codemirror';
// mode
import 'codemirror/mode/markdown/markdown';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/css/css';
import 'codemirror/mode/htmlmixed/htmlmixed';
import 'codemirror/mode/vue/vue';
// edit
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/matchbrackets';
// placeholder
import 'codemirror/addon/display/placeholder';
// active-line
import 'codemirror/addon/selection/active-line';
// scrollbar
import 'codemirror/addon/scroll/simplescrollbars';
import 'codemirror/addon/scroll/simplescrollbars.css';
// style
import 'codemirror/lib/codemirror.css';
import hljs from 'highlight.js';
//
// import Prism from 'prismjs';
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
// import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
// import '@kangc/v-md-editor/lib/theme/style/github.css';

// highlightjs
// import hljs from 'highlight.js';
import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
import createTipPlugin from '@kangc/v-md-editor/lib/plugins/tip/index';
import '@kangc/v-md-editor/lib/plugins/tip/tip.css';
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
VMdEditor.Codemirror = Codemirror;
VMdEditor.use(githubTheme, {
  Hljs: hljs,
});
VMdEditor.use(createTipPlugin())
VMdEditor.use(createEmojiPlugin())
VMdEditor.use(createLineNumbertPlugin())
// VueMarkdownEditor.use(createEmojiPlugin());
VMdPreview.use(githubTheme, {
  Hljs: hljs,
});
// import 'v-charts/lib/index.js'
//import './css/style.css'
// createApp(App).use(ElementPlus).mount("#app")
// VueMarkdownEditor.use(vuepressTheme, {
//     Prism,
//   });
//创建vue实例
const vapp = createApp(App)
//设置反向代理，前端请求默认发送到 http://localhost:8443/personmis
const axios = require('axios')//使用 axios 来完成 ajax 请求。
//全局注册，之后可在其他组件中通过 this.$axios 发送数据
axios.defaults.baseURL = 'http://124.220.16.147:8011/zf'
//axios挂载到vue实例
vapp.config.globalProperties.$axios = axios
//阻止显示生产模式的消息
vapp.config.productionTip = false
installElementPlus(vapp)
// vapp.prototype.$echarts=
vapp.use(echarts).use(VMdPreview).use(VMdEditor).use(store).use(router).mount('#app')