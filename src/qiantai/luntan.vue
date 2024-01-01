<template>
<el-container class="all-1">
<el-aside style="width:1000px;margin-left:100px;">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="最多观看" name="first"></el-tab-pane>
        <el-tab-pane label="最新发布" name="second"></el-tab-pane>
        <el-tab-pane label="Role" name="third"></el-tab-pane>
        <el-tab-pane label="Task" name="fourth"></el-tab-pane>
    </el-tabs>
    <div v-for="item in textdata" :key="item">
        <div class="content1-a1">
            <div v-if="item.iscover">
                <div>
                    <img style="margin-left:10px;padding-top:10px;" :src="geturl(item.avatar)" width="200" height="120"/>
                </div>
                <div class="content1-b1">
                    <p style="font-weight: bold;">{{item.biaoti}}</p>
                    <p class="p-a1" >{{item.content}}</p>
                </div>
            </div>
            <div v-else>
                <div style="margin-left:15px;height:120px;padding-top:5px;">
                    <p style="font-weight: bold;">{{item.biaoti}}</p>
                    <p class="p-a1">{{item.content}}</p>
                </div>
            </div>
            <div class="center1">
                <el-avatar :size="35" :src="geturl(item.pic)" style="margin-left:10px;"></el-avatar>
                <span style="margin-left:10px;">{{item.username}}</span>
                <el-icon style="margin-left:15px;"><Clock /></el-icon>
                <span style="margin-left:3px;">{{item.time}}</span>
                <el-icon style="margin-left:15px;"><View /></el-icon>
                <span style="margin-left:3px;">{{item.view_sum}}</span>
                <el-icon style="margin-left:15px;"><ChatDotSquare /></el-icon>
                <!-- <span style="margin-left:3px;"></span> -->
                <span style="margin-left:3px;">0</span>
            </div>          
        </div>
        <el-divider></el-divider>  
    </div>
</el-aside>
<el-main>
    <div>
        <el-button @click="dialog()" type="primary" size="small"><el-icon><CirclePlus /></el-icon>发布问答</el-button>
    </div>
</el-main>
</el-container>
<el-dialog v-model="dialogTableVisible" title="发布文章" width="1200px">
    <div style="margin-top:10px;">
    <v-md-editor :toolbar="toolbar" left-toolbar="undo redo clear | color h bold italic strikethrough quote | ul ol table hr | link image code | tip emoji save" 
    v-model="addText.content" @upload-image="handleUploadImage" :disabled-menus="[]" height="550px"></v-md-editor>
    </div>
    <div style="margin-top:10px;">
    <span>封面</span>
    <el-radio-group v-model="addText.iscover1" style="margin-left:20px;">
      <el-radio label="1" size="large" @click="xian()">有封面</el-radio>
      <el-radio label="0" size="large" @click="xian()">无封面</el-radio>
    </el-radio-group>
  </div>
  <div style="margin-left:80px;margin-top:10px;">
  <el-upload
    v-model:file-list="fileList"
    :action="setpic()"
    list-type="picture-card"
    :on-preview="handlePictureCardPreview"
    :on-remove="handleRemove"
    :on-success="handleUploadSuccess"
    v-show="show1"
  >
    <el-icon><Plus /></el-icon>
  </el-upload>

  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>
  </div>
  <div style="margin-top:10px;">
    <span>标题</span>
    <el-input v-model="addText.biaoti" style="margin-left:20px;width:500px;" placeholder="请输入标题"></el-input>
    <el-button @click="addLuntanText()" type="primary" style="margin-left:10px;">发布</el-button>
  </div>
</el-dialog>
</template>
<script>
import { ElMessage } from 'element-plus'
import {Clock,View,ChatDotSquare,CirclePlus} from "@element-plus/icons"
export default{
    data(){
        return{
            textdata:[{}],
            dialogTableVisible:false,
            inputValue1:'',
            show1:true,
            dialogVisible:false,
            addText:{},
        }
    },
    components:{
        Clock,
        View,
        ChatDotSquare,
        CirclePlus,
    },
    created: function(){
        this.handle()
    },
    methods:{
        addLuntanText(){
            this.addText.user_id=this.$store.state.userid1
            this.$axios
            .post("/addLuntanText",this.addText)
            .then(successResponse => {
                if(successResponse.data=='ok'){
                    ElMessage({
                        showClose: true,
                        message: '添加成功',
                        type: 'success',
                    })
                }else{
                    ElMessage({
                        showClose: true,
                        message: '添加失败',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        dialog(){
            this.dialogTableVisible=true
        },
        xian(){
            if(this.addText.iscover1 === '1'){
            this.show1=false
            }else{
            this.show1=true
            }
        },
        geturl(val){
            return 'http://124.220.16.147:8011/zf/'+val
        },
        handle(){
            this.$axios
            .get("/getLuntanText")
            .then(successResponse => {
                this.textdata=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        handleRemove(res){
            this.addText.pic=res.name
            this.$axios
            .post('/deleteimg',this.addText)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })

        },
        handlePictureCardPreview(){
            this.dialogImageUrl = this.uploadFile.url
            this.dialogVisible = true
        },
        handleUploadSuccess(res){
            this.addText.avatar=res
        },
        setpic(){
            let url='http://124.220.16.147:8011/zf/'+'uploadBiaoTiPic'
            return url
        },
        handleUploadImage(state,insertImage,file) {
                // 上传成功，返回图片url地址
            console.log(file[0]);
            const formData = new FormData()
            formData.append('image',file[0])
            this.$axios
            .post('/uploadCoverPic',formData)
            .then(successResponse => {
                let url1 = 'http://124.220.16.147:8011/zf/'+successResponse.data
                insertImage({
                url:url1,
                desc: '七龙珠',
                // width: 'auto',
                // height: 'auto',
            });
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
    },
}
</script>

<style scoped>
.p-a1{
    color: rgb(129, 130, 131);
}
#dian1:hover{
    color: coral;
}
#dian2:hover{
    color: coral;
}
.input{
    width: 250px;
    margin-top:40px;
    margin-left:25px;
}
.center:hover{
    cursor: pointer;
    color: rgb(255, 123, 0);
}
.center1{
    align-items: center;
    display: flex;
    margin-top:5px;
}
.content1-a1{
    background-color: rgb(255, 255, 255);
    margin-top:10px;
    border-radius: 10px;
    height: 180px;
}
.content1-a1:hover{
    cursor: pointer;
    
}
.content1-a1::-webkit-scrollbar{
    display: none;
}
.content1-b1{
    display: inline-block;
    word-wrap: break-word;
    word-break: break-all;
    overflow: hidden;
    position:absolute;
    margin-left:220px;
    margin-top:-135px;
}
.c1{
    display: inline-block;
    margin-left:25px;
    text-align: center;
    justify-content:center;
    font-weight: bold;
}
.content-a2-b2{
    background-color:rgb(252, 252, 252);
    height: 100%;
}
.all-1{
    background-color: rgb(255, 255, 255);
    background-size: 100%,100%;
    height: auto;
}
.center{
    /* align-items: center; */
    text-align: center;
    justify-content:center;
}
#a{
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}
.content1{
    display:inline-block;
    position: absolute;
    height: auto;
    width: 980px;
    margin-left:100px;
    margin-top:15px;
}
.ifa{
    width: 340px;
    border: none;
    height: 540px;
    margin-left:-20px;
}
</style>