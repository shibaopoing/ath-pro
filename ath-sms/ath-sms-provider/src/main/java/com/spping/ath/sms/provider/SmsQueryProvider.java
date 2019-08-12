package com.spping.ath.sms.provider;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.provider.BaseProvider;
import com.spping.ath.sms.api.SmsQueryFeignApi;
import com.spping.ath.sms.api.dto.VerifiCateReqDto;
import com.spping.ath.sms.service.ISmsVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 石保平
 * @Description: 验证短信接口
 * @Date: 2019/2/24 15:01
 * @Version: 1.0
 */
@RestController
public class SmsQueryProvider extends BaseProvider implements SmsQueryFeignApi {
    @Autowired
    ISmsVerifyCodeService SmsVerifyCodeServiceImpl;
    /**
     * @Author 石保平
     * @Version  1.0
     * @Description
     * @param req
     * @Return com.micro.tiger.common.dto.BaseRsp
     * @Exception
     * @Date 2019/2/24 15:10
     */
    @Override
    public BaseRsp querySmsCode(@RequestBody VerifiCateReqDto req) {
        return SmsVerifyCodeServiceImpl.queryVerifyCode(req);
    }

    @Override
    public BaseRsp checkSmsCode(@RequestBody VerifiCateReqDto req) {
        return SmsVerifyCodeServiceImpl.checkVerifyCode(req);
    }
}
