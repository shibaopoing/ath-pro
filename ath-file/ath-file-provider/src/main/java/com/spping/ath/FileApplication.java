package com.spping.ath;

import com.spping.ath.common.utils.SpringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication  //启动自动扫描 加载配置类
@ServletComponentScan //filter 扫描
@EnableScheduling  //定时任务扫描
@EnableAsync //开启异步任务
@MapperScan("com.spping.ath.file.dao")   //扫描mapper路径
@EnableTransactionManagement    //事务加载 扫描路径
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableFeignClients
public class FileApplication {
    @Bean
    public SpringUtils getSpringUtils(){
        return new SpringUtils();
    }
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }

}
