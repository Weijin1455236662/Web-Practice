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
        if (type === '0') {
            for (let i = 0; i < orderlist.length; i++) {
                if (orderlist[i].teamList !== null) {
                    let size = orderlist[i].teamList.teamList.length
                    for (let j = 0; j < size; j++) {
                        if (parseInt(id) === orderlist[i].teamList.teamList[j].teamid) {
                            if (orderlist[i].timeslot !== null) {
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
            }
        } else if (type === '1') {
            for (let i = 0; i < orderlist.length; i++) {
                if (orderlist[i].timeslot != null) {
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
        }
        console.log(tasks)
        return tasks
    }
}

// 获取订单进度
export const getOrderSchedule = (idList, date) => {
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
                if (orderList[j].timeslot !== null&&orderList[j].timeslot.date<=date) {
                    if (parseInt(idList[i].id) === parseInt(orderList[j].parent_id)) {
                        subOrderNum += 1
                        sum = sum + parseInt(orderList[j].quantity)
                    }
                }
            }
            let counter = 0
            let completeDate = ''
            for (let j = 0; j < orderList.length; j++) {
                if (orderList[j].timeslot !== null) {
                    if (parseInt(idList[i].id) === parseInt(orderList[j].parent_id)) {
                        counter += 1
                    }
                    if (counter === subOrderNum) {
                        completeDate = orderList[j].timeslot.date
                    }
                }
            }
            orderSchedule.push({
                id: parseInt(idList[i].id),
                value: (sum / idList[i].quantity * 100) < 100 ? (sum / idList[i].quantity * 100).toString() + "%" : "100%",
                planDate: idList[i].delivery_date,
                actualDate: completeDate
            })
        }
        return orderSchedule
    }
}

// 计算负载率
export const getLoadRate = (dateList) => {
    let session = sessionStorage.getItem('subOrders')
    if (!session) {
        return ''
    } else {
        let orderList = JSON.parse(session)
        let personList = []
        let equipList = []
        for (let i = 0; i < orderList.length; i++) {
            let tempEquip = {
                id: orderList[i].equipment.equipmentid,
                name: orderList[i].equipment.name
            }
            if (JSON.stringify(equipList).indexOf(JSON.stringify(tempEquip))=== -1) {
                equipList.push(tempEquip)
            }
            for (let j = 0; j < orderList[i].teamList.teamList.length; j++) {
                let tempPerson = {
                    id: orderList[i].teamList.teamList[j].teamid,
                    name: orderList[i].teamList.teamList[j].name
                }
                if (JSON.stringify(personList).indexOf(JSON.stringify(tempPerson)) === -1) {
                    personList.push(tempPerson)
                }
            }
        }
        for (let i = 0; i < personList.length; i++) {
            let innerDateList = []
            for (let j = 0; j < dateList.length; j++) {
                let obj = {}
                obj[dateList[j]] = 0
                innerDateList.push(obj)
            }
            personList[i]['innerDateList'] = innerDateList
        }
        for (let i = 0; i < equipList.length; i++) {
            let innerDateList = []
            for (let j = 0; j < dateList.length; j++) {
                let obj = {}
                obj[dateList[j]] = 0
                innerDateList.push(obj)
            }
            equipList[i]['innerDateList'] = innerDateList
        }
        for (let i = 0; i < orderList.length; i++) {
            let time = orderList[i].timeslot.date
            let equipmentid = orderList[i].equipment.equipmentid
            // 统计机器工作时间
            for (let k = 0; k < equipList.length; k++) {
                if (equipmentid == equipList[k].id) {
                    for (let n = 0; n < equipList[k].innerDateList.length; n++) {
                        if (Object.keys(equipList[k].innerDateList[n])[0] === time) {
                            equipList[k].innerDateList[n][time]++
                        }
                    }
                }
            }
            // 统计人员工作时间
            for (let j = 0; j < orderList[i].teamList.teamList.length; j++) {
                for (let k = 0; k < personList.length; k++) {
                    if (personList[k].id === orderList[i].teamList.teamList[j].teamid) {
                        for (let m = 0; m < personList[k].innerDateList.length; m++) {
                            if (Object.keys(personList[k].innerDateList[m])[0] === time) {
                                personList[k].innerDateList[m][time]++
                            }
                        }
                    }
                }
            }
        }
        let result = {
            personList: personList,
            equipList: equipList
        }
        return result
    }
}

// 获取订单计划图
export const getOrderPlan = function (id) {
    let session = sessionStorage.getItem('subOrders')
    if (!session) {
        return ''
    } else {
        let orderList = JSON.parse(session)
        let subOrderList = []
        console.log("gun")
        for (let i = 0; i < orderList.length; i++) {
            if (parseInt(orderList[i].parent_id) === parseInt(id)) {
                console.log(orderList[i])
                subOrderList.push(orderList[i])
            }
        }
        console.log(subOrderList)
    }
}

// 时间转换
const getTime = (time) => {
    if (0 <= time && time <= 9) {
        return "0" + time + ":00"
    } else {
        return time + ":00"
    }
}