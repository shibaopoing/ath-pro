package com.spping.ath.common.utils;

import com.spping.ath.common.constants.RspCodeEnum;
import com.spping.ath.common.exceptions.AthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * Created by fanghuabao on 2018/4/26 0026.
 */
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext ac;
    private static Logger logger = LoggerFactory.getLogger(SpringUtils.class);

    /**
     * 获取application中的值
     * @param key
     * @return
     */
    public static String getVal(String key){
        if(StringUtils.isEmpty(ac.getEnvironment().getProperty(key))){
            throw new AthException(RspCodeEnum.ERROR.CONFIG_VAL_NOT_EXIST);
        }
        return ac.getEnvironment().getProperty(key);
    }

    public static <T> T getVal(String key,Class<T> t){
        if(StringUtils.isEmpty(ac.getEnvironment().getProperty(key,t))){
            throw new AthException(RspCodeEnum.ERROR.CONFIG_VAL_NOT_EXIST);
        }
        return ac.getEnvironment().getProperty(key,t);
    }

    public SpringUtils() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public static <T> T getBean(String beanId, Class<T> type) {
        return ac.getBean(beanId, type);
    }

    public static <T> T getBean(Class<T> type) {
        return ac.getBean(type);
    }

    public static <T> T getBean(String beanId) {
        return (T)ac.getBean(beanId);
    }


}

