<template>
    <span>图片管理</span>
    <el-button style="margin-left:15px;" @click="addphoto1()" size="small" type="primary">上传图片</el-button>
    <el-table :data="tableData" style="width: 100%" max-height="auto">
        <el-table-column prop="username" label="用户名" width="200"></el-table-column>
        <el-table-column label="图片" width="100">
            <template #default="scope">
                <img @click="large(scope.row.pic1)" :src="geturl(scope.row.pic1)" width="75" height="100">
            </template>
        </el-table-column>
        <el-table-column prop="content" label="内容" width="500"></el-table-column>
        <el-table-column prop="time1" label="上传时间" width="200"></el-table-column>
        <el-table-column label="操作" width="100">
            <template #default="scope">
                <el-button @click="deletePhoto(scope.row)" type="primary">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-dialog v-model="visvible1">
        <img :src="this.img1" width="525" height="700">
    </el-dialog>
    <el-dialog title="上传图片" v-model="visvible">
<div>
  <el-upload
    v-model:file-list="fileList"
    :action="setpic()"
    list-type="picture-card"
    :on-preview="handlePictureCardPreview"
    :on-remove="handleRemove"
    :on-success="handleUploadSuccess"
  >
    <el-icon><Plus /></el-icon>
  </el-upload>

  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>
</div>
<el-input
  style="margin-top:15px;width:200px;"
  v-model="addphoto.content"
  maxlength="30"
  placeholder="Please input"
  show-word-limit
  type="text"
/>
<div style="margin-top:15px;">
  <el-button @click="add()" type="primary">确定</el-button>
  <el-button @click="this.visvible=false">取消</el-button>
</div>
</el-dialog>
</template>
<script>
import {Plus} from "@element-plus/icons"
import { ElMessage } from 'element-plus'
export default{
    data(){
        return{
            tableData:[{}],
            visvible1:false,
            img1:'',
            visvible:false,
            dialogImageUrl:'',
            dialogVisible:false,
            addphoto:{},
            addText:{},
        }
    },
    components:{
        Plus,
    },
    created: function(){
        this.getPhoto()
    },
    methods:{
        setpic(){
            let url='http://124.220.16.147:8011/zf/'+'uploadBiaoTiPic'
            return url
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
            this.addphoto.pic1=res
            this.addText.pic=res
        },
        add(){
            this.addphoto.user_id=5
            this.$axios
            .post("/addPhoto",this.addphoto)
            .then(successResponse => {
                if(successResponse.data=='ok'){
                  ElMessage({
                    showClose: true,
                    message: '上传成功',
                    type: 'success',
                  })
                  this.getPhoto()
                  this.visvible=false
                }
                else{
                  ElMessage({
                    showClose: true,
                    message: '上传失败',
                    type: 'error',
                  })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        addphoto1(){
            this.visvible=true
        },
        deletePhoto(val){
            this.$axios
            .get("/deletePhoto?id="+val.id+"&&pic="+val.pic1)
            .then(successResponse => {
                if(successResponse.data=='ok'){
                    ElMessage({
                    showClose: true,
                    message: '删除成功',
                    type: 'success',
                  })
                }
                else{
                    ElMessage({
                    showClose: true,
                    message: '删除失败',
                    type: 'error',
                  })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        large(val){
            this.visvible1=true
            let url = 'http://124.220.16.147:8011/zf/'+val
            this.img1=url
        },
        getPhoto(){
            this.$axios
            .get("/getPhoto")
            .then(successResponse => {
                this.tableData=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        geturl(val){
            let url = 'http://124.220.16.147:8011/zf/'+val
            return url
        },

    }
}
</script>