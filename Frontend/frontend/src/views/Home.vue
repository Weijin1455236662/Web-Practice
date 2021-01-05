<template>
  <div :class="isPC?'home':'mobile'">
    <div class="welcome">
      <div class="title">欢迎使用APS系统！</div>
      <div class="name">互联网+实践B组</div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'Home',
    data(){
        return{
            isPC: true
        }
    },
    mounted() {
        if(sessionStorage.getItem('equipment')==='Mobile'){
            this.isPC = false;
        }
    }
}
</script>
<style scoped lang="less">
  .home{
    height: 100%;
    display: flex;
    justify-content: center;
    .welcome{
      margin-top: 30vh;
      text-align: center;
      .title{
        font-size: 56px;
      }
      .name{
        margin-top: 6px;
        margin-left: 270px;
        font-size: 22px;
      }
    }
  }
  .mobile{
    height: 100%;
    display: flex;
    justify-content: center;
    min-width: 100vw;
    .welcome{
      margin-top: 30vh;
      text-align: center;
      .title{
        font-size: 40px;
      }
      .name{
        margin-top: 6px;
        margin-left: 270px;
        font-size: 14px;
      }
    }
  }
</style>