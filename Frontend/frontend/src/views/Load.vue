<template>
  <div :class="isPC?'main':'mobile'">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
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
import MessageTip from "../components/MessageTip";
export default {
  name: "Load",
  components: {MessageTip, LoadItem, AngularGaugeItem},
  data(){
    return{
        isPC: true,
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
      sourceList: [],
      dateList: [],
      datas: [],
      messageState: false,
      messageType: 0,
      message: '',
    }
  },
  methods: {
    showMessage: function(type, message){
      this.messageType = type;
      this.message = message;
      this.messageState = true;
      setTimeout(this.hideMessage,2000);
    },
    hideMessage: function(){
      this.messageState = false;
      setTimeout(function () {
          this.messageType = 0;
          this.message = '';
      },600);
    },
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
        this.showMessage(1, "不在排程日期内，请重新选择日期！")
        if(this.startDate < sessionStorage.getItem("beginDate")){
            date = sessionStorage.getItem("beginDate");
            this.startDate = sessionStorage.getItem("beginDate");
        }else{
            date = sessionStorage.getItem("endDate");
            this.startDate = sessionStorage.getItem("endDate");
        }
      }
      this.dateList = []
      this.sourceList = []
      this.datas = []
      this.dateList.push(date)
      for (let i = 1; i < 7; i++) {
        this.dateList.push(this.calculateDate(date, i))
      }

      this.subcaption1 = this.subcaption2 = '';
      this.punctuality1 = this.punctuality2 = 0;
      let that = this;
      setTimeout(function () {
          let time1 = that.startDate.split("-")[0] + "年" + that.startDate.split("-")[1] + "月" + parseInt(that.startDate.split("-")[2]) + "日"
          let time2 = that.calculateDate(that.startDate, 6)
          let time3 = time2.split("-")[0] + "年" + time2.split("-")[1] + "月" + parseInt(time2.split("-")[2]) + "日"
          that.subcaption1 = that.subcaption2 = time1 + "  -  " + time3

          // 获得负载率
          let result = getLoadRate(that.dateList)
          let personList = result.personList
          let equipList = result.equipList
          // console.log(personList)
          // console.log(equipList)
          for (let i = 0; i < equipList.length; i++) {
              that.sourceList.push(equipList[i].name)
              let temp = []
              for (let j = 0; j < equipList[i].innerDateList.length; j++) {
                  temp.push(equipList[i].innerDateList[j][that.dateList[j]] / 24 / 60)
                  that.punctuality1 = that.punctuality1 + equipList[i].innerDateList[j][that.dateList[j]] / 24 /60;
              }
              that.datas.push(temp)
          }
          for (let i = 0; i < personList.length; i++) {
              that.sourceList.push(personList[i].name)
              let temp = []
              for (let j = 0; j < personList[i].innerDateList.length; j++) {
                  temp.push(personList[i].innerDateList[j][that.dateList[j]] / 12 / 60)
                  that.punctuality2 = that.punctuality2 + personList[i].innerDateList[j][that.dateList[j]] / 12 / 60;
              }
              that.datas.push(temp)
          }
          that.punctuality1 = that.punctuality1 * 100 / (7 * equipList.length)
          that.punctuality2 = that.punctuality2 * 100 / (7 * personList.length)
          setTimeout(function () {
              document.getElementsByTagName('path')[3].style.opacity = '0';
              document.getElementsByTagName('path')[7].style.opacity = '0';
              document.getElementsByTagName('circle')[0].style.opacity = '0';
              document.getElementsByTagName('circle')[1].style.opacity = '0';
              document.getElementsByClassName('pic')[0].style.opacity = '1';
              document.getElementsByClassName('pic')[1].style.opacity = '1';
          },200);
      }, 100)

    },
  },
  mounted(){
    this.startDate = sessionStorage.getItem("beginDate")
    this.changeDateList(this.startDate)
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
    min-height: 50vh;
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
    margin: 30px 4%;
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
          width: 122px;
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
          min-width: 100px;
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
  .mobile{
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
      min-height: 50vh;
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
      margin: 30px 4%;
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
            width: 122px;
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
            min-width: 100px;
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