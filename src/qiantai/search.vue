<template>
    <div class="head">
        <el-dropdown style="margin-left:100px;margin-top:10px;">
            <el-input @keyup.enter="searchBiaotiText()" v-model="value" input-style="background-color: rgb(235, 235, 235);" style="width:600px;" placeholder="请输入关键词"></el-input>
            <el-button @click="searchBiaotiText()" type="primary">搜索</el-button>
            <template #dropdown>
            <el-dropdown-menu v-if="this.$store.state.userid1!=0" class="menu" v-for="item in search_history" :key="item">
                <el-dropdown-item @click.native="change(item.content)">{{item.content}}</el-dropdown-item>
            </el-dropdown-menu>
            </template>
        </el-dropdown>
    </div>
    <div class="middle">
        <div style="margin-left:20px;">
            <el-tabs class="demo-tabs"  @tab-click="handleClick">
                <el-tab-pane label="博文" ></el-tab-pane>
                <el-tab-pane label="用户" ></el-tab-pane>
            </el-tabs>
            <div v-show="show1">
                <div v-if="this.$store.state.searchtext.length!=0">
                    <div v-for="item in this.$store.state.searchtext" :key="item">
                        <div class="content1-a1">
                            <div @click="entrytetx(item)">
                                <div v-if="item.iscover">
                                    <div>
                                        <img style="margin-left:10px;padding-top:10px;" :src="geturl(item.pic)" width="200" height="120"/>
                                    </div>
                                    <div class="content1-b1">
                                        <p style="font-weight: bold;">{{item.biaoti}}</p>
                                        <p class="p-a1" >{{item.zhaiyao}}</p>
                                    </div>
                                </div>
                                <div v-else>
                                    <div style="margin-left:15px;height:120px;padding-top:5px;">
                                        <p style="font-weight: bold;">{{item.biaoti}}</p>
                                        <p class="p-a1">{{item.zhaiyao}}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="center1">
                                
                                <span style="margin-left:10px;">{{item.username}}</span>
                                <el-icon style="margin-left:15px;"><Clock /></el-icon>
                                <span style="margin-left:3px;">{{item.create_time1}}</span>
                                <el-icon style="margin-left:15px;"><View /></el-icon>
                                <span style="margin-left:3px;">{{item.view_sum}}</span>
                                <el-icon style="margin-left:15px;"><ChatDotSquare /></el-icon>
                                <!-- <span style="margin-left:3px;"></span> -->
                                <span style="margin-left:3px;">0</span>
                                <el-icon id="dian1" @click="dian(item)" style="margin-left:15px;"><Pointer /></el-icon>
                                <span id="dian2" @click="dian(item)" style="margin-left:3px;">{{item.dianzan_sum}}</span>
                                <el-icon id="collect1" @click="collect(item)" style="margin-left:15px;"><StarFilled /></el-icon>
                                <span id="collect2" @click="collect(item)" style="margin-left:3px;">{{item.collect_sum}}</span>&nbsp&nbsp
                                <div class="biaoqian1" style="margin-left:0px;" v-for="item1 in biaoqian1" :key="item1">
                                    <div class="biaoqian1" style="" v-if="item.id==item1.id" v-for="item2 in item1.list" :key="item2">
                                        <button  aria-disabled="true" class="button1">{{item2}}</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-else style="height:400px;">
                    <p class="span-a1">没有搜索到与 "{{this.value}}" 有关的内容</p>
                </div>
            </div>
            <div v-show="show2">
                <div v-if="this.$store.state.searchuser!=0">
                    <div v-for="item in this.$store.state.searchuser" :key="item">
                        <div class="all-a1">
                            <div style="display: inline-block;margin-left:15px;">
                                <img @click="userhome(item.id)" :src="geturl(item.pic)" width="80" height="80" class="img">
                            </div>
                            <div style="display: inline-block;margin-left:15px;width:900px;">
                                <span>{{item.username}}</span><br>
                                <span>{{item.introduce}}</span>
                            </div>
                            <div style="display: inline-block;">
                                <button @click="attention(item.id)" class="button10">关注</button>
                            </div>
                        </div>
                        <el-divider></el-divider>
                    </div>
                </div>
                <div v-else style="height:400px;">
                    <p class="span-a1">没有搜索到与 "{{this.value}}" 有关的内容</p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
import {ArrowRightBold,ArrowLeftBold,Search,Clock,View,ChatDotSquare,Pointer,StarFilled,CaretBottom} from "@element-plus/icons"
export default{
    data(){
        return{
            biaoqian1:[{}],
            textdata:[{}],
            text_sum:0,
            view_sum:0,
            current:1,
            rdcurrent:1,
            value:'',
            show1:true,
            show2:false,
            search_history:[{}],
        }
    },
    components:{
        Clock,
        View,
        ChatDotSquare,
        Pointer,
        StarFilled,
        CaretBottom,
        Search,
        ArrowLeftBold,
        ArrowRightBold,
    },
    created: function(){
        this.$store.commit("getIsviewbot",true)
        this.handle()
        if(this.$store.state.userid1!=0){
            this.getUser_search_history()
        }
    },
    methods:{
        change(str){
            this.value=str
        },
        getUser_search_history(){
            this.$axios
            .get('/getUser_search_history?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.search_history=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        addUser_search_history(){
            this.$axios
            .get('/addUser_search_history?id='+this.$store.state.userid1+'&&content='+this.value)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        attention(id){
            this.$axios
            .get('/addUserAttention?user_id='+this.$store.state.userid1+'&&attention_id='+id)
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
        handleClick(tab, event){
            if(tab.index==0){
                this.show1=true
                this.show2=false
            }
            else{
                this.show2=true
                this.show1=false
            }
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
        searchBiaotiText(){
            this.$axios
            .get("/searchBiaotiText?str="+this.value)
            .then(successResponse => {
                this.$store.commit("getsearchtext",successResponse.data)
                this.searchUsername()
                if(this.$store.state.userid1!=0){
                    this.addUser_search_history()
                    setTimeout(()=>{
                        this.getUser_search_history()
                    },300)
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        searchUsername(){
             this.$axios
            .get("/searchUsername?username="+this.value)
            .then(successResponse => {
                this.$store.commit("getsearchuser",successResponse.data)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        geturl(val){
            let url = 'http://124.220.16.147:8011/zf/'+val
            return url
        },
        //改变点赞数量
        dian(val){
            let sum=this.textdata.indexOf(val)
            this.textdata[sum].dianzan_sum=val.dianzan_sum+1
            document.getElementById("dian1").style.color='coral'
            document.getElementById("dian2").style.color='coral'
            this.$axios
            .get("/updatedian?user_id="+this.$store.state.userid1+'&&text_id='+val.id+'&&view_sum='+val.dianzan_sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //改变观看数量
        updateview(id,sum){
            this.$axios
            .get("/updateview?user_id="+this.$store.state.userid1+'&&text_id='+id+'&&view_sum='+sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
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
        extend(){
            if(this.a){
                document.getElementById("a").style.height='140px'
                this.a=false
            }
            else{
                document.getElementById("a").style.height='70px'
                this.a=true
            }
        },
        entrytetx(item){
          this.$store.commit("getBiaoqian",this.biaoqian1)
          this.addUserBrowse(item.id)
          this.$store.commit("getText",item) 
          this.updateview(item.id,item.view_sum)
          this.$router.push({path: '/textdetail'})
        },
        geturl(val){
            return 'http://124.220.16.147:8011/zf/'+val
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
.all-a1{
    align-items: center;
    display: flex;
}
.span-a1{
    margin-left:300px;
    font-size: 50px;
}
.head{
    height: 60px;
    background-color: rgb(255, 255, 255);
    width: 100%;
}
.menu{
    width: 600px;
}
.img{
    border-radius: 50%;
}
.button10{
    font-size:17px;
    color: rgb(141, 141, 141);
    border-radius: 20px;
    width: 70px;
    border: rgb(189, 189, 189) solid 1px;
}
.button10:hover{
    border: rgb(111, 111, 111) solid 1px;
    cursor: pointer;
}
.middle{
    width: 1300px;
    background-color: rgb(255, 255, 255);
    margin-top:15px;
    margin-left:100px;
    position: relative;
}
.p-a1{
    color: rgb(129, 130, 131);
}
#dian1:hover{
    color: coral;
}
#dian2:hover{
    color: coral;
}
.input{
    width: 250px;
    margin-top:40px;
    margin-left:25px;
}
.biaoqian1{
    display: inline-flex;
}
.center:hover{
    cursor: pointer;
    color: rgb(255, 123, 0);
}
.button1{
    background-color:rgb(255, 151, 77);
    border-radius: 5px;
    height:20px;
    border: none;
    margin-left:5px;
}
.center1{
    align-items: center;
    display: flex;
    margin-top:5px;
}
.content1-a1{
    background-color: rgb(255, 255, 255);
    margin-top:10px;
    border-radius: 10px;
    height: 180px;
}
.content1-a1:hover{
    background-color: rgb(228, 228, 228);
    cursor: pointer;
    
}
.content1-b1{
    display: inline-block;
    word-wrap: break-word;
    word-break: break-all;
    overflow: hidden;
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
.center{
    /* align-items: center; */
    text-align: center;
    justify-content:center;
}
#a{
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}
.content1{
    display:inline-block;
    position: absolute;
    height: auto;
    width: 980px;
    margin-left:100px;
    margin-top:15px;
}
.demo-image{
    overflow: hidden;
    background-color: rgb(255, 255, 255);
    width: 1300px;
    margin-left: 100px;
    transform:translate(0px,15px);
    height: 70px;
    border-radius: 5px;
}
.demo-image .block {
  padding: 10px 25px;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  /* width: 25%; */
  box-sizing: border-box;
  vertical-align: top;
}
.demo-image1 .block1{
  padding: 10px 0px;
  width:600px;
  /* text-align: center; */
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 50%;
  box-sizing: border-box;
  vertical-align: top;
}
.ifa{
    width: 340px;
    border: none;
    height: 540px;
    margin-left:-20px;
}
</style>

