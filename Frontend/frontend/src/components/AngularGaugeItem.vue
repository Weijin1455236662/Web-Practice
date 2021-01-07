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
      width: "38%",
      height: "50%",
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
  watch: {
    subcaption(newVal, oldVal) {
      this.subcaption = newVal
      console.log(newVal)
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
    let that = this
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
    let dial = [
      {
        value: that.punctuality === 100 ? 99.9 : that.punctuality,
        valueX: "50%",
        valueY: "210",
        toolText: this.caption + ": " + that.punctuality + "%"
      }
    ]
    this.dataSource = {
      chart: {
        caption: that.caption,
        captionFontSize: 14,
        subcaption: that.subcaption,
        subcaptionFontSize: 10,
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
        dial: dial
      }
    }
  }
}
</script>

<style>

</style>
