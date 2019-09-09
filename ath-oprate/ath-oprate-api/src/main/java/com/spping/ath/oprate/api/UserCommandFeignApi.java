package com.spping.ath.oprate.api;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.oprate.api.dto.UserDto;
import com.spping.ath.oprate.api.hystix.UserCommandFeignHystrix;
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
@FeignClient(value = "oprate-user-command",fallback = UserCommandFeignHystrix.class)
@Api(description = "用户操作服务")
public interface UserCommandFeignApi {

    @RequestMapping(value = "/opLogin", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public BaseRsp userOpLogin(@RequestBody UserDto userDto);
    @RequestMapping(value = "/opRegister", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public BaseRsp userOpRegister(@RequestBody UserDto userDto);
    @RequestMapping(value = "/setOpFaceImage", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户设置头像")
    public BaseRsp setOpHeadImage(@RequestBody UserDto userDto);
    @RequestMapping(value = "/seOptNickname", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户设置昵称")
    public BaseRsp setOpNickname(@RequestBody UserDto userDto);
    @RequestMapping(value = "/setOpPhoneNum", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户设置手机号码")
    public BaseRsp setOpPhoneNum(@RequestBody UserDto userDto);
    @RequestMapping(value = "/checkOpPassWord", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户密码复核")
    public BaseRsp checkOpPassWord(@RequestBody UserDto userDto);
    @RequestMapping(value = "/setOpPassWord", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户设置密码")
    public BaseRsp setOpPassWord(@RequestBody UserDto userDto);
}
