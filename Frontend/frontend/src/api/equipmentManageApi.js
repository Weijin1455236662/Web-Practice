import axios from './axios'

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

export const getEquipmentById = (equipmentid) => {
    return axios.get('/equipment/' + equipmentid).then(res => res.data);
};