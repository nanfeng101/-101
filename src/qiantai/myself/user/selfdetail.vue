<template>
    <div>
        <div style="background-color:#ffffff;width:1000px;height:170px;margin-top:-20px;">
            <div style="margin-left:15px;display: inline-block;position: absolute;padding-top:15px;">
                <el-dialog width="400px" v-model="dialogVisible">
                    <el-upload
                        class="upload-demo"
                        drag
                        :action="seturl()"
                        :on-success="handleUploadSuccess"
                        multiple
                    >
                        <el-icon class="el-icon--upload" style="font-size:80px;margin-top:20px;"><upload-filled /></el-icon>
                        <div class="el-upload__text">
                        Drop file here or <em>click to upload</em>
                        </div>
                        <template #tip>
                        <div class="el-upload__tip">
                            jpg/png files with a size less than 500kb
                        </div>
                        </template>
                    </el-upload>
                </el-dialog>
                <img @click="uploadimg()" class="img1" :src="geturl()" alt="请上传图片" width="130" height="130">
            </div>
            <div style="margin-left:180px;padding-top:15px;">
                <p style="font-size:20px;font-weight: bold;">{{this.$store.state.userdetail.username}}</p>
                <p style="color: rgb(181, 181, 181);">{{this.$store.state.userdetail.introduce}}</p>
                <button class="button1">个人主页</button>
            </div>
        </div>
        <div style="background-color:#ffffff;width:1000px;height:450px;">
            <p style="font-size:20px;font-weight: bold;padding-top:15px;margin-left:10px;">基本信息</p>
            <el-divider></el-divider>
            <div style="margin-left:20px;">
                <div  @mouseout="a(1)" @mouseleave="b(1)" style="display:flex;align-items: center;">
                    <span>用户昵称</span>
                    <span style="margin-left:60px;">{{this.$store.state.userdetail.username}}</span>
                    <el-input v-model="editUser.username" placeholder="请输入昵称" v-show="show1_1" size="small" style="width:200px;margin-left:50px;"></el-input>
                    <el-button @click="update(1)" style="margin-left:15px;" v-show="show1_1" size="small">确定</el-button>
                    <el-button @click="quxiao(1)" v-show="show1_1" size="small">取消</el-button>
                    <el-icon v-show="show1" style="color:rgb(19, 174, 251);font-size:18px;margin-left:30px;"><Edit /></el-icon>
                    <span @click="edit(1)" v-show="show1" style="color:rgb(19, 174, 251);margin-left:5px;">编辑</span>
                </div>
                <div style="margin-top:35px;">
                    <span>用 户 I D</span>
                    <span style="margin-left:60px;">{{this.$store.state.userdetail.id}}</span>
                </div>
                <div @mouseout="a(2)" @mouseleave="b(2)" style="margin-top:35px;display:flex;align-items: center;">
                    <span>性 </span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <span> 别</span>
                    <span style="margin-left:60px;">{{this.$store.state.userdetail.sex}}</span>
                    <el-input v-model="editUser.sex" placeholder="请输入性别" v-show="show2_2" size="small" style="width:200px;margin-left:50px;"></el-input>
                    <el-button @click="update(2)" style="margin-left:15px;" v-show="show2_2" size="small">确定</el-button>
                    <el-button @click="quxiao(2)" v-show="show2_2" size="small">取消</el-button>
                    <el-icon v-show="show2" style="color:rgb(19, 174, 251);font-size:18px;margin-left:30px;"><Edit /></el-icon>
                    <span @click="edit(2)" v-show="show2" style="color:rgb(19, 174, 251);margin-left:5px;">编辑</span>
                </div>
                <div  @mouseout="a(3)" @mouseleave="b(3)" style="margin-top:35px;display:flex;align-items: center;width:">
                    <span>个人签名</span>
                    <span style="margin-left:60px;">{{this.$store.state.userdetail.introduce}}</span>
                    <el-input v-model="editUser.introduce"  v-show="show3_3" placeholder="请输入签名" size="small" style="width:200px;margin-left:50px;"></el-input>
                    <el-button @click="update(3)" style="margin-left:15px;" v-show="show3_3" size="small">确定</el-button>
                    <el-button @click="quxiao(3)" v-show="show3_3" size="small">取消</el-button>
                    <el-icon v-show="show3" style="color:rgb(19, 174, 251);font-size:18px;margin-left:30px;"><Edit /></el-icon>
                    <span @click="edit(3)" v-show="show3" style="color:rgb(19, 174, 251);margin-left:5px;">编辑</span>
                </div>
                <div  @mouseout="a(4)" @mouseleave="b(4)" style="margin-top:35px;display:flex;align-items: center;">
                    <span>所在地区</span>
                    <span style="margin-left:60px;">{{this.$store.state.userdetail.location}}</span>
                    <el-input v-model="editUser.location"  v-show="show4_4" placeholder="请输入地区" size="small" style="width:200px;margin-left:50px;"></el-input>
                    <el-button @click="update(4)" style="margin-left:15px;" v-show="show4_4" size="small">确定</el-button>
                    <el-button @click="quxiao(4)" v-show="show4_4" size="small">取消</el-button>
                    <el-icon v-show="show4" style="color:rgb(19, 174, 251);font-size:18px;margin-left:30px;"><Edit /></el-icon>
                    <span @click="edit(4)" v-show="show4" style="color:rgb(19, 174, 251);margin-left:5px;">编辑</span>
                </div>
                <div  @mouseout="a(5)" @mouseleave="b(5)" style="margin-top:35px;display:flex;align-items: center;">
                    <span>出生日期</span>
                    <span style="margin-left:60px;">{{this.$store.state.userdetail.birth1}}</span>
                    <div v-show="show5_5">
                    <el-date-picker
                        type="date"
                        v-model="editUser.birth"
                        style="width:200px;margin-left:50px;"
                        placeholder="请输入出生年月"
                        size="small"
                    />
                    </div>
                    <el-button @click="update(5)" style="margin-left:15px;" v-show="show5_5" size="small">确定</el-button>
                    <el-button @click="quxiao(5)" v-show="show5_5" size="small">取消</el-button>
                    <el-icon v-show="show5" style="color:rgb(19, 174, 251);font-size:18px;margin-left:30px;"><Edit /></el-icon>
                    <span @click="edit(5)" v-show="show5" style="color:rgb(19, 174, 251);margin-left:5px;">编辑</span>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
import { UploadFilled,Edit } from '@element-plus/icons'
export default{
    data(){
        return{
            dialogVisible:false,
            show1:false,
            show2:false,
            show3:false,
            show4:false,
            show5:false,
            show1_1:false,
            show2_2:false,
            show3_3:false,
            show4_4:false,
            show5_5:false,
            editUser:{},
        }
    },
    components:{
        UploadFilled,
        Edit,
    },
    created: function(){
        this.getdetail()
    },
    methods:{
        seturl(){
            let url='http://124.220.16.147:8011/zf/'+'updateUserPic?id='+this.$store.state.userid1
            return url
        },
        handleUploadSuccess(res){
        this.$store.state.userdetail.pic=res
      },
        update(val){
            this.editUser.id=this.$store.state.userid1
            if(val == 1){
                this.$store.state.userdetail.username=this.editUser.username
            }
            else if(val == 2){
                this.$store.state.userdetail.sex=this.editUser.sex
            }
            else if(val == 3){
                this.$store.state.userdetail.introduce=this.editUser.introduce
            }
            else if(val == 4){
                this.$store.state.userdetail.location=this.editUser.location
            }else{
                this.$store.state.userdetail.birth=this.editUser.birth
            }
            this.$axios
            .post('/updatedetail',this.editUser)
            .then(successResponse => {
                if(successResponse.data == 'ok'){
                    ElMessage({
                        showClose: true,
                        message: '修改成功',
                        type: 'success',
                    })
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '修改失败',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //鼠标移动到元素上触发的事件
        a(id){
            if(id==1){
                this.show1=true
            }
            else if(id==2){
                this.show2=true
            }
            else if(id==3){
                this.show3=true
            }
            else if(id==4){
                this.show4=true
            }else{
                this.show5=true
            }
        },
        //鼠标离开时触发的事件
        b(id){
            if(id==1){
                this.show1=false
            }
            else if(id==2){
                this.show2=false
            }
            else if(id==3){
                this.show3=false
            }
            else if(id==4){
                this.show4=false
            }else{
                this.show5=false
            }
        },
        edit(id){
            if(id==1){
                this.show1_1=true
            }
            else if(id==2){
                this.show2_2=true
            }
            else if(id==3){
                this.show3_3=true
            }
            else if(id==4){
                this.show4_4=true
            }else{
                this.show5_5=true
            }
        },
        quxiao(id){
            if(id==1){
                this.show1_1=false
            }
            else if(id==2){
                this.show2_2=false
            }
            else if(id==3){
                this.show3_3=false
            }
            else if(id==4){
                this.show4_4=false
            }else{
                this.show5_5=false
            }
        },
        geturl(){
            let url = 'http://124.220.16.147:8011/zf/'+this.$store.state.userdetail.pic
            return url
        },
        uploadimg(){
            this.dialogVisible=true
        },
        getdetail(){
            this.$axios
            .get('/getUserDetail?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.$store.commit("getUserdetail",successResponse.data)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
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