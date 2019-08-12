package com.spping.ath.sms.asyncTask;
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
    private RedisUtil redisUtil;
    @Async
    public Future<String> saveToRedis(String key,String value,long timeout)  {
        redisUtil.set(key,value,timeout);
        return new AsyncResult<String>(key);
    }
    @Async
    public Future<String> deleteFromRedis(String key)  {
        redisUtil.del(key);
        return new AsyncResult<String>(key);
    }
}
