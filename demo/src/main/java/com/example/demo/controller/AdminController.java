package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.AdminService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @PostMapping("/login")
    public String login(@RequestBody UserEntity user, HttpSession httpSession){
        return adminService.login(user,httpSession);
    }

    //用于安卓发送短信
    @PostMapping("/send")
    public String send(String str){
        int appid = 1400863437;
        //这里输入步骤1.5的appkey
        String appkey = "b825a8a83541882436947bb93661631f";
        //{"这里输入手机号","第二个手机号"}; //手机号可以添很多。。
        String[] phoneNumbers = {str};
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
        return randNum;
    }
}
