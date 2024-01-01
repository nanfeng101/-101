<template>
<p>热点文章</p>
<el-container>
    <el-aside style="width:680px;">
    <div>
        <el-table :data="tableData" style="width: 100%" max-height="auto">
        <el-table-column fixed prop="id" label="编号" width="100" />
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
        <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">
            <el-button
            link
            type="primary"
            size="small"
            @click="deleteRow(scope.row.id)"
            >
            Remove
            </el-button>
        </template>
        </el-table-column>
    </el-table>
    <el-button class="mt-4" style="width: 100%" @click="onAddItem"
        >添加</el-button
    >
    </div>
    </el-aside>
    <el-main style="width:480px;margin-top:-20px;">
    <div style="width:480px;">
        <el-calendar>
            <template #date-cell="{ data }">
            <p :class="data.isSelected ? 'is-selected' : ''">
                {{ data.day.split('-').slice(1).join('-') }}
                {{ data.isSelected ? '✔️' : '' }}
            </p>
            </template>
        </el-calendar>
    </div>
    </el-main>
</el-container>
<el-dialog title="添加头条文章" v-model="dialogVisible">
<el-input v-model="value" @keyup.enter="searchBiaotiText()" placeholder="请输入文章标题"></el-input>
<el-table :data="tableData1" stripe style="width: 100%">
    <el-table-column prop="create_time1" label="Date" width="180" />
    <el-table-column prop="biaoti" label="Name" width="430" />
    <el-table-column label="Address" >
        <template #default="scope">
            <el-button @click="addRow(scope.row.id)" size="small" type="primary">添加</el-button>
        </template>
    </el-table-column>
</el-table>
</el-dialog>
</template>
<script>
import { ElMessage } from 'element-plus'
export default{
    data(){
        return{
            tableData:[{}],
            tableData1:[{}],
            biaoqian1:[{}],
            dialogVisible:false,
            value:'',
        }
    },
    created: function(){
        this.getTouTiapStatus(1,15)
        this.handle()
    },
    methods:{
        searchBiaotiText(){
            this.$axios
            .get("/searchBiaotiText?str="+this.value)
            .then(successResponse => {
                this.tableData1=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        onAddItem(){
            if(this.tableData.length>=15){
                ElMessage({
                        showClose: true,
                        message: '头条文章数已满',
                        type: 'error',
                    })
            }else{
                this.dialogVisible=true
            }
        },
        addRow(id){
            this.$axios
            .get("/updateStatusRedian?n="+1+'&&id='+id)
            .then(successResponse => {
                if(successResponse.data==1){
                    ElMessage({
                        showClose: true,
                        message: '添加成功',
                        type: 'success',
                    })
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '添加失败',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        deleteRow(id){
            this.$axios
            .get("/updateStatusRedian?n="+0+'&&id='+id)
            .then(successResponse => {
                if(successResponse.data==1){
                    ElMessage({
                        showClose: true,
                        message: '移除成功',
                        type: 'success',
                    })
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '移除失败',
                        type: 'error',
                    })
                }
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getTouTiapStatus(val,i){
            let a=val
            this.$axios
            .get("/getReiDianStatus?current="+a+'&&pageSize='+i)
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