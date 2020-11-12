<template>
  <div class="main">
    <div class="head">
      <div class="title">资源负载图</div>
      <hr/>
    </div>
    <div class="loadPic">
      <div>
        <AngularGaugeItem class="pic" v-if="subcaption1" :punctuality="punctuality1" :caption="caption1" :subcaption="subcaption1" @change="changeDateList(startDate)"></AngularGaugeItem>
      </div>
      <div>
        <AngularGaugeItem class="pic" v-if="subcaption2" :punctuality="punctuality2" :caption="caption2" :subcaption="subcaption2" @change="changeDateList(startDate)"></AngularGaugeItem>
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
          <input id="day" type="date" v-model="startDate" @change="changeDateList(startDate)"/>
        </div>
        <div class="dateColumn" v-for="(date, index) in dateList" :key="index">{{date}}</div>
      </div>
      <div class="row" v-for="(data, index1) in datas" :key="index1">
        <div class="source">
          <div class="name">{{sourceList[index1]}}</div>
        </div>
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
import {getLoadRate} from "../api/scheduleApi"
export default {
  name: "Load",
  components: {LoadItem, AngularGaugeItem},
  data(){
    return{
      caption1: "设备总负载",
      subcaption1: "",
      punctuality1: 87,
      flag1: false,
      caption2: "人员总负载",
      subcaption2: "",
      punctuality2: 45,
      flag2: false,
      startDate: "",
      colorList: [
        '#9F9F9F', '#08ffff', '#4ea4cb', '#98f20c', '#f59d2a', '#e4c7ff', '#e02e44'
      ],
      indexList: [
        "图例", "0~20%", "20%~40%", "40%~60%", "60%~80%", "80%~100%", ">100%"
      ],
      sourceList: [
        "Line1", "Line2", "张三"
      ],
      dateList: [],
      datas: [
        [0.1, 0.32, 0.5, 0.67, 0.9, 1, 1.15111],
        [0.6, 0.24, 0.11, 0.98, 0.45, 1, 0.7],
        [1.3, 0.16, 0.41, 0.78, 0.92, 1, 0]
      ]
    }
  },
  methods: {
    // 计算日期加减
    calculateDate: function(date, n) {
      let date1 = new Date(Date.parse(date.replace(/-/g,"/")))
      let date2 = new Date(date1)
      date2.setDate(date1.getDate() + n)
      let resultDate = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + (date2.getDate().toString().length === 1 ? "0" + date2.getDate().toString() : date2.getDate())
      return resultDate
    },
    // 判断初始日期是否越界
    ifStartDateValid: function (date) {
      let start = sessionStorage.getItem("beginDate")
      let end = sessionStorage.getItem("endDate")
      if ((new Date(date.replace(/-/g,"/"))) <= (new Date(end.replace(/-/g,"/"))) && (new Date(date.replace(/-/g,"/"))) >= (new Date(start.replace(/-/g,"/")))) {
        return true
      } else {
        return false
      }
    },
    // 动态修改dateList和subcaption
    changeDateList: function (date) {
      if (!this.ifStartDateValid(date)) {
        alert("不在排程日期内，请重新选择日期！")
        date = sessionStorage.getItem("beginDate")
        this.startDate = sessionStorage.getItem("beginDate")
      }
      this.dateList = []
      this.dateList.push(date)
      for (let i = 1; i < 7; i++) {
        this.dateList.push(this.calculateDate(date, i))
      }

      let time1 = this.startDate.split("-")[0] + "年" + this.startDate.split("-")[1] + "月" + parseInt(this.startDate.split("-")[2]) + "日"
      let time2 = this.calculateDate(this.startDate, 6)
      let time3 = time2.split("-")[0] + "年" + time2.split("-")[1] + "月" + parseInt(time2.split("-")[2]) + "日"
      this.subcaption1 = this.subcaption2 = time1 + "  -  " + time3

      let result = getLoadRate(this.dateList)

    },
  },
  mounted(){
    this.startDate = sessionStorage.getItem("beginDate")
    this.changeDateList(this.startDate)
    setTimeout(function () {
      document.getElementsByTagName('path')[3].style.opacity = '0';
      document.getElementsByTagName('path')[7].style.opacity = '0';
      document.getElementsByTagName('circle')[0].style.opacity = '0';
      document.getElementsByTagName('circle')[1].style.opacity = '0';
      document.getElementsByClassName('pic')[0].style.opacity = '1';
      document.getElementsByClassName('pic')[1].style.opacity = '1';
    },200);
  }
}
</script>

<style scoped lang="less">
.main{
  margin-bottom: 100px;
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
    .pic{
      opacity: 0;
    }
  }
  .tip{
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 20px 0;
    .item{
      width: 100px;
      height: 35px;
      line-height: 35px;
    }
  }
  .graph{
    margin: 30px 6%;
    .dateBar{
      display: flex;
      margin-bottom: 20px;
      .dayWrap{
        width: 15%;
        margin: 0 1px;
        #dayLabel{
          margin-right: 10px;
        }
        #day{
          width: 118px;
        }
      }
      .dateColumn{
        width: 12%;
        min-width: 100px;
        text-align: center;
        font-size: 20px;
        border-top: 1px solid #000000;
        border-bottom: 1px solid #000000;
        padding-left: 1px;
      }
      .dateColumn:first-child{
        border-left: 0;
      }
    }
    .row{
      display: flex;
      .source{
        width: 15%;
        height: 120px;
        line-height: 120px;
        margin: 10px 0;
        display: flex;
        align-items: center;
        justify-content: center;
        .name{
          width: 60px;
          border: 1px dotted #000000;
        }
      }
      .loadWrap{
        display: flex;
        justify-content: center;
        width: 12%;
        min-width: 102px;
        margin-right: 1px;
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