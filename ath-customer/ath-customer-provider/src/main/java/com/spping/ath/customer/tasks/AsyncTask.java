package com.spping.ath.customer.tasks;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 异步任务类
 */
@Component
@Async  //异步注解，类中所有方法都是异步方法
public class AsyncTask {
    public Future<String> asyncTaskTest1() throws InterruptedException {
        Long begin = System.currentTimeMillis();
        Thread.sleep(2000);
        Long end = System.currentTimeMillis();
        System.out.println("异步任务开始1耗时：" + (end - begin));
        return new AsyncResult<String>("异步任务1");
    }

    public Future<String> asyncTaskTest2() throws InterruptedException {
        Long begin = System.currentTimeMillis();
        Thread.sleep(2000);
        Long end = System.currentTimeMillis();
        System.out.println("异步任务开始1耗时：" + (end - begin));
        return new AsyncResult<String>("异步任务2");
    }

    public Future<String> asyncTaskTest3() throws InterruptedException {
        Long begin = System.currentTimeMillis();
        Thread.sleep(2000);
        Long end = System.currentTimeMillis();
        System.out.println("异步任务开始1耗时：" + (end - begin));
        return new AsyncResult<String>("异步任务3");
    }

    public Future<String> asyncTaskTest4() throws InterruptedException {
        Long begin = System.currentTimeMillis();
        Thread.sleep(2000);
        Long end = System.currentTimeMillis();
        System.out.println("异步任务开始1耗时：" + (end - begin));
        return new AsyncResult<String>("异步任务4");
    }
}
