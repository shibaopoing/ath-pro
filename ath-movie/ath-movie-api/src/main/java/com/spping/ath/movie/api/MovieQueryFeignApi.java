package com.spping.ath.movie.api;

import com.spping.ath.movie.api.hystix.MovieQueryFeignHystrix;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 石保平
 * @Description: SMS后勤服务
 * @Date: 2019/2/24 14:02
 * @Version: 1.0
 */
@FeignClient(value = "movie",fallback = MovieQueryFeignHystrix.class)
@Api(description = "聊天室查询服务")
public interface MovieQueryFeignApi {

}
