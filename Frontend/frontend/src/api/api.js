import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://localhost:8081'
    : 'http://localhost:8081';

export const getAllStaff = () =>{
    return axios.get('/team/1').then(res=>res.data);
};