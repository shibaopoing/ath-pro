package com.spping.ath.movie.api;

import com.spping.ath.movie.api.hystix.MovieCommandFeignHystrix;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 石保平
 * @Description: 提供CHAT命令服务
 * @Date: 2019/2/24 14:00
 * @Version: 1.0
 */
@FeignClient(value = "movie",fallback = MovieCommandFeignHystrix.class)
@Api(description = "手影App操作服务")
public interface MovieCommandFeignApi {

}
