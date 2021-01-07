import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://101.200.166.56:8081'
    : 'http://172.17.145.227:8081';

export default axios;
