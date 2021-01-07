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
            0: '#47F566',
            1: '#5188E8',
            2: '#f1e515',
            3: '#b351e8',
            4: '#e88d51',
            5: '#51e8db',
            6: '#5b51e8'
        }
        subOrders.forEach(function (subOrder) {
            if(subOrder.timeslot!==null){
                if((subOrder.timeslot.date===date&&subOrder.timeslot.time>=7)||(subOrder.timeslot.date===tomorrow&&subOrder.timeslot.time<7)){
                    subOrder.teamList.teamList.forEach(function (team) {
                        console.log(subOrder)
                        console.log(team)
                        data.push({
                            processid: team.name,
                            // start: subOrder.timeslot.time + ':00:00',
                            // end: (subOrder.timeslot.time + 1) + ':00:00',
                            start: getTime(subOrder.timeslot.time),
                            end: getTime(subOrder.timeslot.time+1),
                            label: subOrder.id + '<br/>物料：' + subOrder.material_code,
                            color: colorDic[parseInt(subOrder.material_code)%7]
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
                        end: getTime(subOrder.timeslot.time+1),
                        label: subOrder.id + '<br/>物料：' + subOrder.material_code,
                        color: colorDic[parseInt(subOrder.material_code)%7]
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
        // console.log(data);
        // console.log(uniqueProcess);
        return {
            data: data,
            process: uniqueProcess,
            load: load
        }
    }

};
