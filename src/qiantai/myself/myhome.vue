<template>
<div>
    <el-container>
        <el-aside>
            <el-menu 
                style="min-height:100%; overflow:hidden;height:680px"
                :default-active="activeIndex"
                class="el-menu-demo" 
                :collapse="isCollapse"
                @select="handleSelect"
                background-color=rgb(236,237,238)
                text-color=#000000
                active-text-color=red>
                <div style="height:60px; line-height:60px;text-align:center;">
                    <span>创作中心</span>
                </div>
                <el-submenu index="1">
                    <template class="b" #title><el-icon style="margin-right: 8px; margin-top: 1px; color:#000000"><DataLine/></el-icon><span>管理</span></template>
                    <el-menu-item index="1-1" @click="com('contentmanage')">内容管理</el-menu-item>
                    <el-menu-item index="1-2" >评论管理</el-menu-item>
                </el-submenu>
                <el-submenu index="2">
                    <template class="b" #title><el-icon style="margin-right: 8px; margin-top: 1px; color:#000000"><DataAnalysis/></el-icon><span>数据</span></template>
                    <el-menu-item index="2-1" @click="com('textdata')">作品数据</el-menu-item>
                    <el-menu-item index="2-2" @click="com('fensidata')">粉丝数据</el-menu-item>
                    <el-menu-item index="2-3" >七天小结</el-menu-item>
                </el-submenu>
                <el-submenu index="3">
                    <template class="b" #title><el-icon style="margin-right: 8px; margin-top: 1px; color:#000000"><Position/></el-icon><span>创作活动</span></template>
                    <el-menu-item index="3-1" >活动列表</el-menu-item>
                    <el-menu-item index="3-2" >投稿管理</el-menu-item>
                </el-submenu>
                <el-submenu index="4">
                    <template class="b" #title><el-icon style="margin-right: 8px; margin-top: 1px; color:#000000"><Setting/></el-icon><span>设置</span></template>
                    <el-menu-item index="4-1" >博客设置</el-menu-item>
                    
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-main>
            <el-card>
                <component :is="currentCom"></component>
            </el-card>
        </el-main>
    </el-container>
</div>
</template>
<script>
import Contentmanage from '../myself/manage/contentmanage.vue'
import Textdata from '../myself/data/textdata.vue'
import fensidata from '../myself/data/fensidata.vue'
import {Position,DataAnalysis,DataLine,Setting} from "@element-plus/icons"
export default{
    data(){
        return{
            currentCom:'contentmanage',
        }
    },
    components:{
        Setting,
        DataLine,
        DataAnalysis,
        Position,
        Contentmanage,
        Textdata,
        fensidata,
        
    },
    created: function(){
        this.$store.commit("getIsviewbot",false)
    },
    methods:{
        com(val){
            this.currentCom=val
        },
    }
}
</script>
