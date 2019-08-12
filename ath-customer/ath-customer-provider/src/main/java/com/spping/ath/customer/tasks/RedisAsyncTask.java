package com.spping.ath.customer.tasks;

import com.spping.ath.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Author: 石保平
 * @Description: redis 异步任务
 * @Date: 2019/3/3 11:29
 * @Version: 1.0
 */
@Component
public class RedisAsyncTask {
    @Autowired
    private RedisUtil redisUtils;
    @Async
    public Future<String> saveToRedis(String key,String value,long timeout)  {
        redisUtils.set(key,value);
        redisUtils.expire(key,timeout);
        return new AsyncResult<String>("SUCCESS");
    }
    @Async
    public Future<String> deleteFromRedis(String key)  {
        redisUtils.del(key);
        return new AsyncResult<String>("SUCCESS");
    }
}
