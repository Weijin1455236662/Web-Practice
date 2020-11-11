import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://101.200.166.56:8081'
    : 'http://localhost:8081';

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
        } else if (type == "line") {
            for (let i = 0; i < orderlist.length; i++) {
                if (parseInt(id) === orderlist[i].equipment.equipmentid) {
                    tasks.push({
                        date: orderlist[i].timeslot.date,
                        start: getTime(orderlist[i].timeslot.time),
                        end: getTime(orderlist[i].timeslot.time + 1),
                        material: "物料" + orderlist[i].material_code,
                    })
                }
            }
        }
        console.log(tasks)
        return tasks
    }
}

// 获取订单进度
export const getOrderSchedule = (idList) => {
    let session = sessionStorage.getItem('subOrders')
    if (!session) {
        return ''
    } else {
        let orderList = JSON.parse(session)
        let orderSchedule = []
        for (let i = 0; i < idList.length; i++) {
            let sum = 0
            let subOrderNum = 0
            for (let j = 0; j < orderList.length; j++) {
                if (parseInt(idList[i].id) === parseInt(orderList[j].parent_id)) {
                    subOrderNum += 1
                    sum = sum + parseInt(orderList[j].quantity)
                }
            }
            let counter = 0
            let completeDate
            for (let j = 0; j < orderList.length; j++) {
                if (parseInt(idList[i].id) === parseInt(orderList[j].parent_id)) {
                    counter += 1
                }
                if (counter === subOrderNum) {
                    completeDate = orderList[j].timeslot.date
                }
            }
            orderSchedule.push({
                id: parseInt(idList[i].id),
                value: (sum / idList[i].quantity * 100).toString() + "%",
                planDate: idList[i].delivery_date,
                actualDate: completeDate
            })
        }
        return orderSchedule
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