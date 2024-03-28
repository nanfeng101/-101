package com.example.demo.service.status;

import cn.hutool.json.JSONUtil;
import com.example.demo.dao.status.TextStatusMapper;
import com.example.demo.dao.user.UserDetailMapper;
import com.example.demo.enity.Text;
import com.example.demo.enity.User;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.demo.utils.RedisConstes.*;

@Service
public class TextStatusServiceImpl implements TextStatusService{
    @Resource
    private TextStatusMapper textStatusMapper;
    @Resource
    private UserDetailMapper userDetailMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    //获取头条文章
//    @Cacheable(value = "toutiaotext" , key = "'getTouTiaoStatus'")
    public List<Text> getTouTiaoStatus(int current, int pageSize){
        int sum=pageSize*(current-1);
        String key = TEXT_TOUTIAO_KEY+sum;
        String detail = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(detail)){
            return JSONUtil.toList(detail,Text.class);
        }
        if(detail!=null){
            return null;
        }
        List<Text> list = textStatusMapper.getTouTiaoStatus(sum,pageSize);
        for(Text item1:list){
            User user = userDetailMapper.getUserDetail(item1.getUser_id());
            item1.setUsermame(user.getUsername());
            item1.setUserpic(user.getPic());
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(list),TIME_MINUTES, TimeUnit.MINUTES);
        return list;
    }
    //获取热点文章
//    @Cacheable(value = "rediantext" , key = "'getReiDianStatus'")
    public List<Text> getReiDianStatus(int current, int pageSize){
        int sum=pageSize*(current-1);
        String key = TEXT_REDIAN_KEY+sum;
        String detail = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(detail)){
            return JSONUtil.toList(detail,Text.class);
        }
        if(detail!=null){
            return null;
        }
        List<Text> list = textStatusMapper.getReiDianStatus(sum,pageSize);
        for(Text item1:list){
            User user = userDetailMapper.getUserDetail(item1.getUser_id());
            item1.setUsermame(user.getUsername());
            item1.setUserpic(user.getPic());
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(list),TIME_MINUTES,TimeUnit.MINUTES);
        return list;
    }
    //获取所有用户待审核文章
    public List<Text> getAllExamineStatus(){
        List<Text> list = textStatusMapper.getAllExamineStatus();
        for(Text item1:list){
            User user = userDetailMapper.getUserDetail(item1.getUser_id());
            item1.setUsermame(user.getUsername());
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        return list;
    }
    //获取登录用户待审核文章
    public List<Text> getExamineStatus(int id){
        List<Text> list = textStatusMapper.getExamineStatus(id);
        for(Text item1:list){
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        return list;
    }
    //取消头条推荐
    public int updateStatusToutiao(int n,int id){
        if(textStatusMapper.updateStatusToutiao(n,id)>0){
            return 1;
        }
        else{
            return  0;
        }
    }
    //取消热点推荐
    public int updateStatusRedian(int n,int id){
        if(textStatusMapper.updateStatusRedian(n,id)>0){
            return 1;
        }
        else{
            return 0;
        }
    }
    //根据标题搜索文章
    public List<Text> searchBiaotiText(String str){
        List<Text> list = textStatusMapper.searchBiaotiText(str);
        for(Text item1:list){
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        return list;
    }
}
