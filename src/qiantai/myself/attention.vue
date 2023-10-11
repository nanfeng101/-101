<template>
    <div style="margin-left:100px;margin-top:20px;">
        <span id="b1" @click="singer1()" style="color:rgb(0, 174, 255)" >歌手{{this.singer_sum}}</span>
        <span id="b2" @click="consumer1()">用户{{this.consumer_sum}}</span>
    </div>

    <div v-show="show1" style="margin-left:130px;margin-top:20px;">
        <div class="demo-image1">
            <div class="block1" v-for="item in this.singer" :key="item">
                <img @click="singerReplace(item.id)" class="img1" :src="geturl(item.pic)" width="150" height="150"><br>
                <div style="text-align:center;display:flex;justify-content:center;">
                <p class="span1">{{item.name}}</p><br>
                </div>
                <div style="text-align:center;display:flex;justify-content:center;">
                <p class="span2">{{item.attention_sum}}人关注</p>
                </div>
            </div>
        </div>
    </div>

    <div v-show="show2" style="margin-left:130px;margin-top:20px;">
        <div class="demo-image1">
            <div class="block1" v-for="item in this.consumer" :key="item">
                <img class="img1" :src="geturl(item.avator)" width="150" height="150"><br>
                <div style="text-align:center;display:flex;justify-content:center;">
                <p class="span1">{{item.username}}</p><br>
                </div>
                <div style="text-align:center;display:flex;justify-content:center;">
                <p class="span2">{{item.email}}</p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default{
    data(){
        return{
            singer:[{}],
            consumer:[{}],
            singer_sum:0,
            consumer_sum:0,
            show1:true,
            show2:false,
        }
    },
    created: function(){
        this.hand1()
    },
    methods:{
        singerReplace(id1){
        this.$store.commit('addSong_listId', id1)
        this.$router.push({path: '/songdetail'})
        },
        singer1(){
            this.show1=true
            this.show2=false
            document.getElementById("b1").style.color = 'rgb(0, 174, 255)'
            document.getElementById("b2").style.color = 'rgb(0, 0, 0)'
        },
        consumer1(){
            this.show1=false
            this.show2=true
            document.getElementById("b2").style.color = 'rgb(0, 174, 255)'
            document.getElementById("b1").style.color = 'rgb(0, 0, 0)'
        },
        geturl(url){
            let url1="http://localhost:8081/zf/"+url
            return url1
        },
        hand1(){
            let id = this.$store.state.userid1
            this.$axios
            .get('/getSingerDetail?id=' + id)
            .then(successResponse=>{
                this.singer=successResponse.data.singer
                this.consumer=successResponse.data.consumer
                this.singer_sum=successResponse.data.singer_sum
                this.consumer_sum=successResponse.data.aa
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
    border-radius: 50%;
    margin-left:40px;
    margin-top:30px;
}
span{
    margin-left:50px;
}
span:hover{
    color: rgb(0, 183, 255) !important;
    cursor:pointer;
}
.demo-image{
    background-color: #475669;
}
.block1{
  margin-left:20px;
  margin-top:10px;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 25%;
  box-sizing: border-box;
  vertical-align: top;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  border-radius: 15px;
}
.demo-image1{
    width: 1100px;
}
.block1{
    width:230px;
    height: 300px;
    background-color: rgb(242, 250, 251);
}
.block1:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    /* background-color: #e2f4ff; */
    /* opacity:0.5; */
    cursor:pointer;
    color: rgb(20, 142, 241);
}
</style>