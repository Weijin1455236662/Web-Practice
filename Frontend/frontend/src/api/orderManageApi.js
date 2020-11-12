import axios from './axios'

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
