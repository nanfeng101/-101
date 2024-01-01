<template>
<div id="container" style="height: 400px"></div>
</template>
<script>
import * as echarts from 'echarts';
export default{
    mounted(){
        setTimeout(()=>{
            this.a()
        },300)
    },
    created: function(){
        this.getview()
        this.getDateDian()
        this.getDateCollect()
    },
    data(){
        return{
            viewTime:[],
            viewSum:[],
            dianSum:[],
            dianTime:[],
            collectSum:[],
            commentsum:[],
        }
    },
    computed: {
        text(){
        return this.$store.state.text
        }
    },
    watch:{
        text(){
            this.getview()
            this.getDateDian()
            this.getDateCollect()
            this.getCommentsum()
            setTimeout(()=>{
                this.a()
            },300)
        }
    },
    methods:{
        getCommentsum(){
            let i=this.$store.state.text.id
            this.$axios.get('/getCommentsum?id='+i)
            .then(successResponse => {
                this.commentsum=successResponse.data.sum1
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getDateDian(){
            let i=this.$store.state.text.id
            let item=this.$store.state.userid1
            this.$axios.get('/getDateDian?user_id='+item+'&&text_id='+i)
            .then(successResponse => {
                this.dianSum=successResponse.data.sum1
                this.dianTime=successResponse.data.time1
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getDateCollect(){
            let i=this.$store.state.text.id
            let item=this.$store.state.userid1
            this.$axios.get('/getDateCollect?user_id='+item+'&&text_id='+i)
            .then(successResponse => {
                this.collectSum=successResponse.data.sum1
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getview(){
            let i=this.$store.state.text.id
            let item=this.$store.state.userid1
            this.$axios.get('/getDateView?user_id='+item+'&&text_id='+i)
            .then(successResponse => {
                this.viewSum=successResponse.data.sum
                this.viewTime=successResponse.data.time
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        a(){
            var myChart = echarts.init(document.getElementById('container'));
            myChart.setOption({
                title: {
                    text: '数据排行',
                    subtext: '七天数据排行'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['阅读量', '点赞量','评论量','收藏量']
                    // data:this.name
                },
                toolbox: {
                    show: true,
                    feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                    }
                },
                calculable: true,
                xAxis: [
                    {
                    type: 'category',
                    // prettier-ignore
                    // data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',]
                    data: this.viewTime
                    }
                ],
                yAxis: [
                    {
                    type: 'value'
                    }
                ],
                series: [
                    {
                    name: '阅读量',
                    type: 'bar',
                    // data: [
                    //     2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6
                    // ],
                    data: this.viewSum,
                    markPoint: {
                        data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    },
                    color: '#87CEFA'
                    },
                    {
                    name: '点赞量',
                    type: 'bar',
                    // data: [
                    //     2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6
                    // ],
                    data: this.dianSum,
                    markPoint: {
                        data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    },
                    color: '#FFC0CB'
                    },
                    {
                    name: '评论量',
                    type: 'bar',
                    data: this.commentsum,
                    // data: this.mvplay,
                    markPoint: {
                        data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    },
                    color: '#00FF00'
                    },
                    {
                    name: '收藏量',
                    type: 'bar',
                    // data: [
                    //     3.6, 4.9, 8.0, 32.4, 18.7, 40.7, 185.6
                    // ],
                    data: this.collectSum,
                    markPoint: {
                        data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    },
                    color: '#fd6b09'
                    }
                ]
            })
        }
    }
}
</script>
