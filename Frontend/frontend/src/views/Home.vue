<template>
  <div class="home">
    <div class="welcome">
      <div class="title">欢迎使用APS系统！</div>
      <div class="name">互联网+实践B组</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
}
</script>
<style scoped lang="less">
  .home{
    height: 100%;
    display: flex;
    justify-content: center;
  }
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
</style>