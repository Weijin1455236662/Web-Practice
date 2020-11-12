import axios from './axios'

export const importTeam = () => {
    return axios.get('/team/import').then(res => res.data);
}

export const importEquipment = () => {
    return axios.get('/equipment/import').then(res => res.data);
}

export const importOrder = () => {
    return axios.get('/order/import').then(res => res.data);
}

export const importCraft = () => {
    return axios.get('/team/importCraft').then(res => res.data);
}