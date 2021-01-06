<template>
  <div :class="isPC?'main':'mobile'">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div class="text">排程管理</div>
      <hr/>
    </div>
    <div class="container">
      <div class="title">时间管理：</div>
      <div v-if="!doSet" class="hasDate">
        <div class="text">当前排程时间是：</div>
        <div class="date">{{beginDate}}</div>
        <div class="text middle">~</div>
        <div class="date">{{endDate}}</div>
      </div>
      <div v-if="doSet" class="setDate">
        <label class="text" for="beginDate">设置时间为：</label>
        <input class="input" type="date" id="beginDate" v-model="newBeginDate"/>
        <label class="text" for="endDate"> ~ </label>
        <input class="input" type="date" id="endDate" v-model="newEndDate"/>
      </div>
      <div class="buttonWrap">
        <button class="button confirm" @click="doSet?changeDate():changeSetMode()" :disabled="arranging">修改</button>
        <button v-if="doSet" class="button" @click="cancelChange()">取消</button>
      </div>

      <div class="title">数据管理：</div>
      <div class="buttonWrap">
        <button class="button confirm" @click="resetData()" :disabled="arranging||doSet">更新数据</button>
        <button class="button confirm" @click="arrangement()" :disabled="arranging||doSet">排程</button>
        <div v-if="hasSubOrders&&!arranging" class="hasSubOrders has"><i class="icon-font i-tick-circle"></i>已有排程</div>
        <div v-if="arranging" class="hasSubOrders getting"><i class="icon-font i-tick-circle"></i>排程中：{{countDown}}s</div>
        <div v-if="!hasSubOrders&&!arranging" class="hasSubOrders noHas"><i class="icon-font i-cross-circle"></i>还未排程</div>
      </div>
    </div>
  </div>
</template>

<script>
    import MessageTip from "../../components/MessageTip";
    import {getScheduleInfo} from "../../api/scheduleApi";
    import {importCraft, importEquipment, importOrder, importTeam} from "../../api/arrangementAPI";
    export default {
        name: "DateManage",
        components: {MessageTip},
        data() {
            return {
                isPC: true,
                beginDate: '',
                endDate: '',
                newBeginDate:'',
                newEndDate:'',
                doSet: false,
                hasSubOrders: false,
                arranging: false,
                messageState: false,
                messageType: 0,
                message: '',
                countDown: 100,
            }
        },
        mounted(){
            if(sessionStorage.getItem('equipment')==='Mobile'){
                this.isPC = false;
            }
            let date1 = sessionStorage.getItem('beginDate');
            let date2 = sessionStorage.getItem('endDate');
            if(!date1){
                sessionStorage.setItem('beginDate', '2018-11-09');
                date1 = '2018-11-09';
            }
            if(!date2){
                sessionStorage.setItem('endDate', '2018-12-31');
                date2 = '2018-12-31'
            }
            this.beginDate = date1;
            this.endDate = date2;
            this.newBeginDate = JSON.parse(JSON.stringify(this.beginDate));
            this.newEndDate = JSON.parse(JSON.stringify(this.endDate));
            if(!sessionStorage.getItem('subOrders')){
                this.hasSubOrders = false;
            }else{
                this.hasSubOrders = true;
            }
        },
        methods: {
            changeSetMode: function(){
                this.doSet = true;
            },
            cancelChange: function() {
                this.doSet = false;
                this.newBeginDate = JSON.parse(JSON.stringify(this.beginDate));
                this.newEndDate = JSON.parse(JSON.stringify(this.endDate));
            },
            changeDate: function () {
                if(this.newBeginDate>this.newEndDate){
                    this.showMessage(1, '开始时间不可晚于结束时间！');
                }else{
                    this.beginDate = JSON.parse(JSON.stringify(this.newBeginDate));
                    this.endDate = JSON.parse(JSON.stringify(this.newEndDate));
                    sessionStorage.setItem('beginDate', this.beginDate);
                    sessionStorage.setItem('endDate', this.endDate);
                    this.doSet = false;
                    this.showMessage(0, '修改成功，请重新排程！');
                    this.$parent.$parent.changeTime(this.beginDate, this.endDate);
                    sessionStorage.removeItem('subOrders');
                    this.hasSubOrders = false;
                }
            },
            arrangement: function(){
                this.arranging = true;
                sessionStorage.removeItem('subOrders');
                this.hasSubOrders = false;
                this.countDown = 100;
                let that = this;
                let count = setInterval(function () {
                    if(that.countDown > 0){
                        that.countDown -= 1;
                    }else{
                        clearInterval(count);
                    }

                },1000);
                getScheduleInfo(this.beginDate, this.endDate).then(res=>{
                    if(res.flag){
                        let list = res.data.subOrderList;
                        sessionStorage.setItem('subOrders', JSON.stringify(list));
                        this.hasSubOrders = true;
                        this.arranging = false;
                    }else{
                        this.showMessage(1, '排程失败！')
                        this.arranging = false;
                    }
                }).catch(err=>{
                    this.showMessage(1, '排程失败！')
                    this.arranging = false;
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
            resetData: function () {
                this.arranging = true;
                sessionStorage.removeItem('subOrders');
                this.hasSubOrders = false;
                importTeam().then(res=>{
                    if(res.flag){
                        importEquipment().then(res=>{
                            if(res.flag){
                                importOrder().then(res=>{
                                    if(res.flag){
                                        importCraft().then(res=>{
                                            if(res.flag){
                                                this.showMessage(0, '更新数据成功！');
                                                this.arranging = false;
                                            }else{
                                                this.showMessage(1, '获取工艺信息失败！');
                                                this.arranging = false;
                                            }
                                        }).catch(err=>{
                                            this.showMessage(1, '获取工艺信息失败！');
                                            this.arranging = false;
                                        })
                                    }else{
                                        this.showMessage(1, '获取订单信息失败！');
                                        this.arranging = false;
                                    }
                                }).catch(err=>{
                                    this.showMessage(1, '获取订单信息失败！');
                                    this.arranging = false;
                                })
                            }else{
                                this.showMessage(1, '获取设备信息失败！');
                                this.arranging = false;
                            }
                        }).catch(err=>{
                            this.showMessage(1, '获取设备信息失败！');
                            this.arranging = false;
                        })
                    }else{
                        this.showMessage(1, '获取团队信息失败！');
                        this.arranging = false;
                    }
                }).catch(err=>{
                    this.showMessage(1, '获取团队信息失败！');
                    this.arranging = false;
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
      padding: 10px 20px;
      text-align: left;
      flex-wrap: wrap;
      .title{
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 13px;
      }
      .title:not(:first-child){
        margin-top: 30px;
      }
      .hasDate{
        display: flex;
        margin-bottom: 13px;
        .text{
          font-size: 20px;
        }
        .date{
          font-size: 20px;
        }
        .middle{
          margin: 0 10px;
        }
      }
      .setDate{
        margin-bottom: 10px;
        .text{
          font-size: 20px;
        }
        #beginDate{
          font-size: 16px;
          cursor: pointer;
          width: 160px;
          padding-left: 6px;
        }
        #endDate{
          font-size: 16px;
          cursor: pointer;
          width: 160px;
          padding-left: 6px;
        }
      }
      .buttonWrap{
        display: flex;
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
        .button:disabled{
          opacity: 0.5;
          cursor: not-allowed;
        }
        .confirm{
          background-color: #1B9AF7;
          border-color: #008dcb;
          color: #FFFFFF;
        }
        .confirm:hover{
          background-color: #4cb0f9;
        }
        .hasSubOrders{
          display: flex;
          align-items: baseline;
          margin-top: 6px;

        }
        .has{
          color: #008000;
        }
        .getting{
          color: #feae1b;
        }
        .noHas{
          color: #FF0000;
        }
      }
    }
  }
  .mobile{
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
      text-align: left;
      flex-wrap: wrap;
      .title{
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 13px;
      }
      .title:not(:first-child){
        margin-top: 30px;
      }
      .hasDate{
        display: flex;
        margin-bottom: 13px;
        .text{
          font-size: 14px;
        }
        .date{
          font-size: 14px;
        }
        .middle{
          margin: 0 10px;
        }
      }
      .setDate{
        margin-bottom: 10px;
        .text{
          font-size: 14px;
        }
        #beginDate{
          font-size: 12px;
          cursor: pointer;
          width: 26vw;
          padding-left: 6px;
        }
        #endDate{
          font-size: 12px;
          cursor: pointer;
          width: 26vw;
          padding-left: 6px;
        }
      }
      .buttonWrap{
        display: flex;
        .button{
          background-color: #E6E6E6;
          color: #666666;
          margin-right: 10px;
          border-radius: 4px;
          border: 1px solid #e6e6e6;
          padding: 4px 10px 4px;
          font-size: 14px;
          cursor: pointer;
        }
        .button:focus{
          outline: none;
        }
        .button:hover{
          background-color: #F6F6F6;
        }
        .button:disabled{
          opacity: 0.5;
          cursor: not-allowed;
        }
        .confirm{
          background-color: #1B9AF7;
          border-color: #008dcb;
          color: #FFFFFF;
        }
        .confirm:hover{
          background-color: #4cb0f9;
        }
        .hasSubOrders{
          display: flex;
          align-items: baseline;
          margin-top: 6px;

        }
        .has{
          color: #008000;
        }
        .getting{
          color: #feae1b;
        }
        .noHas{
          color: #FF0000;
        }
      }
    }
  }
</style>