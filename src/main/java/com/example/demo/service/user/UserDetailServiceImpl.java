package com.example.demo.service.user;

import com.example.demo.dao.user.UserDetailMapper;
import com.example.demo.enity.User;
import com.example.demo.enity.User_loginlocation;
import com.example.demo.enity.User_search_history;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import java.io.BufferedReader;import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDetailServiceImpl implements UserDetailService{
    @Resource
    private UserDetailMapper userDetailMapper;

    //根据ip地址获取所在地区
    public String getAddressByIP1(String strIP) throws IOException {
        URL url = new URL("http://whois.pconline.com.cn/ipJson.jsp?ip=" + strIP + "&json=true");
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
        String line = null;
        StringBuilder result = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        String res = result.toString();
        Gson gson = new Gson();
        HashMap<String, Object> kv = gson.fromJson(res, HashMap.class);
        return (String) kv.get("pro")+(String) kv.get("city");
    }
    public User login(User user)throws Exception{
        //根据ip地址获取所在地区
//        String ip = "223.89.151.200";
//        String add = getAddressByIP1(ip);
//        System.out.println(add);
        //
        if(userDetailMapper.login(user)!=null){
            if(!userDetailMapper.login(user).isDisable()){
                User user2 = new User();
                user2.setId(0);
                return user2;
            }
            User user1=userDetailMapper.login(user);
            Date birth = user1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            user1.setCreate_time1(birth1);
            if(user1.getBirth()!=null) {
                Date bir = user1.getBirth();
                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                String birth3 = dateFormat1.format(bir);
                user1.setBirth1(birth3);
            }
            //获取登录地址
            URL url = new URL("https://myip.ipip.net");
            //用Scanner对象来接收
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder builder = new StringBuilder();
            //读取信息
            while (scanner.hasNext()) {
                String next = scanner.nextLine();
                builder.append(next);
            }
            //这是我们爬取到的信息
            String s = builder.toString();
            int j=s.lastIndexOf("：");
            String str=s.substring(j+1);//地理位置
            User_loginlocation user_loginlocation = new User_loginlocation();
            user_loginlocation.setUser_id(user1.getId());
            Date date2 = new Date();
            user_loginlocation.setTime(date2);
            user_loginlocation.setLocation(str);
            userDetailMapper.addLoginLocation(user_loginlocation);
            return user1;
        }
        else {
            return null;
        }
    }
    //用户注册
    public String register(User user){
        if(userDetailMapper.selectUserPhone(user)!=null){
            return "no";
        }
        userDetailMapper.addChatUser(user.getId());
        Random r = new Random();
        int a=r.nextInt(100000);
        String name="游客"+a;
        user.setUsername(name);
        Date date = new Date();
        user.setCreate_time(date);
        if(userDetailMapper.register(user)>0){
            User user1 = userDetailMapper.selectphoneUser(user.getPhone());
            userDetailMapper.addCollect_name(user1.getId());
            return "ok";
        }
        else{
            return "no";
        }
    }
    //获取用户登录的地理位置
    public List<User_loginlocation> getLoginLocation(int id){
        List<User_loginlocation> list = userDetailMapper.getLoginLocation(id);
        for(User_loginlocation item:list) {
            Date birth = item.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1 = dateFormat.format(birth);
            item.setTime1(birth1);
        }
        return list;
    }
    //获取全部用户
    public List<User> getAllUser(){
        List<User> list=userDetailMapper.getAllUser();
        for(User item:list) {
            if(item.getBirth()!=null) {
                Date birth = item.getBirth();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birth1 = dateFormat.format(birth);
                item.setBirth1(birth1);
            }
        }
        return list;
    }
    @CacheEvict(value = "user",allEntries = true)
    //修改用户个人信息
    public String updatedetail(User user){
        if(user.getId()==0){
            if(userDetailMapper.selectphoneUser(user.getOldphone())!=null) {
                User user1 = userDetailMapper.selectphoneUser(user.getOldphone());
                user.setId(user1.getId());
            }
            if(userDetailMapper.selectemailUser(user.getOldemail())!=null){
                User user1 = userDetailMapper.selectemailUser(user.getOldemail());
                user.setId(user1.getId());
            }
        }
        if(userDetailMapper.updatedetail(user)>0){
            return "ok";
        }
        return "no";
    }
    //禁用账号
    public String updateUserDisable(int id,boolean disable){
        if(userDetailMapper.updateUserDisable(disable,id)>0){
            return "ok";
        }
        return "no";
    }
    //根据用户id查询用户个人信息
//    @Cacheable(value = "user" , key = "'getUserDetail'")
    public User getUserDetail(int id){
        User user=userDetailMapper.getUserDetail(id);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
        if(user.getBirth()!=null) {
            Date birth = user.getBirth();
            String birth1 = dateFormat.format(birth);
            user.setBirth1(birth1);
        }
        Date date=user.getCreate_time();
        String date1=dateFormat.format(date);
        user.setCreate_time1(date1);
        return user;
    }
    //根据用户昵称模糊查询用户
    public List<User> searchUsername(String username){
        return userDetailMapper.searchUsername(username);
    }
    //获得用户搜索记录
    public List<User_search_history> getUser_search_history(int id){
        return userDetailMapper.getUser_search_history(id);
    }
    //添加用户搜索记录
    public void addUser_search_history(User_search_history user_search_history){
        if(userDetailMapper.searchaddUser_search_history(user_search_history)!=null){
            userDetailMapper.deleteUser_search_history(userDetailMapper.searchaddUser_search_history(user_search_history).getId());
            userDetailMapper.addUser_search_history(user_search_history);
        }
        else {
            userDetailMapper.addUser_search_history(user_search_history);
        }
    }
}
