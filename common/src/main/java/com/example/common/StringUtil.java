package com.example.common;

public class StringUtil {
    /**
     * 判断昵称格式是否符合
     * @param value  昵称
     * @return
     */
    public boolean isNickname(String value){
        if(2<value.length() && value.length()<=10){
            return true;
        }else {
            return false;
        }
    }
}
