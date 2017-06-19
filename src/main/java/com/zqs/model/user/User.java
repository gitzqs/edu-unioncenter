package com.zqs.model.user;

import java.util.Date;

import com.zqs.model.basics.REntity;
/**
 * 用户基本信息
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 上午10:28:12
 */
public class User extends REntity{

	private static final long serialVersionUID = 1643043714980965815L;
	
	/** 用户名称 */
	private String userName;
	
	/** 手机号 */
	private String userMobile;
	
	/** 密码 */
	private String userPwd;
	
	/** 重复密码 */
	private String userPwdAgain;
	
	/** 注册时间 */
	private Date regTime;
	
	/** 剩余错误次数  */
	private int errorNum;
	
	/** 冻结时间 */
	private Date frozenTime;
	
	/** 上次错误时间 */
	private Date lastErrorTime;
	
	/** 上次登录时间 */
	private Date lastLoginTime;
	
	/** 状态 {@link EUserStatus.java} */
	private int status;
	
	/** 微信关联标识 */
	private String openId;
	
	/** 短信验证码 */
	private String messageCode;
	
	/** 图片验证码 */
	private String imgCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public int getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}

	public Date getFrozenTime() {
		return frozenTime;
	}

	public void setFrozenTime(Date frozenTime) {
		this.frozenTime = frozenTime;
	}

	public Date getLastErrorTime() {
		return lastErrorTime;
	}

	public void setLastErrorTime(Date lastErrorTime) {
		this.lastErrorTime = lastErrorTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserPwdAgain() {
		return userPwdAgain;
	}

	public void setUserPwdAgain(String userPwdAgain) {
		this.userPwdAgain = userPwdAgain;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
}
