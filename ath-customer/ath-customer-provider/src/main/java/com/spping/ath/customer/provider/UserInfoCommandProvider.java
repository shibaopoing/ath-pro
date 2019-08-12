package com.spping.ath.customer.provider;

import com.spping.ath.customer.api.CustomerCommandFeignApi;
import com.spping.ath.customer.api.dto.UsersDto;
import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.provider.BaseProvider;
import com.spping.ath.customer.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserInfoCommandProvider extends BaseProvider implements CustomerCommandFeignApi {
    @Autowired
    UserInfoServiceImpl userInfoService;
    @RequestMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp userLogin(@RequestBody UsersDto userDto) {
        return userInfoService.login(userDto);
    }
    @RequestMapping(value = "/getUserInfo", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp getUserInfo(@RequestBody UsersDto userInfo) {
        return userInfoService.getuserInfo(userInfo);
    }
    @RequestMapping(value = "/userRegister", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp userRegister(@RequestBody UsersDto userDto){
        return userInfoService.addNewUserInfo(userDto);
    }
    @RequestMapping(value = "/setFaceImage", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp setHeadImage(@RequestBody UsersDto userDto){
        return userInfoService.setHeadImage(userDto);
    }
    @RequestMapping(value = "/setNickname", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp setNickname(@RequestBody UsersDto userDto){
        return userInfoService.setNickName(userDto);
    }
    @RequestMapping(value = "/setPhoneNum", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp setPhoneNum(@RequestBody UsersDto userDto){
        return userInfoService.setPhoneNum(userDto);
    }
    @RequestMapping(value = "/checkPassWord", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp checkPassWord(@RequestBody UsersDto userDto){
        return userInfoService.checkPassWord(userDto);
    }
    @RequestMapping(value = "/setPassWord", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp setPassWord(@RequestBody UsersDto userDto){
        return userInfoService.setPassWord(userDto);
    }
}
