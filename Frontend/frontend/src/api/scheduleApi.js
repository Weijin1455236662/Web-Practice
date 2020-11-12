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
        } else if (type == "line") {
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
                if (orderList[j].timeslot !== null) {
                    if (parseInt(idList[i].id) === parseInt(orderList[j].parent_id)) {
                        subOrderNum += 1
                        sum = sum + parseInt(orderList[j].quantity)
                    }
                }
            }
            let counter = 0
            let completeDate
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
        let innerDateList = []
        for (let i = 0; i < dateList.length; i++) {
            let obj = {}
            obj[dateList[i]] = 0
            innerDateList.push(obj)
        }
        let orderList = JSON.parse(session)
        let personList = []
        let equipList = []
        for (let i = 0; i < orderList.length; i++) {
            let tempEquip = {
                id: orderList[i].equipment.equipmentid,
                name: orderList[i].equipment.name,
                innerDateList: innerDateList
            }
            if (JSON.stringify(equipList).indexOf(JSON.stringify(tempEquip))=== -1) {
                equipList.push(tempEquip)
            }
            for (let j = 0; j < orderList[i].teamList.teamList.length; j++) {
                let tempPerson = {
                    id: orderList[i].teamList.teamList[j].teamid,
                    name: orderList[i].teamList.teamList[j].name,
                    innerDateList: innerDateList
                }
                if (JSON.stringify(personList).indexOf(JSON.stringify(tempPerson)) === -1) {
                    personList.push(tempPerson)
                }
            }
        }
        for (let i = 0; i < orderList.length; i++) {
            let time = orderList[i].timeslot.date
            let equipmentid = orderList[i].equipment.equipmentid
            console.log(equipmentid)
            // let personidList = []
            // for (let j = 0; j < orderList[i].teamList.teamList.length; j++) {
            //     personidList.push(orderList[i].teamList.teamList[j].teamid)
            // }
            for (let k = 0; k < equipList.length; k++) {
                if (equipmentid = equipList[k].id) {
                    for (let m = 0; m < 7; m++) {
                        if (Object.keys(equipList[k].innerDateList[m])[0] === time) {
                            console.log(equipList[k])
                            console.log(equipList[k].innerDateList[m][time])
                            equipList[k].innerDateList[m][time]++
                            break
                        }
                    }
                    break
                }
            }
        }
        // for (let i = 0; i < dateList.length; i++) {
        //     for (let j = 0; j < orderList.length; j++) {
        //         if (dateList[i] === orderList[j].timeslot.date) {
        //             // 设备负载率累加
        //             for (let k = 0; k < equipList.length; k++) {
        //                 if (orderList[j].equipment.equipmentid === equipList[k].id) {
        //                     let key = dateList[i]
        //                     for (let m = 0; m < 7; m++) {
        //                         if (Object.keys(equipList[k].innerDateList[m])[0] === key) {
        //                             console.log(equipList[k])
        //                             // let num = equipList[k].innerDateList[m][key] + 1
        //                             equipList[k].innerDateList[m][key]++
        //                             break
        //                         }
        //                     }
        //                 }
        //             }
        //         }
        //     }
        // }
        console.log(personList)
        console.log(equipList)
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