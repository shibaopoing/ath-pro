package com.spping.ath.oprate.dao.model;

import lombok.Data;
@Data
public class UserInfo {
	private Long id;
	private String userCode;
	private String userName;
	private String userPwd;
	private String userPhone;
	private String userEmail;
	private String faceImage;

}
