<template>
    <div style="margin-left:150px;margin-top:15px;">
        <el-container>
            <el-aside style="width:200px;">
                <el-menu 
                    style="min-height:100%;height:550px"
                    :default-active="activeIndex"
                    class="el-menu-demo" 
                    :collapse="isCollapse"
                    @select="handleSelect"
                    background-color=rgb(255,255,255)
                    text-color=#000000
                    active-text-color=red>
                        <el-menu-item index="1" @click="com('selfdetail')">个人资料</el-menu-item>
                        <el-menu-item index="2" @click="com('usersetting')">账号设置</el-menu-item>
                        <el-menu-item index="3" @click="com('usercollect')">我的收藏</el-menu-item>
                        <el-menu-item index="4" @click="com('userattention')">关注</el-menu-item>
                        <el-menu-item index="5" @click="com('userbrowse')">浏览历史</el-menu-item>
                        <el-menu-item index="6" >内容管理</el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <component :is="currentCom"></component>
            </el-main>
        </el-container>
    </div>
</template>
<script>
import { UploadFilled,Edit } from '@element-plus/icons'
import selfdetail from '../user/selfdetail.vue'
import usersetting from '../user/usersetting.vue'
import usercollect from '../user/usercollect.vue'
import userattention from '../user/userattention.vue'
import userbrowse from '../user/userbrowse.vue'
export default{
    data(){
        return{
            currentCom:'selfdetail',
        }
    },
    components:{
        UploadFilled,
        Edit,
        selfdetail,
        usersetting,
        usercollect,
        userattention,
        userbrowse,
    },
    methods:{
        com(val){
            this.currentCom=val
        }
    }
}
</script>

<style scoped>
.button1{
    width:100px;
    border: none;
    border-radius: 20px;
}
.button1:hover{
    cursor: pointer;
    color: rgb(255, 255, 255);
    background-color: rgb(123, 123, 123);
}
.img1{
    border-radius: 50%;
}
span:hover{
    cursor: pointer;
}
.img1:hover{
    cursor: pointer;
}
.el-menu-demo{
    text-align: center;
}
</style>