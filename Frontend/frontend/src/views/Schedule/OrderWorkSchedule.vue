<template>
  <div>
    <h1>订单-生产单关系表</h1>
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
    getAllScheduleInfo: function(){
      getScheduleInfo("2020-10-15","2020-10-27").then(res => {
        console.log(res)
        console.log(getEquipmentInfo(res))
        console.log(getSubOrderList(res))
      })
    }
  },
  mounted(){
    this.getAllScheduleInfo()
  }
}
</script>

<style scoped>

</style>