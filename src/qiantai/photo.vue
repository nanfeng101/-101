<template>
<div class="head-a1">
    <div class="aa1">
        <span class="sp1">图片分享</span>
        <button @click="addphoto1()" class="button0" style="margin-left:20px;">上传图片</button>
    </div>
<div class="all1">
    <div class="box" v-for="item in photo" :key="item" @click="download(item.pic1)">
        <div class="card" style="--color:#0d9745">
            <img :src="geturl(item.pic1)">
            <div class="text-box">
                <h3>用户:{{item.username}}</h3>
                <p>上传时间:{{item.time1}}</p>
                <p>{{item.content}}</p>
            </div>
        </div>
    </div>
</div>
</div>
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
            photo:[{}],
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
        add(){
            this.addphoto.user_id=this.$store.state.userid1
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
          if(this.$store.state.userid1!=0){
            this.visvible=true
          }
          else{
            ElMessage({
              showClose: true,
              message: '请先登录',
              type: 'warning',
            })
          }
        },
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
        geturl(val){
            let url = 'http://124.220.16.147:8011/zf/'+val
            return url

        },
        download(val){
            let fileurl = 'http://124.220.16.147:8011/zf/'+val
            var a = document.createElement('a')
            a.download = val
            a.target='_blank'
            a.style.display = 'none'
            let blob = new Blob([fileurl])
            a.href = fileurl
            // 触发点击
            document.body.appendChild(a)
            // 然后移除
            a.click()
            document.body.removeChild(a)
            a.remove()
        },
        getPhoto(){
            this.$axios
            .get("/getPhoto")
            .then(successResponse => {
                this.photo=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    },
}
</script>

<style>
.button0{
  color: rgb(255, 255, 255);
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  width: 200px;
  height: 50px;
  background-image: linear-gradient(to right,
  rgb(19, 120, 244),
  rgb(255, 85, 124),
  rgb(241, 255, 85),
  rgb(19, 120, 244)
  );
  background-size: 400%;
  border-radius: 30px;
  border:none;
  margin-left:40%;
}
.button0:hover{
  animation: run 8s infinite;
  cursor: pointer;
}
@keyframes run{
  100%{
    background-position: -400%,0;
  }
}
.sp1{
    font-size: 23px;
    font-weight: bold;
}
.aa1{
    margin-left:20px;
    padding-top:10px;
}
.head-a1{
    background: linear-gradient(10deg, #0d9545, #45b9dd);
}
.all1{
  /* display: grid; */
  place-content: center;
  min-height: 100vh;
  box-sizing: border-box;
  padding: 0;
  background: linear-gradient(10deg, #0d9545, #45b9dd);
}
.box {
  display: inline-block;
  margin-left:21px;
  margin-top:15px;
  gap: 5vmin;
}
.box:hover{
    cursor: pointer;
}
.card {
  width: 30vmin;
  aspect-ratio: 3/4;
  border-radius: 1vmin;
  box-shadow: rgba(255, 255, 255, 0.2) 0 5vmin 6vmin -8vmin,
    var(--color) 0 4.5vmin 5vmin -6vmin,
    rgba(50, 50, 50, 0.5) 0 4vmin 8vmin -2vmin,
    rgba(0, 0, 0, 0.8) 0 4vmin 5vmin -3vmin;
  overflow: hidden;
  position: relative;
}

.card img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
  transition: all 0.8s;
}
.card:hover img {
  transform: scale(1.1);
}

.card::before,
.card::after {
  content: "";
  width: 100%;
  height: 50%;
  background-color: rgba(0, 0, 0, 0.5);
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;

  transform: scaleX(0);
  transition: all 0.5s;
  transform-origin: left;
}
.card::after {
  top: 50%;
  transition-delay: 0.15s;
}
.card:hover::before,
.card:hover::after {
  transform: scaleX(1);
}

.card .text-box {
  color: rgba(255, 255, 255, 0.9);
  font-size: 1.5vmin;
  letter-spacing: 0.1vmin;
  inset: 2vmin;
  position: absolute;
  top: auto;
  z-index: 2;
}
.card .text-box h3,
.card .text-box p {
  color: var(--color);
  opacity: 0;
  transform: translateY(10vmin);
  transition: all 0.8s, color 3s;
}
.card .text-box h3 {
  font-size: 4vmin;
  font-weight: bold;
  margin-bottom: 1vmin;
}
.card .text-box p {
  transition-delay: 0.15s;
}
.card:hover .text-box h3,
.card:hover .text-box p {
  color: inherit;
  opacity: 1;
  transform: translateY(0);
}

</style>