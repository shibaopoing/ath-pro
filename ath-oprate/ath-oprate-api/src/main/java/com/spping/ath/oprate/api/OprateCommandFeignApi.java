package com.spping.ath.oprate.api;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.oprate.api.dto.ChunkDto;
import com.spping.ath.oprate.api.dto.FileInfoDto;
import com.spping.ath.oprate.api.hystix.OprateCommandFeignHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 石保平
 * @Description: 运营台文件上传服务
 * @Date: 2019/2/24 14:00
 * @Version: 1.0
 */
@FeignClient(value = "oprate-command",fallback = OprateCommandFeignHystrix.class)
@Api(value = "运营台操作服务")
public interface OprateCommandFeignApi {
    @PostMapping("/chunk")
    @ApiOperation(value = "文件上传主要接口")
    public BaseRsp uploadChunk( ChunkDto chunkDto);

    @GetMapping("/chunk")
    @ApiOperation(value = "分片校验")
    public BaseRsp checkChunk(ChunkDto chunkDto, HttpServletResponse response);

    @PostMapping("/mergeFile")
    @ApiOperation(value = "文件合并接口")
    public BaseRsp mergeFile( FileInfoDto fileInfoDto);
}
