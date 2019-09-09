package com.spping.ath.oprate.service.impl;

import com.spping.ath.common.constants.RspCodeEnum;
import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.exceptions.AthException;
import com.spping.ath.oprate.api.dto.UserDto;
import com.spping.ath.oprate.dao.mapper.UserInfoMapper;
import com.spping.ath.oprate.dao.model.UserInfo;
import com.spping.ath.oprate.service.IUserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    private static  final Logger logger =  LogManager.getLogger(UserInfoServiceImpl.class);
    private  String imageCachePath="";

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public BaseRsp getUserInfo(Map userInfo) {
        logger.info("获取登录用户信息");
        Map rspMap=new HashMap();
        rspMap.put("roles","'admin'-token");
        rspMap.put("introduction","I am a super administrator");
        rspMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        rspMap.put("name","Super Admin");
        rspMap.put("token","admin-token");
        rspMap.put("code",20000);
        return  BaseRsp.returnSuccss(rspMap);
    }
    @Override
    public BaseRsp getUserInfo(UserDto userDto) {
/*        logger.info("获取登录用户信息");
        Map rspMap=new HashMap();
        rspMap.put("roles","'admin'-token");
        rspMap.put("introduction","I am a super administrator");
        rspMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        rspMap.put("name","Super Admin");
        rspMap.put("token","admin-token");
        rspMap.put("code",20000);*/
        return  BaseRsp.returnSuccss();
    }
    @Override
    public BaseRsp login(UserDto userDto) {
        logger.info("获取登录用户信息");
        Map rspMap=new HashMap();
        rspMap.put("token","admin-token");
        rspMap.put("code",20000);
        return BaseRsp.returnSuccss(rspMap);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public BaseRsp addNewUserInfo(UserDto userDto){
        logger.info("新增加用户");
        String userCode = userDto.getUserCode();
        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(userDto,user);
        user.setUserName(userDto.getUserPhone());
        user.setUserCode(userDto.getUserPhone());
        UserInfo userInfoTem = userInfoMapper.selectByUserCode(userCode);
        if (userInfoTem != null) {
            throw new AthException(RspCodeEnum.ERROR.NICKNAME_REGISTED);
        } else {
            userInfoMapper.insert(user);
        }
        return BaseRsp.returnSuccss(userInfoMapper.selectByUserCode(userCode));
    }
    @Override
    public BaseRsp setNickName(UserDto userDto){
        UserInfo user = new UserInfo();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        userInfoMapper.updateByPrimaryKeySelective(user);
        return BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userDto.getId()));
    }
    @Override
    public BaseRsp setPhoneNum(UserDto userDto){
        UserInfo user = new UserInfo();
        user.setId(userDto.getId());
        user.setUserPhone(userDto.getUserPhone());
        userInfoMapper.updateByPrimaryKeySelective(user);
        return BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userDto.getId()));
    }
    @Override
    public BaseRsp checkPassWord(UserDto userDto){
        String pwd=userDto.getUserPwd();
        UserInfo user = userInfoMapper.selectByPrimaryKey(userDto.getId());
        if(!pwd.equals(user.getUserPwd())){
            throw new AthException(RspCodeEnum.ERROR.LOGIN_OLD_PSW_WRONG);
        }
        return BaseRsp.returnSuccss();
    }
    @Override
    public BaseRsp setPassWord(UserDto userDto){
        UserInfo user = new UserInfo();
        user.setId(userDto.getId());
        user.setUserPwd(userDto.getUserPwd());
        userInfoMapper.updateByPrimaryKeySelective(user);
        return BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userDto.getId()));
    }
    @Override
    public BaseRsp setHeadImage(UserDto userDto) {

        // 获取前端传过来的base64字符串, 然后转换为文件对象再上传
/*        String base64Data = userInfo.getFaceImage();
        ImgUploadDto imgUploadDto = new ImgUploadDto();
        imgUploadDto.setImgByteStr(base64Data);
        imgUploadDto = fileCommandFeignApi.uploadImg(imgUploadDto).getData();
        // 更细用户头像
        UserInfo user = new UserInfo();
        user.setId(userInfo.getId());
        user.setFaceImage(imgUploadDto.getSmallImgUrl());
        user.setFaceImageBig(imgUploadDto.getBigImgUrl());
        userInfoMapper.updateByPrimaryKeySelective(user);
        return BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userInfo.getId()));*/
        return BaseRsp.returnSuccss();
    }
}
