package com.spping.ath.sms.api.dto;

import com.spping.ath.common.dto.req.BaseReq;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: 石保平
 * @Description: 短信印证码请求体
 * @Date: 2019/2/24 14:27
 * @Version: 1.0
 */
public class VerifiCateReqDto extends BaseReq {
    //手机号
    @NotBlank(message = "不能为空")
    @ApiModelProperty("手机号")
    private String phoneNumber;
    //短信码
    @ApiModelProperty("验证码,短信校验必输项")
    private String verifyCode;
    //短信用途 01：注册，02：登陆，03：通知',
    @NotBlank(message = "不能为空")
    @ApiModelProperty("短信用途,00-注册,01-登录,03-通知,04-其他")
    private String useType;
    @ApiModelProperty("验证码状态，Y-可用,N-不可用")
    private String verifyCodeStatue;

    public String getVerifyCode() {
        return verifyCode;
    }
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUseType() {
        return useType;
    }
    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getVerifyCodeStatue() {
        return verifyCodeStatue;
    }
    public void setVerifyCodeStatue(String verifyCodeStatue) {
        this.verifyCodeStatue = verifyCodeStatue;
    }
}
