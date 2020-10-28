<template>
  <div class="main">
    <div class="head">
      <div class="text">订单甘特图</div>
      <hr/>
    </div>
    <div class="container">
      <!-- <div class="today">
        今天是{{today}}
      </div> -->
      <fusioncharts
        :type="type"
        :width="width"
        :height="height"
        :dataFormat="dataFormat"
        :dataSource="dataSource"
      ></fusioncharts>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderGantt",
  data(){
    return{
      today: "",
      type: "angulargauge",
      width: "50%",
      height: "40%",
      dataFormat: "json",
      dataSource: {},
      punctuality: "30"
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
    this.dataSource = {
      chart: {
        caption: "按期交货率",
        subcaption: this.today + "之前",
        showvalue: "1",
        numbersuffix: "%",
        theme: "fusion",
      },
      colorrange: {
        color: [
          {
            minvalue: "0",
            maxvalue: "25",
            code: "#F2726F"
          },
          {
            minvalue: "25",
            maxvalue: "50",
            code: "#AB05FA"
          },
          {
            minvalue: "50",
            maxvalue: "75",
            code: "#FFC533"
          },
          {
            minvalue: "75",
            maxvalue: "100",
            code: "#62B58F"
          },
        ]
      },
      dials: {
        dial: [
          {
            value: this.punctuality
          }
        ]
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
    margin: 30px 5% 0;
    .today{
      text-align: left;
      font-size: 30px;
    }
  }
}
</style>