<template>
    <p>博客数据</p>
    <el-row :gutter="20" class="mgb20" style="margin-left:10px;">
        <el-col :span="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}" > 
        <div class="grid-content grid-con-1">
            <div class="grid-cont-right">
            <div class="grid-num">{{this.textsum}}</div>
            <div>文章总数</div>
            </div>
        </div>
        </el-card>
        </el-col>
        <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
            <div class="grid-content grid-con-1">
                <div class="grid-cont-right">
                <div class="grid-num">{{this.allview}}</div>
                <div>总阅读量</div>
                </div>
            </div>
            </el-card>
        </el-col>
        <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
            <div class="grid-content grid-con-2">
                <div class="grid-cont-right">
                <div class="grid-num">0</div>
                <div>总评论量</div>
                </div>
            </div>
            </el-card>
        </el-col>
        <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
            <div class="grid-content grid-con-3">
                <div class="grid-cont-right">
                <div class="grid-num">{{this.alldianzan}}</div>
                <div>总点赞量</div>
                </div>
            </div>
            </el-card>
        </el-col>
        <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}" >
            <div class="grid-content grid-con-4">
                <div class="grid-cont-right">
                <div class="grid-num">0</div>
                <div>粉丝量</div>
                </div>
            </div>
            </el-card>
        </el-col>
        <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
            <div class="grid-content grid-con-4">
                <div class="grid-cont-right">
                <div class="grid-num">{{this.allcollect}}</div>
                <div>总收藏数</div>
                </div>
            </div>
            </el-card>
        </el-col>
    </el-row>
    <div style="margin-top:15px;align-items: center;display: flex;">
        <span>全部文章</span>
        <el-tooltip
        class="box-item"
        effect="dark"
        content="统计帐号下所有文章在七天内的每天数据"
        placement="bottom-start"
        >
        <el-icon style="font-size:18px;margin-left:5px;color:#999;"><QuestionFilled /></el-icon>
        </el-tooltip>
    </div>
    <B style="margin-top:15px;"></B>
</template>
<script>
import {QuestionFilled} from "@element-plus/icons"
import B from '../manage/echarts/b.vue'
export default{
    data(){
        return{
            addText:[{}],
            textsum:0,
            visible:false,
            title:'',
            allview:0,
            alldianzan:0,
            allcollect:0,
        }
    },
    created: function(){
        this.getUserIdText()
    },
    components:{
        B,
        QuestionFilled,
    },
    methods:{
        detaildata(val){
            this.visible=true
            this.text=val
            this.$store.commit("getText",val)
        },
        geturl(val){
            let url='http://124.220.16.147:8011/zf/'+val
            return url
        },
        getUserIdText(){
            let item=this.$store.state.userid1
            this.$axios.get('/getUserIdText?id='+item)
            .then(successResponse => {
                this.addText=successResponse.data
                this.$store.commit("getUsertext",successResponse.data)
                this.textsum=this.addText.length
                for(let item of this.addText){
                    this.allview=this.allview+item.view_sum
                    this.alldianzan=this.alldianzan+item.dianzan_sum
                    this.allcollect=this.allcollect+item.collect_sum
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
    }

}
</script>
<style scoped>
.grid-content {
    display: flex;
    align-items: center;
    height: 100px;
  }

  .grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
  }

  .grid-num {
    font-size: 30px;
    font-weight: bold;
  }
</style>