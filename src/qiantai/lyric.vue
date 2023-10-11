<template>
<div class="all">
<div style="display: inline-block;">
    <img style="padding-top:100px;padding-left:200px" :src="this.$store.state.song_pic" width="400" height="300">
</div>
<div style="display: inline-block;position: absolute;">
    <p style="padding-top:100px;font-size:30px;padding-left:170px;color:#fff">{{this.$store.state.song_name}}</p>
</div>
<div id="demo" ref="top">
    <ul class="has-lyric" v-if="lyr.length" key="has-lyric">
        <li style="margin-top:5px;color:#fff" v-for="item in lyr" :key="item">
          {{ item[1] }}
        </li>
      </ul>
</div>
</div>
</template>
<script>
export default{
    name:'lyric',
    data(){
        return{
            lyr:[],
            scroll:0,
        }
    },
    created: function(){
        this.handlelyric()
    },
    methods:{
        handlelyric(){
            let text = this.$store.state.lyric
            let lines = text.split('\n')
            let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g
            this.lyr = []

            // 对于歌词格式不对的特殊处理
            if (!(/\[.+\]/.test(text))) {
                return [[0, text]]
            }

            while (!pattern.test(lines[0])) {
                lines = lines.slice(1)
            }

            lines[lines.length - 1].length === 0 && lines.pop()
            for (let item of lines) {
                let time = item.match(pattern) // 存前面的时间段
                let value = item.replace(pattern, '') // 存歌词
                // console.log(time) // 时间
                // console.log(value) // 歌词数据
                for (let item1 of time) {
                let t = item1.slice(1, -1).split(':')
                if (value !== '') {
                    this.lyr.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value])
                }
                }
            }
            this.lyr.sort(function (a, b) {
                return a[0] - b[0]
            })
        }
    },
    computed: {
        time(){
        return this.$store.state.time
        },
        lyric1(){
            return this.$store.state.lyric
        }
  },
    watch:{
        time(val){
            if(this.lyr.length !== 0){
            for(let i=0;i<this.lyr.length;i++){
                if(val >= this.lyr[i][0]){
                    for(let j=0;j<this.lyr.length;j++){
                        document.querySelectorAll('.has-lyric li')[j].style.color = '#fff'
                        document.querySelectorAll('.has-lyric li')[j].style.fontSize = '15px'
                    }
                    if (i >= 0) {
                        document.querySelectorAll('.has-lyric li')[i].style.color = '#95d2f6'
                        document.querySelectorAll('.has-lyric li')[i].style.fontSize = '25px'
                        setTimeout(()=>{
                            if(i>4 && i%10==0){
                            this.scroll+=7
                            document.getElementById("demo").scrollTo({top:this.scroll,behavior:"smooth"})
                           }  
                        },100)                 
                        }
                }
            }
            }
        },
        lyric1(val){
            this.lyr=val
            this.handlelyric()
        }

    },
}
</script>
<style scoped>
.all{
    height: 700px;
    background-image: url("/img/lyr.jpg");
    background-size: 100%,100%;
}
#demo{
    margin-left: 120px;
    margin-top: 200px;
    height: 400px;
    overflow-y: scroll;
    position: absolute;
    display: inline-block;
}
#demo::-webkit-scrollbar{
    display: none;
}
.has-lyric{
    list-style: none;
}
</style>
