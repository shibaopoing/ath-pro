package com.spping.ath.oprate.provider;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.provider.BaseProvider;
import com.spping.ath.oprate.api.UserCommandFeignApi;
import com.spping.ath.oprate.api.dto.UserDto;
import com.spping.ath.oprate.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserCommandProvider extends BaseProvider implements UserCommandFeignApi {
    @Autowired
    IUserInfoService iUserInfoService;
    public BaseRsp userOpLogin(@RequestBody UserDto userDto) {
        return iUserInfoService.login(userDto);
    }

    public BaseRsp userOpRegister(@RequestBody UserDto userDto){
        return iUserInfoService.addNewUserInfo(userDto);
    }

    public BaseRsp setOpHeadImage(@RequestBody UserDto userDto){
        return iUserInfoService.setHeadImage(userDto);
    }

    public BaseRsp setOpNickname(@RequestBody UserDto userDto){
        return iUserInfoService.setNickName(userDto);
    }

    public BaseRsp setOpPhoneNum(@RequestBody UserDto userDto){
        return iUserInfoService.setPhoneNum(userDto);
    }

    public BaseRsp checkOpPassWord(@RequestBody UserDto userDto){
        return iUserInfoService.checkPassWord(userDto);
    }

    public BaseRsp setOpPassWord(@RequestBody UserDto userDto){
        return iUserInfoService.setPassWord(userDto);
    }
}
