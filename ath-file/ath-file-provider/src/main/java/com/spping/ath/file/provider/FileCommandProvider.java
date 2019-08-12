package com.spping.ath.file.provider;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.provider.BaseProvider;
import com.spping.ath.file.api.FileCommandFeignApi;
import com.spping.ath.file.api.dto.FileUploadDto;
import com.spping.ath.file.api.dto.ImgUploadDto;
import com.spping.ath.file.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class FileCommandProvider extends BaseProvider implements FileCommandFeignApi {
    @Autowired
    IFileUploadService iFileUploadService;
    @Override
    @RequestMapping(value = "/uploadImg", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp<ImgUploadDto> uploadImg(ImgUploadDto imgUploadDto) {
        return iFileUploadService.uploadImg(imgUploadDto);
    }

    @Override
    @RequestMapping(value = "/uploadFiles", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    public BaseRsp uploadFiles(FileUploadDto fileUploadDto) {
        return null;
    }
}
