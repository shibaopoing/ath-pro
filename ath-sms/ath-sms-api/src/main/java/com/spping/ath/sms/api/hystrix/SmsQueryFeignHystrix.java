package com.spping.ath.sms.api.hystrix;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.sms.api.SmsQueryFeignApi;
import com.spping.ath.sms.api.dto.VerifiCateReqDto;
import org.springframework.stereotype.Component;

/**
 * @Author: 石保平
 * @Description: SMS 查询服务熔断处理类
 * @Date: 2019/2/24 14:05
 * @Version: 1.0
 */
@Component
public class SmsQueryFeignHystrix extends BaseHystrix implements SmsQueryFeignApi {
    /**
     * @Author 石保平
     * @Version  1.0
     * @Description
     * @param req
     * @Return com.micro.tiger.common.dto.BaseRsp
     * @Exception
     * @Date 2019/2/24 15:06
     */
    @Override
    public BaseRsp querySmsCode(VerifiCateReqDto req) {
        return defualt();
    }
    /**
     * @Author 石保平
     * @Version  1.0
     * @Description
     * @param req
     * @Return com.micro.tiger.common.dto.BaseRsp
     * @Exception
     * @Date 2019/3/10 11:53
     */
    @Override
    public BaseRsp checkSmsCode(VerifiCateReqDto req) {

        return defualt();
    }
}
