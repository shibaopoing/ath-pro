package com.spping.ath.sms.api.hystrix;


import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.sms.api.SmsCommandFeignApi;
import com.spping.ath.sms.api.dto.VerifiCateReqDto;
import org.springframework.stereotype.Component;

/**
 * @Author: 石保平
 * @Description:  SMS 命令服务熔断处理类
 * @Date: 2019/2/24 14:04
 * @Version: 1.0
 */
@Component
public class SmsCommandFeignHystrix extends BaseHystrix implements SmsCommandFeignApi {

    /**
     * @Author 石保平
     * @Version  1.0
     * @Description
     * @param req
     * @Return com.micro.tiger.common.dto.BaseRsp
     * @Exception
     * @Date 2019/2/24 14:35
     */
    @Override
    public BaseRsp sendSmsCode(VerifiCateReqDto req) {
        return defualt();
    }

    @Override
    public BaseRsp smsCodeFinished(VerifiCateReqDto req) {
        return defualt();
    }
}
