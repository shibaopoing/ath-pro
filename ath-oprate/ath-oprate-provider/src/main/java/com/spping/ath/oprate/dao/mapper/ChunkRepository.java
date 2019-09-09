package com.spping.ath.oprate.dao.mapper;


import com.spping.ath.oprate.dao.model.Chunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author luoliang
 * @date 2018/6/21
 */
public interface ChunkRepository extends JpaRepository<Chunk, Long>, JpaSpecificationExecutor<Chunk> {
}
