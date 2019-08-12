package com.spping.ath.sms.dao.mapper;

import com.spping.ath.sms.dao.model.SmsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface SmsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsInfo record);

    SmsInfo selectLastByParams(@Param("phoneNumber") String phoneNumber, @Param("useType") String useType);
    int insertSelective(SmsInfo record);

    SmsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsInfo record);
    int updateByParams(@Param("phoneNumber") String phoneNumber, @Param("smsCode") String smsCode);

    int updateByPrimaryKey(SmsInfo record);
}