<template>
  <div>
    <h1>资源负载图</h1>
    <div class="table">
      <div class="row" v-for="(data, index1) in datas" :key="index1">
        <div class="load-item" v-for="(d, index2) in data" :key="index2">
          <load-item :data="d" :index="index2 + index1 * data.length"></load-item>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
    import LoadItem from "../components/LoadItem";
    export default {
        name: "Load",
        components: {LoadItem},
        data(){
            return{
                datas: [
                    [0.1, 0.32, 0.5, 0.67, 0.9, 1, 1.15111],
                    [0.6, 0.24, 0.11, 0.98, 0.45, 1, 0.7],
                    [1.3, 0.16, 0.41, 0.78, 0.92, 1, 0]
                ]
            }
        }
    }
</script>

<style scoped lang="less">
  .row{
    display: flex;
    margin: 20px 0;
    .load-item{
      width: 100px;
      height: 120px;
      margin-left: 10px;
    }
  }
</style>