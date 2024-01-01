<template>
<p>用户管理</p>
<el-table :data="tableData" style="width: 100%">
    <el-table-column fixed prop="username" label="用户名" width="150" />
    <el-table-column prop="sex" label="性别" width="120" />
    <el-table-column prop="phone" label="手机号" width="120" />
    <el-table-column prop="email" label="邮箱" width="180" />
    <el-table-column prop="introduce" label="简介" width="400" />
    <el-table-column prop="birth1" label="出生日期" width="120" />
    <el-table-column prop="location" label="位置" width="120" />
    <el-table-column fixed="right" label="是否禁用" width="120">
    <template #default="scope">
        <el-switch @click="updateUserDisable(scope.row)" v-model="scope.row.disable" />
    </template>
    </el-table-column>
    <el-table-column fixed="right" label="Operations" width="200">
    <template #default="scope">
        <el-button style="margin-left:10px;" link type="primary" size="small" @click="handleClick"
        >Detail</el-button
        >
        <el-button link type="primary" size="small">Edit</el-button>
    </template>
    </el-table-column>
</el-table>
</template>
<script>
import { ElMessage } from 'element-plus'
export default{
    data(){
        return{
            tableData:[{}],
        }
    },
    created: function(){
        this.getAlluser()
    },
    methods:{
        updateUserDisable(val){
            if(!val.disable){
                this.$axios
                .get("/updateUserDisable?id="+val.id+"&&disable="+false)
                .then(successResponse => {
                    if(successResponse.data=='ok'){
                        ElMessage({
                            showClose: true,
                            message: `${val.username}用户禁用成功`,
                            type: 'success',
                        })
                        let a=this.tableData.indexOf(val)
                        this.tableData[a].disable=false
                    }
                    else{
                        ElMessage({
                            showClose: true,
                            message: '禁用失败',
                            type: 'error',
                        })
                    }
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
            }
            else{
                this.$axios
                .get("/updateUserDisable?id="+val.id+"&&disable="+true)
                .then(successResponse => {
                    if(successResponse.data=='ok'){
                        ElMessage({
                            showClose: true,
                            message: `${val.username}用户禁用取消成功`,
                            type: 'success',
                        })
                        let a=this.tableData.indexOf(val)
                        this.tableData[a].disable=true
                    }
                    else{
                        ElMessage({
                            showClose: true,
                            message: '禁用失败',
                            type: 'error',
                        })
                    }
                })
                .catch(failResponse => {
                this.$alert(failResponse.response.status)
                })
            }
        },
        getAlluser(){
            this.$axios
            .get("/getAllUser")
            .then(successResponse => {
                this.tableData=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>
