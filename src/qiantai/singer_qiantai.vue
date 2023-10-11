<template>
    <div class="all">
        <div class="all-child1">
            <el-menu
                :default-active="activeIndex"
                style="border-top-left-radius:20px;border-top-right-radius:20px"
                mode="horizontal"
                @select="handleSelect"
            >
                <el-menu-item index="1" @click="com(null)" style="margin-left:20px">全部歌手</el-menu-item>
                <el-menu-item index="2" @click="com(1)">男歌手</el-menu-item>
                <el-menu-item index="3" @click="com(3)">女歌手</el-menu-item>
                <el-menu-item index="4" @click="com(2)">组合</el-menu-item>
            </el-menu>
            <!-- <component :is="currentCom"></component> -->
            <div class="demo-image" style="background-color: #ffffff;">
                <div class="block" v-for="item in songsList" :key="item">
                    <div class="img1" @click="singerReplace(item.id)">
                        <img class="img2" :src="geturl(item.pic)">
                        <p style="font-size:13px">{{item.name}}</p>
                    </div>
                </div>
            </div>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next"
                :total="total1" 
                v-model:currentPage="currentPage" 
                @current-change="handleCurrentChange"
                :page-size="pageSize">
                </el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
// import AllSong_list from "./song_list/allSong_list.vue"
// import HuaSong_list from "./song_list/huaSong_list.vue"
export default{
    name:'song_list_qiantai',
    // components:{
    //     AllSong_list,
    //     HuaSong_list,

    // },
    data(){
        return{
            songsList: [{}],
            currentPage:1,
            pageSize:10,
            total1: 0
        }
    },
    created: function () {
        this.getSong_list()
    },
    methods:{
        com(a){
        this.$store.commit("getSingerSex",a)
        this.getSong_list()
      },
      getSong_list(){
            let sex = this.$store.state.sex
            if(sex === null){
                 this.$axios
                .get('/getSingerPage?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
                .then(successResponse => {
                this.songsList = successResponse.data.singer
                this.total1=successResponse.data.total
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })

            }else{
                this.$axios
                .get('/getSingerPage?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize + '&&sex=' + sex)
                .then(successResponse => {
                this.songsList = successResponse.data.singer
                this.total1=successResponse.data.total
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
            }
        },
        singerReplace(id1){
        this.$store.commit('addSong_listId', id1)
        this.$router.push({path: '/songdetail'})
      },
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        handleCurrentChange(val) {
            this.currentPage = val
            this.getSong_list()
       },
    }
}
</script>
<style scoped>
.all-child1{
    background-color:#ffffff;
    width:1300px;
    margin-left:100px;
    position: absolute;
    margin-top: 30px;
    border-radius: 20px;
}
.all{
    height:1100px;
    background-color:#e0e4ea;
    padding-top: 50px;
}
.demo-image .block {
  padding: 10px 25px;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 20%;
  box-sizing: border-box;
  vertical-align: top;
}
.demo-image .block:last-child {
  border-right: none;
}
.demo-image .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  padding-top: 15px;
}
.img1{
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    overflow: hidden;
    border-radius: 20px;
    background-color: #e0e4ea;
}
.img2{
    width: 249px;
    height: 330px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}
.img2:hover{
    width: 349px;
    height: 430px;
}
.img1:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    background-color: #858f9d;
    opacity:0.5;
    cursor:pointer;
}
.pagination{
    margin-top: 16px;
    /* text-align: center; */
    
}
</style>