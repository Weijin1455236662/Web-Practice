import {getScheduleInfo} from "../api/scheduleApi";

export const reduceSource = (date)=>{
    let session = sessionStorage.getItem('subOrders');
    let tomorrow = new Date(date);
    tomorrow = tomorrow.setDate(tomorrow.getDate()+1);
    tomorrow = new Date(tomorrow);
    console.log(date)
    tomorrow = tomorrow.getFullYear()+'-'+(tomorrow.getMonth()+1)+'-'+tomorrow.getDate();
    console.log(tomorrow);
    if(!session){
        return '';
    }else{
        let subOrders = JSON.parse(session);
        console.log(subOrders);
        let data = [];
        let process = [];
        subOrders.forEach(function (subOrder) {
            if((subOrder.timeslot.date===date&&subOrder.timeslot.time>=7)||(subOrder.timeslot.date===tomorrow&&subOrder.timeslot.time<7)){
                subOrder.teamList.teamList.forEach(function (team) {
                    data.push({
                        processid: team.name,
                        // start: subOrder.timeslot.time + ':00:00',
                        // end: (subOrder.timeslot.time + 1) + ':00:00',
                        start: (subOrder.timeslot.time>=7?subOrder.timeslot.time>=17?(subOrder.timeslot.time-7):('0' + subOrder.timeslot.time-7):(subOrder.timeslot.time+17)) + ':00:00',
                        end: (subOrder.timeslot.time>=7?subOrder.timeslot.time>=16?(subOrder.timeslot.time-6):('0' + subOrder.timeslot.time-6):(subOrder.timeslot.time+18)) + ':00:00',
                        label: subOrder.material_code,
                        color: subOrder.material_code===1?"#47F566":"#5188E8"
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
                    start: (subOrder.timeslot.time>=7?subOrder.timeslot.time>=17?(subOrder.timeslot.time-7):('0' + subOrder.timeslot.time-7):(subOrder.timeslot.time+17)) + ':00:00',
                    end: (subOrder.timeslot.time>=7?subOrder.timeslot.time>=16?(subOrder.timeslot.time-6):('0' + subOrder.timeslot.time-6):(subOrder.timeslot.time+18)) + ':00:00',
                    label: subOrder.material_code,
                    color: subOrder.material_code===1?"#47F566":"#5188E8"
                });
                process.push({
                    label: subOrder.equipment.name,
                    id: subOrder.equipment.name
                })
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
        // console.log(data);
        // console.log(uniqueProcess);
        return {
            data: data,
            process: uniqueProcess
        }
    }

};
