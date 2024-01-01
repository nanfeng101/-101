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
        this.getAll()
    },
    data(){
        return{
            time:[],
            allview:[],
            alldianzan:[],
            allcollect:[],
        }
    },
    methods:{
        getAll(){
            let item=this.$store.state.userid1
            this.$axios.get('/getAllData?user_id='+item)
            .then(successResponse => {
                this.allview=successResponse.data.view
                this.alldianzan=successResponse.data.dian
                this.time=successResponse.data.time
                this.allcollect=successResponse.data.collect
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        a(){
            var myChart = echarts.init(document.getElementById('container'));
            myChart.setOption({
                title: {
                    text: '总数据排行',
                    subtext: '七天总数据排行'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['阅读量', '点赞量','关注量','收藏量']
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
                    data: this.time
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
                    data: this.allview,
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
                    data: this.alldianzan,
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
                    name: '关注量',
                    type: 'bar',
                    data: [
                        3.6, 4.9, 8.0, 2.4, 8.7, 10.7, 5.6
                    ],
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
                    data: this.allcollect,
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
