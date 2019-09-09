package com.spping.ath.oprate.service;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.oprate.api.dto.UserDto;

import java.util.Map;

public interface IUserInfoService {
    public BaseRsp getUserInfo(Map userDto);
    public BaseRsp getUserInfo(UserDto userDto);
    public BaseRsp login(UserDto userDto);
    public BaseRsp addNewUserInfo(UserDto userDto);
    public BaseRsp setNickName(UserDto userDto);
    public BaseRsp setPhoneNum(UserDto userDto);
    public BaseRsp checkPassWord(UserDto userDto);
    public BaseRsp setPassWord(UserDto userDto);
    public BaseRsp setHeadImage(UserDto userDto);
}
