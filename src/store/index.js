import Vuex from 'vuex'
import VuexAlong from 'vuex-along'
const store = new Vuex.Store({
    state: {
      address:'http://localhost:8011/zf/',
      currentCom:'edittext',//用于后台挑战
      biaoti:'',
      biaoqian:[{}],//用于存储所有文章的标签
      text:{},//用于存储文章信息
      usertext:[{}],//用于存储用户的所有文章信息
      hottext:[{}],//用于存储热门文章
      textsum:0,//用于存储文章总数
      viewsum:0,//用于存储总浏览量
      alllink:[{}],
      userid1:0,//用于存储用户登录后的id
      userdetail:{},//用于存储用户登录后的信息
      islogin:false,//判断用户是否登录
      isviewbot:true,//是否显示最底部
      userhome:{},//用于存储其他人的信息
      isview:false,//用于控制是否显示head
      searchtext:[{}], // 用于存储搜索到的文章信息
      searchuser:[{}],//用于存储搜索到的用户信息
      usercomment:[{}],//用于存储用户评论
      isread:0,//用于存储用户私信未读信息总数

    },
    mutations:{
      getIsread(state,val){
        state.isread=val
      },
      getUsercomment(state,val){
        state.usercomment=val
      },
      getsearchtext(state,val){
        state.searchtext=val
      },
      getsearchuser(state,val){
        state.searchuser=val
      },
      getIsview(state,val){
        state.isview=val
      },
      getUserhome(state,val){
        state.userhome=val
      },
      getUserdetail(state,val){
        state.userdetail=val
      },
      getIslogin(state,val){
        state.islogin=val
      },
      getUserid(state,val){
        state.userid1=val
      },
      getUsertext(state,val){
        state.usertext=val
      },
      getIsviewbot(state,val){
        state.isviewbot=val
      },
      getAlllink(state,val){
        state.alllink=val
      },
      getCurrentCom(state,val){
        state.currentCom=val;
      },
      getHottext(state,val){
        state.hottext=val
      },
      getTetxsum(state,val){
        state.textsum=val
      },
      getViewsum(state,val){
        state.viewsum=val
      },
      getBiaoti(state,val){
        state.biaoti=val
      },
      getBiaoqian(state,val){
        state.biaoqian=val
      },
      getText(state,val){
        state.text=val
      }
    },
    plugins:[VuexAlong({
        name:'along',
        local:false,
    })]
  })
  
export default store

