<template>
<div v-show="this.$store.state.ismenu">
<div class="head">
  <img src="https://www.kugou.com/common/images/kugou_white.png" width="140" height="40" style="padding-top:20px;padding-left:300px;">
  <el-input @change="change" v-model="search" size="small" placeholder="搜索音乐" type="text" style="width:300px;padding-left:40px;position:absolute;padding-top:25px;">
        <template #prepend>
          <el-icon style="color:#4EC2FFFF"><search/></el-icon>
        </template>
    </el-input>
  <el-avatar class="a1" @click="user()" :src="this.$store.state.userpic"  :size="50" style="margin-left:380px;position: absolute;margin-top:15px;"/>
</div>
</div>
<keep-alive>
<router-view ></router-view>
</keep-alive>
<div class="head1" v-show="this.$store.state.ismenu">
<el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    text-color=#000000
    @select="handleSelect"
    active-text-color=#02AAFFFF
  >
    <el-menu-item index="1" style="margin-left:300px;text-decoration: none;font-size:18px"><router-link style="text-decoration: none;" to="/home"><button class="item1" >首页</button></router-link></el-menu-item>
    <el-menu-item index="2" style="margin-left:50px;font-size:18px"><router-link style="text-decoration: none;"  to="/song_list_qiantai"><button class="item1" >歌单</button></router-link></el-menu-item>
    <el-menu-item  index="3" style="margin-left:50px;font-size:18px"><router-link style="text-decoration: none;"  to="/singer_qiantai"><button class="item1" >歌手</button></router-link></el-menu-item>
    <el-menu-item  index="4" style="margin-left:50px;font-size:18px"><router-link style="text-decoration: none;" to="/bangdan"><button class="item1" >榜单</button></router-link></el-menu-item>
    <el-menu-item  index="5" style="margin-left:50px;font-size:18px"><router-link style="text-decoration: none;" to="/mvclassify"><button class="item1" >MV</button></router-link></el-menu-item>
    <el-menu-item  index="6" style="margin-left:50px;font-size:18px" @click="mysong()"><button class="item1" >我的音乐</button></el-menu-item>
    
  </el-menu>
</div>
<div class="play" v-show="this.$store.state.ismenu">
   <playbar></playbar>
</div>
</template>
<script>
import playbar from '../src/qiantai/playbar.vue'
import {Search} from '@element-plus/icons'
import { ElNotification } from 'element-plus'
export default{
  components:{
    playbar,
    Search,
  },
  data(){
    return{
      search:'',
    }
  },
  created: function () {
        if(this.$store.state.userid !== null){
          this.$router.push({path: '/home'})
        }
        this.$store.commit("getSong_url",'')
        this.$store.commit("getSong_name",'')
        this.$store.commit("getSong_pic",'未播放.jpg')
        this.$store.commit("setIsmenu",true)
        this.$store.commit("getUserId",null)
        this.$store.commit("getSongid",null)
        this.$store.commit("getUserpic",'22.jpg')
    },
  methods:{
    change(){
      this.$store.commit('getsearchvalue',this.search)
      this.$router.push({path: '/searchhome'})
      this.search=''
    },
    mysong(){
      if(this.$store.state.userid === null){
        ElNotification({
          title: 'Error',
          message: '请先登录',
          type: 'error',
        })
        this.$router.push({path: '/userlogin'})
      }
      else{
        this.$store.commit("getUserid1",this.$store.state.userid)
        this.$router.push({path: '/mysong'})
      }
      // this.$router.push({path: '/mysong'})
    },
    user(){
      if(this.$store.state.userid !== null){
        this.$router.push({path: '/userupdate'})
      }
      else{
        this.$router.push({path: '/userlogin'})
      }
      }
    },
  
}
</script>
<style scoped>
.item1{
    width: 60px;
    height: 30px;
    border: none;
    background: none;
    border-radius: 20px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;

}
.item1:hover{
    /* color:#02AAFFFF; */
    cursor:pointer;
    /* transform: scale(1.4); */
    background-image: linear-gradient(to right, rgb(11, 200, 253) , rgb(0, 149, 255));
    transform:scaleX(1.7);
    border-radius: 20px;
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    color: rgb(254, 254, 254);
}
.item1:focus{
    background-image: linear-gradient(to right, rgb(11, 200, 253) , rgb(0, 149, 255));
    color: rgb(255, 255, 255);
}
.head{
  background-color: #02AAFFFF;
  width: 100%;
  height: 80px;
  /* display: inline-block; */
}
.a1{
  border-radius: 50%;
}
.a1:hover{
  cursor:pointer;
}
.el-menu-demo{
  font-weight: bold;
  width:100%;
  text-decoration: none;
  /* overflow: hidden; */
}
.head1{
  top:87px;
  position:absolute;
  width: 1500px;
  opacity:0.7;
  overflow: hidden;
}

</style>
