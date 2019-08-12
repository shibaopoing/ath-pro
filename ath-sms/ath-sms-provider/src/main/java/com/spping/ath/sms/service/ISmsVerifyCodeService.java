package com.spping.ath.sms.service;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.sms.api.dto.VerifiCateReqDto;

/**
 * @Author: 石保平
 * @Description: 短信验证码Service层接口
 * @Date: 2019/2/24 15:18
 * @Version: 1.0
 */
public interface ISmsVerifyCodeService {

    public BaseRsp sendVerifyCode(VerifiCateReqDto Req);

    public BaseRsp queryVerifyCode(VerifiCateReqDto Req);

    public BaseRsp dropVerifyCode(VerifiCateReqDto Req);

    public BaseRsp checkVerifyCode(VerifiCateReqDto Req);
}
