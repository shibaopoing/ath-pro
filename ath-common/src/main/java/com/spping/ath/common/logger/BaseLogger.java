package com.spping.ath.common.logger;

import com.spping.ath.common.constants.RspCodeEnum;
import com.spping.ath.common.dto.rsp.BaseRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author huabao.fang
 * @Date 14:45 2019-02-21
 **/
public abstract class BaseLogger {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected BaseRsp defualt(){
        return BaseRsp.returnFail(RspCodeEnum.ERROR.SYS_BUSY);
    }
}
