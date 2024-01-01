<template>
  <div class="all" id="all1">
    <div @click="add()" class="button1"><p style="padding-top:10px;">添加留言</p></div>
  </div>
  <el-drawer
    v-model="table"
    title="添加留言"
    direction="rtl"
    size="30%"
  >
    <div>
      <el-input
        v-model="addLiuyan.content"
        maxlength="100"
        placeholder="Please input"
        show-word-limit
        type="text"
      />
      <el-button @click="addUserLiuyan()" style="margin-top:20px;margin-left:20px;" type="primary">添加</el-button>
    </div>
  </el-drawer>
</template>

<script>
import { ElMessage } from 'element-plus'
export default {
  data() {
    return {
      liuyan:[{}],
      sum:3,
      table:false,
      addLiuyan:{},
    }
  },
  computed: {
  },
  mounted() {
    this.foesent()
    // setTimeout(()=>{
    // // 模拟添加弹幕数据
    // let that=this
    // for(let i=0;i<that.sum;i++){
    //   setTimeout(()=>{
    //     that.sent(i)
    //   },1000+i*1000)
    // }
    // },500)
  },
  created: function(){
    this.getUserLiuyan()
  },
  methods: {
    foesent(){
      setTimeout(()=>{
    // 模拟添加弹幕数据
      let that=this
      for(let i=0;i<that.sum;i++){
        setTimeout(()=>{
          that.sent(i)
        },1000+i*1000)
      }
      },500)
    },
    addUserLiuyan(){
      this.addLiuyan.user_id=this.$store.state.userid1
      this.$axios
      .post("/addUserLiuyan",this.addLiuyan)
      .then(successResponse => {
          if(successResponse.data=='ok'){
            ElMessage({
              showClose: true,
              message: '添加成功',
              type: 'success',
            })
            this.addLiuyan.content=''
            this.foesent()
          }else if(successResponse.data=='no'){
            ElMessage({
              showClose: true,
              message: '添加失败',
              type: 'error',
            })
          }else{
            ElMessage({
              showClose: true,
              message: `${successResponse.data}`,
              type: 'error',
            })
          }
      })
      .catch(failResponse => {
      this.$alert(failResponse.response.status)
      })
    },
    getsum(){
      return this.liuyan.length
    },
    sent(val){
      this.a=0
      const mask = document.getElementById('all1')
      const div = document.createElement('div')
      mask.append(div)
      if(this.liuyan[val].user_id==this.$store.state.userid1){
        div.innerHTML='<div style="opacity: 0.5;background-color: rgb(241, 255, 85);width:400px;display:flex;align-items: center;">'
                    +'<span style="margin-left:10px">'+this.liuyan[val].username+'：</span><sapn>'+this.liuyan[val].content+'</sapn></div>'
      }
      else{
      div.innerHTML='<div style="opacity: 0.5;background-color: rgb(204, 203, 202);width:400px;display:flex;align-items: center;">'
                    +'<span style="margin-left:10px">'+this.liuyan[val].username+'：</span><sapn>'+this.liuyan[val].content+'</sapn></div>'
      }
      div.classList.add('TanText')
      const random=Math.random() * (mask.offsetHeight-div.offsetHeight-70)
      div.style.top=random+'px'
      div.style.position='absolute'
      div.style.transition='all 10s linear'
      div.style.transform=`translateX(${mask.offsetWidth}px)`

    },
    add(){
      if(this.$store.state.userid1==0){
        ElMessage({
              showClose: true,
              message: '请先登录',
              type: 'warning',
            })
      }else{
      this.table=true
      this.foesent()
      
      }
    },
    getUserLiuyan(){
      this.$axios
      .get("/getUserLiuyan")
      .then(successResponse => {
          this.liuyan=successResponse.data
          this.sum=this.liuyan.length
      })
      .catch(failResponse => {
      this.$alert(failResponse.response.status)
      })
    }
  },
}
</script>
<style scoped>
.button1{
  color: rgb(255, 255, 255);
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  width: 200px;
  height: 50px;
  background-image: linear-gradient(to right,
  rgb(19, 120, 244),
  rgb(255, 85, 124),
  rgb(241, 255, 85),
  rgb(19, 120, 244)
  );
  background-size: 400%;
  border-radius: 30px;
  margin-left:40%;
}
.button1:hover{
  animation: run 8s infinite;
  cursor: pointer;
}
@keyframes run{
  100%{
    background-position: -400%,0;
  }
}
.all{
  margin-top:-20px;
    height: 686px;
    background-image: url("/img/121.png");
    background-size: 100%;
}
.a{
  background-color: rgb(204, 203, 202);
  text-align: center;
  opacity: 0.5;
}
</style>
