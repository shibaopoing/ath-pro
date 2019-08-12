package com.spping.ath.common.utils;

import java.util.Date;

/**
 * @Author: 石保平
 * @Description: 时间工具类
 * @Date: 2019/2/24 20:28
 * @Version: 1.0
 */
public class DateUtil {
    /**
     * @Author 石保平
     * @Version  1.0
     * @Description 计算 date1和date2 相差多少秒
     * @param date1，date2
     * @Return
     * @Exception
     * @Date 2019/2/28 19:15
     */
    private  static final  String yyyyMMddHHss = "yyyy-MM-dd HH:mm:ss";

    public static  int diffSeconds(Date date1,Date date2){
        long secondData = (date1.getTime()-date2.getTime())/1000;
        return secondData > 0 ? (int)secondData:(int) Math.abs(secondData);
    }
    /**
     * @Author 石保平
     * @Version  1.0
     * @Description
     * @param date1
     * @Return
     * @Exception 计算 date1 与当前时间 相差多少秒
     * @Date 2019/2/28 19:14
     */
    public static  int diffSecondsNow(Date date1){
        long secondData = (System.currentTimeMillis()-date1.getTime())/1000;
        return secondData > 0 ? (int)secondData:(int) Math.abs(secondData);
    }
}
