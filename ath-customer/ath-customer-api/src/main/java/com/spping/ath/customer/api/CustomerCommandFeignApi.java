package com.spping.ath.customer.api;

import com.spping.ath.customer.api.dto.UsersDto;
import com.spping.ath.customer.api.hystrix.CustomerCommandFeignHystrix;
import com.spping.ath.common.dto.rsp.BaseRsp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 石保平
 * @Description: 用户操作服务
 * @Date: 2019/2/24 14:00
 * @Version: 1.0
 */
@FeignClient(value = "customer-command",fallback = CustomerCommandFeignHystrix.class)
@Api(description = "用户操作服务")
public interface CustomerCommandFeignApi {

    @RequestMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public BaseRsp userLogin(@RequestBody UsersDto userDto);
    @RequestMapping(value = "/userRegister", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public BaseRsp userRegister(@RequestBody UsersDto userDto);
    @RequestMapping(value = "/setFaceImage", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户设置头像")
    public BaseRsp setHeadImage(@RequestBody UsersDto userDto);
    @RequestMapping(value = "/setNickname", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户设置昵称")
    public BaseRsp setNickname(@RequestBody UsersDto userDto);
    @RequestMapping(value = "/setPhoneNum", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户设置手机号码")
    public BaseRsp setPhoneNum(@RequestBody UsersDto userDto);
    @RequestMapping(value = "/checkPassWord", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户密码复核")
    public BaseRsp checkPassWord(@RequestBody UsersDto userDto);
    @RequestMapping(value = "/setPassWord", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户设置密码")
    public BaseRsp setPassWord(@RequestBody UsersDto userDto);
}
