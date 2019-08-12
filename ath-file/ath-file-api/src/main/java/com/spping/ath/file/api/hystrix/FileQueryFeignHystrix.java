package com.spping.ath.file.api.hystrix;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.file.api.FileQueryFeignHystrixApi;
import com.spping.ath.file.api.dto.FileUploadDto;

public class FileQueryFeignHystrix extends BaseHystrix implements FileQueryFeignHystrixApi {
    @Override
    public BaseRsp queryFileInfo(FileUploadDto fileUploadDto) {
        return defualt();
    }
}
