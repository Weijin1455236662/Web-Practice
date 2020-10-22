import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import WorkSchedule from "../views/Schedule/WorkSchedule";
import Error from "../views/Error";
import Manage from "../views/Manage/Manage";
import StaffManage from "../views/Manage/StaffManage";
import MachineManage from "../views/Manage/MachineManage";
import OrderManage from "../views/Manage/OrderManage";
import Load from "../views/Load";
import OrderGantt from "../views/OrderGantt";
import Schedule from "../views/Schedule/Schedule";
import SourceSchedule from "../views/Schedule/SourceSchedule";
import OrderSchedule from "../views/Schedule/OrderSchedule";
import OrderWorkSchedule from "../views/Schedule/OrderWorkSchedule";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '主页',
    component: Home
  },
  {
    path: '/manage',
    name: '管理',
    component: Manage,
    children: [
      {
        path: 'staff',
        name: '人员管理',
        component: StaffManage
      },
      {
        path: 'machine',
        name: '设备管理',
        component: MachineManage
      },
      {
        path: 'order',
        name: '订单管理',
        component: OrderManage
      }
    ]
  },
  {
    path: '/load',
    name: '资源负载图',
    component: Load
  },
  {
    path: '/order',
    name: '订单甘特图',
    component: OrderGantt
  },
  {
    path: '/schedule',
    name: '安排',
    component: Schedule,
    children: [
      {
        path: 'work',
        name: '生产单',
        component: WorkSchedule
      },
      {
        path: 'source',
        name: '资源甘特图',
        component: SourceSchedule
      },
      {
        path: 'order',
        name: '订单计划图',
        component: OrderSchedule
      },
      {
        path: 'orderWork',
        name: '订单-生产单关系表',
        component: OrderWorkSchedule
      }
    ]
  },
  {
    path: '*',
    name: '404',
    component: Error
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
