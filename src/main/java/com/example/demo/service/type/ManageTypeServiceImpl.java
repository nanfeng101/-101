package com.example.demo.service.type;

import cn.hutool.json.JSONUtil;
import com.example.demo.dao.type.ManageTypeMapper;
import com.example.demo.enity.Text;
import com.example.demo.enity.Text_type;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.demo.utils.RedisConstes.*;

@Service
public class ManageTypeServiceImpl implements ManageTypeService{
    @Resource
    private ManageTypeMapper manageTypeMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    //获取全部标签
    public List<Text_type> getAllType(){
        String key = TEXT_TYPE_KEY;
        String detail = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(detail)){
            return JSONUtil.toList(detail,Text_type.class);
        }
        if(detail!=null){
            return null;
        }
        List<Text_type> allType = manageTypeMapper.getAllType();
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(allType),TIME_MINUTES, TimeUnit.MINUTES);
        return allType;
    }
}
