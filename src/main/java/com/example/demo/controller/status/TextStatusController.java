package com.example.demo.controller.status;

import com.example.demo.dao.status.TextStatusMapper;
import com.example.demo.enity.Text;
import com.example.demo.service.status.TextStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TextStatusController {
    @Resource
    private TextStatusService textStatusService;
    @GetMapping("/getTouTiaoStatus")
    public List<Text> getTouTiaoStatus(int current, int pageSize){
        return textStatusService.getTouTiaoStatus(current,pageSize);
    }
    @GetMapping("/getReiDianStatus")
    public List<Text> getReiDianStatus(int current, int pageSize){
        return textStatusService.getReiDianStatus(current,pageSize);
    }
    @GetMapping("/getExamineStatus")
    public List<Text> getExamineStatus(int id){
        return textStatusService.getExamineStatus(id);
    }
    @GetMapping("/getAllExamineStatus")
    public List<Text> getAllExamineStatus(){
        return textStatusService.getAllExamineStatus();
    }
    @GetMapping("/updateStatusToutiao")
    public int updateStatusToutiao(int n,int id){
        return textStatusService.updateStatusToutiao(n,id);
    }
    @GetMapping("/updateStatusRedian")
    public int updateStatusRedian(int n,int id){
        return textStatusService.updateStatusRedian(n,id);
    }
    @GetMapping("/searchBiaotiText")
    public List<Text> searchBiaotiText(String str){
        return textStatusService.searchBiaotiText(str);
    }
}
