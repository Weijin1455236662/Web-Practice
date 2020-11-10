<template>
  <div class="main">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div class="text">时间管理</div>
      <hr/>
    </div>
    <div class="container">
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
        <button class="button confirm" @click="doSet?changeDate():changeSetMode()">修改</button>
        <button v-if="doSet" class="button" @click="cancelChange()">取消</button>
      </div>
    </div>
  </div>
</template>

<script>
    import MessageTip from "../../components/MessageTip";
    export default {
        name: "DateManage",
        components: {MessageTip},
        data() {
            return {
                beginDate: '',
                endDate: '',
                newBeginDate:'',
                newEndDate:'',
                doSet: false,
                messageState: false,
                messageType: 0,
                message: '',
            }
        },
        mounted(){
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
                    this.showMessage(0, '修改成功！');
                    this.$parent.$parent.changeTime(this.beginDate, this.endDate);
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
</style>