package com.example.andorid_project.shouye.geci;

import java.util.List;

public class LrcBean {
    long longtime;//long类型时间
    String time, text;//时间文本与歌词文本
    String biaoti;
    String zhuozhe;
    String geci;
    int pic;
    List<LrcBean> lrcBeanList;

    public long getLongtime() {
        return longtime;
    }

    public void setLongtime(long longtime) {
        this.longtime = longtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LrcBean(long longtime, String time, String text) {
        this.longtime = longtime;
        this.time = time;
        this.text = text;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getZhuozhe() {
        return zhuozhe;
    }

    public void setZhuozhe(String zhuozhe) {
        this.zhuozhe = zhuozhe;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getGeci() {
        return geci;
    }

    public void setGeci(String geci) {
        this.geci = geci;
    }

    public List<LrcBean> getLrcBeanList() {
        return lrcBeanList;
    }

    public void setLrcBeanList(List<LrcBean> lrcBeanList) {
        this.lrcBeanList = lrcBeanList;
    }
}
