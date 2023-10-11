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
                    <el-menu-item index="1"  @click="com('systemhome','主页')"><el-icon class="econ" style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Management /></el-icon><span>主页</span></el-menu-item>
                    <el-menu-item index="2"  @click="com('user','用户管理')"><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><Postcard /></el-icon><span>用户管理</span></el-menu-item>
                    <el-menu-item index="3" @click="com('singer','歌手管理')"><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><User/></el-icon><span>歌手管理</span></el-menu-item>
                    <el-menu-item index="4" @click="com('songlist','歌单管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><DataLine/></el-icon><span>歌单管理</span></el-menu-item>
                    <el-menu-item index="5" @click="com('song','歌曲管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><TrendCharts/></el-icon><span>歌曲管理</span></el-menu-item>
                    <el-menu-item index="6" @click="com('mangemv','MV管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><RemoveFilled/></el-icon><span>MV管理</span></el-menu-item>
                    <el-menu-item index="7" @click="com('aa','MV管理')" ><el-icon style="margin-right: 8px; margin-top: 1px; color:#EAEAEA"><TrendCharts/></el-icon><span>MV管理</span></el-menu-item>
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
                          <component :is="currentCom"></component>
                      </el-card>
                </el-main>
            </el-container>
        </el-container>
    </el-container>
</template>
<script>
import singer from "../houtai/singer.vue"
import songlist from "../houtai/song-list.vue"
import user from "../houtai/user.vue"
import song from "../houtai/song.vue"
import Systemhome from "../houtai/systemhome.vue"
import Mangemv from '../houtai/mangemv.vue'
import Aa from "../houtai/echarts/aa.vue"
import {User,DataLine,Management,Postcard,TrendCharts,RemoveFilled,Promotion,Expand,Setting} from "@element-plus/icons"
  export default {
    name: 'Menu',
    components: {
      User,
      DataLine,
      Management,
      Postcard,
      TrendCharts,
      RemoveFilled,
      Promotion,
      Expand,
      Setting,
      singer,
      songlist,
      song,
      user,
      Systemhome,
      Mangemv,
      Aa,

    },
    data() {
      return {
        //员工信息中心点亮
        activeIndex: '9',
        isCollapse:true,
        timer: undefined,
        nowTime: new Date(),
        latedata:"2020-1-9",
        currentCom:'systemhome',
        tabIndex:1,
        editableTabsValue:'1',
        editableTabs:[{title: '主页',name: Systemhome,content: 'systemhome'}],

      }
    },
    created: function(){
    this.$store.commit("setIsmenu",false)
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
        this.currentCom=tab.props.name
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
        this.currentCom=a
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
    margin-left: -240px;
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