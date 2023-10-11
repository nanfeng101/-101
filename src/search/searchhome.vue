<template>
<div style="height:800px;">
<div style="padding-top:100px;">
    <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    text-color=#000000
    @select="handleSelect"
    active-text-color=#02AAFFFF
  >
    <el-menu-item @click="getcon('4')"  index="1" style="margin-left:300px;text-decoration: none;font-size:18px">单曲</el-menu-item>
    <el-menu-item @click="getcon('5')" index="2" style="margin-left:50px;font-size:18px" >歌单</el-menu-item>
    <el-menu-item @click="getcon('6')" index="3" style="margin-left:50px;font-size:18px" >MV</el-menu-item>
    <el-menu-item @click="getcon('7')" index="4" style="margin-left:50px;font-size:18px" >歌手</el-menu-item>
    <el-menu-item @click="getcon('8')" index="5" style="margin-left:50px;font-size:18px" >用户</el-menu-item>    
  </el-menu>
</div>
  <div style="margin-left:260px;">
    <div class="table" v-show="show1">
        <span class="table-p1">搜索到"</span>
        <span class="table-p1" style="color:rgb(16, 174, 253);">{{this.$store.state.searchvalue}}</span>
        <span class="table-p1">"相关歌曲</span>
        <el-button @click="playSelect()" type="primary" style="margin-left:700px;width:100px;">播放选择</el-button>
        <el-divider class="table-divider"></el-divider>
        <div>
            <el-table :data="tableData" size="mini" @row-click="handleRowChange" @selection-change="handleSelectionChange">
                <el-table-column type="selection"  width="50px"></el-table-column>
                <el-table-column class="column1" label="歌曲名">
                    <template #default="scope" width="260px">
                        <span style="font-size:15px">{{getsongname(scope.row.name,scope.row.singer_name)}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="play_sum" label="播放量" width="100px">
                </el-table-column>
                <el-table-column width="70px">
                    <template #default="scope">
                    <el-icon @click.native="download(scope.row.url)" class="table-image"><Download/></el-icon>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
    <div class="table" v-show="show2">
        <span class="table-p1">搜索到"</span>
        <span class="table-p1" style="color:rgb(16, 174, 253);">{{this.$store.state.searchvalue}}</span>
        <span class="table-p1">"相关歌单</span>
        <el-divider class="table-divider"></el-divider>
        <el-table :data="tableData2" size="mini" @row-click="handleRowChange1">
            <el-table-column label="图片"  width="100px">
                <template #default="scope">
                    <img :src="geturl(scope.row.pic)" width="80" height="70">
                </template>
            </el-table-column>
            <el-table-column class="column1" prop="title" label="歌单名">
            </el-table-column>
        </el-table>
    </div>
    <div style="display: inline-block;position: absolute;margin-left:20px;" v-show="show3">
        <span class="table-p1">搜索到"</span>
        <span class="table-p1" style="color:rgb(16, 174, 253);">{{this.$store.state.searchvalue}}</span>
        <span class="table-p1">"相关MV</span>
        <el-divider class="table-divider"></el-divider>
        <div class="demo-image1">
            <div class="block1" v-for="item in tableData3" :key="item">
                <img @click="playMv(item.url,item.introduce)" :src="geturl(item.pic)" style="border-radius: 15px;" width="150" height="80">
                <el-tooltip :content="item.introduce" placement="bottom" effect="light">
                    <span style="marfin-left:6px;">{{item.name}}</span>
                </el-tooltip>
            </div>
        </div>
    </div>

    <div style="display: inline-block;position: absolute;margin-left:20px;" v-show="show4">
        <span class="table-p1">搜索到"</span>
        <span class="table-p1" style="color:rgb(16, 174, 253);">{{this.$store.state.searchvalue}}</span>
        <span class="table-p1">"相关歌手</span>
        <el-divider class="table-divider"></el-divider>
        <div class="demo-image1">
            <div class="block2" v-for="item in tableData4" :key="item">
                <div @click="singerReplace(item.id)">
                    <img class="img2" :src="geturl(item.pic)" width="100" height="100">
                    <div style="text-align:center;display:flex;justify-content:center;margin-top:20px;">
                    <span>{{item.name}}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div style="display: inline-block;position: absolute;margin-left:20px;" v-show="show5">
        <span class="table-p1">搜索到"</span>
        <span class="table-p1" style="color:rgb(16, 174, 253);">{{this.$store.state.searchvalue}}</span>
        <span class="table-p1">"相关用户</span>
        <el-divider class="table-divider"></el-divider>
        <div class="demo-image1">
            <div class="block2" v-for="item in tableData5" :key="item">
                <div @click="gohome(item.id)">
                    <img class="img2" :src="geturl(item.avator)" width="100" height="100">
                    <div style="text-align:center;display:flex;justify-content:center;margin-top:20px;">
                    <span>{{item.username}}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </div>
</div>
</template>
<script>
import {Download} from "@element-plus/icons"
export default{
    name:'searchhome',
    data(){
        return{
            tableData:[{}],
            tableData2:[{}],
            tableData3:[{}],
            tableData4:[{}],
            tableData5:[{}],
            multipleSelection: [],
            show1:true,
            show2:false,
            show3:false,
            show4:false,
            show5:false,
            currentCom:'',
        }
    },
    components:{
        Download,
    },
    created: function(){
        this.getSong1()
        this.getSong_list1()
        this.getmv1()
        this.getuser1()
        this.getsinger1()
    },
    computed:{
        getsearchvalue(){
            return this.$store.state.searchvalue
        }
    },
    watch:{
        getsearchvalue(){
            this.getSong1()
            this.getSong_list1()
            this.getmv1()
            this.getuser1()
            this.getsinger1()
        }
    },
    methods:{
        singerReplace(id1){
        this.$store.commit('addSong_listId', id1)
        this.$router.push({path: '/songdetail'})
        },
        gohome(id){
            this.$store.commit("getUserid1",id)
            this.$router.push({path: '/mysong'})
        },
        getsinger1(){
            let val = this.$store.state.searchvalue
             this.$axios
            .get('/selectSinger?name=' + val)
            .then(successResponse=>{
                this.tableData4=successResponse.data.singer
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
        getuser1(){
            let val = this.$store.state.searchvalue
             this.$axios
            .get('/selectConsumer?name=' + val)
            .then(successResponse=>{
                this.tableData5=successResponse.data.consumer
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
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
        playMv(val1,val2){
        this.$store.commit("getVideourl",val1)
        this.$store.commit("getVideoint",val2)
        this.$router.push({path: '/mv'})

        },
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        getcon(val){
            if(val === '4'){
                this.show1=true
                this.show2=false
                this.show3=false
                this.show4=false
                this.show5=false
            }
            else if(val === '5'){
                this.show1=false
                this.show2=true
                this.show3=false
                this.show4=false
                this.show5=false
            }
            else if(val === '6'){
                this.show1=false
                this.show2=false
                this.show3=true
                this.show4=false
                this.show5=false
            }
            else if(val === '7'){
                this.show1=false
                this.show2=false
                this.show3=false
                this.show4=true
                this.show5=false
            }
            else{
                this.show1=false
                this.show2=false
                this.show3=false
                this.show4=false
                this.show5=true
            }
        },
        getsongname(name,singer_name){
            let val = name+" - "+singer_name
            return val
        },
        playSelect(){
            this.$store.commit("getSong_name",this.multipleSelection[0].name)
            this.$store.commit("getSong_pic",this.multipleSelection[0].pic)
            this.$store.commit("getSong_url",this.multipleSelection[0].url)
            this.$store.commit("getLyric",this.multipleSelection[0].lyric)
            this.$store.commit("getSongid",this.multipleSelection[0].id)
            this.$store.commit("setUrllist",this.multipleSelection)
            this.$store.commit("getList_id",0)
        },
        handleSelectionChange(val){
            this.multipleSelection=val
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
        handleRowChange1(row){
            this.$store.commit('addSong_listId', row.id)
            this.$router.push({path: '/singerdetail'})
        },
        getSong1(){
            let val = this.$store.state.searchvalue
             this.$axios
            .get('/classifysong1?name=' + val)
            .then(successResponse=>{
                this.tableData=successResponse.data
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
        getSong_list1(){
            let val = this.$store.state.searchvalue
             this.$axios
            .get('/selectTitleSong_list?title=' + val)
            .then(successResponse=>{
                this.tableData2=successResponse.data.song_list
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
        getmv1(){
            let val = this.$store.state.searchvalue
             this.$axios
            .get('/classifymv?name=' + val)
            .then(successResponse=>{
                this.tableData3=successResponse.data
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
    }
}
</script>
<style scoped>
.table-image:hover{
    color:rgb(16, 174, 253);
    cursor:pointer;
}
.img2{
    border-radius: 50%;
    margin-left:50px;
    margin-top:20px;
}
.table-p1{
    margin-top: 25px;
    font-size: 18px;
    color: rgb(36, 36, 36);
    display: inline-block;
}
.table{
    display: inline-block;
    position: absolute;
    width:65%;
    margin-left: 20px;
}
.demo-image{
    background-color: #475669;
}
.block1{
  margin-left:10px;
  margin-top:10px;
  /* text-align: center; */
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 20%;
  box-sizing: border-box;
  vertical-align: top;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  border-radius: 15px;
  width:150px;
}
.block2{
  margin-left:10px;
  margin-top:10px;
  /* text-align: center; */
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 20%;
  box-sizing: border-box;
  vertical-align: top;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  border-radius: 15px;
  width:200px;
  height: 250px;
  background-color: rgb(242, 255, 255);
}
.block2:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    /* background-color: #e2f4ff; */
    /* opacity:0.5; */
    cursor:pointer;
    color: rgb(20, 142, 241);
}
.demo-image1{
    width: 800px;
}
.block1:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    /* background-color: #e2f4ff; */
    /* opacity:0.5; */
    cursor:pointer;
    color: rgb(20, 142, 241);
}
</style>
