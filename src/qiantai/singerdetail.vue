<template>
   <div class="all" :data="song_listData">
    <div>
        <img class="img1" :src="geturl(song_listData[0].pic)">
        <div class="all-child1" style="width:700px">
            <span style="font-size:23px;font-weight: bold;">{{song_listData[0].title}}</span>
            <div style="position:absolute">
                <p>用户：{{song_listData[0].username}}</p>
                <p>标签：{{song_listData[0].style}}</p>
                <p>播放量：{{song_listData[0].play_sum}}</p>
                <div>
                <el-button @click="playSelect()" type="primary" style="width:100px;margin-top:10px;">播放选择</el-button>
                <el-button @click="shoucang21(song_listData[0].id)">收藏</el-button>
                </div>
            </div>
        </div>
    </div>
    <div >
        <div class="all-child2">
            <div style="display: inline-block;width:300px;position: absolute;">
                <p style="font-weight: bold;font-size:18px;">简介：</p>
                <p style="font-size:14px;color: rgb(172, 172, 172);">{{song_listData[0].introduction}}</p>
            </div>
            <div class="all-child2-a1">
                <el-divider />
                <el-table style="width: 100%" :data="songdata" @row-click="handleRowChange" @selection-change="handleSelectionChange">
                    <el-table-column type="selection"  width="50px">
                    </el-table-column>
                    <el-table-column  width="50px" label="全选">
                        <template #default="scope">
                            <div>{{xuhao(scope.row)}}</div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="name" label="歌曲名" width="240" ></el-table-column>
                    <el-table-column prop="singer_name"  label="艺人" width="230" ></el-table-column>
                    <el-table-column prop="introduction" label="专辑" width="230"></el-table-column>
                    <el-table-column width="70px">
                        <template #default="scope">
                        <el-icon @click.native="download(scope.row.url)" class="table-image"><Download/></el-icon>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="margin-top:30px;">
                    <div>
                        <span style="font-size:22px">评论</span>
                        <span style="margin-left:10px;color:rgb(182, 182, 182);">共有{{this.sum}}评论</span><br>
                        <el-input v-model="textarea"
                            maxlength="300"
                            placeholder="说说你的看法吧"
                            show-word-limit
                            type="textarea"
                            style="margin-top:20px;"
                            >
                        </el-input>
                        <el-button @click="addcomment(false)" type="primary" style="margin-top:20px;margin-left:900px">发表评论</el-button>
                    </div>
                    <div>
                        <span>全部评论</span>
                        <el-divider />
                        <div class="comment">
                            <div v-for="item in this.$store.state.comment" :key="item">
                                <div style="margin-top:20px" v-if="!item.type">
                                    <div class="ava">
                                        <el-avatar @click="gohome(item.user_id)" :src="geturl(item.avator)" class="ava1"></el-avatar>
                                    </div>
                                    <div style="display: inline-block;margin-left:60px;position: relative;">
                                        <span style="margin-top:0px;color:rgb(182, 182, 182);">{{item.username}}</span><br>
                                        <span style="color:rgb(182, 182, 182);">{{item.data}}</span>&nbsp&nbsp
                                        <span style="color:rgb(182, 182, 182);">来自 {{item.location}}</span><br>
                                        <span style="word-wrap:break-word;">{{item.content}}</span><br>
                                        <span>{{}}</span>&nbsp&nbsp&nbsp<span class="span11" @click="huifu(item)">{{this.huifu1}}</span>
                                    </div>
                                        <div v-show="item.show1" style="margin-left:60px;">
                                            <el-input v-model="textarea1"
                                                maxlength="300"
                                                placeholder="说说你的看法吧"
                                                show-word-limit
                                                type="textarea"
                                                style="margin-top:20px;"
                                                >
                                            </el-input>
                                            <el-button @click="huifuComment(true,item,0)" type="primary" style="margin-top:20px;margin-left:840px">发表评论</el-button>
                                        </div>
                                        <div style="margin-top:10px;margin-left:60px;" class="aaaa">
                                            <el-collapse v-model="activeName" accordion>
                                                <el-collapse-item :title="addcommentsum(item.id)" name="1">
                                                    <div style="margin-top:20px" v-for="item1 in this.$store.state.comment" :key="item1">
                                                        <div v-if="item.id==item1.up">
                                                            <div class="ava">
                                                                <el-avatar @click="gohome(item1.user_id)" :src="geturl(item1.avator)" class="ava2"></el-avatar>
                                                            </div>
                                                            <div style="display: inline-block;margin-left:60px;position: relative;">
                                                                <span style="margin-top:0px;color:rgb(182, 182, 182);">{{item1.username}}</span>&nbsp
                                                                <span style="margin-top:0px;color:rgb(182, 182, 182);" v-if="item1.up_user!=0">-> {{getup_user(item1.up_user)}}</span>
                                                                <br>
                                                                <span style="color:rgb(182, 182, 182);">{{item1.data}}</span>&nbsp&nbsp
                                                                <span style="color:rgb(182, 182, 182);">来自 {{item1.location}}</span><br>
                                                                <span style="word-wrap:break-word;">{{item1.content}}</span><br>
                                                                <span>{{}}</span>&nbsp&nbsp&nbsp<span class="span11" @click="huifu(item1)">{{this.huifu1}}</span>
                                                            </div>
                                                                <div v-show="item1.show1" style="margin-left:60px;">
                                                                    <el-input v-model="textarea1"
                                                                        maxlength="300"
                                                                        placeholder="说说你的看法吧"
                                                                        show-word-limit
                                                                        type="textarea"
                                                                        style="margin-top:20px;"
                                                                        >
                                                                    </el-input>
                                                                    <el-button @click="huifuComment(true,item,item1.id)" type="primary" style="margin-top:20px;margin-left:780px">发表评论</el-button>
                                                                </div>
                                                        </div>
                                                    </div>
                                                </el-collapse-item>
                                            </el-collapse>
                                            </div>
                                </div>
                            </div>
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
import { ElNotification } from 'element-plus'
export default{
    components:{
        Download,
    },
    data(){
        return{
            texts:[1,2,3,4,5,6,7,8,9,10],
            song_listData:[{}],
            songdata:[{}],
            auroid1:0,
            i:0,
            multipleSelection:[],
            textarea:'',
            textarea1:'',
            show1:false,
            huifu1:'回复',
            comment:{avator:'',user_id:0,username:'',data:'',content:'',type:false,up:0,up_user:0},
            sum:0,
        }
    },
    created: function(){
        let id = this.$store.state.song_listId
        this.lead1(id)
        this.getsong_listComment(id)
    },
    methods:{
        gohome(id){
            this.$store.commit("getUserid1",id)
            this.$router.push({path: '/mysong'})
        },
        addcommentsum(id){
            let i=0
            for(let item of this.$store.state.comment){
                if(item.up===id){
                    i++
                }
            }
            return '查看'+i+'条'+'回复'
        },
        getup_user(id){
            if(id!=0){
                for(let item of this.$store.state.comment){
                    if(item.id===id){
                        return item.username
                    }
                }

            }

        },
        shoucang21(id2){
            if(this.$store.state.userid === null){
                ElNotification({
                title: 'Error',
                message: '请先登录',
                type: 'error',
                })
                this.$router.push({path: '/userlogin'})
            }
            else{
                this.$axios
                .get("/Song_listShouCang?id1="+this.$store.state.userid+"&&id2="+id2)
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
                            message: '歌单已收藏',
                            type: 'error',
                            })
                    }
                    
                    
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
            }
        },
        huifuComment(i,val,i1){
            if(this.$store.state.userid === null){
                ElNotification({
                title: 'Error',
                message: '请先登录',
                type: 'error',
                })
            }
            else{
                let type=i
                let now = new Date()
                let year = now.getFullYear()
                let month = now.getMonth()+1
                let day = now.getDate()
                let date = year+'-'+month+'-'+day
                this.comment.avator=this.$store.state.userdetail.avator
                this.comment.username=this.$store.state.userdetail.username
                this.comment.username=this.$store.state.userdetail.username
                // this.comment.user_id=this.$store.state.userdetail.id
                this.comment.data=date
                this.comment.content=this.textarea1
                this.comment.type=type
                this.comment.up=val.id
                this.comment.up_user=i1
                this.$store.commit("insertComment",this.comment)
                // alert(i1)
                this.$axios
                .get("/addHuifuComment?user_id="+this.$store.state.userid+'&&song_list_id='+this.$store.state.song_listId+'&&content='+this.textarea1+'&&type='+type+'&&up='+val.id+'&&up_user='+i1)
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
                this.textarea1=''
                // this.getsong_listComment(this.$store.state.song_listId)
                val.show!=val.show1
            }
        },
        addcomment(val){
            if(this.$store.state.userid === null){
                ElNotification({
                title: 'Error',
                message: '请先登录',
                type: 'error',
                })
            }
            else{
                let type=val
                let now = new Date()
                let year = now.getFullYear()
                let month = now.getMonth()+1
                let day = now.getDate()
                let date = year+'-'+month+'-'+day
                this.comment.avator=this.$store.state.userdetail.avator
                this.comment.username=this.$store.state.userdetail.username
                this.comment.data=date
                this.comment.content=this.textarea
                this.comment.type=type
                this.$store.commit("insertComment",this.comment)
                this.$axios
                .get("/addComment?user_id="+this.$store.state.userid+'&&song_list_id='+this.$store.state.song_listId+'&&content='+this.textarea+'&&type='+type)
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
                this.textarea=''
                // this.getsong_listComment(this.$store.state.song_listId)
            }
        },
        getsong_listComment(id){
            this.$axios
            .get("/getSong_listComment?id="+id)
            .then(successResponse=>{
                this.$store.commit("getComment",successResponse.data.song_list)
                this.sum=successResponse.data.song_list.length

            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        geturl(val){
            return "http://localhost:8081/zf/"+val
        },
        huifu(val){
            val.show1=!val.show1
            if(val.show1){
                this.huifu1='取消'
            }
            else{
                this.huifu1='回复'
            }
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
        xuhao(cal){
            let i = this.songdata.indexOf(cal)
            return i+1
        },
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        handleRowChange(row){
            let a = row.name
            let b = row.pic
            let c = row.url
            this.$store.commit("getSong_name",a)
            this.$store.commit("getSong_pic",b)
            this.$store.commit("getSong_url",c)
            this.$store.commit("getSongid",row.id)
            // this.$store.commit("setUrllist",this.songdata)
            this.$store.commit("getLyric",row.lyric)
            this.$store.commit("insertUrllist",row)
            for(let i=0;i<this.songdata.length;i++){
                if(this.songdata.songid === this.songdata[i].id){
                this.$store.commit("getList_id",i)
                }
            }
            
        },
        lead1(id){
            this.$axios
            .get('/getSong_list_to_qianttai?id='+id)
            .then(successResponse=>{
                this.song_listData=successResponse.data.song_list
                this.songdata=successResponse.data.song
                // this.$store.commit("getComment",successResponse.data.comment)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
    
}
</script>

<style scoped>
.span11{
    color:rgb(182, 182, 182)
}
.span11:hover{
    color:rgb(0, 179, 255);
    cursor:pointer;
}
.ava{
    display: inline-block;
    position: relative;  
}
.aaaa{
    background-color: rgb(174, 174, 174);
}
.ava1{
    position: absolute;
    margin-top: -76px;
}
/* .comment{
    height: 400px;
    overflow-y: scroll;
} */
.ava2{
    position: absolute;
    margin-top: -80px;
}
/* .comment::-webkit-scrollbar{
    display: none;
} */
.table-image:hover{
    color:aqua;
    cursor:pointer;
}
.all-child2-a1{
    display: inline-block;
    /* position: absolute; */
    /* background-color: rgb(254, 249, 249); */
    width:1000px;
    /* margin-left: 50px; */
    border-radius: 10px;
    margin-left: 310px;
}
.all-child2{
    display: inline-block;
    margin-left:50px;
    margin-top:20px;
    
}
.all-child1{
    display: inline-block;
    margin-left:50px;
    margin-top:70px;
    position: absolute;
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
    /* background-image: linear-gradient(to bottom, rgba(95, 220, 251, 0.766) 20%, rgb(228, 228, 228) 20%); */
    height: 100%;
    position: relative;
}   
</style>