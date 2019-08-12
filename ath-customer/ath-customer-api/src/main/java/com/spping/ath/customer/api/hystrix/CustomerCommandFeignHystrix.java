package com.spping.ath.customer.api.hystrix;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.customer.api.CustomerCommandFeignApi;
import com.spping.ath.customer.api.dto.UsersDto;

public class CustomerCommandFeignHystrix extends BaseHystrix implements CustomerCommandFeignApi {
    @Override
    public BaseRsp userLogin(UsersDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp userRegister(UsersDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp setHeadImage(UsersDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp setNickname(UsersDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp setPhoneNum(UsersDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp checkPassWord(UsersDto userDto) {
        return defualt();
    }

    @Override
    public BaseRsp setPassWord(UsersDto userDto) {
        return defualt();
    }
}
