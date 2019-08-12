package com.spping.ath.common.exceptions;

import com.spping.ath.common.constants.RspCodeEnum;
import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.dto.rsp.RspHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by kinginblue on 2017/4/10.
 *
 * @ControllerAdvice + @ExceptionHandler 实现全局的 Controller 层的异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class.getName());
    @Autowired
    private RspHolder responseHolder;

    /**
     * 处理所有不可知的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    BaseRsp handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return commonException();
    }

    /**
     * 处理系统的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AthException.class)
    BaseRsp handleAthException(AthException e) {
        logger.error(e.getMessage(), e);
        BaseRsp responseModel = responseHolder.getModel();
        responseModel.setCode(e.getCode());
        responseModel.setSuccess(false);
        responseModel.setMessage(e.getMsg());
        return responseModel;
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    BaseRsp handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return commonException();
    }
    BaseRsp commonException() {
        BaseRsp responseModel = responseHolder.getModel();
        responseModel.setErrorMsg(RspCodeEnum.ERROR.SYS_ERR);
        return responseModel;
    }
}
