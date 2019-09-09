package com.spping.ath.oprate.api;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.oprate.api.dto.UserDto;
import com.spping.ath.oprate.api.hystix.UserQueryFeignHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @Author: 石保平
 * @Description: 用户信息查询
 * @Date: 2019/2/24 14:02
 * @Version: 1.0
 */
@FeignClient(value = "oprate-user-query",fallback = UserQueryFeignHystrix.class)
@Api(value = "用户信息查询服务")
public interface UserQueryFeignApi {
    @RequestMapping(value = "/opInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息")
    public BaseRsp getOpUserInfo(Map userInfo);
}
