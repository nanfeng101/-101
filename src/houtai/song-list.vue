<template>
<div v-show="show1">
    <div class="box-a1">
        <el-button type="primary" size="small" @click="detAll()">批量删除</el-button>
        <el-input v-model="select_word" @keyup="search" size="small" placeholder="筛选关键词" style="margin-left:20px;width:150px"></el-input>
        <el-button type="primary" @click="ogtenSinger()" size="small" style="margin-left:20px">添加歌单</el-button>
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
        <el-table-column prop="title" width="200px" label="标题" align="center"></el-table-column>
        <el-table-column prop="introduction" width="550px" label="简介" align="center"></el-table-column>
        <el-table-column prop="style" width="100px" label="风格" align="center"></el-table-column>
        <el-table-column  width="100px" label="内容" align="center">
            <template #default="scope">
                <el-button @click="shoucang(scope.row.id)" size="mini">内容</el-button>
            </template>
        </el-table-column>
        <el-table-column width="100px" label="评论" align="center">
            <template #default="scope">
                <el-button @click="pinglun(scope.row.id)" size="mini">评论</el-button>
            </template>
        </el-table-column>
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
</div>

    <!-- 添加歌手 -->
    <el-dialog title="添加歌单" v-model="centerDialogVisible" width="400px" center>
        <el-foem ref="addForm" :model="addForm">
            <el-form-item prop="title">
                <el-input v-model="addForm.title"  placeholder="主题"></el-input>
            </el-form-item>
            <el-form-item prop="introduction">
                <el-input type="textarea" v-model="addForm.introduction" placeholder="简介"></el-input>
            </el-form-item>
            <el-form-item prop="style">
                <el-input v-model="addForm.style" placeholder="风格"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="add()" :loading="loadingbut">{{loadingbuttext}}</el-button>
                <el-button type="danger" @click="cancel()">重置</el-button>
            </el-form-item>
        </el-foem>
    </el-dialog>

    <!-- 编辑歌手信息 -->
    <el-dialog title="编辑歌单" v-model="dialogVisibleDetail" width="400px" center>
        <el-form ref="updatedate" :model="updatedate">
            <el-form-item prop="id">
                <el-input v-model="updatedate.id" disabled  placeholder="id"></el-input>
            </el-form-item>
            <el-form-item prop="title">
                <el-input v-model="updatedate.title" placeholder="标题"></el-input>
            </el-form-item>
            <el-form-item prop="introduction">
                <el-input type="textarea" v-model="updatedate.introduction" placeholder="简介"></el-input>
            </el-form-item>
            <el-form-item prop="style">
                <el-input v-model="updatedate.style" placeholder="风格"></el-input>
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

    <!-- 收藏 -->
    <div v-show="show2">
        <div>
            <el-button type="primary" size="small" @click="detAll3()">批量删除</el-button>
            <el-input   size="small" placeholder="筛选关键词" style="margin-left:20px;width:200px"></el-input>
            <el-button type="primary" @click="back2()"  size="small" style="margin-left:20px">返回</el-button>
        </div>
            <el-table id="table1" ref="multipleTable3" :data="tableData3" size="mini"  @selection-change="handleSelectionChange3" highlight-current-row border style="margin-top:10px;" height="480px" :header-cell-style="headClass" :cell-style="{ textAlign: 'center' }"
            :default-sort="{ prop: 'id', order: 'descending' }">
                <el-table-column type="selection" width="40px"></el-table-column>
                <el-table-column width="1087px" prop="name" label="歌手-歌曲" align="center"></el-table-column>
                <el-table-column width="250px" label="操作" align="center">
                    <template #default="scope">
                    <el-button size="mini" @click="handelete3(scope.row.id)"  style="background-color:rgba(250, 15, 62, 0.859);color:#ffffff">删除</el-button>
                    </template>
                </el-table-column>
        </el-table>
    </div>
    <!-- 收藏删除提示框 -->
    <el-dialog title="删除歌手" v-model="disvivbile3" width="400px" center>
        <div>
            <span>真的要删除吗?</span>
        </div>
        <div>
            <el-button type="primary" @click="det3()" >确定</el-button>
            <el-button type="danger" @click="disvivbile3=false">取消</el-button>
        </div>
    </el-dialog>

    <!-- 评论 -->
    <div v-show="show4">
        <div>
            <el-button type="primary" size="small" @click="detAll4()">批量删除</el-button>
            <el-input   size="small" placeholder="筛选关键词" style="margin-left:20px;width:200px"></el-input>
            <el-button type="primary" @click="back4()"  size="small" style="margin-left:20px">返回</el-button>
        </div>
            <el-table id="table1" ref="multipleTable4" :data="tableData4" size="mini"  @selection-change="handleSelectionChange3" highlight-current-row border style="margin-top:10px;" height="480px" :header-cell-style="headClass" :cell-style="{ textAlign: 'center' }"
            :default-sort="{ prop: 'id', order: 'descending' }">
                <el-table-column type="selection" width="40px"></el-table-column>
                <el-table-column prop="username" width="270px" align="center" label="用户"></el-table-column>
                <el-table-column width="817px" prop="content" label="评论" align="center"></el-table-column>
                <el-table-column width="250px" label="操作" align="center">
                    <template #default="scope">
                    <el-button size="mini" @click="handelete4(scope.row.id)"  style="background-color:rgba(250, 15, 62, 0.859);color:#ffffff">删除</el-button>
                    </template>
                </el-table-column>
        </el-table>
    </div>
    <!-- 评论删除提示框 -->
    <el-dialog title="删除歌手" v-model="disvivbile4" width="400px" center>
        <div>
            <span>真的要删除吗?</span>
        </div>
        <div>
            <el-button type="primary" @click="det4()" >确定</el-button>
            <el-button type="danger" @click="disvivbile4=false">取消</el-button>
        </div>
    </el-dialog>        
</template>


<script>
export default{
    name:'song',
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
            //新增歌单
            centerDialogVisible: false,
            addForm:{},
            loadingbut: false, 
            loadingbuttext: '新增',
            //编辑歌单
            dialogVisibleDetail:false,
            updatedate:{},
            updatebut:false,
            updatebuttext:'修改',
            //删除和批量删除
            multipleSelection: [],
            idx:-1,
            disvivbile:false,
            act:'',
            //收藏
            show1:true,
            show2:false,
            tableData3:[{}],
            disvivbile3:false,
            idx3:-1,
            multipleSelection3:[],
            //评论
            show4:false,
            tableData4:[{}],
            disvivbile4:false,
            idx4:-1,
            multipleSelection4:[],

        }
    },
    methods:{
        //评论
        handelete4(id){
            this.idx4=id
            this.disvivbile4=true
        },
        handleSelectionChange4(val){
            this.multipleSelection4=val
        },
        detAll4(){
            for(let item of this.multipleSelection4){
                this.$axios
                .get('/deleteSong_ListComment?id='+item.id)
                .then(successResponse=>{
                if(successResponse.data === "ok"){
                    this.act=successResponse.data
                    this.$alert('删除成功', {confirmButtonText: '确定' })
                }
                else{
                    this.act='no'
                }
                })
                .catch(failResponse=>{
                    this.$alert(failResponse.response.status)
                })
            }
            this.loadDepartments()
        },
        det4(){
            this.disvivbile4=false
            this.$axios
            .get('/deleteSong_ListComment?id='+this.idx)
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
        pinglun(id){
            this.show1 = !this.show1
            this.show4 = !this.show4
            this.$axios
            .get('/getSong_listComment?id='+id)
            .then(successResponse=>{
                this.tableData4=successResponse.data.song_list
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })

        },
        back4(){
            this.show1 = !this.show1
            this.show4 = !this.show4
        },
        //内容
        handelete3(id){
            this.idx3=id
            this.disvivbile3=true
        },
        handleSelectionChange3(val){
            this.multipleSelection3=val
        },
        detAll3(){
            for(let item of this.multipleSelection3){
                this.$axios
                .get('/deleteSong_ListSong?id='+item.id)
                .then(successResponse=>{
                if(successResponse.data === "ok"){
                    this.act=successResponse.data
                    this.$alert('删除成功', {confirmButtonText: '确定' })
                }
                else{
                    this.act='no'
                }
                })
                .catch(failResponse=>{
                    this.$alert(failResponse.response.status)
                })
            }
            this.loadDepartments()
        },
        det3(){
            this.disvivbile=false
            this.$axios
            .get('/deleteSong_ListSong?id='+this.idx)
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
        shoucang(id){
            this.show1 = !this.show1
            this.show2 = !this.show2
            this.$axios
            .get('/getSong_listContent?id='+id)
            .then(successResponse=>{
                this.tableData3=successResponse.data.song_list
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })

        },
        back2(){
            this.show1 = !this.show1
            this.show2 = !this.show2
        },
        search(){
            if(this.select_word===''){
                this.tableData=this.tableDate1
                this.total1=this.total2
                this.acr=''

            }else{
                this.tableData=null
                this.$axios
                .get('selectTitleSong_list?title=' + this.select_word)
                .then(successResponse =>{
                    this.tableData=successResponse.data.song_list
                    this.total1=successResponse.data.total
                    this.act='byCon'
                })
                .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
                })
            }

        },
        //编辑歌单
        update(){
            this.updatebut=true
            this.updatebuttext='修改中'
            this.$axios
            .post('/updateSong_list',this.updatedate)
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
            .get('/getIdSong_list?id='+id)
            .then(successResponse=>{
                this.updatedate=successResponse.data
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
            this.dialogVisibleDetail=true

        },
        //删除歌单
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
                .get('/deleteSong_list?id='+item.id)
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
            .get('/deleteSong_list?id='+this.idx)
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
        //添加歌单
        add(){
            this.loadingbut=true
            this.loadingbuttext='增加中'
            this.$axios
            .post('/addSong_list',this.addForm)
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
            let url="http://localhost:8081/zf/updateSong_listPic?id="+id
            return url
        },
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        loadDepartments() {
            this.$axios
            .get('/getSong_listPage?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
            this.tableData = successResponse.data.song_list
            this.tableData1 = successResponse.data.song_list
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
            this.loadDepartments()
        }

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