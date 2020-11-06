<template>
  <div class="main">
    <div class="head">
      <div class="title">资源负载图</div>
      <hr/>
    </div>
    <div class="loadPic">
      <div>
        <AngularGaugeItem v-if="subcaption1" :punctuality="punctuality1" :caption="caption1" :subcaption="subcaption1"></AngularGaugeItem>
      </div>
      <div>
        <AngularGaugeItem v-if="subcaption2" :punctuality="punctuality2" :caption="caption2" :subcaption="subcaption2"></AngularGaugeItem>
      </div>
    </div>
    <div>
      <div class="tip" >
        <div class="item" v-for="(color, index) in colorList" :key="index" :style="{background: color}">{{indexList[index]}}</div>
      </div>
    </div>
    <div class="graph">
      <div class="dateBar">
        <div class="dayWrap">
          <label id="dayLabel" for="day">今天是</label>
          <input id="day" type="date"/>
        </div>
        <div class="dateColumn" v-for="(date, index) in dateList" :key="index">{{date}}</div>
      </div>
      <div class="row" v-for="(data, index1) in datas" :key="index1">
        <div class="source">{{sourceList[index1]}}</div>
        <div class="loadWrap" v-for="(d, index2) in data" :key="index2">
          <div class="loadItem">
            <load-item :data="d" :index="index2 + index1 * data.length"></load-item>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LoadItem from "../components/LoadItem";
import AngularGaugeItem from "../components/AngularGaugeItem";
export default {
  name: "Load",
  components: {LoadItem, AngularGaugeItem},
  data(){
    return{
      caption1: "设备总负载",
      subcaption1: "",
      punctuality1: 87,
      caption2: "人员总负载",
      subcaption2: "",
      punctuality2: 45,
      colorList: [
        '#9F9F9F', '#08ffff', '#4ea4cb', '#98f20c', '#f59d2a', '#e4c7ff', '#e02e44'
      ],
      indexList: [
        "图例", "0~20%", "20%~40%", "40%~60%", "60%~80%", "80%~100%", ">100%"
      ],
      sourceList: [
        "Line1", "Line2", "张三"
      ],
      dateList: [
        "2020-10-30", "2020-11-01", "2020-11-02", "2020-11-03", "2020-11-04", "2020-11-05", "2020-11-06", 
      ],
      datas: [
        [0.1, 0.32, 0.5, 0.67, 0.9, 1, 1.15111],
        [0.6, 0.24, 0.11, 0.98, 0.45, 1, 0.7],
        [1.3, 0.16, 0.41, 0.78, 0.92, 1, 0]
      ]
    }
  },
  mounted(){
    let date1 = new Date()
    let time1 = date1.getFullYear() + "年" + (date1.getMonth()+1) + "月" + date1.getDate() + "日"
    let date2 = new Date(date1)
    date2.setDate(date1.getDate() - 7)
    let time2 = date2.getFullYear() + "年" + (date2.getMonth()+1) + "月" + date2.getDate() + "日"
    this.subcaption1 = time2 + "-" + time1
    this.subcaption2 = time2 + "-" + time1
    console.log(this.subcaption1)
  }
}
</script>

<style scoped lang="less">
.main{
  .head{
    margin: 30px 5% 0;
    text-align: left;
    .title{
      font-size: 32px;
      font-weight: bold;
    }
  }
  .loadPic{
    margin-left: 40px;
    display: flex;
    justify-content: space-around;
  }
  .tip{
    display: flex;
    justify-content: center;
    margin: 20px 0;
    .item{
      width: 100px;
      height: 35px;
      line-height: 35px;
    }
  }
  .graph{
    margin: 0 6%;
    .dateBar{
      display: flex;
      margin-bottom: 20px;
      .dayWrap{
        width: 200px;
        #dayLabel{
          margin-right: 10px;
        }
        #day{
          width: 118px;
        }
      }
      .dateColumn{
        width: 170px;
        text-align: center;
        border: 1px solid #000000;

      }
      .dateColumn:first-child{
        border-left: 0;
      }
    }
    .row{
      display: flex;
      .source{
        width: 60px;
        height: 120px;
        line-height: 120px;
        margin: 10px 69px;
        border-style: dotted;
        border-width: 1px;
      }
      .loadWrap{
        display: flex;
        justify-content: center;
        width: 172px;
        padding: 10px 0;
        .loadItem{
          width: 100px;
          height: 120px;
        }
      }
    }
  }
}
</style>