<template>
<p>文章审核</p>
<el-container>
    <el-aside style="width:880px;">
        <el-table :data="tableData" style="width: 100%" max-height="auto">
            <el-table-column fixed prop="usermame" label="用户" width="200" />
            <el-table-column prop="create_time1" label="Date" width="200" />
            <el-table-column prop="biaoti" label="标题" width="420" />
            <el-table-column prop="type" label="类型" width="120" >
                <template #default="scope">
                    <button class="button1">{{scope.row.type}}</button>
                </template>
            </el-table-column>
            <el-table-column label="标签" width="420" >
                <template #default="scope">
                    <div class="biaoqian1" style="margin-left:0px;" v-for="item1 in biaoqian1" :key="item1">
                        <div class="biaoqian1" style="" v-if="scope.row.id==item1.id" v-for="item2 in item1.list" :key="item2">
                            <button style="margin-left:5px;"  aria-disabled="true" class="button2">{{item2}}</button>
                        </div>
                    </div>
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="80">
            <template #default="scope">
                <el-button
                link
                type="primary"
                size="small"
                @click="detail(scope.row,'detailbutton')"
                >
                查看
                </el-button>
            </template>
            </el-table-column>
            <el-table-column fixed="right" label="审核" width="120">
                <template #default="scope">
                    <el-button @click="updateExamine(scope.row.id)" type="success" size="small" circle><el-icon><Check /></el-icon></el-button>
                    <el-button @click="enterclose(scope.row)" type="danger" size="small" circle><el-icon><Close /></el-icon></el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-aside>
    <el-main style="margin-top:-20px;">
        <iframe class="ifa" src="/html/colck.html"></iframe>
    </el-main>
</el-container>
<el-dialog title="请输入驳回原因" v-model="dialogVisible">
<el-input v-model="user_chat.message"></el-input>
<div style="margin-top:15px;">
    <el-button @click="closeReason()" type="primary">确定</el-button>
    <el-button @click="this.dialogVisible=false">取消</el-button>
</div>
</el-dialog>
</template>
<script>
import {Check,Close} from "@element-plus/icons"
import { ElMessage } from 'element-plus'
export default{
    data(){
        return{
            tableData:[{}],
            biaoqian1:[{}],
            dialogVisible:false,
            user_chat:{},
        }
    },
    components:{
        Check,
        Close,
    },
    created:function(){
        this.getAllExamineStatus()
        this.handle()
    },
    methods:{
        closeReason(){
            this.$axios
            .post("/closeReason",this.user_chat)
            .then(successResponse => {
                if(successResponse.data=='ok'){
                    this.getAllExamineStatus()
                    ElMessage({
                        showClose: true,
                        message: '驳回审核成功',
                        type: 'success',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        updateExamine(id){
            this.$axios
            .get("/updateExamine?id="+id)
            .then(successResponse => {
                if(successResponse.data=='ok'){
                    this.getAllExamineStatus()
                    ElMessage({
                        showClose: true,
                        message: '审核通过成功',
                        type: 'success',
                    })
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '审核通过失败',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        enterclose(val){
            this.dialogVisible=true
            this.user_chat.user_id=val.user_id
            this.user_chat.text_id=val.id
        },
        detail(item,con){
            this.$store.commit("getText",item)
            this.$store.commit("getCurrentCom",con)
        },
        getAllExamineStatus(){
            this.$axios
            .get("/getAllExamineStatus")
            .then(successResponse => {
                this.tableData=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        handle(){
            this.$axios
            .get("/getAllTextDetail")
            .then(successResponse => {
                this.biaoqian1=successResponse.data.biaoqian
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>
<style scoped>
.ifa{
    height: 200px;
    width: 200px;
    border: none;
    margin-top:-20px;
    margin-left:80px;
}
.button1{
    border:none;
    background-color: rgb(243, 142, 105);
    color: aliceblue;
}
.button2{
    border:none;
    background-color: rgb(176, 243, 105);
    color: rgb(157, 158, 159);
}
.biaoqian1{
    display: inline-flex;
}
</style>
