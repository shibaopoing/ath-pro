package com.spping.ath.common.utils;
/**
 * @Author huabao.fang
 * @Date 12:20 2019-03-21
 * @Description 正则校验工具类
 **/
public class RegexUtils {

    public static boolean match(String src,String regex){

        if(src == null){
            return false;
        }
        return src.matches(regex);

    }

    public static void main(String[] args) {

      //  System.out.println(match("1qq36", ValidateConstants.REG_SMS_CODE));
    }

}
