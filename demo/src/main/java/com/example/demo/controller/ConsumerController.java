package com.example.demo.controller;

import com.example.demo.entity.Consumer;
import com.example.demo.entity.ConsumerByCon;
import com.example.demo.entity.Song_list;
import com.example.demo.service.ConsumerService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
public class ConsumerController {
    @Resource
    private ConsumerService consumerService;
    @GetMapping("/getConsumerPage")
    public Map<String,Object> getConsumerPage(ConsumerByCon consumerByCon){
        return consumerService.getConsumerPage(consumerByCon);
    }

    @PostMapping("/updateAcator")
    public void updatePic(@RequestParam("file") MultipartFile myfile, @RequestParam("id") int id)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fileName = myfile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        myfile.transferTo(filePath);
        consumerService.updateAcator(giamg,id);
    }
    @PostMapping("/addConsumer")
    public String addConsumer(@RequestBody Consumer consumer){
        return consumerService.addConsumer(consumer);
    }
    @GetMapping("/deleteConsumer")
    public String deleteConsumer(int id){
        return consumerService.deleteConsumer(id);
    }
    @GetMapping("/getIdConsumer")
    public Consumer getIdConsumer(int id){
        return consumerService.getIdConsumer(id);
    }
    @PostMapping("/updateConsumer")
    public String updateConsumer(@RequestBody Consumer consumer){
        return consumerService.updateConsumer(consumer);
    }
    @GetMapping("/selectConsumer")
    public Map<String,Object> selectConsumer(String name){
        return consumerService.selectConsumer(name);
    }
    @PostMapping("/userLogin")
    public Map<String,Object> userLogin(@RequestBody Consumer consumer){
        return consumerService.userLogin(consumer);
    }
    @PostMapping("/userRegister")
    public String userRegister(@RequestBody Consumer consumer){
        return consumerService.userRegister(consumer);
    }
    @GetMapping("/shoucang")
    public String shoucang(int userid,int songid){

        return consumerService.shoucang(userid,songid);
    }
    @GetMapping("/getMySong")
    public Map<String,Object> getMySong(int id){
        return consumerService.getMySong(id);
    }
    @GetMapping("/getUserCreateSong_list")
    public List<Song_list> getUserCreateSong_list(int id){
        return consumerService.getUserCreateSong_list(id);
    }
    @GetMapping("/getSingerDetail")
    public Map<String,Object> getSingerDetail(int id){
        return consumerService.getSingerDetail(id);
    }


}
