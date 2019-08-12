package com.spping.ath.common.constants;

import com.spping.ath.common.utils.EnumUtils;

/**
 * @Author huabao.fang
 * @Date 14:51 2019-02-21
 * @Description 项目编码枚举
 **/
public enum ProjectCodeEnum implements TE{


    KANSHU("kanshu","侃书"),
    BLOG("blog","博客"),
    BOSS("boss","运营台"),
    OFFICAL_WEB("officalWeb","官网"),
    ;





    ProjectCodeEnum(String k, String v) {
        this.k = k;
        this.v = v;
    }

    private String k;

    private String v;

    public String getK() {
        return k;
    }


    public String getV() {
        return v;
    }






    public static void main(String[] args) {
        //System.out.println(getKRegs());//kanshu|blog|boss|officalWeb
        System.out.println(EnumUtils.getEnumByK("blog", ProjectCodeEnum.class));
        System.out.println(EnumUtils.getKRegs(ProjectCodeEnum.class));
    }
}
