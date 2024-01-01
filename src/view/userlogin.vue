<template>
<div class="demo">
    <div style="padding-top:80px;">
    <div class="demo-a1" id="a1" v-show="show1">
        <p>登录账号</p>
        <el-form :model="addUserForm">
            <el-form-item prop="phone">
                <el-input maxlength="11" style="width:200px;margin-top:50px;margin-left:50px;" v-model="addUserForm.phone" placeholder="手机号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" style="width:200px;margin-left:50px;" v-model="addUserForm.password" placeholder="密码"></el-input>
            </el-form-item>
            <el-button type="primary" style="margin-left:110px;border-radius:20px;" @click="login()">登录</el-button>
        </el-form>
    </div>
    <div class="demo-a2" id="a2" v-show="show1">
        <p>欢迎登录南枫博客平台</p>
        <el-button type="primary" style="margin-left:52px;margin-top:80px;border-radius:20px;" @click="changezuce()">还没有账号？点击注册</el-button>
        <p class="sp1" @click="update()">忘记密码</p>
    </div>
    <div class="demo-a3" id="a3" v-show="show2">
        <p>注册账号</p>
        <el-form :model="addUserForm2">
            <el-form-item prop="phone">
                <el-input maxlength="11" style="width:200px;margin-top:50px;margin-left:50px;" v-model="addUserForm2.phone" placeholder="手机号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input style="width:200px;margin-left:50px;" v-model="addUserForm2.password" placeholder="密码"></el-input>
            </el-form-item>
            <el-button type="primary" style="margin-left:110px;border-radius:20px;" @click="zuche()">注册</el-button>
        </el-form>
    </div>
    <div class="demo-a4" id="a4" v-show="show2">
        <p>欢迎注册南枫博客平台</p>
        <el-button type="primary" style="margin-left:52px;margin-top:80px;border-radius:20px;" @click="changelogin()">已有账号？点击登录</el-button>
    </div>
    </div>
</div>
 <el-dialog v-model="dialogVisible1" width="400px" title="修改密码">
        <el-input v-model="editadd.oldphone" style="width:300px;" placeholder="请输入手机号"></el-input>
        <el-input v-model="editadd.password" style="width:300px;margin-top:10px;" placeholder="请输入新密码"></el-input>
        <div style="margin-top:10px;">
            <el-input style="width:160px;" v-model="editadd.yanzheng" placeholder="请输入验证码"></el-input>
            <el-button v-if="this.value==120 || this.value==0" @click="send()" v-model="editadd.yanzheng" style="margin-left:30px;">获取验证码</el-button>
            <span style="marfin-left:30px;" v-else>{{this.value}}</span>
        </div>
        <div style="margin-top:10px;">
            <el-button @click="updatephone1()" type="primary">确定</el-button>
            <el-button>取消</el-button>
        </div>
    </el-dialog>
</template>
<script>
import { ElMessage } from 'element-plus'
export default{
    name:'userlogin',
    data(){
        return{
            addUserForm:{},
            show1:true,
            show2:false,
            editadd:{},
            addUserForm2:{},
            dialogVisible1:false,
            value:120,
        }
    },
    created: function(){
        this.getChatRead()
    },
    methods:{
        getChatRead(){
        this.$axios
            .get('/getChatRead?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.$store.commit("getIsread",successResponse.data)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        update(){
            this.dialogVisible1=true
        },
        send(){
            this.value=120
            ElMessage({
                showClose: true,
                message: '短信已发送至手机，请及时接受',
                type: 'success',
            })
            setInterval(()=>{
               if(this.value>0){
                this.value--
               }
            },1000)
            this.$axios.post("/send",this.editadd)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        updatephone1(){
            this.$axios.post("/updatephone",this.editadd)
            .then(successResponse => {
                if(successResponse.data == 'ok'){
                    ElMessage({
                        showClose: true,
                        message: '修改成功',
                        type: 'success',
                    })
                    this.dialogVisible1=false
                }
                else if(successResponse.data=='no'){
                    ElMessage({
                        showClose: true,
                        message: '验证码超时',
                        type: 'error',
                    })
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '验证码错误',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        changelogin(){
            this.show1=true
            this.show2=false
            setTimeout(()=>{
                document.getElementById('a2').style.marginLeft='355px'
                document.getElementById('a1').style.marginLeft='55px'
                document.getElementById('a4').style.marginLeft='355px'
                document.getElementById('a3').style.marginLeft='55px'
            },300)
        },
        changezuce(){
            this.show1=false
            this.show2=true
            setTimeout(()=>{
                document.getElementById('a2').style.marginLeft='55px'
                document.getElementById('a1').style.marginLeft='355px'
                document.getElementById('a4').style.marginLeft='55px'
                document.getElementById('a3').style.marginLeft='355px'
            },100)
        },
        zuche(){
            this.$axios
            .post('/register',this.addUserForm2)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    this.addUserForm2.phone=''
                    this.addUserForm2.password=''
                    ElMessage({
                        showClose: true,
                        message: '注册成功',
                        type: 'success',
                    })
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '该手机号已注册',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
                this.$alert(failResponse.response.status)
            })
        },
        login(){
            this.$axios
            .post('/login',this.addUserForm)
            .then(successResponse=>{
                if(successResponse.data.password == this.addUserForm.password){
                    this.$store.commit("getIslogin",true)
                    this.$store.commit("getUserid",successResponse.data.id)
                    this.$store.commit("getUserdetail",successResponse.data)
                    ElMessage({
                        showClose: true,
                        message: '登录成功',
                        type: 'success',
                    })
                    this.$router.push({path: '/home'})
                    this.getChatRead()
                }
                else{
                    if(successResponse.data.id==0){
                        ElMessage({
                        showClose: true,
                        message: '该账号因涉嫌违规已被禁用',
                        type: 'error',
                    })
                    }
                    else{
                    ElMessage({
                        showClose: true,
                        message: '密码错误',
                        type: 'error',
                    })
                    }
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
.sp1:hover{
    cursor: pointer;
    color: coral;
}
.demo-a4{
    margin-top: 10px;
    margin-left: 355px;
}
.demo-a2{
    margin-top: 10px;
    margin-left: 355px;
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
    margin-top: 10px;
    margin-left: 55;
}
.demo-a1{
    margin-top: 10px;
    margin-left: 55px;
     
}
.demo{
    height: 520px;
    background-image: url("https://p.qpic.cn/dnfbbspic/0/dnfbbs_dnfbbs_dnf_gamebbs_qq_com_forum_201911_22_083548z4dn4295a5ej89ad.jpg/0");
    background-size: 100%,100%;
}
</style>