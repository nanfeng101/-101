package com.example.demo.controller.user;

import com.example.demo.enity.User_browse;
import com.example.demo.enity.User_browse_current;
import com.example.demo.enity.User_collect_name;
import com.example.demo.service.user.UserCollectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class UserCollectController {
    @Resource
    private UserCollectService userCollectService;
    @GetMapping("/selectCollectName")
    public List<User_collect_name> selectCollectName(int id){
        return userCollectService.selectCollectName(id);
    }
    @PostMapping("/addCollect")
    public String addCollect(@RequestBody User_collect_name user_collect_name){
        return userCollectService.addCollect(user_collect_name);
    }
    @GetMapping("/addCollect_text")
    public List<User_collect_name> addCollect_text(int id,String name){
        return userCollectService.addCollect_text(id,name);
    }
    @PostMapping("/updateCollect_name")
    public String updateCollect_name(@RequestBody User_collect_name user_collect_name){
        return userCollectService.updateCollect_name(user_collect_name);
    }
    @GetMapping("/deleteCollect")
    public String deleteCollect(int user_id,int text_id){
        return userCollectService.deleteCollect(user_id,text_id);
    }
    @PostMapping("/getUserBrowse")
    public Map<String,Object> getUserBrowse(@RequestBody User_browse_current user_browse_current){
        return userCollectService.getUserBrowse(user_browse_current);
    }
    @PostMapping("/addUserBrowse")
    public void addUserBrowse(@RequestBody User_browse user_browse){
        userCollectService.addUserBrowse(user_browse);
    }
}
