<template>
  <div class="main">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div class="text">订单计划图</div>
      <hr/>
    </div>
    <div v-if="showList" class="container">
      <div class="group">
        <div class="title">订单列表</div>
        <div class="list">
          <div v-for="(order, index) in orderList" :key="index" class="name">
            <span class="text" @click="nav(order.orderid)">订单 {{order.orderid}}</span>
          </div>
        </div>
      </div>
    </div>
    <div>
      <fusioncharts
        v-if="!showList && flag"
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
import MessageTip from "../../components/MessageTip"
import {getAllOrder} from "../../api/orderManageApi"
import {getOrderPlan} from "../../api/scheduleApi"
export default {
  name: "OrderSchedule",
  components: {MessageTip},
  data(){
    return{
      showList: true,
      messageState: false,
      messageType: 0,
      message: '',
      flag: false,
      orderList: [],
      chart: {
        type: "gantt",
        width: "90%",
        height: "85%",
        dataFormat: "json",
        dataSource: {}
      },
    }
  },
  methods: {
    nav: function(id){
      this.$router.push({
        path: '/schedule/order',
        query: {
          id: id
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
    measure: function() {
      let id = this.$route.query.id
      if (id === '0') {
        this.showList = true
        getAllOrder().then(res => {
          if (res.flag) {
            this.orderList = res.data
            console.log(res)
          } else {
            this.showMessage(1, '获取订单列表失败！')
          }
        }).catch(err => {
          this.showMessage(1, '获取订单列表失败！')
        })
      } else {
        this.showList = false;
      }
    },
    updateData: function() {
      getOrderPlan(this.$route.query.id)
    }
    // 绘图
    // render: function(orderSchedule){
    //   this.chart.dataSource = {
    //     tasks: {},
    //     processes: {},
    //     categories: [
    //         {
    //           category: [
    //             {
    //               start: "00:00:00",
    //               end: "23:59:59",
    //               label: "时间"
    //             }
    //           ]
    //         },
    //         {
    //           category: [
    //             {
    //               start: "00:00:00",
    //               end: "11:59:59",
    //               label: "早班"
    //             },
    //             {
    //               start: "12:00:00",
    //               end: "23:59:59",
    //               label: "晚班"
    //             }
    //           ]
    //         },
    //         {
    //           align: "center",
    //           category: [
    //             {
    //               start: "00:00:00",
    //               end: "01:59:59",
    //               label: "7:00-9:00"
    //             },
    //             {
    //               start: "02:00:00",
    //               end: "03:59:59",
    //               label: "9:00-11:00"
    //             },
    //             {
    //               start: "04:00:00",
    //               end: "05:59:59",
    //               label: "11:00-13:00"
    //             },
    //             {
    //               start: "06:00:00",
    //               end: "07:59:59",
    //               label: "13:00-15:00"
    //             },
    //             {
    //               start: "08:00:00",
    //               end: "09:59:59",
    //               label: "15:00-17:00"
    //             },
    //             {
    //               start: "10:00:00",
    //               end: "11:59:59",
    //               label: "17:00-19:00"
    //             },
    //             {
    //               start: "12:00:00",
    //               end: "13:59:59",
    //               label: "19:00-21:00"
    //             },
    //             {
    //               start: "14:00:00",
    //               end: "15:59:59",
    //               label: "21:00-23:00"
    //             },
    //             {
    //               start: "16:00:00",
    //               end: "17:59:59",
    //               label: "23:00-次日1:00"
    //             },
    //             {
    //               start: "18:00:00",
    //               end: "19:59:59",
    //               label: "次日1:00-3:00"
    //             },
    //             {
    //               start: "20:00:00",
    //               end: "21:59:59",
    //               label: "次日3:00-5:00"
    //             },
    //             {
    //               start: "22:00:00",
    //               end: "23:59:59",
    //               label: "次日5:00-7:00"
    //             }
    //           ]
    //         }
    //       ],
    //       chart: 
    //       {
    //         dateformat: "dd/mm/yyyy",
    //         outputdateformat: "hh12:mn ampm",
    //         plottooltext: "$label",
    //         caption: "产品3路线图",
    //         theme: "fusion"
    //       }
    //   }
    // }
  },
  mounted(){
    // this.render([])
    this.measure()
  },
  watch: {
    $route: function () {
      this.measure();
      this.updateData();
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