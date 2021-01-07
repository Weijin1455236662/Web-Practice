import axios from 'axios';
import data from '../assets/data1.json'

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.baseURL = process.env.NODE_ENV === 'production'
    ? 'http://101.200.166.56:8081'
    : 'http://localhost:8081';

export const getScheduleInfo = (startDate, endDate) => {
    return  axios.get('/arrangement/' + startDate + '/' + endDate).then(res => res.data)
}

export const getLocalScheduleInfo = () =>{
    return data;
}

// 获取生产单
export const getOrderWorkSchedule = (id, type) => {
    let session = sessionStorage.getItem('subOrders')
    if (!session) {
        return ''
    } else {
        let orderlist = JSON.parse(session)
        // console.log(orderlist[0].timeslot)
        // function sortTime (a, b){
        //     console.log(a,b)
        //     if(a.timeslot.date<b.timeslot.date){
        //         console.log('0')
        //         return -1;
        //     }else if(a.timeslot.date>b.timeslot.date){
        //         console.log('1')
        //         return 1;
        //     }else{
        //         console.log('2')
        //         return a.timeslot.time<a.timeslot.time;
        //     }
        // }
        // let a = orderlist.sort(sortTime);
        let tasks = []
        console.log(orderlist)
        // console.log(a);
        if (type === '0') {
            for (let i = 0; i < orderlist.length; i++) {
                if (orderlist[i].teamList !== null) {
                    let size = orderlist[i].teamList.teamList.length
                    for (let j = 0; j < size; j++) {
                        if (parseInt(id) === orderlist[i].teamList.teamList[j].teamid) {
                            console.log(1)
                            if (orderlist[i].timeslot !== null) {
                                tasks.push({
                                    date: orderlist[i].timeslot.date,
                                    start: getTime(orderlist[i].timeslot.time),
                                    end: getTime(orderlist[i].timeslot.time + 1),
                                    material: orderlist[i].material_code,
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
                            material: orderlist[i].material_code,
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
        console.log(orderList)
        let orderSchedule = []
        for (let i = 0; i < idList.length; i++) {
            let sum0 = 0
            let sum = 0
            let sum1 = 0
            let subOrderNum0 = 0
            let subOrderNum = 0
            let subOrderNum1 = 0
            for (let j = 0; j < orderList.length; j++) {
                if (orderList[j].timeslot !== null&&orderList[j].timeslot.date<=date) {
                    if (parseInt(idList[i].id) === parseInt(orderList[j].parent_id)) {
                        if (orderList[j].type === 0) {
                            sum0 += 1
                        } else if (orderList[j].type === 1){
                            sum += 1
                        } else if (orderList[j].type === 2){
                            sum1 += 1
                        }
                    }
                }
            }
            for (let j = 0; j < orderList.length; j++) {
                if (orderList[j].timeslot !== null) {
                    if (parseInt(idList[i].id) === parseInt(orderList[j].parent_id)) {
                        if (orderList[j].type === 0) {
                            subOrderNum0 += 1
                        } else if (orderList[j].type === 1){
                            subOrderNum += 1
                        } else if (orderList[j].type === 2){
                            subOrderNum1 += 1
                        }
                    }
                }
            }
            let counter0 = 0
            let counter = 0;
            let counter1 = 0
            let completeDate0 = ''
            let completeDate = ''
            let completeDate1 = ''
            for (let j = 0; j < orderList.length; j++) {
                if (orderList[j].timeslot !== null) {
                    if (parseInt(idList[i].id) === parseInt(orderList[j].parent_id)) {
                        if (orderList[j].type === 0) {
                            counter0 += 1
                        } else if (orderList[j].type === 1){
                            counter += 1
                        } else if (orderList[j].type === 2){
                            counter1 += 1
                        }
                    }
                    if (counter0 === subOrderNum0) {
                        completeDate0 = orderList[j].timeslot.date
                        counter0 = -100
                    } else if (counter === subOrderNum) {
                        completeDate = orderList[j].timeslot.date
                        counter = -100
                    } else if (counter1 === subOrderNum1) {
                        completeDate1 = orderList[j].timeslot.date
                        counter1 = -100
                    }
                }
            }
            console.log(counter0, counter, counter1)
            orderSchedule.push({
                id: parseInt(idList[i].id),
                value0: (sum0 / idList[i].quantity * 100) < 100 ? (sum0 / idList[i].quantity * 100).toString() + "%" : "100%",
                value: (sum / idList[i].quantity * 100) < 100 ? (sum / idList[i].quantity * 100).toString() + "%" : "100%",
                value1: (sum1 / idList[i].quantity * 100) < 100 ? (sum1 / idList[i].quantity * 100).toString() + "%" : "100%",
                planDate: idList[i].delivery_date,
                actualDate0: completeDate0,
                actualDate: completeDate,
                actualDate1: completeDate1,
            })
        }
        console.log(orderSchedule)
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
        for (let i = 0; i < orderList.length; i++) {
            if (parseInt(orderList[i].parent_id) === parseInt(id)) {
                subOrderList.push({
                    date: orderList[i].timeslot.date,
                    start: getTime(orderList[i].timeslot.time),
                    end: getTime(orderList[i].timeslot.time + 1),
                    id: orderList[i].id,
                    material: orderList[i].material_code
                })
            }
        }
        return subOrderList
    }
}

// 时间转换
export const getTime = (time) => {
    let result = '';
    if (0 <= (time/60).toFixed(0) && (time/60).toFixed(0) <= 9) {
        result += '0' + (time/60).toFixed(0);
    } else {
        result += (time/60).toFixed(0);
    }
    if(0 <= time%60 && time%60 <= 9){
        result += ':0' + time % 60;
    } else{
        result += ':' + time % 60;
    }
    return result
}
