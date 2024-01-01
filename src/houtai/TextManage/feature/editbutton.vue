<template>
  <el-input
    v-model="addText.biaoti"
    maxlength="30"
    placeholder="请输入标题"
    show-word-limit
    type="text"
    style="width:1080px;"
  />
  <el-button @click="add()" type="primary" style="margin-left:30px;">发布文章</el-button>
<div style="margin-top:10px;">
    <v-md-editor left-toolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | link image code | tip emoji save" 
     v-model="addText.content" @upload-image="handleUploadImage" :disabled-menus="[]" height="426px"></v-md-editor>
</div>
<el-dialog title="发布文章" v-model="dialog" width="600px">
  <div>
    <span>封面&摘要</span>
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
  <div style="margin-top:20px;">
    <el-button @click="this.dialog=false">取消</el-button>
    <el-button type="primary" @click="edittext()">确认</el-button>
  </div>
</el-dialog>

</template>
<script>
import {Plus} from "@element-plus/icons"
export default{
    data(){
      return{
        // text:'',
        dialog:false,
        // biaoti:'基于springboot+vue的个人博客',
        // pic:'',
        addText:{},
        // type:'springboot',
        // content:"",
        // zhaiyao:'前后端分离',
        inputVisible:false,
        dynamicTags:[],
        inputValue:'',
        inputVisible1:false,
        dynamicTags1:[],
        inputValue1:'',
        show1:true,
        iscover:'1',
        id1:0,
        fileList:[{name:'',url:''}],
        dialogImageUrl:'',
        dialogVisible:false,
      }
    },
    components:{
        Plus,
    },
    created: function(){
        this.addText=this.$store.state.text
        this.dynamicTags1.push(this.addText.type)
        for(let item of this.$store.state.biaoqian){
            if(this.addText.id==item.id){
                this.dynamicTags=item.list
            }
        }
        this.fileList[0].name=this.addText.pic
        this.fileList[0].url='http://124.220.16.147:8011/zf/'+this.addText.pic
    },
    methods:{
      xian(){
        if(this.iscover === '1'){
          this.show1=false
        }else{
          this.show1=true
        }
      },
      handleUploadSuccess(res){
        this.addText.pic=res
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
     edittext(){
        // '/addTextContent?content='+this.content+'&&biaoti='+this.biaoti+'&&zhaiyao='+this.zhaiyao+'&&pic='+this.pic+'&&type='+this.type
        this.$store.commit("getBiaoti",this.addText.biaoti)
        this.$axios
        .post('/updateText',this.addText)
        .then(successResponse => {
          this.id1=successResponse.data
          if(successResponse.data == -1){
            alert('修改失败')
          }
          else{
            alert('修改成功')
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
            alert(this.dynamicTags1[0])
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
    },
}
</script>
<style scoped>
.upload{
    height: 50px;
    width: 50px;
}
</style>