<template>
<div class="all">
  <div id="a" class="demo-image" style="background-color: #ffffff;">
        <div class="block" v-for="item in this.alltype" :key="item">
        <div class="img1" >
            <p style="font-size:17px">{{item.type}}</p>
        </div>
        </div>
    </div>
    <div style="position: absolute;margin-left:700px;">
        <el-icon @click="extend()"><CaretBottom /></el-icon>
    </div>
  <div class="content1">
    <div style="margin-top:10px;">
        <div class="toutiao">
            <div style="display: flex;align-items: center;height:50px;font-size:20px;">
                <span style="font-weight:bold;margin-left:15px;">头</span>
                <span style="font-weight:bold;">条</span>
                <el-icon style="cursor:not-allowed;color:rgb(173, 173, 173);margin-left:505px;" id="left" @click="left()"><ArrowLeftBold /></el-icon>
                <el-icon id="right" @click="right()"><ArrowRightBold /></el-icon>
            </div>
            <div>
                <div>
                    <div @click="entrytetx(this.toutiaotext[0])" style="position: absolute;display: inline-block;margin-left:15px;width:300px;overflow: hidden;">
                        <img :src="geturl(this.toutiaotext[0].pic)" width="300" height="200">
                        <p>{{this.toutiaotext[0].biaoti}}</p>
                        <p style="color:rgb(177, 177, 177)">{{this.toutiaotext[0].zhaiyao}}</p>
                    </div>
                    <div v-for="item in toutiaotext" :key="item" style="font-size:15px;margin-left: 325px;width:250px;word-break:keep-all;white-space:nowrap;overflow: hidden;">
                        <div v-if="item.id!=this.toutiaotext[0].id" @click="entrytetx(item)">
                            <span>{{item.biaoti}}</span>
                            <p style="color:rgb(177, 177, 177)">{{item.zhaiyao}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="redian">
            <div style="display: flex;align-items: center;height:50px;font-size:20px;">
                <span style="font-weight:bold;margin-left:15px;">热</span>
                <span style="font-weight:bold;">点</span>
                <el-icon style="cursor:not-allowed;color:rgb(173, 173, 173);margin-left:265px;" id="RDleft" @click="RDleft()"><ArrowLeftBold /></el-icon>
                <el-icon id="RDright" @click="RDright()"><ArrowRightBold /></el-icon>
            </div>
            <div v-for="item in rediantext" :key="item" style="font-size:15px;margin-left: 15px;width:290px;word-break:keep-all;white-space:nowrap;overflow: hidden">
                        <div @click="entrytetx(item)">
                            <span>{{item.biaoti}}</span>
                            <p style="color:rgb(177, 177, 177)">{{item.zhaiyao}}</p>
                        </div>
                    </div>
        </div>
    </div>
    <el-tabs style="margin-top:10px;" @tab-click="handleClick">
        <el-tab-pane label="推荐"></el-tab-pane>
        <el-tab-pane label="关注"></el-tab-pane>
        <el-tab-pane label="资讯"></el-tab-pane>
        <el-tab-pane label="热榜"></el-tab-pane>
    </el-tabs>
    <div>
        <div v-for="item in this.textdata" :key="item">
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
                    <el-avatar :size="35" :src="geturl(item.userpic)" style="margin-left:10px;"></el-avatar>
                    <span style="margin-left:10px;">{{item.usermame}}</span>
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
  </div>
  <div class="content2">
    <div>
        <iframe class="ifa" src="/html/tianqi/index.html"></iframe>
    </div>
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
    <div style="height:330px;" class="content2-a2">
        <el-carousel :interval="5000" loop arrow="always" height="330">
            <el-carousel-item v-for="item in image" :key="item">
                <img @click="tiaozhuan(item.url)" :src="item.pic" width="300" height="330">
            </el-carousel-item>
        </el-carousel>
    </div>
    <div style="height:auto;" class="content2-a2">
        <p style="font-weight: bold;margin-left:15px;padding-top:10px;">友情链接</p>
        <div style="display: inline-block;box-sizing: border-box;vertical-align: top;">
            <div style="display: inline-block;box-sizing: border-box;vertical-align: top;width:40%;margin-left:10px;margin-top:10px;" v-for="item in alllink" :key="item">
                <el-button><a style="text-decoration: none;color: rgb(0, 0, 0);" :href="item.url" target="_blank">{{item.name}}</a></el-button>
            </div>
        </div>
        <el-divider style="color:ffffff"></el-divider>     
    </div>
    <div style="height:auto;" class="content2-a2">
        <p style="font-weight: bold;margin-left:15px;padding-top:10px;">热门文章</p>
        <div style="">
            <div v-for="item in hottextdata" :key="item">
                <el-divider></el-divider>
                <p style="margin-left:10px;" @click="entrytetx(item)">{{item.biaoti}}</p>
            </div>
        </div>
        <el-divider style="color:ffffff"></el-divider>
    </div>
    <div class="content2-a2">
        <p style="font-weight: bold;margin-left:15px;padding-top:10px;">签名</p>
        <p style="margin-left:15px;color: rgb(136, 136, 136);">不想努力了，啊啊啊啊！！！！！</p>
    </div>
  </div>
  <!-- <div style="width:100%;height:500px;">
    <vue-markdown :source="markdown"></vue-markdown>
    </div> -->
</div>
</template>
<script>
import { ElMessage } from 'element-plus'
// import VueMarkdown from 'vue-markdown'
import {ArrowRightBold,ArrowLeftBold,Search,Clock,View,ChatDotSquare,Pointer,StarFilled,CaretBottom} from "@element-plus/icons"
export default{
    data(){
        return{
            biaoqian1:[{}],
            text:'# Hello!',
            textdata:[{}],
            hottextdata:[{}],//热门文章
            text_sum:0,
            view_sum:0,
            a:true,
            input3:'',
            alltype:[{}],
            alllink:[{}],
            current:1,
            rdcurrent:1,
            toutiaotext:[{}],
            rediantext:[{}],
            image:[{pic:'/img/11.png',url:'https://marketing.csdn.net/p/cac73e51b6cbdfdbf393c510f425f37a?utm_source=sylunbo4&utm_medium=ad.590'},
            {pic:'/img/22.png',url:'https://jinshuju.net/f/dHxwJ7?x_field_1=gg-lunbo'}]
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
        if(this.$store.state.userid1==0){
           this.handle()
        }
        else{
            this.getUserRecommend()
        }
        this.getHotText()
        this.getAllType()
        this.getAllLink()
        this.getReiDianStatus(1,5)
        this.getTouTiapStatus(1,6)
    },
    methods:{
        geturl(val){
            if(val!=''){
                let url = 'http://124.220.16.147:8011/zf/'+val
                return url
            }
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
        //改变收藏量
        // collect(val){
        //     let sum=this.textdata.indexOf(val)
        //     this.textdata[sum].collect_sum=val.collect_sum+1
        //     document.getElementById("collect1").style.color='coral'
        //     document.getElementById("collect2").style.color='coral'
        //     this.$axios
        //     .get("/updatecollect?user_id="+this.$store.state.userid1+'&&text_id='+val.id+'&&view_sum='+val.collect_sum)
        //     .then(successResponse => {
        //         if(successResponse.data=="ok"){
        //             ElMessage({
        //                 showClose: true,
        //                 message: '收藏成功',
        //                 type: 'success',
        //             })
        //         }
        //         else{
        //             ElMessage({
        //                 showClose: true,
        //                 message: '该文章已经收藏',
        //                 type: 'error',
        //             })
        //         }
        //     })
        //     .catch(failResponse => {
        //     this.$alert(failResponse.response.status)
        //     })
        // },
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
        //获得热点文章
        getReiDianStatus(val,i){
            let a=val
            this.$axios
            .get("/getReiDianStatus?current="+a+'&&pageSize='+i)
            .then(successResponse => {
                this.rediantext=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //获得头条文章
        getTouTiapStatus(val,i){
            let a=val
            this.$axios
            .get("/getTouTiaoStatus?current="+a+'&&pageSize='+i)
            .then(successResponse => {
                this.toutiaotext=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        left(){
            if(this.current==1){
                document.getElementById('left').style.cursor='not-allowed'
                document.getElementById('left').style.color='rgb(173, 173, 173)'
        
            }
            else{
                this.current=this.current-1
                document.getElementById('right').style.cursor='default'
                document.getElementById('right').style.color='rgb(0, 0, 0)'
                this.getTouTiapStatus(this.current,6)
                if(this.current==1){
                    document.getElementById('left').style.cursor='not-allowed'
                    document.getElementById('left').style.color='rgb(173, 173, 173)'
                }
            }
        },
        right(){
            if(this.current==3){
                document.getElementById('right').style.cursor='not-allowed'
                document.getElementById('right').style.color='rgb(173, 173, 173)'
            }
            else{
                this.current=this.current+1
                document.getElementById('left').style.cursor='default'
                document.getElementById('left').style.color='rgb(0, 0, 0)'
                this.getTouTiapStatus(this.current,6)
                if(this.current==3){
                    document.getElementById('right').style.cursor='not-allowed'
                    document.getElementById('right').style.color='rgb(173, 173, 173)'
                }
            }
        },
        RDleft(){
            if(this.rdcurrent==1){
                document.getElementById('RDleft').style.cursor='not-allowed'
                document.getElementById('RDleft').style.color='rgb(173, 173, 173)'
        
            }
            else{
                this.rdcurrent=this.rdcurrent-1
                document.getElementById('RDright').style.cursor='default'
                document.getElementById('RDright').style.color='rgb(0, 0, 0)'
                this.getReiDianStatus(this.rdcurrent,5)
                if(this.rdcurrent==1){
                    document.getElementById('RDleft').style.cursor='not-allowed'
                    document.getElementById('RDleft').style.color='rgb(173, 173, 173)'
                }
            }
        },
        RDright(){
            if(this.rdcurrent==3){
                document.getElementById('RDright').style.cursor='not-allowed'
                document.getElementById('RDright').style.color='rgb(173, 173, 173)'
            }
            else{
                this.rdcurrent=this.rdcurrent+1
                document.getElementById('RDleft').style.cursor='default'
                document.getElementById('RDleft').style.color='rgb(0, 0, 0)'
                this.getReiDianStatus(this.rdcurrent,5)
                if(this.rdcurrent==3){
                    document.getElementById('RDright').style.cursor='not-allowed'
                    document.getElementById('RDright').style.color='rgb(173, 173, 173)'
                }
            }
        },
        tiaozhuan(url){
            var link=document.createElement('a')
            link.target='_blank'
            link.href=url
            link.click()
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
        // geturl(val){
        //     if(val!=''){
        //     return 'http://124.220.16.147:8011/zf/'+val
        //     }
        // },
        //获得热门文章
        getHotText(){
            //this.$axios
           // .get("/getHottext")
           // .then(successResponse => {
           //     this.hottextdata=successResponse.data
           //     this.$store.commit("getHottext",successResponse.data)
           // })
           // .catch(failResponse => {
           // this.$alert(failResponse.response.status)
            //})
            this.$axios
            .get("/getAllType")
            .then(successResponse => {
                this.hottextdata=successResponse.data
                this.$store.commit("getHottext",successResponse.data)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //获得全部链接
        getAllLink(){
            this.$axios
            .get("/getAllLink")
            .then(successResponse => {
                this.alllink=successResponse.data
                this.$store.commit("getAlllink",successResponse.data)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //获取全部类型
        getAllType(){
            this.$axios
            .get("/getAllType")
            .then(successResponse => {
                this.alltype=successResponse.data
                // this.$store.commit("getHottext",successResponse.data)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        handleClick(tab, event){
            if(tab.index==1){
                this.getAttentionTextDetail()
            }
            else if(tab.index==0){
                if(this.$store.state.userid1!=0){
                    this.getUserRecommend()
                }else{
                    this.handle()
                }
            }else{
                ElMessage({
                    showClose: true,
                    message: '该功能还在完善中',
                    type: 'error',
                })
            }
        },
        handle(){
            this.$axios
            .get("/getAllTextDetail")
            .then(successResponse => {
                this.textdata=successResponse.data.text
                this.biaoqian1=successResponse.data.biaoqian
                this.text_sum=successResponse.data.text_sum
                this.view_sum=successResponse.data.view_sum
                this.$store.commit("getTetxsum",successResponse.data.text_sum)
                this.$store.commit("getViewsum",successResponse.data.view_sum)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getUserRecommend(){
            if(this.$store.state.userid1!=0){
                this.$axios
                .get("/getUserRecommend?id="+this.$store.state.userid1)
                .then(successResponse => {
                this.textdata=successResponse.data.text
                this.biaoqian1=successResponse.data.biaoqian
                this.text_sum=successResponse.data.text_sum
                this.view_sum=successResponse.data.view_sum
                this.$store.commit("getTetxsum",successResponse.data.text_sum)
                this.$store.commit("getViewsum",successResponse.data.view_sum)
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
            }
        },
        getAttentionTextDetail(){
            if(this.$store.state.userid1!=0){
                this.$axios
                .get("/getAttentionTextDetail?id="+this.$store.state.userid1)
                .then(successResponse => {
                this.textdata=successResponse.data
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
            }else{
                ElMessage({
                    showClose: true,
                    message: '请先登录',
                    type: 'error',
                })
            }
        }
    }
}
</script>

<style scoped>
.redian{
    margin-left:615px;
    background-color: rgb(255, 255, 255);
    height: 400px;
    border-radius: 10px;
}
.toutiao{
    width: 600px;
    height: 400px;
    background-color: rgb(255, 255, 255);
    display: inline-block;
    position: absolute;
    border-radius: 10px;
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
    background-color:rgb(252, 252, 252);
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
#a{
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}
.content2-a2{
    margin-top:20px;
    height:300px;
    background-color: rgb(255, 255, 255);
    border-radius: 20px;
    color: black !important;
}
.content2-a1{
    height:340px;
    background-color: rgb(255, 255, 255);
    border-radius: 20px;
    opacity: 0.9;
    overflow: hidden;
}
.content2{
    display:inline-block;
    width: 300px;
    margin-left: 1100px;
    margin-top:25px;
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