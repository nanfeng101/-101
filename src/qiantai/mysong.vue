<template>
    <div class="all">
        <div style="padding-left:680px;padding-top:100px;width:200px;">
            <img :src="setimg()" class="img1" width="130" height="130"><br>
            <span class="span1">{{this.songdata.username}}</span><br>
            <div class="all-child1">
                <span class="span2">{{this.singer_sum}}</span><br>
                <span class="span3">关注</span>
            </div>
            <div class="all-child1" style="margin-left:110px;">
                <span class="span2">{{this.consumer_sum}}</span><br>
                <span class="span3">粉丝</span>
            </div>
        </div>
        <div class="head1" >
            <div class="demo-tabs">
                <!-- <el-tab-pane @click.native="replace('mylove')" label="我喜欢" name="first"></el-tab-pane>
                <el-tab-pane @click.native="replace('mycreatesong_list')" label="我创建的歌单" name="second"></el-tab-pane>
                <el-tab-pane label="关注" name="third"></el-tab-pane>
                <el-tab-pane label="粉丝" name="fourth"></el-tab-pane> -->
                <span style="color:rgb(0, 174, 255);" class="span-a1" id="a1" @click="replace('mylove')">我喜欢</span>
                <span class="span-a1" id="a2" @click="replace('mycreatesong_list')">我创建的歌单</span>
                <span class="span-a1" id="a3" @click="replace('attention')">关注</span>
                <span class="span-a1" id="a4" @click="replace('fans')">粉丝</span>
            </div>
        </div>
    </div>
    <div>
        <component :is="currentCom"></component>
    </div>
</template>
<script>
import Mylove from '../qiantai/myself/mylove.vue'
import Mycreatesong_list from '../qiantai/myself/mycreatesong_list.vue'
import Attention from '../qiantai/myself/attention.vue'
import Fans from '../qiantai/myself/fans.vue'
export default{
    name:'mysong',
    data(){
        return{
            songdata:{},
            currentCom:'mylove',
            singer_sum:0,
            consumer_sum:0,
        }
    },
    components:{
        Mylove,
        Mycreatesong_list,
        Attention,
        Fans,
    },
    created: function () {
        this.hand()
        this.hand1()
    },
    methods:{
        hand1(){
            let id = this.$store.state.userid1
            this.$axios
            .get('/getSingerDetail?id=' + id)
            .then(successResponse=>{
                this.singer_sum=successResponse.data.singer_sum
                this.consumer_sum=successResponse.data.consumer_sum
            })
            .catch(failResponse => {
                this.$alert(failResponse.response.status)
            })
        },
        replace(val){
            this.currentCom=val
            if(val === 'mylove'){
                document.getElementById("a1").style.color = 'rgb(0, 174, 255)'
                document.getElementById("a2").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a3").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a4").style.color = 'rgb(255, 255, 255)'
            }
            else if(val === 'mycreatesong_list'){
                document.getElementById("a2").style.color = 'rgb(0, 174, 255)'
                document.getElementById("a1").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a3").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a4").style.color = 'rgb(255, 255, 255)'
            }
            else if(val === 'attention'){
                document.getElementById("a2").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a1").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a3").style.color = 'rgb(0, 174, 255)'
                document.getElementById("a4").style.color = 'rgb(255, 255, 255)'
            }
            else{
                document.getElementById("a2").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a1").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a3").style.color = 'rgb(255, 255, 255)'
                document.getElementById("a4").style.color = 'rgb(0, 174, 255)'
            }
        },
        setimg(){
            return 'http://localhost:8081/zf/' + this.songdata.avator
        },
        hand(){
            this.$axios
                .get('/getIdConsumer?id=' + this.$store.state.userid1)
                .then(successResponse => {
                this.songdata = successResponse.data
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
        }
    }
}
</script>
<style scoped>
.img1{
    border: aliceblue solid 7px;
    border-radius: 50%;
}
.head1{
    background: none;
    margin-top:128px;
    margin-left:100px;
}
.all{
    height: 450px;
    width: 100%;
    background-image: url("/img/aa.jpg");
    background-size: 100%,100%;
}
.all-child1{
    position: absolute;
    display: inline-block;
}
.span1{
   padding-left:40px;
   font-size: 30px;
   font-weight: bold;
   color: rgb(255, 255, 255);
}
.span-a1{
    margin-left:50px;
}
.span-a1:hover{
    cursor:pointer;
    color: rgb(0, 174, 255) !important;
}

.span2{
    margin-left:9px;
    font-size: 22px;
    color: rgb(255, 255, 255);
}
.span2:hover{
    cursor:pointer;
    color: rgb(0, 174, 255);
}
.span3{
    color: rgb(255, 255, 255);
}
</style>
