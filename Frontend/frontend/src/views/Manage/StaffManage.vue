<template>
  <div :class="isPC?'main':'mobile'">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div class="text">团队管理</div>
      <hr/>
    </div>
    <div class="container">
      <div class="card" v-for="(staff,index) in allStaff" :key="index">
        <div class="group">
          <div class="name"><i class="icon-font i-user-group"></i><div class="text">{{staff.name.indexOf('组')!==-1?staff.name.split('-')[1]:staff.name}}</div></div>
          <div v-if="staff.name.indexOf('组')!==-1" class="id"><div class="number">{{staff.name.split('组')[0]}}</div><div class="text">组</div></div>
        </div>
        <div class="group">
          <div class="num">
            <div class="text">成员:</div>
            <div class="number">{{staff.num}}</div>
            <div class="text">人</div></div>
          <div>
            <div class="day">{{weekDic[staff.begin_day]}}-{{weekDic[staff.end_day]}}</div>
            <div class="time">{{staff.begin_time}}:00-<span v-if="staff.begin_time>staff.end_time">次日</span>{{staff.end_time + 1}}:00</div>
          </div>
        </div>
        <div class="toolbox">
          <i class="icon-font i-edit tooltip" title="修改团队" @click="updateStaff(staff)"></i>
          <i class="icon-font i-delete tooltip" title="删除团队" @click="deleteStaff(staff.teamid)"></i>
          <i class="icon-font i-schedule tooltip" title="查看生产单" @click="navSourceSchedule(staff.teamid)"></i>
        </div>
      </div>
      <div class="card dash" title="新增团队" @click="addStaff">
        <div class="plus">
          <i class="icon-font i-add"></i>
        </div>
      </div>
    </div>
    <div class="background" v-if="showForm" @click="closeForm"></div>
    <div class="form" v-if="showForm">
      <div class="form_head">
        <div class="text">{{formType===0?'新增团队':'更新团队'}}</div>
        <div class="close" @click="closeForm"><i class="icon-font i-close"></i></div>
        <hr/>
      </div>
      <div class="form_body">
        <div class="form_id line" v-if="formType!==0&&form.name.indexOf('组')!==-1">
          <label id="teamid_label" v-html="'组&#8194;&#8194;号：'"></label>
          <div id="teamid_disable">{{form.name.split('组')[0]}}</div>
        </div>
        <div class="form_name line">
          <label id="name_label" for="name" v-html="'组&#8194;&#8194;长：'"></label>
          <input id="name" type="text" v-model="form.name"/>
        </div>
        <div class="form_num line">
          <label id="num_label" for="num" v-html="'人&#8194;&#8194;数：'"></label>
          <input id="num" type="number" min="1" v-model="form.num"/>
        </div>
        <div class="form_day line">
          <label id="day_label" for="begin_day" v-html="'工作日：'"></label>
          <select id='begin_day' v-model="form.begin_day">
            <option :value="1">星期一</option>
            <option :value="2">星期二</option>
            <option :value="3">星期三</option>
            <option :value="4">星期四</option>
            <option :value="5">星期五</option>
            <option :value="6">星期六</option>
            <option :value="7">星期日</option>
          </select>
          <div id="day_gap" >
            <label for="end_day">-</label>
          </div>
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
        <div class="form_time line">
          <label id="time_label" for="time" v-html="'班&#8194;&#8194;次：'"></label>
          <select id='time' v-model="timeMode">
            <option :value="0">早班</option>
            <option :value="1">晚班</option>
          </select>
        </div>
        <div class="form_button line">
          <button class="button confirm" @click="submitForm" v-if="formType===0">添加</button>
          <button class="button confirm" @click="submitForm" v-if="formType===1">更新</button>
          <button class="button" @click="closeForm">取消</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    import {addStaff, deleteStaff, getAllStaff, updateStaff} from "../../api/staffManageApi";
    import MessageTip from "../../components/MessageTip";

    export default {
        name: "StaffManage",
        components: {MessageTip},
        data(){
            return{
                isPC: true,
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
                    end_time: 18
                }
            }
        },
        mounted() {
            if(sessionStorage.getItem('equipment')==='Mobile'){
                this.isPC = false;
            }
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
                    end_time: 18
                };
                this.showForm = false;
            },
            changeFormTime: function(){
                if(this.timeMode===0){
                    this.form.begin_time = 7;
                    this.form.end_time = 18;
                }else if(this.timeMode===1){
                    this.form.begin_time = 19;
                    this.form.end_time = 6;
                }
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
            getAllStaff: function () {
                getAllStaff().then(res=>{
                    if(res.flag){
                        this.allStaff = res.data;
                    }else{
                        this.showMessage(1, res.message);
                    }
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
                    end_time: 18
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
                if(this.form.name===''){
                    this.showMessage(1, '请输入组长名称！');
                }else if(this.form.num===''||Number(this.form.num) < 1){
                    this.showMessage(1, '人数不能小于1！');
                }else{
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

            },
            navSourceSchedule: function (id) {
                this.$router.push({
                    path: '/schedule/work',
                    query: {
                        id: id,
                        type: 0
                    }
                })
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
      align-items: center;
      .card{
        width: 20%;
        height: 150px;
        margin: 20px 1%;
        border: 6px solid #000000;
        padding: 0 10px 10px;
        background-color: #FFFFFF;
        border-radius: 10px;
        .group{
          display: flex;
          justify-content: space-between;
        }
        .id{
          display: flex;
          .pretext{
            margin: 33px 4px 0 0;
            font-size: 20px;
          }
          .number{
            font-size: 40px;
            margin-top: 16px;
            color: #000000;
            font-weight: bold;
          }
          .text{
            margin: 33px 0 0 4px;
            font-size: 20px;
          }
        }
        .name{
          margin-top: 6px;
          display: flex;
          .i-user-group{
            margin-top: 6px;
            font-size: 50px;
          }
          .text{
            margin-top: 27px;
            margin-left: 4px;
            font-size: 20px;
            font-weight: bold;
          }
        }
        .num{
          display: flex;
          .number{
            margin-left: 4px;
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
            cursor: pointer;
            margin-right: 8px;
          }
        }
      }
      .card:not(:last-child):hover{
        box-shadow: 6px 6px 6px #aaaaaa;
        background-color: #f2f2f2;
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
      top: 20%;
      left: 35%;
      width: 30%;
      min-width: 570px;
      height: 40%;
      min-height: 370px;
      padding: 32px 10px 10px;
      text-align: left;
      .form_head{
        margin-top: 10px;
        font-size: 32px;
        font-weight: bold;
        position: relative;
        .text{
          line-height: 6px;
          padding-left: 10px;
        }
        .close{
          position: absolute;
          top: -44px;
          right: 10px;
          cursor: pointer;
        }
      }
      .form_body{
        padding: 0 88px;
        .line{
          margin-bottom: 20px;
        }
        .line:first-child{
          margin-top: 40px;
        }
        .form_id{
          display: flex;
          #teamid_label{
            font-size: 20px;
            font-weight: bold;
          }
          #teamid{
            font-size: 16px;
            width: 260px;
            padding-left: 6px;
            min-height: 20px;
          }
          #teamid_disable{
            font-size: 20px;
            font-weight: bold;
            padding-left: 6px;
          }
        }
        .form_name{
          #name_label{
            font-size: 20px;
            font-weight: bold;
          }
          #name{
            font-size: 16px;
            width: 260px;
            min-height: 20px;
            padding-left: 6px;
          }
        }
        .form_num{
          #num_label{
            font-size: 20px;
            font-weight: bold;
          }
          #num{
            font-size: 16px;
            width: 260px;
            min-height: 20px;
            padding-left: 6px;
          }
        }
        .form_day{
          display: flex;
          #day_label{
            font-size: 20px;
            font-weight: bold;
          }
          #day_gap{
            text-align: center;
            width: 14px;
          }
          #begin_day{
            font-size: 16px;
            cursor: pointer;
            width: 125px;
            padding: 0 0 4px 32px;
          }
          #end_day{
            font-size: 16px;
            cursor: pointer;
            width: 125px;
            padding: 0 0 4px 32px;
          }
        }
        .form_time{
          #time_label{
            font-size: 20px;
            font-weight: bold;
          }
          #time{
            font-size: 16px;
            cursor: pointer;
            width: 125px;
            padding: 0 0 4px 32px;
          }
        }
        .form_button{
          .button{
            background-color: #E6E6E6;
            color: #666666;
            margin-right: 10px;
            border-radius: 4px;
            border: 1px solid #e6e6e6;
            padding: 4px 14px 6px;
            font-size: 16px;
            cursor: pointer;
          }
          .button:focus{
            outline: none;
          }
          .button:hover{
            background-color: #F6F6F6;
          }
          .confirm{
            background-color: #1B9AF7;
            border-color: #008dcb;
            color: #FFFFFF;
          }
          .confirm:hover{
            background-color: #4cb0f9;
          }
        }
      }
    }
  }
  .mobile{
    min-width: 100vw;
    .head{
      margin: 12px 5% 0;
      text-align: left;
      .text{
        font-size: 16px;
        font-weight: bold;
      }
    }
    .container{
      margin: 10px 5%;
      padding: 0 1vw;
      text-align: left;
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      align-items: center;
      .card{
        width: 42%;
        margin: 2px 0;
        border: 3px solid #000000;
        padding: 0 8px 4px;
        background-color: #FFFFFF;
        border-radius: 6px;
        min-height: 90px;
        .group{
          display: flex;
          justify-content: space-between;
        }
        .id{
          display: flex;
          .number{
            font-size: 24px;
            margin-top: 3px;
            color: #000000;
            font-weight: bold;
          }
          .text{
            margin: 8px 0 0 4px;
            font-size: 16px;
          }
        }
        .name{
          display: flex;
          .i-user-group{
            margin-top: 6px;
            font-size: 20px;
          }
          .text{
            margin-top: 8px;
            margin-left: 4px;
            font-size: 16px;
            font-weight: bold;
          }
        }
        .num{
          display: flex;
          .number{
            margin-left: 2px;
            font-size: 16px;
            color: #000000;
          }
          .text{
            font-size: 12px;
            margin-top: 2px;
          }
        }
        .day{
          margin-top: 2px;
          font-size: 12px;
        }
        .time{
          text-align: right;
          font-size: 12px;
        }
        .toolbox{
          .tooltip{
            font-size: 20px;
            cursor: pointer;
            margin-right: 8px;
          }
        }
      }
      .card:not(:last-child):hover{
        box-shadow: 6px 6px 6px #aaaaaa;
        background-color: #f2f2f2;
      }
      .dash{
        border: 3px dashed #000000;
        cursor: pointer;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .plus{
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
      top: 20vh;
      left: 15vw;
      width: 70vw;
      /*height: 23vh;*/
      padding: 6px 8px 8px;
      text-align: left;
      align-items: center;
      .form_head{
        margin-top: 2px;
        font-size: 16px;
        font-weight: bold;
        position: relative;
        .text{
          padding-left: 4px;
        }
        .close{
          position: absolute;
          top: -1px;
          right: 2px;
          cursor: pointer;
        }
      }
      .form_body{
        /*padding: 0 88px;*/
        .line{
          margin: 0 0 4px 12vw;
          align-items: center;
        }
        .form_id{
          display: flex;
          #teamid_label{
            font-size: 14px;
            font-weight: bold;
          }
          #teamid{
            font-size: 12px;
            width: 30vw;
            padding-left: 6px;
            min-height: 20px;
            border: 1px solid #aaaaaa;
          }
          #teamid_disable{
            font-size: 14px;
            font-weight: bold;
            padding-left: 6px;
          }
        }
        .form_name{
          #name_label{
            font-size: 14px;
            font-weight: bold;
          }
          #name{
            font-size: 12px;
            width: 30vw;
            min-height: 16px;
            padding-left: 6px;
            border: 1px solid #aaaaaa;
          }
        }
        .form_num{
          #num_label{
            font-size: 14px;
            font-weight: bold;
          }
          #num{
            font-size: 12px;
            width: 30vw;
            min-height: 16px;
            padding-left: 6px;
            border: 1px solid #aaaaaa;
          }
        }
        .form_day{
          display: flex;
          #day_label{
            font-size: 14px;
            font-weight: bold;
          }
          #day_gap{
            text-align: center;
            width: 8px;
          }
          #begin_day{
            font-size: 12px;
            cursor: pointer;
            width: 15vw;
            padding: 0 0 3px 0;
            background-color: #ffffff;
            border: 1px solid #aaaaaa;
          }
          #end_day{
            font-size: 12px;
            cursor: pointer;
            width: 15vw;
            padding: 0 0 3px 0;
            background-color: #ffffff;
            border: 1px solid #aaaaaa;
          }
        }
        .form_time{
          #time_label{
            font-size: 14px;
            font-weight: bold;
          }
          #time{
            font-size: 12px;
            cursor: pointer;
            width: 32vw;
            padding: 0 0 3px 0;
            background-color: #ffffff;
            border: 1px solid #aaaaaa;
          }
        }
        .form_button{
          .button{
            background-color: #E6E6E6;
            color: #666666;
            margin-right: 10px;
            border-radius: 2px;
            border: 1px solid #e6e6e6;
            padding: 2px 8px 2px;
            font-size: 14px;
            cursor: pointer;
          }
          .button:focus{
            outline: none;
          }
          .button:hover{
            background-color: #F6F6F6;
          }
          .confirm{
            background-color: #1B9AF7;
            border-color: #008dcb;
            color: #FFFFFF;
          }
          .confirm:hover{
            background-color: #4cb0f9;
          }
        }
      }
    }
  }
</style>