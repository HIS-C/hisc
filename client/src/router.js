import Vue from 'vue'
import Router from 'vue-router'
import DashBoard from './views/DashBoard.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: '대쉬보드',
      component: DashBoard
    },
    {
      path: '/monitor',
      name: '감염추적관리',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/Monitor.vue')
    },
    {
      path: '/ward',
      name: '병동정보',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/Ward.vue')
    },
    {
      path: '/table',
      name: 'Table',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './components/BasicTable.vue')
    }
  ]
})
