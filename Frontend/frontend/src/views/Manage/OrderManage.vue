<template>
  <div class="main">
    <message-tip :message-state="messageState" :message-type="messageType" :message="message"></message-tip>
    <div class="head">
      <div class="text">订单管理</div>
      <hr/>
    </div>
    <div class="container">
      <div class="table">
        <div class="row">
          <div class="table-head column index1">订单号码</div>
          <div class="table-head column index2">物料号码</div>
          <div class="table-head column index3">物料数量</div>
          <div class="table-head column index4">交付日期</div>
          <div class="table-head column index5">操作</div>
          <div class="table-head column index6"><span class="icon-font tooltip" @click="addOrder">&#xe674;</span></div>
        </div>
        <div class="row" v-for="(order, index) in allOrder" :key="index">
          <div class="table-data column index1">{{order.orderid}}</div>
          <div class="table-data column index2">{{order.material_code}}</div>
          <div class="table-data column index3">{{order.quantity}}</div>
          <div class="table-data column index4">{{dateTransfer(order.delivery_date)}}</div>
          <div class="table-data column index5">
            <i class="icon-font i-edit tooltip" title="修改订单" @click="updateOrder(order)"></i>
            <i class="icon-font i-delete tooltip" title="删除订单" @click="deleteOrder(order.orderid)"></i>
          </div>
          <div class="table-data column index6"></div>
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
        <div class="form_id">
          <label id="orderid_label" for="orderid" v-html="'订单号码：'"></label>
          <input id="orderid" type="number" min="1" v-model="form.orderid"/>
        </div>
        <div class="form_material">
          <label id="material_label" for="material" v-html="'物料号码：'"></label>
          <input id="material" type="number" v-model="form.material_code"/>
        </div>
        <div class="form_num">
          <label id="num_label" for="num" v-html="'物料数量：'"></label>
          <input id="num" type="number" min="1" v-model="form.quantity"/>
        </div>
        <div class="form_date">
          <label id="date_label" for="date" v-html="'交付日期：'"></label>
          <input id="date" type="date" v-model="form.delivery_date" max="2035-12-31">
        </div>
        <div class="form_button">
          <button class="button" @click="closeForm">取消</button>
          <button class="button confirm" @click="submitForm" v-if="formType===0">添加</button>
          <button class="button confirm" @click="submitForm" v-if="formType===1">更新</button>
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
              allOrder: [],
              showForm: false,
              formType: 0,
              messageState: false,
              messageType: 0,
              message: '',
              form: {
                  orderid: '',
                  material_code: '',
                  quantity: '',
                  delivery_date: ''
              }
          }
        },
        mounted(){
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
            getAllOrder:function () {
                getAllOrder().then(res=>{
                    if(res.flag){
                        this.allOrder = res.data;
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
      margin: 10px 15%;

      .table{
        .row{
          display: flex;
          border-bottom: 1px solid #e0e0e0;
          padding: 12px 0;
          .table-head{
            font-weight: bold;
          }
          .column{
            padding: 2px 4px;
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
            width: 19%;
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
        }
        .row:first-child{
          border-top: 1px solid #e0e0e0;
          background-color: #f2f2f2;
        }
        .row:hover{
          background-color: #e2eff9;
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
      height: 40%;
      min-height: 370px;
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
        .form_id{
          margin-top: 40px;
          margin-bottom: 20px;
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
        }
        .form_material{
          margin-bottom: 20px;
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
          margin-bottom: 20px;
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
          margin-bottom: 20px;
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
</style>