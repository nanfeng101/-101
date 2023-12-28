package com.example.andorid_project.shouye.geci;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PraseLrc {
    public static List<LrcBean> jiexi(String str) {
        //获取歌词文本
        String lyr = str;
        //[ti:歌曲名]－title题目,标题,曲目
        String tiText = getCenterText("[ti", "]", lyr);
        //[ar:歌手名]－artist艺术家、演唱者
        String arText = getCenterText("[ar", "]", lyr);
        //[al:专辑名]－album唱片集、专辑
        String alText = getCenterText("[al", "]", lyr);
        //[by:编辑者](一般指lrc歌词的制作人)
        String byText = getCenterText("[by", "]", lyr);
        //[offset:时间补偿值]其单位是毫秒，正值表示延迟，负值表示提前。用于整体调整歌词显示慢（快）于音乐播放。
        String offsetText = getCenterText("[offset", "]", lyr);
        //解析歌词
        List<LrcBean> lrcBeans = new ArrayList<>();//存放歌词
        String[] split = lyr.split("\\n");//分割
        if (split.length == 1) split = lyr.split("\\\\n");
        for (String s : split) {
            List<LrcBean> list = parseLine(s);
            if (list != null && !list.isEmpty()) {
                lrcBeans.addAll(list);
            }
        }
        //按照时间排序
        Collections.sort(lrcBeans, (lyrBean, t1) -> (int)
                (lyrBean.getLongtime() - t1.getLongtime()));
        //输出看看歌词效果
        return lrcBeans;

    }

    //解析每一行的歌词
    private static List<LrcBean> parseLine(String s) {
        if (s.isEmpty()) {
            return null;
        }
        // 去除空格
        s = s.trim();
        // 正则表达式，判断s中是否有[00:00.60]或[00:00.600]格式的片段
        Matcher lineMatcher = Pattern.
                compile("((\\[\\d{2}:\\d{2}\\.\\d{2,3}\\])+)(.+)").matcher(s);
        // 如果没有，返回null
        if (!lineMatcher.matches()) {
            return null;
        }
        // 得到时间标签
        String times = lineMatcher.group(1);
        // 得到歌词文本内容
        String text = lineMatcher.group(3);
        List<LrcBean> entryList = new ArrayList<>();
        Matcher timeMatcher = Pattern.compile("\\[(\\d\\d):(\\d\\d)\\.(\\d{2,3})\\]").matcher(times);
        while (timeMatcher.find()) {
            long min = Long.parseLong(timeMatcher.group(1));// 分
            long sec = Long.parseLong(timeMatcher.group(2));// 秒
            long mil = Long.parseLong(timeMatcher.group(3));// 毫秒
            // 转换为long型时间
            int scale_mil=mil>100?1:10;//如果毫秒是3位数则乘以1，反正则乘以10
            // 转换为long型时间
            long time =
                    min * 60000 +
                            sec * 1000 +
                            mil * scale_mil;
            // 最终解析得到一个list
            entryList.add(new LrcBean(time, times, text));
        }
        return entryList;
    }

    //获取中间文本，主要是获取歌词中的歌名专辑名这些
    private static String getCenterText(String start, String end, String s) {
        if (start.isEmpty() || end.isEmpty() || s.isEmpty()) {
            return "";
        }
        int i = s.indexOf(start);
        int i1 = s.indexOf(end, i);
        if (i == -1 || i1 == -1) return "";
        String s1 = s.substring(i + start.length(), i1);
        return s1;
    }

    //歌词文本
}
