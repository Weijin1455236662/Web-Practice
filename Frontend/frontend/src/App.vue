<template>
  <div id="app">
    <div class="header" v-if="showHead">
      <div class="delete" @click="changeHeadState">
        <i class="icon-font i-close min"></i>
      </div>
      <div class="info" @click="changeNavState">
        <i class="icon-font i-list-pc" :class="showNav?'blue':''"></i>
        <div class="name">{{name}}</div>
      </div>
    </div>
    <div class="body">
      <div class="nav" v-if="showHead&&showNav">
        <navigation></navigation>
      </div>
      <div :id="showNav&&showHead?'main-1':showHead?'main-2':'main3'">
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
                showNav: true
            }
        },
        mounted() {
            this.name = this.$route.name;
        },
        watch:{
            $route: function () {
                this.name = this.$route.name;
            }
        },
        methods:{
            changeHeadState: function() {
                this.showHead = !this.showHead;
                this.showNav = true;
            },
            changeNavState: function () {
                this.showNav = !this.showNav;
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
    font-family: 'Source Sans Pro', 'Trebuchet MS', Arial, serif;
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
      cursor: pointer;
    }
    .header{
      background-color: #f2f2f2;
      width: 100%;
      height: 36px;
      border-bottom: 1px solid #cccccc;
      font-size: 14px;
      display: flex;
      position: fixed;
      z-index: 100;
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
        cursor: pointer;
        i{
          font-size: 28px;
        }
        .name{
          font-size: 14px;
          line-height: 29px;
          margin: 0 6px 0 12px;
        }
        .blue{
          color: #008dcb;
        }
      }
      .info:hover{
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
        height: 100%;
        margin-top: 36px;
        margin-left: 220px;
      }
      #main-2{
        width: 100%;
        height: 100%;
        margin-top: 36px;
      }
      #main-3{
        width: 100%;
        height: 100%;
      }
    }
  }
</style>