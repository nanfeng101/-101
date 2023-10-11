import { createRouter, createWebHistory } from 'vue-router'
import Login from '../view/Login.vue'
import HomePage from '../view/HomePage.vue'
import Home from '../qiantai/Home.vue'
import SingerDetail from '../qiantai/singerdetail.vue'
import Song_list_qiantai from '../qiantai/song_list_qiantai.vue'
import Singer_qiantai from '../qiantai/singer_qiantai.vue'
import Songdetail from '../qiantai/songdetail.vue'
import Lyric from '../qiantai/lyric.vue'
import Userupdate from '../qiantai/userupdate.vue'
import Userlogin from '../view/userlogin.vue'
import Mysong from '../qiantai/mysong.vue'
import Mv from '../qiantai/mv.vue'
import Bangdan from '../qiantai/bangdan.vue'
import Mvclassify from '../qiantai/mvclassify'
import Searchhome from '../search/searchhome.vue'
const routes = [
  {
    path: '/',
    redirect:'/Home',
    meta:{auth:true}//登录不需要验证权限
  },
  {
    path: '/Home',
    component: Home,
    meta:{auth:true}
  },
  //主页
  {
    name: Login,
    path: '/Login',
    component: Login,
    meta:{title:'主页'}
  },
  {
    name: HomePage,
    path: '/homePage',
    component: HomePage,
    meta:{title:'主页'}
  },
  {
    name: SingerDetail,
    path: '/singerdetail',
    component: SingerDetail,
    meta:{title:'歌单详情页面'}
  },
  {
    name: Song_list_qiantai,
    path: '/song_list_qiantai',
    component: Song_list_qiantai,
    meta:{title:'歌单详情页面'}
  },
  {
    name: Singer_qiantai,
    path: '/singer_qiantai',
    component: Singer_qiantai,
    meta:{title:'歌单详情页面'}
  },
  {
    name: Songdetail,
    path: '/songdetail',
    component: Songdetail,
    meta:{title:'歌手详情页面'}
  },
  {
    name: Lyric,
    path: '/lyric',
    component: Lyric,
    meta:{title:'歌手详情页面'}
  },
  {
    name: Userupdate,
    path: '/userupdate',
    component: Userupdate,
    meta:{title:'用户编辑页面'}
  },
  {
    name: Userlogin,
    path: '/userlogin',
    component: Userlogin,
    meta:{title:'用户登录页面'}
  },
  {
    name: Mysong,
    path: '/mysong',
    component: Mysong,
    meta:{title:'用户登录页面'}
  },
  {
    name: Mv,
    path: '/mv',
    component: Mv,
    meta:{title:'MV播放页面'}
  },
  {
    name: Bangdan,
    path: '/bangdan',
    component: Bangdan,
    meta:{title:'榜单页面'}
  },
  {
    name: Mvclassify,
    path: '/mvclassify',
    component: Mvclassify,
    meta:{title:'MV管理页面'}
  },
  {
    name: Searchhome,
    path: '/searchhome',
    component: Searchhome,
    meta:{title:'MV管理页面'}
  },
  ]
  const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
  })
  
export default router