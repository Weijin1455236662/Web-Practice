import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://101.200.166.56:8081'
    : 'http://101.200.166.56:8081';


export const getScheduleInfo = (startDate, endDate) => {
    var result = axios.get('/arrangement/' + startDate + '/' + endDate).then(res => res.data)
    return result
}

// 获取生产单
export const getOrderWorkSchedule = (id, type) => {
    let session = sessionStorage.getItem('subOrders')
    if (!session) {
        return ''
    } else {
        let orderlist = JSON.parse(session)
        let tasks = []
        console.log(orderlist)
        if (type == "0") {
            for (let i = 0; i < orderlist.length; i++) {
                let size = orderlist[i].teamList.teamList.length
                for (let j = 0; j < size; j++) {
                    if (parseInt(id) === orderlist[i].teamList.teamList[j].teamid) {
                        tasks.push({
                            date: orderlist[i].timeslot.date,
                            start: getTime(orderlist[i].timeslot.time),
                            end: getTime(orderlist[i].timeslot.time + 1),
                            material: "物料" + orderlist[i].material_code,
                        })
                    }
                }
            }
        }
        console.log(tasks)
        return tasks
    }
}

export const getEquipmentInfo = (arrangement) => {
    var equipment = arrangement.data.equipmentList
    return equipment
}

export const getSubOrderList = (arrangement) => {
    var subOrderList = arrangement.data.subOrderList
    return subOrderList
}

// 时间转换
const getTime = (time) => {
    if (0 <= time && time <= 9) {
        return "0" + time + ":00"
    } else {
        return time + ":00"
    }
}