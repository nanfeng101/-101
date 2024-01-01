package com.example.demo.controller.link;

import com.example.demo.enity.Link;
import com.example.demo.service.link.ManageLinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ManageLinkController {
    @Resource
    private ManageLinkService manageLinkService;
    //获得所有友链
    @GetMapping("/getAllLink")
    public List<Link> getAllLink(){
        return manageLinkService.getAllLink();
    }
}
