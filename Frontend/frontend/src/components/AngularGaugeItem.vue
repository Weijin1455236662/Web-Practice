<template>
  <div>
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
export default {
  name: "AngularGuageItem",
  data(){
    return{
      type: "angulargauge",
      width: "40%",
      height: "35%",
      dataFormat: "json",
      dataSource: {}
    }
  },
  props: {
    punctuality: {
      type: Number,
      default: 0
    },
    caption: {
      type: String,
      default: ""
    },
    subcaption: {
      type: String,
      default: ""
    }
  },
  methods: {
    // 根据进度改变颜色
    calculateColor(cap, val){
      if (cap == "按期交货率") {
        if (0 <= val && val <= 25) {
          return "#F5000C"
        } else if (25 < val && val <= 50) {
          return "#f8bd19"
        } else if (50 < val && val <= 75) {
          return "#0C9EF0"
        } else if (75 < val && val <= 100) {
          return "#57F525"
        }
      } else {
        if (0 <= val && val <= 25) {
          return "#57F525"
        } else if (25 < val && val <= 50) {
          return "#0C9EF0"
        } else if (50 < val && val <= 75) {
          return "#f8bd19"
        } else if (75 < val && val <= 100) {
          return "#F5000C"
        }
      }
    }
  },
  mounted(){
    var that = this
    let colorrange = {
      color: [
        {
          minvalue: "0",
          maxvalue: that.punctuality,
          code: that.calculateColor(that.caption, that.punctuality)
        },
        {
          minvalue: that.punctuality,
          maxvalue: "100",
          code: "#EAEAEA"
        }
      ]
    }
    this.dataSource = {
      chart: {
        caption: that.caption,
        subcaption: that.subcaption,
        showvalue: "1",
        numbersuffix: "%",
        theme: "fusion",
        gaugeStartAngle: "90",
        gaugeEndAngle: "-270",
        valueFontSize: "30",
        valueFontBold: 1,
        showTickMarks: "0",
      },
      colorrange: colorrange,
      dials: {
        dial: [
          {
            value: that.punctuality,
            valueX: "50%",
            valueY: "165",
            toolText: this.caption + ": " + this.punctuality + "%"
          }
        ]
      }
    }
  }
}
</script>

<style>

</style>