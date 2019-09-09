package com.spping.ath.oprate.provider;

import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.provider.BaseProvider;
import com.spping.ath.oprate.api.UserCommandFeignApi;
import com.spping.ath.oprate.api.UserQueryFeignApi;
import com.spping.ath.oprate.api.dto.UserDto;
import com.spping.ath.oprate.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class UserQueryProvider extends BaseProvider implements UserQueryFeignApi {
    @Autowired
    IUserInfoService iUserInfoService;
    public BaseRsp getOpUserInfo(Map userInfo) {
        return iUserInfoService.getUserInfo(userInfo);
    }
}
