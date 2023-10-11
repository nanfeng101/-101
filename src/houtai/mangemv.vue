<template>
    <div class="box-a1">
        <el-button type="primary" size="small" @click="detAll()">批量删除</el-button>
        <el-input v-model="select_word" @keyup="search" size="small" placeholder="筛选关键词" style="margin-left:20px"></el-input>
        <el-button type="primary" @click="ogtenSinger()" size="small" style="margin-left:20px">添加MV</el-button>
    </div>
    <el-table id="table1" ref="multipleTable" :data="tableData" size="mini"  @selection-change="handleSelectionChange" highlight-current-row border style="margin-top:10px;height:480px !important;overflow:scroll !important;"  :header-cell-style="headClass" :cell-style="{ textAlign: 'center' }"
      :default-sort="{ prop: 'id', order: 'descending' }">
        <el-table-column type="selection" width="40px"></el-table-column>
        <el-table-column   width="150px" label="MV图片" align="center">
            <template #default="scope">
            <div>
                <img :src="geturl(scope.row.pic)" alt="未知" style="height:80px;overflow:hidden;width:100%"/>
            </div>
            <el-upload
              class="upload-demo"
              :show-file-list="false"
              :before-upload="beforeAvatarUpload"
              :action="setpic(scope.row.id)"
              >
              <el-button size="mini">更新图片</el-button>
            </el-upload>
            </template>
        </el-table-column>
        <el-table-column prop="name" width="150px" label="名称" align="center"></el-table-column>
        <el-table-column prop="introduce" width="748px" label="介绍" align="center">
        </el-table-column>
        <el-table-column prop="style" width="100px" label="类型" align="center"></el-table-column>
        <el-table-column width="150px" label="操作" align="center">
            <template #default="scope">
            <el-button size="mini" @click="handelUpdate(scope.row.id1)" style="background-color:rgb(13, 165, 236);color:#ffffff">编辑</el-button>
            <el-button size="mini" @click="handelete(scope.row.id1)"  style="background-color:rgba(250, 15, 62, 0.859);color:#ffffff">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div class="pagination">
        <el-pagination background layout="total, prev, pager, next"
        :total="total1" 
        v-model:currentPage="currentPage" 
        @current-change="handleCurrentChange"
        :page-size="pageSize">
        </el-pagination>
    </div>

    <!-- 添加MV -->
    <el-dialog title="添加MV" v-model="centerDialogVisible" width="400px" center>
        <el-foem ref="addForm" :model="addForm">
            <el-form-item prop="name">
                <el-input v-model="addForm.name"  placeholder="MV名"></el-input>
            </el-form-item>
            <el-form-item prop="introduce">
                <el-input v-model="addForm.introduce"  placeholder="介绍"></el-input>
            </el-form-item>
            <el-form-item prop="style">
                <el-input v-model="addForm.style"  placeholder="类型"></el-input>
            </el-form-item>
            <el-form-item prop="url">
                <el-input type="file" v-model="addForm.url" placeholder="上传MV"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="add()" :loading="loadingbut">{{loadingbuttext}}</el-button>
                <el-button type="danger" @click="cancel()">重置</el-button>
            </el-form-item>
        </el-foem>
    </el-dialog>

    <!-- 编辑MV信息 -->
    <el-dialog title="编辑MV" v-model="dialogVisibleDetail" width="400px" center>
        <el-form ref="updatedate" :model="updatedate">
            <el-form-item prop="id1">
                <el-input v-model="updatedate.id1" disabled  placeholder="id"></el-input>
            </el-form-item>
            <el-form-item prop="name">
                <el-input v-model="updatedate.name"  placeholder="MV名"></el-input>
            </el-form-item>
            <el-form-item prop="introduce">
                <el-input type="textarea" v-model="updatedate.introduce" placeholder="介绍"></el-input>
            </el-form-item>
            <el-form-item prop="style">
                <el-input v-model="updatedate.style" placeholder="类型"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="update()" :loading="updatebut">{{updatebuttext}}</el-button>
                <el-button type="danger" @click="cancel()">重置</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>


    <!-- 删除提示框 -->
    <el-dialog title="删除歌手" v-model="disvivbile" width="400px" center>
        <div>
            <span>真的要删除吗?</span>
        </div>
        <div>
            <el-button type="primary" @click="det()" :loading="loadingbut">确定</el-button>
            <el-button type="danger" @click="disvivbile=false">取消</el-button>
        </div>
    </el-dialog>    
</template>
<script>
import { ElMessage } from 'element-plus'
export default{
    name:'mangemv',
    data(){
        return{
            currentPage: 1,
            tableData: [{}],//定义空数组接收数据
            tableDate1:[{}],
            pageSize: 5,  //一页几个数据
            total1: 0,   //共多少页
            total2:0,
            select_word:'',
            img:'',
            //新增MV
            centerDialogVisible: false,
            addForm:{},
            loadingbut: false, 
            loadingbuttext: '新增',
            //编辑MV
            dialogVisibleDetail:false,
            updatedate:{},
            updatebut:false,
            updatebuttext:'修改',
            //删除和批量删除
            multipleSelection: [],
            idx:-1,
            disvivbile:false,
            act:'',
        }
    },
    created: function(){
        this.loadDepartments()
    },
    methods:{
        //编辑歌手
        update(){
            this.updatebut=true
            this.updatebuttext='修改中'
            this.$axios
            .post('/updateSinger',this.updatedate)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    ElMessage({
                        showClose: true,
                        message: '修改成功',
                        type: 'success',
                    })
                    // this.$alert('修改成功', {confirmButtonText: '确定' })
                    this.updatebuttext='修改'
                    this.updatebut=false
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '修改失败',
                        type: 'error',
                    })
                    // this.$alert('修改失败', {confirmButtonText: '确定' })
                }

            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
            this.dialogVisibleDetail=false
            this.loadDepartments()
        },
        handelUpdate(id){
            this.$axios
            .get('/selectIdMv?id='+id)
            .then(successResponse=>{
                this.updatedate=successResponse.data
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
            this.dialogVisibleDetail=true

        },
        //删除歌手
        handelete(id){
            this.idx=id
            this.disvivbile=true
        },
        handleSelectionChange(val){
            this.multipleSelection=val
        },
        detAll(){
            for(let item of this.multipleSelection){
                this.$axios
                .get('/deleteMv?id='+item.id1)
                .then(successResponse=>{
                if(successResponse.data === "ok"){
                    this.act=successResponse.data
                }
                else{
                    this.act='no'
                }
                })
                .catch(failResponse=>{
                    ElMessage({
                        showClose: true,
                        message: failResponse.response.status,
                        type: 'warning',
                    })
                    // this.$alert(failResponse.response.status)
                })
            }
            if(this.act === 'ok'){
                ElMessage({
                        showClose: true,
                        message: '删除成功',
                        type: 'success',
                    })
                // this.$alert('删除成功', {confirmButtonText: '确定' })
            }
            this.loadDepartments()
        },
        det(){
            this.disvivbile=false
            this.$axios
            .get('/deleteMv?id='+this.idx)
            .then(successResponse=>{
                if(successResponse.data === "ok"){
                    ElMessage({
                        showClose: true,
                        message: '删除成功',
                        type: 'success',
                    })
                    // this.$alert('删除成功', {confirmButtonText: '确定' })
                    this.loadDepartments()
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '删除失败',
                        type: 'error',
                    })
                    // this.$alert('删除失败', {confirmButtonText: '确定' })
                }
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
        //添加歌手
        add(){
            this.loadingbut=true
            this.loadingbuttext='增加中'
            this.$axios
            .post('/addMv',this.addForm)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    ElMessage({
                        showClose: true,
                        message: '添加成功',
                        type: 'success',
                    })
                    // this.$alert('添加成功', {confirmButtonText: '确定' })
                    this.loadingbuttext='增加'
                    this.loadingbut=false
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '添加失败',
                        type: 'error',
                    })
                    // this.$alert('添加失败', {confirmButtonText: '确定' })
                }
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
        cancel(){
           this.$refs['addForm'].resetFields()
        },
        ogtenSinger(){
            this.centerDialogVisible=true
        },
        setpic(id){
            let url="http://localhost:8081/zf/updateMvPic?id="+id
            return url
        },
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        loadDepartments() {
            this.$axios
            .get('/getMvDetail?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
            this.tableData = successResponse.data.mv
            this.tableData1 = successResponse.data.mv
            this.total1 = successResponse.data.total
            this.total2 = successResponse.data.total
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        //页码变换
        handleCurrentChange(val) {
            this.currentPage = val
            // if (this.act === 'byCon') {
            //     this.selectForm.currentPage = this.currentPage
            //     this.selectForm.pageSize = this.pageSize
            //     this.selectDepartmentsByCon()
            // }
            this.loadDepartments()
    },
    },
}
</script>
<style scoped>
.box-a1{
    display: inline-flex;
}
.pagination{
    margin-top: 26px;
    text-align: center;
    
}
</style>
