import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://101.200.166.56:8081'
    : 'http://101.200.166.56:8081';

export const getAllOrder = () =>{
    return axios.get('/order/all').then(res=>res.data);
};

export const addOrder = (order) =>{
    return axios.post('/order', order).then(res=>res.data);
};

export const updateOrder = (order) =>{
    return axios.put('/order/' + order.orderid, order).then(res=>res.data);
};

export const deleteOrder = (orderid) =>{
    return axios.delete('/order/' + orderid).then(res=>res.data);
};
