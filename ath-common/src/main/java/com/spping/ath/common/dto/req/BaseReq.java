package com.spping.ath.common.dto.req;

public class BaseReq {
    /**
     * 项目编码
     */
    private String projectCode;
    /**
     * 请求来源
     */
    private String reqSource;

    public String getReqSource() {
        return reqSource;
    }

    public void setReqSource(String reqSource) {
        this.reqSource = reqSource;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
