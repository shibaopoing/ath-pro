package com.spping.ath.dao.mapper;

import com.spping.ath.dao.model.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}