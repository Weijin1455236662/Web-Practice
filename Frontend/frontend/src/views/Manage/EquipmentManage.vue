<template>
  <div :class="isPC?'main':'mobile'">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div class="text">设备管理</div>
      <hr/>
    </div>
    <div class="container">
      <div class="card" v-for="(equipment,index) in allEquipment" :key="index">
        <div class="group">
          <div class="name">
            <i class="icon-font i-tupu"></i>
            <div>
              <div class="text">{{equipment.name}}</div>
              <div class="type">{{equipment.type==='line'?'生产线':equipment.type}}</div>
            </div>
          </div>
          <div v-if="/^line\d+$/.test(equipment.name)" class="id"><div class="number">{{equipment.name.split('line')[1]}}</div><div class="text">号</div></div>
        </div>
        <div class="group">
          <div class="amount">
            <div class="number">{{equipment.amount}}</div>
            <div class="text">台</div></div>
          <div>
            <div class="day">{{equipment.type==='line'?'星期一-星期五':'星期一-星期日'}}</div>
            <div class="time">0:00-24:00</div>
          </div>
        </div>
        <div class="toolbox">
          <i class="icon-font i-edit tooltip" title="修改设备" @click="updateEquipment(equipment)"></i>
          <i class="icon-font i-delete tooltip" title="删除设备" @click="deleteEquipment(equipment.equipmentid)"></i>
          <i class="icon-font i-schedule tooltip" title="查看生产单" @click="navSourceSchedule(equipment.equipmentid)"></i>
        </div>
      </div>
      <div class="card dash" title="新增设备" @click="addStaff">
        <div class="plus">
          <i class="icon-font i-add"></i>
        </div>
      </div>
    </div>
    <div class="background" v-if="showForm" @click="closeForm"></div>
    <div class="form" v-if="showForm">
      <div class="form_head">
        <div class="text">{{formType===0?'新增设备':'更新设备'}}</div>
        <div class="close" @click="closeForm"><i class="icon-font i-close"></i></div>
        <hr/>
      </div>
      <div class="form_body">
        <div class="form_id line" v-if="formType!==0&&/^line\d+$/.test(form.name)">
          <label id="equipmentid_label" v-html="'设&#8194;备&#8194;号：'"></label>
          <div id="equipmentid_disable">{{form.name.split('line')[1]}}</div>
        </div>
        <div class="form_name line">
          <label id="name_label" for="name" v-html="'设备名称：'"></label>
          <input id="name" type="text" v-model="form.name"/>
        </div>
        <div class="form_amount line">
          <label id="amount_label" for="amount" v-html="'设&#8194;备&#8194;数：'"></label>
          <input id="amount" type="number" min="1" v-model="form.amount"/>
        </div>
        <div class="form_type line">
          <label id="type_label" for="type" v-html="'设备类型：'"></label>
          <select id='type' v-model="form.type">
            <option value="line">生产线</option>
            <option value="移印机">移印机</option>
            <option value="高电压测试机">高电压测试机</option>
            <option value="弹片机">弹片机</option>
            <option value="自动包装机">自动包装机</option>
            <option value="电阻测试机">电阻测试机</option>
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
    import {addEquipment, deleteEquipment, getAllEquipment, updateEquipment} from "../../api/equipmentManageApi";
    import MessageTip from "../../components/MessageTip";

    export default {
        name: "EquipmentManage",
        components: {MessageTip},
        data(){
            return{
                isPC: true,
                allEquipment: [],
                showForm: false,
                formType: 0,
                messageState: false,
                messageType: 0,
                message: '',
                form:{
                    equipmentid: '',
                    name: '',
                    amount: '',
                    type: ''
                }
            }
        },
        mounted() {
            if(sessionStorage.getItem('equipment')==='Mobile'){
                this.isPC = false;
            }
            this.getAllEquipment();
        },
        methods:{
            openForm: function(){
                this.showForm = true;
            },
            closeForm: function () {
                this.form = {
                    equipmentid: '',
                    name: '',
                    amount: '',
                    type: ''
                };
                this.showForm = false;
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
            getAllEquipment: function () {
                getAllEquipment().then(res=>{
                    if(res.flag){
                        this.allEquipment = res.data;
                    }else{
                        this.showMessage(1, res.message);
                    }
                })
            },
            addStaff: function(){
                this.formType = 0;
                this.form = {
                    equipmentid: '',
                    name: '',
                    amount: '',
                    type: ''
                };
                this.openForm();
            },
            updateEquipment: function(equipment){
                this.formType = 1;
                this.form.equipmentid = equipment.equipmentid;
                this.form.name = equipment.name;
                this.form.amount = equipment.amount;
                this.form.type = equipment.type;
                this.openForm();
            },
            deleteEquipment: function (equipmentid) {
                deleteEquipment(equipmentid).then(res=>{
                    if(res.flag){
                        this.showMessage(0, '删除成功！');
                        this.getAllEquipment();
                    }else{
                        this.showMessage(1, res.message);
                    }
                })
            },
            submitForm: function () {
                if(this.form.name===''){
                    this.showMessage(1, '请输入设备名称！');
                }else if(this.form.amount===''||Number(this.form.amount) < 1){
                    this.showMessage(1, '设备数不能小于1！');
                }else if(this.form.type===''){
                    this.showMessage(1, '请选择设备类型！');
                }else{
                    this.form.equipmentid = Number(this.form.equipmentid);
                    this.form.amount = Number(this.form.amount);
                    if (this.formType === 0) {
                        addEquipment(this.form).then(res => {
                            if (res.flag) {
                                this.showMessage(0, '添加成功！');
                                this.closeForm();
                                this.getAllEquipment();
                            } else {
                                this.showMessage(1, res.message);
                            }
                        })
                    } else {
                        updateEquipment(this.form).then(res => {
                            if (res.flag) {
                                this.showMessage(0, '更新成功！');
                                this.closeForm();
                                this.getAllEquipment();
                            } else {
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
                        type: 1
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
      .card{
        width: 20%;
        height: 150px;
        margin: 20px 1%;
        border: 6px solid #000000;
        padding: 2px 10px 10px;
        background-color: #FFFFFF;
        border-radius: 10px;
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
            margin: 32px 0 0 4px;
            font-size: 20px;
          }
        }
        .name{
          margin-top: 6px;
          margin-left: -6px;
          display: flex;
          .i-tupu{
            font-size: 50px;
          }
          .text{
            margin-top: 10px;
            font-size: 20px;
            font-weight: bold;
          }
          .type{
            margin-top: 4px;
            margin-left: 2px;
            font-size: 14px;
          }
        }
        .amount{
          display: flex;
          margin-left: 10px;
          .number{
            font-size: 32px;
            color: #000000;
          }
          .text{
            font-size: 20px;
            line-height: 44px;
            max-width: 80px;
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
      height: 35%;
      min-height: 328px;
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
          #equipmentid_label{
            font-size: 20px;
            font-weight: bold;
          }
          #equipmentid{
            font-size: 16px;
            width: 260px;
            min-height: 20px;
            padding-left: 6px;
          }
          #equipmentid_disable{
            font-size: 20px;
            font-weight: bold;
            padding-left: 6px;
          }
        }
        .form_name{
          margin-bottom: 20px;
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
        .form_amount{
          margin-bottom: 20px;
          #amount_label{
            font-size: 20px;
            font-weight: bold;
          }
          #amount{
            font-size: 16px;
            width: 260px;
            min-height: 20px;
            padding-left: 6px;
          }
        }
        .form_type{
          margin-bottom: 20px;
          #type_label{
            font-size: 20px;
            font-weight: bold;
          }
          #type{
            font-size: 16px;
            cursor: pointer;
            width: 125px;
            padding: 0 0 4px 6px;
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
        min-height: 102px;
        .group{
          display: flex;
          justify-content: space-between;
        }
        .id{
          display: flex;
          .number{
            font-size: 22px;
            margin-top: 4px;
            color: #000000;
            font-weight: bold;
          }
          .text{
            margin: 8px 0 0 2px;
            font-size: 16px;
          }
        }
        .name{
          display: flex;
          .i-tupu{
            margin-top: 6px;
            font-size: 30px;
          }
          .text{
            margin-top: 8px;
            margin-left: 2px;
            font-size: 14px;
            font-weight: bold;
            display: flex;
            justify-content: center;
            align-items: center;
          }
          .type{
            margin-left: 2px;
            font-size: 12px;
          }
        }
        .amount{
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
          margin: 0 0 10px 12vw;
          align-items: center;
        }
        .form_id{
          display: flex;
          #equipmentid_label{
            font-size: 14px;
            font-weight: bold;
          }
          #equipmentid{
            font-size: 12px;
            width: 30vw;
            padding-left: 6px;
            min-height: 20px;
            border: 1px solid #aaaaaa;
          }
          #equipmentid_disable{
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
        .form_amount{
          #amount_label{
            font-size: 14px;
            font-weight: bold;
          }
          #amount{
            font-size: 12px;
            width: 30vw;
            min-height: 16px;
            padding-left: 6px;
            border: 1px solid #aaaaaa;
          }
        }
        .form_type{
          #type_label{
            font-size: 14px;
            font-weight: bold;
          }
          #type{
            font-size: 12px;
            cursor: pointer;
            width: 30vw;
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