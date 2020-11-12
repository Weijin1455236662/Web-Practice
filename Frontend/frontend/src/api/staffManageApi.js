import axios from './axios'

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

export const getStaffById = (teamid) => {
    return axios.get('/team/' + teamid).then(res => res.data);
}