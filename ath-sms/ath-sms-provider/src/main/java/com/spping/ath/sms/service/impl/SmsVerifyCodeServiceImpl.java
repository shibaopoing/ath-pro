package  com.spping.ath.sms.service.impl;

import com.github.pagehelper.StringUtil;
import com.spping.ath.common.constants.RspCodeEnum;
import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.exceptions.AthException;
import com.spping.ath.common.utils.DateUtil;
import com.spping.ath.common.utils.PhoneUtil;
import com.spping.ath.common.utils.RedisUtil;
import com.spping.ath.sms.api.dto.VerifiCateReqDto;
import com.spping.ath.sms.asyncTask.RedisAsyncTask;
import com.spping.ath.sms.dao.mapper.SmsInfoMapper;
import com.spping.ath.sms.dao.model.SmsInfo;
import com.spping.ath.sms.service.ISmsVerifyCodeService;
import com.spping.ath.sms.util.SmsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @Author: 石保平
 * @Description: 验证码实现层
 * @Date: 2019/2/24 15:28
 * @Version: 1.0
 */
@PropertySource({"classpath:sms.properties"})
@Service
public class SmsVerifyCodeServiceImpl implements ISmsVerifyCodeService {
    private static final Logger logger =  LogManager.getLogger(SmsVerifyCodeServiceImpl.class);
    @Value("${verifyCodeRepeatSub}")
    private int repeatSubSec;
    @Autowired
    RedisAsyncTask redisAsyncTask;
    @Autowired
    SmsInfoMapper smsInfoMapper;
    @Autowired
    private RedisUtil redisUtils;
    @Override
    public BaseRsp sendVerifyCode(VerifiCateReqDto req) {
        String phoneNumber = req.getPhoneNumber();
        String useType = req.getUseType();
        logger.info("请求发送验证码手机号;"+phoneNumber);
        logger.info("验证码使用类型;"+useType);
        if(!PhoneUtil.isPhone(phoneNumber)){
            throw  new AthException(RspCodeEnum.ERROR.SMS_MOBILE_NOT_CORRECT);
        }else{
            //   repeatSendCheck(phoneNumber,useType,"send");

            if(StringUtil.isEmpty(readFromRedis(phoneNumber,useType))){
                //发送验证码
                //SmsManager.SmsRsp smsRsp=SmsManager.sendMsg(phoneNumber);
                //SmsUtil.SmsRsp smsRsp= SmsUtil.sendMsg(phoneNumber);
                SmsUtil.SmsRsp smsRsp = new SmsUtil.SmsRsp();
                smsRsp.setCode("OK");
                smsRsp.setVerifyCode("334551");
                if("OK".equals(smsRsp.getCode())){
                    SmsInfo smsInfo = new SmsInfo();
                    smsInfo.setPhoneNo(phoneNumber);
                    smsInfo.setSmsUseType(req.getUseType());
                    smsInfo.setActDate("");
                    smsInfo.setPhoneNo(phoneNumber);
                    smsInfo.setSmsChannel("TXY");
                    smsInfo.setSmsCode(smsRsp.getVerifyCode());
                    smsInfo.setSmsReturnCode(smsRsp.getBizId());
                    smsInfo.setSmsReturnMsg(smsRsp.getMessage());
                    smsInfo.setSmsSource(req.getReqSource());
                    smsInfo.setSmsStatus("Y");
                    //异步任务，存之redis
                    saveToRedis(smsInfo);
                    smsInfoMapper.insert(smsInfo);
                }else{

                    throw  new AthException(RspCodeEnum.ERROR.SMS_SEND_ERROR,smsRsp.getCode()+smsRsp.getMessage());
                }
            }else{
                throw  new AthException(RspCodeEnum.ERROR.SMS_SEND_QUICKLY,repeatSubSec);
            }
            return BaseRsp.returnSuccss();
        }
    }

    @Override
    public BaseRsp queryVerifyCode(VerifiCateReqDto req) {
        String phoneNumber = req.getPhoneNumber();
        String useType = req.getUseType();
        logger.info("查询验证码手机号;"+phoneNumber);
        logger.info("查询验证码类型;"+useType);
        if(!PhoneUtil.isPhone(phoneNumber)){
            throw  new AthException(RspCodeEnum.ERROR.SMS_MOBILE_NOT_CORRECT);
        }else{
            //    SmsInfo  smsInfo =repeatSendCheck(phoneNumber,useType,"query");
            String smsCode = readFromRedis(phoneNumber,useType);
            if(StringUtil.isEmpty(smsCode)){
                //如果缓存中没值。去数据库查询。
              /*  SmsInfo smsInfo=repeatSendCheck(phoneNumber,useType,"query");
                if(smsInfo!=null){
                    req.setVerifyCode(smsInfo.getSmsCode());
                    //保存到redis
                    saveToRedis(smsInfo);
                }else{
                    throw  new CommonException(RspCodeEnum.PLEASE_SEND_SMS_CODE,repeatSubSec);
                }*/
                throw  new AthException(RspCodeEnum.ERROR.PLEASE_SEND_SMS_CODE,repeatSubSec);
            }else{
                req.setVerifyCode(smsCode);
                req.setVerifyCodeStatue("Y");
            }
        }
        return BaseRsp.returnSuccss(req);
    }
    @Override
    public BaseRsp checkVerifyCode(VerifiCateReqDto req) {
        String phoneNumber = req.getPhoneNumber();
        String useType = req.getUseType();
        String smsCode = req.getVerifyCode();
        logger.info("校验码手机号;"+phoneNumber);
        logger.info("校验码类型;"+useType);
        logger.info("校验码;"+smsCode);
        if(!PhoneUtil.isPhone(phoneNumber)){
            throw  new AthException(RspCodeEnum.ERROR.SMS_MOBILE_NOT_CORRECT);
        }else{
            String redisSmsCode = readFromRedis(phoneNumber,useType);
            if(StringUtil.isEmpty(redisSmsCode)){
                throw  new AthException(RspCodeEnum.ERROR.SMS_INFO_UN_AVAIL);
            }else{
                if(redisSmsCode.equals(smsCode)){
                    req.setVerifyCodeStatue("Y");
                }else{
                    throw  new AthException(RspCodeEnum.ERROR.SMS_CODE_NOT_CORRECT);
                }
            }
        }
        return BaseRsp.returnSuccss(req);
    }
    @Override
    public BaseRsp dropVerifyCode(VerifiCateReqDto req) {
        String smsCode = req.getVerifyCode();
        String phoneNumber = req.getPhoneNumber();
        String useType = req.getUseType();
        if(StringUtil.isEmpty(smsCode)){
            throw  new AthException(RspCodeEnum.ERROR.SMS_PARAM_IS_NULL,"verifyCode");
        }
        //先从缓存中查找下有没有该key
        if(redisUtils.hasKey(redisKey(phoneNumber,useType))){
            //从缓存中删除key
            deleteFromRedis(phoneNumber,useType);
            //修改代码状态
            smsInfoMapper.updateByParams(phoneNumber,smsCode);
            req.setVerifyCodeStatue("N");
        }
        return BaseRsp.returnSuccss(req);
    }

    public SmsInfo repeatSendCheck(String phoneNumber, String useType, String checkType){
        //检查1分钟内是否已发送
        SmsInfo smsInfoTem = smsInfoMapper.selectLastByParams(phoneNumber,useType);
        if(smsInfoTem!=null){
            //获得当前时间
            //检查之前有没有发送
            Date lastDt = smsInfoTem.getCreateTime();
            int diff = DateUtil.diffSecondsNow(lastDt);
            logger.info("短信类型:"+useType+",相差时间:"+diff);
            //注册短信60秒内不能重发
            if("send".equals(checkType)){
                if(diff<60){
                    throw  new AthException(RspCodeEnum.ERROR.SMS_SEND_QUICKLY,repeatSubSec);
                }
            }
            //查询短信60秒外失效
            if("query".equals(checkType)){
                if(diff>repeatSubSec){
                    throw  new AthException(RspCodeEnum.ERROR.SMS_INFO_UN_AVAIL,repeatSubSec);
                }
            }
        }
        return smsInfoTem;
    }
    public String readFromRedis(String phoneNumber,String useType){
        String value =redisUtils.get(redisKey(phoneNumber,useType));
        if(value==null){
            value="";
        }
        return value;
    }
    public void saveToRedis(SmsInfo smsInfo){
        redisAsyncTask.saveToRedis(redisKey(smsInfo),smsInfo.getSmsCode(),repeatSubSec);
    }
    public void deleteFromRedis(String phoneNumber,String useType){
        redisAsyncTask.deleteFromRedis(redisKey(phoneNumber,useType));
    }
    public String redisKey(SmsInfo smsInfo){
        return "sms-"+smsInfo.getPhoneNo()+"-"+smsInfo.getSmsUseType();
    }
    public String redisKey(String phoneNumber,String useType){
        return "sms-"+phoneNumber+"-"+useType;
    }
}
