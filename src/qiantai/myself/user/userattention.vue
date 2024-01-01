<template>
    <div style="background-color: rgb(255, 255, 255);margin-top:-20px">
        <div style="padding-top:15px;">
            <span @click="com(1)" id="p1" style="margin-left:15px;">关注的人</span>
            <span @click="com(2)" id="p2" style="margin-left:20px;">粉丝</span>
            <el-divider></el-divider>
        </div>
        <div v-show="show1" v-for="item in attentiondata" :key="item">
            <div class="all-a1">
                <div style="display: inline-block;margin-left:15px;">
                    <img @click="userhome(item.attention_id)" :src="geturl(item.pic)" width="80" height="80" class="img">
                </div>
                <div style="display: inline-block;margin-left:15px;width:900px;">
                    <span>{{item.username}}</span><br>
                    <span>{{item.introduce}}</span>
                </div>
                <div style="display: inline-block;">
                    <button @click="delete1(item.user_id,item.attention_id)" class="button1">已关注</button>
                </div>
            </div>
            <el-divider></el-divider>
        </div>
        <div v-show="show2" v-for="item in fensidata" :key="item">
            <div class="all-a1">
                <div style="display: inline-block;margin-left:15px;">
                    <img @click="userhome(item.fensi_id)" :src="geturl(item.pic)" width="80" height="80" class="img">
                </div>
                <div style="display: inline-block;margin-left:15px;width:900px;">
                    <span>{{item.username}}</span><br>
                    <span>{{item.introduce}}</span>
                </div>
            </div>
            <el-divider></el-divider>
        </div>
    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
export default{
    data(){
        return{
            attentiondata:[{}],
            fensidata:[{}],
            show1:true,
            show2:false,
        }
    },
    created: function(){
        this.getattention()
        this.getfensi()
    },
    methods:{
        com(val){
            if(val==1){
                document.getElementById("p1").style.color="coral"
                document.getElementById("p2").style.color="black"
                this.show1=true
                this.show2=false
            }
            else{
                document.getElementById("p2").style.color="coral"
                document.getElementById("p1").style.color="black"
                this.show2=true
                this.show1=false
            }
        },
        delete1(id1,id2){
            this.$axios
            .get("/deleteUserAttention?user_id="+id1+'&&attention_id='+id2)
            .then(successResponse => {
                if(successResponse.data == 'ok'){
                    ElMessage({
                        showClose: true,
                        message: '删除成功',
                        type: 'success',
                    })

                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '删除成功',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
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
        geturl(val){
            let url = 'http://124.220.16.147:8011/zf/'+val
            return url

        },
        getfensi(){
            this.$axios
            .get('/getUserFensi?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.fensidata=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getattention(){
            this.$axios
            .get('/getUserAttention?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.attentiondata=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>

<style scoped>
.button1{
    font-size:17px;
    color: rgb(141, 141, 141);
    border-radius: 20px;
    border: rgb(189, 189, 189) solid 1px;
}
.button1:hover{
    border: rgb(111, 111, 111) solid 1px;
    cursor: pointer;
}
#p1{
    color: coral;
}
#p1:hover{
    cursor: pointer;
}
#p2:hover{
    cursor: pointer;
}
.img{
    border-radius: 50%;
}
.all-a1{
    align-items: center;
    display: flex;
}
</style>