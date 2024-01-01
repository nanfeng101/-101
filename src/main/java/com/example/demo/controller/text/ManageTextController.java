package com.example.demo.controller.text;

import com.example.demo.enity.Text;
import com.example.demo.service.text.ManageTextService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ManageTextController {
    @Resource
    private ManageTextService manageTextService;
    //获取所有文章的信息
    @GetMapping("/getAllTextDetail")
    public Map<String,Object> getAllTextDetail(){
        return manageTextService.getAllTextDetail();
    }
    //获取文章对应的标签
    @GetMapping("/getTextBiaoqian")
    public List<String> getTextBiaoqian(int id){
        return manageTextService.getTextBiaoqian(id);
    }
    //获得热门文章
    @GetMapping("/getHottext")
    public List<Text> getHottext(){
        return manageTextService.getHottext();
    }
    //修改文章
    @PostMapping("/updateText")
    public int updateText(@RequestBody Text text){
        Date date = new Date();
        text.setUpdate_time(date);
        return manageTextService.updateText(text);

    }
    //删除本地图片
    @PostMapping("/deleteimg")
    public void deleteimg(@RequestBody Text text)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static/"+text.getPic();
        File file = new File(path);
        file.delete();

    }
    //根据文章id获取文章信息
    @GetMapping("/selectIdText")
    public Text selectIdText(int id){
        return manageTextService.selectIdText(id);
    }

}
