package com.spping.ath.sms.interceptors;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Author: 石保平
 * @Description: redis异步任务拦截器
 * @Date: 2019/3/3 12:02
 * @Version: 1.0
 */
@Component
@Aspect
public class RedisAsyncInterceptor {
    private static final Logger logger =  LogManager.getLogger(RedisAsyncInterceptor.class);
    @Pointcut("execution(public  * com.spping.ath.sms.asyncTask.RedisAsyncTask.*(..))")
    public void aspectjMethod() {
    }
    @Around(value = "aspectjMethod()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        printReqParams(pjp);
        Object result = pjp.proceed();
        if(Future.class.isInstance(result)){
            Future task= Future.class.cast(result);
            if(task.isDone()){
                logger.info("redis缓存操作成功, 方法 {}", pjp.getSignature().toShortString());
            }else{
                logger.info("redis缓存操作失败, 方法 {}", pjp.getSignature().toShortString());
            }
        }
        return result;
    }
    @AfterThrowing(value = "aspectjMethod()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        MethodInvocationProceedingJoinPoint methodpoint = (MethodInvocationProceedingJoinPoint) joinPoint;
        logger.info("redis缓存操作异常, 方法 {}, {} ", methodpoint.getSignature().toShortString(), ex.getMessage());
    }

    private void printReqParams(ProceedingJoinPoint pjp){

        Object[] args = pjp.getArgs();

        StringBuilder sb = new StringBuilder();

        sb.append("请求redis缓存方法  {}, 入参【");

        String[]  jsonArgs = new String[args.length+1];

        String methodName=pjp.getSignature().toString();

        jsonArgs[0]=getShortMethodName(pjp);

        for(int i=0;i<args.length;i++){

            sb.append(args[i].getClass().getSimpleName()+"={},");

            jsonArgs[i+1]= JSONObject.toJSONString(args[i]);

        }

        if(sb.toString().endsWith(",")){

            sb.delete(sb.length()-1, sb.length());

        }

        sb.append("】");

        logger.info(sb.toString(),jsonArgs);

    }

    /**
     *
     * getShortMethodName:获取简短方法名. <br/>
     *PayRsp com.zb.payment.gan.facade.service.TestService.test(PayReq)==>> TestService.test(PayReq)
     * @return
     */
    private String getShortMethodName(ProceedingJoinPoint pjp){

        String longMethodName=pjp.getSignature().toString();

        String[] names=longMethodName.split("\\.");

        return names[names.length-2]+"."+names[names.length-1];
    }
}
