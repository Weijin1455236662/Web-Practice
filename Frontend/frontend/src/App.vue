<template>
  <div id="app">
    <div class="header" v-if="showHead">
      <div class="left">
        <div class="delete" @click="changeHeadState">
          <i class="icon-font i-close min"></i>
        </div>
        <div class="info" @click="changeNavState">
          <i class="icon-font i-list-pc info-icon" :class="showNav?'blue':''"></i>
          <div class="name">{{name}}</div>
        </div>
      </div>
      <div class="right">
        <div class="text" title="前往修改" @click="goTimeManage()">排程时间段：{{begin}} ~ {{end}}</div>
      </div>
    </div>
    <div class="body">
      <div class="nav" v-if="showHead&&showNav">
        <navigation></navigation>
      </div>
      <div :id="showNav&&showHead?'main-1':showHead?'main-2':'main-3'">
        <router-view/>
      </div>
    </div>
    <div class="tool" v-if="!showHead" @click="changeHeadState">
      <i class="icon-font i-v-right min"></i>
    </div>
  </div>
</template>

<script>
    import Navigation from "./components/Navigation";
    export default {
        name: 'app',
        components: {Navigation},
        data(){
            return{
                name: '',
                showHead: true,
                showNav: true,
                begin: '',
                end:''
            }
        },
        mounted() {
            this.name = this.$route.name;
            let date1 = sessionStorage.getItem('beginDate');
            let date2 = sessionStorage.getItem('endDate');
            if(!date1){
                sessionStorage.setItem('beginDate', '2018-11-09');
                date1 = '2018-11-09';
            }
            if(!date2){
                sessionStorage.setItem('endDate', '2018-12-31');
                date2 = '2018-12-31'
            }
            this.begin = date1;
            this.end = date2;
        },
        watch:{
            $route: function () {
                this.name = this.$route.name;
            },
            sessionStorage
        },
        methods:{
            changeHeadState: function() {
                this.showHead = !this.showHead;
                this.showNav = true;
            },
            changeNavState: function () {
                this.showNav = !this.showNav;
            },
            goTimeManage: function () {
                this.$router.push('/manage/date').catch(err=>{
                    // location.reload();
                    console.log();
                });
            },
            changeTime: function (begin, end) {
                this.begin = begin;
                this.end = end;
            }
        }
    }
</script>

<style lang="less">
  body{
    margin: 0;
    width: 100%;
    height: 100vh;
  }
  #app{
    font-family: 'Source Sans Pro', 'Trebuchet MS', Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #000000;
    position: relative;
    height: 100%;
    .tool{
      background-color: #f2f2f2;
      position: absolute;
      top: 0;
      left: 0;
      width: 28px;
      border-right: 1px solid #cbcbcb;
      border-bottom: 1px solid #cccccc;
      padding: 12px 0;
      font-size: 10px;
      transform: translateX(-28px);
      cursor: pointer;
    }
    .tool:hover{
      transform: translateX(0);
      transition: transform 0.5s;
    }
    .header{
      background-color: #f2f2f2;
      width: 100%;
      height: 36px;
      border-bottom: 1px solid #cccccc;
      font-size: 14px;
      display: flex;
      justify-content: space-between;
      position: fixed;
      z-index: 100;
      .left{
        display: flex;
        .delete{
          width: 28px;
          border-right: 1px solid #cbcbcb;
          padding: 12px 0;
          font-size: 10px;
          cursor: pointer;
          .min{
            font-size: 12px;
          }
        }
        .info{
          height: 28px;
          margin: 4px 6px;
          display: flex;
          border-radius: 4px;
          cursor: pointer;
          i{
            font-size: 28px;
            margin-left: 10px;
          }
          .name{
            font-size: 16px;
            line-height: 29px;
            margin: 0 16px 0 12px;
          }
          .blue{
            color: #008dcb;
          }
          .info-icon{
            margin-top: -4px;
          }
        }
        .info:hover{
          background-color: #e6e6e6;
        }
      }
      .right{
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 4px 10px 4px 0;
        border-radius: 4px;
        cursor: pointer;
        .text{
          font-size: 15px;
          margin: 0 12px 0 12px;
        }
      }
      .right:hover{
        background-color: #e6e6e6;
      }
    }
    .body{
      display: flex;
      height: 100%;
      .nav {
        background-color: #f2f2f2;
        min-width: 220px;
        height: 100%;
        border-right: 1px solid #cccccc;
        position: fixed;
        a {
          font-weight: bold;
          color: #2c3e50;

          &.router-link-exact-active {
            color: #42b983;
          }
        }
      }
      #main-1{
        width: 100%;
        margin-top: 36px;
        margin-left: 220px;
      }
      #main-2{
        width: 100%;
        margin-top: 36px;
      }
      #main-3{
        width: 100%;
      }
    }
  }
</style>