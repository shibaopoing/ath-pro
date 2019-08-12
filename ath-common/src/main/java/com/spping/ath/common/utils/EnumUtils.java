package com.spping.ath.common.utils;


import com.spping.ath.common.constants.TE;

/**
 * @Author huabao.fang
 * @Date 17:43 2019-02-24
 * @Description enum工具类
 **/
public class EnumUtils {

    public static <F extends Enum & TE> String getKRegs(Class<F> f){
        String regex = "";
        F[] arr = f.getEnumConstants();
        for(F a:arr){
            regex = regex +"|"+a.getK();
        }
        if(regex.startsWith("|")){
            regex = regex.substring(1);
        }
        return regex;
    }

    public static <F extends Enum & TE> F getEnumByK(String k, Class<F> f){

        for(F a: f.getEnumConstants()){

            if(a.getK().equals(k)){

                return a;

            }
        }
        return null;
    }

}
