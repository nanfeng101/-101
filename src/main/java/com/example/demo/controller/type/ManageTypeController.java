package com.example.demo.controller.type;

import com.example.demo.enity.Text_type;
import com.example.demo.service.type.ManageTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ManageTypeController {
    @Resource
    private ManageTypeService manageTypeService;
    //获取全部标签
    @GetMapping("/getAllType")
    public List<Text_type> getAllType(){
        return manageTypeService.getAllType();
    }
}
