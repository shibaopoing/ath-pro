package com.spping.ath.oprate.api.hystix;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.hystrix.BaseHystrix;
import com.spping.ath.oprate.api.OprateCommandFeignApi;
import com.spping.ath.oprate.api.dto.ChunkDto;
import com.spping.ath.oprate.api.dto.FileInfoDto;

import javax.servlet.http.HttpServletResponse;

public class OprateCommandFeignHystrix extends BaseHystrix implements OprateCommandFeignApi {
    @Override
    public BaseRsp uploadChunk(ChunkDto chunkDto) {
        return defualt();
    }
    @Override
    public BaseRsp checkChunk(ChunkDto chunkDto, HttpServletResponse response) {
        return defualt();
    }

    @Override
    public BaseRsp mergeFile(FileInfoDto fileInfoDto) {
        return defualt();
    }
}
