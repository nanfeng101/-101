<template>
<div class="all">
    <p style="margin-left:15px;padding-top:10px;">足迹</p>
    <el-divider></el-divider>
    <div>
        <el-timeline>
            <el-timeline-item  :timestamp="item.time1" v-for="item in time" :key="item" placement="top" color="rgb(193, 193, 193)">
                <div v-for="item1 in user_browse" :key="item1">
                    <div v-if="item1.time==item.time">
                    <el-card @click="entrytetx(item1)" v-if="item1.iscover==1" style="width:1000px;">
                        <div style="display: inline-block;">
                            <img :src="geturl(item1.pic)" width="130" height="70">
                        </div>
                        <div style="display: inline-block;margin-left:15px;">
                            <h4>{{item1.biaoti}}</h4>
                            <p>{{item1.username}}</p>
                        </div>
                    </el-card>
                    <el-card @click="entrytetx(item1)" v-else style="width:1000px;">
                        <h4>{{item1.biaoti}}</h4>
                        <p>{{item1.username}}</p>
                    </el-card>
                    </div>
                </div>
            </el-timeline-item>
        </el-timeline>
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
</template>
<script>
export default{
    data(){
        return{
            user_browse:[{}],
            currentPage:1,
            total1:0,
            pageSize:10,
            browse_current:{},
            time:[{}],
        }
    },
    created: function(){
        this.getUser_browse()
    },
    methods:{
        //改变观看数量
        updateview(id,sum){
            this.$axios
            .get("/updateview?user_id="+this.$store.state.userid1+'&&text_id='+id+'&&view_sum='+sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        entrytetx(item){
            this.$axios
            .get('/selectIdText?id='+item.text_id)
            .then(successResponse => {
                // this.$store.commit("getBiaoqian",this.biaoqian1)
                let text=successResponse.data
                this.$store.commit("getText",text) 
                this.updateview(text.id,text.view_sum)
                this.$router.push({path: '/textdetail'})
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        geturl(val){
            let url = 'http://124.220.16.147:8011/zf/'+val
            return url
        },
        handleCurrentChange(val){
            this.currentPage=val
            this.getUser_browse()
        },
        getUser_browse(){
            this.browse_current.id=this.$store.state.userid1
            this.browse_current.current=this.currentPage
            this.browse_current.page=this.pageSize
            this.$axios
            .post('/getUserBrowse',this.browse_current)
            .then(successResponse => {
                this.user_browse=successResponse.data.user_browse
                this.time=successResponse.data.time
                this.total1=successResponse.data.sum
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>

<style scoped>
.all{
    background-color: rgb(255, 255, 255);
    margin-top:-40px;
}
.pagination{
    display: flex;
    text-align: center;
    justify-content:center;
}
</style>