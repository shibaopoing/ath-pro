package com.spping.ath.customer.service.impl;

import com.spping.ath.customer.api.dto.UsersDto;
import com.spping.ath.common.constants.RspCodeEnum;
import com.spping.ath.common.dto.rsp.BaseRsp;
import com.spping.ath.common.exceptions.AthException;
import com.spping.ath.customer.dao.mapper.UserInfoMapper;
import com.spping.ath.customer.dao.model.UserInfo;
import com.spping.ath.customer.service.IUserInfoService;
import com.spping.ath.file.api.FileCommandFeignApi;
import com.spping.ath.file.api.dto.ImgUploadDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    private static  final Logger logger =  LogManager.getLogger(UserInfoServiceImpl.class);
    @Value("${fdfs.image.cache.path}")
    private  String imageCachePath;

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private FileCommandFeignApi fileCommandFeignApi;
    @Override
    public BaseRsp getuserInfo(UsersDto userDto) {
        logger.info("获取登录用户信息");
        return  BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userDto.getId()));
    }
    @Override
    public BaseRsp login(UsersDto userDto) {
        logger.info("获取登录用户信息");
      //  String userCode =  userDto.getUserCode();
        String userPhone = userDto.getUserPhone();
        String userPwd = userDto.getUserPwd();
        //UserInfo user= userInfoMapper.selectByUserCode(userCode);
        UserInfo user= userInfoMapper.selectByPhone(userPhone);
        if(user!=null){
            if(!user.getUserPwd().equals(userPwd)){
                throw new AthException(RspCodeEnum.ERROR.LOGIN_PSW_WRONG);
            }
        }else{
            throw new AthException(RspCodeEnum.ERROR.LOGIN_UN_REGIST);
        }
        return BaseRsp.returnSuccss(user);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public BaseRsp addNewUserInfo(UsersDto userDto){
        logger.info("新增加用户");
        String userCode = userDto.getUserCode();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDto,userInfo);
        userInfo.setUserName(userDto.getUserPhone());
        userInfo.setUserCode(userDto.getUserPhone());
        userInfo.setRoleCode(1L);
        UserInfo userInfoTem = userInfoMapper.selectByUserCode(userCode);
        if (userInfoTem != null) {
            throw new AthException(RspCodeEnum.ERROR.NICKNAME_REGISTED);
        } else {
            userInfoMapper.insert(userInfo);
        }
        return BaseRsp.returnSuccss(userInfoMapper.selectByUserCode(userCode));
    }
    @Override
    public BaseRsp setNickName(UsersDto userDto){
        UserInfo user = new UserInfo();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        userInfoMapper.updateByPrimaryKeySelective(user);
        return BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userDto.getId()));
    }
    @Override
    public BaseRsp setPhoneNum(UsersDto userDto){
        UserInfo user = new UserInfo();
        user.setId(userDto.getId());
        user.setUserPhone(userDto.getUserPhone());
        userInfoMapper.updateByPrimaryKeySelective(user);
        return BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userDto.getId()));
    }
    @Override
    public BaseRsp checkPassWord(UsersDto userDto){
        String pwd=userDto.getUserPwd();
        UserInfo user = userInfoMapper.selectByPrimaryKey(userDto.getId());
        if(!pwd.equals(user.getUserPwd())){
            throw new AthException(RspCodeEnum.ERROR.LOGIN_OLD_PSW_WRONG);
        }
        return BaseRsp.returnSuccss();
    }
    @Override
    public BaseRsp setPassWord(UsersDto userDto){
        UserInfo user = new UserInfo();
        user.setId(userDto.getId());
        user.setUserPwd(userDto.getUserPwd());
        userInfoMapper.updateByPrimaryKeySelective(user);
        return BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userDto.getId()));
    }
    @Override
    public BaseRsp setHeadImage(UsersDto userDto) {

        // 获取前端传过来的base64字符串, 然后转换为文件对象再上传
        String base64Data = userDto.getFaceImage();
        ImgUploadDto imgUploadDto = new ImgUploadDto();
        imgUploadDto.setImgByteStr(base64Data);
        imgUploadDto = fileCommandFeignApi.uploadImg(imgUploadDto).getData();
        // 更细用户头像
        UserInfo user = new UserInfo();
        user.setId(userDto.getId());
        user.setFaceImage(imgUploadDto.getSmallImgUrl());
        user.setFaceImageBig(imgUploadDto.getBigImgUrl());
        userInfoMapper.updateByPrimaryKeySelective(user);
        return BaseRsp.returnSuccss(userInfoMapper.selectByPrimaryKey(userDto.getId()));
    }
}
