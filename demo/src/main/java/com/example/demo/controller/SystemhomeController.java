package com.example.demo.controller;

import com.example.demo.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class SystemhomeController {
    @Resource
    private SystemhomeService systemhomeService;
    @GetMapping("/getUserSum")
    public int getUserSum(){
        return systemhomeService.getUserSum();
    }
    @GetMapping("/getSongSum")
    public int getSongSum(){
        return systemhomeService.getSongSum();
    }
    @GetMapping("/getSingerSum")
    public int getSingerSum(){
        return systemhomeService.getSingerSum();
    }
    @GetMapping("/getSong_listSum")
    public int getSong_listSum(){
        return systemhomeService.getSong_listSum();
    }
    // /userSexwomandata  singerwomanData  userSexmandata  songData  singermanData  song_listData
    @GetMapping("/userSexwomandata")
    public int userSexwomandata(){
//        System.out.println(systemhomeService.selectUserSex(0));
        return systemhomeService.selectUserSex(0);
    }
    @GetMapping("/userSexmandata")
    public int userSexmandata(){
//        System.out.println(systemhomeService.selectUserSex(1));
        return systemhomeService.selectUserSex(1);
    }
    @GetMapping("/singerwomanData")
    public int singerwomanData(){
//        System.out.println(systemhomeService.selectSingerSex(0));
        return systemhomeService.selectSingerSex(3);
    }
    @GetMapping("/singermanData")
    public int singermanData(){
//        System.out.println(systemhomeService.selectSingerSex(1));
        return systemhomeService.selectSingerSex(1);
    }
    @GetMapping("/songData")
    public List<Integer> songData(){
//        System.out.println(systemhomeService.selectSong_listStyle());
        return systemhomeService.selectSong_listStyle();
    }
    @GetMapping("/song_listData")
    public List<Integer> song_listData(){
//        System.out.println(systemhomeService.selectSingerLocation());
        return systemhomeService.selectSingerLocation();
    }
    @GetMapping("/getSongMvPlay")
    public Map<String,Object> getSongMvPlay(){
        return systemhomeService.getSongMvPlaySum();
    }

}
