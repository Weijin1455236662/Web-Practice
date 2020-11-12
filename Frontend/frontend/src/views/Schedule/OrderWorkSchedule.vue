<template>
  <div class="main">
    <div class="head">
      <div class="text">订单-生产单关系表</div>
      <hr/>
    </div>
    <div></div>
  </div>
</template>

<script>
import {getScheduleInfo, getEquipmentInfo, getSubOrderList} from "../../api/scheduleApi"
export default {
  name: "OrderWorkSchedule",
  data(){
    return{
      val: ""
    }
  },
  methods: {
    // getAllScheduleInfo: function(){
    //   getScheduleInfo("2020-10-15","2020-10-27").then(res => {
    //     console.log(res)
    //     console.log(getEquipmentInfo(res))
    //     console.log(getSubOrderList(res))
    //   })
    // }
  },
  mounted(){
    // this.getAllScheduleInfo()
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