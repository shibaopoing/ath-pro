package com.spping.ath.common.utils;

/**
 * @Author: 石保平
 * @Description: ${description}
 * @Date: 2019/2/24 15:41
 * @Version: 1.0
 */
public class StringUtil {
    /**
     * @Author 石保平
     * @Version  1.0
     * @Description 判断字符串是否为null 或 空值
     * @param str
     * @Return  boolean
     * @Exception 
     * @Date 2019/2/24 15:42
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
    /**
     * @Author 石保平
     * @Version  1.0
     * @Description
     * @param  str
     * @Return str
     * @Exception
     * @Date 2019/2/24 15:45
     */
    public static String strTrim(String str) {
        if(str == null){
            return "";
        }else{
            return str.trim();
        }
    }
}
