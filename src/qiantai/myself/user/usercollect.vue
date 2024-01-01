<template>
    <div class="all">
        <el-container>
            <el-aside style="width:200px;">
                <el-menu 
                    style="min-height:100%;height:700px"
                    :default-active="activeIndex"
                    class="el-menu-demo" 
                    :collapse="isCollapse"
                    @select="handleSelect"
                    background-color=rgb(255,255,255)
                    text-color=#000000
                    active-text-color=red>
                    <div class="add">
                        <el-icon style="margin-left:15px;margin-top:15px;"><FolderAdd /></el-icon>
                        <span @click="addcollect()" style="margin-left:10px;">新建收藏夹</span>
                    </div>
                    <div v-for="item in collectdata" :key="item">
                        <el-menu-item @click="com(item)" :index="item.id" ><el-icon><Folder /></el-icon><span style="margin-left:10px;">{{item.collect_name}}</span></el-menu-item>
                    </div>
                </el-menu>
            </el-aside>
            <el-main>
                <div>
                    <span v-show="!show1">{{this.collect1.collect_name}}</span>
                    <el-input v-show="show1" v-model="collect2.collect_name" placeholder="请添加收藏夹标题" type="small" style="width:300px;"></el-input>
                    <el-button @click="update()" v-show="show1" type="success" size="small" circle style="margin-left:15px;"><el-icon><Check /></el-icon></el-button>
                    <el-button @click="this.show1=false" v-show="show1" type="danger" size="small" circle style="margin-left:15px;"><el-icon><Close /></el-icon></el-button>
                    <el-icon @click="xianshi(1)" style="margin-left:10px;font-size:15px;"><Edit /></el-icon>
                </div>
                <div style="margin-top:15px;">
                    <span v-show="!show2">{{this.collect1.collect_miaoshu}}</span>
                    <el-input v-show="show2" v-model="collect2.collect_miaoshu" placeholder="请添加收藏夹描述" type="small" style="width:300px;"></el-input>
                    <el-button @click="update()" v-show="show2" type="success" size="small" circle style="margin-left:15px;"><el-icon><Check /></el-icon></el-button>
                    <el-button @click="this.show2=false" v-show="show2" type="danger" size="small" circle style="margin-left:15px;"><el-icon><Close /></el-icon></el-button>
                    <el-icon @click="xianshi(2)" style="margin-left:10px;font-size:15px;"><Edit /></el-icon>
                </div>
                <div v-for="item in collectTextdata" :key="item">
                    <div class="text">
                        <div @click="entrytetx(item)" style="margin-left:20px;display: inline-flex;width:700px;">
                            <p>{{item.biaoti}}</p>
                        </div>
                        <div style="display: inline-flex;">
                            <el-icon id="icon1" @click="deleteCollect(item)"><StarFilled /></el-icon>
                        </div>
                    </div>
                </div>
            </el-main>
        </el-container>
    </div>
    <el-dialog width="300px" v-model="dialogVisible" title="新建收藏夹">
        <div>
            <p>标题</p>
            <el-input v-model="collect.collect_name" placeholder="请添加收藏夹标题" type="small" style="width:200px;"></el-input>
        </div>
        <div>
            <P>描述（选填）</P>
            <el-input v-model="collect.collect_miaoshu" placeholder="请输入描述" type="small" style="width:200px"></el-input>
        </div>
        <div>
            <el-button @click="add()" type="paimary" size="small">确定</el-button>
            <el-button @click="quxiao()" size="small">取消</el-button>
        </div>
    </el-dialog>
</template>
<script>
import { ElMessage } from 'element-plus'
import {FolderAdd,Folder,Edit,Check,Close,StarFilled} from "@element-plus/icons"
export default{
    data(){
        return{
            collectdata:[{}],
            collectTextdata:[{}],
            dialogVisible:false,
            collect:{},
            collect1:{},
            collect2:{},
            show1:false,
            show2:false,
        }
    },
    components:{
        FolderAdd,
        Folder,
        Edit,
        Check,
        Close,
        StarFilled,
    },
    created: function(){
        this.getCollectName()
        this.getCollect_text('我的收藏夹')

    },
    methods:{
        updateview(id,sum){
            this.$axios
            .get("/updateview?user_id="+this.$store.state.userid1+'&&text_id='+id+'&&view_sum='+sum)
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        addUserBrowse(val){
            var user_browse={}
            user_browse.user_id=this.$store.state.userid1
            user_browse.text_id=val
            this.$axios
            .post("/addUserBrowse",user_browse)
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
                this.addUserBrowse(text.id) 
                this.updateview(text.id,text.view_sum)
                this.$router.push({path: '/textdetail'})
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        deleteCollect(val){
            this.$axios
            .get('/deleteCollect?user_id='+this.$store.state.userid1+'&&text_id='+val.text_id)
            .then(successResponse => {
                let id = this.collectTextdata.indexOf(val)
                this.collectTextdata.splice(id,1)
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
        update(){
            this.$axios
            .post('/updateCollect_name',this.collect)
            .then(successResponse => {
                if(successResponse.data == 'ok'){
                    this.dialogVisible=false
                    this.getCollectName()
                    ElMessage({
                        showClose: true,
                        message: '修改成功',
                        type: 'success',
                    })

                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '修改成功',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        xianshi(i){
            if(i==1){
                this.show1=true
            }
            else{
                this.show2=true
            }
        },
        com(val){
            this.collect1=val
            this.getCollect_text(val.collect_name)
        },
        getCollect_text(val){
            this.$axios
            .get('/addCollect_text?id='+this.$store.state.userid1+'&&name='+val)
            .then(successResponse => {
                this.collectTextdata=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        add(){
            this.collect.user_id=this.$store.state.userid1
            this.$axios
            .post('/addCollect',this.collect)
            .then(successResponse => {
                if(successResponse.data == 'ok'){
                    this.dialogVisible=false
                    this.getCollectName()
                    ElMessage({
                        showClose: true,
                        message: '添加成功',
                        type: 'success',
                    })

                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '添加成功',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        quxiao(){
            this.dialogVisible=false
        },
        addcollect(){
            this.dialogVisible=true
        },
        getCollectName(){
            this.$axios
            .get('/selectCollectName?id='+this.$store.state.userid1)
            .then(successResponse => {
                this.collectdata=successResponse.data
                this.collect1=successResponse.data[0]
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>
<style scoped>
.add{
    height:50px;
}
.add:hover{
    cursor: pointer;
    background-color: rgb(168, 168, 168);
}
.text{
    height: 50px;
    border: rgb(200, 200, 200) solid 1px;
    border-radius: 10px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    margin-top:15px;
}
.text:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    cursor:pointer; 
}
.all{
    margin-top:-20px;
    background-color: rgb(255, 255, 255);
    height: 700px;
    width:1000px;
}
#icon1{
    color:#ffd727;
    font-size:25px;
}
</style>
