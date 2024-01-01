<template>
    <div id="a">
        <div>
            <span style="color:rgb(178, 178, 178);">状态</span>
            <span id="head1" style="margin-left:20px;color:rgba(234, 108, 11, 0.912);" @click="replace(1)">全部{{this.textsum}}</span>
            <span id="head2" style="margin-left:20px;color:rgb(178, 178, 178);" @click="replace(2)">审核{{this.addExamineText.length}}</span>
            <span id="head3" style="margin-left:20px;color:rgb(178, 178, 178);" @click="replace(3)">草稿箱{{this.addTextDraft.length}}</span>
        </div>
        <div style="diplay:flex;margin-top:15px;">
            <el-input size="small" style="width:300px;" placeholder="请输入关键词"></el-input>
            <button>搜索</button>
        </div>
        <div style="margin-top:15px;" v-show="show1">
            <div v-for="item in addText" :key="item" >
                <div v-if="item.iscover" style="margin-top:10px;height:100px;">
                    <div style="display: inline-block;">
                        <div style="display: inline-block;">
                            <img :src="geturl(item.pic)" width="150" height="90">
                        </div>
                        <div style="display: inline-block;width:700px;">
                            <span style="margin-left:20px;">{{item.biaoti}}</span>
                            <div style="margin-top:40px;margin-left:20px;">
                                <span style="color:rgb(178, 178, 178);">阅读{{item.view_sum}}</span>
                                <span style="margin-left:20px;color:rgb(178, 178, 178);">评论</span>
                                <span style="margin-left:20px;color:rgb(178, 178, 178);">收藏</span>
                            </div>
                        </div>
                    </div>
                    <div style="display: inline-block;transform: translate(0,2px);">
                        <span style="color:rgb(178, 178, 178);">{{item.create_time1}}</span>
                        <div style="margin-top:40px;">
                            <span class="p1" @click="detaildata(item)">查看数据</span>
                            <span class="p1" @click="edittext(item)" style="margin-left:20px;">编辑</span>
                            <span @click="entrytetx(item)" class="p1"  style="margin-left:20px;">浏览</span>
                            <span class="p1" style="margin-left:20px;">删除</span>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <div style="display: inline-block;width:850px;">
                        <span style="margin-left:20px;">{{item.biaoti}}</span>
                        <div style="margin-top:40px;margin-left:20px;">
                            <span style="color:rgb(178, 178, 178);">阅读{{item.view_sum}}</span>
                            <span style="margin-left:20px;color:rgb(178, 178, 178);">评论</span>
                            <span style="margin-left:20px;color:rgb(178, 178, 178);">收藏</span>
                        </div>
                    </div>
                    <div style="display: inline-block;transform: translate(0,2px);">
                        <span style="color:rgb(178, 178, 178);">{{item.create_time1}}</span>
                        <div style="margin-top:40px;">
                            <span class="p1" @click="detaildata(item)">查看数据</span>
                            <span class="p1" @click="edittext(item)" style="margin-left:20px;">编辑</span>
                            <span @click="entrytetx(item)" class="p1" style="margin-left:20px;">浏览</span>
                            <span class="p1" style="margin-left:20px;">删除</span>
                        </div>
                    </div>
                </div>
                <el-divider></el-divider>
            </div>
        </div>
        <!-- 待审核文章 -->
        <div style="margin-top:15px;" v-show="show2">
            <div v-for="item in addExamineText" :key="item" >
                <div v-if="item.iscover" style="margin-top:10px;height:100px;">
                    <div style="display: inline-block;">
                        <div style="display: inline-block;">
                            <img :src="geturl(item.pic)" width="150" height="90">
                        </div>
                        <div style="display: inline-block;width:700px;">
                            <span style="margin-left:20px;">{{item.biaoti}}</span>
                            <div style="margin-top:40px;margin-left:20px;">
                                <span class="p1" @click="edittext(item)" style="margin-left:20px;">编辑</span>
                                <span class="p1" style="margin-left:20px;">删除</span>
                            </div>
                        </div>
                    </div>
                    <div style="display: inline-block;transform: translate(0,2px);">
                        <span style="color:rgb(178, 178, 178);">{{item.create_time1}}</span>
                    </div>
                </div>
                <div v-else>
                    <div style="display: inline-block;width:850px;">
                        <span style="margin-left:20px;">{{item.biaoti}}</span>
                        <div style="margin-top:40px;margin-left:20px;">
                            <span class="p1" @click="edittext(item)" style="margin-left:20px;">编辑</span>
                            <span class="p1" style="margin-left:20px;">删除</span>
                        </div>
                    </div>
                    <div style="display: inline-block;transform: translate(0,2px);">
                        <span style="color:rgb(178, 178, 178);">{{item.create_time1}}</span>
                    </div>
                </div>
                <el-divider></el-divider>
            </div>
        </div>
        <!-- 草稿 -->
        <div style="margin-top:15px;" v-show="show3">
            <div v-for="item in addTextDraft" :key="item">
                <div v-if="item.iscover" style="margin-top:10px;height:100px;">
                    <div style="display: inline-block;">
                        <div style="display: inline-block;">
                            <img :src="geturl(item.pic)" width="150" height="90">
                        </div>
                        <div style="display: inline-block;width:700px;">
                            <span style="margin-left:20px;">{{item.biaoti}}</span>
                            <div style="margin-top:40px;margin-left:20px;">
                                <span class="p1" @click="edittext(item)" style="margin-left:20px;">编辑</span>
                                <span class="p1" style="margin-left:20px;">删除</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <div style="display: inline-block;width:850px;">
                        <span style="margin-left:20px;">{{item.biaoti}}</span>
                        <div style="margin-top:40px;margin-left:20px;">
                            <span class="p1" @click="editdraft(item)" style="margin-left:20px;">编辑</span>
                            <span class="p1" style="margin-left:20px;">删除</span>
                        </div>
                    </div>
                </div>
                <el-divider></el-divider>
            </div>
        </div>
    </div>
    <el-drawer v-model="visible" :title="this.text.biaoti" :modal="false" size="65%">
        <template #header="{ close, titleId, titleClass }">
        <h4 :id="titleId" :class="titleClass">{{this.title}}</h4>
        </template>
        <p style="margin-left:20px">发布至今数据</p>
        <el-row :gutter="20" class="mgb20" style="margin-left:10px">
            <el-col :span="6">
                <el-card shadow="hover" :body-style="{padding: '0px'}">
                <div class="grid-content grid-con-1">
                    <div class="grid-cont-right">
                    <div class="grid-num">{{this.text.view_sum}}</div>
                    <div>阅读量</div>
                    </div>
                </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card shadow="hover" :body-style="{padding: '0px'}">
                <div class="grid-content grid-con-2">
                    <div class="grid-cont-right">
                    <div class="grid-num">{{this.commentsum}}</div>
                    <div>评论量</div>
                    </div>
                </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card shadow="hover" :body-style="{padding: '0px'}">
                <div class="grid-content grid-con-3">
                    <div class="grid-cont-right">
                    <div class="grid-num">{{this.text.dianzan_sum}}</div>
                    <div>点赞量</div>
                    </div>
                </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card shadow="hover" :body-style="{padding: '0px'}">
                <div class="grid-content grid-con-4">
                    <div class="grid-cont-right">
                    <div class="grid-num">{{this.text.collect_sum}}</div>
                    <div>收藏量</div>
                    </div>
                </div>
                </el-card>
            </el-col>
        </el-row>
        <A style="margin-top:15px;"></A>
  </el-drawer>
</template>
<script>
import * as echarts from 'echarts'
import A from '../manage/echarts/a.vue'
export default{
    data(){
        return{
            addText:[{}],
            addExamineText:[{}],
            addTextDraft:[{}],
            textsum:0,
            visible:false,
            title:'',
            text:{},
            viewTime:[],
            viewSum:[],
            dianSum:[],
            dianTime:[],
            show1:true,
            show2:false,
            show3:false,
            commentsum:0,
        }
    },
    created: function(){
        this.getUserIdText()
        this.getExamineStatus()
        this.getUserIdTextDraft()
    },
    components:{
        A,
    },
    methods:{
        getTextCommentSum(){
            let i=this.$store.state.text.id
            this.$axios.get('/getTextCommentSum?id='+i)
            .then(successResponse => {
                this.commentsum=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        replace(id){
            if(id==1){
                this.show1=true
                this.show2=false
                this.show3=false
                document.getElementById("head1").style.color="rgba(234, 108, 11, 0.912)"
                document.getElementById("head2").style.color="rgb(178, 178, 178)"
                document.getElementById("head3").style.color="rgb(178, 178, 178)"
            }
            else if(id==2){
                this.show1=false
                this.show2=true
                this.show3=false
                document.getElementById("head2").style.color="rgba(234, 108, 11, 0.912)"
                document.getElementById("head1").style.color="rgb(178, 178, 178)"
                document.getElementById("head3").style.color="rgb(178, 178, 178)"
            }else{
                this.show1=false
                this.show3=true
                this.show2=false
                document.getElementById("head3").style.color="rgba(234, 108, 11, 0.912)"
                document.getElementById("head1").style.color="rgb(178, 178, 178)"
                document.getElementById("head2").style.color="rgb(178, 178, 178)"
            }
        },
        //获得草稿文章
        getUserIdTextDraft(){
            this.$axios.get('/getUserIdTextDraft?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.addTextDraft=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },

        //获得待审核的文章
        getExamineStatus(){
            this.$axios.get('/getExamineStatus?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.addExamineText=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        editdraft(val){
            this.$store.commit("getText",val)
            this.$router.push({path: '/useredittext'})
        },
        edittext(val){
            this.$axios.get('/selectIdText?id='+val.id)
            .then(successResponse => {
                this.$store.commit("getText",successResponse.data)
                this.$router.push({path: '/useredittext'})
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //改变观看数量
        updateview(id,sum){
            this.$axios
            .get("/updateview?user_id="+this.$store.state.userid1+'&&text_id='+id+'&&view_sum='+sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        entrytetx(item){
          this.$store.commit("getBiaoqian",this.biaoqian1)
          this.$store.commit("getText",item) 
          this.updateview(item.id,item.view_sum)
          this.$router.push({path: '/textdetail'})
        },
        detaildata(val){
            this.visible=true
            this.text=val
            this.$store.commit("getText",val)
            this.getTextCommentSum()
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
                this.textsum=this.addText.length
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
    }

}
</script>

<style scoped>
button{
    width: 70px;
    height: 30px;
    border: coral solid 1px;
    background: none;
    color: coral;
    font-weight: bold;
    margin-left:50px;
}
#a{
    height: auto;
}
button:hover{
    background-color: coral;
    color: rgb(255, 255, 255);
}
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
  .p1:hover{
    cursor: pointer;
    color: coral;
  }
  #head1:hover{
    cursor: pointer;
  }
  #head2:hover{
    cursor: pointer;
  }
  #head3:hover{
    cursor: pointer;
  }
</style>