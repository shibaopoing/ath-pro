package com.spping.ath.oprate.api.hystix;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.oprate.api.UserCommandFeignApi;
import com.spping.ath.oprate.api.dto.UserDto;

public class UserCommandFeignHystrix extends BaseHystrix implements UserCommandFeignApi {
    @Override
    public BaseRsp userOpLogin(UserDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp userOpRegister(UserDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp setOpHeadImage(UserDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp setOpNickname(UserDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp setOpPhoneNum(UserDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp checkOpPassWord(UserDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp setOpPassWord(UserDto userDto) {
        return defualt();
    }
}
