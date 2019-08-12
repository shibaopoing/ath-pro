package com.spping.ath.file.service;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.file.api.dto.FileUploadDto;
import com.spping.ath.file.api.dto.ImgUploadDto;

public interface IFileUploadService {
    public BaseRsp<ImgUploadDto> uploadImg(ImgUploadDto imgUploadDto);
    public BaseRsp<FileUploadDto> uploadFiles(FileUploadDto fileUploadDto);
}
