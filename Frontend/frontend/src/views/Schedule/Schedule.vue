<template>
  <div :class="isPC?'schedule':'mobile'">
    <div v-if="!isReady">暂无排程数据，请<em @click="nav()">排程</em>后查看</div>
    <router-view v-if="isReady"></router-view>  </div>
</template>

<script>
    export default {
        name: "Schedule",
        data(){
            return{
                isPC: false,
                isReady: false
            }
        },
        mounted() {
            if(sessionStorage.getItem('equipment')==='Mobile'){
                this.isPC = false;
            }
            this.judgeOrderReady();
        },
        methods: {
            judgeOrderReady: function () {
                let session = sessionStorage.getItem('subOrders');
                if(!session){
                    this.isReady = false;
                }else{
                    this.isReady = true;
                }
            },
            nav: function () {
                this.$router.push({
                    path: '/manage/arrangement'
                }).catch(()=>{
                    console.log()
                });
            }
        },
        watch: {
            $route: function () {
                this.judgeOrderReady();
            }
        }
    }
</script>

<style scoped lang="less">
  .schedule{
    margin-top: 30px;
    margin-bottom: 100px;
    em{
      text-decoration: underline;
      cursor: pointer;
    }
  }
  .mobile{
    em{
      text-decoration: underline;
      cursor: pointer;
    }
  }
</style>