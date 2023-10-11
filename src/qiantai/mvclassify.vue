<template>
<div style="height:900px">
    <div class="demo">
        <div class="demo-car">
            <el-carousel indicator-position="outside" height="320px">
                <el-carousel-item v-for="item in mvimg" :key="item">
                    <img :src="item" w height="320" style="width:100%;">
                </el-carousel-item>
            </el-carousel>
        </div>
        <div class="demo-bang">
            <button class="button1" disabled>MV热播总排行</button>
            <button class="button2">更多</button>
            <div class="demo-tab">
                <div v-for="item in mvdata1" :key="item" >
                    <div class="demo-span1" @click="playMv(item.url,item.introduce)">
                        <span style="margin-left:20px;">{{item.id1}}</span>
                        <span style="margin-left:20px;">{{item.name}}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="partall">
        <div style="width:185px;display: inline-block;">
            <button class="btn3" disabled>MV分类</button><br>
            <button style="margin-top:10px;" class="btn4" disabled>新歌推荐</button><br>
            <button class="btn4" disabled>华语精选</button><br>
            <button class="btn4" disabled>日韩精选</button><br>
            <button class="btn4" disabled>欧美精选</button>
        </div>
        <div style="display: inline-block;position: absolute;margin-left:20px;">
            <div class="demo-image1">
                <div class="block1" v-for="item in mvdata1" :key="item">
                    <img @click="playMv(item.url,item)" :src="getpic(item.pic)" style="border-radius: 15px;" width="150" height="80">
                    <el-tooltip :content="item.introduce" placement="bottom" effect="light">
                        <span style="marfin-left:6px;">{{item.name}}</span>
                    </el-tooltip>
                </div>
            </div>
        </div>
    </div>
    <div class="pagination">
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
export default{
    data(){
        return{
            dataTable:[{}],
            total1:100,
            pageSize:20,
            currentPage:1,
            mvimg:['/img/mv1.jpg','/img/mv2.jpg','/img/mv3.jpg','/img/mv4.jpg','/img/mv5.jpg','/img/mv6.jpg'],
            mvdata1:[{}],
            
        }
    },
    created: function(){
        this.getMvdetail()
    },
    methods:{
        getpic(val){
            let url = "http://localhost:8081/zf/"+val
            return url
        },
        playMv(val1,val2){
        this.$store.commit("getVideourl",val1)
        this.$store.commit("getvideodetail",val2)
        this.$router.push({path: '/mv'})

        },
        getMvdetail(){
            this.$axios
            .get('/getMvDetail?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
                this.mvdata1=successResponse.data.mv
                this.total1 = successResponse.data.total
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>

<style scoped>
.pagination{
    margin-top:270px;
    margin-left:300px
}
.btn4{
    border:none;
    height: 35px;
    color: #000000;
    font-size: 17px;
    width: 185px; 
}
.btn4:hover{
    color: #ffffff;
    background-color: rgb(14, 192, 247);
}
.btn3{
    border:none;
    height: 30px;
    background-color: #9d9d9d;
    color: #ffffff;
    font-size: 17px;
    width: 185px;
}
.demo-span1{
    color: #ffffff;
    height: 28px;
}
.demo-span1:hover{
    background-color:#bdbdbd;
    cursor:pointer;
}
.demo-tab{
    height: 287px;
    margin-top:3px;
}
.button2{
    border:none;
    margin-left:3px;
    height: 30px;
    background-color: #232323;
    color: #ffffff;
    font-size: 17px;
    width: 80px;
}
.button1{
    border: none;
    color: #475669;
    font-size: 17px;
    background-color: #e9c170;
    width: 240px;
    height: 30px;
    padding-right:110px;
}
.demo-bang{
    position: absolute;
    display: inline-block;
    margin-left:20px;
    overflow-y: scroll;
    background-color: #232323;
}
.demo-bang::-webkit-scrollbar{
    display: none;
}
.demo-car{
    display: inline-block;
}
.demo{
    margin-top: 80px;
    margin-left: 270px;
}
.partall{
    margin-left: 270px;
}
.el-carousel__item h3 {
  display: flex;
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}
.el-carousel{
    width:640px;
}
/* .el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
} */
.demo-image{
    background-color: #475669;
}
.block1{
  margin-left:10px;
  margin-top:10px;
  /* text-align: center; */
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 20%;
  box-sizing: border-box;
  vertical-align: top;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  border-radius: 15px;
}
.demo-image1{
    width: 800px;
}
.block1{
    width:150px;
}
.block1:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    /* background-color: #e2f4ff; */
    /* opacity:0.5; */
    cursor:pointer;
    color: rgb(20, 142, 241);
}
/* .demo-image .block:last-child {
  border-right: none;
} */
</style>