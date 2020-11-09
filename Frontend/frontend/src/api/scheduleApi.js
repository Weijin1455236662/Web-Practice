import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = 'http://101.200.166.56:8081';

export const getScheduleInfo = (startDate, endDate) => {
    return axios.get('/arrangement/' + startDate + '/' + endDate).then(res => res.data)
}