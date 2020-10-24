<template>
  <div>
    <h1>人员管理</h1>
    <div v-for="(staff, index) in allStaff" :key="index">
      <div>第{{staff.teamid}}组</div>
      <div>{{staff.name}}</div>
      <div>{{staff.num}}人</div>
    </div>
  </div>
</template>

<script>
    import {getAllStaff} from "../../api/api";

    export default {
        name: "StaffManage",
        data(){
            return{
                allStaff: []
            }
        },
        mounted() {
            getAllStaff().then(res=>{
                if(res.flag){
                    this.allStaff.push(res.data);
                }else{
                    alert(res.message);
                }
            })
        }
    }
</script>

<style scoped>

</style>