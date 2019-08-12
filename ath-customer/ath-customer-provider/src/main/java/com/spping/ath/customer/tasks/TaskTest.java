package com.spping.ath.customer.tasks;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务测试类
 */
//@Component
public class TaskTest {
    //两秒执行一次
    @Scheduled(fixedRate = 2000)
    public void sum() {
        System.out.println("定时任务测试");
    }

    //两秒执行一次
    @Scheduled(cron = "*/4 * * * * *")
    public void dec() {
        System.out.println("定时任务测试111");
    }
}
