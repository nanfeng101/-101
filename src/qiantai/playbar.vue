<template>
  <audio  id="audioID" ref="audio" class="aud" @timeupdate="updateTime" @volumechange="volumechange"  @ended="audioEnded">
    <source :src="audioUrl" />
  </audio>
   <el-affix position="bottom" :offset="0">
    <el-icon id="aa" @click="hidden()"><ArrowDown /></el-icon>
  <div class="play" v-show="show2">
    <img class="img1" width="30" @click="retart()" height="30" src="/img/上一首.png"/>&nbsp;&nbsp;&nbsp;
    <img class="img2" width="30" height="30" :src="image1" @click="auditionFun()"/>&nbsp;&nbsp;&nbsp;
    <img class="img3" @click="speed()" width="30" height="30" src="/img/下一首.png" />&nbsp;&nbsp;&nbsp;
    <img class="a1" @click="lyric()" :src="this.$store.state.song_pic" style="margin-left:50px" width="40" height="40">
    <p style="margin-top:10px;margin-left:20px;">{{this.$store.state.song_name}}</p>
    <div style="display: inline-flex;margin-left:10px;">
      <p style="margin-top:10px">{{currentTime1}}:</p>
      <p style="margin-top:10px">{{currentTime}}</p>&nbsp;&nbsp;&nbsp;
    </div>
    <input id="dur" @click="tuodong()" style="width: 200px" type="range" min="0" :max="max1" step="0.1" v-model="value1"/>&nbsp;&nbsp;
    <div style="display: inline-flex;">
      <p style="margin-top:10px">{{totalTime}}:</p>
      <p style="margin-top:10px">{{totalTime1}}</p>
    </div>
    <img class="a1" @click="shoucang()" src="/img/收藏.png" width="25" height="25" style="margin-left:50px;margin-top:10px;">
    <el-icon class="a1" @click="liebiao()"  style="font-size:20px; margin-left:50px; margin-top: 10px; color:black"><Expand /></el-icon>
    <img class="a1" @click="source1()" width="25" height="25" src="/img/声音.png" style="margin-top:8px;margin-left:50px;"/>
    <input v-show="show1" id="input1" type="range" min="0" :max="max2" step="1" v-model="value2"/>
  </div>
   </el-affix>
   <!-- <el-dialog  v-model="disvivbile" width="200px" center>
       <el-table class="table1"  :data="this.$store.state.urllist" @row-click="handleRowChange">
          <el-table-column prop="name" label="歌曲名" width="100" ></el-table-column>
      </el-table>
    </el-dialog> -->
    <el-drawer  v-model="disvivbile" :direction="direction" :modal="false" size="300px" :lock-scroll="false" close-on-click-modal>
      <!-- <template #header>
        <h4>歌曲列表</h4>
      </template> -->
      <!-- <template #default> -->
        <div>
          <el-table  class="table1"  :data="this.$store.state.urllist" @row-click="handleRowChange">
            <el-table-column prop="name" label="歌曲列表" width="200px"></el-table-column>
            <el-table-column width="50px">
              <template #default="scope">
                <el-icon class="icon-close" @click.native="download(scope.row.url)"><Download /></el-icon>
              </template>
            </el-table-column>
            <el-table-column width="50px">
              <template #default="scope">
                <el-icon class="icon-close" @click.native="remove1(scope.row)"><Close /></el-icon>
              </template>
            </el-table-column>
          </el-table>
        </div>
      <!-- </template> -->
      <!-- <template #footer>
        <div style="flex: auto">
          <el-button @click="cancelClick">cancel</el-button>
          <el-button type="primary" @click="confirmClick">confirm</el-button>
        </div>
      </template> -->
    </el-drawer>
</template>
<script>
import { ElNotification } from 'element-plus'
import {Expand,ArrowDown,Close,Download} from "@element-plus/icons"
export default{
  name:"playbar",
  data(){
    return{
      audioOpen:false,
      audioUrl:'',
      // audioUrl:'http://localhost:8081/zf/张杰-天下.mp3',
      currentTime: 0,
      totalTime: 0,
      percentage:0,
      currentTime1:0,
      totalTime1:0,
      max1:200,
      value1:0,
      image1:'/img/暂停.jpg',
      song_name:'',
      index:0,
      disvivbile:false,
      lyr:[],
      show1:false,
      mix2:1,
      value2:0,
      show2:false,
    }
  },
  components: {
    Expand,
    ArrowDown,
    Close,
    Download

  },

   mounted() {
    // const audioPlayer = this.$refs.audio;
    // audioPlayer.addEventListener('timeupdate', this.updateProgress);
    setTimeout(()=>{
          this.audio = this.$refs.audio;
          this.value2=this.audio.volume
            // this.audio.currentTime=this.value1
            // this.updateTime()
        },300)
    setTimeout(()=>{
        this.updateTime()
    },10000)
  },
  computed: {
    song_url(){
      return this.$store.state.song_url
    }
  },
  methods:{
    download(val){
        let fileurl = 'http://localhost:8081/zf/' + val
        var a = document.createElement('a')
        a.download = val
        a.style.display = 'none'
        let blob = new Blob([fileurl])
        a.href = URL.createObjectURL(blob)
          // 触发点击
        document.body.appendChild(a)
          // 然后移除
        a.click()
        document.body.removeChild(a)
        a.remove()

      },
    remove1(val){
      let item = this.$store.state.urllist
      let index = item.indexOf(val)
      item.splice(index,1)
      this.$store.commit("setUrllist",item)
    },
    hidden(){
      this.show2 = !this.show2
      document.getElementById('aa').style.marginTop = '50px'
    },
    //上一首
    retart(){
      let i = this.$store.state.list_id
      let urlist = this.$store.state.urllist
      if(i===0){
        this.$store.commit("getList_id",urlist.length)
      }
      this.$store.commit("getSong_url",urlist[i-1].url)
      this.$store.commit("getSong_name",urlist[i-1].name)
      this.$store.commit("getSong_pic",urlist[i-1].pic)
      this.$store.commit("getLyric",urlist[i-1].lyric)
      this.$store.commit("getList_id",i-1)
      this.$store.commit("getSongid",urlist[i-1].id)
    },
    //下一首
    speed(){
      let i = this.$store.state.list_id
      let urlist = this.$store.state.urllist
      if(i===urlist.length-1){
        this.$store.commit("getList_id",-1)
      }
      this.$store.commit("getSong_url",urlist[i+1].url)
      this.$store.commit("getSong_name",urlist[i+1].name)
      this.$store.commit("getSong_pic",urlist[i+1].pic)
      this.$store.commit("getLyric",urlist[i+1].lyric)
      this.$store.commit("getList_id",i+1)
      this.$store.commit("getSongid",urlist[i+1].id)
    },
    shoucang(){
      let userid = this.$store.state.userid
      let songid = this.$store.state.songid
      if(this.$store.state.userid === null){
        ElNotification({
          title: 'Error',
          message: '请先登录',
          type: 'error',
        })
      }
      else if(this.$store.state.songid === null){
        ElNotification({
          title: 'Error',
          message: '还未播放歌曲',
          type: 'error',
        })
      }
      else{
      this.$axios
      .get('/shoucang?userid=' + userid + '&&songid=' + songid)
      .then(successResponse=>{
          if(successResponse.data === 'ok'){
            ElNotification({
              title: 'Success',
              message: '收藏成功',
              type: 'success',
            })
          }
          else if(successResponse.data === 'in'){
            ElNotification({
              title: 'Error',
              message: '歌曲已被收藏',
              type: 'error',
            })
          }
          else{
            ElNotification({
              title: 'Error',
              message: '收藏失败',
              type: 'error',
            })
          }
      })
      .catch(failResponse => {
          this.$alert(failResponse.response.status)
      })
      }
    },
    source1(){
      this.show1 = !this.show1
    },
    volumechange(){
      this.value2=this.audio.volume
    },
    lyric(){
      this.$router.push({path: '/lyric'})
    },
    handleRowChange(row){
      this.$store.commit("getSong_name",row.name)
      this.$store.commit("getSong_pic",row.pic)
      this.$store.commit("getSong_url",row.url)
      this.$store.commit("getLyric",row.lyric)
      this.$store.commit("getSongid",row.id)
      for(let i=0;i<this.$store.state.urllist.length;i++){
        if(this.$store.state.songid === this.$store.state.urllist[i].id){
          this.$store.commit("getList_id",i)
        }
      }
    },
    //播放列表
    liebiao(){
      this.disvivbile=true
    },
    //开始和暂停
    auditionFun() {
      if(!this.audioOpen){
        console.log("进入播放");
        this.$store.commit("setIsplay",true)
        this.play()
      }else{
        console.log("进入暂停");
        this.pause()
      }
    },
    //进度条
    tuodong(){
      this.pause()
      this.$store.commit("setIsplay",true)
      this.play()
    },
    updateTime() {
        this.currentTime1 = Math.floor(this.audio.currentTime/60)
        this.currentTime=Math.round(this.audio.currentTime)%60
        this.totalTime = Math.floor(this.audio.duration/60)
        this.totalTime1 = Math.round(this.audio.duration)%60
        this.max1=Math.round(this.audio.duration)
        setTimeout(()=>{
          this.value1=Math.round(this.audio.currentTime)
       },10000)
    },
    //监听播放完成的回调
    audioEnded(){
      if(this.value1==this.max1){
      this.audioOpen=false
      console.log('end')
      }
    },
    // 播放音频
    play(){
      //  this.$state.commit("setIsplay",false)
      this.audioOpen=true
      this.audio.currentTime=this.value1
      this.image1='/img/播放.jpg'
      if(this.$store.state.isPlay){
        this.$refs.audio.play();
        // this.$store.commit("setIsplay",false)

      }else{
        this.$refs.audio.load();
        this.$refs.audio.play();
      }
     },
     pause(){
      this.audioOpen=false
      //  this.$state.commit("setIsplay",true)
       this.image1='/img/暂停.jpg'
       this.$refs.audio.pause();
    },
    jishu(){
      this.index=this.index+1
    },
  },
  watch:{
    //监听进度条，当进度条满了之后播放列表中的下一首
    value1(){
        this.$store.commit("getTime",this.value1)
        const duration = document.getElementById('dur')
        let parcent=this.value1/this.max1*100
        duration.style.backgroundSize=`${parcent}%,100%`
        if(this.value1==this.max1){
          this.value1=0
          let i = this.$store.state.list_id
          this.$store.commit("getSong_name",this.$store.state.urllist[i+1].name)
          this.$store.commit("getSong_pic",this.$store.state.urllist[i+1].pic)
          this.$store.commit("getLyric",this.$store.state.urllist[i+1].lyric)
          let sum = this.$store.state.urllist.length
          this.$store.commit("getSong_url",this.$store.state.urllist[i+1].url)
          this.$store.commit("getList_id",i+1)
          this.audio.currentTime=0
          if(i === sum-1){
            this.$store.commit("getList_id",-1)
          }
      }
    },
     song_url(val){
      this.audioUrl=val
      this.$store.commit("setIsplay",false)
      this.$axios
      .get('/updatePlay_sum?id=' + this.$store.state.songid)
      // for(let i=0;i<this.$store.state.urllist.length;i++){
      //   if(this.$store.state.songid === this.$store.state.urllist[i].id){
      //     this.$store.commit("getList_id",i)
      //   }
      // }
      setTimeout(()=>{
        this.play()
      },300)
     },
     value2(){
      this.audio.volume = this.value2
     }
  },
}
</script>

<style scoped>
.icon-close:hover{
  color: #05aff7;
}
.play{
  display: inline-flex;
  height: 40px;
  background-color: rgb(255, 255, 255);
  width: 100%;
  box-shadow: 0px -10px 20px #888888;
}
.progress-bar {
  width: 200px;
  height: 10px;
}
.a1:hover{
  cursor:pointer;
}
.img1,.img2,.img3{
  margin-top: 5px;
}
.img1{
  margin-left: 300px;
}
.img1,.img2,.img3:hover{
  cursor:pointer;
}
#dur[type="range"]{
  -webkit-appearance: none;
  /* overflow: hidden; */
  width:200px;
  height: 4px;
  /* outline: none; */
  /* background: none; */
  margin-top: 20px;
  appearance: none;
  background: -webkit-linear-gradient(#05aff7,#21b5f4) no-repeat #ddd;
  background-size: 0%,100%;
}
#dur[type="range"]::-webkit-slider-thumb{
  -webkit-appearance: none;
  appearance: none;
  background: rgb(0, 0, 0);
  width: 10px;
  height: 10px;
  border-radius: 50%;
  transition: 2s;
  
}
#dur[type="range"]::-webkit-slider-thumb:hover{
  background:#666;
}
.table1:hover{
  cursor:pointer;
}
/* #input1[type="range"]{
  -webkit-appearance: slider-vertical;
  height: 100px;
} */
/* input[type="range"]::-webkit-slider-thumb:{
  position: absolute;
  top: 3px;
  height: 4px;
  content: "";
  pointer-events: none;
  transition: 2s;

} */
/* input[type="range"]::-moz-range-track{
  left:-1997px;
  background: rgb(9, 236, 248);
}
input[type="range"]::-webkit-slider-runnable-track{
  left:10px;
  background: linear-gradient(to right, rgb(2, 255, 242), rgb(47, 255, 217));
  height: 5px;
  top: 5px;
  border-radius: 15px;
} */
</style>
