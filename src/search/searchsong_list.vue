<template>
    <div class="table">
            <span class="table-p1">搜索到"</span>
            <span class="table-p1" style="color:rgb(16, 174, 253);">{{this.$store.state.searchvalue}}</span>
            <span class="table-p1">"相关歌单</span>
            <el-divider class="table-divider"></el-divider>
            <div>
                <el-table :data="tableData" size="mini" @row-click="handleRowChange" @selection-change="handleSelectionChange">
                    <el-table-column label="图片"  width="100px"></el-table-column>
                    <el-table-column class="column1" prop="name" label="歌单名">
                    </el-table-column>
                </el-table>
            </div>
        </div>
</template>
<script>
import {Download} from "@element-plus/icons"
export default{
    data(){
        return{
            tableData:[{}],
            multipleSelection: [],
        }
    },
    components:{
        Download,
    },
    methods:{
        handleRowChange(){
        },
        getSong(){
            
        }
    }

}
</script>
<style scoped>
.table-image:hover{
    color:rgb(16, 174, 253);
    cursor:pointer;
}
.table-p1{
    margin-top: 25px;
    font-size: 18px;
    color: rgb(36, 36, 36);
    display: inline-block;
}
.table{
    display: inline-block;
    position: absolute;
    width:65%;
    margin-left: 20px;
}
</style>
