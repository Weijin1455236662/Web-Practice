<template>
  <div class="main">
    <div class="head">
      <div class="text">订单甘特图</div>
      <hr/>
    </div>
    <div class="container">
      <AngularGaugeItem v-if="subcaption" :punctuality="punctuality" :caption="caption" :subcaption="subcaption"></AngularGaugeItem>
      <div class="today">
        今天是 <span class="text">{{today1}}</span>
      </div>
      <fusioncharts
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
      punctuality: 80,
      chart1: {
        type: "msbar2d",
        width: "100%",
        height: "2000",
        dataFormat: "json",
        dataSource: {},
      }
    }
  },
  mounted(){
    let nowDate = new Date()
    let date = {
      year: nowDate.getFullYear(),
      month: nowDate.getMonth() + 1,
      day: nowDate.getDate()
    }
    this.today = date.year + "年" + date.month + "月" + date.day + "日"
    this.today1 = date.year + "-" + date.month + "-" + date.day
    this.subcaption = this.today + "之前"
    // 绘制订单甘特图
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
        numberSuffix: "%",
        plottooltext: "订单 $label<br>" + "$seriesName：$value",
        theme: "fusion",
      },
      categories: [
        {
          fontSize: 18,
          toolText: "$label<br>预计完成：2020/10/28<br>实际完成：2020/10/25",
          category: [
            {
              label: "418575"
            },
            {
              label: "418577"
            },
            {
              label: "764486"
            },
            {
              label: "762904"
            },
            {
              label: "418477"
            },
            {
              label: "418006"
            },
            {
              label: "418575",
            },
            {
              label: "418577"
            },
            {
              label: "764486"
            },
            {
              label: "762904"
            },
            {
              label: "418477"
            },
            {
              label: "418006"
            },
            {
              label: "418575",
            },
            {
              label: "418577"
            },
            {
              label: "764486"
            },
            {
              label: "762904"
            },
            {
              label: "418477"
            },
            {
              label: "418006"
            }
          ]
        }
      ],
      dataset: [
        {
          seriesName: "装配率",
          data: [
            {
              value: "100%",
            },
            {
              value: "80%"
            },
            {
              value: "60%"
            },
            {
              value: "10%"
            },
            {
              value: "13%"
            },
            {
              value: "40%"
            }
          ]
        },
        {
          seriesName: "打弹片率",
          data: [
            {
              value: "80%"
            },
            {
              value: "54%"
            },
            {
              value: "20%"
            },
            {
              value: "50%"
            },
            {
              value: "0%"
            },
            {
              value: "40%"
            }
          ]
        },
        {
          seriesName: "测试率",
          data: [
            {
              value: "80%"
            },
            {
              value: "54%"
            },
            {
              value: "20%"
            },
            {
              value: "50%"
            },
            {
              value: "73%"
            },
            {
              value: "40%"
            }
          ]
        }
      ]
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