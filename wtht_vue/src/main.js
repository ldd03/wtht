import { createApp } from 'vue'
import ElementPlus from 'element-plus' // 组件的引入
import 'element-plus/dist/index.css' //样式引入
import './assets/global.css'
import router from './router'
import App from './App.vue'
// 导入axios
import axios from 'axios'

//createApp(App).use(ElementPlus).mount('#app')
const app = createApp(App) 

app.use(ElementPlus) 
app.use(router)
app.mount('#app')

// 为axios配置请求根路径
axios.defaults.baseURL = 'http://localhost:8888/'
//挂在到全局 属性中 方便调用
app.config.globalProperties.$http = axios // $http 自定义属性 相当于 axios