import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://101.200.166.56:8081'
    : 'http://localhost:8081';

export const getAllEquipment = () =>{
    return axios.get('/equipment/all').then(res=>res.data);
};

export const addEquipment = (equipment) =>{
    return axios.post('/equipment', equipment).then(res=>res.data);
};

export const updateEquipment = (equipment) =>{
    return axios.put('/equipment/' + equipment.equipmentid, equipment).then(res=>res.data);
};

export const deleteEquipment = (equipmentid) =>{
    return axios.delete('/equipment/' + equipmentid).then(res=>res.data);
};
