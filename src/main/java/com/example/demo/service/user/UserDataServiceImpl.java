package com.example.demo.service.user;

import com.example.demo.dao.user.UserDataMapper;
import com.example.demo.enity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserDataServiceImpl implements UserDataService{
    @Resource
    private UserDataMapper userDataMapper;
    //更新文章观看量
    public void updateview(int user_id,int text_id,int view_sum){
        System.out.println(user_id);
        userDataMapper.updateTextView(view_sum+1,text_id);
        Date date = new Date();
        Date date1 = new Date(date.getYear(),date.getMonth(),date.getDate());
        if(userDataMapper.getUserView(date1,text_id)==null){
            userDataMapper.addview(date1,text_id);
        }
        else {
            User_view_sum a = userDataMapper.getUserView(date1,text_id);
            userDataMapper.updateUser_view_sum(a.getSum()+1,a.getId());
        }

    }
    //获取七天的观看量
    public Map<String, Object> getDateView(int user_id, int text_id){
        Map<String,Object> map = new HashMap<String,Object>();
        List sum = new ArrayList();
        List<String> time = new ArrayList<String>();
        Date date = new Date();
        int year=date.getYear();
        int month=date.getMonth();
        int day=date.getDate();
        for(int i=6;i>=0;i--){
            Date date1 = new Date(year,month,day-i);
            if(userDataMapper.getUserView(date1,text_id)!=null) {
                User_view_sum userViewSum = userDataMapper.getUserView(date1, text_id);
                sum.add(userViewSum.getSum());
                Date birth = userViewSum.getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(birth);
                time.add(birth1);
            }
            else{
                sum.add(0);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(date1);
                time.add(birth1);
            }
        }
        map.put("time",time);
        map.put("sum",sum);
        return map;

    }


    //更新文章点赞量
    public void updatedian(int user_id,int text_id,int view_sum){
        System.out.println(user_id);
        userDataMapper.updateTextDian(view_sum,text_id);
        Date date = new Date();
        Date date1 = new Date(date.getYear(),date.getMonth(),date.getDate());
        if(userDataMapper.getUserDian(date1,text_id)==null){
            userDataMapper.adddian(date1,text_id);
        }
        else {
            User_dianzan_sum a = userDataMapper.getUserDian(date1,text_id);
            userDataMapper.updateUser_Dian_sum(a.getSum()+1,a.getId());
        }

    }
    //获取七天的点赞量
    public Map<String, Object> getDateDian(int user_id, int text_id){
        Map<String,Object> map = new HashMap<String,Object>();
        List sum = new ArrayList();
        List<String> time = new ArrayList<String>();
        Date date = new Date();
        int year=date.getYear();
        int month=date.getMonth();
        int day=date.getDate();
        for(int i=6;i>=0;i--){
            Date date1 = new Date(year,month,day-i);
            if(userDataMapper.getUserDian(date1,text_id)!=null) {
                User_dianzan_sum userViewSum = userDataMapper.getUserDian(date1, text_id);
                sum.add(userViewSum.getSum());
                Date birth = userViewSum.getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(birth);
                time.add(birth1);
            }
            else{
                sum.add(0);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(date1);
                time.add(birth1);
            }
        }
        map.put("time1",time);
        map.put("sum1",sum);
        return map;
    }
    //获取七天的收藏量
    public Map<String, Object> getDateCollect(int user_id, int text_id){
        Map<String,Object> map = new HashMap<String,Object>();
        List sum = new ArrayList();
        List<String> time = new ArrayList<String>();
        Date date = new Date();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();
        for (int i = 6; i >= 0; i--) {
            Date date1 = new Date(year, month, day - i);
            if (userDataMapper.getUserCollect(date1, text_id) != null) {
                User_collect_sum userViewSum = userDataMapper.getUserCollect(date1, text_id);
                sum.add(userViewSum.getSum());
                Date birth = userViewSum.getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(birth);
                time.add(birth1);
            } else {
                sum.add(0);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(date1);
                time.add(birth1);
            }
        }
        map.put("time1", time);
        map.put("sum1", sum);
        map.put("iscollect","ok");
        return map;
    }
    //更新文章收藏量
    public String updatecollect(int user_id,int text_id,int view_sum,int collect_name_id){
        System.out.println(user_id);
        userDataMapper.updateUser_Collect_sum(view_sum,text_id);
        if(userDataMapper.selectUseer_collect(user_id,text_id)==null){
            userDataMapper.addUser_collect(user_id,text_id,collect_name_id);
            Date date = new Date();
            Date date1 = new Date(date.getYear(), date.getMonth(), date.getDate());
            if (userDataMapper.getUserCollect(date1, text_id) == null) {
                userDataMapper.addcollect(date1, text_id);
            } else {
                User_collect_sum a = userDataMapper.getUserCollect(date1, text_id);
                userDataMapper.updateUser_Collect_sum(a.getSum() + 1, a.getId());
            }
            return "ok";
        }else {
            return "no";
        }

    }
    //获取用户7天所有的点赞和观看数量
    public Map<String,Object> getAllData(int user_id){
        int sum=7;
        List<Text> textList = userDataMapper.getUserIdTexsId(user_id);
        Map<String,Object> map = new HashMap<String,Object>();
        int[] viewlist = new int[sum];
        int[] dianlist = new int[sum];
        int[] collectlist = new int[sum];
        List<String> timelist = new ArrayList<String>();
        Date date = new Date();
        int year=date.getYear();
        int month=date.getMonth();
        int day=date.getDate();
        for(int j=sum-1;j>=0;j--) {
            Date date2 = new Date(year, month, day - j);
            if (userDataMapper.getUserView(date2, 17) != null) {
                User_view_sum userViewSum = userDataMapper.getUserView(date2,  17);
                Date birth = userViewSum.getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(birth);
                timelist.add(birth1);
            } else {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(date2);
                timelist.add(birth1);
            }
        }
        if(textList!=null){
            for(Text item:textList){
                for(int i=sum-1;i>=0;i--){
                    Date date1 = new Date(year, month, day - i);
                    if(userDataMapper.getUserDian(date1,item.getId())!=null) {
                        User_dianzan_sum userdianSum = userDataMapper.getUserDian(date1, item.getId());
                        dianlist[sum-1-i]=dianlist[sum-1-i]+userdianSum.getSum();
                    }
                    else{
                        dianlist[sum-1-i]=dianlist[sum-1-i]+0;
                    }
                }
                for(int i=sum-1;i>=0;i--) {
                    Date date1 = new Date(year, month, day - i);
                    if (userDataMapper.getUserView(date1, item.getId()) != null) {
                        User_view_sum userViewSum = userDataMapper.getUserView(date1, item.getId());
                        viewlist[sum-1-i] = viewlist[sum-1-i] + userViewSum.getSum();
                    } else {
                        viewlist[sum-1-i] = viewlist[sum-1-i] + 0;
                    }
                }

                for(int i=sum-1;i>=0;i--) {
                    Date date1 = new Date(year, month, day - i);
                    if (userDataMapper.getUserCollect(date1, item.getId()) != null) {
                        User_collect_sum userViewSum = userDataMapper.getUserCollect(date1, item.getId());
                        collectlist[sum-1-i] =collectlist[sum-1-i] + userViewSum.getSum();
                    } else {
                        collectlist[sum-1-i] = collectlist[sum-1-i] + 0;
                    }
                }

            }
            map.put("view",viewlist);
            map.put("dian",dianlist);
            map.put("time",timelist);
            map.put("collect",collectlist);
            return map;
        }
        else {
            map.put("view", viewlist);
            map.put("dian", dianlist);
            map.put("time", timelist);
            map.put("collect",collectlist);
            return map;
        }
    }
    //获得用户粉丝数量及来自地区
    public Map<String,Object> getUserFensiLocation(int id){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("location",userDataMapper.getUserFensiLocation(id));
        map.put("sum",userDataMapper.getUserFensiSum(id).size());
        return map;
    }
    //获取七天的评论量
    public Map<String, Object> getCommentsum(int id){
        Map<String,Object> map = new HashMap<String,Object>();
        List sum = new ArrayList();
        Date date = new Date();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();
        for (int i = 6; i >= 0; i--) {
            Date date1 = new Date(year, month, day - i);
            if (userDataMapper.getCommentsum(date1,id).size() != 0) {
//                User_collect_sum userViewSum = userDataMapper.getUserCollect(date1,id);
                sum.add(userDataMapper.getCommentsum(date1,id).size());
//                Date birth = userViewSum.getTime();
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String birth1 = dateFormat.format(birth);
//                time.add(birth1);
            } else {
                sum.add(0);
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String birth1 = dateFormat.format(date1);
//                time.add(birth1);
            }
        }
        map.put("sum1", sum);
        map.put("iscollect","ok");
        return map;
    }
    //获得文章评论数量
    public int getTextCommentSum(int id){
        int sum=userDataMapper.getTextCommentsum(id).size();
        return sum;
    }
}
