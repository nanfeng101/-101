<template>
    <div class="head">
        <div>
            <div style="display: inline-block;position: absolute;margin-left:15px;margin-top:10px;">
                <img :src="geturl(this.$store.state.userhome.pic)" width="100" height="100" style="border-radius:50%;">
            </div>
            <div style="display: inline-block;margin-left:125px;">
                <div style="padding-top:15px;">
                    <span style="font-size:20px;">{{this.$store.state.userhome.username}}</span>&nbsp&nbsp
                    <el-icon v-if="this.$store.state.userhome.sex=='女'"><Female /></el-icon>
                    <el-icon v-else><Male /></el-icon>
                    <el-button @click="attention()" v-if="this.$store.state.userid1!=this.$store.state.userhome.id" style="margin-left:800px;">关注</el-button>
                    <el-button @click="chat()" v-if="this.$store.state.userid1!=this.$store.state.userhome.id" style="margin-left:15px;">私信</el-button>
                </div>
                <div style="">
                    <span style="font-size:20px;font-weight: bold;">{{this.viewsun}}</span>
                    <span>总访问量</span>
                    <span style="font-size:20px;font-weight: bold;margin-left:15px;">{{this.textsum}}</span>
                    <span>文章数</span>
                    <span style="font-size:20px;font-weight: bold;margin-left:15px;">0</span>
                    <span>粉丝</span>
                </div>
                <div style="padding-top:15px;color: rgb(147, 147, 147);">
                    <span>个人简介: {{this.$store.state.userhome.introduce}}</span>
                </div>
                <div style="padding-top:15px;color: rgb(147, 147, 147);">
                    <span>地区: {{this.$store.state.userhome.location}}</span>
                </div>
                <div style="padding-top:15px;color: rgb(147, 147, 147);">
                    <span>加入时间: {{this.$store.state.userhome.create_time1}}</span>
                </div>
            </div>
        </div>
    </div>
    <div style="margin-left:100px;position: absolute;margin-top:300px;">
        <el-container>
            <el-aside style="width:250px;margin-top:20px;">
                <el-card>
                    <span>发布问答</span>
                    <el-divider></el-divider>
                    <div v-for="item in userluntan" :key="item">
                        <div>
                            <p>{{item.content}}</p>
                        </div>
                        <div>
                            <span style="color: rgba(208, 208, 208, 0.912);">{{item.time}}</span>
                        </div>
                        <el-divider></el-divider>
                    </div>
                </el-card>
            </el-aside>
            <el-main>
                <el-card style="width:1020px;">
                <span id="p1" @click="classify(1)" style="color: coral;">按最后发布时间</span>
                <el-icon id="p1" style="color: coral;"><DCaret /></el-icon>
                <span id="p2" @click="classify(2)" style="margin-left:15px;">按观看量</span>
                <el-icon id="p2"><DCaret /></el-icon>
                <el-divider></el-divider>
                <div v-for="item in addtext" :key="item" >
                    <div @click="entrytetx(item)" v-if="item.iscover" style="margin-top:10px;height:100px;">
                        <div style="display: inline-block;">
                            <div style="display: inline-block;">
                                <img :src="geturl(item.pic)" width="150" height="90">
                            </div>
                            <div style="display: inline-block;width:600px;">
                                <span style="margin-left:20px;">{{item.biaoti}}</span>
                                <div style="margin-top:40px;margin-left:20px;">
                                    <span style="color:rgb(178, 178, 178);">{{item.create_time1}}</span>
                                    <span style="color:rgb(178, 178, 178);margin-left:20px;">阅读{{item.view_sum}}</span>
                                    <span style="margin-left:20px;color:rgb(178, 178, 178);">评论</span>
                                    <span style="margin-left:20px;color:rgb(178, 178, 178);">收藏</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div v-else>
                        <div @click="entrytetx(item)" style="display: inline-block;width:850px;">
                            <span style="margin-left:20px;">{{item.biaoti}}</span>
                            <div style="margin-top:40px;margin-left:20px;">
                                <span style="color:rgb(178, 178, 178);">{{item.create_time1}}</span>
                                <span style="color:rgb(178, 178, 178);margin-left:20px;">阅读{{item.view_sum}}</span>
                                <span style="margin-left:20px;color:rgb(178, 178, 178);">评论</span>
                                <span style="margin-left:20px;color:rgb(178, 178, 178);">收藏</span>
                            </div>
                        </div>
                    </div>
                    <el-divider></el-divider>
                </div>
            </el-card>
            </el-main>
        </el-container>
    </div>
    <iframe class="ifr" src="/html/xingkong.html"></iframe>
</template>
<script>
import { ElMessage } from 'element-plus'
import {DCaret,Female,Male} from "@element-plus/icons"
export default{
    data(){
        return{
            textsum:0,
            viewsun:0,
            fensisun:0,
            addtext:[{}],
            biaoqian1:[{}],
            userluntan:[{}],
        }
    },
    components:{
        DCaret,
        Female,
        Male,
    },
    created: function(){
        this.getAllText()
        this.handle()
        this.getUserIdLuntanText()
    },
    methods:{
        getUserIdLuntanText(){
            this.$axios
            .get('/getUserIdLuntanText?id='+this.$store.state.userhome.id)
            .then(successResponse => {
                this.userluntan=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        chat(){
            this.$axios
            .get('/selectUser_chat_user?id1='+this.$store.state.userid1+'&&id2='+this.$store.state.text.user_id)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
            this.$router.push({path: '/userchat'})
        },
        attention(){
            this.$axios
            .get('/addUserAttention?user_id='+this.$store.state.userid1+'&&attention_id='+this.$store.state.userhome.id)
            .then(successResponse => {
                if(successResponse.data=="ok"){
                    ElMessage({
                        showClose: true,
                        message: '关注成功',
                        type: 'success',
                    })
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '该用户已经关注',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        classify(val){
            if(val==1){
                document.getElementById("p1").style.color="coral"
                document.getElementById("p2").style.color="rgb(0, 0, 0)"
                this.selectDateText()
            }
            else{
                document.getElementById("p2").style.color="coral"
                document.getElementById("p1").style.color="rgb(0, 0, 0)"
                this.selectViewText()
            }
        },
        selectDateText(){
            this.$axios
            .get("/selectDateText?id="+this.$store.state.userhome.id)
            .then(successResponse => {
                this.addtext=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        selectViewText(){
            this.$axios
            .get("/selectViewText?id="+this.$store.state.userhome.id)
            .then(successResponse => {
                this.addtext=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        geturl(val){
            let url = 'http://124.220.16.147:8011/zf/'+val
            return url
        },
        addUserBrowse(val){
            var user_browse={}
            user_browse.user_id=this.$store.state.userid1
            user_browse.text_id=val
            this.$axios
            .post("/addUserBrowse",user_browse)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        entrytetx(item){
          this.$store.commit("getBiaoqian",this.biaoqian1)
          this.$store.commit("getText",item)
          this.addUserBrowse(item.id) 
          this.updateview(item.id,item.view_sum)
          this.$router.push({path: '/textdetail'})
        },
        updateview(id,sum){
            this.$axios
            .get("/updateview?user_id="+this.$store.state.userid1+'&&text_id='+id+'&&view_sum='+sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getAllText(){
            this.$axios
            .get("/selectDateText?id="+this.$store.state.userhome.id)
            .then(successResponse => {
                this.viewsun=0
                this.addtext=successResponse.data
                this.textsum=this.addtext.length
                for(let item of this.addtext){
                    this.viewsun=this.viewsun+item.view_sum
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        handle(){
            this.$axios
            .get("/getAllTextDetail")
            .then(successResponse => {
                this.biaoqian1=successResponse.data.biaoqian
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>

<style scoped>
.ifr{
    /* position: absolute; */
    width: 100%;
    height: 2000px;
}
.head{
    position: absolute;
    height: 200px;
    width:1300px;
    background-color: rgb(255, 255, 255);
    margin-left:100px;
    margin-top:80px;

}
#p1:hover{
    cursor: pointer;
}
#p2:hover{
    cursor: pointer;
}
</style>