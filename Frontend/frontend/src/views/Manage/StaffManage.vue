<template>
  <div class="main">
    <div v-if="showMessage" class="tip" :class="messageType===0?'green':'red'">{{message}}</div>
    <div class="head">
      <div class="text">人员管理</div>
      <hr/>
    </div>
    <div class="container">
      <div class="card" v-for="(staff,index) in allStaff" :key="index">
        <div class="group">
          <div class="name"><i class="icon-font i-user"></i><div class="text">{{staff.name}}</div></div>
          <div class="id"><div class="number">{{staff.teamid}}</div><div class="text">组</div></div>
        </div>
        <div class="group">
          <div class="num"><div class="number">{{staff.num}}</div><div class="text">人</div></div>
          <div>
            <div class="day">{{weekDic[staff.begin_day]}}-{{weekDic[staff.end_day]}}</div>
            <div class="time">{{staff.begin_time}}:00-<span v-if="staff.begin_time>staff.end_time">次日</span>{{staff.end_time}}:00</div>
          </div>
        </div>
        <div class="toolbox">
          <i class="icon-font i-edit tooltip" @click="updateStaff(staff)"></i>
          <i class="icon-font i-delete tooltip" @click="deleteStaff(staff.teamid)"></i>
        </div>
      </div>
      <div class="card dash" @click="addStaff">
        <div class="plus">
          <i class="icon-font i-add"></i>
        </div>
      </div>
    </div>
    <div class="background" v-if="showForm" @click="closeForm"></div>
    <div class="form" v-if="showForm">
      <div>{{formType===0?'新增人员':'修改人员'}}</div>
      <div>
        <div>
          <label for="teamid">组号</label>
          <input id="teamid" type="number" min="1" v-model="form.teamid"/>
        </div>
        <div>
          <label for="name">组长</label>
          <input id="name" type="text" v-model="form.name"/>
        </div>
        <div>
          <label for="num">人数</label>
          <input id="num" type="number" min="1" v-model="form.num"/>
        </div>
        <div>
          <label for="begin_day">工作日</label>
          <select id='begin_day' v-model="form.begin_day">
            <option :value="1">星期一</option>
            <option :value="2">星期二</option>
            <option :value="3">星期三</option>
            <option :value="4">星期四</option>
            <option :value="5">星期五</option>
            <option :value="6">星期六</option>
            <option :value="7">星期日</option>
          </select>
          <label for="end_day">-</label>
          <select id='end_day' v-model="form.end_day">
            <option :value="1">星期一</option>
            <option :value="2">星期二</option>
            <option :value="3">星期三</option>
            <option :value="4">星期四</option>
            <option :value="5">星期五</option>
            <option :value="6">星期六</option>
            <option :value="7">星期日</option>
          </select>
        </div>
        <div>
          <label for="time">班次</label>
          <select id='time' v-model="timeMode">
            <option :value="0">早班</option>
            <option :value="1">晚班</option>
          </select>
        </div>
      </div>
      <div>
        <button @click="closeForm">取消</button>
        <button @click="submitForm" v-if="formType===0">添加</button>
        <button @click="submitForm" v-if="formType===1">更新</button>
      </div>
    </div>
  </div>
</template>

<script>
    import {addStaff, deleteStaff, getAllStaff, updateStaff} from "../../api/api";

    export default {
        name: "StaffManage",
        data(){
            return{
                allStaff: [],
                weekDic: {
                    1: '星期一',
                    2: '星期二',
                    3: '星期三',
                    4: '星期四',
                    5: '星期五',
                    6: '星期六',
                    7: '星期日',
                },
                showForm: false,
                formType: 0,
                timeMode: 0,
                messageState: false,
                messageType: 0,
                message: '',
                form:{
                    teamid: '',
                    name: '',
                    num: '',
                    begin_day: 1,
                    end_day: 5,
                    begin_time: 7,
                    end_time: 19
                }
            }
        },
        mounted() {
            this.getAllStaff();
        },
        watch:{
            'timeMode': function () {
                this.changeFormTime();
            }
        },
        methods:{
            openForm: function(){
                this.showForm = true;
            },
            closeForm: function () {
                this.form = {
                    teamid: '',
                    name: '',
                    num: '',
                    begin_day: 1,
                    end_day: 5,
                    begin_time: 7,
                    end_time: 19
                };
                this.showForm = false;
            },
            changeFormTime: function(){
                if(this.timeMode===0){
                    this.form.begin_time = 7;
                    this.form.end_time = 19;
                }else if(this.timeMode===1){
                    this.form.begin_time = 19;
                    this.form.end_time = 7;
                }
            },
            showMessage: function(type, message){
                this.messageType = type;
                this.message = message;
                this.messageState = true;
                setTimeout(this.hideMessage,3000);
            },
            hideMessage: function(){
                this.messageType = 0;
                this.message = '';
                this.messageState = false;
            },
            getAllStaff: function () {
                getAllStaff().then(res=>{
                    if(res.flag){
                        this.allStaff = res.data;
                    }else{
                        this.showMessage(1, res.message);                    }
                })
            },
            addStaff: function(){
                this.formType = 0;
                this.form = {
                    teamid: '',
                    name: '',
                    num: '',
                    begin_day: 1,
                    end_day: 5,
                    begin_time: 7,
                    end_time: 19
                };
                this.openForm();
            },
            updateStaff: function(staff){
                this.formType = 1;
                this.form.teamid = staff.teamid;
                this.form.name = staff.name;
                this.form.num = staff.num;
                this.form.begin_day = staff.begin_day;
                this.form.end_day = staff.end_day;
                if(staff.begin_time>=7&&staff.begin_time<19){
                    this.timeMode = 0;
                }else{
                    this.timeMode = 1;
                }
                this.openForm();
            },
            deleteStaff: function (teamid) {
                deleteStaff(teamid).then(res=>{
                    if(res.flag){
                        this.showMessage(0, '删除成功！');
                        this.getAllStaff();
                    }else{
                        this.showMessage(1, res.message);
                    }
                })
            },
            submitForm: function () {
                this.form.teamid = Number(this.form.teamid);
                this.form.num = Number(this.form.num);
                this.changeFormTime();
                if(this.formType===0){
                    addStaff(this.form).then(res=>{
                        if(res.flag){
                            this.showMessage(0, '添加成功！');
                            this.closeForm();
                            this.getAllStaff();
                        }else{
                            this.showMessage(1, res.message);
                        }
                    })
                }else{
                    updateStaff(this.form).then(res=>{
                        if(res.flag){
                            this.showMessage(0, '更新成功！');
                            this.closeForm();
                            this.getAllStaff();
                        }else{
                            this.showMessage(1, res.message);
                        }
                    })
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
    .container{
      margin: 10px 5%;
      /*border: 1px solid #000000;*/
      text-align: left;
      display: flex;
      flex-wrap: wrap;

      .card{
        width: 20%;
        height: 150px;
        margin: 20px 1%;
        border: 6px solid #000000;
        padding: 0 10px 10px;
        background-color: #FFFFFF;

        .group{
          display: flex;
          justify-content: space-between;
        }
        .id{
          display: flex;
          font-weight: bold;
          .number{
            font-size: 56px;
            color: #000000;
          }
          .text{
            margin: 34px 0 0 4px;
            font-size: 20px;
          }
        }
        .name{
          margin-top: 6px;
          display: flex;
          .i-user{
            margin-top: 1px;
            font-size: 50px;
          }
          .text{
            margin-top: 28px;
            font-size: 20px;
            font-weight: bold;
          }
        }
        .num{
          display: flex;
          margin-left: 8px;
          .number{
            font-size: 32px;
            color: #000000;
          }
          .text{
            font-size: 20px;
            line-height: 44px;
          }
        }
        .day{
          margin-top: 6px;
        }
        .time{
          text-align: right;
        }
        .toolbox{
          margin-top: 8px;
          .tooltip{
            font-size: 24px;
          }
        }
      }
      .dash{
        border: 6px dashed #000000;
        cursor: pointer;
      }
      .plus{
        margin: 45px 0;
        text-align: center;
        .i-add{
          font-size: 60px;
        }
      }
    }
    .background{
      background-color: #000000;
      opacity: 0.8;
      position: fixed;
      z-index: 200;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
    }
    .form{
      background-color: #FFFFFF;
      position: fixed;
      z-index: 300;
      top: 10%;
      left: 30%;
      width: 40%;
      height: 80%;
      text-align: left;
    }
    .tip{
      position: fixed;
      width: 100px;
      height: 36px;
    }
    .green{
      color: green;
    }
    .red{
      color: red;
    }
  }
</style>