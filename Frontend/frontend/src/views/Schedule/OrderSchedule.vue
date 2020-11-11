<template>
  <div class="main">
    <div class="head">
      <div class="text">订单计划图</div>
      <hr/>
    </div>
    <div>
      <fusioncharts
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
export default {
  name: "OrderSchedule",
  data(){
    return{
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
    // 绘图
    render: function(orderSchedule){
      this.chart.dataSource = {
        tasks: {},
        processes: {},
        categories: [
            {
              category: [
                {
                  start: "00:00:00",
                  end: "23:59:59",
                  label: "时间"
                }
              ]
            },
            {
              category: [
                {
                  start: "00:00:00",
                  end: "11:59:59",
                  label: "早班"
                },
                {
                  start: "12:00:00",
                  end: "23:59:59",
                  label: "晚班"
                }
              ]
            },
            {
              align: "center",
              category: [
                {
                  start: "00:00:00",
                  end: "01:59:59",
                  label: "7:00-9:00"
                },
                {
                  start: "02:00:00",
                  end: "03:59:59",
                  label: "9:00-11:00"
                },
                {
                  start: "04:00:00",
                  end: "05:59:59",
                  label: "11:00-13:00"
                },
                {
                  start: "06:00:00",
                  end: "07:59:59",
                  label: "13:00-15:00"
                },
                {
                  start: "08:00:00",
                  end: "09:59:59",
                  label: "15:00-17:00"
                },
                {
                  start: "10:00:00",
                  end: "11:59:59",
                  label: "17:00-19:00"
                },
                {
                  start: "12:00:00",
                  end: "13:59:59",
                  label: "19:00-21:00"
                },
                {
                  start: "14:00:00",
                  end: "15:59:59",
                  label: "21:00-23:00"
                },
                {
                  start: "16:00:00",
                  end: "17:59:59",
                  label: "23:00-次日1:00"
                },
                {
                  start: "18:00:00",
                  end: "19:59:59",
                  label: "次日1:00-3:00"
                },
                {
                  start: "20:00:00",
                  end: "21:59:59",
                  label: "次日3:00-5:00"
                },
                {
                  start: "22:00:00",
                  end: "23:59:59",
                  label: "次日5:00-7:00"
                }
              ]
            }
          ],
          chart: 
          {
            dateformat: "dd/mm/yyyy",
            outputdateformat: "hh12:mn ampm",
            plottooltext: "$label",
            caption: "产品3路线图",
            theme: "fusion"
          }
      }
    }
  },
  mounted(){
    this.render([])
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
}
</style>