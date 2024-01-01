package com.example.demo.service.Luntan;

import com.example.demo.dao.luntan.LuntanMapper;
import com.example.demo.enity.Luntan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LuntanServiceImpl implements LuntanService{
    @Resource
    private LuntanMapper luntanMapper;
    public List<Luntan> getLuntanText(){
        List<Luntan> list = luntanMapper.getLuntanText();
        for(Luntan item:list){
            Date birth = item.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item.setTime(birth1);
        }
        return list;
    }
    public List<Luntan> getUserIdLuntanText(int id){
        List<Luntan> list = luntanMapper.getUserIdLuntanText(id);
        for(Luntan item:list){
            Date birth = item.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item.setTime(birth1);
        }
        return list;
    }
    public String addLuntanText(Luntan luntan){
        Date date = new Date();
        luntan.setCreate_time(date);
        if(luntan.getIscover1()==1){
            luntan.setIs_cover(true);
        }
        else{
            luntan.setIs_cover(false);
        }
        if(luntanMapper.addLuntanText(luntan)>0){
            return "ok";
        }
        else{
            return "no";
        }
    }
}
