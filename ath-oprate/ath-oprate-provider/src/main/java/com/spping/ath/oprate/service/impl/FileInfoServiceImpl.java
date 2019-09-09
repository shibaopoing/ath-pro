package com.spping.ath.oprate.service.impl;

import com.spping.ath.oprate.dao.mapper.FileInfoRepository;
import com.spping.ath.oprate.dao.model.FileInfo;
import com.spping.ath.oprate.service.IFileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luoliang
 * @date 2018/6/20
 */
@Service
public class FileInfoServiceImpl implements IFileInfoService {
    @Resource
    private FileInfoRepository fileInfoRepository;

    @Override
    public FileInfo addFileInfo(FileInfo fileInfo) {
        return fileInfoRepository.save(fileInfo);
    }
}
