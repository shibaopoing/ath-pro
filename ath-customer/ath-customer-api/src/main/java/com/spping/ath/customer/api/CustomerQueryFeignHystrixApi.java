package com.spping.ath.customer.api;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.customer.api.dto.UsersDto;
import com.spping.ath.customer.api.hystrix.CustomerQueryFeignHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 石保平
 * @Description: 用户信息查询
 * @Date: 2019/2/24 14:02
 * @Version: 1.0
 */
@FeignClient(value = "customer-query",fallback = CustomerQueryFeignHystrix.class)
@Api(description = "用户信息查询服务")
public interface CustomerQueryFeignHystrixApi {
    @RequestMapping(value = "/getUserInfo", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "获取用户信息")
    public BaseRsp getUserInfo(@RequestBody UsersDto userInfo);
}
