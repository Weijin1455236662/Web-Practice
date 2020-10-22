<template>
  <div>
    <div class="nav-container">
      <div class="menu">
        <div class="name" :class="active===0?'active':''" @click="nav(0)">
          <div class="body">
            <i class="icon-font i-home"></i>
            <div>主页</div>
          </div>
        </div>
      </div>
      <div class="menu">
        <div
          class="name"
          :class="active>0&&active<4?'active':''"
          @click="changeShowMenu1">
          <div class="body">
            <i class="icon-font i-user-group-border"></i>
            <div>管理</div>
          </div>
          <i class="icon-font i-v-bottom"></i>
        </div>
        <div class="submenu" v-if="showMenu1">
          <div class="item" :class="active===1?'active':''" @click="nav(1)"><i class="icon-font i-list-index"></i>人员管理</div>
          <div class="item" :class="active===2?'active':''" @click="nav(2)"><i class="icon-font i-list-index"></i>设备管理</div>
          <div class="item" :class="active===3?'active':''" @click="nav(3)"><i class="icon-font i-list-index"></i>订单管理</div>
        </div>
      </div>
      <div class="menu">
        <div
          class="name"
          :class="active>=4?'active':''"
          @click="changeShowMenu2">
          <div class="body">
            <i class="icon-font i-schedule"></i>
            <div>计划</div>
          </div>
          <i class="icon-font i-v-bottom"></i>
        </div>
        <div class="submenu" v-if="showMenu2">
          <div class="item" :class="active===4?'active':''" @click="nav(4)"><i class="icon-font i-list-index"></i>生产单</div>
          <div class="item" :class="active===5?'active':''" @click="nav(5)"><i class="icon-font i-list-index"></i>资源负载图</div>
          <div class="item" :class="active===6?'active':''" @click="nav(6)"><i class="icon-font i-list-index"></i>订单甘特图</div>
          <div class="item" :class="active===7?'active':''" @click="nav(7)"><i class="icon-font i-list-index"></i>资源甘特图</div>
          <div class="item" :class="active===8?'active':''" @click="nav(8)"><i class="icon-font i-list-index"></i>订单计划图</div>
          <div class="item" :class="active===9?'active':''" @click="nav(9)"><i class="icon-font i-list-index"></i>订单-生产单关系表</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Navigation",
        data(){
            return{
                active: 1,
                showMenu1: false,
                showMenu2: false,
                navDic: {
                    0: '/',
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
                if(path==='/'){
                    this.active = 0;
                }else{
                    for(let i = 1;i <= 9;i++){
                        if (this.navDic[i] === path){
                            this.active = i;
                        }
                    }
                }
                if(this.active>0&&this.active<4){
                    this.showMenu1 = true;
                }else if(this.active>=4){
                    this.showMenu2 = true;
                }
            },
            changeShowMenu1: function(){
                this.showMenu1 = !this.showMenu1;
            },
            changeShowMenu2: function(){
                this.showMenu2 = !this.showMenu2;
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
    margin-top: 36px;
    padding: 0;
    text-align: left;
    .menu{
      font-size: 16px;
      .name{
        height: 32px;
        line-height: 2.2;
        justify-content: space-between;
        display: flex;
        padding-left: 20px;
        padding-right: 14px;
        cursor: pointer;
      }
      .submenu{
        .item{
          padding-left: 44px;
          height: 28px;
          font-size: 14px;
          line-height: 1.93;
          cursor: pointer;
        }
        .item:hover{
          background-color: #dddddd;
        }
      }
    }
    .active{
      color: #008dcb;
    }
  }
</style>