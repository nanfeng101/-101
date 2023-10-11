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
        this.getSongMvPlay()
    },
    data(){
        return{
            songplay:[],
            mvplay:[],
            name:[],
        }
    },
    methods:{
        getSongMvPlay(){
            this.$axios
            .get('/getSongMvPlay')
            .then(successResponse=>{
                this.songplay=successResponse.data.song
                this.mvplay=successResponse.data.mv
                this.name=successResponse.data.name
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })
        },
        a(){
            var myChart = echarts.init(document.getElementById('container'));
            myChart.setOption({
                title: {
                    text: '热度排行',
                    subtext: '热度前12'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['歌曲', 'MV']
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
                    // data: ['Jan,mv', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                    data: this.name
                    }
                ],
                yAxis: [
                    {
                    type: 'value'
                    }
                ],
                series: [
                    {
                    name: '歌曲',
                    type: 'bar',
                    // data: [
                    //     2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
                    // ],
                    data: this.songplay,
                    markPoint: {
                        data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    }
                    },
                    {
                    name: 'MV',
                    type: 'bar',
                    // data: [
                    //     2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
                    // ],
                    data: this.mvplay,
                    markPoint: {
                        data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    },
                    color: ['#87CEFA', '#FFC0CB']
                    }
                ]
            })
        }
    }
}
</script>

