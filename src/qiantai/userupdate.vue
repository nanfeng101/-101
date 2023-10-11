<template>
<el-card>
    <el-container>
        <el-aside>
            <el-menu 
                style="min-height:100%;margin-left:110px; overflow-x:hidden;"
                :default-active="activeIndex"
                class="el-menu-demo" 
                @select="handleSelect"
                background-color=#fff
                text-color=#000
                active-text-color=red>
                <el-menu-item index="1" @click="detail()">个人信息</el-menu-item>
                <el-menu-item index="2" @click="pic()">修改头像</el-menu-item>
                <el-menu-item index="3" @click="tuichu()">退出登录</el-menu-item>
            </el-menu>
        </el-aside>
        <el-main>
            <div v-show="show1">
                <p class="title">编辑个人资料</p>
                <hr/>
                <!-- <div class="personal"> -->
                <el-form :model="registerForm" class="demo-ruleForm" label-width="80px">
                    <el-form-item prop="username" label="用户名">
                        <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码">
                        <el-input type="password" placeholder="密码" v-model="registerForm.password"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                    <el-radio-group v-model="registerForm.sex">
                        <el-radio :label="0">女</el-radio>
                        <el-radio :label="1">男</el-radio>
                    </el-radio-group>
                    </el-form-item>
                    <el-form-item prop="phonenum" label="手机">
                        <el-input  placeholder="手机" v-model="registerForm.phonenum" ></el-input>
                    </el-form-item>
                    <el-form-item prop="email" label="邮箱">
                        <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
                    </el-form-item>
                    <el-form-item prop="birth" label="生日">
                        <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.birth" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                    <el-form-item prop="introduction" label="签名">
                        <el-input  type="textarea" placeholder="签名" v-model="registerForm.introduction" ></el-input>
                    </el-form-item>
                    <el-form-item prop="location" label="地区">
                        <el-input placeholder="签名" v-model="registerForm.location"></el-input>
                    </el-form-item>
                    <el-form-item prop="update_time">
                        <el-date-picker v-model="registerForm.update_time" type="date" placeholder="修改日期"></el-date-picker>
                    </el-form-item>
                </el-form>
                <div style="margin-left:400px;">
                    <el-button type="primary" @click="update()">保存</el-button>
                    <el-button @click="quxiao()" type="danger">取消</el-button>
                </div>
                <!-- </div> -->
            </div>
            <div v-show="show2">
                <el-upload
                    class="upload"
                    drag
                    :action="setpic(this.$store.state.userid)"
                    multiple
                >
                    <el-icon style="font-size:40px" class="el-icon--upload"><upload-filled /></el-icon>
                    <div class="el-upload__text">
                    Drop file here or <em>click to upload</em>
                    </div>
                    <template #tip>
                    <div class="el-upload__tip">
                        jpg/png files with a size less than 10M
                    </div>
                    </template>
                </el-upload>
            </div>
        </el-main>
    </el-container>
    </el-card>
</template>
<script>
import { UploadFilled } from '@element-plus/icons'
export default{
    name:'userupdate',
    data(){
        return{
            registerForm:{},
            show1:true,
            show2:false,
        }
    },
    components:{
        UploadFilled,
    },
    created: function () {
        this.handle()
    },
    methods:{
        setpic(id){
            let url="http://localhost:8081/zf/updateAcator?id="+id
            return url
        },
        detail(){
            this.show1=true
            this.show2=false
        },
        pic(){
            this.show1=false
            this.show2=true
        },
        update(){
            this.$axios
            .post('/updateConsumer',this.registerForm)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    this.$alert('保存成功', {confirmButtonText: '确定' })
                    this.handle()
                }
                else{
                    this.$alert('保存失败', {confirmButtonText: '确定' })
                }

            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
        quxiao(){
            this.$router.push({path: '/home'})
        },
        tuichu(){
            this.$store.commit("getUserId",null)
            this.$store.commit("getUserpic",'22.jpg')
            this.$router.push({path: '/home'})
        },
        handle(){
            this.$axios
            .get('getIdConsumer?id=' + this.$store.state.userid)
            .then(successResponse=>{
                this.registerForm=successResponse.data
                this.$store.commit("getuserdetail",successResponse.data)
            })
            .catch(failResponse => {
                this.$alert(failResponse.response.status)
            })

        }
    }
}
</script>

<style scoped>
.el-menu::-webkit-scrollbar{
    display: none;
}
.el-menu-demo:not(.el-menu--collapse){
    width: 150px;
}
.upload{
    height: 100%;
    width: 500px;
}
</style>
