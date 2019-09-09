package com.spping.ath.oprate.service;


import com.spping.ath.oprate.dao.model.Chunk;

/**
 * @author luoliang
 * @date 2018/6/21
 */
public interface IChunkService {
    /**
     * 保存文件块
     *
     * @param chunk
     */
    void saveChunk(Chunk chunk);

    /**
     * 检查文件块是否存在
     *
     * @param identifier
     * @param chunkNumber
     * @return
     */
    boolean checkChunk(String identifier, Integer chunkNumber);
}
