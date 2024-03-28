package com.example.demo.service.link;

import cn.hutool.json.JSONUtil;
import com.example.demo.dao.link.ManageLinkMapper;
import com.example.demo.enity.Link;
import com.example.demo.enity.Text;
import com.example.demo.service.type.ManageTypeService;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.demo.utils.RedisConstes.*;

@Service
public class ManageLinkServiceImpl implements ManageLinkService {
    @Resource
    private ManageLinkMapper manageLinkMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    public List<Link> getAllLink(){
        String key = LINK_KEY;
        String detail = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(detail)){
            return JSONUtil.toList(detail, Link.class);
        }
        if(detail!=null){
            return null;
        }
        List<Link> allLink = manageLinkMapper.getAllLink();
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(allLink),TIME_MINUTES, TimeUnit.MINUTES);
        return allLink;
    }
}
