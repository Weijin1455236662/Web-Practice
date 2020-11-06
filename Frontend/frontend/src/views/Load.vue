<template>
  <div class="main">
    <div class="head">
      <div class="title">资源负载图</div>
      <hr/>
    </div>
    <div class="loadPic">
      <div>
        <AngularGaugeItem v-if="subcaption1" :punctuality="punctuality1" :caption="caption1" :subcaption="subcaption1"></AngularGaugeItem>
      </div>
      <div>
        <AngularGaugeItem v-if="subcaption2" :punctuality="punctuality2" :caption="caption2" :subcaption="subcaption2"></AngularGaugeItem>
      </div>
    </div>
    <div>
      <div class="row" v-for="(data, index1) in datas" :key="index1">
        <div class="load-item" v-for="(d, index2) in data" :key="index2">
          <load-item :data="d" :index="index2 + index1 * data.length"></load-item>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LoadItem from "../components/LoadItem";
import AngularGaugeItem from "../components/AngularGaugeItem";
export default {
  name: "Load",
  components: {LoadItem, AngularGaugeItem},
  data(){
    return{
      caption1: "设备总负载",
      subcaption1: "",
      punctuality1: 87,
      caption2: "人员总负载",
      subcaption2: "",
      punctuality2: 45,
      datas: [
        [0.1, 0.32, 0.5, 0.67, 0.9, 1, 1.15111],
        [0.6, 0.24, 0.11, 0.98, 0.45, 1, 0.7],
        [1.3, 0.16, 0.41, 0.78, 0.92, 1, 0]
      ]
    }
  },
  mounted(){
    let date1 = new Date()
    let time1 = date1.getFullYear() + "年" + (date1.getMonth()+1) + "月" + date1.getDate() + "日"
    let date2 = new Date(date1)
    date2.setDate(date1.getDate() - 7)
    let time2 = date2.getFullYear() + "年" + (date2.getMonth()+1) + "月" + date2.getDate() + "日"
    this.subcaption1 = time1 + "-" + time2
    this.subcaption2 = time1 + "-" + time2
    console.log(this.subcaption1)
  }
}
</script>

<style scoped lang="less">
.main{
  .head{
    margin: 30px 5% 0;
    text-align: left;
    .title{
      font-size: 32px;
      font-weight: bold;
    }
  }
  .loadPic{
    display: flex;
    justify-content: space-between;
  }
  .row{
    display: flex;
    margin: 20px 0;
    .load-item{
      width: 100px;
      height: 120px;
      margin-left: 10px;
    }
  }
}
</style>