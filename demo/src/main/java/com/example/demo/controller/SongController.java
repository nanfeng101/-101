package com.example.demo.controller;
import com.example.demo.entity.SearchSong;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongByCon;
import com.example.demo.service.SongService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
public class SongController {
    @Resource
    private SongService songService;
    @GetMapping("/getSongPage")
    public Map<String,Object> selectSongPage(SongByCon songByCon){
        return songService.getSongPage(songByCon);
    }
    @PostMapping("/updateSongPic")
    public void updateSongPic(@RequestParam("file") MultipartFile myfile, @RequestParam("id") int id)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fileName = myfile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        myfile.transferTo(filePath);
        songService.updateSongPic(giamg,id);
    }
    @GetMapping("/deleteSong")
    public String dleteSong(int id){
        return songService.deleteSong(id);
    }
    @PostMapping("/getIdSong")
    public Song getIdSong(int id){
        return songService.getIdSong(id);
    }
    @PostMapping("/updateSong")
    public String updateSinger(@RequestBody Song song){
        return songService.updateSong(song);
    }
    @PostMapping("/addSong")
    public String addSong(@RequestBody Song song)throws Exception{
        return songService.addSong(song);
    }
    @PostMapping("/updateSongUrl")
    public void updateSongUrl(@RequestParam("file") MultipartFile myfile, @RequestParam("id") int id)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fileName = myfile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        myfile.transferTo(filePath);
        songService.updateSongUrl(giamg,id);
    }
    @PostMapping("/searchSong")
    public Map<String, Object> searchSong(@RequestBody SearchSong searchSong){
        return songService.searchSong(searchSong);
    }
    @GetMapping("/classifysong1")
    public List<Song> classifysong(String name){
        return songService.classifysong(name);
    }
    @GetMapping("/updatePlay_sum")
    public void updatePlay_sum(int id){
        songService.updatePlay_sum(id);
    }
}
