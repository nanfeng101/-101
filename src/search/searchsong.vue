<template>
    <div class="table">
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
                    <el-table-column width="70px">
                        <el-icon class="table-image"><Download/></el-icon>
                    </el-table-column>
                </el-table>
            </div>
        </div>
</template>
<script>
import {Download} from "@element-plus/icons"
export default{
    data(){
        return{
            tableData:[{}],
            multipleSelection: [],
        }
    },
    components:{
        Download,
    },
    methods:{
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
        getSong(){

        }
    }

}
</script>
<style scoped>
.table-image:hover{
    color:rgb(16, 174, 253);
    cursor:pointer;
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
</style>
