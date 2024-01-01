package com.example.demo.controller.luntan;

import com.example.demo.enity.Luntan;
import com.example.demo.service.Luntan.LuntanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class LuntanController {
    @Resource
    private LuntanService luntanService;
    @GetMapping("/getLuntanText")
    public List<Luntan> getLuntanText(){
        return luntanService.getLuntanText();
    }
    @PostMapping("/addLuntanText")
    public String addLuntanText(@RequestBody Luntan luntan){
        return luntanService.addLuntanText(luntan);
    }
    @GetMapping("/getUserIdLuntanText")
    public List<Luntan> getUserIdLuntanText(int id){
        return luntanService.getUserIdLuntanText(id);
    }
}
