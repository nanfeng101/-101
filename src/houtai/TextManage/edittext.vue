<template>
    <el-collapse v-model="activeName" accordion v-for="item in this.textdata" :key="item">
      <el-collapse-item :name="item.id">
        <template #title>
            <p></p>
            {{item.biaoti}}
            <div class="butt">
              <el-button @click="detail(item,'detailbutton')" style="transform:translate(1000px,0px);" type="primary" size="small" >查看</el-button>
              <el-button @click="edit(item,'editbutton')" style="transform:translate(1000px,0px);background-color:rgb(255, 223, 16);border: none;" type="primary" size="small" >编辑</el-button>
              <el-button style="transform:translate(1000px,0px);background-color:rgb(255, 16, 56);border: none;" type="primary" size="small" >删除</el-button>
            </div>  
        </template>
        <div>
          <p>作者名称    南枫</p>
          <p>文章编号    {{item.id}}</p>
          <div>
            <span>文章标签</span>
            <div class="biaoqian1" style="margin-left:0px;" v-for="item1 in biaoqian1" :key="item1">
                <div class="biaoqian1" style="" v-if="item.id==item1.id" v-for="item2 in item1.list" :key="item2">
                <button  aria-disabled="true" class="button1">{{item2}}</button>
                </div>
            </div>
          </div>
          <p>发布时间    {{item.create_time1}}</p>
          <div>
                <span>所属分类</span>
                <button  aria-disabled="true" class="button2">{{item.type}}</button>
          </div>
          <p>点赞数量    {{item.dianzan_sum}}</p>
          <p>收藏数量    0</p>
          <p>浏览数量    {{item.view_sum}}</p>
          <p>评论数量    0</p>
        </div>
      </el-collapse-item>
    </el-collapse>
</template>
<script>
export default{
    data(){
        return{
            activeName:30,
            textdata:[{}],
            biaoqian1:[{}],
        }
    },
    created: function(){
        this.handle()
    },
    methods:{
        edit(item,con){
            this.$store.commit("getText",item)
            this.$store.commit("getCurrentCom",con)
        },
        detail(item,con){
            this.$store.commit("getText",item)
            this.$store.commit("getCurrentCom",con)
        },
        handle(){
            this.$axios
            .get("/getAllTextDetail")
            .then(successResponse => {
                this.textdata=successResponse.data.text
                this.biaoqian1=successResponse.data.biaoqian
                // this.text_sum=successResponse.data.text_sum
                // this.view_sum=successResponse.data.view_sum
                // this.$store.commit("getTetxsum",successResponse.data.text_sum)
                // this.$store.commit("getViewsum",successResponse.data.view_sum)
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    },
}
</script>
<style scoped>
.butt{
    display: inline-block;
    position: absolute;
}
.biaoqian1{
    display: inline-flex;
}
.button2{
    background-color:rgb(255, 191, 164);
    color: rgb(255, 94, 0);
    border-radius: 5px;
    height:20px;
    border: none;
    margin-left:5px;
}
.button1{
    background-color:rgb(255, 245, 139);
    color: rgb(247, 187, 6);
    border-radius: 5px;
    height:20px;
    border: none;
    margin-left:5px;
}

</style>
