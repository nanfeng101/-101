<template>
<div id="container" style="height: 700px;"></div>
</template>
<script>
import * as echarts from 'echarts'
import chinajson from './china.json'
export default{
    mounted(){
        setTimeout(()=>{
            this.a()
        },1000)
    },
    data(){
        return{
            china:[{}],
        }
    },
    created: function(){
        this.getlocation()
    },
    methods:{
        getlocation(){
            let item=this.$store.state.userid1
            this.$axios.get('/getUserFensiLocation?id='+item)
            .then(successResponse => {
                this.china=successResponse.data.location
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        a(){
            echarts.registerMap('中华人民共和国',chinajson)
            var myChart = echarts.init(document.getElementById('container'))
            myChart.setOption({
                visualMap:{
                    min:0,
                    max:100,
                    realtime:false,
                    calculable:true,
                    inRange:{
                        color:['lightskyblue','yellow','orangered']
                    }
                },
                title: {
                    text: '粉丝所在地区',
                },
                tooltip: {
                },
                series:[
                {
                    name:'中国地图',
                    type:'map',
                    map:'中华人民共和国',
                    label: {
                        normal: {
                            show: true,
                            textStyle:{
                                fontSize:10,
                                
                            }
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data:this.china,
                },
                ]
        })
        }
    }
}
</script>
