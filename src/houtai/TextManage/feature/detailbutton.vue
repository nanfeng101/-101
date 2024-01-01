<template>
<div class="all">
    <div class="content1">
        <div class="content1-a1">
            <img class="img1" src="/img/aaaaa.jpg" width="60" height="60"/>
            <div style="margin-left:90px;padding-top:15px;">
            <p style="font-weight: bold;">南枫</p>
            </div>
        </div>
        <div class="content1-a2">
            <p style="padding-top:15px;margin-left:15px;font-weight: bold;">文章数据</p>
            <div style="color: rgb(149, 149, 150);">
                <el-icon style="margin-left:15px;"><Clock /></el-icon>
                <span style="margin-left:3px;">{{this.$store.state.text.create_time1}}</span>
                <p></p>
                <el-icon style="margin-left:15px;"><View /></el-icon>
                <span style="margin-left:3px;">{{this.$store.state.text.view_sum}}</span>
                <p></p>
                <el-icon style="margin-left:15px;"><ChatDotSquare /></el-icon>
                <!-- <span style="margin-left:3px;"></span> -->
                <span style="margin-left:3px;">0</span>
                <p></p>
                <el-icon style="margin-left:15px;"><Pointer /></el-icon>
                <span style="margin-left:3px;">{{this.$store.state.text.dianzan_sum}}</span>
                <p></p>
                <el-icon style="margin-left:15px;"><StarFilled /></el-icon>
                <span style="margin-left:3px;">0</span>
            </div>
        </div>
        <div class="content1-a3">
            <p style="padding-top:15px;margin-left:15px;font-weight: bold;">文章标签</p>
            <div v-for="item in biaoqian1" :key="item">
                <div style="display: flex;margin-left:10px;" v-if="item.id==this.$store.state.text.id">
                    <div v-for="item1 in item.list" :key="item1">
                       <button class="button2">{{item1}}</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content2">
        <div>
           <v-md-preview :text="this.$store.state.text.content"  ref="preview"></v-md-preview>
        </div>
    </div>
</div>
</template>
<script>
import {Clock,View,ChatDotSquare,Pointer,StarFilled} from "@element-plus/icons"
export default{
    data(){
        return{
            biaoqian1:[{}],
        }
    },
    components:{
        Clock,
        View,
        ChatDotSquare,
        Pointer,
        StarFilled,
    },
    created: function(){
        this.handle()
    },
    methods:{
        handle(){
            this.$axios
            .get("/getAllTextDetail")
            .then(successResponse => {
                this.biaoqian1=successResponse.data.biaoqian
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        }
    }
}
</script>

<style scoped>
.button2{
    background-color:rgb(255, 191, 164);
    color: rgb(255, 94, 0);
    border-radius: 5px;
    height:20px;
    border: none;
    margin-left:5px;
}
.img1{
    margin-top:15px;
    margin-left:15px;
    border-radius: 50%;
    display: inline-block;
    position: absolute;
}
.content1-a3{
    margin-top:20px;
    width: 240px;
    height: 100px;
    box-shadow: 0 10px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    border-radius: 5px;
}
.content1-a2{
    margin-top:20px;
    width: 240px;
    height: 250px;
    box-shadow: 0 10px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    border-radius: 5px;
}
.content1-a1{
    width: 240px;
    height: 90px;
    box-shadow: 0 10px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    border-radius: 5px;
}
.content1{
    display: inline-block;
    position: absolute;
}
.content2{
    width: 970px;
    border-radius: 15px;;
    height: auto;
    margin-left:255px;
     box-shadow: 0 10px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}
</style>