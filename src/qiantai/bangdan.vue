<template>
    <div >
        <div class="demo">
            <!-- <p class="demo-span">热门榜单</p> -->
            <el-collapse v-model="activeName" accordion >
                <el-collapse-item style="padding-left:10px;font-size:15px;" title="热门榜单" name="1">
                    <div @click="getsong('酷狗飙升榜')" class="demo-child1">
                        <img src="/img/榜1.jpg" class="img1" width="15" height="15">
                        <sapn class="span1">酷狗飙升榜</sapn>
                    </div>
                    <div @click="getsong2('酷狗TOP500')" class="demo-child1">
                        <img src="/img/榜3.jpg" class="img1" width="15" height="15">
                        <span class="span1">酷狗TOP500</span>
                    </div>
                    <div @click="getsong('抖音热歌榜')" class="demo-child1">
                        <img src="/img/榜2.jpg" class="img1" width="15" height="15">
                        <span class="span1">抖音热歌榜</span>
                    </div>
                </el-collapse-item>
                <el-collapse-item style="padding-left:10px;font-size:15px;" title="特色音乐榜单" name="2">
                    <div @click="getsong('酷狗飙升榜')" class="demo-child1">
                        <!-- <img src="/img/榜1.jpg" class="img1" width="15" height="15"> -->
                        <sapn class="span1">ACG新歌榜</sapn>
                    </div>
                    <div @click="getsong('酷狗TOP500')" class="demo-child1">
                        <!-- <img src="/img/榜3.jpg" class="img1" width="15" height="15"> -->
                        <span class="span1">电音热歌榜</span>
                    </div>
                    <div @click="getsong('抖音热歌榜')" class="demo-child1">
                        <!-- <img src="/img/榜2.jpg" class="img1" width="15" height="15"> -->
                        <span class="span1">综艺新歌榜</span>
                    </div>
                </el-collapse-item>
            </el-collapse>
        </div>
        <div class="table">
            <span class="table-p1">{{bangname}}</span>
            <el-button @click="playSelect()" type="primary" style="margin-left:500px;width:100px">播放选择</el-button>
            <el-divider class="table-divider"></el-divider>
            <div>
                <el-table :data="tableData" size="mini" @row-click="handleRowChange" @selection-change="handleSelectionChange">
                    <el-table-column type="selection"  width="50px"></el-table-column>
                    <el-table-column prop="sort_id" width="50px" label="全选"></el-table-column>
                    <el-table-column class="column1">
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
            <div class="pagination" style="margin-top:30px;" v-show="show">
                <div class="pagination">
                    <el-pagination background layout="total, prev, pager, next"
                    :total="total1" 
                    v-model:currentPage="currentPage" 
                    @current-change="handleCurrentChange"
                    :page-size="pageSize">
                    </el-pagination>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import {Download} from "@element-plus/icons"
// import { t } from 'element-plus/lib/locale'
export default{
    name:'bangdan',
    data(){
        return{
            tableData:[{}],
            bangname:'酷狗飙升榜',
            multipleSelection: [],
            pageSize:15,
            currentPage:1,
            total1:0,
            show:false,

        }
    },
    created: function(){
        this.getsong(this.bangname)
        // this.getsong2(this.bangname)
    },
    components:{
        Download,
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
            this.$store.commit("insertUrllist",row)
            for(let i=0;i<this.$store.state.urllist.length;i++){
                if(this.$store.state.songid === this.$store.state.urllist[i].id){
                this.$store.commit("getList_id",i)
                }
            }
        },
        getsongname(name,singer_name){
            let val = name+" - "+singer_name
            return val
        },
        handleCurrentChange(val) {
            this.currentPage = val
            this.getsong2('酷狗TOP500')
        },
        getsong2(val){
            this.bangname=val
            this.show=true
            this.$axios
            .get('/getTopSong?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
                this.tableData = successResponse.data.song
                this.total1 = successResponse.data.total1 
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getsong(val){
            this.bangname=val
            this.$axios
            .get('/getBangSong?bangname=' + val)
            .then(successResponse=>{
                this.tableData=successResponse.data
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
    color:aqua;
    cursor:pointer;
}
.table-p1{
    margin-top: 105px;
    font-size: 25px;
    color: rgb(36, 36, 36);
    display: inline-block;
}
.table{
    display: inline-block;
    position: absolute;
    width:50%;
    margin-left: 20px;
}
.demo{
    margin-top:100px;
    height: 750px;
    width: 200px;
    border: 1px solid;
    margin-left: 17%;
    border-color: rgb(197, 197, 197);
    display: inline-block;
    /* position: absolute; */
}
.img1{
    padding-top:4px;
    padding-left:10px;
    position: absolute;
}
.span1{
    padding-left:30px;
}
.demo-child1{
    padding-top: 5px;
    margin-top:10px;
    color: rgb(123, 124, 125);
    width: 200px;
    height: 30px;
}
.demo-child1:focus{
    background-color: rgb(20, 63, 218);
    color: rgb(255, 255, 255);
}
.demo-child1:hover{
    color: rgb(20, 129, 218);
    background-color: rgb(246, 252, 255);
    cursor:pointer;
}
.demo-span{
    margin-top:10px;
    margin-left:10px;
    color: rgb(98, 98, 97);
}
</style>
