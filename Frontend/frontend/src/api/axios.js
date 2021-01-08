import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://localhost:8081'   //部署的项目地址
    : 'http://172.17.145.227:8081';     //运行的本地或网络地址

export default axios;
