package com.spping.ath.common.hystrix;

import com.spping.ath.common.constants.RspCodeEnum;
import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.logger.BaseLogger;

/**
 * @Author huabao.fang
 * @Date 14:43 2019-02-21
 **/
public class BaseHystrix extends BaseLogger {

    protected BaseRsp defualt(){
        return BaseRsp.returnFail(RspCodeEnum.ERROR.SYS_BUSY);
    }

}
