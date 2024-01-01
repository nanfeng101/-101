<template>
  <el-container>
        <el-header height="70px">
            <p class="ddd" style="margin-left:-10px">南枫音乐网后台</p>
            <span class="ddd">| 欢迎admin |</span>
            <span class="cc">{{ FormatTime(nowTime) }}</span>
            <div class="toolbar">
                <el-dropdown>
                    <el-icon><setting/></el-icon>
                    <template #dropdown>
                        <el-dropdown-menu>
                        <el-dropdown-item>用户信息</el-dropdown-item>
                        <el-dropdown-item >退出</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
            <el-divider style="margin-top:10px;" />
        </el-header>
        <el-container>
            <el-aside>
                <el-menu 
                    style="min-height:100%; overflow-x:hidden;height:100%"
                    :default-active="activeIndex"
                    class="el-menu-demo" 
                    :collapse="isCollapse"
                    @select="handleSelect"
                    background-color=rgb(48,65,86)
                    text-color=#EAEAEA
                    active-text-color=red>
                    <div style="height:60px; line-height:60px;text-align:center;">
                        <el-icon @click="isC()" style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Expand/></el-icon>
                        <span style="color:white;">navigation bar</span>
                    </div>
                    <el-submenu index="1">
                      <template class="b" #title><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Document/></el-icon><span>文章管理</span></template>
                      <el-menu-item index="1-1" @click="com('edittext','文章管理')">文章管理</el-menu-item>
                      <el-menu-item index="1-2" @click="com('addtetx','文章添加')">文章添加</el-menu-item>
                    </el-submenu>
                    <!-- <el-menu-item index="1"  @click="com('addtext','文章管理')"><el-icon class="econ" style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Management /></el-icon><span>文章</span></el-menu-item> -->
                    <el-menu-item index="2"  @click="com('usermanage','用户管理')"><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Postcard /></el-icon><span>用户管理</span></el-menu-item>
                    <el-submenu index="3">
                      <template class="b" #title><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><House/></el-icon><span>首页管理</span></template>
                      <el-menu-item index="3-1" @click="com('toutiao','头条文章')">头条文章</el-menu-item>
                      <el-menu-item index="3-2" @click="com('redian','热点文章')">热点文章</el-menu-item>
                      <el-menu-item index="3-3" @click="com('addtetx','广告推荐')">广告推荐</el-menu-item>
                    </el-submenu>
                    <el-menu-item index="4" @click="com('songlist','消息管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Message/></el-icon><span>消息管理</span></el-menu-item>
                    <el-menu-item index="5" @click="com('examine','文章审核')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><FolderChecked/></el-icon><span>文章审核</span></el-menu-item>
                    <el-menu-item index="6" @click="com('song','权限管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Lock/></el-icon><span>权限管理</span></el-menu-item>
                    <el-menu-item index="7" @click="com('liuyanmanage','留言管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><ChatLineRound/></el-icon><span>留言管理</span></el-menu-item>
                    <el-menu-item index="8" @click="com('photomanage','图片管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><PictureFilled/></el-icon><span>图片管理</span></el-menu-item>
                    <el-menu-item index="9" @click="com('aa','友链管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Link/></el-icon><span>友链管理</span></el-menu-item>
                </el-menu>
            </el-aside>
            <el-container>
                <el-main id="a" style="overflow-y:hidden;">
                  <el-tabs
                        v-model="editableTabsValue"
                        type="card"
                        class="demo-tabs"
                        closable
                        @tab-remove="removeTab"
                        @tab-click="change"
                      >
                        <el-tab-pane
                          v-for="item in editableTabs"
                          :key="item.name"
                          :label="item.title"
                          :name="item.name"
                        >
                        </el-tab-pane>
                      </el-tabs>
                      <el-card >
                          <component :is="this.$store.state.currentCom"></component>
                      </el-card>
                </el-main>
            </el-container>
        </el-container>
    </el-container>
</template>
<script>
import Addtetx from '../houtai/TextManage/addtetx.vue'
import Edittext from '../houtai/TextManage/edittext.vue'
import Editbutton from '../houtai/TextManage/feature/editbutton.vue'
import Detailbutton from '../houtai/TextManage/feature/detailbutton.vue'
import toutiao from '../houtai/shouye/toutiao.vue'
import redian from '../houtai/shouye/redian.vue'
import usermanage from '../houtai/user/usermanage.vue'
import examine from '../houtai/examine.vue'
import liuyanmanage from '../houtai/liuyanmanage.vue'
import photomanage from '../houtai/photomanage.vue'
import {PictureFilled,Document,Link,FolderChecked,User,Message,House,Postcard,Lock,ChatLineRound,Promotion,Expand,Setting} from "@element-plus/icons"
  export default {
    components: {
      Document,
      Link,
      FolderChecked,
      User,
      Message,
      House,
      Postcard,
      Lock,
      ChatLineRound,
      Promotion,
      Expand,
      Setting,
      Addtetx,
      Edittext,
      Editbutton,
      Detailbutton,
      toutiao,
      redian,
      usermanage,
      examine,
      liuyanmanage,
      photomanage,
      PictureFilled,

    },
    data() {
      return {
        //员工信息中心点亮
        activeIndex: '1',
        isCollapse:false,
        timer: undefined,
        nowTime: new Date(),
        latedata:"2020-1-9",
        // currentCom:'addtetx',
        tabIndex:1,
        editableTabsValue:'1',
        editableTabs:[{title: '文章添加',name: Addtetx,content: 'addtetx'}],

      }
    },
    created: function(){
      this.$store.commit("getIsviewbot",false)
      // this.$store.commit("getCurrentCom",'edittext')
    // 显示时间，在渲染页面之前一直调用该函数，对this.time进行赋值
      this.timer = setInterval(() => {
      this.nowTime= new Date().toLocaleString();
    });
  },
// 销毁定时器
//   beforeDestroy() {
//     if (this.timer) {
//       clearInterval(this.timer);
//     }
//   },
    methods: {
      change(tab){
        // this.currentCom=tab.props.name
        this.$store.commit("getCurrentCom",tab.props.name)
      },
      removeTab(targetName){
        const tabs = this.editableTabs
        let activeName = this.editableTabsValue
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              const nextTab = tabs[index + 1] || tabs[index - 1]
              if (nextTab) {
                activeName = nextTab.name
            }
         }
        })
        }

        this.editableTabsValue = activeName
        this.editableTabs= tabs.filter((tab) => tab.name !== targetName)
      },
      com(a,b){
        this.$store.commit("getCurrentCom",a)
        const newTabName = `${++this.tabIndex}`
        this.editableTabs.push({
          title: `${b}`,
          name: a,
          content: `${a}`,
        })
        this.editableTabsValue = newTabName
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      isC(){
        this.isCollapse = !this.isCollapse
        if(!this.isCollapse){
            document.getElementById("a").style.marginLeft='-100px'
        }
        else{
            document.getElementById("a").style.marginLeft='-240px'
        }
      },
      FormatTime() {
      //返回显示的日期时间格式
      var date = new Date();
      var year = this.formatTime(date.getFullYear());
      var month = this.formatTime(date.getMonth() + 1);
      var day = this.formatTime(date.getDate());
      var hour = this.formatTime(date.getHours());
      var minute = this.formatTime(date.getMinutes());
      var second = this.formatTime(date.getSeconds());
      var weekday = date.getDay();
      var weeks = new Array(
          "星期日",
          "星期一",
          "星期二",
          "星期三",
          "星期四",
          "星期五",
          "星期六"
      );
      var week = weeks[weekday];
      return `${year}-${month}-${day} ${hour}:${minute}:${second} ${week}`;
    },
    formatTime(n) {
      //判断是否需要加0
      if (n < 10) {
        return "0" + n;
      } else {
        return n;
      }
    },
    }
  }
</script>

<style scoped>
.el-card{
    height:100%;
}
#a{
    margin-left: -100px;
    /* position: relative; */
    background-color: rgb(245, 242, 239);
}
.a{
    text-decoration: none;
    color: #EAEAEA;
}
   .el-menu::-webkit-scrollbar{
    display: none;
}
.el-menu-demo:not(.el-menu--collapse){
    width: 200px;
}
.el-header{
    background-color: rgb(255, 255, 255);
}
.toolbar {
  position:absolute;
  display: inline-flex;
  justify-content: center;
  margin-top:22px;
  right: 20px;
}
.cc{
    margin-left: 840px;
    display: inline-flex;
    text-decoration: none;
    margin-top:15px;
    font-size:22px;
    font-family:"隶书";
    background-color:  rgb(245, 242, 239);
    border-radius: 10px;
    /* position:absolute; */
 }
  .ddd{
    margin-left:10px;
    display: inline-flex;
    text-decoration: none;
    margin-top:15px;
    font-size:22px;
    font-family:"隶书";
    
    /* position:absolute; */
  }
  .a{
       text-decoration: none;
       color: #EAEAEA;
   }
   .demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>