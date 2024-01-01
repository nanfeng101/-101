<template>
<iframe class="ifa" src="/html/yanhua.html"></iframe>
    <div class="all">
        <div style="position: absolute;background-color: rgb(255, 255, 255);width:1200px;">
        <p style="font-weight: bold;font-size:20px;padding-top:15px;padding-left:15px;">消息中心</p>
        <el-divider ></el-divider>
        <div>
            <el-container>
                <el-aside>
                    <div style="margin-left:20px;">
                        <div style="align-items: center;display: flex;">
                            <div style="display: inline-block;">
                                <el-avatar :src="geturl(this.$store.state.userdetail.pic)"></el-avatar>
                            </div>
                            <div style="display: inline-block;margin-left:10px;">
                                <span>{{this.$store.state.userdetail.username}}</span>
                            </div>
                        </div>
                        <el-divider></el-divider>
                    </div>
                    <el-menu 
                    style="min-height:100%;height:600px;"
                    :default-active="activeIndex"
                    class="el-menu-demo" 
                    :collapse="isCollapse"
                    @select="handleSelect"
                    background-color=rgb(255,255,255)
                    text-color=#000000>
                    <div v-for="item in chatuser" :key="item" >
                        <el-menu-item @click="chat(item)" style="height:80px;">
                            <div style="align-items: center;">
                                <el-badge type="danger" v-if="item.sum!=0" :value="item.sum" :max="99" class="item">
                                    <div style="display: inline-block;">
                                        <el-avatar :src="geturl(item.pic)"></el-avatar>
                                    </div>
                                    <div style="display: inline-block;margin-left:10px;">
                                        <span>{{item.username}}</span>
                                    </div>
                                </el-badge>
                                <div v-if="item.sum==0">
                                    <div style="display: inline-block;">
                                        <el-avatar :src="geturl(item.pic)"></el-avatar>
                                    </div>
                                    <div style="display: inline-block;margin-left:10px;">
                                        <span>{{item.username}}</span>
                                    </div>
                                </div>
                            </div>
                        </el-menu-item>
                    </div>
                </el-menu>
                </el-aside>
                <el-main>
                    <div>
                        <span>{{this.username}}</span>
                        <span style="margin-left:15px;">{{this.introduce}}</span>
                        <el-divider></el-divider>
                    </div>
                    <div id="send">
                        <div v-if="this.id==0" style="margin-left:250px;">
                            <p style="color: #cfcece;">您还未选中或发起聊天，快去跟好友聊一聊吧</p>
                        </div>
                        <div v-if="this.id!=0" v-for="item in chatcontent" :key="item">
                            <div v-if="item.message!=null" style="text-align: center;justify-content:center;">
                                <p style="color: #b7b7b7;">{{item.send_time1}}</p>
                            </div>
                            <div v-if="item.is_send==0" style="align-items: center;display: flex;margin-top:15px;">
                                <div style="display: inline-block;">
                                    <el-avatar v-if="item.message!=null" :src="geturl(item.pic)"></el-avatar>
                                </div>
                                <div v-if="item.message!=null" class="xinxi1">
                                    <p  style="margin-left:10px;">{{item.message}}</p>
                                </div>
                            </div>
                            <div v-else style="align-items: center;display: flex;margin-left:360px;margin-top:15px;">
                                <div v-if="item.message!=null" class="xinxi1">
                                    <p style="margin-left:10px;">{{item.message}}</p>
                                </div>
                                <div style="display: inline-block;margin-left:10px;">
                                    <el-avatar v-if="item.message!=null" :src="geturl(this.$store.state.userdetail.pic)"></el-avatar>
                                </div>
                            </div>
                        </div>
                        <div class="hsDiv">
                            
                        </div>
                        <div class="hsDiv1">
                            
                        </div>
                    </div>
                    <div v-if="this.id!=0">
                        <el-divider></el-divider>
                        <el-input @keyup.enter="send()" v-model="user_chat.message"></el-input>
                    </div>
                </el-main>
            </el-container>
        </div>
        </div>
    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
export default{
    data(){
        return{
            chatcontent:[{}],
            chatuser:[{}],
            username:'',
            pic:'',
            introduce:'',
            id:0,
            user_chat:{},
            webScoket:null,
            qqNumber:'',
            chat2:{},
            a:'',
            node:null,
            btn:null,
            node1:null,
            btn1:null,
        }
    },
    created: function(){
        this.getChatUser()
        this.getChatRead()
    },
    methods:{
        geturl(val){
            let url = 'http://124.220.16.147:8011/zf/'+val
            return url
        },
        getChatRead(){
        this.$axios
            .get('/getChatRead?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.$store.commit("getIsread",successResponse.data)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        send(){
            if(this.user_chat.message==null){
                ElMessage({
                        showClose: true,
                        message: '要发送的信息为空',
                        type: 'error',
                    })
            }
            else{
                //  发送消息的格式：
                // QQ信息$消息内容$发送时间$昵称
                var nowDate = new Date()
                var sendMsgStr = this.qqNumber+"$"+this.user_chat.message+"$"+(nowDate.getFullYear() +"-"+nowDate.getMonth()+"-"+nowDate.getDate()+" "+nowDate.getHours()+":"+nowDate.getMinutes()+":"+nowDate.getSeconds()+"$"+this.id)
                this.webScoket.send(sendMsgStr)
                this.user_chat.user_id=this.$store.state.userid1
                this.user_chat.send_user_id=this.id
                this.$axios
                .post('/addUserChat',this.user_chat)
                .then(successResponse => {
                    this.user_chat.message=''
                    // this.getIdUserCHat(this.id)
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
            }
        },
        chat(val){
            if(this.webScoket!=null){
                this.webScoket.close()
                this.btn.removeChild(this.node)
                this.btn1.removeChild(this.node1)
            }
            var pic1=this.$store.state.userdetail.pic
            this.username=val.username
            this.introduce=val.introduce
            this.id=val.send_user_id
            this.pic=val.pic
            var btn = document.querySelector(".hsDiv")
            this.btn=btn
            var node = document.createElement("div")
            this.node=node
            var id = val.user_id
            node.setAttribute("id",id)
            btn.appendChild(node)
            var btn1 = document.querySelector(".hsDiv1")
            this.btn1=btn1
            var node1 = document.createElement("div")
            this.node1=node1
            var id1 = val.qqNumber
            node1.setAttribute("id",id1)
            btn1.appendChild(node1)
            // document.getElementById(id).removeChild
            // document.getElementById(id).insertAdjacentHTML.
            this.getIdUserCHat(val.send_user_id)
            setTimeout(()=>{
                document.getElementById("send").scrollTop=document.getElementById("send").scrollHeight-document.getElementById("send").clientHeight
            },300)
             // 生成一个唯一的数字
            var qqNumber  = parseInt(Math.random()*10000000000000);
            this.qqNumber=qqNumber
            var webScoket = new WebSocket("ws://124.220.16.147:8011/zf/qqserver/"+qqNumber)
            this.webScoket=webScoket
            // 定义一个连接成功的回调函数
            webScoket.onopen = function (){
                console.dir("-连接成功！")
                // 获取连接的昵称信息
                // var nikeNames = document.getElementById("nickName").value;
                // nikeName = nikeNames;
            }
            // 定义一个服务器发送消息给客户端的回调函数
            webScoket.onmessage = function(data){
                console.dir("-接收到消息:"+data.data)
                // QQ信息$消息内容$发送时间$昵称
                var msgArr = data.data.split("$")
                // htmls = htmls+'<div style="text-align: center;justify-content:center;">'+
                //                 '<p style="color: #b7b7b7;">'+msgArr[2]+'</p>'+
                //             '</div>'+'<div v-if="item.is_send==0" style="align-items: center;display: flex;margin-top:15px;">'+
                // '<div style="display: inline-block;">'+
                //                             '<img style="border-radius: 50%;" src="http://localhost:8001/zf/'+val.pic+'" width="40" height="40">'+
                //                         '</div>'+
                //                         '<div v-if="item.message!=null" style="display: inline-block;margin-left:10px;background-color: rgb(239, 239, 239);width:450px;word-wrap:break-word;word-break: break-all;overflow: hidden;">'+
                //                             '<p  style="margin-left:10px;">'+msgArr[1]+'</p>'+
                //                         '</div>'+'</div>'
                // document.getElementById(`${msgArr[3]}`).innerHTML = htmls
                var htmls1=`<div style="text-align: center;justify-content:center;">
                                <p style="color: #b7b7b7;">${msgArr[2]}</p>
                            </div><div v-if="item.is_send==0" style="align-items: center;display: flex;margin-top:15px;">
                <div style="display: inline-block;">
                                            <img style="border-radius: 50%;" src="http://localhost:8001/zf/${val.pic}" width="40" height="40">
                                        </div>
                                        <div v-if="item.message!=null" style="display: inline-block;margin-left:10px;background-color: rgb(239, 239, 239);width:450px;word-wrap:break-word;word-break: break-all;overflow: hidden;">
                                            <p  style="margin-left:10px;">${msgArr[1]}</p>
                                        </div></div>`
                var htmls2=`<div style="text-align: center;justify-content:center;">
                                <p style="color: #b7b7b7;">${msgArr[2]}</p>
                            </div><div v-if="item.is_send==0" style="align-items: center;margin-left:360px;display: flex;margin-top:15px;">
                                        <div v-if="item.message!=null" style="display: inline-block;margin-left:10px;background-color: rgb(239, 239, 239);width:450px;word-wrap:break-word;word-break: break-all;overflow: hidden;">
                                            <p  style="margin-left:10px;">${msgArr[1]}</p>
                                        </div><div style="display: inline-block;">
                                            <img style="border-radius: 50%;" src="http://localhost:8001/zf/${pic1}" width="40" height="40">
                                        </div></div>`
                if(id==msgArr[3]){
                    document.getElementById(id1).insertAdjacentHTML('beforeend',htmls1)
                }
                else{
                    document.getElementById(id1).insertAdjacentHTML('beforeend',htmls2)
                }
                
                // 动态html代码段添加进入聊天历史信息框中

            }
        },
        getChatUser(){
            this.$axios
            .get('/getChatUser?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.chatuser=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getIdUserCHat(val){
            this.$axios
            .get('/getIdUserChat?id1='+this.$store.state.userid1+'&&id2='+val)
            .then(successResponse => {
                this.chatcontent=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>

<style scoped>
.ifa{
    position: absolute;
    width: 100%;
    height: 900px;
}
.all{
    background-color: rgb(255, 255, 255);
    width:1200px;
    margin-top:10px;
    margin-left:150px;
    height: 800px;
}
.xinxi1{
    display: inline-block;
    margin-left:10px;
    background-color: rgb(239, 239, 239);
    width:450px;word-wrap:break-word;
    word-break: break-all;
    overflow: hidden;
}
#send{
    height:500px;
    overflow-y: scroll;
}
#send::-webkit-scrollbar{
    display: none;
}

</style>