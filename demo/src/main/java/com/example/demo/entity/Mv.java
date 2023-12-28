package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

public class Mv {
    private int id1;
    private String name;
    private String introduce;
    private String pic;
    private String style;
    private String url;
    private MultipartFile myfile;
    private int play_sum;

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MultipartFile getMyfile() {
        return myfile;
    }

    public void setMyfile(MultipartFile myfile) {
        this.myfile = myfile;
    }

    public int getPlay_sum() {
        return play_sum;
    }

    public void setPlay_sum(int play_sum) {
        this.play_sum = play_sum;
    }
}
