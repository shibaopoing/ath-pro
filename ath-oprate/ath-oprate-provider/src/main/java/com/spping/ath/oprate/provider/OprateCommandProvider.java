package com.spping.ath.oprate.provider;

import com.spping.ath.common.constants.RspCodeEnum;
import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.exceptions.AthException;
import com.spping.ath.common.provider.BaseProvider;
import com.spping.ath.oprate.api.OprateCommandFeignApi;
import com.spping.ath.oprate.api.dto.ChunkDto;
import com.spping.ath.oprate.api.dto.FileInfoDto;
import com.spping.ath.oprate.dao.model.Chunk;
import com.spping.ath.oprate.service.IChunkService;
import com.spping.ath.oprate.service.IFileInfoService;
import com.spping.ath.oprate.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class OprateCommandProvider extends BaseProvider implements OprateCommandFeignApi {
    @Value("${prop.upload-folder}")
    private String uploadFolder;
    @Autowired
    private IFileInfoService iFileInfoService;
    @Autowired
    private IChunkService iChunkService;
    @Override
    public BaseRsp uploadChunk( ChunkDto chunkDto) {
        MultipartFile file = chunkDto.getFile();
        log.debug("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunkDto.getChunkNumber());
        Map rspMap=new HashMap();
        try {
            Chunk chunk= new Chunk();
            BeanUtils.copyProperties(chunkDto,chunk);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FileUtils.generatePath(uploadFolder, chunkDto));
            //文件写入指定路径
            Files.write(path, bytes);
            log.debug("文件 {} 写入成功, uuid:{}", chunkDto.getFilename(), chunkDto.getIdentifier());
            iChunkService.saveChunk(chunk);
            rspMap.put("needMerge",true);
            rspMap.put("result","success");
            rspMap.put("message","成功");
        } catch (IOException e) {
             throw  new AthException(RspCodeEnum.ERROR.SYS_ERR);
        }
        return BaseRsp.returnSuccss(rspMap);
    }
    @Override
    public BaseRsp checkChunk( ChunkDto chunkDto, HttpServletResponse response) {
        if (iChunkService.checkChunk(chunkDto.getIdentifier(), chunkDto.getChunkNumber())) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
        return BaseRsp.returnSuccss(chunkDto);
    }
    @Override
    public BaseRsp mergeFile( FileInfoDto fileInfoDto) {
        String filename = fileInfoDto.getFilename();
        String file = uploadFolder + "/" + fileInfoDto.getIdentifier() + "/" + filename;
        String folder = uploadFolder + "/" + fileInfoDto.getIdentifier();
        FileUtils.merge(file, folder, filename);
        fileInfoDto.setLocation(file);
        //fileInfoService.addFileInfo(fileInfo);
        Map rspMap=new HashMap();
        rspMap.put("needMerge",true);
        rspMap.put("result","success");
        rspMap.put("message","成功");
        return BaseRsp.returnSuccss(rspMap);
    }
}
