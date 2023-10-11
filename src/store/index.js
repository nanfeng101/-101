import Vuex from 'vuex'
import VuexAlong from 'vuex-along'
const store = new Vuex.Store({
    state: {
        song_listId:null,  //用于歌单界面展示数据
        style:'',          
        sex:null,
        song_name:'',
        song_pic:'未播放.jpg',
        song_url:'',
        songid:null,     //歌曲id
        urllist:[{}],   //播放歌曲列表
        list_id:0,
        isPlay:false,
        ismenu:false, // 是否显示顶部菜单栏和播放栏
        lyric:'',
        time:null,
        userid:null, //用于判断用户是否登录
        userid1:null,//用于进入其他用户的主页
        userpic:'http://localhost:8081/zf/22.png', //用户头像
        videourl:'' ,//MV地址
        videoint:'',//MV介绍
        videodetail:{},//mv全部信息
        searchvalue:'', // 搜锁值
        comment:[{}],  //用于存储查找评论
        userdetail:{},//用于存储登录用户的信息
    },
    mutations:{
        getUserid1(state,val){
            state.userid1=val
        },
        getvideodetail(state,val){
            state.videodetail=val
        },
        getuserdetail(state,val){
            state.userdetail=val
        },
        insertComment(state,val){
            state.comment.push(val)
        },
        getComment(state,val){
            state.comment=val
        },
        insertUrllist(state,val){
            state.urllist.push(val)
        },
        getsearchvalue(state,val){
            state.searchvalue=val
        },
        getList_id(state,val){
            state.list_id=val
        },
        getUserId(state,val){
            state.userid=val
        },
        getUserpic(state,val){
            let url = "http://localhost:8081/zf/"+val
            state.userpic=url
        },
        getVideourl(state,val){
            let url = "http://localhost:8081/zf/"+val
            state.videourl=url
        },
        getVideoint(state,val){
            state.videoint=val
        },
        addSong_listId(state,val){
            state.song_listId=val
            // window.localStorage.setItem('song_listId', JSON.stringify(val))
        },
        getSongid(state,val){
            state.songid=val
        },
        getSong_listStyle(state,val){
            state.style=val
        },
        getSingerSex(state,val){
            state.sex=val;
        },
        getLyric(state,val){
            state.lyric=val;
        },
        getSong_name(state,val){
            state.song_name=val
        },
        getTime(state,val){
            state.time=val
        },
        getSong_pic(state,val){
            let pic="http://localhost:8081/zf/"+val
            state.song_pic=pic
        },
        getSong_url(state,val){
            let url="http://localhost:8081/zf/"+val
            state.song_url=url
        },
        setUrllist(state,val){
            state.urllist=val
        },
        setIsplay(state,val){
            state.isPlay=val
        },
        setIsmenu(state,val){
            state.ismenu=val
        },
        
    },
    plugins:[VuexAlong({
        name:'along',
        local:false,
    })]
  })
  
export default store

