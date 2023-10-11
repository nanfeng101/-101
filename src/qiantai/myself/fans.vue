<template>
    <div style="margin-left:130px;margin-top:20px;">
        <div class="demo-image1">
            <div class="block1" v-for="item in this.fans" :key="item">
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
            fans:[{}],
            // singer_sum:0,
            // consumer_sum:0,
            // show1:false,
            // show2:true,
        }
    },
    created: function(){
        this.hand1()
    },
    methods:{
        geturl(url){
            let url1="http://localhost:8081/zf/"+url
            return url1
        },
        hand1(){
            let id = this.$store.state.userid1
            this.$axios
            .get('/getSingerDetail?id=' + id)
            .then(successResponse=>{
                this.fans=successResponse.data.fans
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