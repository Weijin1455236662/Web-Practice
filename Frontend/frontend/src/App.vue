<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <router-view/>
    <div id="chart-container">
      <fusioncharts
          :type="type"
          :width="width"
          :height="height"
          :dataFormat="dataFormat"
          :dataSource="dataSource"
      ></fusioncharts>
    </div>
  </div>
</template>

// STEP 1: to include the dependencies
<script>

    const dataSource = {
        chart: {
            caption: "New Store Opening - Project Plan",
            dateformat: "dd/mm/yyyy",
            outputdateformat: "ddds mns yy",
            ganttwidthpercent: "60",
            ganttpaneduration: "40",
            ganttpanedurationunit: "d",
            useverticalscrolling: "0",
            plottooltext: "<b>$label</b><br>Start: <b>$start</b><br>End: <b>$end</b>",
            theme: "fusion"
        },
        connectors: [
            {
                connector: [
                    {
                        fromtaskid: "1-1",
                        totaskid: "2-1",
                        color: "#F2726F",
                        thickness: "2"
                    },
                    {
                        fromtaskid: "2-1",
                        totaskid: "3-1",
                        color: "#F2726F",
                        thickness: "2"
                    },
                    {
                        fromtaskid: "3-1",
                        totaskid: "4-1",
                        color: "#F2726F",
                        thickness: "2"
                    },
                    {
                        fromtaskid: "4-1",
                        totaskid: "5-1",
                        color: "#F2726F",
                        thickness: "2"
                    },
                    {
                        fromtaskid: "5-1",
                        totaskid: "6-1",
                        color: "#F2726F",
                        thickness: "2"
                    },
                    {
                        fromtaskid: "6-1",
                        totaskid: "7-1",
                        color: "#F2726F",
                        thickness: "2"
                    },
                    {
                        fromtaskid: "7-1",
                        totaskid: "8-1",
                        color: "#F2726F",
                        thickness: "2"
                    },
                    {
                        fromtaskid: "8-1",
                        totaskid: "9-1",
                        color: "#F2726F",
                        thickness: "2"
                    },
                    {
                        fromtaskid: "9-1",
                        totaskid: "10-1",
                        color: "#F2726F",
                        thickness: "2"
                    }
                ]
            }
        ],
        trendlines: [
            {
                line: [
                    {
                        start: "14/4/2018",
                        displayvalue: "AC Testing",
                        color: "5D5D5D",
                        thickness: "1",
                        dashed: "1"
                    }
                ]
            }
        ],
        milestones: {
            milestone: [
                {
                    date: "30/4/2018",
                    taskid: "10-1",
                    color: "#f8bd19",
                    shape: "star",
                    tooltext: "Store Opening"
                }
            ]
        },
        tasks: {
            task: [
                {
                    label: "Clear Site (4 Days)",
                    processid: "1",
                    start: "1/3/2018",
                    end: "5/3/2018",
                    id: "1-1",
                    color: "#5D62B5"
                },
                {
                    label: "Drainage & Foundation (7 Days)",
                    processid: "2",
                    start: "6/3/2018",
                    end: "13/3/2018",
                    id: "2-1",
                    color: "#5D62B5"
                },
                {
                    label: "Ground Floor (8 Days)",
                    processid: "3",
                    start: "14/3/2018",
                    end: "22/3/2018",
                    id: "3-1",
                    color: "#5D62B5"
                },
                {
                    label: "First Floor (5 Days)",
                    processid: "4",
                    start: "23/3/2018",
                    end: "28/3/2018",
                    id: "4-1",
                    color: "#5D62B5"
                },
                {
                    label: "Roofing (5 Days)",
                    processid: "5",
                    start: "29/3/2018",
                    end: "3/4/2018",
                    id: "5-1",
                    color: "#5D62B5"
                },
                {
                    label: "Connect Electricity (6 Days)",
                    processid: "6",
                    start: "4/4/2018",
                    end: "10/4/2018",
                    id: "6-1",
                    color: "#5D62B5"
                },
                {
                    label: "Air Conditioning (3 Days)",
                    processid: "7",
                    start: "11/4/2018",
                    end: "14/4/2018",
                    id: "7-1",
                    color: "#5D62B5"
                },
                {
                    label: "Interiors (8 Days)",
                    processid: "8",
                    start: "15/4/2018",
                    end: "23/4/2018",
                    id: "8-1",
                    color: "#5D62B5"
                },
                {
                    label: "Racking (3 Days)",
                    processid: "9",
                    start: "24/4/2018",
                    end: "28/4/2018",
                    id: "9-1",
                    color: "#5D62B5"
                },
                {
                    label: "Stock Shelving (1 Days)",
                    processid: "10",
                    start: "29/4/2018",
                    end: "30/4/2018",
                    id: "10-1",
                    color: "#5D62B5",
                    toppadding: "9"
                }
            ]
        },
        processes: {
            headertext: "Task",
            isanimated: "1",
            headervalign: "bottom",
            headeralign: "left",
            align: "left",
            isbold: "1",
            bgalpha: "25",
            process: [
                {
                    label: "Clear site",
                    id: "1"
                },
                {
                    label: "Drainage & Foundation",
                    id: "2"
                },
                {
                    label: "Ground Floor",
                    id: "3"
                },
                {
                    label: "First Floor",
                    id: "4"
                },
                {
                    label: "Roofing",
                    id: "5"
                },
                {
                    label: "Connect Electricity",
                    id: "6"
                },
                {
                    label: "Air Conditioning",
                    id: "7"
                },
                {
                    label: "Interiors",
                    id: "8"
                },
                {
                    label: "Racking",
                    id: "9"
                },
                {
                    label: "Stock Shelving",
                    id: "10"
                }
            ]
        },
        categories: [
            {
                align: "middle",
                category: [
                    {
                        start: "1/3/2018",
                        end: "31/3/2018",
                        label: "March"
                    },
                    {
                        start: "1/4/2018",
                        end: "1/5/2018",
                        label: "April"
                    }
                ]
            },
            {
                align: "center",
                category: [
                    {
                        start: "1/3/2018",
                        end: "7/3/2018",
                        label: "Week 1"
                    },
                    {
                        start: "8/3/2018",
                        end: "14/3/2018",
                        label: "Week 2"
                    },
                    {
                        start: "15/3/2018",
                        end: "21/3/2018",
                        label: "Week 3"
                    },
                    {
                        start: "22/3/2018",
                        end: "28/3/2018",
                        label: "Week 4"
                    },
                    {
                        start: "29/3/2018",
                        end: "5/4/2018",
                        label: "Week 5"
                    },
                    {
                        start: "6/4/2018",
                        end: "12/4/2018",
                        label: "Week 6"
                    },
                    {
                        start: "13/4/2018",
                        end: "19/4/2018",
                        label: "Week 7"
                    },
                    {
                        start: "19/4/2018",
                        end: "25/4/2018",
                        label: "Week 8"
                    },
                    {
                        start: "25/4/2018",
                        end: "1/5/2018",
                        label: "Week 9"
                    }
                ]
            }
        ]
    };

    export default {
        name: 'app',
        data(){
            return{
                type: "gantt",
                width: "50%",
                height: "50%",
                dataFormat: "json",
                dataSource
            }
        }
    };
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
