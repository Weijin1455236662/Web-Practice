<template>
  <div :class="isPC?'main':'mobile'">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div v-if="isPC" class="text">订单管理</div>
      <div v-if="!isPC" class="wrap">
        <div class="text">订单管理</div>
        <div title="添加订单"><i class="iconfont icon-tianjia" @click="addOrder"></i></div>
      </div>
      <hr/>
    </div>
    <div class="container">
      <div class="table">
        <div class="row">
          <div class="column index0">序号</div>
          <div class="column index1">
            <div>订单号码</div>
            <div class="sort">
              <div class="up" :class="sortMode===1?'active':''" @click="changeSortMode(1)"><i class="iconfont icon-paixu-shang"></i></div>
              <div class="down" :class="sortMode===2?'active':''" @click="changeSortMode(2)"><i class="iconfont icon-paixu-xia"></i></div>
            </div></div>
          <div class="column index2">
            <div>物料号码</div>
            <div class="sort">
              <div class="up" :class="sortMode===3?'active':''" @click="changeSortMode(3)"><i class="iconfont icon-paixu-shang"></i></div>
              <div class="down" :class="sortMode===4?'active':''" @click="changeSortMode(4)"><i class="iconfont icon-paixu-xia"></i></div>
            </div></div>
          <div class="column index3">
            <div>物料数量</div>
            <div class="sort">
              <div class="up" :class="sortMode===5?'active':''" @click="changeSortMode(5)"><i class="iconfont icon-paixu-shang"></i></div>
              <div class="down" :class="sortMode===6?'active':''" @click="changeSortMode(6)"><i class="iconfont icon-paixu-xia"></i></div>
            </div></div>
          <div class="column index4">
            <div>交付日期</div>
            <div class="sort">
              <div class="up" :class="sortMode===7?'active':''" @click="changeSortMode(7)"><i class="iconfont icon-paixu-shang"></i></div>
              <div class="down" :class="sortMode===8?'active':''" @click="changeSortMode(8)"><i class="iconfont icon-paixu-xia"></i></div>
            </div>
          </div>
          <div class="column index5">操作</div>
          <div v-if="isPC" class="column index6" title="添加订单"><i class="iconfont icon-tianjia tooltip" @click="addOrder"></i></div>
        </div>
        <div class="row" v-for="(order, index) in allOrder" :key="index">
          <div class="column index0">{{(index+1)}}</div>
          <div class="column index1">{{order.orderid}}</div>
          <div class="column index2">{{order.material_code}}</div>
          <div class="column index3">{{order.quantity}}</div>
          <div class="column index4">{{dateTransfer(order.delivery_date)}}</div>
          <div class="column index5">
            <i class="icon-font i-edit tooltip" title="修改订单" @click="updateOrder(order)"></i>
            <i class="icon-font i-delete tooltip" title="删除订单" @click="deleteOrder(order.orderid)"></i>
          </div>
          <div v-if="isPC" class="column index6"></div>
        </div>
        <div class="noOrder" v-if="allOrder.length===0">无订单...</div>
      </div>
      <div v-if="isPC" class="filter">
        <div class="head-text">筛选</div>
        <hr/>
        <div class="content">
          <label id="stringLabel" for="string">内容筛选：</label><br/>
          <input id="string" type="number" placeholder="仅限数字字符串……" v-model="filterString" @input="doFilter"/>
        </div>
        <div class="time">
          <label id="startLabel" for="start">时间筛选：</label><br/>
          <input id="start" type="date" v-model="filterStart" @change="doFilter"/>
          <label id="endLabel" for="end">-</label>
          <input id="end" type="date" v-model="filterEnd" @change="doFilter"/><br/>
          <button class="button" @click="clearFilter">重置</button>
        </div>
      </div>
    </div>
    <div class="background" v-if="showForm" @click="closeForm"></div>
    <div class="form" v-if="showForm">
      <div class="form_head">
        <div class="text">{{formType===0?'新增订单':'更新订单'}}</div>
        <div class="close" @click="closeForm"><i class="icon-font i-close"></i></div>
        <hr/>
      </div>
      <div class="form_body">
        <div class="form_id line" v-if="formType!==0">
          <label id="orderid_label" v-html="'订单号码：'"></label>
<!--          <input id="orderid" type="number" min="1" v-model="form.orderid"/>-->
          <div id="orderid_disable">{{form.orderid}}</div>
        </div>
        <div class="form_material line">
          <label id="material_label" for="material" v-html="'物料号码：'"></label>
          <input id="material" type="number" v-model="form.material_code"/>
        </div>
        <div class="form_num line">
          <label id="num_label" for="num" v-html="'物料数量：'"></label>
          <input id="num" type="number" min="1" v-model="form.quantity"/>
        </div>
        <div class="form_date line">
          <label id="date_label" for="date" v-html="'交付日期：'"></label>
          <input id="date" type="date" v-model="form.delivery_date" max="2035-12-31">
        </div>
        <div class="form_button line">
          <button class="button confirm" @click="submitForm" v-if="formType===0">添加</button>
          <button class="button confirm" @click="submitForm" v-if="formType===1">更新</button>
          <button class="button" @click="closeForm">取消</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    import MessageTip from "../../components/MessageTip";
    import {addOrder, deleteOrder, getAllOrder, updateOrder} from "../../api/orderManageApi";
    export default {
        name: "OrderManage",
        components: {MessageTip},
        data(){
          return {
              isPC: true,
              allOrder: [],
              nativeOrder: [],
              showForm: false,
              formType: 0,
              messageState: false,
              messageType: 0,
              message: '',
              sortMode: 0,
              filterString: '',
              filterStart: '',
              filterEnd: '',
              form: {
                  orderid: '',
                  material_code: '',
                  quantity: '',
                  delivery_date: ''
              }
          }
        },
        mounted(){
            if(sessionStorage.getItem('equipment')==='Mobile'){
                this.isPC = false;
            }
            this.getAllOrder();
        },
        methods: {
            dateTransfer: function (date) {
                if(date){
                    return date.split('T')[0];
                }else{
                    return '无';
                }
            },
            openForm: function(){
                this.showForm = true;
            },
            closeForm: function () {
                this.form = {
                    orderid: '',
                    material_code: '',
                    quantity: '',
                    delivery_date: ''
                };
                this.showForm = false;
            },
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
            changeSortMode: function(newMode){
                if(this.sortMode === newMode){
                    this.sortMode = 0;
                    this.allOrder = JSON.parse(JSON.stringify(this.nativeOrder));
                }else {
                    switch (newMode) {
                        case 1:
                            this.allOrder.sort(function (a, b) {
                                if (a.orderid > b.orderid){
                                    return 1;
                                } else if(a.orderid < b.orderid){
                                    return -1;
                                }else{
                                    return 0;
                                }
                            });
                            break;
                        case 2:
                            this.allOrder.sort(function (a, b) {
                                if (a.orderid < b.orderid){
                                    return 1;
                                } else if(a.orderid > b.orderid){
                                    return -1;
                                }else{
                                    return 0;
                                }
                            });
                            break;
                        case 3:
                            this.allOrder.sort(function (a, b) {
                                if (a.material_code > b.material_code){
                                    return 1;
                                } else if(a.material_code < b.material_code){
                                    return -1;
                                }else{
                                    if (a.orderid > b.orderid){
                                        return 1;
                                    } else if(a.orderid < b.orderid){
                                        return -1;
                                    }else{
                                        return 0;
                                    }
                                }
                            });
                            break;
                        case 4:
                            this.allOrder.sort(function (a, b) {
                                if (a.material_code < b.material_code){
                                    return 1;
                                } else if(a.material_code > b.material_code){
                                    return -1;
                                }else{
                                    if (a.orderid < b.orderid){
                                        return 1;
                                    } else if(a.orderid > b.orderid){
                                        return -1;
                                    }else{
                                        return 0;
                                    }
                                }
                            });
                            break;
                        case 5:
                            this.allOrder.sort(function (a, b) {
                                if (a.quantity > b.quantity){
                                    return 1;
                                } else if(a.quantity < b.quantity){
                                    return -1;
                                }else{
                                    if (a.orderid > b.orderid){
                                        return 1;
                                    } else if(a.orderid < b.orderid){
                                        return -1;
                                    }else{
                                        return 0;
                                    }
                                }
                            });
                            break;
                        case 6:
                            this.allOrder.sort(function (a, b) {
                                if (a.quantity < b.quantity){
                                    return 1;
                                } else if(a.quantity > b.quantity){
                                    return -1;
                                }else{
                                    if (a.orderid < b.orderid){
                                        return 1;
                                    } else if(a.orderid > b.orderid){
                                        return -1;
                                    }else{
                                        return 0;
                                    }
                                }
                            });
                            break;
                        case 7:
                            this.allOrder.sort(function (a, b) {
                                if (a.delivery_date > b.delivery_date){
                                    return 1;
                                } else if(a.delivery_date < b.delivery_date){
                                    return -1;
                                }else{
                                    if (a.orderid > b.orderid){
                                        return 1;
                                    } else if(a.orderid < b.orderid){
                                        return -1;
                                    }else{
                                        return 0;
                                    }
                                }
                            });
                            break;
                        case 8:
                            this.allOrder.sort(function (a, b) {
                                if (a.delivery_date < b.delivery_date){
                                    return 1;
                                } else if(a.delivery_date > b.delivery_date){
                                    return -1;
                                }else{
                                    if (a.orderid < b.orderid){
                                        return 1;
                                    } else if(a.orderid > b.orderid){
                                        return -1;
                                    }else{
                                        return 0;
                                    }
                                }
                            });
                            break;
                        default:
                            this.allOrder = JSON.parse(JSON.stringify(this.nativeOrder));
                    }
                    this.sortMode = newMode;
                }
            },
            doFilter: function() {
                this.allOrder = JSON.parse(JSON.stringify(this.nativeOrder));
                let orders1 = [];
                let that = this;
                if(this.filterString !== '' && this.allOrder.length>0){
                    let str = this.filterString;
                    this.allOrder.forEach(function (order) {
                        if(order.orderid.toString().indexOf(str)!==-1||order.material_code.toString().indexOf(str)!==-1||order.quantity.toString().indexOf(str)!==-1){
                            orders1.push(JSON.parse(JSON.stringify(order)));
                        }
                    });
                }else{
                    orders1 = JSON.parse(JSON.stringify(this.allOrder));
                }
                let orders2 = [];
                if(this.filterStart && orders1.length>0){
                    orders1.forEach(function (order) {
                        if(that.dateTransfer(order.delivery_date) >= that.filterStart){
                            orders2.push(JSON.parse(JSON.stringify(order)));
                        }
                    });
                }else{
                    orders2 = JSON.parse(JSON.stringify(orders1));
                }
                let orders3 = [];
                if(this.filterEnd && orders2.length>0){
                    orders2.forEach(function (order) {
                        if(that.dateTransfer(order.delivery_date) <= that.filterEnd){
                            orders3.push(JSON.parse(JSON.stringify(order)));
                        }
                    });
                }else{
                    orders3 = JSON.parse(JSON.stringify(orders2));
                }
                this.allOrder = orders3;
            },
            clearFilter: function() {
                this.allOrder = JSON.parse(JSON.stringify(this.nativeOrder));
                this.filterString = '';
                this.filterStart = '';
                this.filterEnd = '';
            },
            getAllOrder:function () {
                getAllOrder().then(res=>{
                    if(res.flag){
                        this.allOrder = res.data;
                        this.nativeOrder = JSON.parse(JSON.stringify(res.data));
                    }else{
                        this.showMessage(1, res.message);
                    }
                }).catch(error=>{
                    this.showMessage(1, '系统错误');
                })
            },
            addOrder: function(){
                this.formType = 0;
                this.form = {
                    orderid: '',
                    material_code: '',
                    quantity: '',
                    delivery_date: ''
                };
                this.openForm();
            },
            updateOrder: function(order){
                this.formType = 1;
                this.form.orderid = order.orderid;
                this.form.material_code = order.material_code;
                this.form.quantity = order.quantity;
                this.form.delivery_date = order.delivery_date.split('T')[0];
                this.openForm();
            },
            deleteOrder: function (orderid) {
                deleteOrder(orderid).then(res=>{
                    if(res.flag){
                        this.showMessage(0, '删除成功！');
                        this.getAllOrder();
                    }else{
                        this.showMessage(1, res.message);
                    }
                })
            },
            submitForm: function () {
                if(this.form.material_code===''){
                    this.showMessage(1, '请输入物料号码！');
                }else if(this.form.quantity===''||Number(this.form.quantity) < 1){
                    this.showMessage(1, '物料数量不能小于1！');
                }else if(this.form.delivery_date===''){
                    this.showMessage(1, '交付日期不能为空！');
                }else{
                    this.form.orderid = Number(this.form.orderid);
                    this.form.material_code = Number(this.form.material_code);
                    this.form.quantity = Number(this.form.quantity);
                    this.form.delivery_date = this.form.delivery_date + 'T01:00:00.000+0000';
                    if(this.formType===0){
                        addOrder(this.form).then(res=>{
                            if(res.flag){
                                this.showMessage(0, '添加成功！');
                                this.closeForm();
                                this.getAllOrder();
                            }else{
                                this.showMessage(1, res.message);
                            }
                        })
                    }else{
                        updateOrder(this.form).then(res=>{
                            if(res.flag){
                                this.showMessage(0, '更新成功！');
                                this.closeForm();
                                this.getAllOrder();
                            }else{
                                this.showMessage(1, res.message);
                            }
                        })
                    }
                }
            },
        }
    }
</script>

<style scoped lang="less">
  .main {
    .head {
      margin: 30px 5% 0;
      text-align: left;

      .text {
        font-size: 32px;
        font-weight: bold;
      }
    }
    .container{
      margin: 10px 7%;
      display: flex;
      .filter{
        text-align: left;
        width: 22%;
        margin: 20px 0 40px 3%;
        border: 2px solid #f2f2f2;
        box-shadow: 3px 3px 4px #f2f2f2;
        padding: 40px 2%;
        .head-text{
          font-size: 24px;
          font-weight: bold;
        }
        .content{
          margin: 20px 0;
          #string{
            width: 88%;
            font-size: 14px;
            min-height: 20px;
            margin-top: 8px;
            padding: 2px 0 2px 6px;
          }
        }
        .time{
          margin: 20px 0;
          #start{
            width: 42%;
            font-size: 12px;
            margin-top: 8px;
            padding: 2px 0 2px 2px;
          }
          #endLabel{
            margin: 0 2px;
          }
          #end{
            width: 42%;
            font-size: 12px;
            padding: 2px 0 2px 2px;
          }
          .button{
            background-color: #efefef;
            color: #666666;
            margin-top: 20px;
            border-radius: 4px;
            border: 1px solid #efefef;
            padding: 2px 14px 6px;
            font-size: 16px;
            cursor: pointer;
          }
          .button:focus{
            outline: none;
          }
          .button:hover{
            background-color: #f6f6f6;
          }
        }
      }
      .table{
        width: 70%;
        .row{
          display: flex;
          border-bottom: 1px solid #e0e0e0;
          padding: 12px 0;
          .column{
            padding: 2px 4px;
            display: flex;
            align-items: center;
            justify-content: center;
          }
          .index0{
            width: 6%;
          }
          .index1{
            width: 18%;
          }
          .index2{
            width: 18%;
          }
          .index3{
            width: 18%;
          }
          .index4{
            width: 18%;
          }
          .index5{
            width: 17%;
            .tooltip{
              font-size: 22px;
              cursor: pointer;
            }
            .tooltip:first-child{
              margin-right: 8px;
            }
          }
          .index6{
            width: 5%;
            .tooltip{
              font-size: 22px;
              cursor: pointer;
            }
          }
          .sort{
            margin-top: -6px;
            margin-left: 4px;
            position: relative;
            .up{
              position: absolute;
              top: -6px;
              left: 0;
              z-index: 100;
              line-height: 10px;
              color: #aaaaaa;
              cursor: pointer;
              .icon-paixu-shang{
                font-size: 12px;
              }
            }
            .down{
              position: absolute;
              top: 4px;
              left: 0;
              line-height: 10px;
              color: #aaaaaa;
              cursor: pointer;
              .icon-paixu-xia{
                font-size: 12px;
              }
            }
            .active{
              color: #000000;
            }
          }
        }
        .row:first-child{
          border-top: 1px solid #e0e0e0;
          background-color: #f2f2f2;
          font-weight: bold;
        }
        .row:hover{
          background-color: #f0f6f9;
        }
        .noOrder{
          margin-top: 10px;
          font-size: 20px;
        }
      }
    }
    .background{
      background-color: #000000;
      opacity: 0.8;
      position: fixed;
      z-index: 200;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
    }
    .form{
      background-color: #FFFFFF;
      position: fixed;
      z-index: 300;
      top: 20%;
      left: 35%;
      width: 30%;
      min-width: 570px;
      height: 35%;
      min-height: 328px;
      padding: 32px 10px 10px;
      text-align: left;
      .form_head{
        margin-top: 10px;
        font-size: 32px;
        font-weight: bold;
        position: relative;
        .text{
          line-height: 6px;
          padding-left: 10px;
        }
        .close{
          position: absolute;
          top: -44px;
          right: 10px;
          cursor: pointer;
        }
      }
      .form_body{
        padding: 0 88px;
        .line{
          margin-bottom: 20px;
        }
        .line:first-child{
          margin-top: 40px;
        }
        .form_id{
          display: flex;
          #orderid_label{
            font-size: 20px;
            font-weight: bold;
          }
          #orderid{
            font-size: 16px;
            width: 260px;
            min-height: 20px;
            padding-left: 6px;
          }
          #orderid_disable{
            font-size: 20px;
            font-weight: bold;
            padding-left: 6px;
          }
        }
        .form_material{
          #material_label{
            font-size: 20px;
            font-weight: bold;
          }
          #material{
            font-size: 16px;
            width: 260px;
            min-height: 20px;
            padding-left: 6px;
          }
        }
        .form_num{
          #num_label{
            font-size: 20px;
            font-weight: bold;
          }
          #num{
            font-size: 16px;
            width: 260px;
            min-height: 20px;
            padding-left: 6px;
          }
        }
        .form_date{
          display: flex;
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
        .form_button{
          .button{
            background-color: #E6E6E6;
            color: #666666;
            margin-right: 10px;
            border-radius: 4px;
            border: 1px solid #e6e6e6;
            padding: 4px 14px 6px;
            font-size: 16px;
            cursor: pointer;
          }
          .button:focus{
            outline: none;
          }
          .button:hover{
            background-color: #F6F6F6;
          }
          .confirm{
            background-color: #1B9AF7;
            border-color: #008dcb;
            color: #FFFFFF;
          }
          .confirm:hover{
            background-color: #4cb0f9;
          }
        }
      }
    }
  }
  .mobile {
    .head {
      margin: 12px 5% 0;
      text-align: left;
      .wrap{
        display: flex;
        justify-content: space-between;
        align-items: center;
        .text {
          font-size: 16px;
          font-weight: bold;
        }
      }
    }
    .container{
      margin: 10px 5%;
      display: flex;
      .filter{
        text-align: left;
        width: 22%;
        margin: 20px 0 40px 3%;
        border: 2px solid #f2f2f2;
        box-shadow: 3px 3px 4px #f2f2f2;
        padding: 40px 2%;
        .head-text{
          font-size: 24px;
          font-weight: bold;
        }
        .content{
          margin: 20px 0;
          #string{
            width: 88%;
            font-size: 14px;
            min-height: 20px;
            margin-top: 8px;
            padding: 2px 0 2px 6px;
          }
        }
        .time{
          margin: 20px 0;
          #start{
            width: 42%;
            font-size: 12px;
            margin-top: 8px;
            padding: 2px 0 2px 2px;
          }
          #endLabel{
            margin: 0 2px;
          }
          #end{
            width: 42%;
            font-size: 12px;
            padding: 2px 0 2px 2px;
          }
          .button{
            background-color: #efefef;
            color: #666666;
            margin-top: 20px;
            border-radius: 4px;
            border: 1px solid #efefef;
            padding: 2px 14px 6px;
            font-size: 16px;
            cursor: pointer;
          }
          .button:focus{
            outline: none;
          }
          .button:hover{
            background-color: #f6f6f6;
          }
        }
      }
      .table{
        width: 100%;
        .row{
          display: flex;
          border-bottom: 1px solid #e0e0e0;
          padding: 2px 0;
          .column{
            font-size: 10px;
            padding: 2px 4px;
            display: flex;
            align-items: center;
            justify-content: center;
          }
          .index0{
            width: 8%;
          }
          .index1{
            width: 19%;
          }
          .index2{
            width: 19%;
          }
          .index3{
            width: 19%;
          }
          .index4{
            width: 19%;
          }
          .index5{
            width: 16%;
            .tooltip{
              font-size: 16px;
              cursor: pointer;
            }
            .tooltip:first-child{
              margin-right: 2px;
            }
          }
          .index6{
            width: 5%;
            .tooltip{
              font-size: 22px;
              cursor: pointer;
            }
          }
          .sort{
            margin-top: -6px;
            margin-left: 4px;
            position: relative;
            .up{
              position: absolute;
              top: -5px;
              left: -3px;
              z-index: 100;
              line-height: 10px;
              color: #aaaaaa;
              cursor: pointer;
              .icon-paixu-shang{
                font-size: 10px;
              }
            }
            .down{
              position: absolute;
              top: 2px;
              left: -3px;
              line-height: 10px;
              color: #aaaaaa;
              cursor: pointer;
              .icon-paixu-xia{
                font-size: 10px;
              }
            }
            .active{
              color: #000000;
            }
          }
        }
        .row:first-child{
          border-top: 1px solid #e0e0e0;
          background-color: #f2f2f2;
          font-weight: bold;
        }
        .row:hover{
          background-color: #f0f6f9;
        }
        .noOrder{
          margin-top: 10px;
          font-size: 20px;
        }
      }
    }
    .background{
      background-color: #000000;
      opacity: 0.8;
      position: fixed;
      z-index: 200;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
    }
    .form{
      background-color: #FFFFFF;
      position: fixed;
      z-index: 300;
      top: 20vh;
      left: 15vw;
      width: 70vw;
      /*height: 23vh;*/
      padding: 6px 8px 8px;
      text-align: left;
      align-items: center;
      .form_head{
        margin-top: 2px;
        font-size: 16px;
        font-weight: bold;
        position: relative;
        .text{
          padding-left: 4px;
        }
        .close{
          position: absolute;
          top: -1px;
          right: 2px;
          cursor: pointer;
        }
      }
      .form_body{
        /*padding: 0 88px;*/
        .line{
          margin: 0 0 10px 12vw;
          align-items: center;
        }
        .form_id{
          display: flex;
          #orderid_label{
            font-size: 14px;
            font-weight: bold;
          }
          #orderid{
            font-size: 12px;
            width: 30vw;
            padding-left: 6px;
            min-height: 20px;
            border: 1px solid #aaaaaa;
          }
          #orderid_disable{
            font-size: 14px;
            font-weight: bold;
            padding-left: 6px;
          }
        }
        .form_material{
          #material_label{
            font-size: 14px;
            font-weight: bold;
          }
          #material{
            font-size: 12px;
            width: 30vw;
            min-height: 16px;
            padding-left: 6px;
            border: 1px solid #aaaaaa;
          }
        }
        .form_num{
          #num_label{
            font-size: 14px;
            font-weight: bold;
          }
          #num{
            font-size: 12px;
            width: 30vw;
            min-height: 16px;
            padding-left: 6px;
            border: 1px solid #aaaaaa;
          }
        }
        .form_date{
          display: flex;
          #date_label{
            font-size: 14px;
            font-weight: bold;
          }
          #date{
            font-size: 12px;
            cursor: pointer;
            width: 30vw;
            padding: 2px 1px 1px 6px;
            background-color: #ffffff;
            border: 1px solid #aaaaaa;
          }
        }
        .form_button{
          .button{
            background-color: #E6E6E6;
            color: #666666;
            margin-right: 10px;
            border-radius: 2px;
            border: 1px solid #e6e6e6;
            padding: 2px 8px 2px;
            font-size: 14px;
            cursor: pointer;
          }
          .button:focus{
            outline: none;
          }
          .button:hover{
            background-color: #F6F6F6;
          }
          .confirm{
            background-color: #1B9AF7;
            border-color: #008dcb;
            color: #FFFFFF;
          }
          .confirm:hover{
            background-color: #4cb0f9;
          }
        }
      }
    }
  }
</style>