package com.spping.ath.customer.api.hystrix;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.customer.api.CustomerQueryFeignHystrixApi;
import com.spping.ath.customer.api.dto.UsersDto;

public class CustomerQueryFeignHystrix extends BaseHystrix implements CustomerQueryFeignHystrixApi {
    @Override
    public BaseRsp getUserInfo(UsersDto userInfo) {
        return defualt();
    }
}
