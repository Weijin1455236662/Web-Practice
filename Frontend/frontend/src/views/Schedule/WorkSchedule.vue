<template>
  <div :class="isPC?'main':'mobile'">
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
    <div style="margin: 10px 5% 0;">
      <fusioncharts
          v-if="!showList&&flag"
          :type="chart.type"
          :width="chart.width"
          :height="chart.height"
          :dataFormat="chart.dataFormat"
          :dataSource="chart.dataSource"
      ></fusioncharts>
    </div>
  </div>
</template>

<script>
    import {getOrderWorkSchedule, getTime} from "../../api/scheduleApi"
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
          width: "100%",
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
        name: "line",
        isPC: true
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
        let colorDic = {
          0: '#47F566',
          1: '#5188E8',
          2: '#f1e515',
          3: '#b351e8',
          4: '#e88d51',
          5: '#51e8db',
          6: '#5b51e8'
        }
        let task = []
        let process = []
        let that = this
        console.log(that.name)
        worklist.forEach(function(item){
          task.push({
            processid: that.adjustDate(item.date, item.start),
            start: item.start,
            end: getTime(item.end+3),
            // start: parseInt(item.start.slice(0,2))>=7?(parseInt(item.start.slice(0,2))-7)+item.start.slice(2):(parseInt(item.start.slice(0,2))+17)+item.start.slice(2),
            // end: parseInt(item.end.slice(0,2))>=7?(parseInt(item.end.slice(0,2))-7)+item.end.slice(2):(parseInt(item.end.slice(0,2))+17)+item.end.slice(2),
            label: "物料" + item.material + '<br/>订单: ' + item.parent + '<br/>环节: ' + item.type + "<br/>时间: " + item.start + "-" + getTime(item.end+1),
            color: item.color
          })
          process.push({
            label: that.adjustDate(item.date, item.start),
            id: that.adjustDate(item.date, item.start)
          })
        })
        let tasks = {
          showlabels: "0",
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
        let category = []
        if (that.isPC === true) {
          category = [
            {
              start: "00:00:00",
              end: "01:59:59",
              label: "0:00-2:00"
            },
            {
              start: "02:00:00",
              end: "03:59:59",
              label: "2:00-4:00"
            },
            {
              start: "04:00:00",
              end: "05:59:59",
              label: "4:00-6:00"
            },
            {
              start: "06:00:00",
              end: "07:59:59",
              label: "6:00-8:00"
            },
            {
              start: "08:00:00",
              end: "09:59:59",
              label: "8:00-10:00"
            },
            {
              start: "10:00:00",
              end: "11:59:59",
              label: "10:00-12:00"
            },
            {
              start: "12:00:00",
              end: "13:59:59",
              label: "12:00-14:00"
            },
            {
              start: "14:00:00",
              end: "15:59:59",
              label: "14:00-16:00"
            },
            {
              start: "16:00:00",
              end: "17:59:59",
              label: "16:00-18:00"
            },
            {
              start: "18:00:00",
              end: "19:59:59",
              label: "18:00-20:00"
            },
            {
              start: "20:00:00",
              end: "21:59:59",
              label: "20:00-22:00"
            },
            {
              start: "22:00:00",
              end: "23:59:59",
              label: "22:00-24:00"
            }
          ]
        } else {
          category = [
            {
              start: "00:00:00",
              end: "01:59:59",
              label: "0"
            },
            {
              start: "02:00:00",
              end: "03:59:59",
              label: "2"
            },
            {
              start: "04:00:00",
              end: "05:59:59",
              label: "4"
            },
            {
              start: "06:00:00",
              end: "07:59:59",
              label: "6"
            },
            {
              start: "08:00:00",
              end: "09:59:59",
              label: "8"
            },
            {
              start: "10:00:00",
              end: "11:59:59",
              label: "10"
            },
            {
              start: "12:00:00",
              end: "13:59:59",
              label: "12"
            },
            {
              start: "14:00:00",
              end: "15:59:59",
              label: "14"
            },
            {
              start: "16:00:00",
              end: "17:59:59",
              label: "16"
            },
            {
              start: "18:00:00",
              end: "19:59:59",
              label: "18"
            },
            {
              start: "20:00:00",
              end: "21:59:59",
              label: "20"
            },
            {
              start: "22:00:00",
              end: "23:59:59",
              label: "22"
            }
          ]
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
              align: "center",
              category: category
            }
          ],
          chart:
          {
            dateformat: "dd/mm/yyyy",
            outputdateformat: "hh12:mn ampm",
            plottooltext: "$label",
            caption: this.name + "工作安排",
            captionFontSize: 14,
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
      if(sessionStorage.getItem('equipment')==='Mobile'){
        this.isPC = false;
      }
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
.mobile{
  width: 100vw;
  .head{
    margin: 12px 5% 0;
    text-align: left;
    .text{
      font-size: 16px;
      font-weight: bold;
    }
  }
  .container{
    .group{
      margin: 30px 5% 10px;
      .title{
        text-align: left;
        font-size: 14px;
        font-weight: bold;
      }
      .list{
        display: flex;
        flex-wrap: wrap;
        padding: 16px 0;
        .name{
          width: 25%;
          /*height: 30px;*/
          margin: 8px 0px;
          .text{
            font-size: 12px;
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
