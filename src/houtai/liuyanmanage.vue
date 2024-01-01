<template>
    <p>留言管理</p>
    <el-table :data="tableData" style="width: 100%" max-height="auto">
        <el-table-column prop="username" label="用户名" width="200"></el-table-column>
        <el-table-column prop="content" label="内容" width="700"></el-table-column>
        <el-table-column label="操作" width="100">
            <template #default="scope">
                <el-button @click="deleteLiuyan(scope.row.id)" type="primary">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>
<script>
export default{
    data(){
        return{
            tableData:[{}]
        }
    },
    created: function(){
        this.getUserLiuyan()
    },
    methods:{
        deleteLiuyan(val){
            this.$axios
            .get("/deleteLiuyan?id="+val)
            .then(successResponse => {
                this.tableData=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getUserLiuyan(){
            this.$axios
            .get("/getUserLiuyan")
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
