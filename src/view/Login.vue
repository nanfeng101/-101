<template>
<div class="box">
    <div class="img1">
    <img width="300px" height="400px" src="/image/1111.webp" />
    </div>
    <div class="card">
        <div class="card-a0">
        <p>后台登录</p>
        </div>
        <el-form ref="loginForm" :model="loginForm" :rules="rules">
            <div>
            <el-form-item  prop="uname" class="card-a1">
                <input v-model="loginForm.uname" placeholder="请输入用户名">
            </el-form-item>
            </div>
            <div>
            <el-form-item prop="upwd" class="card-a2">
                <input v-model="loginForm.upwd" placeholder="请输入密码">
            </el-form-item>
            </div>
            <div>
            <el-form-item class="card-a3">
                <button type="primary" @click="login(loginForm)" :loading="loadingbut">{{loadingbuttext}}</button>
            </el-form-item>
            </div>
        </el-form>
    </div>
</div>
</template>
<script>
  export default {
    name: 'Login',
    data () {
      return {
        loginForm: {},
        //验证规则
        rules: {
          uname: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          upwd: [{required: true, message: '请输入密码', trigger: 'blur'}]
        },
       loadingbut: false, 
       loadingbuttext: '登录',
       dialogVisible: true
      }
    },
    methods: {
      login (loginForm) {
        this.$refs['loginForm'].validate((valid) => {
          if (valid) {
            this.loadingbut = true
            this.loadingbuttext = '登录中...'
            this.$axios
            .post('/login',{
                uname: loginForm.uname,
                upwd: loginForm.upwd
              })
              .then(successResponse => {
                if (successResponse.data === "ok") {
                  //Message Box
                //   this.$alert('登录成功', {confirmButtonText: '确定' })
                alert("登录成功")
                //   this.$store.commit('changeLogin',this.loginForm.uname)
                  // let path = this.$route.query.redirect
                  // this.$router.replace({path: path === '/' || path === undefined ? '/homePage': path})
                  // this.$router.go(-1)
                  this.$router.replace({path: '/homepage'})
                  this.$router.go(-1)
                }else {
                  alert('用户名或密码错误！')
                  this.loadingbut = false;
                  this.loadingbuttext = '登录';
                }
              })
              .catch(failResponse => {
                alert(failResponse.response.status)
              })
          }
          else {
              this.$alert('表单验证失败', {confirmButtonText: '确定' })
              return false;
          }
        })
      },
    }
  }
</script>
<style scoped>
.box{
    position: absolute;
    width:1520px;
    height: 730px;
    background-image: linear-gradient(to right,#89f7fe 40%, #4f94f4 60%);
}
.card{
    width:700px;
    height: 400px;
    background-color: #ffffff;
    margin-left: 390px;
    margin-top: 150px;
    box-shadow: inset 4px 4px 4px #d1d9e6;
  }
  .card-a0{
    padding-top: 20px;
    padding-left: 455px;
  }
  button{
    border-radius: 20px;
    background-image: linear-gradient(to right,#89f7fe 40%, #4f94f4 60%);
    border-style: none;
    width:130px;
    height:35px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
  }
  p{
    font-size: 20px;
    color: #21d0c5;
    font-weight: bolder;

  }
.img1{
    display: inline-block;
    position: absolute;
  }
input{
    width:200px;
    height:35px;
    border-top-style: none;
    border-left-style: none;
    border-right-style: none;
    border-bottom-color:#21d0c5;
    color: #89f7fe;
}
input:focus{
    border-color: #4f94f4;
    outline: none;
  }
  .card-a0{
    padding-top: 20px;
    padding-left: 455px;
  }
  p{
    font-size: 20px;
    color: #21d0c5;
    font-weight: bolder;

  }
  .card-a1{
    padding-top:60px;
    padding-left: 400px;
  }
  .card-a2{
    padding-top:30px;
    padding-left: 400px;
  }
  .card-a3{
    padding-top:30px;
    padding-left: 435px;
  }
  button:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
    cursor: pointer;
  }
</style>