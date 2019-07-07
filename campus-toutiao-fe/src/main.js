// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import store from '@/vuex/store.js'

Vue.prototype.bus = new Vue()    //此处全局注册一个Vue作为事件中心

import axios from 'axios'
Vue.prototype.$http = axios
axios.defaults.withCredentials = true;  //让ajax携带cookie

import qs from 'qs'
// 用qs解析，jsonToForm
axios.interceptors.request.use(config => {
    if(config.type == 'formData' || config.method != 'post'){
        return config;
    }
    config.data = qs.stringify(config.data);
    return config;
}, (err) =>{
    Message.error({
        message: '加载超时'
    });
    return Promise.reject(err);
})


Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(iView);

// 时间过滤器
Vue.filter('dateFormat', (date) => {
    if(date){
        let oDate=new Date();
        oDate.setTime(date);

        let y=oDate.getFullYear();
        let M=oDate.getMonth()+1;
        M = M > 9 ? M : '0' + M;
        let d=oDate.getDate();
        d = d > 9 ? d : '0' + d;

        let h=oDate.getHours();
        h = h > 9 ? h : '0' + h;
        let m=oDate.getMinutes();
        m = m > 9 ? m : '0' + m;
        let s=oDate.getSeconds();
        s = s > 9 ? s : '0' + s;

        return y + '-' + M + '-' + d + ' ' + h + ':' + m + ':' + s;
    }
});

router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
        if (store.state.adminUserExist) {
            next();
        } else {
            next({
                path: '/admin/login',
                query: {
                    redirect: to.fullPath  // 将跳转的路由path作为参数，登录成功后跳转到该路由
                }
            });
        }
    } else {
        next();
    }
});

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    store,
    components: {App},
    template: '<App/>'
})
