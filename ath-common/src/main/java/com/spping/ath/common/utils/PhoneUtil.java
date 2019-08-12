package com.spping.ath.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 石保平
 * @Description: 手机号格式校验
 * @Date: 2019/2/24 15:55
 * @Version: 1.0
 */
public class PhoneUtil {
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPhone("17621825100"));
    }
}
