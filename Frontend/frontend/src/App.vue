<template>
  <div id="app">
    <div class="header"></div>
    <div class="body">
      <div class="nav">
        <navigation></navigation>
      </div>
      <router-view/>
    </div>
  </div>
</template>

<style lang="less">
  body{
    margin: 0;
    width: 100%;
    height: 100%;
  }
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  .header{
    background-color: rgb(221,221,221);
    width: 100%;
    height: 30px;
  }
  .body{
    display: flex;
    .nav {
      background-color: rgb(221,221,221);
      width: 200px;
      height: 100vh;

      a {
        font-weight: bold;
        color: #2c3e50;

        &.router-link-exact-active {
          color: #42b983;
        }
      }
    }
  }
</style>
<script>
    import Navigation from "./components/Navigation";
    export default {
        components: {Navigation}
    }
</script>