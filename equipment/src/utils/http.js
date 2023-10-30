import axios from 'axios';
import router from '../router'


//Content-Type 响应头
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
// //基础url http://qfsxb.mynatapp.cc
axios.defaults.baseURL = "http://localhost:9527/equipment/";
/**
 * 封装get方法
 */
export function get(url, params = {}) {
    return new Promise((resolve, reject) => {
        axios.get(url, { params: params })
            .then(response => {
                resolve(response.data);
            })
            .catch(err => {
                reject(err);
            })
    });
}

/**
 * 封装post方法
 */
export function post(url, data = {}) {
    return new Promise((resolve, reject) => {
        axios.post(url, data)
            .then(response => {
                resolve(response.data);
            })
            .catch(err => {
                reject(err);
            })
    });
}

// // //请求拦截器
// axios.interceptors.request.use(function(config){
//     // 为请求头添加Authorization字段为服务端返回的token
//     config.headers.token = localStorage.getItem('token')
//     // return config是固定用法 必须有返回值
//     return config
// })
// // //响应拦截器
// axios.interceptors.response.use(function(response){
//     const url = response.config.url
//     console.log(111,url,response.data.code);
//     //拦截其他的
//     if(response.data.code==false){
//         router.replace({
//             path:'/login',
//             query:{
//                 redirect: router.currentRoute.fullPath
//             }
//         });
//     }
//     return response;
// })