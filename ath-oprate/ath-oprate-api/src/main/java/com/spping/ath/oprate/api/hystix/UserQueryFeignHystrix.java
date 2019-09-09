package com.spping.ath.oprate.api.hystix;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.oprate.api.UserQueryFeignApi;

import java.util.Map;

public class UserQueryFeignHystrix extends BaseHystrix implements UserQueryFeignApi {
    @Override
    public BaseRsp getOpUserInfo(Map userInfo) {
        return defualt();
    }
}
