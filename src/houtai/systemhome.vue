<template>
    <el-row :gutter="20" class="mgb20">
      <el-col :span="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}">
          <div class="grid-content grid-con-1">
            <div class="grid-cont-right">
              <div class="grid-num">{{userCount}}</div>
              <div>用户总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}">
          <div class="grid-content grid-con-2">
            <div class="grid-cont-right">
              <div class="grid-num">{{songCount}}</div>
              <div>歌曲总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}">
          <div class="grid-content grid-con-3">
            <div class="grid-cont-right">
              <div class="grid-num">{{singerCount}}</div>
              <div>歌手数量</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}">
          <div class="grid-content grid-con-4">
            <div class="grid-cont-right">
              <div class="grid-num">{{songListCount}}</div>
              <div>歌单数量</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <Aa style="margin-top:20px"></Aa>
    <div style="display: inline-flex;margin-top:10px">
    <div id="Echart" style="background-color: white;width:700px;height:250px">
    </div>
    <div id="Echart3" style="background-color: white;width:700px;height:250px">
    </div>
    </div>
    <div style="display: inline-flex;">
        <div id="Echart2" style="background-color: white;width:700px;height:300px">
        </div>
        <div id="Echart4" style="background-color: white;width:700px;height:300px">
        </div>
    </div>       

</template>
<script>
import * as echarts from 'echarts';
import Aa from '../houtai/echarts/aa.vue'
export default{
    name:'Systemhome',
    components:{
        Aa,
    },
    created: function () {
        this.sexmanData()
        this.sexwomanData()
        this.singermanData()
        this.singerwomanData()
        this.song_listData1()
        this.songData1()
        this.getUserSum()
        this.getSongSum()
        this.getSingerSum()
        this.getSong_listSum()

    },
    data(){
        return{
            userCount:0,
            songCount:0,
            singerCount:0,
            songListCount:0,
            songData:[],
            song_listData:[],
            userman:0,
            userwoman:0,
            singerman:0,
            singerwoman:0,

        }
    },
    mounted(){
        setTimeout(()=>{
            this.a1()
            this.a2()
            this.a3()
            this.a4()

        },300)
    },
    methods:{
        sexwomanData(){
            this.$axios
            .get('/userSexwomandata')
            .then(successResponse=>{
                this.userwoman=successResponse.data
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })
        },
        singerwomanData(){
            this.$axios
            .get('/singerwomanData')
            .then(successResponse=>{
                this.singerwoman=successResponse.data
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })

        },
        sexmanData(){
            this.$axios
            .get('/userSexmandata')
            .then(successResponse=>{
                this.userman=successResponse.data
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })
        },
        songData1(){
            this.$axios
            .get('/songData')
            .then(successResponse=>{
                this.songData=successResponse.data
            })
            .catch(failResponse =>{
                this.$alert(failResponse.response.status)
            })

        },
        singermanData(){
            this.$axios
            .get('/singermanData')
            .then(successResponse=>{
                this.singerman=successResponse.data
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })

        },
        song_listData1(){
            this.$axios
            .get('/song_listData')
            .then(successResponse=>{
                this.song_listData=successResponse.data
            })
            .catch(failResponse =>{
                    this.$alert(failResponse.response.status)
            })

        },
        a4(){
            var myChart = echarts.init(document.getElementById('Echart4'));
            myChart.setOption({
                 title: {
                text: '歌手性别比例',
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '5%',
                left: 'center'
            },
            series: [
                {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                    show: true,
                    fontSize: 40,
                    fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    { value: this.singerman, name: '男' },
                    { value: this.singerwoman, name: '女' },
                ],
                color: ['#87CEFA', '#FFC0CB']
                }
            ]
          })

        },
        a2(){
            var myChart = echarts.init(document.getElementById('Echart2'));
            myChart.setOption({
            title: {
                text: '用户性别比例',
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '5%',
                left: 'center'
            },
            series: [
                {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                    show: true,
                    fontSize: 40,
                    fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    { value: this.userman, name: '男' },
                    { value: this.userwoman, name: '女' },
                ],
                color: ['#87CEFA', '#FFC0CB']
                }
            ]
            })

        },
        a1(){
            var myChart = echarts.init(document.getElementById('Echart'));
            myChart.setOption({
            title: {
                text: '歌曲类型分布'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {},
            toolbox: {
                show: true,
                feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: { readOnly: false },
                magicType: { type: ['line', 'bar'] },
                restore: {},
                saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['华语', '粤语', '欧美', '日本', 'BGM', '轻音乐','乐器']
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                formatter: '{value}'
                }
            },
            series: [
                {
                name: '歌曲类型',
                type: 'line',
                data: this.songData,
                markPoint: {
                    data: [{ name: '周最低', value: -2, xAxis: 1, yAxis: -1.5 }]
                },
                markLine: {
                    data: [
                    { type: 'average', name: 'Avg' },
                    [
                        {
                        symbol: 'none',
                        x: '90%',
                        yAxis: 'max'
                        },
                        {
                        symbol: 'circle',
                        label: {
                            position: 'start',
                            formatter: 'Max'
                        },
                        type: 'max',
                        name: '最高点'
                        }
                    ]
                    ]
                }
                }
            ]
            })

        },
        a3(){
            var myChart = echarts.init(document.getElementById('Echart3'));
            myChart.setOption({
            title: {
                text: '歌手国籍分布'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {},
            toolbox: {
                show: true,
                feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: { readOnly: false },
                magicType: { type: ['line', 'bar'] },
                restore: {},
                saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['中国', '韩国', '意大利', '日本', '美国','西班牙','英国','瑞典']
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                formatter: '{value}'
                }
            },
            series: [
                {
                name: '歌手国籍',
                type: 'line',
                data: this.song_listData,
                markPoint: {
                    data: [{ name: '周最低', value: -2, xAxis: 1, yAxis: -1.5 }]
                },
                markLine: {
                    data: [
                    { type: 'average', name: 'Avg' },
                    [
                        {
                        symbol: 'none',
                        x: '90%',
                        yAxis: 'max'
                        },
                        {
                        symbol: 'circle',
                        label: {
                            position: 'start',
                            formatter: 'Max'
                        },
                        type: 'max',
                        name: '最高点'
                        }
                    ]
                    ]
                }
                }
            ]
            })

        },
        getUserSum(){
            this.$axios
            .get('/getUserSum')
            .then(successResponse => {
                this.userCount=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getSongSum(){
            this.$axios
            .get('/getSongSum')
            .then(successResponse => {
                this.songCount=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getSingerSum(){
            this.$axios
            .get('/getSingerSum')
            .then(successResponse => {
                this.singerCount=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        getSong_listSum(){
            this.$axios
            .get('/getSong_listSum')
            .then(successResponse => {
                this.songListCount=successResponse.data
            })
            .catch(failResponse => {
            this.$alert(failResponse.response.status)
            })
        },
        
    }
}
</script>

<style>
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
