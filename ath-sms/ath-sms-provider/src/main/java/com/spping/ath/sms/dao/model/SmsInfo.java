package com.spping.ath.sms.dao.model;

import java.util.Date;

public class SmsInfo {
    private Long id;

    private String actDate;

    private String phoneNo;

    private String smsChannel;

    private String smsSource;

    private String smsUseType;

    private String smsCode;

    private String smsStatus;

    private String smsReturnCode;

    private String smsReturnMsg;

    private String smsRemark;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate == null ? null : actDate.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getSmsChannel() {
        return smsChannel;
    }

    public void setSmsChannel(String smsChannel) {
        this.smsChannel = smsChannel == null ? null : smsChannel.trim();
    }

    public String getSmsSource() {
        return smsSource;
    }

    public void setSmsSource(String smsSource) {
        this.smsSource = smsSource == null ? null : smsSource.trim();
    }

    public String getSmsUseType() {
        return smsUseType;
    }

    public void setSmsUseType(String smsUseType) {
        this.smsUseType = smsUseType == null ? null : smsUseType.trim();
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode == null ? null : smsCode.trim();
    }

    public String getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus == null ? null : smsStatus.trim();
    }

    public String getSmsReturnCode() {
        return smsReturnCode;
    }

    public void setSmsReturnCode(String smsReturnCode) {
        this.smsReturnCode = smsReturnCode == null ? null : smsReturnCode.trim();
    }

    public String getSmsReturnMsg() {
        return smsReturnMsg;
    }

    public void setSmsReturnMsg(String smsReturnMsg) {
        this.smsReturnMsg = smsReturnMsg == null ? null : smsReturnMsg.trim();
    }

    public String getSmsRemark() {
        return smsRemark;
    }

    public void setSmsRemark(String smsRemark) {
        this.smsRemark = smsRemark == null ? null : smsRemark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}