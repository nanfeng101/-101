<template>
<div class="body">
    <video style="margin-left:200px;margin-top:70px" ref="video" width="1120" height="630" controls>
        <source :src="this.$store.state.videourl" type="video/mp4">
        <source src="movie.ogg" type="video/ogg">
    </video>
    <div style="margin-top:10px;">
        <span class="a1" style="margin-left:200px;color:#fff;">{{this.$store.state.videodetail.introduce}}</span>
        <span style="margin-left:10px;color:#fff;">播放量  {{this.$store.state.videodetail.play_sum}}</span>
        <el-icon class="icon1" @click="mvshoucang()"><Star/></el-icon>
    </div>
</div>
<div style="margin-top:30px;margin-left:200px;width:1120px;">
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
                        <el-button @click="addcomment(false)" type="primary" style="margin-top:20px;margin-left:1020px">发表评论</el-button>
                    </div>
                    <div>
                        <span>全部评论</span>
                        <el-divider />
                        <div class="comment">
                            <div v-for="item in this.$store.state.comment" :key="item">
                                <div style="margin-top:20px" v-if="!item.type">
                                    <div class="ava">
                                        <el-avatar :src="geturl(item.avator)" class="ava1"></el-avatar>
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
                                            <el-button @click="huifuComment(true,item,0)" type="primary" style="margin-top:20px;margin-left:960px">发表评论</el-button>
                                        </div>
                                        <div style="margin-top:10px;margin-left:60px;">
                                            <el-collapse v-model="activeName" accordion>
                                                <el-collapse-item :title="addcommentsum(item.id)" name="1">
                                                    <div style="margin-top:20px;" v-for="item1 in this.$store.state.comment" :key="item1">
                                                        <div v-if="item.id==item1.up">
                                                            <div class="ava">
                                                                <el-avatar :src="geturl(item1.avator)" class="ava2"></el-avatar>
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
                                                                <el-button @click="huifuComment(true,item,item1.id)" type="primary" style="margin-top:20px;margin-left:900px">发表评论</el-button>
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
</template>
<script>
import { ElNotification } from 'element-plus'
import {Star} from "@element-plus/icons"
export default{
    name:'mv',
    created: function(){
        // this.$store.commit("setIsmenu",false)
        this.getsong_listComment()
    },
    data(){
        return{
            textarea:'',
            textarea1:'',
            show1:false,
            huifu1:'回复',
            comment:{avator:'',username:'',data:'',content:'',type:false,up:0,up_user:0},
            sum:0,
            name:'',
        }
    },
    components:{
        Star,
    },
    methods:{
        getup_user(id){
            if(id!=0){
                for(let item of this.$store.state.comment){
                    if(item.id===id){
                        return item.username
                    }
                }

            }

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
                this.comment.data=date
                this.comment.content=this.textarea1
                this.comment.type=type
                this.comment.up=val.id
                this.comment.up_user=i1
                this.$store.commit("insertComment",this.comment)
                this.$axios
                .get("/addMvHuifuComment?user_id="+this.$store.state.userid+'&&song_list_id='+this.$store.state.videodetail.id1+'&&content='+this.textarea1+'&&type='+type+'&&up='+val.id+'&&up_user'+i1)
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
                this.textarea1=''
                // this.getsong_listComment(this.$store.state.song_listId)
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
                .get("/addMvComment?user_id="+this.$store.state.userid+'&&song_list_id='+this.$store.state.videodetail.id1+'&&content='+this.textarea+'&&type='+type)
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
                this.textarea=''
                // this.getsong_listComment(this.$store.state.song_listId)
            }
        },
        getsong_listComment(){
            let id=this.$store.state.videodetail.id1
            this.$axios
            .get("/getMvComment?id="+id)
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
        mvshoucang(){
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
                .get("/MvShouCang?id1="+this.$store.state.userid+"&&id2="+this.$store.state.videodetail.id1)
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
                            message: 'mv已收藏',
                            type: 'error',
                            })
                    }
                    
                    
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
            }
        }
    }
}
</script>
<style scoped>
.icon1{
    font-size: 22px;
    padding-top:3px;
    position: absolute;
    color: rgb(244, 247, 250);
    margin-left:610px;
}
.icon1:hover{
    color: rgb(0, 147, 245);
    cursor: pointer;
}
.a1{
    font-size:20px;
}
.body{
    height: 750px;
    background-color: #404040FF;
}
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
.ava1{
    position: absolute;
    margin-top: -77px;
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
