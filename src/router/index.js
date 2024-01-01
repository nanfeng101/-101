import { createRouter, createWebHistory } from 'vue-router'
import Home from '../view/home.vue'
import Homepage from '../houtai/homepage.vue'
import Textdetail from '../qiantai/textdetail.vue'
import Useraddtext from '../qiantai/useraddtext.vue'
import Useredittext from '../qiantai/useredittext.vue'
import Myhome from '../qiantai/myself/myhome.vue'
import Userdetail from '../qiantai/myself/user/userdetail.vue'
import Userhome from '../qiantai/userhome.vue'
import Userchat from '../qiantai/myself/user/userchat.vue'
import Search from '../qiantai/search.vue'
import Liuyan from '../qiantai/liuyan.vue'
import Photo from '../qiantai/photo.vue'
import Luntan from '../qiantai/luntan.vue'
const routes = [
  {
    path: '/',
    redirect:'/home',
    meta:{auth:true}//登录不需要验证权限
  },
  {
    path: '/home',
    component: Home,
    meta:{auth:true}
  },
  {
    name: Homepage,
    path: '/homepage',
    component: Homepage,
    meta:{title:'后台'}
  },
  {
    name: Textdetail,
    path: '/textdetail',
    component: Textdetail,
    meta:{title:'文章'}
  },
  {
    name: Useraddtext,
    path: '/useraddtext',
    component: Useraddtext,
    meta:{title:'文章'}
  },
  {
    name: Useredittext,
    path: '/useredittext',
    component: Useredittext,
    meta:{title:'文章'}
  },
  {
    name: Myhome,
    path: '/myhome',
    component: Myhome,
    meta:{title:'文章'}
  },
  {
    name: Userdetail,
    path: '/userdetail',
    component: Userdetail,
    meta:{title:'文章'}
  },
  {
    name: Userhome,
    path: '/userhome',
    component: Userhome,
    meta:{title:'文章'}
  },
  {
    name: Userchat,
    path: '/userchat',
    component: Userchat,
    meta:{title:'文章'}
  },
  {
    name: Search,
    path: '/search',
    component: Search,
    meta:{title:'文章'}
  },
  {
    name: Liuyan,
    path: '/liuyan',
    component: Liuyan,
    meta:{title:'文章'}
  },
  {
    name: Photo,
    path: '/photo',
    component: Photo,
    meta:{title:'文章'}
  },
  {
    name: Luntan,
    path: '/luntan',
    component: Luntan,
    meta:{title:'文章'}
  },
  ]
  const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
  })
  
export default router