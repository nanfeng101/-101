package com.example.demo.controller.user;

import com.example.demo.service.user.UserDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserDataController {
    @Resource
    private UserDataService userDataService;
    @GetMapping("/updateview")
    public void updateview(int user_id,int text_id,int view_sum){
        userDataService.updateview(user_id,text_id,view_sum);
    }
    @GetMapping("/getDateView")
    public Map<String, Object> getDateView(int user_id,int text_id){
        return userDataService.getDateView(user_id,text_id);
    }
    @GetMapping("/getDateDian")
    public Map<String, Object> getDateDian(int user_id,int text_id){
        return userDataService.getDateDian(user_id,text_id);
    }
    @GetMapping("/updatedian")
    public void updatedian(int user_id,int text_id,int view_sum){
        userDataService.updatedian(user_id,text_id,view_sum);
    }
    @GetMapping("/getDateCollect")
    public Map<String, Object> getDateCollect(int user_id,int text_id){
        return userDataService.getDateCollect(user_id,text_id);
    }
    @GetMapping("/updatecollect")
    public String updatecollect(int user_id,int text_id,int view_sum,int collect_name_id){
        return userDataService.updatecollect(user_id,text_id,view_sum,collect_name_id);
    }
    @GetMapping("/getAllData")
    public Map<String,Object> getAllData(int user_id){
        return userDataService.getAllData(user_id);
    }
    @GetMapping("/getUserFensiLocation")
    public Map<String,Object> getUserFensiLocation(int id){
        return userDataService.getUserFensiLocation(id);
    }
    @GetMapping("getCommentsum")
    public Map<String,Object> getCommentsum(int id){
        return userDataService.getCommentsum(id);
    }
    @GetMapping("/getTextCommentSum")
    public int getTextCommentSum(int id){
        return userDataService.getTextCommentSum(id);
    }
}
