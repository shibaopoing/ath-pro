package com.spping.ath.file.api.dto;

import com.spping.ath.common.dto.req.BaseReq;

public class ImgUploadDto extends BaseReq {
    /**
     图片文件字节串
     */
    private String imgByteStr;

    /**
     *上传后的路径：大图
     */
    private String bigImgUrl;
    /**
     *上传后的路径：略缩图
     */
    private String smallImgUrl;
    public String getImgByteStr() {
        return imgByteStr;
    }

    public void setImgByteStr(String imgByteStr) {
        this.imgByteStr = imgByteStr;
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }
}
