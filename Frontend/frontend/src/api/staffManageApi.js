import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://localhost:8081'
    : 'http://localhost:8081';

export const getAllStaff = () =>{
    return axios.get('/team/all').then(res=>res.data);
};

export const addStaff = (form) =>{
    return axios.post('/team', form).then(res=>res.data);
};

export const updateStaff = (form) =>{
    return axios.put('/team/' + form.teamid, form).then(res=>res.data);
};

export const deleteStaff = (teamid) =>{
    return axios.delete('/team/' + teamid).then(res=>res.data);
};
