<template>
  <div class="nav-container">
      <div class="menu">
        <div class="name" :class="active===0?'active':''" @click="nav(0)">
          <div class="title">
            <i class="icon-font i-home"></i>
            <div class="text">主页</div>
          </div>
        </div>
      </div>
      <div class="menu">
        <div
          class="name"
          :class="active>0&&active<5?'active':''"
          @click="changeShowMenu1">
          <div class="title">
            <i class="icon-font i-user-group-border"></i>
            <div class="text">管理</div>
          </div>
          <i v-if="!showMenu1" class="icon-font i-v-right"></i>
          <i v-if="showMenu1" class="icon-font i-v-bottom"></i>
        </div>
        <div class="submenu" v-if="showMenu1">
          <div class="item" :class="active===1?'active':''" @click="nav(1)">
            <i class="icon-font i-user-group"></i>
            <div class="text">团队管理</div>
          </div>
          <div class="item" :class="active===2?'active':''" @click="nav(2)">
            <i class="icon-font i-tupu"></i>
            <div class="text">设备管理</div>
          </div>
          <div class="item" :class="active===3?'active':''" @click="nav(3)">
            <i class="icon-font i-list-index"></i>
            <div class="text">订单管理</div>
          </div>
          <div class="item" :class="active===4?'active':''" @click="nav(4)">
            <i class="icon-font i-list-index"></i>
            <div class="text">排程管理</div>
          </div>
        </div>
      </div>
      <div class="menu">
        <div
          class="name"
          :class="active>=5?'active':''"
          @click="changeShowMenu2">
          <div class="title">
            <i class="icon-font i-schedule"></i>
            <div class="text">计划</div>
          </div>
          <i v-if="!showMenu2" class="icon-font i-v-right"></i>
          <i v-if="showMenu2" class="icon-font i-v-bottom"></i>
        </div>
        <div class="submenu" v-if="showMenu2">
          <div class="item" :class="active===5?'active':''" @click="nav(5)">
            <i class="icon-font i-list-index"></i>
            <div class="text">生产单</div>
          </div>
          <div class="item" :class="active===6?'active':''" @click="nav(6)">
            <i class="icon-font i-list-index"></i>
            <div class="text">资源负载图</div>
          </div>
          <div class="item" :class="active===7?'active':''" @click="nav(7)">
            <i class="icon-font i-list-index"></i>
            <div class="text">订单甘特图</div>
          </div>
          <div class="item" :class="active===8?'active':''" @click="nav(8)">
            <i class="icon-font i-list-index"></i>
            <div class="text">资源甘特图</div>
          </div>
          <div class="item" :class="active===9?'active':''" @click="nav(9)">
            <i class="icon-font i-list-index"></i>
            <div class="text">订单计划图</div>
          </div>
          <div class="item" :class="active===10?'active':''" @click="nav(10)">
            <i class="icon-font i-list-index"></i>
            <div class="text">订单-生产单关系表</div>
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
                    2: '/manage/equipment',
                    3: '/manage/order',
                    4: '/manage/arrangement',
                    5: '/schedule/work',
                    6: '/load',
                    7: '/order',
                    8: '/schedule/source',
                    9: '/schedule/order',
                    10: '/schedule/orderWork'
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
                    for(let i = 1;i <= 10;i++){
                        if (this.navDic[i] === path){
                            this.active = i;
                        }
                    }
                }
                if(this.active>0&&this.active<5){
                    this.showMenu1 = true;
                }else if(this.active>=5){
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
                if(this.active===5){
                    this.$router.push({
                        path: this.navDic[active],
                        query: {
                            id: 0,
                            type: 0
                        }
                    }).catch(()=>{
                        console.log();
                    });
                } else if (this.active===9){
                  this.$router.push({
                    path: this.navDic[active],
                    query: {
                      id: 0
                    }
                  }).catch(()=>{
                    console.log()
                  });
                } else {
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
    margin-top: 72px;
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
        .title{
          display: flex;
          .text{
            margin-top: 1px;
            margin-left: 4px;
          }
        }
      }
      .name:hover{
        background-color: #e6e6e6;
      }
      .submenu{
        .item{
          display: flex;
          padding-left: 44px;
          height: 28px;
          font-size: 14px;
          line-height: 1.93;
          cursor: pointer;
          .text{
            margin-top: 3px;
            margin-left: 4px;
          }
        }
        .item:hover{
          background-color: #e6e6e6;
        }
      }
    }
    .active{
      color: #008dcb;
    }
  }
</style>