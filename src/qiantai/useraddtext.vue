<template>
<div style="margin-top:10px;">
    <v-md-editor codemirror-style-reset="true" default-show-toc="true" :toolbar="toolbar" left-toolbar="undo redo clear | color h bold italic strikethrough quote | ul ol table hr | link image code | tip emoji save" 
    v-model="addText.content" @upload-image="handleUploadImage" :disabled-menus="[]" height="550px"></v-md-editor>
</div>
<div style="background-color: rgb(255, 255, 255);margin-top:15px;">
<div style="padding-left:250px">
    <div>
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
  <span>摘要</span>
  <div>
    <el-input style="width:500px;padding-left:66px;" v-model="addText.zhaiyao" maxlength="300" type="textarea" placeholder="摘要(选填)会在推荐,首页列表等页面展示"></el-input>
  </div>
  
  <div style="margin-top:10px;">
    <span>文章标签</span>
    <el-tag
    v-for="tag in dynamicTags"
    :key="tag"
    class="mx-1"
    closable
    :disable-transitions="false"
    @close="handleClose(tag)"
    style="margin-left:10px;"
  >
    {{ tag }}
  </el-tag>
  <el-input
    v-if="inputVisible"
    ref="InputRef"
    v-model="inputValue"
    class="ml-1 w-20"
    size="small"
    @change="handleInputConfirm()"
    style="width:100px;"
  />
  <el-button style="margin-left:10px;" v-else class="button-new-tag ml-1" size="small" @click="showInput()">
    +添加文章标签
  </el-button>
  </div>

  <div style="margin-top:10px;">
    <span>分类专栏</span>
    <el-tag
    v-for="tag in dynamicTags1"
    :key="tag"
    class="mx-1"
    closable
    :disable-transitions="false"
    @close="handleClose1(tag)"
    style="margin-left:10px;"
  >
    {{ tag }}
  </el-tag>
  <el-input
    v-if="inputVisible1"
    ref="InputRef"
    v-model="inputValue1"
    class="ml-1 w-20"
    size="small"
    @change="handleInputConfirm1()"
    style="width:100px;"
  />
  <el-button style="margin-left:10px;" v-else class="button-new-tag ml-1" size="small" @click="showInput1()">
    +添加分类专栏
  </el-button>
  </div>
</div>
</div>
<div>
<el-affix position="bottom" :offset="0">
    <div style="border-top: #a9a9a9 solid 1px;width:100%;height:60px;display: flex;background-color: rgb(255, 255, 255);">
        <el-input class="input" v-model="addText.biaoti" maxlength="50" placeholder="请输入文章标题" ></el-input>
        <button class="button1" style="margin-left:200px;" @click="addTextDraft()">保存草稿</button>
        <button class="button1" @click="addtext()" style="margin-left:15px;background-color: rgb(255, 121, 25);color:#ffffff;">发布文章</button>
    </div>
</el-affix>
</div>
</template>
<script >
import {Plus} from "@element-plus/icons"
import { ElMessage } from 'element-plus'
export default{
    data(){
        return{
            addText:{},
            inputVisible:false,
            dynamicTags:[],
            inputValue:'',
            inputVisible1:false,
            dynamicTags1:[],
            inputValue1:'',
            show1:true,
            iscover:'1',
            id1:0,
            dialogImageUrl:'',
            dialogVisible:false,
            toolbar:{
                 color: {
                    title: '颜色',
                    icon: 'el-icon-orange',
                    menus: [
                        {
                            name: 'menu1',
                            text: '红色',
                            action(editor) {
                                editor.insert(function (selected) {
                                    const prefix = '<p style="color:#FF0000">';
                                    const suffix = '</p>';
                                    const placeholder = '请输入文本';
                                    const content = selected || placeholder;

                                    return {
                                    text: `${prefix}${content}${suffix}`,
                                    selected: content,
                                    };
                                });
                            },
                        },
                    ],
                },
            }
        }
    },
    created: function(){
        this.$store.commit("getIsviewbot",false)
    },
    components:{
        Plus,
    },
    methods:{
        addTextDraft(){
            this.$store.commit("getBiaoti",this.addText.biaoti)
            alert(this.addText.content)
            this.addText.user_id=this.$store.state.userid1
            this.$axios
            .post('/addTextDraft',this.addText)
            .then(successResponse => {
            if(successResponse.data == -1){
                ElMessage({
                    showClose: true,
                    message: '保存失败',
                    type: 'error',
                })
            }
            else{
                ElMessage({
                    showClose: true,
                    message: '保存成功',
                    type: 'success',
                })
            }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        xian(){
            if(this.addText.iscover1 === '1'){
            this.show1=false
            }else{
            this.show1=true
            }
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
            this.addText.pic=res
        },
        handleClose(a){
            let i=this.dynamicTags.indexOf(a)
            this.dynamicTags.splice(i,1)
        },
        showInput(){
            this.inputVisible = true
        },
        handleInputConfirm(){
            this.dynamicTags.push(this.inputValue)
            this.inputVisible= false
            this.inputValue = ''
        },
        handleClose1(a){
            let i=this.dynamicTags1.indexOf(a)
            this.dynamicTags1.splice(i,1)
        },
        showInput1(){
            this.inputVisible1 = true
        },
        handleInputConfirm1(){
            if(this.dynamicTags1.length<1){
            this.dynamicTags1.push(this.inputValue1)
            this.inputVisible1= false
            this.inputValue1 = ''
            }
            else{
            alert("分类专栏只能有一条")
            this.inputVisible1= false
            this.inputValue1 = ''
            }
        },
        add(){
            this.dialog=true
        },
        addtext(){
            // '/addTextContent?content='+this.content+'&&biaoti='+this.biaoti+'&&zhaiyao='+this.zhaiyao+'&&pic='+this.pic+'&&type='+this.type
            this.$store.commit("getBiaoti",this.addText.biaoti)
            this.addText.user_id=this.$store.state.userid1
            this.$axios
            .post('/addTextContent',this.addText)
            .then(successResponse => {
            this.id1=successResponse.data
            if(successResponse.data == -1){
                ElMessage({
                    showClose: true,
                    message: '添加失败',
                    type: 'error',
                })
            }
            else{
                ElMessage({
                    showClose: true,
                    message: '添加成功',
                    type: 'success',
                })
            }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
            setTimeout(()=>{
            if(this.id1!=0){
            for(let item of this.dynamicTags){
                this.addbiaoqian(item)
            }
            }
            },500)
            setTimeout(()=>{
            if(this.id1!=0){
                // alert(this.dynamicTags1[0])
            this.addtype(this.dynamicTags1[0])
            }
            },500)
        },
        addtype(val){
            let item=val
            this.$axios.get('/addTextType?id='+this.id1+'&&type='+item)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        addbiaoqian(val){
            let item=val
            this.$axios.get('/addBioaqian?biaoti='+this.id1+'&&name='+item)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
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
    }
}
</script>
<style scoped>
.input{
    width: 500px;
    margin-top:10px;
    margin-left:250px;
}
.button1{
    width: 100px;
    height: 40px;
    border-radius: 30px;
    border:none;
    font-weight: bold;
    margin-top:10px;
}
</style>
