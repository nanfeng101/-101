<template>
    <div v-show="show1" style="background-color:#ffffff;width:1000px;height:650px;margin-top:-40px;">
        <p style="font-size:20px;font-weight: bold;padding-top:15px;margin-left:10px;">账号设置</p>
        <div style="margin-left:20px;">
            <div style="margin-top:35px">
                <span>密 </span>&nbsp&nbsp&nbsp&nbsp&nbsp
                <span> 码</span>
                <span class="p1" @click="update(1)">修改密码</span>
                <el-divider></el-divider>
            </div>
            <div style="margin-top:35px">
                <span>手 </span>&nbsp&nbsp&nbsp&nbsp&nbsp
                <span>机</span>
                <span class="p1" @click="update(2)">修改手机</span>
                <el-divider></el-divider>
            </div>
            <div style="margin-top:35px">
                <span>邮 </span>&nbsp&nbsp&nbsp&nbsp&nbsp
                <span>箱</span>
                <span class="p1" @click="update(3)">修改邮箱</span>
                <el-divider></el-divider>
            </div>
            <div style="margin-top:35px">
                <span>登录记录</span>
                <span @click="jilu()" class="p1">查看记录</span>
                <el-divider></el-divider>
            </div>
            <div style="margin-top:35px">
                <span>账号注销</span>
                <span class="p1" @click="update(4)">注销账号</span>
                <el-divider></el-divider>
            </div>
        </div>
    </div>
    <div v-show="show2" style="background-color:#ffffff;width:1000px;">
        <span class="p2" @click="a1()">账号设置</span>
        <span style="color: rgb(152, 152, 152);">/</span>
        <span class="p3">登录日志</span>
        <div style="margin-left:20px;margin-top:40px;"><span>若发现登录异常，请尽快修改密码</span></div>
        <el-table :data="tableData" stripe style="width: 980px;margin-left:20px;">
            <el-table-column prop="time1" label="时间" width="800" />
            <el-table-column prop="location" label="位置" width="180" />
        </el-table>
    </div>
    <el-dialog v-model="dialogVisible1" width="400px" title="修改密码">
        <el-input v-model="editadd.oldphone" style="width:300px;" placeholder="请输入手机号"></el-input>
        <el-input v-model="editadd.password" style="width:300px;margin-top:10px;" placeholder="请输入新密码"></el-input>
        <el-input v-model="password1" style="width:300px;margin-top:10px;" placeholder="请再次输入密码"></el-input>
        <div style="margin-top:10px;">
            <el-input style="width:160px;" v-model="editadd.yanzheng" placeholder="请输入验证码"></el-input>
            <el-button v-if="this.value==120 || this.value==0" @click="send()" v-model="editadd.yanzheng" style="margin-left:30px;">获取验证码</el-button>
            <span style="marfin-left:30px;" v-else>{{this.value}}</span>
        </div>
        <div style="margin-top:10px;">
            <el-button @click="updatephone(1)" type="primary">确定</el-button>
            <el-button>取消</el-button>
        </div>
    </el-dialog>
    <el-dialog v-model="dialogVisible2" width="400px" title="修改手机号">
        <el-input v-model="editadd.oldphone" style="width:300px;" placeholder="请输入旧手机号"></el-input>
        <el-input v-model="editadd.phone" style="width:300px;margin-top:10px;" placeholder="请输入新手机号"></el-input>
        <div style="margin-top:10px;">
            <el-input style="width:160px;" v-model="editadd.yanzheng" placeholder="请输入验证码"></el-input>
            <el-button v-if="this.value==120 || this.value==0" style="margin-left:30px;" @click="send()">获取验证码</el-button>
            <span style="marfin-left:30px;" v-else>{{this.value}}</span>
        </div>
        <div style="margin-top:10px;">
            <el-button type="primary" @click="updatephone(2)">确定</el-button>
            <el-button>取消</el-button>
        </div>
    </el-dialog>
    <el-dialog v-model="dialogVisible3" width="400px" title="修改邮箱">
        <el-input v-model="editadd.oldemail" style="width:300px;" placeholder="请输入旧邮箱"></el-input>
        <el-input v-model="editadd.email" style="width:300px;margin-top:10px;" placeholder="请输入新邮箱"></el-input>
        <div style="margin-top:10px;">
            <el-input v-model="editadd.yanzheng"  style="width:160px;" placeholder="请输入验证码"></el-input>
            <el-button @click="send2()" v-if="this.value==120 || this.value==0" v-model="editadd.yanzheng" style="margin-left:30px;">获取验证码</el-button>
            <span style="marfin-left:30px;" v-else>{{this.value}}</span>
        </div>
        <div style="margin-top:10px;">
            <el-button @click="updatephone2()" type="primary">确定</el-button>
            <el-button>取消</el-button>
        </div>
    </el-dialog>
    <el-dialog v-model="dialogVisible4" width="400px" title="真的要删除吗">
        <div style="margin-top:10px;">
            <el-button  type="primary">确定</el-button>
            <el-button>取消</el-button>
        </div>
    </el-dialog>
</template>
<script>
import { ElMessage } from 'element-plus'
export default{
    data(){
        return{
            tableData:[{}],
            show1:true,
            show2:false,
            dialogVisible1:false,
            dialogVisible2:false,
            dialogVisible3:false,
            dialogVisible4:false,
            editadd:{},
            value:120,
            password1:'',
        }
    },
    created: function(){
        this.getlocation()
    },
    methods:{
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
            },2000)
            this.$axios.post("/send",this.editadd)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        send2(){
            this.value=120
            ElMessage({
                showClose: true,
                message: '验证码已发送至邮箱，请及时接受',
                type: 'success',
            })
            setInterval(()=>{
               if(this.value>0){
                this.value--
               }
            },2000)
            this.$axios.post("/send1",this.editadd)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //用于修改密码
        updatephone(val){
            if(val==1){
                if(this.editadd.password==this.password1){
                    if(this.editadd.yanzheng==null){
                        ElMessage({
                            showClose: true,
                            message: '验证码为空',
                            type: 'error',
                        })
                    }else{
                        this.updatephone1()
                    }
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '两次密码输入不一致',
                        type: 'error',
                    })
                }

            }else{
                if(this.editadd.yanzheng==null){
                        ElMessage({
                            showClose: true,
                            message: '验证码为空',
                            type: 'error',
                        })
                    }else{
                        this.updatephone1()
                    }
            }
        },
        //用于修改邮箱
        updatephone2(){
            if(this.editadd.oldemail==null || this.editadd.email==null || this.editadd.yanzheng){
                ElMessage({
                            showClose: true,
                            message: '有内容输入为空',
                            type: 'error',
                        })
            }else{
                this.$axios.post("/updatephone2",this.editadd)
                .then(successResponse => {
                    if(successResponse.data == 'ok'){
                        ElMessage({
                            showClose: true,
                            message: '修改成功',
                            type: 'success',
                        })
                        this.value=120
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
            }
        },
        updatephone1(){
            if(this.editadd.oldemail==null || this.editadd.email==null || this.editadd.yanzheng){
                ElMessage({
                            showClose: true,
                            message: '有内容输入为空',
                            type: 'error',
                        })
            }else{
                this.$axios.post("/updatephone",this.editadd)
                .then(successResponse => {
                    if(successResponse.data == 'ok'){
                        ElMessage({
                            showClose: true,
                            message: '修改成功',
                            type: 'success',
                        })
                        this.value=120
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
            }
        },
        update(val){
            if(val==1){
                this.dialogVisible1=true
            }else if(val == 2){
                this.dialogVisible2=true
            }else if(val==3){
                this.dialogVisible3=true
            }else{
                this.dialogVisible4=true
            }
        },
        jilu(){
            this.show1=false
            this.show2=true
        },
        a1(){
            this.show1=true
            this.show2=false
        },
        getlocation(){
            this.$axios
            .get('/getLoginLocation?id='+this.$store.state.userdetail.id)
            .then(successResponse => {
                this.tableData=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>

<style scoped>
.p1{
    color: rgb(0, 191, 255);
    margin-left:800px;
}
.p1:hover{
    cursor: pointer;
}
.p2{
    margin-left:20px;
    color: rgb(152, 152, 152);
}
.p3{
    color: rgb(152, 152, 152);
}
.p3:hover{
    cursor: pointer;
    color: rgb(0, 191, 255);
}
.p2:hover{
    cursor: pointer;
    color: rgb(0, 191, 255);
}
</style>