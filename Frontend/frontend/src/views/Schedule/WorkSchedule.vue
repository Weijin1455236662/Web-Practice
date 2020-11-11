<template>
  <div class="main">
    <div class="head">
      <div class="text">生产单</div>
      <hr/>
    </div>
    <fusioncharts
      v-if="flag"
      :type="chart.type"
      :width="chart.width"
      :height="chart.height"
      :dataFormat="chart.dataFormat"
      :dataSource="chart.dataSource"
    ></fusioncharts>
  </div>
</template>

<script>
import {getScheduleInfo, getOrderWorkSchedule} from "../../api/scheduleApi"
import {getStaffById} from "../../api/staffManageApi"
export default {
  name: "WorkSchedule",
    data(){
      return{
        chart: {
          type: "gantt",
          width: "90%",
          height: "85%",
          dataFormat: "json",
          dataSource: {}
        },
        flag: false,
        workInfo: {},
        name: "line"
      }
    },
    methods: {
      // 根据工作时间调整日期
      adjustDate: function(date, start) {
        if (0 <= parseInt(start.slice(0,2)) && parseInt(start.slice(0,2)) <= 6) {
          let date1 = new Date(Date.parse(date.replace(/-/g,"/")))
          let date2 = new Date(date1)
          date2.setDate(date1.getDate() - 1)
          let trueDate = date2.getFullYear() + "/" + (date2.getMonth() + 1) + "/" + date2.getDate()
          return trueDate
        } else {
          return date
        }
      },
      // 根据id获取name
      getNameById: function() {
        getStaffById(this.$route.query.id).then(res => {
          console.log(res.data.name);
          this.name = res.data.name;
          let that = this;
          let id = this.$route.query.id;
          let type = this.$route.query.type;
          let data = getOrderWorkSchedule(id, type)
          if (data === '') {
            let timer = setInterval(function () {
              let data = getOrderWorkSchedule(id, type)
              if (data !== ''){
                  clearInterval(timer)
                  that.render(data)
              }
            }, 100)
          } else {
              this.render(data)
          }
        })
      },
      // 绘图
      render: function(worklist){
        let task = []
        let process = []
        let that = this
        console.log(that.name)
        worklist.forEach(function(item){
          task.push({
            processid: that.adjustDate(item.date, item.start),
            start: parseInt(item.start.slice(0,2))>=7?(parseInt(item.start.slice(0,2))-7)+item.start.slice(2):(parseInt(item.start.slice(0,2))+17)+item.start.slice(2),
            end: parseInt(item.end.slice(0,2))>=7?(parseInt(item.end.slice(0,2))-7)+item.end.slice(2):(parseInt(item.end.slice(0,2))+17)+item.end.slice(2),
            label: item.material + ": " + item.start + "-" + item.end
          })
          process.push({
            label: that.adjustDate(item.date, item.start),
            id: that.adjustDate(item.date, item.start)
          })
        })
        let tasks = {
          showlabels: "0",
          color: "#5D62B5",
          task: task
        }
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
        }
        that.chart.dataSource = {
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
          chart: 
          {
            dateformat: "dd/mm/yyyy",
            outputdateformat: "hh12:mn ampm",
            plottooltext: "$label",
            caption: this.name + "工作安排",
            theme: "fusion"
          }
        }
        this.flag = true
      }
      // 合并数组
      // merge: function(task) {
      //   let len = task.length
      //   let ans = []
      //   let start
      //   let end
      //   let day
      //   let material
      //   for (let i = 0; i < len; i++) {
      //     let s = task[i].start
      //     let e = task[i].end
      //     let d = task[i].precessid
      //     let m = task[i].label.split(":")[0]
      //     if (start === undefined || material !== m) {
      //       start = s
      //       end = e
      //       day = d
      //       material = m
      //     } else {
      //       if (d === day) {
      //         if (m === material) {
      //           if (s === end) {
      //             end = e
      //             day = d
      //             material = m
      //           } else {
      //             ans.push({
      //               processid: day,
      //               start: start,
      //               end: end,
      //               material: material
      //             })
      //             start = s
      //             end = e
      //             day = d
      //             material = m
      //           }
      //         } else {
      //           start = undefined
      //           end = undefined
      //           day = undefined
      //           material = undefined
      //         }
      //       }
      //     }
      //   }
      //   if (start !== undefined) {
      //     ans.push({
      //       processid: day,
      //       start: start,
      //       end: end,
      //       material: material
      //     })
      //   }
      //   return ans
      // }
    },
    mounted() {
      this.getNameById();
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