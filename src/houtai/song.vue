<template>
<!-- 作曲管理 -->
<div v-show="show1">
<div class="box-a1" :modle="addForm2">
    <el-button type="primary" size="small" @click="detAll()">批量删除</el-button>
    <el-select v-model="addForm2.val_id" placeholder="Select" style="width: 120px;margin-left:20px">
        <el-option label="歌手名" value="1"/>
        <el-option label="歌曲名"  value="2"/>
    </el-select>
    <el-input
        v-model="addForm2.val"
        placeholder="Please input"
        @keyup="search"
        style="width: 250px"
        >
    </el-input>
    <!-- <el-input   size="small" placeholder="筛选关键词" style="margin-left:20px"></el-input> -->
    <el-button type="primary" @click="ogtenSinger()" size="small" style="margin-left:20px">添加歌曲</el-button>
</div>
<el-table id="table1" ref="multipleTable2" :data="tableData2" size="mini"  @selection-change="handleSelectionChange" highlight-current-row border style="margin-top:10px;" height="480px" :header-cell-style="headClass" :cell-style="{ textAlign: 'center' }"
:default-sort="{ prop: 'id', order: 'descending' }">
    <el-table-column type="selection" width="40px"></el-table-column>
    <el-table-column   width="120px" label="歌手图片" align="center">
        <template #default="scope">
        <div>
            <img :src="geturl(scope.row.pic)" alt="未知" style="height:80px;overflow:hidden;width:100%"/>
        </div>
        </template>
    </el-table-column>
    <el-table-column prop="name" width="170px" label="歌名" align="center"></el-table-column>
    <el-table-column prop="introduction" width="100px" label="专辑" align="center"></el-table-column>
    <el-table-column width="588px" label="歌词" align="center">
        <template  #default="scope">
            <div style="height:80px;overflow:scroll;" >
            {{scope.row.lyric}}
            </div>
        </template>
        <!-- <div v-for="item in a" :key="item">
        <div>{{item}}</div>
        </div> -->
    </el-table-column>
    <el-table-column width="120px" label="资源更新" align="center">
        <template #default="scope">
            <el-upload
                class="upload-demo"
                :show-file-list="false"
                :before-upload="beforeAvatarUpload"
                :action="setpic1(scope.row.id)"
            >
        <el-button size="mini">更新图片</el-button>
        </el-upload>
        <el-upload
              class="upload-demo change"
              :action="uploadSongUrl(scope.row.id)"
              :show-file-list="false"
              :on-success="handleSongSuccess"
              :before-upload="beforeSongUpload">
              <el-button size="mini">更新歌曲</el-button>
            </el-upload>
        </template>
    </el-table-column>
    <el-table-column width="90px" label="评论" align="center">
        <template #default="scope">
        <el-button size="mini" @click="pinglun(scope.row.id)" style="background-color:rgb(13, 165, 236);color:#ffffff">评论</el-button>
        </template>
    </el-table-column>
    <el-table-column width="150px" label="操作" align="center">
        <template #default="scope">
        <el-button size="mini" @click="handelUpdate(scope.row.id)"  style="background-color:rgb(13, 165, 236);color:#ffffff">编辑</el-button>
        <el-button size="mini" @click="handelete(scope.row.id)"  style="background-color:rgba(250, 15, 62, 0.859);color:#ffffff">删除</el-button>
        </template>
    </el-table-column>
</el-table>
<div class="pagination">
    <div class="pagination">
        <el-pagination background layout="total, prev, pager, next"
        :total="total1" 
        v-model:currentPage="currentPage" 
        @current-change="handleCurrentChange"
        :page-size="pageSize">
        </el-pagination>
    </div>
</div>
</div>

<el-dialog title="编辑歌手" v-model="dialogVisibleDetail" width="400px" center>
        <el-foem ref="updatedate" :model="updatedate">
            <el-form-item prop="id">
                <el-input v-model="updatedate.id" disabled  placeholder="id"></el-input>
            </el-form-item>
            <el-form-item prop="singer_id">
                <el-input v-model="updatedate.singer_id"   placeholder="歌手id"></el-input>
            </el-form-item>
            <el-form-item prop="name">
                <el-input v-model="updatedate.name"  placeholder="歌手名"></el-input>
            </el-form-item>
            <el-form-item prop="update_time">
                <el-date-picker v-model="updatedate.update_time" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
            <el-form-item prop="introduction">
                <el-input  v-model="updatedate.introduction" placeholder="简介"></el-input>
            </el-form-item>
            <el-form-item prop="lyric">
                <el-input type="textarea" v-model="updatedate.lyric" placeholder="歌词"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="update()" :loading="updatebut">{{updatebuttext}}</el-button>
                <el-button type="danger" @click="cancel()">重置</el-button>
            </el-form-item>
        </el-foem>
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

    <!-- 添加歌曲 -->
    <el-dialog title="添加歌手" v-model="centerDialogVisible" width="400px" center>
        <el-foem ref="addForm" :model="addForm">
            <el-form-item prop="name">
                <el-input v-model="addForm.name"  placeholder="歌曲名"></el-input>
            </el-form-item>
            <el-form-item prop="singer_name">
                <el-input v-model="addForm.singer_name"  placeholder="歌手名"></el-input>
            </el-form-item>
            <el-form-item prop="create_time">
                <el-date-picker v-model="addForm.create_time" type="date" placeholder="添加日期"></el-date-picker>
            </el-form-item>
            <el-form-item prop="introduction">
                <el-input  v-model="addForm.introduction" placeholder="专辑"></el-input>
            </el-form-item>
            <el-form-item prop="lyric">
                <el-input type="textarea" v-model="addForm.lyric" placeholder="歌词"></el-input>
            </el-form-item>
            <el-form-item prop="mpfile">
                <el-input type="file" v-model="addForm.mpfile" placeholder="上传歌曲"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="add()" :loading="loadingbut">{{loadingbuttext}}</el-button>
                <el-button type="danger" @click="cancel()">重置</el-button>
            </el-form-item>
        </el-foem>
    </el-dialog>
    <!-- 评论 -->
    <div v-show="show2">
        <div>
            <el-button type="primary" size="small" @click="detAll3()">批量删除</el-button>
            <el-input   size="small" placeholder="筛选关键词" style="margin-left:20px;width:200px"></el-input>
            <el-button type="primary" @click="back2()"  size="small" style="margin-left:20px">返回</el-button>
        </div>
            <el-table id="table1" ref="multipleTable3" :data="tableData3" size="mini"  @selection-change="handleSelectionChange3" highlight-current-row border style="margin-top:10px;" height="480px" :header-cell-style="headClass" :cell-style="{ textAlign: 'center' }"
            :default-sort="{ prop: 'id', order: 'descending' }">
                <el-table-column type="selection" width="40px"></el-table-column>
                <el-table-column prop="username" width="270px" align="center" label="用户"></el-table-column>
                <el-table-column width="817px" prop="content" label="评论" align="center">
                </el-table-column>
                <el-table-column width="250px" label="操作" align="center">
                    <template #default="scope">
                    <el-button size="mini"  style="background-color:rgb(13, 165, 236);color:#ffffff">编辑</el-button>
                    <el-button size="mini" @click="handelete3(scope.row.id)"  style="background-color:rgba(250, 15, 62, 0.859);color:#ffffff">删除</el-button>
                    </template>
                </el-table-column>
        </el-table>
    </div>
    <!-- 评论删除提示框 -->
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
  export default {
    name: 'Song',
    created: function () {
        this.loadDepartments()
    },
    data() {
      return {
        //歌曲管理
        total1:0,
        tableData2:[{}],
        pageSize:5,
        currentPage: 1,
        ida:-1,
        dialogVisibleDetail:false,
        updatedate:{},
        updatebut:false,
        updatebuttext:'修改',
        result:[],
        multipleSelection: [],
        idx:-1,
        disvivbile:false,
        act:'',
        //新增歌曲
        centerDialogVisible: false,
        addForm:{},
        loadingbut: false, 
        loadingbuttext: '新增',
        addForm2:{},
        tableDate1:[{}],
        total2:0,
        //评论
        show1:true,
        show2:false,
        tableData3:[{}],
        //评论删除
        multipleSelection3: [],
        idx3:-1,
        disvivbile3:false,


      }
    },
    methods: {
        //评论
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
                .get('/deleteComment?id='+item.id)
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
        pinglun(id){
            this.show1 = !this.show1
            this.show2 = !this.show2
            this.$axios
            .get('/getComment?id='+id)
            .then(successResponse=>{
                this.tableData3=successResponse.data.comment
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })
        },
        back2(){
            this.show1 = !this.show1
            this.show2 = !this.show2
        },
        //
        search(){
            if(this.addForm2.val === ''){
                this.tableData2=this.tableDate1
                this.total1=this.total2
                this.pageSize=5

            }else{
                this.tableData=null
                this.$axios
                .post('/searchSong',this.addForm2)
                .then(successResponse =>{
                    this.tableData2=successResponse.data.song
                    this.total1=successResponse.data.total
                    this.pageSize=successResponse.data.total
                })
                .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
                })
            }

        },
        //添加歌曲
        add(){
            this.loadingbut=true
            this.loadingbuttext='增加中'
            this.$axios
            .post('/addSong',this.addForm)
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
        ogtenSinger(){
            this.centerDialogVisible=true
        },
        //删除歌曲
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
        //歌曲管理
        geturl(url1){
            let url="http://localhost:8081/zf/"+url1
            return url
        },
        update(){
            this.updatebut=true
            this.updatebuttext='修改中'
            this.$axios
            .post('/updateSong',this.updatedate)
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
        },
        parseLyric (text) {
            let lines = text.split('\n')
            let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g
            this.result = []

            // 对于歌词格式不对的特殊处理
            if (!(/\[.+\]/.test(text))) {
                return [text]
            }
            for (let item of lines) {
                if (pattern.test(item)) {
                let value = item.replace(pattern, '') // 存歌词
                this.result.push(value)
                }
            }
            return this.result
        },
        handelUpdate(id){
            this.dialogVisibleDetail=true
            this.$axios
            .get('/getIdSong?id='+id)
            .then(successResponse=>{
                this.updatedate=successResponse.data
            })
            .catch(failResponse=>{
                this.$alert(failResponse.response.status)
            })

        },
        uploadSongUrl(id){
            let url="http://localhost:8081/zf/updateSongUrl?id="+id
            return url
        },
        setpic1(id){
            let url="http://localhost:8081/zf/updateSongPic?id="+id
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
        loadDepartments() {
            this.$axios
            .get('/getSongPage?currentPage=' + this.currentPage + '&&pageSize=' + this.pageSize)
            .then(successResponse => {
                this.tableData2 = successResponse.data.song
                this.tableData1 = successResponse.data.song
                this.total1 = successResponse.data.total1
                this.total2 = successResponse.data.total1     
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
            },
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