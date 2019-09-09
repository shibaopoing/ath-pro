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
public class CustomerCommandProvider extends BaseProvider implements CustomerCommandFeignApi {
    @Autowired
    UserInfoServiceImpl userInfoService;
    public BaseRsp userLogin(@RequestBody UsersDto userDto) {
        return userInfoService.login(userDto);
    }

    public BaseRsp getUserInfo(@RequestBody UsersDto userInfo) {
        return userInfoService.getuserInfo(userInfo);
    }

    public BaseRsp userRegister(@RequestBody UsersDto userDto){
        return userInfoService.addNewUserInfo(userDto);
    }

    public BaseRsp setHeadImage(@RequestBody UsersDto userDto){
        return userInfoService.setHeadImage(userDto);
    }

    public BaseRsp setNickname(@RequestBody UsersDto userDto){
        return userInfoService.setNickName(userDto);
    }

    public BaseRsp setPhoneNum(@RequestBody UsersDto userDto){
        return userInfoService.setPhoneNum(userDto);
    }

    public BaseRsp checkPassWord(@RequestBody UsersDto userDto){
        return userInfoService.checkPassWord(userDto);
    }

    public BaseRsp setPassWord(@RequestBody UsersDto userDto){
        return userInfoService.setPassWord(userDto);
    }
}
