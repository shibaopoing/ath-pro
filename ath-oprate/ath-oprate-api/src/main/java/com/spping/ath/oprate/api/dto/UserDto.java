package com.spping.ath.oprate.api.dto;

import lombok.Data;
import com.spping.ath.common.dto.req.BaseReq;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserDto extends BaseReq {
	@Id
	@GeneratedValue
	private Long id;
	private String userCode;
	private String userName;
	private String userPwd;
	private String userPhone;
	private String userEmail;
	private String faceImage;
	private String token;

}
