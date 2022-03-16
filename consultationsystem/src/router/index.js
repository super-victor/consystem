import Vue from 'vue'
import VueRouter from 'vue-router'
import { Store } from 'vuex'
import Home from '../views/Home.vue'
import Login from '../components/Login/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    // redirect:"/test",
    // component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue')
    component: () => import(/* webpackChunkName: "about" */ '../components/Login/Login.vue')
  },
  {
    path:"/home",
    name :'home',
    // component: () => import('../components/Login/Logon.vue')
    component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue'),
    children:[
      {
        path:'/main',
        component:()=> import('../components/basic/Main.vue'),
        children:[
        {
          path:'shouye',
          component:()=> import('../views/shouye.vue')},
        {
          path:'usermanage',
          component:()=>import('../views/usermanage.vue')
        },
        {
          path:'journalmanage',
          component:()=>import('../views/journalmanage.vue')
        },{
          path:'log',
          component:()=>import('../views/log.vue')
        },{
          path:'mymeeting',
          component:()=>import('../views/mymeeting.vue')
        },{
          path:'message',
          component:()=>import('../views/message.vue')
        }
        ]
      }
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path:'/aside',
    component:() => import('../components/basic/TheAside.vue')
  },
  {
    path:'/shouye',
    component:()=> import('../views/shouye.vue')
  },
  {
    path:'/main',
    component:()=> import('../components/basic/Main.vue'),
    children:[
      {path:'shouye',
      component:()=> import('../views/shouye.vue')}
    ]
  },
  {
    path:'/test',
    component:()=>import('../views/test.vue')
  }
]

const router = new VueRouter({
  routes,
  mode:'history'
})


export default router
