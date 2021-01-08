import {getScheduleInfo, getTime} from "../api/scheduleApi";

export const reduceSource = (date)=>{
    let session = sessionStorage.getItem('subOrders');
    let tomorrow = new Date(date);
    tomorrow = tomorrow.setDate(tomorrow.getDate()+1);
    tomorrow = new Date(tomorrow);
    tomorrow = tomorrow.getFullYear()+'-'+((tomorrow.getMonth()+1)>9?tomorrow.getMonth()+1:('0'+(tomorrow.getMonth()+1)))+'-'+(tomorrow.getDate()>9?tomorrow.getDate():'0'+tomorrow.getDate());
    if(!session){
        return '';
    }else{
        let subOrders = JSON.parse(session);
        let data = [];
        let process = [];
        let load = [
            {
                label: "80%",
            },
            {
                label: "40%"
            },
            {
                label: "60%"
            },
            {
                label: "60%"
            },
            {
                label: "78%",
            },
        ];
        let colorDic = {
            0: {
                0:'#f5cb84',
                1: '#cdac00',
                2: '#fa6604',
                3: '#fa6402',
                },
            1: {
                0:'#51b6ff',
                1: '#2d81cb',
                2: '#3c6def',
                3: '#1150b1',
            },
            2: {
                0:'#785fff',
                1: '#6a30f3',
                2: '#4820fa',
                3: '#a74bf6',
            }
        }
        let typeDic = {
            0: '打弹片',
            1: '装配',
            2: '测试'
        }
        subOrders.forEach(function (subOrder) {
            if(subOrder.timeslot!==null){
                if((subOrder.timeslot.date===date&&subOrder.timeslot.time>=7)||(subOrder.timeslot.date===tomorrow&&subOrder.timeslot.time<7)){
                    subOrder.teamList.teamList.forEach(function (team) {
                        data.push({
                            processid: team.name,
                            // start: subOrder.timeslot.time + ':00:00',
                            // end: (subOrder.timeslot.time + 1) + ':00:00',
                            start: getTime(subOrder.timeslot.time),
                            end: subOrder.timeslot.time,
                            label: subOrder.id + '<br/>订单：' + subOrder.parent_id + '<br/>物料：' + subOrder.material_code + '<br/>环节：' + typeDic[subOrder.type],
                            color: colorDic[parseInt(subOrder.type)][parseInt(subOrder.parent_id)%4]
                        });
                        process.push({
                            label: team.name,
                            id: team.name
                        })
                    });
                    data.push({
                        processid: subOrder.equipment.name,
                        // start: subOrder.timeslot.time + ':00:00',
                        // end: (subOrder.timeslot.time + 1) + ':00:00',
                        // start: (subOrder.timeslot.time>=7?subOrder.timeslot.time>=17?(subOrder.timeslot.time-7):('0' + subOrder.timeslot.time-7):(subOrder.timeslot.time+17)) + ':00:00',
                        // end: (subOrder.timeslot.time>=7?subOrder.timeslot.time>=16?(subOrder.timeslot.time-6):('0' + subOrder.timeslot.time-6):(subOrder.timeslot.time+18)) + ':00:00',
                        start: getTime(subOrder.timeslot.time),
                        end: subOrder.timeslot.time+1,
                        label: subOrder.id + '<br/>订单：' + subOrder.parent_id + '<br/>物料：' + subOrder.material_code + '<br/>环节：' + typeDic[subOrder.type],
                        color: colorDic[parseInt(subOrder.type)][parseInt(subOrder.parent_id)%4]
                    });
                    process.push({
                        label: subOrder.equipment.name,
                        id: subOrder.equipment.name
                    })
                }
            }
        });
        let processHash = {};
        let uniqueProcess = [];
        for(let i =0; i<process.length; i++){
            if(!processHash[process[i].id]){
                uniqueProcess.push(process[i]);
                processHash[process[i].id] = true;
            }
        }
        if(uniqueProcess.length===0){
            data.push({
                processid: '1',
                start: '00:00:00',
                end: '00:00:00',
                label: 111,
                color: '#5188E8'
            });
            uniqueProcess.push({
                label: '',
                id: '1'
            });
            load = [];
        }
        return {
            data: data,
            process: uniqueProcess,
            load: load
        }
    }

};
