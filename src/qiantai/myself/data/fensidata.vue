<template>
<p style="font-weight: bold;font-size:19px;">粉丝数据</p>
<el-card style="width:200px;" shadow="hover" :body-style="{padding: '0px'}" > 
    <div class="grid-content grid-con-1">
        <div class="grid-cont-right">
        <div class="grid-num">{{this.sum}}</div>
        <div>粉丝人数</div>
        </div>
    </div>
</el-card>
<C style="margin-top:15px;"></C>
</template>
<script>
import C from '../manage/echarts/c.vue'
export default{
    components:{
        C,
    },
    data(){
        return{
            sum:0,
        }
    },
    created: function(){
        this.getUserFensiSum()
    },
    methods:{
        getUserFensiSum(){
            let item=this.$store.state.userid1
            this.$axios.get('/getUserFensiLocation?id='+item)
            .then(successResponse => {
                this.sum=successResponse.data.sum
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>

<style scoped>
.grid-content {
    display: flex;
    align-items: center;
    height: 100px;
  }

  .grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
  }

  .grid-num {
    font-size: 30px;
    font-weight: bold;
  }
</style>