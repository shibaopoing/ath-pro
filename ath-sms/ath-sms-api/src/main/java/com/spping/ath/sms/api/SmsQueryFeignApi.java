package com.spping.ath.sms.api;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.sms.api.dto.VerifiCateReqDto;
import com.spping.ath.sms.api.hystrix.SmsQueryFeignHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 石保平
 * @Description: SMS后勤服务
 * @Date: 2019/2/24 14:02
 * @Version: 1.0
 */
@FeignClient(value = "sms-query",fallback = SmsQueryFeignHystrix.class)
@Api(description = "短信验证码查询服务")
public interface SmsQueryFeignApi {
    @RequestMapping(value = "/querySmsCode",method = RequestMethod.POST)
    @ApiOperation(value = "查询短信验证码")
    public BaseRsp querySmsCode(@RequestBody VerifiCateReqDto req);
    @RequestMapping(value = "/checkSmsCode",method = RequestMethod.POST)
    @ApiOperation(value = "校验短信验证码")
    public BaseRsp checkSmsCode(@RequestBody VerifiCateReqDto req);
}
