<template>
<div class="body" >
  <!-- <el-carousel :interval="4000" type="card" height="400px" style="background-color: #ffffff;">
    <el-carousel-item v-for="item in url" :key="item">
        <img :src="item" width="760" height="400">
    </el-carousel-item>
  </el-carousel> -->
  <div style="position:relative;">
    <el-carousel height="550px" indicator-position="outside" style="width:100%">
      <el-carousel-item v-for="item in url" :key="item">
        <!-- <h3 text="2xl" justify="center">{{ item }}</h3> -->
        <div style="width:100%">
        <img :src="item" height="550" style="width:200%;margin-left:-850px;overflow: hidden;">
        </div>
      </el-carousel-item>
    </el-carousel>
    <div style="position:absolute;top:500px;height:50px;background-color:#ffffff;width:100%;opacity:0.7;">
    </div>
  </div>
  <div>
    <div>
      <div class="card1">
        <p style="padding-left:30px;font-size:25px;font-weight: bold;">精选歌单</p>
        <p @click="gosong_list()" class="p1" style="position:absolute;top:696px;left:830px;">更多></p>
        <div class="demo-image" style="background-color: #ffffff;">
          <div class="block" v-for="item in songsList" :key="item">
            <div class="img1" @click="singer_listReplace(item.id)">
                <img class="img2" :src="geturl(item.pic)">
                <p style="font-size:13px">{{item.title}}</p>
            </div>
          </div>
        </div>
      </div>
      <!-- <el-card class="card_song">
        <span>抖音热歌榜</span>
        <el-table style="font-size:14px"  :data="songdata" @row-click="handleRowChange">
          <el-table-column prop="id1" label="id" width="40" ></el-table-column>
          <el-table-column prop="name" label="歌曲名" width="140" ></el-table-column>
          <el-table-column prop="singer_name"  label="艺人" width="130" ></el-table-column>
          <el-table-column prop="introduction" label="专辑" width="130"></el-table-column>
        </el-table>
      </el-card> -->
      <div class="card-1">
        <p style="margin-left:20px;font-size:25px;font-weight: bold;">热门榜单</p>
        <p class="p1" @click="replaceBangdan()" style="position:absolute;top:20px;left:270px;">更多></p>
        <div class="card-a2" style="margin-left:20px;margin-top:20px">
          <img class="img1" src="/img/rank_i1.png" width="100" height="100" >
          <div style="position: absolute;display: inline-block;">
            <div v-for="item in 3" :key="item" style="margin-top:10px;margin-left:15px;font-size:14px;">
              <span >{{songdata1[item-1].id1}}. </span>
              <span >{{songdata1[item-1].singer_name}}-</span>
              <span >{{songdata1[item-1].name}}</span>
            </div>
          </div>
        </div>
        <div class="card-a2" style="margin-left:20px;margin-top:20px">
          <img class="img1" src="/img/rank_i1.png" width="100" height="100" >
          <div style="position: absolute;display: inline-block;">
            <div v-for="item in 3" :key="item" style="margin-top:10px;margin-left:15px;font-size:14px;">
              <span >{{songdata1[item-1].id1}}. </span>
              <span >{{songdata1[item-1].singer_name}}-</span>
              <span >{{songdata1[item-1].name}}</span>
            </div>
          </div>
        </div>
        <div class="card-a2" style="margin-left:20px;margin-top:20px">
          <img class="img1" src="/img/rank_i2.png" width="100" height="100" >
          <div style="position: absolute;display: inline-block;">
            <div v-for="item in 3" :key="item" style="margin-top:10px;margin-left:15px;font-size:14px;">
              <span >{{songdata2[item-1].sort_id}}. </span>
              <span >{{songdata2[item-1].singer_name}}-</span>
              <span >{{songdata2[item-1].name}}</span>
            </div>
          </div>
        </div>
        <div class="card-a2" style="margin-left:20px;margin-top:20px">
          <img class="img1" src="/img/rank_i3.png" width="100" height="100" >
          <div style="position: absolute;display: inline-block;">
            <div v-for="item in 3" :key="item" style="margin-top:10px;margin-left:15px;font-size:14px;">
              <span >{{songdata[item-1].id1}}. </span>
              <span >{{songdata[item-1].singer_name}}-</span>
              <span >{{songdata[item-1].name}}</span>
            </div>
          </div>
        </div>
      </div>
      <!-- 每周推荐 -->
      <div>
        <div class="card3">
          <p style="margin-left:5px;font-size:25px;font-weight: bold;">每周推荐</p>
          <button class="card3-p1" @click="qiehuanRecommend('华语')" style="position:absolute;top:1305px;left:460px;">华语</button>
          <button class="card3-p1" @click="qiehuanRecommend('欧美')" style="position:absolute;top:1305px;left:530px;">欧美</button>
          <button class="card3-p1" @click="qiehuanRecommend('韩国')" style="position:absolute;top:1305px;left:600px;">韩国</button>
          <button class="card3-p1" @click="qiehuanRecommend('日本')" style="position:absolute;top:1305px;left:670px;">日本</button>
          <!-- <p class="p1" style="position:absolute;top:1240px;left:830px;">更多></p> -->
          <div class="demo-image1">
            <div class="block1" v-for="item in recommendlist" :key="item">
              <div @click="handleRowChange11(item)">
                <img :src="geturl2(item.pic)" width="60" height="60" style="border-radius:10px;">
                <div style="position: absolute;display: inline-block;">
                  <span style="margin-left:10px;padding-top:5px;display: inline-block;">{{item.name}}</span>
                  <p style="margin-top:5px;margin-left:10px;">{{item.singer_name}}</p>
                </div>
                <div style="position: absolute;display: inline-block;">
                  <div class="play2" style="margin-left:140px;margin-top:15px"></div>
                  <div @click.native="download(item.url)" class="dow2" style="margin-left:190px;margin-top:15px"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 热门mv -->
        <div class="card4">
          <p style="margin-left:5px;font-size:25px;font-weight: bold;margin-top:35px">推荐MV</p>
          <p @click="qianwangMv()" class="p1" style="position:absolute;top:27px;left:255px;">更多></p>
          <div>
            <img @click="playMv('可能.mp4')" src="/img/可能.png" class="mvimg" width="300" height="200">
            <span>可能</span><br>
            <span>程响</span><br>
            <div style="margin-top:20px;display: inline-block;position: absolute;">
              <img src="/img/兰亭序.png" @click="playMv('兰亭序.mp4')" class="mvimg" width="140" height="80"><br>
              <span>兰亭序</span><br>
              <span>周杰伦</span>
            </div>
            <div style="padding-left:160px;margin-top:20px">
              <img src="/img/时光尽头等我.png" @click="playMv('三体.mp4')" class="mvimg" width="140" height="80"><br>
              <span>时光尽头等我</span><br>
              <span>三体</span>
            </div>
          </div>
        </div>
      </div>
  </div>
  </div>
</div>
</template>

<script>
export default{
    data(){
        return{
            activeIndex:1,
            url:["/img/1.jpg","/img/2.jpg","/img/3.jpg","/img/4.jpg","/img/5.jpg"],
            songsList: [{}],
            currentPage:1,
            pageSize:8,
            songsList2: [{}],
            songdata:[{}], //抖音榜单
            songdata1:[{}], //酷狗飙升榜单
            songdata2:[{}], //酷狗TOP500
            style:'华语',
            recommendlist:[{}],  //每周推荐


        }
    },
    created: function () {
        // this.$store.commit("getSong_url",'')
        // this.$store.commit("getSong_name",'')
        // this.$store.commit("getSong_pic",'未播放.jpg')
        this.$store.commit("setIsmenu",true)
        this.getSong_list()
        this.getSong()
        this.handelDouyin()
        this.getRecommend()
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
      qianwangMv(){
        this.$router.push({path: '/mvclassify'})
      },
      //前往榜单页面
      replaceBangdan(){
        this.$router.push({path: '/bangdan'})
      },
      playMv(val){
        this.$store.commit("getVideourl",val)
        this.$store.commit("getVideoint",'')
        this.$router.push({path: '/mv'})

      },
      qiehuanRecommend(val){
        this.style=val
        this.getRecommend()
      },
      //点击每日推荐歌曲进行播放
      handleRowChange11(row){
            let a = row.name
            let b = row.pic
            let c = row.url
            this.$store.commit("getSong_name",a)
            this.$store.commit("getSong_pic",b)
            this.$store.commit("getSong_url",c)
            this.$store.commit("getSongid",row.id)
            this.$store.commit("setUrllist",this.recommendlist)
            this.$store.commit("getLyric",row.lyric)
            for(let i=0;i<this.songdata.length;i++){
                if(this.songdata.songid === this.songdata[i].id){
                this.$store.commit("getList_id",i)
                }
            }
            
        },
      //获得每周推荐歌曲
      getRecommend(){
        this.$axios
        .get('/getRecommendSong?style=' + this.style)
        .then(successResponse=>{
          this.recommendlist=successResponse.data
        })
        .catch(failResponse => {
            this.$alert(failResponse.response.status)
        })
      },
      play1(){
        this.play = '/img/play1.png'
      },
      //用于获取每周推荐歌曲的图片地址
      geturl2(pic){
        let url = "http://localhost:8081/zf/"+pic
        return url
      },
      gosong_list(){
        this.$router.push({path: '/song_list_qiantai'})
      },
      handleRowChange(row){
        let a = row.name
        let b = row.pic
        let c = row.url
        for(let i=0;i<this.songdata.length;i++){
          if(row.id1 === this.songdata[i].id1){
            this.$store.commit("getList_id",i)
          }
        }
        this.$store.commit("getSong_name",a)
        this.$store.commit("getSong_pic",b)
        this.$store.commit("getSong_url",c)
        this.$store.commit("setUrllist",this.songdata)
        this.$store.commit("getLyric",row.lyric)
      },
      singerReplace(id1){
        this.$store.commit('addSong_listId', id1)
        this.$router.push({path: '/songdetail'})

      },
      singer_listReplace(id1){
        this.$store.commit('addSong_listId', id1)
        this.$router.push({path: '/singerdetail'})
      },
      geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        getSong_list(){
            this.$axios
            .get('/getSong_listPage?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
            this.songsList = successResponse.data.song_list
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getSong(){
            this.$axios
            .get('/getSingerPage?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
            this.songsList2 = successResponse.data.singer
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        handelDouyin(){
          let val = "抖音热歌榜"
          this.$axios
          .get('/getBangSong?bangname=' + val)
          .then(successResponse => {
            this.songdata=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
          let val1 = "酷狗飙升榜"
          this.$axios
          .get('/getBangSong?bangname=' + val1)
          .then(successResponse => {
            this.songdata1=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
          this.$axios
            .get('/getTopSong?currentPage=1&&pageSize=3')
            .then(successResponse => {
                this.songdata2 = successResponse.data.song
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>
<style scoped>
.mvimg{
  border-radius: 20px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
}
.mvimg:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    background-color: #858f9d;
    opacity:0.5;
    cursor:pointer;
}
.play2{
  background-image: url("/img/play.png");
  width: 25px;
  height: 25px;
  display: inline-block;
  position: absolute;
  background-size:25px,25px
  /* background-color: aquamarine; */
}
.card3-p1{
  border: hidden;
  background-color: #ffffff;
  width:60px;
  height: 25px;
  background: none;
}
.card3-p1:hover{
    cursor:pointer;
    color:rgb(0, 106, 255);
}
.card3-p1:focus{
  color:rgb(0, 106, 255);
  background-color: rgb(177, 238, 254);
  border-radius: 20px;
}
.dow2{
  background-image: url("/img/dow1.png");
  width: 25px;
  height: 25px;
  position: absolute;
  background-size:25px,25px
}
.play2:hover{
  background-image: url("/img/play1.png");
}
.dow2:hover{
  background-image: url("/img/dow.png");
}
.card-1{
  display: inline-block;
  position: absolute;
  margin-top: 10px;

}
.card-a2{
  border-radius: 20px;
  width: 300px;
  background-color:#FCFCFCFF;
}
.p1:hover{
  cursor:pointer;
  color:rgb(4, 201, 255)
}
.card_song{
  display: inline-block;
  position:absolute;
  /* width: 200px; */
  border-radius: 20px;
  margin-top: 10px;
  margin-left: 20px;
  height: 100px;
}
.card1{
  width: 650px;
  margin-left: 250px;
  border-radius: 20px;
  margin-top: 10px;
  display: inline-block;
}
.card3{
  width: 650px;
  margin-left: 250px;
  border-radius: 20px;
  margin-top: 10px;
  display: inline-block;
}
.card4{
  display: inline-block;
  position: absolute;
  margin-left:20px;
  width: 300px;
}
.card2{
  width: 650px;
  margin-left: 200px;
  border-radius: 20px;
  margin-top: 10px;
}
.body{
    background-color: #ffffff;
    left: 50%;
}
.img1{
    /* border: #2c7ef1 solid 1px; */
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    overflow: hidden;
    border-radius: 20px;
    background-color: #e0e4ea;
    width: 100px;
}
.img2{
    width: 100px;
    height: 150px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}
.img2:hover{
    /* width: 150px;
    height: 200px; */
    transform: scale(1.3);
}
.img1:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    background-color: #858f9d;
    opacity:0.5;
    cursor:pointer; 
    transform: scale(1.2); 
}
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #3c434d;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #5f6771;
}
.demo-image{
  width:600px;
}
.demo-image .block {
  padding: 10px 25px;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  /* width: 25%; */
  box-sizing: border-box;
  vertical-align: top;
}
.demo-image1 .block1{
  padding: 10px 0px;
  width:600px;
  /* text-align: center; */
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 50%;
  box-sizing: border-box;
  vertical-align: top;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
}
.block1:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    background-color: #e2f4ff;
    /* opacity:0.5; */
    cursor:pointer;
    color: rgb(20, 142, 241);
    border-radius: 15px;
    transform: scale(1.1);
}
.demo-image .block:last-child {
  border-right: none;
}
.demo-image .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  padding-top: 15px;
}
.el-carousel__item img {
  display: flex;
  /* color: #475669; */
  /* opacity: 0.75; */
  line-height: 300px;
  margin: 0;
}

/* .el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
} */
</style>
