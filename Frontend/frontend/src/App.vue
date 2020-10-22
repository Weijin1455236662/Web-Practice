<template>
  <div id="app">
    <div class="header">
      <div class="delete">
        <i class="icon-font i-close min"></i>
      </div>
      <div class="info" @click="changeNavState">
        <i class="icon-font i-list-pc" :class="showNav?'blue':''"></i>
        <div class="name">{{name}}</div>
      </div>
    </div>
    <div class="body">
      <div class="nav" v-if="showNav">
        <navigation></navigation>
      </div>
      <div class="main">
        <router-view/>
      </div>
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
    height: 100%;
  }
  #app {
    font-family: 'Source Sans Pro', 'Trebuchet MS', Arial, serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #000000;
  }
  .header{
    background-color: #f2f2f2;
    width: 100%;
    height: 36px;
    border-bottom: 1px solid #cccccc;
    font-size: 14px;
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
    .nav {
      background-color: #f2f2f2;
      width: 14vw;
      height: 100vh;
      border-right: 1px solid #cccccc;
      a {
        font-weight: bold;
        color: #2c3e50;

        &.router-link-exact-active {
          color: #42b983;
        }
      }
    }
    .main{
      width: 100%;
      height: 100%;
    }
  }
</style>