package com.example.demo.service.user;

import com.example.demo.dao.user.PjotoMapper;
import com.example.demo.enity.Photo;
import com.example.demo.enity.User_comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService{
    @Resource
    private PjotoMapper pjotoMapper;
    public List<Photo> getPhoto(){
        List<Photo> list = pjotoMapper.getPhoto();
        for(Photo item:list){
            Date birth = item.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birth1 = dateFormat.format(birth);
            item.setTime1(birth1);
        }
        return list;
    }
    public String addPhoto(Photo photo){
        Date date = new Date();
        photo.setTime(date);
        if(pjotoMapper.addPhoto(photo)>0){
            return "ok";
        }
        else{
            return "no";
        }
    }
    public String deletePhoto(int id){
        if(pjotoMapper.deletePhoto(id)>0){
            return "ok";
        }
        else{
            return "no";
        }
    }
}
