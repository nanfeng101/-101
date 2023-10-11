<template>
    <div style="margin-left:150px;margin-top:20px;">
        <el-button style="width:130px">新建歌单</el-button>
        <el-button style="width:130px">导入歌单</el-button>
    </div>
    <div style="margin-left:150px;">
        <el-table style="width: 100%" :data="songdata" @row-click="handleRowChange1">
            <el-table-column prop="pic" width="100" >
                <template #default="scope">
                    <img :src="geturl(scope.row.pic)" width="80" height="80">
                </template>
            </el-table-column>
            <el-table-column prop="title" width="440" ></el-table-column>
            <el-table-column prop="style"  label="类型" width="430" ></el-table-column>
            <el-table-column prop="play_sum" label="播放量" width="330"></el-table-column>
        </el-table>
    </div>
</template>
<script>
export default{
    data(){
        return{
            songdata:[{}],
        }
    },
    created: function () {
        this.handel()
    },
    methods:{
        geturl(url){
            let url1="http://localhost:8081/zf/"+url
            return url1
        },
        handleRowChange1(row){
            this.$store.commit('addSong_listId',row.id)
            this.$router.push({path: '/singerdetail'})
        },
         handel(){
            let id = this.$store.state.userid1
            this.$axios
            .get('/getUserCreateSong_list?id=' + id)
            .then(successResponse=>{
                this.songdata=successResponse.data
            })
            .catch(failResponse => {
                this.$alert(failResponse.response.status)
            })
        },
    }
}
</script>
