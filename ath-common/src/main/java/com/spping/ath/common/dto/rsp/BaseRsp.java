package com.spping.ath.common.dto.rsp;

import com.spping.ath.common.constants.RspCodeEnum;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BaseRsp<T> {
    private boolean success = false;
    private String code;
    private String message;
    private T data;
    private Locale locale;

    public BaseRsp() {
        this.locale = Locale.CHINA;
    }
    public BaseRsp(RspCodeEnum.SUCCESS success){
        this.code = success.getCode();
        this.message =success.getMsg();
        this.success = true;
    }
    public BaseRsp(RspCodeEnum.ERROR err){
        this.code = err.getCode();
        this.message =err.getMsg();
        this.success = false;
    }
/*    public ResponseModel put(String key, Object value) {
        if (this.data == null || this.data instanceof Map) {
            this.data = new HashMap<String, Object>();
        }
        Map<String, Object> map = (Map<String, Object>) this.data;
        map.put(key, value);
        return this;
    }*/

    public T getData() {
        return data;
    }

    public BaseRsp setData(T data) {
        this.data = data;
        return this;
    }

    public BaseRsp setErrorMsg(RspCodeEnum.ERROR code) {
        this.setCode(code.getCode());
        this.setMessage(code.getMsg());
        this.success=false;
        return this;
    }

    public BaseRsp setCode(String code) {
        this.code = code;
        return this;
    }

    public BaseRsp setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public BaseRsp<T> appendData(T data){
        this.data = data;
        return this;
    }
    public static BaseRsp returnSuccss(){
        return new BaseRsp(RspCodeEnum.SUCCESS.SUCCESS);
    }
    public static BaseRsp returnFail(RspCodeEnum.ERROR err){
        return new BaseRsp(err);
    }
    public static <T> BaseRsp returnSuccss(T rspDTO){
        BaseRsp baseRsp = new BaseRsp(RspCodeEnum.SUCCESS.SUCCESS);
        baseRsp.setData(rspDTO);
        return baseRsp;
    }
}
