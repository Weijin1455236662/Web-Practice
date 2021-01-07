<template>
  <div :class="isPC?'main':'mobile'">
    <div class="head">
      <div class="text">资源甘特图</div>
      <hr/>
    </div>
    <div class="dateWrap">
      <label id="date_label" for="date">今天是：</label>
      <input id="date" type="date" v-model="date"/>
    </div>
    <fusioncharts
      :type="type"
      :width="width"
      :height="height"
      :dataFormat="dataFormat"
      :dataSource="dataSource"
    ></fusioncharts>
  </div>
</template>

<script>
import {reduceSource} from "../../data/sourceSolve";

export default {
    name: "SourceSchedule",
    data(){
        return{
            type: "gantt",
            width: "95%",
            height: "70%",
            dataFormat: "json",
            dataSource: {},
            date: '',
          isPC: true
        }
    },
    mounted(){
      if(sessionStorage.getItem('equipment')==='Mobile'){
        this.isPC = false;
      }
        this.date = sessionStorage.getItem('beginDate');
        // this.updateData();
    },
    methods:{
        updateData: function(){
            let data = reduceSource(this.date);
            let that = this;
            if (data===''){
                let timer = setInterval(function () {
                    let data = reduceSource(that.date);
                    if (data!==''){
                        clearInterval(timer);
                        that.render(data);
                    }
                }, 100)
            }else{
                this.render(data);
            }
        },
        calculateTime(val){
            // console.log(val)
            if (parseInt(val.slice(0,2)) + 7 <= 24) {
                return (parseInt(val.slice(0,2)) + 7) + val.slice(2)
            } else {
                return "次日" + (parseInt(val.slice(0,2)) - 17) + val.slice(2)
            }
        },
        render: function(data) {
            console.log(data)
            let task = [];
            let that = this;
            data.data.forEach(function(item){
              // console.log(item)
              task.push({
                processid: item.processid,
                start: item.start,
                end: item.end,
                color: item.color,
                label: "子订单：" + item.label + "<br/>时间："+ that.calculateTime(item.start) + " - " + that.calculateTime(item.end)
              })
            });
            let category = []
            if (that.isPC === true) {
                category = [
                    {
                        start: "00:00:00",
                        end: "01:59:59",
                        label: "0:00-2:00"
                    },
                    {
                        start: "02:00:00",
                        end: "03:59:59",
                        label: "2:00-4:00"
                    },
                    {
                        start: "04:00:00",
                        end: "05:59:59",
                        label: "4:00-6:00"
                    },
                    {
                        start: "06:00:00",
                        end: "07:59:59",
                        label: "6:00-8:00"
                    },
                    {
                        start: "08:00:00",
                        end: "09:59:59",
                        label: "8:00-10:00"
                    },
                    {
                        start: "10:00:00",
                        end: "11:59:59",
                        label: "10:00-12:00"
                    },
                    {
                        start: "12:00:00",
                        end: "13:59:59",
                        label: "12:00-14:00"
                    },
                    {
                        start: "14:00:00",
                        end: "15:59:59",
                        label: "14:00-16:00"
                    },
                    {
                        start: "16:00:00",
                        end: "17:59:59",
                        label: "16:00-18:00"
                    },
                    {
                        start: "18:00:00",
                        end: "19:59:59",
                        label: "18:00-20:00"
                    },
                    {
                        start: "20:00:00",
                        end: "21:59:59",
                        label: "20:00-22:00"
                    },
                    {
                        start: "22:00:00",
                        end: "23:59:59",
                        label: "22:00-24:00"
                    }
                ]
            } else {
                category = [
                    {
                        start: "00:00:00",
                        end: "01:59:59",
                        label: "0"
                    },
                    {
                        start: "02:00:00",
                        end: "03:59:59",
                        label: "2"
                    },
                    {
                        start: "04:00:00",
                        end: "05:59:59",
                        label: "4"
                    },
                    {
                        start: "06:00:00",
                        end: "07:59:59",
                        label: "6"
                    },
                    {
                        start: "08:00:00",
                        end: "09:59:59",
                        label: "8"
                    },
                    {
                        start: "10:00:00",
                        end: "11:59:59",
                        label: "10"
                    },
                    {
                        start: "12:00:00",
                        end: "13:59:59",
                        label: "12"
                    },
                    {
                        start: "14:00:00",
                        end: "15:59:59",
                        label: "14"
                    },
                    {
                        start: "16:00:00",
                        end: "17:59:59",
                        label: "16"
                    },
                    {
                        start: "18:00:00",
                        end: "19:59:59",
                        label: "18"
                    },
                    {
                        start: "20:00:00",
                        end: "21:59:59",
                        label: "20"
                    },
                    {
                        start: "22:00:00",
                        end: "23:59:59",
                        label: "22"
                    }
                ]
            }
            this.dataSource = {
              chart: {
                dateformat: "dd/mm/yyyy",
                outputdateformat: "hh:mn:ss",
                caption: "资源甘特图",
                captionFontSize: 14,
                plottooltext: "$label",
                theme: "fusion"
              },
              categories: [
                {
                  category: [
                    {
                      start: "00:00:00",
                      end: "23:59:59",
                      label: "时间"
                    }
                  ]
                },
                {
                  align: "center",
                  category: category
                }
              ],
              tasks: {
                showlabels: "0",
                color: "#5D62B5",
                task: task
              },
              processes: {
                fontsize: "12",
                isbold: "1",
                align: "Center",
                headertext: "生产线/员工",
                headerfontsize: "14",
                headervalign: "middle",
                headeralign: "left",
                process: data.process
              },
              // datatable: {
              //   headervalign: "middle",
              //   datacolumn: [
              //     {
              //       headertext: "负载率",
              //       headervalign: "middle",
              //       headeralign: "middle",
              //       align: "middle",
              //       text: data.load
              //     }
              //   ]
              // }
            };
            console.log(this.dataSource)
        }
    },
    watch: {
        'date': function () {
            this.updateData();
        }
    }
}
</script>

<style scoped lang="less">
.main{
  .head{
    margin: 30px 5% 0;
    text-align: left;
    .text{
      font-size: 32px;
      font-weight: bold;
    }
  }
  .dateWrap{
    display: flex;
    align-items: center;
    margin: 20px 0 10px 7%;
    #date_label{
      font-size: 20px;
      font-weight: bold;
    }
    #date{
      font-size: 16px;
      cursor: pointer;
      width: 260px;
      padding-left: 6px;
    }
  }
}
.mobile{
  .head{
    margin: 30px 4% 0;
    text-align: left;
    .text{
      font-size: 16px;
      font-weight: bold;
    }
  }
  .dateWrap{
    display: flex;
    align-items: center;
    margin: 20px 0 10px 7%;
    #date_label{
      font-size: 14px;
      font-weight: bold;
    }
    #date{
      font-size: 14px;
      cursor: pointer;
      width: 230px;
      padding-left: 6px;
    }
  }
}
</style>
