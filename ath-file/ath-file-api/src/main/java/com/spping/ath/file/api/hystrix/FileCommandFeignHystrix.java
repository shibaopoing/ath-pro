package com.spping.ath.file.api.hystrix;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.file.api.FileCommandFeignApi;
import com.spping.ath.file.api.dto.FileUploadDto;
import com.spping.ath.file.api.dto.ImgUploadDto;

public class FileCommandFeignHystrix extends BaseHystrix implements FileCommandFeignApi {
    @Override
    public BaseRsp uploadImg(ImgUploadDto imgUploadDto) {
        return defualt();
    }

    @Override
    public BaseRsp uploadFiles(FileUploadDto fileUploadDto) {
        return defualt();
    }
}
