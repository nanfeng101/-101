<template>
    <div style="margin-left:100px;margin-top:20px;">
        <span id="b1" style="color:rgb(0, 174, 255)" @click="song()">歌曲{{this.songsum}}</span>
        <span id="b2" @click="song_list()">歌单{{this.song_listsum}}</span>
        <span>专辑</span>
        <span id="b4" @click="mv()">视频{{this.mvsum}}</span>
    </div>
    <div v-show="show1">
        <div style="margin-left:150px;margin-top:50px;">
            <el-button @click="playall()" style="width:130px" type="primary"><el-icon style="font-size:15px;"><VideoPlay /></el-icon>播放全部</el-button>
            <el-popover placement="right" :width="130" trigger="click">
                <template #reference>
                    <el-button style="width:130px">添加到</el-button>
                </template>
                <div style="margin-left:-50px;text-align:center;">
                <span @click="playSelect()">播放队列</span>
                <el-divider />
                <span>我喜欢</span>
                <el-divider />
                <span @click="addSong_list()">添加到新的歌单</span>
                </div>   
            </el-popover>
            <el-button style="width:130px" @click="download()">下载</el-button>
            <el-button style="width:130px">批量操作</el-button>
        </div>
        <div style="margin-left:150px;">
            <el-table style="width: 100%" :data="songdata" @row-click="handleRowChange" @selection-change="handleSelectionChange">
                <el-table-column width="50" type="selection"></el-table-column>
                <el-table-column prop="name" label="歌曲名" width="440" ></el-table-column>
                <el-table-column prop="singer_name"  label="艺人" width="430" ></el-table-column>
                <el-table-column prop="introduction" label="专辑" width="330"></el-table-column>
            </el-table>
        </div>
    </div>
    <div v-show="show2" style="margin-left:150px;">
        <el-table style="width: 100%" :data="songdata1" @row-click="handleRowChange1">
            <el-table-column prop="pic" width="100" >
                <template #default="scope">
                    <img :src="geturl(scope.row.pic)" width="80" height="80">
                </template>
            </el-table-column>
            <el-table-column prop="title" width="440" ></el-table-column>
            <el-table-column prop="username"  label="创建人" width="430" ></el-table-column>
            <el-table-column prop="play_sum" label="播放量" width="330"></el-table-column>
        </el-table>
    </div>
    <el-dialog title="创建新歌单" v-model="disvivbile" width="400px" center>
        <div>
            <el-input v-model="textarea1"
                maxlength="100"
                placeholder="请输入歌单主题"
                show-word-limit
                type="textarea"
                style="margin-top:20px;"
                >
            </el-input>
        </div>
        <div style="margin-top:20px;">
            <el-button type="primary"  :loading="loadingbut">确定</el-button>
            <el-button type="danger" @click="disvivbile=false">取消</el-button>
        </div>
    </el-dialog> 

    <div v-show="show3" style="margin-left:130px;margin-top:20px;">
        <div class="demo-image1">
            <div class="block1" v-for="item in this.mvdata" :key="item">
                <img @click="playMv(item.url,item)" :src="geturl(item.pic)" style="border-radius: 15px;" width="250" height="150">
                <el-tooltip :content="item.introduce" placement="bottom" effect="light">
                    <span style="margin-left:10px;">{{item.name}}</span>
                </el-tooltip>
                <span>播放量 {{item.play_sum}}</span>
            </div>
        </div>
    </div>
</template>
<script>
import { VideoPlay,Share} from "@element-plus/icons"
export default{
    data(){
        return{
            songdata:[{}],
            songdata1:[{}],
            mvdata:[{}],
            show1:true,
            show2:false,
            show3:false,
            multipleSelection:[],
            disvivbile:false,
            textarea1:'',
            song_listsum:0,
            songsum:0,
            mvsum:0,
        }
    },
    components:{
        VideoPlay,
        Share,
    },
    created: function () {
        this.handel()
    },
    methods:{
        addSong_list(){
            this.disvivbile=true
        },
        //将选中的添加到播放列表
        playSelect(){
            this.$store.commit("getSong_name",this.multipleSelection[0].name)
            this.$store.commit("getSong_pic",this.multipleSelection[0].pic)
            this.$store.commit("getSong_url",this.multipleSelection[0].url)
            this.$store.commit("getLyric",this.multipleSelection[0].lyric)
            this.$store.commit("getSongid",this.multipleSelection[0].id)
            this.$store.commit("setUrllist",this.multipleSelection)
            this.$store.commit("getList_id",0)
        },
        //将选中的下载
        download(){
            if(this.multipleSelection.length===0){
                ElNotification({
                    title: 'Error',
                    message: '还未选择要下载的歌曲',
                    type: 'error',
                    })
                
            }else{
                for(let item of this.multipleSelection){
                    this.dow(item.url)
                }
            }
      },
        dow(val){
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
        handleSelectionChange(val){
            this.multipleSelection=val
        },
        geturl(url){
            let url1="http://localhost:8081/zf/"+url
            return url1
        },
        song(){
            this.show1=true
            this.show2=false
            this.show3=false
            document.getElementById("b1").style.color = 'rgb(0, 174, 255)'
            document.getElementById("b2").style.color = 'rgb(0, 0, 0)'
            document.getElementById("b4").style.color = 'rgb(0, 0, 0)'
        },
        song_list(){
            this.show1=false
            this.show2=true
            this.show3=false
            document.getElementById("b2").style.color = 'rgb(0, 174, 255)'
            document.getElementById("b1").style.color = 'rgb(0, 0, 0)'
            document.getElementById("b4").style.color = 'rgb(0, 0, 0)'
        },
        mv(){
            this.show1=false
            this.show2=false
            this.show3=true
            document.getElementById("b4").style.color = 'rgb(0, 174, 255)'
            document.getElementById("b1").style.color = 'rgb(0, 0, 0)'
            document.getElementById("b2").style.color = 'rgb(0, 0, 0)'
        },
        playMv(val1,val2){
        this.$store.commit("getVideourl",val1)
        this.$store.commit("getvideodetail",val2)
        this.$router.push({path: '/mv'})

        },
        
        handel(){
            let id = this.$store.state.userid1
            this.$axios
            .get('/getMySong?id=' + id)
            .then(successResponse=>{
                this.songdata=successResponse.data.song
                this.songdata1=successResponse.data.song_list
                this.songsum=successResponse.data.songsum
                this.song_listsum=successResponse.data.song_listsum
                this.mvdata=successResponse.data.mv
                this.mvsum=successResponse.data.mv_sum
            })
            .catch(failResponse => {
                this.$alert(failResponse.response.status)
            })
        },
        playall(){
            this.$store.commit("getSong_name",this.songdata[0].name)
            this.$store.commit("getSong_pic",this.songdata[0].pic)
            this.$store.commit("getSong_url",this.songdata[0].url)
            this.$store.commit("getLyric",this.songdata[0].lyric)
            this.$store.commit("getSongid",this.songdata[0].id)
            this.$store.commit("setUrllist",this.songdata)
            this.$store.commit("getList_id",0)
        },
        handleRowChange1(row){
            this.$store.commit('addSong_listId',row.id)
            this.$router.push({path: '/singerdetail'})
        },
        handleRowChange(row){
            let a = row.name
            let b = row.pic
            let c = row.url
            this.$store.commit("getSong_name",a)
            this.$store.commit("getSong_pic",b)
            this.$store.commit("getSong_url",c)
            this.$store.commit("getSongid",row.id)
            this.$store.commit("setUrllist",this.songdata)
            this.$store.commit("getLyric",row.lyric)
            
        },
    }
}
</script>

<style scoped>
span{
    margin-left:50px;
}
span:hover{
    color: rgb(0, 183, 255) !important;
    cursor:pointer;
}

.demo-image{
    background-color: #475669;
}
.block1{
  margin-left:10px;
  margin-top:10px;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 25%;
  box-sizing: border-box;
  vertical-align: top;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  border-radius: 15px;
}
.demo-image1{
    width: 1100px;
}
.block1{
    width:250px;
}
.block1:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    /* background-color: #e2f4ff; */
    /* opacity:0.5; */
    cursor:pointer;
    color: rgb(20, 142, 241);
}
</style>
