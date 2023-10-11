<template>
    <div class="box-a1">
        <el-button type="primary" size="small" @click="detAll()">批量删除</el-button>
        <el-input v-model="select_word" @keyup="search" size="small" placeholder="筛选关键词" style="margin-left:20px"></el-input>
        <el-button type="primary" @click="ogtenSinger()" size="small" style="margin-left:20px">添加歌手</el-button>
    </div>
    <el-table id="table1" ref="multipleTable" :data="tableData" size="mini"  @selection-change="handleSelectionChange" highlight-current-row border style="margin-top:10px;height:480px !important;overflow:scroll !important;"  :header-cell-style="headClass" :cell-style="{ textAlign: 'center' }"
      :default-sort="{ prop: 'id', order: 'descending' }">
        <el-table-column type="selection" width="40px"></el-table-column>
        <el-table-column   width="120px" label="歌手图片" align="center">
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
        <el-table-column prop="name" width="70px" label="歌手" align="center"></el-table-column>
        <el-table-column prop="sex" width="50px" label="性别" align="center">
            <template #default="scope">
               <span>{{sex(scope.row.sex)}}</span>
            </template>
        </el-table-column>
        <el-table-column prop="birth" width="100px" label="出生年月" align="center"></el-table-column>
        <el-table-column prop="location" width="100px" label="地区" align="center"></el-table-column>
        <el-table-column prop="introduction" width="728px" label="简介" align="center"></el-table-column>
        <el-table-column width="150px" label="操作" align="center">
            <template #default="scope">
            <el-button size="mini" @click="handelUpdate(scope.row.id)" style="background-color:rgb(13, 165, 236);color:#ffffff">编辑</el-button>
            <el-button size="mini" @click="handelete(scope.row.id)"  style="background-color:rgba(250, 15, 62, 0.859);color:#ffffff">删除</el-button>
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
    <!-- 添加歌手 -->
    <el-dialog title="添加歌手" v-model="centerDialogVisible" width="400px" center>
        <el-foem ref="addForm" :model="addForm">
            <el-form-item prop="name">
                <el-input v-model="addForm.name"  placeholder="歌手名"></el-input>
            </el-form-item>
            <el-form-item prop="sex">
                <el-radio-group v-model="addForm.sex">
                    <el-radio label="0">女</el-radio>
                    <el-radio label="1">男</el-radio>
                    <el-radio label="2">组合</el-radio>
                    <el-radio label="3">未知</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item prop="birth">
                <el-date-picker v-model="addForm.birth" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
            <el-form-item prop="location">
                <el-input v-model="addForm.location" placeholder="地区"></el-input>
            </el-form-item>
            <el-form-item prop="introduction">
                <el-input type="textarea" v-model="addForm.introduction" placeholder="简介"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="add()" :loading="loadingbut">{{loadingbuttext}}</el-button>
                <el-button type="danger" @click="cancel()">重置</el-button>
            </el-form-item>
        </el-foem>
    </el-dialog>

    <!-- 编辑歌手信息 -->
    <el-dialog title="编辑歌手" v-model="dialogVisibleDetail" width="400px" center>
        <el-form ref="updatedate" :model="updatedate">
            <el-form-item prop="id">
                <el-input v-model="updatedate.id" disabled  placeholder="id"></el-input>
            </el-form-item>
            <el-form-item prop="name">
                <el-input v-model="updatedate.name"  placeholder="歌手名"></el-input>
            </el-form-item>
            <el-form-item prop="sex">
                <el-radio-group v-model="updatedate.sex">
                    <el-radio :label="0">女</el-radio>
                    <el-radio :label="1">男</el-radio>
                    <el-radio :label="2">组合</el-radio>
                    <el-radio :label="3">未知</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item prop="birth">
                <el-date-picker v-model="updatedate.birth" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
            <el-form-item prop="location">
                <el-input v-model="updatedate.location" placeholder="地区"></el-input>
            </el-form-item>
            <el-form-item prop="introduction">
                <el-input type="textarea" v-model="updatedate.introduction" placeholder="简介"></el-input>
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
export default{
    name:'singer',
    created: function () {
    this.loadDepartments()
    },
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
            //新增歌手
            centerDialogVisible: false,
            addForm:{},
            loadingbut: false, 
            loadingbuttext: '新增',
            //编辑歌手
            dialogVisibleDetail:false,
            updatedate:{},
            updatebut:false,
            updatebuttext:'修改',
            //删除和批量删除
            multipleSelection: [],
            idx:-1,
            disvivbile:false,
            act:'',
            //歌曲管理
            show:true,
            show1:false,
            total3:0,
            tableData2:[{}],
            pageSize3:5,
            currentPage3: 1,
            ida:-1,
            dialogVisibleDetail3:false,
            updatedate3:{},
            updatebut3:false,
            updatebuttext3:'修改',
        }
            
    },
    methods: {
        //编辑歌手
        update(){
            this.updatebut=true
            this.updatebuttext='修改中'
            this.$axios
            .post('/updateSinger',this.updatedate)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    this.$alert('修改成功', {confirmButtonText: '确定' })
                    this.updatebuttext='修改'
                    this.updatebut=false
                }
                else{
                    this.$alert('修改失败', {confirmButtonText: '确定' })
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
            .get('/getIdSinger?id='+id)
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
                .get('/deleteSinger?id='+item.id)
                .then(successResponse=>{
                if(successResponse.data === "ok"){
                    this.act=successResponse.data
                }
                else{
                    this.act='no'
                }
                })
                .catch(failResponse=>{
                    this.$alert(failResponse.response.status)
                })
            }
            if(this.act === 'ok'){
                this.$alert('删除成功', {confirmButtonText: '确定' })
            }
            this.loadDepartments()
        },
        det(){
            this.disvivbile=false
            this.$axios
            .get('/deleteSinger?id='+this.idx)
            .then(successResponse=>{
                if(successResponse.data === "ok"){
                    this.$alert('删除成功', {confirmButtonText: '确定' })
                    this.loadDepartments()
                }
                else{
                    this.$alert('删除失败', {confirmButtonText: '确定' })
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
            .post('/addSinger',this.addForm)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    this.$alert('添加成功', {confirmButtonText: '确定' })
                    this.loadingbuttext='增加'
                    this.loadingbut=false
                }
                else{
                    this.$alert('添加失败', {confirmButtonText: '确定' })
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
            let url="http://localhost:8081/zf/updatePic?id="+id
            return url
        },
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        beforeAvatarUpload (file) {
            const isJPG = (file.type === 'image/jpeg') || (file.type === 'image/png')
            const isLt10M = file.size / 1024 / 1024 < 10
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt10M) {
                this.$message.error('上传头像图片大小不能超过 10MB!')
            }
            return isJPG && isLt10M
        },
        sex(val){
            if(val==1){
                return "男"
            }else if(val==3){
                return "女"
            }else if(val==2){
                return "组合"
            }else{
                return "未知"
            }
        },
        search(){
            if(this.select_word===''){
                this.tableData=this.tableDate1
                this.total1=this.total2
                this.acr=''

            }else{
                this.tableData=null
                this.$axios
                .get('selectSinger?name=' + this.select_word)
                .then(successResponse =>{
                    this.tableData=successResponse.data.singer
                    this.total1=successResponse.data.total
                    this.act='byCon'
                })
                .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
                })
            }

        },
        loadDepartments() {
            this.$axios
            .get('/getSingerPage?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
            this.tableData = successResponse.data.singer
            this.tableData1 = successResponse.data.singer
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
    }
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
