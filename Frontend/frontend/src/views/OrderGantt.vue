<template>
  <div class="main">
    <div class="head">
      <div class="text">订单甘特图</div>
      <hr/>
    </div>
    <div class="container">
      <AngularGaugeItem class="pic" v-if="flag" :punctuality="punctuality" :caption="caption" :subcaption="subcaption"></AngularGaugeItem>
      <div class="today">
        今天是 <span class="text">{{today1}}</span>
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
export default {
  name: "OrderGantt",
  components: {AngularGaugeItem},
  data(){
    return{
      today: "",
      today1: "",
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
      }
    }
  },
  methods: {
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
        fontSize: 26,
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
          captionFontSize: 24,
          xAxisname: "订<br>单<br>号",
          xAxisNameFontSize: 20,
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
      this.flag = true
    }
  },
  mounted(){
    let endDate = sessionStorage.getItem("endDate")
    this.today = endDate.split("-")[0] + "年" + endDate.split("-")[1] + "月" + endDate.split("-")[2] + "日"
    this.today1 = endDate
    this.subcaption = this.today + "之前"
    let that = this
    getAllOrder().then(res => {
        console.log(res.data)
        let order = res.data
        let orderList = []
        order.forEach(function(item){
          orderList.push({
            id: item.orderid,
            quantity: item.quantity,
            delivery_date: item.delivery_date.split("T")[0]
          })
        })
        let orderSchedule = getOrderSchedule(orderList)
        if (orderSchedule === '') {
          let timer = setInterval(function () {
            let orderSchedule = getOrderSchedule(orderList)
            if (orderSchedule !== '') {
              clearInterval(timer)
              that.render(orderSchedule)
            }
          }, 100)
        } else {
          this.render(orderSchedule)
        }
      })
    setTimeout(function () {
        document.getElementsByTagName('path')[3].style.opacity = '0';
        document.getElementsByTagName('circle')[0].style.opacity = '0';
        document.getElementsByClassName('pic')[0].style.opacity = '1';
    },200);
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
    .today{
      text-align: left;
      font-size: 26px;
      .text{
        font-weight: bold;
      }
    }
  }
}
</style>