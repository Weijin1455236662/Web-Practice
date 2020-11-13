<template>
  <div class="main">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div class="text">生产单</div>
      <hr/>
    </div>
    <div v-if="showList" class="container">
      <div class="group">
        <div class="title">团队列表</div>
        <div class="list">
          <div v-for="(team, index) in teamList" :key="index" class="name">
            <span class="text" @click="nav(team.teamid, 0)">{{team.name}}</span>
          </div>
        </div>
      </div>
      <div class="group">
        <div class="title">设备列表</div>
        <div class="list">
          <div v-for="(equipment, index) in equipmentList" :key="index" class="name">
            <span class="text" @click="nav(equipment.equipmentid, 1)">{{equipment.name}}</span>
          </div>
        </div>
      </div>
    </div>
    <fusioncharts
      v-if="!showList&&flag"
      :type="chart.type"
      :width="chart.width"
      :height="chart.height"
      :dataFormat="chart.dataFormat"
      :dataSource="chart.dataSource"
    ></fusioncharts>
  </div>
</template>

<script>
import {getOrderWorkSchedule} from "../../api/scheduleApi"
import {getAllStaff, getStaffById} from "../../api/staffManageApi"
import {getAllEquipment, getEquipmentById} from "../../api/equipmentManageApi"
import MessageTip from "../../components/MessageTip";
export default {
  name: "WorkSchedule",
    components: {MessageTip},
    data(){
      return{
        chart: {
          type: "gantt",
          width: "90%",
          height: "85%",
          dataFormat: "json",
          dataSource: {}
        },
        showList: true,
        teamList: [],
        equipmentList: [],
        messageState: false,
        messageType: 0,
        message: '',
        flag: false,
        workInfo: {},
        name: "line"
      }
    },
    methods: {
        nav: function(id, type){
            this.$router.push({
                path: '/schedule/work',
                query: {
                    id: id,
                    type: type
                }
            }).catch(()=>{
                console.log()
            });
        },
        showMessage: function(type, message){
            this.messageType = type;
            this.message = message;
            this.messageState = true;
            setTimeout(this.hideMessage,2000);
        },
        hideMessage: function(){
            this.messageState = false;
            setTimeout(function () {
                this.messageType = 0;
                this.message = '';
            },600);
        },
      // 根据工作时间调整日期
      adjustDate: function(date, start) {
        if (0 <= parseInt(start.slice(0,2)) && parseInt(start.slice(0,2)) <= 6) {
          let date1 = new Date(Date.parse(date.replace(/-/g,"/")))
          let date2 = new Date(date1)
          date2.setDate(date1.getDate() - 1)
          let trueDate = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + this.adjustDay(date2.getDate())
          return trueDate
        } else {
          return date
        }
      },
      adjustDay: function(val) {
        if (1 <= val && val <= 9) {
          return "0" + String(val)
        } else {
          return String(val)
        }
      },
      // 根据id获取name
      getNameById: function() {
        if (this.$route.query.type === "0") {
          getStaffById(this.$route.query.id).then(res => {
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
        } else {
            getEquipmentById(this.$route.query.id).then(res=>{
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
        }
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
          color: "#008ee4",
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
        if (uniqueProcess.length === 0) {
        task.push({
          processid: '1',
                start: '00:00:00',
                end: '00:00:00',
                label: 111,
                color: '#5188E8'
        })
        uniqueProcess.push({
          label: '',
                id: '1'
        })
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
      },
        measure: function () {
            let id = this.$route.query.id;
            if (id==='0'){
                this.showList = true;
                getAllStaff().then(res=>{
                    if(res.flag){
                        this.teamList = res.data;
                        getAllEquipment().then(res=>{
                            if(res.flag){
                                this.equipmentList = res.data;
                            }else{
                                this.showMessage(1, '获取设备列表失败！')
                            }
                        }).catch(err=>{
                            this.showMessage(1, '获取设备列表失败！')
                        })
                    }else{
                        this.showMessage(1, '获取团队列表失败！')
                    }
                }).catch(err=>{
                    this.showMessage(1, '获取团队列表失败！')
                })
            }else{
                this.showList = false;
                this.getNameById();
            }
        }
    },
    mounted() {
      this.measure();
    },
    watch: {
      $route : function () {
          this.measure();
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
  .container{
    .group{
      margin: 30px 7% 10px;
      .title{
        text-align: left;
        font-size: 24px;
        font-weight: bold;
      }
      .list{
        display: flex;
        flex-wrap: wrap;
        padding: 16px 0;
        .name{
          width: 25%;
          margin: 6px 0;
          .text{
            color: #5a5a5a;
            text-decoration: underline;
            cursor: pointer;
          }
          .text:hover{
            color: #000000;
          }
        }

      }
    }
  }
}
</style>