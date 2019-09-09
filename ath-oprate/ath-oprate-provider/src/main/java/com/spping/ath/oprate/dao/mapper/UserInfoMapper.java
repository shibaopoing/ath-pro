package com.spping.ath.oprate.dao.mapper;

import com.spping.ath.oprate.dao.model.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);
    UserInfo selectByUserCode(@Param("userCode") String userCode);
    UserInfo selectByPhone(@Param("userPhone") String userPhone);
    UserInfo loginByUserCode(@Param("userCode") String userCode, @Param("userPwd") String userPwd);
    UserInfo loginByPhone(@Param("userPhone") String userPhone, @Param("userPwd") String userPwd);
    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}