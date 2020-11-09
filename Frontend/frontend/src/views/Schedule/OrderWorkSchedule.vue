<template>
  <div>
    <h1>订单-生产单关系表</h1>
  </div>
</template>

<script>
import {getScheduleInfo} from "../../api/scheduleApi"
export default {
  name: "OrderWorkSchedule",
  methods: {
    getAllScheduleInfo: function(){
      getScheduleInfo("2020-10-15","2020-10-27").then(res => {
        console.log(res)
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