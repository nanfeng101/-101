<template>
<div v-show="show1">
   <div class="box-a1">
        <el-button type="primary" size="small" @click="detAll()">批量删除</el-button>
        <el-input v-model="select_word" @keyup="search" size="small" placeholder="筛选关键词" style="margin-left:20px;width:160px"></el-input>
        <el-button type="primary" @click="ogtenSinger()" size="small" style="margin-left:20px">添加用户</el-button>
    </div>
    <el-table id="table1"  :data="tableData" size="mini"  @selection-change="handleSelectionChange" highlight-current-row border style="margin-top:10px;height:480px !important;overflow:scroll !important;"  :header-cell-style="headClass" :cell-style="{ textAlign: 'center' }"
      :default-sort="{ prop: 'id', order: 'descending' }">
        <el-table-column type="selection" width="40px"></el-table-column>
        <el-table-column   width="120px" label="用户图片" align="center">
            <template #default="scope">
            <div>
                <img :src="geturl(scope.row.avator)" alt="未知" style="height:80px;overflow:hidden;width:100%"/>
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
        <el-table-column prop="username" width="90px" label="用户名" align="center"></el-table-column>
        <el-table-column prop="password" width="90px" label="密码" align="center"></el-table-column>
        <el-table-column prop="sex" width="50px" label="性别" align="center">
            <template #default="scope">
               <span>{{sex(scope.row.sex)}}</span>
            </template>
        </el-table-column>
        <el-table-column prop="phone_num" width="130px" label="手机号码" align="center"></el-table-column>
        <el-table-column prop="email" width="150px" label="邮箱" align="center"></el-table-column>
        <el-table-column prop="birth" width="100px" label="出生年月" align="center"></el-table-column>
        <el-table-column prop="introduction" width="260px" label="签名" align="center"></el-table-column>
        <el-table-column prop="location" width="100px" label="地区" align="center"></el-table-column>
        <el-table-column  width="80px" label="收藏" align="center">
            <template #default="scope">
            <el-button @click="shoucang(scope.row.id)" size="mini">收藏</el-button>
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
     <!-- 添加用户 -->
    <el-dialog title="添加用户" v-model="centerDialogVisible" width="400px" center>
        <el-form ref="addForm" :model="addForm">
            <el-form-item prop="username" >
                <el-input v-model="addForm.username"  placeholder="用户名" ></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="addForm.password"  placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item prop="sex">
                <el-radio-group v-model="addForm.sex">
                    <el-radio label="0">女</el-radio>
                    <el-radio label="1">男</el-radio>
                    <el-radio label="2">组合</el-radio>
                    <el-radio label="3">未知</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item prop="phone_num">
                <el-input v-model="addForm.phone_num"  placeholder="手机号"></el-input>
            </el-form-item>
            <el-form-item prop="email">
                <el-input v-model="addForm.email"  placeholder="电子邮箱"></el-input>
            </el-form-item>
            <el-form-item prop="birth">
                <el-date-picker v-model="addForm.birth" type="date" placeholder="出生年月"></el-date-picker>
            </el-form-item>
            <el-form-item prop="location">
                <el-input v-model="addForm.location" placeholder="地区"></el-input>
            </el-form-item>
            <el-form-item prop="introduction">
                <el-input type="textarea" v-model="addForm.introduction" placeholder="签名"></el-input>
            </el-form-item>
            <el-form-item prop="create_time">
                <el-date-picker v-model="addForm.create_time" type="date" placeholder="创建日期"></el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="add()" :loading="loadingbut">{{loadingbuttext}}</el-button>
                <el-button type="danger" @click="cancel()">重置</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>

    <!-- 编辑用户信息 -->
    <el-dialog title="编辑用户" v-model="dialogVisibleDetail" width="400px" center>
        <el-form ref="updatedate" :model="updatedate">
            <el-form-item prop="id">
                <el-input v-model="updatedate.id" disabled  placeholder="id"></el-input>
            </el-form-item>
            <el-form-item prop="username">
                <el-input v-model="updatedate.username"  placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="updatedate.password"  placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item prop="sex">
                <el-radio-group v-model="updatedate.sex">
                    <el-radio :label="0">女</el-radio>
                    <el-radio :label="1">男</el-radio>
                    <el-radio :label="2">组合</el-radio>
                    <el-radio :label="3">未知</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item prop="phone_num">
                <el-input v-model="updatedate.phone_num"  placeholder="手机号"></el-input>
            </el-form-item>
            <el-form-item prop="email">
                <el-input v-model="updatedate.email"  placeholder="邮箱"></el-input>
            </el-form-item>
            <el-form-item prop="birth">
                <el-date-picker v-model="updatedate.birth" type="date" placeholder="出生日期"></el-date-picker>
            </el-form-item>
            <el-form-item prop="location">
                <el-input v-model="updatedate.location" placeholder="地区"></el-input>
            </el-form-item>
            <el-form-item prop="introduction">
                <el-input type="textarea" v-model="updatedate.introduction" placeholder="简介"></el-input>
            </el-form-item>
            <el-form-item prop="update_time">
                <el-date-picker v-model="updatedate.update_time" type="date" placeholder="修改日期"></el-date-picker>
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
</template>


<script>
export default{
    name:'user',
    data(){
        return{
            currentPage: 1,
            tableData: [{}],//定义空数组接收数据
            tableDate1:[{}],
            pageSize: 5,  //一页几个数据
            total1: 0,   //共多少页
            total2:0,
            //新增用户
            centerDialogVisible: false,
            addForm:{},
            loadingbut: false, 
            loadingbuttext: '新增',
            //删除和批量删除
            multipleSelection: [],
            idx:-1,
            disvivbile:false,
            act:'',
            //编辑歌手
            dialogVisibleDetail:false,
            updatedate:{},
            updatebut:false,
            updatebuttext:'修改',
            //收藏
            show1:true,
            show2:false,
            tableData3:[{}],
            disvivbile3:false,
            idx3:-1,
            multipleSelection3:[],
            //搜索
            select_word:'',

        }
    },
    created: function () {
        this.loadDepartments()
    },
    methods:{
        search(){
            if(this.select_word===''){
                this.tableData=this.tableDate1
                this.total1=this.total2
                this.acr=''

            }else{
                this.tableData=null
                this.$axios
                .get('selectConsumer?name=' + this.select_word)
                .then(successResponse =>{
                    this.tableData=successResponse.data.consumer
                    this.total1=successResponse.data.total
                    this.act='byCon'
                })
                .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
                })
            }

        },
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
                .get('/deleteCollect?id='+item.id)
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
            .get('/deleteSong?id='+this.idx)
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
            .get('/getCollect?id='+id)
            .then(successResponse=>{
                this.tableData3=successResponse.data.collect
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })

        },
        back2(){
            this.show1 = !this.show1
            this.show2 = !this.show2
        },
        //编辑歌手
        update(){
            this.updatebut=true
            this.updatebuttext='修改中'
            this.$axios
            .post('/updateConsumer',this.updatedate)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    this.$alert('修改成功', {confirmButtonText: '确定' })
                    this.updatebuttext='修改'
                    this.updatebut=false
                    this.dialogVisibleDetail=false
                    this.loadDepartments()
                }
                else{
                    this.$alert('修改失败', {confirmButtonText: '确定' })
                    this.updatebuttext='修改'
                    this.updatebut=false
                }

            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
                this.updatebuttext='修改'
                this.updatebut=false
            })
        },
        handelUpdate(id){
            this.$axios
            .get('/getIdConsumer?id='+id)
            .then(successResponse=>{
                this.updatedate=successResponse.data
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
            this.dialogVisibleDetail=true

        },
        //删除用户
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
            .get('/deleteConsumer?id='+this.idx)
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
        add(){
            this.loadingbut=true
            this.loadingbuttext='增加中'
            this.$axios
            .post('/addConsumer',this.addForm)
            .then(successResponse=>{
                if(successResponse.data === 'ok'){
                    this.$alert('添加成功', {confirmButtonText: '确定' })
                    this.loadingbuttext='增加'
                    this.loadingbut=false
                    this.centerDialogVisible=false
                    this.loadDepartments()
                }
                else{
                    this.$alert('添加失败', {confirmButtonText: '确定' })
                }
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })
        },
         ogtenSinger(){
            this.centerDialogVisible=true
        },
        setpic(id){
            let url="http://localhost:8081/zf/updateAcator?id="+id
            return url
        },
        sex(val){
            if(val==1){
                return "男"
            }else if(val==0){
                return "女"
            }else if(val==2){
                return "组合"
            }else{
                return "未知"
            }
        },
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        loadDepartments() {
            this.$axios
            .get('/getConsumerPage?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
                this.tableData = successResponse.data.consumer
                this.tableData1 = successResponse.data.consumer
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