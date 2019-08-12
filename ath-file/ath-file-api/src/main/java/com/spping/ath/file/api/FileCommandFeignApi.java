package com.spping.ath.file.api;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.file.api.dto.FileUploadDto;
import com.spping.ath.file.api.dto.ImgUploadDto;
import com.spping.ath.file.api.hystrix.FileCommandFeignHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 石保平
 * @Description: 文件操作服务
 * @Date: 2019/2/24 14:00
 * @Version: 1.0
 */
@FeignClient(value = "file-command",fallback = FileCommandFeignHystrix.class)
@Api(description = "文件操作服务")
public interface FileCommandFeignApi {
    @RequestMapping(value = "/uploadImg", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "图片上传")
    public BaseRsp<ImgUploadDto> uploadImg(@RequestBody ImgUploadDto imgUploadDto);
    @RequestMapping(value = "/uploadFiles", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    public BaseRsp uploadFiles(@RequestBody FileUploadDto fileUploadDto);

}
