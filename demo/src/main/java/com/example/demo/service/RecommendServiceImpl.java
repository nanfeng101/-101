package com.example.demo.service;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.Recommend;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.mapper.RecommendMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RecommendServiceImpl implements RecommendService{
    @Resource
    private RecommendMapper recommendMapper;
    public List<Recommend> getRecommendSong(String style){
        List<Recommend> list1 = recommendMapper.getRecommendSong(style);
        for(Recommend item:list1){
            String name1=item.getName();
            char[] name2=name1.toCharArray();
            String name3="";
            String name4="";
            int m=0;
            for(int i=0;i<name2.length;i++){
                if(name2[i]=='-'){
                    m=i;
                    break;
                }
                name3=name3+name2[i];
            }
            item.setSinger_name(name3);
            for(int j=m+1;j<name2.length;j++){
                name4=name4+name2[j];
            }
            item.setName(name4);
        }
        return list1;
    }
}
