<template>
<div class="all">
  <el-affix position="top" :offset="60">
<div class="tooltip" id="tool2" v-show="show3" @mouseleave="browse(2)">
  <el-container>
    <el-aside style="width:150px;">
      <el-menu 
        style="min-height:100%;"
        :default-active="activeIndex"
        class="el-menu-demo" 
        :collapse="isCollapse"
        @select="handleSelect"
        background-color=rgb(255,255,255)
        text-color=#000000>
        <el-menu-item index="0"><el-icon><Clock /></el-icon><span style="margin-left:10px;" @click="getUser_browse()">浏览记录</span></el-menu-item>
        <div v-for="item in collectdata" :key="item">
            <el-menu-item @click="com(item)" :index="item.id" ><el-icon><Folder /></el-icon><span style="margin-left:10px;">{{item.collect_name}}</span></el-menu-item>
        </div>
    </el-menu>
    </el-aside>
    <el-main class="m1">
      <span class="p1" @click="userdetail()">查看更多</span>
      <el-icon class="p1" style="margin-left:10px;"><More /></el-icon>
      <el-divider></el-divider>
      <div v-show="show1" v-for="item in collectTextdata" :key="item">
          <div class="text">
              <div @click="entrytetx(item)">
                  <p>{{item.biaoti}}</p>
              </div>
          </div>
      </div>
      <div v-show="show2" v-for="item in user_browse" :key="item">
          <div class="text">
              <div @click="entrytetx(item)">
                  <p>{{item.biaoti}}</p>
              </div>
          </div>
      </div>
    </el-main>
  </el-container>
</div>
</el-affix>
<el-affix position="top" :offset="0">
  <div class="head1" v-show="!this.$store.state.islogin">
    <span style="margin-left:100px;"><router-link to="home" style="text-decoration: none;"><span>博客</span></router-link></span>
    <span @click="photo()">图片分享</span>
    <span>专栏</span>
    <span @click="liuyan()">留言</span>
    <span @click="luntan()">论坛</span>
    <input @keyup.enter="search()" v-model="value" class="input1" placeholder="请输入关键词">
    <button @click="search()" class="button-2"><el-icon><Search /></el-icon>搜索</button>
    <span @click="login()">登录/注册</span>
    <span>消息</span>
    <span>历史</span>
    <span @click="myself()">创作中心</span>
    <button @click="useradd()" class="button1">发布文章</button>
  </div>
  <div class="head1" v-show="this.$store.state.islogin">
    <span style="margin-left:100px;"><router-link to="home" style="text-decoration: none;"><span>博客</span></router-link></span>
    <span @click="photo()">图片分享</span>
    <span>专栏</span>
    <span @click="liuyan()">留言</span>
    <span @click="luntan()">论坛</span>
    <input @keyup.enter="search()" v-model="value" class="input1" placeholder="请输入关键词">
    <button @click="search()" class="button-2"><el-icon><Search /></el-icon>搜索</button>
    <el-dropdown>
      <el-avatar @click="userhome()" :src="geturl()" size="50"></el-avatar>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>
              <div style="align-items: center;display: flex;">
                <el-icon><User /></el-icon>
                <p @click="userdetail()">个人中心</p>
              </div>
          </el-dropdown-item>
          <el-dropdown-item>
            <div style="align-items: center;display: flex;">
                <el-icon><Collection /></el-icon>
                <p>内容管理</p>
              </div>
          </el-dropdown-item>
          <el-dropdown-item>
            <div style="align-items: center;display: flex;">
                <el-icon><SwitchButton /></el-icon>
                <p @click="tuichu()">退出</p>
              </div>
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
    <el-dropdown>
      <span>消息</span>
      <template #dropdown>
        <el-dropdown-menu style="width:80px;">
          <el-dropdown-item>评论</el-dropdown-item>
          <el-dropdown-item>收藏</el-dropdown-item>
          <el-dropdown-item @click="userchat()"><el-badge type="danger" v-if="this.$store.state.isread!=0" :value="this.$store.state.isread" :max="99" class="item">私信</el-badge></el-dropdown-item>
          <el-dropdown-item @click="userchat()" v-if="this.$store.state.isread==0">私信</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
    <span @mouseenter="browse(1)">浏览</span>
    <span @click="myself()">创作中心</span>
    <button @click="useradd()" class="button1">发布文章</button>
  </div>
</el-affix>
<div class="view">
<router-view ></router-view>
</div>
<el-backtop :bottom="100">
    <div
      style="
        height: 100%;
        width: 100%;
        background-color: var(--el-bg-color-overlay);
        box-shadow: var(--el-box-shadow-lighter);
        text-align: center;
        line-height: 40px;
        color: #1989fa;
      "
    >
      <el-icon><Top /></el-icon>
    </div>
  </el-backtop>
<div class="bottom" v-show="this.$store.state.isviewbot">
  <div style="margin-left:100px;padding-top:20px;">
  <p>站点声明:本站转载作品版权归原作者及来源网站所有，原创内容作品版权归作者所有，任何内容转载，商业用途均需要联系作者并注明来源</p>
  <P>相关侵权，举报，投诉建议等，请发E-mail:2694134538@qq.com</P>
  </div>
</div>
</div>
<el-dialog v-model="visible">
<login></login>
</el-dialog>
</template>
<script>
import { ElMessage } from 'element-plus'
import Login from "../src/view/userlogin.vue"
import {Search,Top,User,Collection,SwitchButton,Folder,More,Clock} from "@element-plus/icons"
export default{
  data(){
    return{
      visible:false,
      collectdata:[{}],
      collectTextdata:[{}],
      user_browse:[{}],
      show1:false,
      show2:true,
      browse_current:{},
      show3:false,
      value:'',
    }
  },
  components:{
    Top,
    Login,
    User,
    Collection,
    SwitchButton,
    Folder,
    More,
    Clock,   
    Search,                                                                                                                                                                                                                                                                                                                                                                                                 
  },
  created:function(){
    this.getCollectName()
    this.getUser_browse()
  },
  methods:{
    luntan(){
      this.$router.push({path: '/luntan'})
    },
    photo(){
      this.$router.push({path: '/photo'})
    },
    link(){
      this.$router.push({path: '/link'})
    },
    liuyan(){
      this.$router.push({path: '/liuyan'})
    },
    search(){
      this.value=''
      this.searchBiaotiText()
      this.searchUsername()
      this.$router.push({path: '/search'})
    },
    searchBiaotiText(){
        this.$axios
        .get("/searchBiaotiText?str="+this.value)
        .then(successResponse => {
            this.$store.commit("getsearchtext",successResponse.data)
        })
        .catch(failResponse => {
        this.$alert(failResponse.response.status)
        })
    },
    searchUsername(){
          this.$axios
        .get("/searchUsername?username="+this.value)
        .then(successResponse => {
            this.$store.commit("getsearchuser",successResponse.data)
        })
        .catch(failResponse => {
        this.$alert(failResponse.response.status)
        })
    },
    browse(val){
      if(val==1){
        this.show3=true
      }else{
        this.show3=false
      }
    },
    getUser_browse(){
        this.show1=false
        this.show2=true
        this.browse_current.id=this.$store.state.userid1
        this.browse_current.current=1
        this.browse_current.page=10
        this.$axios
        .post('/getUserBrowse',this.browse_current)
        .then(successResponse => {
            this.user_browse=successResponse.data.user_browse
        })
        .catch(failResponse => {
        this.$alert(failResponse.response.status)
        })
    },
    updateview(id,sum){
            this.$axios
            .get("/updateview?user_id="+this.$store.state.userid1+'&&text_id='+id+'&&view_sum='+sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        addUserBrowse(val){
            var user_browse={}
            user_browse.user_id=this.$store.state.userid1
            user_browse.text_id=val
            this.$axios
            .post("/addUserBrowse",user_browse)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        entrytetx(item){
            this.$axios
            .get('/selectIdText?id='+item.text_id)
            .then(successResponse => {
                // this.$store.commit("getBiaoqian",this.biaoqian1)
                let text=successResponse.data
                this.$store.commit("getText",text)
                this.addUserBrowse(text.id) 
                this.updateview(text.id,text.view_sum)
                this.$router.push({path: '/textdetail'})
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
    com(val){
        this.show1=true
        this.show2=false
        this.collect1=val
        this.getCollect_text(val.collect_name)
    },
    getCollect_text(val){
        this.$axios
        .get('/addCollect_text?id='+this.$store.state.userid1+'&&name='+val)
        .then(successResponse => {
            this.collectTextdata=successResponse.data
        })
        .catch(failResponse => {
        this.$alert(failResponse.response.status)
        })
    },
    userhome(){
        this.$axios
        .get("/getUserDetail?id="+this.$store.state.userid1)
        .then(successResponse => {
            this.$store.commit("getUserhome",successResponse.data)
            this.$router.push({path: '/userhome'})
        })
        .catch(failResponse => {
        this.$alert(failResponse.response.status)
        })
    },
    userdetail(){
      this.$router.push({path: '/userdetail'})
    },
    userchat(){
      this.$router.push({path: '/userchat'})
    },
    geturl(){
      if(this.$store.state.userdetail.pic!=''){
        let url = 'http://124.220.16.147:8011/zf/'+this.$store.state.userdetail.pic
        return url
      }

    },
    login(){
      this.visible=true
    },
    tuichu(){
      this.$store.commit("getIslogin",false)
      this.$store.commit("getUserid",0)
      this.$router.push({path: '/home'})
    },
    myself(){
      if(!this.$store.state.islogin){
        ElMessage({
          showClose: true,
          message: '请先登录',
          type: 'warning',
        })
      }
      else{
        this.$router.push({path: '/myhome'})
      }
    },
    useradd(){
      if(!this.$store.state.islogin){
        ElMessage({
          showClose: true,
          message: '请先登录',
          type: 'warning',
        })
      }
      else{
         this.$router.push({path: '/useraddtext'})
      }
    },
    getCollectName(){
      this.$axios
      .get('/selectCollectName?id='+this.$store.state.userid1)
      .then(successResponse => {
          this.collectdata=successResponse.data
          this.collect1=successResponse.data[0]
      })
      .catch(failResponse => {
      this.$alert(failResponse.response.status)
      })
      }
  },
}
</script>

<style scoped>
.button-2{
  width:70px;
  height: 32px;
  border: none;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  background-color: coral;
}
.button-2:hover{
  background-color:crimson;
}
.input1{
  width: 400px;
  height: 30px;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  border:none;
  background-color: rgb(238, 238, 238);
  margin-left:60px;
}
.p1{
  color: rgb(175, 173, 173);
  margin-left:10px;
}
.p1:hover{
  color: coral;
  cursor: pointer;
}
.tooltip{
  width: 500px;
  margin-left:65%;
  position: absolute;
  border-bottom: 1px dotted black;
  margin-top:0px;
  -webkit-transition-duration: 1.0s; /* Safari */
  transition-duration: 1.0s;
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}
.text{
  height: 40px;
  /* display:flex;
  align-items: center; */
  width: 270px;
  overflow: hidden;

}
.text:hover{
  background-color: rgb(207, 207, 207);
  cursor: pointer;
}

.m1{
  background-color: rgb(255, 255, 255);
  width: 400px;
}
.button1{
  margin-left:20px;
  width: 80px;
  height: 30px;
  background-color: rgb(242, 117, 33);
  color: rgb(255, 255, 255);
  font-weight: bold;
  border:none;
  border-radius: 30px;
}
.head1{
  background-color: rgb(255, 255, 255);
  height:50px;
  display:flex;
  align-items: center;
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
  /* justify-content:center; */
}
.view{
  position: relative;
  overflow: hidden;
}
span{
  margin-left:30px;
  color: rgb(0, 0, 0);
  font-size: 16px;
}
.all{
  background-color: rgb(236, 237, 238);
  /* background-image: url("/img/00.jpg");
  background-size: 100%,100%; */
}
.add{
    height:50px;
}
.add:hover{
    cursor: pointer;
    background-color: rgb(168, 168, 168);
}
.bottom{
  margin-top:20px;
  width: 100%;
  height: 120px;
  background-color: black;
  color: rgb(218, 218, 218);
  font-size: 13px;
}
/* .block-col-2 .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
} */
</style>