<template>
<div class="demo">
    <div style="padding-top:80px;">
    <div class="demo-a1" id="a1" v-show="show1">
        <p>登录账号</p>
        <el-form :model="addUserForm">
            <el-form-item prop="username">
                <el-input style="width:200px;margin-top:50px;margin-left:50px;" v-model="addUserForm.username" placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" style="width:200px;margin-left:50px;" v-model="addUserForm.password" placeholder="密码"></el-input>
            </el-form-item>
            <el-button type="primary" style="margin-left:110px;border-radius:20px;" @click="login()">登录</el-button>
        </el-form>
    </div>
    <div class="demo-a2" id="a2" v-show="show1">
        <p>欢迎登录南枫音乐网</p>
        <el-button type="primary" style="margin-left:52px;margin-top:80px;border-radius:20px;" @click="changezuce()">还没有账号？点击注册</el-button>
    </div>
    <div class="demo-a3" id="a3" v-show="show2">
        <p>注册账号</p>
        <el-form :model="addUserForm2">
            <el-form-item prop="username">
                <el-input style="width:200px;margin-top:50px;margin-left:50px;" v-model="addUserForm2.username" placeholder="手机号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input style="width:200px;margin-left:50px;" v-model="addUserForm2.password" placeholder="密码"></el-input>
            </el-form-item>
            <el-button type="primary" style="margin-left:110px;border-radius:20px;" @click="zuche()">注册</el-button>
        </el-form>
    </div>
    <div class="demo-a4" id="a4" v-show="show2">
        <p>欢迎注册南枫音乐网</p>
        <el-button type="primary" style="margin-left:52px;margin-top:80px;border-radius:20px;" @click="changelogin()">已有账号？点击登录</el-button>
    </div>
    </div>
</div>
</template>
<script>
import { ElNotification } from 'element-plus'
export default{
    name:'userlogin',
    data(){
        return{
            addUserForm:{},
            show1:true,
            show2:false,
            addUserForm2:{},
        }
    },
    methods:{
        changelogin(){
            this.show1=true
            this.show2=false
            setTimeout(()=>{
                document.getElementById('a2').style.marginLeft='720px'
                document.getElementById('a1').style.marginLeft='420px'
                document.getElementById('a4').style.marginLeft='720px'
                document.getElementById('a3').style.marginLeft='420px'
            },300)
        },
        changezuce(){
            this.show1=false
            this.show2=true
            setTimeout(()=>{
                document.getElementById('a2').style.marginLeft='420px'
                document.getElementById('a1').style.marginLeft='720px'
                document.getElementById('a4').style.marginLeft='420px'
                document.getElementById('a3').style.marginLeft='720px'
            },100)
        },
        zuche(){
            this.$axios
            .post('/userRegister',this.addUserForm2)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    ElNotification({
                        title: 'Success',
                        message: '注册成功',
                        type: 'success',
                    })
                }
                else{
                    ElNotification({
                        title: 'Error',
                        message: '密码或用户名不能为空',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
                this.$alert(failResponse.response.status)
                ElNotification({
                        title: 'Error',
                        message: '用户名已存在',
                        type: 'error',
                    })
            })
        },
        login(){
            this.$axios
            .post('/userLogin',this.addUserForm)
            .then(successResponse=>{
                if(successResponse.data.islogin === 1){
                    this.$store.commit("getUserId",successResponse.data.consumer.id)
                    this.$store.commit("getUserid1",successResponse.data.consumer.id)
                    this.$store.commit("getUserpic",successResponse.data.consumer.avator)
                    this.$store.commit("getuserdetail",successResponse.data.consumer)
                    this.$router.push({path: '/userupdate'})
                    ElNotification({
                        title: 'Success',
                        message: '登录成功',
                        type: 'success',
                    })
                }
                else{
                    ElNotification({
                        title: 'Error',
                        message: '密码或用户名错误',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
                this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>
<style scoped>
p{
    font-size: 20px;
    font-weight: bold;
    text-align: center;
}

.demo-a4{
    margin-top: 50px;
    margin-left: 720px;
}
.demo-a2{
    margin-top: 50px;
    margin-left: 720px;
}
.demo-a2, .demo-a4{
    background-image: url("https://gao7pic.gao7.com/e20112f81c74471ca64dfe9ade0df82b.png");
    background-size: 300px,350px;
}
.demo-a1, .demo-a3{
    opacity: 0.9;
    background:none;
}
.demo-a1, .demo-a2 , .demo-a3, .demo-a4{
    width: 300px;
    height: 350px;
    display: inline-block;
    position: absolute;
    -webkit-transition-duration: 1.0s; /* Safari */
    transition-duration: 1.0s;
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);

}
.demo-a3{
    margin-top: 50px;
    margin-left: 420;
}
.demo-a1{
    margin-top: 50px;
    margin-left: 420px;
     
}
.demo{
    height: 620px;
    background-image: url("https://p.qpic.cn/dnfbbspic/0/dnfbbs_dnfbbs_dnf_gamebbs_qq_com_forum_201911_22_083548z4dn4295a5ej89ad.jpg/0");
    background-size: 100%,100%;
}
</style>
