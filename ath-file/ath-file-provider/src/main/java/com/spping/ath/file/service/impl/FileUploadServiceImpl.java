package com.spping.ath.file.service.impl;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.utils.FileUtils;
import com.spping.ath.file.api.dto.FileUploadDto;
import com.spping.ath.file.api.dto.ImgUploadDto;
import com.spping.ath.file.dao.model.FileInfo;
import com.spping.ath.file.service.IFileUploadService;
import com.spping.ath.file.utils.FastDFSClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class FileUploadServiceImpl implements IFileUploadService {
    private static  final Logger logger =  LogManager.getLogger(FileUploadServiceImpl.class);
    @Value("${file-upload-folder}")
    private String uploadFolder;
    @Value("${fdfs.image.cache.path}")
    private  String imageCachePath;
    @Autowired
    private FastDFSClient fastDFSClient;
    @Override
    public BaseRsp<ImgUploadDto> uploadImg(ImgUploadDto imgUploadDto) {
        // 获取前端传过来的base64字符串, 然后转换为文件对象再上传
        String base64Data = imgUploadDto.getImgByteStr();
        String userFacePath =imageCachePath + new Date() + "userface64.png";
        String thump = "_150x150.";
        String thumpImgUrl="";
        String url="";
        try {
            FileUtils.base64ToFile(userFacePath, base64Data);
            // 上传文件到fastdfs
            MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
            url = fastDFSClient.uploadBase64(faceFile);
            // 获取缩略图的url
            String arr[] = url.split("\\.");
            thumpImgUrl = arr[0] + thump + arr[1];
            imgUploadDto.setBigImgUrl(url);
            imgUploadDto.setSmallImgUrl(thumpImgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseRsp.returnSuccss(imgUploadDto);
    }

    @Override
    public BaseRsp<FileUploadDto> uploadFiles(FileUploadDto fileUploadDto) {
        MultipartFile file = fileUploadDto.getFile();
        logger.debug("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), fileUploadDto.getChunkNumber());
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            Path path = Paths.get(FileUtils.generatePath(uploadFolder, fileUploadDto.getIdentifier(),fileUploadDto.getFilename(),fileUploadDto.getChunkNumber()));
            //文件写入指定路径
            Files.write(path, bytes);
        } catch (IOException e) {
        }
        logger.debug("文件 {} 写入成功, uuid:{}", fileUploadDto.getFilename(), fileUploadDto.getIdentifier());
       // chunkService.saveChunk(fileUploadDto);
        return  BaseRsp.returnSuccss();
    }
    public String mergeFile(FileInfo fileInfo) {
        String filename = fileInfo.getFilename();
        String file = uploadFolder + "/" + fileInfo.getIdentifier() + "/" + filename;
        String folder = uploadFolder + "/" + fileInfo.getIdentifier();
        FileUtils.merge(file, folder, filename);
        fileInfo.setLocation(file);
        //fileInfoService.addFileInfo(fileInfo);

        return "合并成功";
    }
}
