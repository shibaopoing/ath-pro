package com.spping.ath.sms.util;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: 石保平
 * @Description: ${description}
 * @Date: 2019/4/13 23:55
 * @Version: 1.0
 */
@Component
public class SmsUtil {

    private static final Logger logger =  LogManager.getLogger(SmsUtil.class);

    public static String genSmsCode(){
        int x;//定义两变量
        Random ne=new Random();//实例化一个random的对象ne
        x=ne.nextInt(9000)+1000;//为变量赋随机值1000-9999
        return String.valueOf(x);
    }
    public static SmsRsp sendMsg(String mobile){
        String smsCode = genSmsCode();
        return sendMsg(mobile,smsCode);
    }
    public static SmsRsp sendMsg(String mobile, String smsCode) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4k9r9k4tYHER", "L9BIuIKEvu1WDAexYIz16UwaVSAJpm");
        IAcsClient client = new DefaultAcsClient(profile);
        SmsRsp rsp = null;
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("TemplateCode", "SMS_163437733");
        request.putQueryParameter("SignName", "滴答信息");
        request.putQueryParameter("TemplateParam","{\"code\":\""+smsCode+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            rsp= JSON.parseObject(response.getData(),SmsRsp.class);
            rsp.setVerifyCode(smsCode);
            logger.info(response.getData());
        } catch (ServerException e) {
            rsp.setCode("ERR");
            rsp.setMessage(e.getMessage());
           // e.printStackTrace();
        } catch (ClientException e) {
            rsp.setCode("ERR");
            rsp.setMessage(e.getMessage());
           // e.printStackTrace();
        }
        return  rsp;
    }
    public static void main(String[] args) {
        sendMsg("13681987954");
    }
    public static class SmsRsp {

        private String Message;
        private String RequestId;
        private String BizId;
        private  String Code;
        private  String VerifyCode;
        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String requestId) {
            RequestId = requestId;
        }

        public String getBizId() {
            return BizId;
        }

        public void setBizId(String bizId) {
            BizId = bizId;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String code) {
            Code = code;
        }
        public String getVerifyCode() {
            return VerifyCode;
        }
        public void setVerifyCode(String verifyCode) {
            VerifyCode = verifyCode;
        }
    }
}
