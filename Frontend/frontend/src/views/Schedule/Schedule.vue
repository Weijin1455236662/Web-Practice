<template>
  <div class="schedule">
    <div v-if="!isReady">暂无排程数据，请稍后查看</div>
    <router-view v-if="isReady"></router-view>  </div>
</template>

<script>
    export default {
        name: "Schedule",
        data(){
            return{
                isReady: false
            }
        },
        mounted() {
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
    margin-bottom: 100px;
  }
</style>