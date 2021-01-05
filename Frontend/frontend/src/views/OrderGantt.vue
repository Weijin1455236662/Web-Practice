<template>
  <div :class="isPC?'main':'mobile'">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div class="text">订单甘特图</div>
      <hr/>
    </div>
    <div class="container">
      <div class="picWrap">
        <AngularGaugeItem class="pic" v-if="subcaption" :punctuality="punctuality" :caption="caption" :subcaption="subcaption"></AngularGaugeItem>
      </div>
      <div class="today">
        <label id="date_label" for="date">今天是：</label>
        <input id="date" type="date" v-model="today"/>
      </div>
      <fusioncharts
        v-if="flag"
        :type="chart1.type"
        :width="chart1.width"
        :height="chart1.height"
        :dataFormat="chart1.dataFormat"
        :dataSource="chart1.dataSource"
      ></fusioncharts>
    </div>
  </div>
</template>

<script>
    import {getAllOrder} from "../api/orderManageApi"
    import {getOrderSchedule} from "../api/scheduleApi"
    import AngularGaugeItem from "../components/AngularGaugeItem"
    import MessageTip from "../components/MessageTip";

    export default {
  name: "OrderGantt",
  components: {MessageTip, AngularGaugeItem},
  data(){
    return{
      today: "",
      caption: "按期交货率",
      subcaption: "",
      punctuality: 20,
      flag: false,
      chart1: {
        type: "msbar2d",
        width: "100%",
        height: "100%",
        dataFormat: "json",
        dataSource: {},
      },
      messageState: false,
      messageType: 0,
      message: '',
      isPC: true,
    }
  },
  methods: {
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
    start: function(){
        let that = this
        getAllOrder().then(res => {
            let order = res.data
            let orderList = []
            order.forEach(function(item){
                orderList.push({
                    id: item.orderid,
                    quantity: item.quantity,
                    delivery_date: item.delivery_date.split("T")[0]
                })
            })
            let orderSchedule = getOrderSchedule(orderList, this.today)
            if (orderSchedule === '') {
                let timer = setInterval(function () {
                    let orderSchedule = getOrderSchedule(orderList, that.today)
                    if (orderSchedule !== '') {
                        clearInterval(timer)
                        that.render(orderSchedule)
                    }
                }, 100)
            } else {
                this.render(orderSchedule)
            }
        })
    },
    // 判断初始日期是否越界
    ifStartDateValid: function (date) {
      let start = sessionStorage.getItem("beginDate")
      let end = sessionStorage.getItem("endDate")
      if ((new Date(date.replace(/-/g,"/"))) <= (new Date(end.replace(/-/g,"/"))) && (new Date(date.replace(/-/g,"/"))) >= (new Date(start.replace(/-/g,"/")))) {
        return true
      } else {
        return false
      }
    },
    // 判断颜色
    calculateColor: function(val) {
      val = parseInt(val)
      if (0 <= val && val <= 25) {
        return "#F5000C"
      } else if (25 < val && val <= 50) {
        return "#f8bd19"
      } else if (50 < val && val <= 75) {
        return "#0C9EF0"
      } else if (75 < val && val <= 100) {
        return "#57F525"
      }
    },
    // 绘图
    render: function (orderList) {
      let that = this
      let punctualSum = 0
      let category = []
      let dataset = [{
        seriesName: "装配率",
        fontSize: 16,
        data: []
      }]
      let data = []
      orderList.forEach(function(item){
        category.push({
          label: item.id + "",
          toolText: "$label<br>预计完成：" + item.planDate + "<br>实际完成：" + item.actualDate
        })
        data.push({
          value: item.value,
          color: that.calculateColor(item.value)
        })
      })
      for (let i = 0; i < data.length; i++) {
        if (data[i].value == "100%") {
          punctualSum += 1
        }
      }
      this.punctuality = punctualSum / data.length * 100
      console.log(this.punctuality)
      let categories = [
        {
          fontSize: 18,
          category: category
        }
      ]
      dataset[0].data = data
      console.log(categories)
      console.log(dataset)
      this.chart1.dataSource = {
        chart: {
          caption: "订单进度",
          captionFontSize: 14,
          xAxisname: "订<br>单<br>号",
          xAxisNameFontSize: 14,
          rotateXAxisName: 0,
          yAxisMaxValue: 100,
          legendposition: "top",
          showValues: 1,
          showLabels: 1,
          numberSuffix: "%",
          plottooltext: "<b>订单$label<br>" + "$seriesName：$value</b>",
          theme: "fusion",
        },
        categories: categories,
        dataset: dataset
      }
      this.flag = true;
      this.subcaption = '';
      setTimeout(function () {
          that.subcaption = that.today.split("-")[0] + "年" + that.today.split("-")[1] + "月" + that.today.split("-")[2] + "日" + "之前";
          setTimeout(function () {
              document.getElementsByTagName('path')[3].style.opacity = '0';
              document.getElementsByTagName('circle')[0].style.opacity = '0';
              document.getElementsByClassName('pic')[0].style.opacity = '1';
              let percentage = document.getElementsByTagName('text')[0];
              if(percentage.innerHTML === '99.9%'){
                  percentage.innerHTML = '100%';
              }
          },100);
      },100);
    }
  },
  mounted(){
    if(sessionStorage.getItem('equipment')==='Mobile'){
      this.isPC = false;
    }
    this.today = sessionStorage.getItem("beginDate")
    this.start();
  },
  watch: {
    'today': function () {
        if(!this.ifStartDateValid(this.today)){
            this.showMessage(1, "不在排程日期内，请重新选择日期！")
            if(this.today < sessionStorage.getItem("beginDate")){
                this.today = sessionStorage.getItem("beginDate");
            }else{
                this.today = sessionStorage.getItem("endDate");
            }
        }
        this.start();
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
    margin: 30px 5% 0;
    .picWrap{
      min-height: 50vh;
    }
    .today{
      text-align: left;
      margin-bottom: 2px;
      padding-left: 4%;
      #date_label{
        font-size: 20px;
        font-weight: bold;
      }
      #date{
        font-size: 16px;
        cursor: pointer;
        width: 260px;
        padding-left: 6px;
      }
    }
  }
}
.mobile{
  .head{
    margin: 30px 4% 0;
    text-align: left;
    .text{
      font-size: 16px;
      font-weight: bold;
    }
  }
  .container{
    margin: 10px 5% 0;
    .picWrap{
      min-height: 50vh;
    }
    .today{
      text-align: left;
      margin-bottom: 2px;
      padding-left: 4%;
      #date_label{
        font-size: 14px;
        font-weight: bold;
      }
      #date{
        font-size: 16px;
        cursor: pointer;
        width: 230px;
        padding-left: 6px;
      }
    }
  }
}
</style>
