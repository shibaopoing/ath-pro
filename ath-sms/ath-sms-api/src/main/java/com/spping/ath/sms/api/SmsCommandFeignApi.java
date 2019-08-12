package com.spping.ath.sms.api;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.sms.api.dto.VerifiCateReqDto;
import com.spping.ath.sms.api.hystrix.SmsCommandFeignHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 石保平
 * @Description: 提供SMS命令服务
 * @Date: 2019/2/24 14:00
 * @Version: 1.0
 */
@FeignClient(value = "sms-command",fallback = SmsCommandFeignHystrix.class)
@Api(description = "短信验证码操作服务")
public interface SmsCommandFeignApi {
    @RequestMapping(value = "/sendSmsCode",method = RequestMethod.POST)
    @ApiOperation(value = "请求发送验证码")
    public BaseRsp sendSmsCode(@RequestBody VerifiCateReqDto req);
    @RequestMapping(value = "/smsCodeFinished",method = RequestMethod.POST)
    @ApiOperation(value = "验证码使用完毕")
    public BaseRsp smsCodeFinished(@RequestBody VerifiCateReqDto req);
}
