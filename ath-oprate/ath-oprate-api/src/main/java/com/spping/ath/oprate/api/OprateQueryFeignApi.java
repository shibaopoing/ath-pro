package com.spping.ath.oprate.api;

import com.spping.ath.oprate.api.hystix.OprateQueryFeignHystrix;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 石保平
 * @Description: SMS后勤服务
 * @Date: 2019/2/24 14:02
 * @Version: 1.0
 */
@FeignClient(value = "oprate-query",fallback = OprateQueryFeignHystrix.class)
@Api(value = "运营台查询服务")
public interface OprateQueryFeignApi {

}
