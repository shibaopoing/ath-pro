package com.spping.ath.sms.provider;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.provider.BaseProvider;
import com.spping.ath.sms.api.SmsCommandFeignApi;
import com.spping.ath.sms.api.dto.VerifiCateReqDto;
import com.spping.ath.sms.service.ISmsVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 石保平
 * @Description: 发送短信接口
 * @Date: 2019/2/24 14:59
 * @Version: 1.0
 */
@RestController
public class SmsCommandProvider extends BaseProvider implements SmsCommandFeignApi {
    @Autowired
    ISmsVerifyCodeService SmsVerifyCodeServiceImpl;
    @Override
    public BaseRsp sendSmsCode(@RequestBody VerifiCateReqDto req) {
        return SmsVerifyCodeServiceImpl.sendVerifyCode(req);
    }

    @Override
    public BaseRsp smsCodeFinished(@RequestBody VerifiCateReqDto req) {
        return SmsVerifyCodeServiceImpl.dropVerifyCode(req);
    }
}
