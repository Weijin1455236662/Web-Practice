<template>
  <div class="main">
    <div class="head">
      <div class="text">资源甘特图</div>
      <hr/>
    </div>
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
import {getSourceGantt} from "../../api/sourceGanttAPI"
export default {
  name: "SourceSchedule",
  data(){
    return{
      type: "gantt",
      width: "95%",
      height: "70%",
      dataFormat: "json",
      dataSource: {}
    }
  },
  methods: {
    calculateTime(val){
      console.log(val)
      if (parseInt(val.slice(0,2)) + 7 <= 24) {
        return (parseInt(val.slice(0,2)) + 7) + val.slice(2)
      } else {
        return "次日" + (parseInt(val.slice(0,2)) - 17) + val.slice(2)
      }
    }
  },
  mounted(){
    let data = getSourceGantt("2020/10/31")
    console.log(data)
    let task = []
    let process = []
    let that = this
    data.forEach(function(item){
      console.log(item)
      task.push({
        processid: item.processid,
        start: item.start,
        end: item.end,
        color: item.color,
        label: item.label + ": "+ that.calculateTime(item.start) + " - " + that.calculateTime(item.end)
      })
    })
    this.dataSource = {
      chart: {
        dateformat: "dd/mm/yyyy",
        outputdateformat: "hh:mn:ss",
        caption: "资源甘特图",
        plottooltext: "$label",
        theme: "fusion"
      },
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
              label: "次日1:00-次日3:00"
            },
            {
              start: "20:00:00",
              end: "21:59:59",
              label: "次日3:00-次日5:00"
            },
            {
              start: "22:00:00",
              end: "23:59:59",
              label: "次日5:00-次日7:00"
            }
          ]
        }
      ],
      tasks: {
        showlabels: "1",
        color: "#5D62B5",
        task: task
      },
      processes: {
        fontsize: "12",
        isbold: "1",
        align: "Center",
        headertext: "生产线/员工",
        headerfontsize: "14",
        headervalign: "middle",
        headeralign: "left",
        process: [
          {
            label: "Line1",
            id: "Line1"
          },
          {
            label: "Line4",
            id: "Line4"
          },
          {
            label: "李四",
            id: "李四"
          },
          {
            label: "小明",
            id: "小明"
          },
          {
            label: "张三",
            id: "张三"
          },
        ]
      },
      datatable: {
        headervalign: "middle",
        datacolumn: [
          {
            headertext: "负载率",
            headervalign: "middle",
            headeralign: "middle",
            align: "middle",
            text: [
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
            ]
          }
        ]
      }
    }
  }
}
</script>

<style scoped lang="less">
.main{
  .head{
    margin: 30px 5% 0;
    text-align: left;
    .text{
      font-size: 32px;
      font-weight: bold;
    }
  }
}
</style>