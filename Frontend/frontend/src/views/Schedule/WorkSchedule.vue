<template>
  <div class="container">
    <fusioncharts
      :type="type"
      :width="width"
      :height="height"
      :dataFormat="dataFormat"
      :dataSource="dataSource"
    ></fusioncharts>
  </div>
</template>

<script>
    import {getProductionOrder} from "../../api/analogueAPI";

    export default {
        name: "WorkSchedule",
        data(){
            return{
                type: "gantt",
                width: "80%",
                height: "80%",
                dataFormat: "json",
                dataSource: {}
            }
        },
        mounted() {
            let data = getProductionOrder(1,1);
            let task = [];
            let process = [];
            data.forEach(function (order) {
                task.push({
                    processid: order.date,
                    start: parseInt(order.start.slice(0,2))>=7?(parseInt(order.start.slice(0,2))-7)+order.start.slice(2):(parseInt(order.start.slice(0,2))+17)+order.start.slice(2),
                    end: parseInt(order.end.slice(0,2))>=7?(parseInt(order.end.slice(0,2))-7)+order.end.slice(2):(parseInt(order.end.slice(0,2))+17)+order.end.slice(2),
                    label: order.material + ": " + order.start + "-" + order.end
                });
                process.push({
                    label: order.date,
                    id: order.date
                })
            })
            let tasks = {
                showlabels: "1",
                color: "#5D62B5",
                task: task
            };
            let processHash = {};
            let uniqueProcess = [];
            for(let i =0; i<process.length; i++){
                if(!processHash[process[i].id]){
                    uniqueProcess.push(process[i]);
                    processHash[process[i].id] = true;
                }
            }
            let processes = {
                fontsize: "12",
                isbold: "1",
                align: "Center",
                headertext: "日期",
                headerfontsize: "14",
                headervalign: "middle",
                headeralign: "center",
                process: uniqueProcess
            };
            this.dataSource = {
                tasks: tasks,
                processes: processes,
                categories: [
                    {
                        category: [
                            {
                                start: "00:00:00",
                                end: "23:59:59",
                                label: "时间"
                            }
                        ]
                    },
                    {
                        category: [
                            {
                                start: "00:00:00",
                                end: "11:59:59",
                                label: "早班"
                            },
                            {
                                start: "12:00:00",
                                end: "23:59:59",
                                label: "晚班"
                            }
                        ]
                    },
                    {
                        align: "center",
                        category: [
                            {
                                start: "00:00:00",
                                end: "01:59:59",
                                label: "7:00-9:00"
                            },
                            {
                                start: "02:00:00",
                                end: "03:59:59",
                                label: "9:00-11:00"
                            },
                            {
                                start: "04:00:00",
                                end: "05:59:59",
                                label: "11:00-13:00"
                            },
                            {
                                start: "06:00:00",
                                end: "07:59:59",
                                label: "13:00-15:00"
                            },
                            {
                                start: "08:00:00",
                                end: "09:59:59",
                                label: "15:00-17:00"
                            },
                            {
                                start: "10:00:00",
                                end: "11:59:59",
                                label: "17:00-19:00"
                            },
                            {
                                start: "12:00:00",
                                end: "13:59:59",
                                label: "19:00-21:00"
                            },
                            {
                                start: "14:00:00",
                                end: "15:59:59",
                                label: "21:00-23:00"
                            },
                            {
                                start: "16:00:00",
                                end: "17:59:59",
                                label: "23:00-次日1:00"
                            },
                            {
                                start: "18:00:00",
                                end: "19:59:59",
                                label: "次日1:00-3:00"
                            },
                            {
                                start: "20:00:00",
                                end: "21:59:59",
                                label: "次日3:00-5:00"
                            },
                            {
                                start: "22:00:00",
                                end: "23:59:59",
                                label: "次日5:00-7:00"
                            }
                        ]
                    }
                ],
                chart: {
                    dateformat: "dd/mm/yyyy",
                    outputdateformat: "hh12:mn ampm",
                    plottooltext: "这个提示的时间在组装dataSource的时候直接赋值吧",
                    caption: "赵星睿工作安排",
                    subcaption: "",
                    theme: "fusion"
                }
            };
        }
    }
</script>

<style scoped>
  .container{
    width: 100%;
    height: 400px;
    margin-bottom: 100px;
  }
</style>