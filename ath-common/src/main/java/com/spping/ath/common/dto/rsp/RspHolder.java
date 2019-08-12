package com.spping.ath.common.dto.rsp;

import com.spping.ath.common.constants.RspCodeEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RspHolder {
    private static ThreadLocal<Object> model = new ThreadLocal<>();

    public void clean() {
        model.remove();
    }

    public BaseRsp getModel() {
        Object o = model.get();
        if (o == null) {
            this.setModel(new BaseRsp());
            o = this.getObject();
        }
        if (o != null && o instanceof BaseRsp) {
            return (BaseRsp) o;
        } else {
            return null;
        }
    }

    public Object getObject() {
        return model.get();
    }

    public void setModel(Object o) {
        model.set(o);
    }

    public BaseRsp put(String key, Object value) {
        BaseRsp responseModel = this.getModel();
        Object data = responseModel.getData();
        if (data == null || !(data instanceof Map)) {
            data = new HashMap<String, Object>();
            responseModel.setData(data);
        }
        Map<String, Object> map = (Map<String, Object>) data;
        map.put(key, value);
        return responseModel;
    }

    public BaseRsp setData(Object data) {
        BaseRsp responseModel = this.getModel();
        responseModel.setData(data);
        return responseModel;
    }

    public BaseRsp setErrorMsg(RspCodeEnum codeEnum) {
        BaseRsp responseModel = this.getModel();
        responseModel.setCode(codeEnum.getCode());
        responseModel.setMessage(codeEnum.getMessage());
        return responseModel;
    }
}
