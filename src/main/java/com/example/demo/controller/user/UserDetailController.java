package com.example.demo.controller.user;

import com.example.demo.enity.User;
import com.example.demo.enity.User_loginlocation;
import com.example.demo.enity.User_search_history;
import com.example.demo.service.user.UserDetailService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@RestController
public class UserDetailController {
    @Resource
    private UserDetailService userDetailService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private JavaMailSender sender;
    @PostMapping("/login")
    public User login(@RequestBody User user)throws Exception{
        return userDetailService.login(user);
    }
    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userDetailService.getAllUser();
    }
    @GetMapping("/updateUserDisable")
    public String updateUserDisable(int id,boolean disable){
        return userDetailService.updateUserDisable(id,disable);
    }
    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userDetailService.register(user);
    }
    @GetMapping("/getLoginLocation")
    public List<User_loginlocation> getLoginLocation(int id){
        return userDetailService.getLoginLocation(id);
    }
    @PostMapping("/updatedetail")
    public String updatedetail(@RequestBody User user){
        return userDetailService.updatedetail(user);
    }
    @PostMapping("/updateUserPic")
    public String updateSongPic(@RequestParam("file") MultipartFile myfile, @RequestParam("id") int id)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fileName = myfile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        myfile.transferTo(filePath);
        User user = new User();
        user.setPic(giamg);
        user.setId(id);
        userDetailService.updatedetail(user);
        return giamg;
    }
    @GetMapping("/getUserDetail")
    public User getUserDetail(int id){
        return userDetailService.getUserDetail(id);
    }
    //用于qq邮箱发送验证码
    @PostMapping("/send1")
    public void send1(@RequestBody User user)throws Exception{
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
        //随机数字6位
        Random random =new Random();
        String randNum = "";
        for (int i = 0; i<6; i++) {
            randNum += random.nextInt(10);
        }
        String email = user.getOldemail();
        redisTemplate.opsForValue().set(user.getOldemail(),randNum,120, TimeUnit.SECONDS);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("[南枫博客平台]邮箱验证");//发送邮件的主题
        message.setText("您好，您修改邮箱所需要的验证码为"+randNum+"您会收到这封自动产生的邮件是因为有人在"+s+"试图修改您的绑定邮箱，且提供了正确的手机号和密码");
        message.setTo(email);
        message.setFrom("2694134538@qq.com");
        sender.send(message);
    }
    @PostMapping("/updatephone2")
    public String updatephone2(@RequestBody User user){

        if(redisTemplate.opsForValue().get(user.getOldemail())==null){
            return "no";
        }
        else{
            if(user.getYanzheng().equals(redisTemplate.opsForValue().get(user.getOldemail()))){
                userDetailService.updatedetail(user);
                return "ok";
            }
            return "no1";
        }
    }
    @PostMapping("/send")
    public void send(@RequestBody User user){
        int appid = 1400863437;
        //这里输入步骤1.5的appkey
        String appkey = "b825a8a83541882436947bb93661631f";
        //{"这里输入手机号","第二个手机号"}; //手机号可以添很多。。
        String[] phoneNumbers = {user.getOldphone()};
        //这里添你申请的模板ID，注意是模板ID不是签名ID;
        int templateId = 1961247;
        //这里添你申请的签名，注意不是ID，是签名，中文。"
        String smsSign = "nanfengTech网";
        //随机数字6位
        Random random =new Random();
        String randNum = "";
        for (int i = 0; i<6; i++) {
            randNum += random.nextInt(10);
        }
        System.out.println(user.getOldphone());
        redisTemplate.opsForValue().set(user.getOldphone(),randNum,120, TimeUnit.SECONDS);
        //存验证码在服务器---可应用与servlet中
        // HttpSession session = req.getSession();
        // session.setAttribute("trueyzm", randNum);
        //以秒为单位，即在没有活动30分钟后，session将失效
        //session.setMaxInactiveInterval(30*60);
        // System.out.println("yzm-&gt;:" + result);
        try {
            //第一个参数传递{1}位置想要的内容，第二个传递{2}的内容，以此类推。
            String[] params = {randNum,"2"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/updatephone")
    public String updatephone(@RequestBody User user){

        if(redisTemplate.opsForValue().get(user.getOldphone())==null){
            return "no";
        }
        else{
            if(user.getYanzheng().equals(redisTemplate.opsForValue().get(user.getOldphone()))){
                userDetailService.updatedetail(user);
                return "ok";
            }
            return "no1";
        }
    }
    @GetMapping("/searchUsername")
    public List<User> searchUsername(String username){
        return userDetailService.searchUsername(username);
    }
    @GetMapping("/getUser_search_history")
    List<User_search_history> getUser_search_history(int id){
        return userDetailService.getUser_search_history(id);
    }
    @GetMapping("/addUser_search_history")
    public void addUser_search_history(int id,String content){
        User_search_history user_search_history = new User_search_history();
        user_search_history.setUser_id(id);
        user_search_history.setContent(content);
        Date date = new Date();
        user_search_history.setTime(date);
        userDetailService.addUser_search_history(user_search_history);
    }

}
