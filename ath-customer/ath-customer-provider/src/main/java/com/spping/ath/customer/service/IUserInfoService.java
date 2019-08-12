package com.spping.ath.customer.service;

import com.spping.ath.customer.api.dto.UsersDto;
import com.spping.ath.common.dto.rsp.BaseRsp;

public interface IUserInfoService {
    public BaseRsp getuserInfo(UsersDto userDto);
    public BaseRsp login(UsersDto userDto);
    public BaseRsp addNewUserInfo(UsersDto userDto);
    public BaseRsp setNickName(UsersDto userDto);
    public BaseRsp setPhoneNum(UsersDto userDto);
    public BaseRsp checkPassWord(UsersDto userDto);
    public BaseRsp setPassWord(UsersDto userDto);
    public BaseRsp setHeadImage(UsersDto userDto);
}
