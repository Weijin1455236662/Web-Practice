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
    export default {
        name: "SingleSourceGantt",
        data(){
            return{
                type: "gantt",
                width: "80%",
                height: "100%",
                dataFormat: "json",
                dataSource: {}
            }
        },
        mounted() {
            this.dataSource = {
                tasks: {
                    showlabels: "1",
                    color: "#5D62B5",
                    task: [
                        {
                            processid: "EMP120",
                            start: "07:00:00",
                            end: "16:00:00",
                            label: "Morning Shift"
                        },
                        {
                            processid: "EMP121",
                            start: "14:00:00",
                            end: "22:00:00",
                            label: "Afternoon Shift"
                        },
                        {
                            processid: "EMP121",
                            start: "07:00:00",
                            end: "11:30:00",
                            label: "Half Day"
                        },
                        {
                            processid: "EMP122",
                            start: "14:00:00",
                            end: "18:30:00",
                            label: "Half Day"
                        },
                        {
                            processid: "EMP123",
                            start: "07:00:00",
                            end: "16:00:00",
                            label: "Morning Shift"
                        },
                        {
                            processid: "EMP124",
                            start: "14:00:00",
                            end: "22:00:00",
                            label: "Afternoon Shift"
                        },
                        {
                            processid: "EMP125",
                            start: "00:00:00",
                            end: "08:00:00",
                            label: "Early Morning support"
                        },
                        {
                            processid: "EMP126",
                            start: "07:00:00",
                            end: "11:30:00",
                            label: "Half Day"
                        }
                    ]
                },
                processes: {
                    fontsize: "12",
                    isbold: "1",
                    align: "Center",
                    headertext: "Date",
                    headerfontsize: "14",
                    headervalign: "middle",
                    headeralign: "center",
                    process: [
                        {
                            label: "2018/10/01",
                            id: "EMP120"
                        },
                        {
                            label: "2018/10/02",
                            id: "EMP121"
                        },
                        {
                            label: "2018/10/03",
                            id: "EMP122"
                        },
                        {
                            label: "2018/10/04",
                            id: "EMP123"
                        },
                        {
                            label: "2018/10/05",
                            id: "EMP124"
                        },
                        {
                            label: "2018/10/06",
                            id: "EMP125"
                        },
                        {
                            label: "2018/10/07",
                            id: "EMP126"
                        }
                    ]
                },
                categories: [
                    {
                        category: [
                            {
                                start: "00:00:00",
                                end: "23:59:59",
                                label: "Time"
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
    height: 500px;
    margin-bottom: 100px;
  }
</style>