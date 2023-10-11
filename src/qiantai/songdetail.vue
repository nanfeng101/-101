<template>
   <div class="all" :data="song_listData">
    <div>
        <img class="img1" :src="geturl(song_listData[0].pic)">
        <div class="all-child1" style="width:1000px">
            <p style="font-size:23px;margin-top:30px;">{{song_listData[0].name}}</p>
            <p style="font-weight: bold;font-size:18px;">生日：{{song_listData[0].birth1}}</p>
            <p style="font-weight: bold;font-size:18px;">故乡：{{song_listData[0].location}}</p>
            <el-button type="primary">播放全部</el-button>
            <el-button @click="attention()">关注</el-button>
            <div>
            </div>
        </div>
    </div>
    <div >
        <div class="all-child2">
            <div style="display: inline-block;width:300px">
                <p style="font-size:14px;color: rgb(172, 172, 172);">{{song_listData[0].introduction}}</p>
            </div>
            <div class="all-child2-a1">
                <p style="margin-left:500px;font-size:20px;font-weight: bold;">歌单</p>
                <el-divider />
                <el-table style="width: 100%" :data="songdata" @row-click="handleRowChange">
                    <el-table-column prop="name" label="歌曲名" width="240" ></el-table-column>
                    <el-table-column prop="singer_name"  label="艺人" width="230" ></el-table-column>
                    <el-table-column prop="introduction" label="专辑" width="230"></el-table-column>
                </el-table>
            </div>
        </div>
    </div>
   </div> 
</template>
<script>
export default{
    data(){
        return{
            song_listData:[{}],
            songdata:[{}],
            auroid1:0,
        }
    },
    created: function(){
        let id = this.$store.state.song_listId
        
        this.lead1(id)
    },
    methods:{
        attention(){
            alert(this.song_listData[0].id)
            this.$axios
            .get('/Attention_singer?id1='+this.$store.state.userid+'&&id2='+this.song_listData[0].id)
            // .then(successResponse=>{
            // })
            .then(successResponse=>{
                    if(successResponse.data === 'ok'){
                        ElNotification({
                            title: 'Success',
                            message: '收藏成功',
                            type: 'success',
                            })
                    }
                    else{
                        ElNotification({
                            title: 'Error',
                            message: '歌手已关注',
                            type: 'error',
                            })
                    }
                    
                    
                })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
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
            for(let i=0;i<this.songdata.length;i++){
                if(this.songdata.songid === this.songdata[i].id){
                this.$store.commit("getList_id",i)
                }
            }
            
        },
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        lead1(id){
            this.$axios
            .get('/getSong_to_qiantai?id='+id)
            .then(successResponse=>{
                this.song_listData=successResponse.data.singer
                this.songdata=successResponse.data.song
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
    
}
</script>

<style scoped>
.all-child2-a1{
    display: inline-block;
    position: absolute;
    background-color: rgb(254, 249, 249);
    width:1000px;
    margin-left: 50px;
    border-radius: 10px;
}
.all-child2{
    display: inline-block;
    margin-left:50px;
    margin-top:20px;
    
}
.all-child1{
    display: inline-block;
    margin-left:50px;
    margin-top:50px;
    position: absolute;
    font-weight: bold;
}
.img1{
    margin-top: 50px;
    margin-left: 50px;
    height: 250px;
    width: 250px;
    border: azure solid 5px;
    border-radius: 10%;
}
.all{
    background-image: linear-gradient(to bottom, rgba(95, 220, 251, 0.766) 10%, rgb(228, 228, 228) 20%);
    height: 1200px;
}   
</style>