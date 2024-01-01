<template>
<!-- <iframe class="ifa" src="/html/yanhua.html"></iframe> -->
<div class="all">
  <div class="content1">
        <div class="content2-a1">
            <img width="300" src="/img/aaaaa.jpg" >
            <el-input
            v-model="input3"
            placeholder="请输入关键字进行搜索"
            class="input"
            >
            <template #prepend>
                <el-icon><Search/></el-icon>
            </template>
            </el-input>
        </div>
        <div class="content2-a2">
            <p style="font-weight: bold;margin-left:15px;padding-top:10px;">签名</p>
            <p style="margin-left:15px;color: rgb(136, 136, 136);">不想努力了，啊啊啊啊！！！！！</p>
        </div>
        <div style="height:auto;" class="content2-a2">
            <p style="font-weight: bold;margin-left:15px;padding-top:10px;">友情链接</p>
            <div style="display: inline-block;box-sizing: border-box;vertical-align: top;">
                <div style="display: inline-block;box-sizing: border-box;vertical-align: top;width:40%;margin-left:10px;margin-top:10px;" v-for="item in this.$store.state.alllink" :key="item">
                    <el-button><a style="text-decoration: none;color: rgb(0, 0, 0);" :href="item.url" target="_blank">{{item.name}}</a></el-button>
                </div>
            </div>
            <el-divider style="color:ffffff"></el-divider>     
        </div>
        <div style="height:auto;" class="content2-a2">
            <p style="font-weight: bold;margin-left:15px;padding-top:10px;">热门文章</p>
            <div>
                <div v-for="item1 in this.$store.state.hottext" :key="item1">
                    <el-divider></el-divider>
                    <p style="margin-left:10px;" @click="entrytetx(item1)">{{item1.biaoti}}</p>
                </div>
            </div>
            <el-divider style="color:ffffff"></el-divider>
        </div>
        <el-affix position="top" offset="51">
        <div class="mulu">
            <p style="margin-left:10px;font-weight: bold;padding-top:10px;font-size:20px;">目录</p>
            <div
                v-for="anchor in titles"
                :style="{ padding: `10px 0 10px ${anchor.indent * 20}px` }"
                @click="handleAnchorClick(anchor)"
                class="mulu1"
                >
                <a class="mulu2" style="cursor: pointer">{{ anchor.title }}</a>
            </div>
        </div>
        </el-affix>
  </div>
  <div class="content2" id="con">
    <div style="margin-left:50px;font-weight: bold;font-size:22px;">
        <p>{{this.$store.state.text.biaoti}}</p>
    </div>
    <div class="content3">
        <div style="padding-top:10px;">
            <el-icon style="margin-left:20px;"><User /></el-icon>
            <span style="margin-left:5px;" >{{this.$store.state.text.usermame}}</span>
            <el-icon style="margin-left:5px;"><Clock/></el-icon>
            <span style="margin-left:5px;">{{this.$store.state.text.create_time1}} 发布</span>
            <el-icon style="margin-left:20px;"><View/></el-icon>
            <span style="margin-left:5px;">{{this.$store.state.text.view_sum}}</span>
            <el-icon style="margin-left:20px;"><ChatDotSquare/></el-icon>
            <span style="margin-left:5px;">0</span>
        </div>
        <div style="padding-top:10px;display: flex;">
            <span style="margin-left:20px;">分类专栏：</span>
            <button class="button1">{{this.$store.state.text.type}}</button>
            <span style="margin-left:20px;">文章标签：</span>
            <div v-for="item in this.$store.state.biaoqian" :key="item">
                <div style="display: flex;" v-if="item.id==this.$store.state.text.id">
                    <div v-for="item1 in item.list" :key="item1">
                       <button class="button2">{{item1}}</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <v-md-preview :text="this.$store.state.text.content"  ref="preview"></v-md-preview>
    </div>
    <el-divider></el-divider>
    <div class="user">
        <el-avatar style="margin-left:10px;" @click="userhome(this.$store.state.text.user_id)" :src="geturl(this.$store.state.text.pic)"></el-avatar>
        <span style="margin-left:10px;">{{this.$store.state.text.usermame}}</span>
        <!-- <el-button @click="attention()" style="margin-left:15px;" size="small">关注</el-button> -->
        <el-icon id="dian1" @click="dian(this.$store.state.text)" style="margin-left:15px;"><Pointer /></el-icon>
        <span id="dian2" @click="dian(this.$store.state.text)" style="margin-left:3px;">{{this.$store.state.text.dianzan_sum}}</span>
        <!-- <el-icon id="collect1" @click="collect(this.$store.state.text)" style="margin-left:15px;"><StarFilled /></el-icon> -->
        <el-dropdown :hide-on-click="false">
            <el-icon @click="collect(this.$store.state.text)" style="margin-left:15px;"><Star /></el-icon>
            <template #dropdown>
            <el-dropdown-menu>
                <div v-for="item in collectdata" :key="item">
                    <el-dropdown-item><span @click="collect(item)">{{item.collect_name}}</span></el-dropdown-item>
                </div>
            </el-dropdown-menu>
            </template>
        </el-dropdown>
        <span style="font-size:20px;">{{this.$store.state.text.collect_sum}}</span>
        <el-icon @click="comment()" style="margin-left:15px;font-size:18px;"><ChatDotSquare /></el-icon>

    </div>
  </div>
</div>
<el-drawer
    v-model="table"
    :title="commentsum()"
    direction="rtl"
    size="45%"
  >
  <div class="drawer" style="height:660px;">
    <div>
    <div style="display:inline-block;margin-left:10px;position: relative;">
        <el-avatar :src="url(this.$store.state.userdetail.pic)"></el-avatar>
    </div>
    <div style="width:600px;display:inline-block;margin-left:10px;">
        <div>
        <el-input v-model="user_comment.content" maxlength="300" placeholder="Please input" show-word-limit type="textarea" />
        </div>
    </div>
  </div>
  <div style="margin-left:590px;margin-top:10px;">
        <el-button @click="addUserComment(null,1)" type="primary">评论</el-button>
    </div>
    <el-divider></el-divider>
    <div>
        <div v-for="item in this.$store.state.usercomment" :key="item">
            <div style="margin-top:25px;margin-left:10px;" v-if="item.huifu==0">
                <div class="ava">
                    <el-avatar :src="url(item.pic)"></el-avatar>
                </div>
                <div style="display: inline-block;margin-left:10px;position: relative;">
                    <span style="margin-top:0px;color:rgb(182, 182, 182);">{{item.time1}}</span>&nbsp
                    <span style="color:rgb(182, 182, 182);">{{item.username}}</span>&nbsp&nbsp
                    <span style="color:rgb(182, 182, 182);">来自 {{item.location}}</span><br>
                    <span style="word-wrap:break-word;">{{item.content}}</span>
                    <span>{{}}</span>&nbsp&nbsp&nbsp<span class="span11" @click="huifu(item)">{{this.huifu1}}</span>
                </div>
                <div v-show="item.show2" style="margin-left:40px;margin-top:10px;">
                    <div style="width:600px;display:inline-block;margin-left:10px;">
                        <div>
                        <el-input v-model="user_comment.content" maxlength="300" placeholder="Please input" show-word-limit type="textarea" />
                        </div>
                    </div>
                </div>
                <div v-show="item.show2" style="margin-left:580px;margin-top:10px;">
                    <el-button @click="addUserComment(item,2)" type="primary">评论</el-button>
                </div>
                <div style="margin-top:10px;margin-left:60px;">
                    <el-collapse v-model="activeName" accordion>
                        <el-collapse-item title="查看全部回复" name="1">
                            <div style="margin-top:20px;" v-for="item1 in this.$store.state.usercomment" :key="item1">
                                <div v-if="item1.huifu==item.id">
                                    <div class="ava">
                                        <el-avatar :src="url(item1.pic)" class="ava2"></el-avatar>
                                    </div>
                                    <div style="display: inline-block;margin-left:10px;position: relative;">
                                        <span style="margin-top:0px;color:rgb(182, 182, 182);">{{item1.time1}}</span>
                                        <span style="color:rgb(182, 182, 182);">{{item1.username}}</span>
                                        <span style="margin-top:0px;color:rgb(182, 182, 182);" v-if="item1.huifu_huifu!=0">-> {{getup_user(item1.huifu_huifu)}}</span>&nbsp&nbsp
                                        <span style="color:rgb(182, 182, 182);">来自 {{item1.location}}</span><br>
                                        <span style="word-wrap:break-word;">{{item1.content}}</span>
                                        <span>{{}}</span>&nbsp&nbsp&nbsp<span class="span11" @click="huifu3(item1)">{{this.huifu2}}</span>
                                    </div>
                                    <div v-show="item1.show1" style="margin-left:40px;margin-top:10px;">
                                        <div style="width:600px;display:inline-block;margin-left:10px;">
                                            <div>
                                            <el-input style="width:570px;" v-model="user_comment.content" maxlength="300" placeholder="Please input" show-word-limit type="textarea" />
                                            </div>
                                        </div>
                                    </div>
                                    <div v-show="item1.show1" style="margin-left:550px;margin-top:10px;">
                                        <el-button @click="addUserComment(item1,3)" type="primary">评论</el-button>
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
  </el-drawer>
</template>
<script>
import { ElMessage } from 'element-plus'
// import VueMarkdown from 'vue-markdown'
import {User,Search,Clock,View,ChatDotSquare,Pointer,Star} from "@element-plus/icons"
export default{
    data(){
        return{
            titles: [],
            collectdata:[{}],
            table:false,
            content:'',
            huifu1:'回复',
            huifu2:'回复',
            user_comment:{},
        }
    },
    components:{
        Clock,
        View,
        ChatDotSquare,
        Pointer,
        Star,
        Search,
        User,
    },
    mounted() {
        const anchors = this.$refs.preview.$el.querySelectorAll('h1,h2,h3,h4,h5,h6');
        const titles = Array.from(anchors).filter((title) => !!title.innerText.trim());

        if (!titles.length) {
        this.titles = [];
        return;
        }

        const hTags = Array.from(new Set(titles.map((title) => title.tagName))).sort();

        this.titles = titles.map((el) => ({
        title: el.innerText,
        lineIndex: el.getAttribute('data-v-md-line'),
        indent: hTags.indexOf(el.tagName),
        }));
    },
    created: function(){
        this.$store.commit("getIsviewbot",true)
        this.getCollectName()
        this.getUserComment()
    },
    methods:{
        commentsum(){
            var s=this.collectdata.length
            return "评论"+s
        },
        addUserComment(val,i){
            this.user_comment.pic=this.$store.state.userdetail.pic
            this.user_comment.username=this.$store.state.userdetail.username
            this.user_comment.location=this.$store.state.userdetail.location
            var data = new Date()
            var nian=data.getFullYear()
            var yue=data.getDate()
            var day=data.getUTCDay()+5
            var str=nian+'-'+yue+'-'+day
            this.user_comment.time1=str
            this.user_comment.text_id=this.$store.state.text.id
            this.user_comment.user_id=this.$store.state.userid1
            this.user_comment.show2=false
            this.user_comment.show1=false
            this.user_comment.i=i
            if(i==1){
                this.user_comment.type=false
                this.user_comment.huifu=0
                this.user_comment.huifu_huifu=0
            }
            else if(i==2){
                this.user_comment.type=true
                this.user_comment.huifu=val.id
                this.user_comment.huifu_huifu=0
            }
            else{
                this.user_comment.type=true
                this.user_comment.huifu=val.huifu
                this.user_comment.huifu_huifu=val.id
            }
            this.$axios
            .post('/addUserComment',this.user_comment)
            .then(successResponse => {
                if(successResponse.data=='ok'){
                    ElMessage({
                        showClose: true,
                        message: '评论成功',
                        type: 'success',
                    })
                    var a=this.$store.state.usercomment
                    a.push(this.user_comment)
                    this.$store.commit("getUsercomment",a)
                }else if(successResponse.data=='no'){
                    ElMessage({
                        showClose: true,
                        message: '评论失败',
                        type: 'error',
                    })
                }else{
                    ElMessage({
                        showClose: true,
                        message: `${successResponse.data}`,
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getup_user(id){
            if(id!=0){
                for(let item of this.$store.state.usercomment){
                    if(item.id===id){
                        return item.username
                    }
                }

            }

        },
        getUserComment(){
            this.$axios
            .get('/getUserComment?id='+this.$store.state.text.id)
            .then(successResponse => {
                this.$store.commit("getUsercomment",successResponse.data)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        huifu(val){
            val.show2=!val.show2
            if(val.show2){
                this.huifu1='取消'
            }
            else{
                this.huifu1='回复'
            }
        },
        huifu3(val){
            val.show1=!val.show1
            if(val.show1){
                this.huifu2='取消'
            }
            else{
                this.huifu2='回复'
            }
        },
        url(val){
            return 'http://124.220.16.147:8011/zf/'+val
        },
        comment(){
            this.table=true
        },
        attention(){
            this.$axios
            .get('/addUserAttention?user_id='+this.$store.state.userid1+'&&attention_id='+this.$store.state.text.user_id)
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
        getCollectName(){
            this.$axios
            .get('/selectCollectName?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.collectdata=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //改变点赞数量
        dian(val){
            this.$store.state.text.dianzan_sum=val.dianzan_sum+1
            document.getElementById("dian1").style.color='coral'
            document.getElementById("dian2").style.color='coral'
            this.$axios
            .get("/updatedian?user_id="+this.$store.state.userid1+'&&text_id='+val.id+'&&view_sum='+val.dianzan_sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //改变收藏量
        collect(val){
            // document.getElementById("collect1").style.color='coral'
            // document.getElementById("collect2").style.color='coral'
            this.$axios
            .get("/updatecollect?user_id="+this.$store.state.userid1+'&&text_id='+this.$store.state.text.id+'&&view_sum='+this.$store.state.text.collect_sum+'&&collect_name_id='+val.id)
            .then(successResponse => {
                if(successResponse.data=="ok"){
                    this.$store.state.text.collect_sum=this.$store.state.text.collect_sum+1
                    ElMessage({
                        showClose: true,
                        message: '收藏成功',
                        type: 'success',
                    })
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '该文章已经收藏',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        geturl(){
            let url = 'http://124.220.16.147:8011/zf/'+this.$store.state.text.userpic
            return url

        },
        userhome(id){
            this.$axios
            .get("/getUserDetail?id="+id)
            .then(successResponse => {
                this.$store.commit("getUserhome",successResponse.data)
                setTimeout(()=>{
                    this.$router.push({path: '/userhome'})
                },300)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        updateview(id,sum){
            let a=this.$store.state.userid1
            this.$axios
            .get("/updateview?user_id="+a+'&&text_id='+id+'&&view_sum='+sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        entrytetx(item){
        //   this.$store.commit("getBiaoqian",this.biaoqian1)
          this.updateview(item.id,item.view_sum)
          this.addUserBrowse(item.id)
          this.$store.commit("getText",item) 
          this.$router.push({path: '/textdetail'})
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
        handleAnchorClick(anchor) {
            const { preview } = this.$refs;
            const { lineIndex } = anchor;

            const heading = preview.$el.querySelector(`[data-v-md-line="${lineIndex}"]`);

            if (heading) {
                preview.scrollToTarget({
                target: heading,
                scrollContainer: window,
                top: 60,
                });
            }
        },
    }
}
</script>

<style scoped>
.drawer{
    overflow-y: scroll;
}
.drawer::-webkit-scrollbar{
    display: none;
}
.span11{
    color:rgb(182, 182, 182)
}
.span11:hover{
    color:rgb(0, 179, 255);
}
.ifa{
    position: absolute;
    width: 100%;
    height: 100%;
}
.ifa::-webkit-scrollbar{
    display: none;
}
.input{
    width: 250px;
    margin-top:40px;
    margin-left:25px;
}
.a{
    height: 200px;;
}
.mulu1{
    margin-left:10px;
}
.ava{
    display: inline-block;
    position: relative; 
    margin-top:-10px; 
}
.mulu2:hover{
    color: rgb(0, 200, 255);
}
.mulu2:focus{
    color: rgb(0, 200, 255);
}
.mulu{
    background-color: rgb(255, 255, 255);
    border-radius: 20px;
    height: 680px;
    overflow-y:scroll;
}
.content3{
    color: rgb(175, 172, 172);
    background-color: rgb(244, 237, 237);
    width: 700px;
    height: 70px;
    margin-left:50px;
    border-radius: 10px;
}
.biaoqian1{
    display: flex;
}
.center:hover{
    cursor: pointer;
    color: rgb(255, 123, 0);
}
#dian1:hover{
    color: coral;
    cursor: pointer;
}
#dian2:hover{
    color: coral;
    cursor: pointer;
}
.button2{
    background-color:rgb(255, 191, 164);
    color: rgb(255, 94, 0);
    border-radius: 5px;
    height:20px;
    border: none;
    margin-left:5px;
}
.button1{
    background-color:rgb(255, 245, 139);
    color: rgb(247, 187, 6);
    border-radius: 5px;
    height:20px;
    border: none;
    margin-left:5px;
}
.center1{
    align-items: center;
    /* justify-content:center; */
    display: flex;
}
.content1-a1{
    background-color: rgb(255, 255, 255);
    margin-top:10px;
    border-radius: 10px;
}
.content1-b1{
    display: inline-block;
    position:absolute;
    margin-left:220px;
    margin-top:-135px;
}
.c1{
    display: inline-block;
    margin-left:25px;
    text-align: center;
    justify-content:center;
    font-weight: bold;
}
.content-a2-b2{
    background-color:rgb(255, 255, 255);
    height: 100%;
}
.all{
    background-color: rgb(236, 237, 238);
    background-size: 100%,100%;
}
.center{
    /* align-items: center; */
    text-align: center;
    justify-content:center;
}
.user{
    align-items: center;
    display: flex;
    height: 50px;
    /* display: inline-flex; */
}
.content2-a2{
    margin-top:20px;
    height:100px;
    background-color: rgb(255, 255, 255);;
    border-radius: 20px;
}
.content2-a1{
    height:340px;
    background-color: rgb(255, 255, 255);
    border-radius: 20px;
    overflow: hidden;
}
.content2{
    display:inline-block;
    width: 800px;
    margin-left: 550px;
    margin-top:20px;
    background-color: rgb(255, 255, 255);
    height: auto;
}
/* .content2::-webkit-scrollbar{
    display: none;
} */
.content1{
    display:inline-block;
    position: absolute;
    height: auto;
    width: 300px;
    margin-left: 180px;
    margin-top:20px;
}
</style>