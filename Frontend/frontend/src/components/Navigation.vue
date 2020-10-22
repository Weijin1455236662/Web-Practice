<template>
  <div>
    <ul class="nav-container">
      <li :class="active===1?'active':''" @click="nav(1)">人员管理</li>
      <li :class="active===2?'active':''" @click="nav(2)">设备管理</li>
      <li :class="active===3?'active':''" @click="nav(3)">订单管理</li>
      <li :class="active===4?'active':''" @click="nav(4)">生产单</li>
      <li :class="active===5?'active':''" @click="nav(5)">资源负载图</li>
      <li :class="active===6?'active':''" @click="nav(6)">订单甘特图</li>
      <li :class="active===7?'active':''" @click="nav(7)">资源甘特图</li>
      <li :class="active===8?'active':''" @click="nav(8)">订单计划图</li>
      <li :class="active===9?'active':''" @click="nav(9)">订单-生产单关系表</li>
    </ul>
  </div>
</template>

<script>
    export default {
        name: "Navigation",
        data(){
            return{
                active: 1,
                navDic: {
                    1: '/manage/staff',
                    2: '/manage/machine',
                    3: '/manage/order',
                    4: '/schedule/work',
                    5: '/load',
                    6: '/order',
                    7: '/schedule/source',
                    8: '/schedule/order',
                    9: '/schedule/orderWork'
                }
            }
        },
        mounted() {
            this.changeActive();
        },
        watch:{
            $route: function () {
                this.changeActive();
            }
        },
        methods:{
            changeActive: function(){
                let path = this.$route.path;
                for(let i = 1;i <= 9;i++){
                    if (this.navDic[i] === path){
                        this.active = i;
                    }
                }
            },
            nav: function (active) {
                this.active = active;
                if(this.active===4){
                    this.$router.push({
                        path: this.navDic[active],
                        query: {
                            id: 0,
                            type: 0
                        }
                    }).catch(()=>{
                        console.log();
                    });
                }
                else {
                    this.$router.push({path:this.navDic[active]}).catch(()=>{
                        console.log();
                    });
                }
            }
        }
    }
</script>

<style scoped lang="less">
  .nav-container{
    padding: 0;
    text-align: left;
    li{
      cursor: pointer;
    }
    li:hover{
      background-color: #000000;
    }
    .active{
      background-color: #aaaaaa;
    }
  }
</style>