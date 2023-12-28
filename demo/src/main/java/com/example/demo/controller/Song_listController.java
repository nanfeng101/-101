package com.example.demo.controller;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.Song;
import com.example.demo.entity.Song_list;
import com.example.demo.entity.Song_listByCon;
import com.example.demo.service.Song_listService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
public class Song_listController {
    @Resource
    private Song_listService song_listService;
    @GetMapping("/getSong_listPage")
    public Map<String,Object> getSong_listPage(Song_listByCon song_listByCon) {
        return song_listService.getSong_listPage(song_listByCon);
    }
    @PostMapping("/updateSong_listPic")
    public void updateSong_listPic(@RequestParam("file") MultipartFile myfile, @RequestParam("id") int id)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fileName = myfile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        myfile.transferTo(filePath);
        song_listService.updateSong_listPic(giamg,id);
    }
    @GetMapping("/getIdSong_list")
    public Song_list getIdSong_list(int id){
        return song_listService.getIdSong_list(id);
    }
    @PostMapping("/updateSong_list")
    public String updateSong_list(@RequestBody Song_list songList){
        return song_listService.updateSong_list(songList);
    }
    @GetMapping("/deleteSong_list")
    public String deleteSong_list(int id){
        return song_listService.deleteSong_list(id);
    }
    @PostMapping("/addSong_list")
    public String addSong_list(Song_list songList){
        return song_listService.addSong_list(songList);
    }
    @GetMapping("/selectTitleSong_list")
    public Map<String,Object> selectTitleSong_list(String title){
        return song_listService.selectTitleSong_list(title);
    }
    @GetMapping("/getSong_listContent")
    public Map<String,Object> getSong_listContent(int id){
        return song_listService.getSong_listContent(id);
    }
    @GetMapping("/deleteSong_ListSong")
    public String deleteSong_ListSong(int id){
        return song_listService.deleteSong_ListSong(id);
    }
    @GetMapping("/getSong_list_to_qianttai")
    public Map<String,Object> getSong_list_to_qianttai(int id){
        return song_listService.getSong_list_to_qianttai(id);
    }
    //安卓获取歌单调用的方法
    @PostMapping ("/getAndroidSong_listPage")
    public List<Song_list> getAndroidSong_list(Song_listByCon song_listByCon) {
        System.out.println(song_listByCon.getPageSize());
        return song_listService.getAndroidSong_listPage(song_listByCon);
    }
    @PostMapping("/getAndroidSong_listMusic")
    public List<Bangdan> getAndroidSong_listMusic(int id){
        return song_listService.getAndroidSong_listMusic(id);
    }


}
