package com.zqs.model.code;

import java.util.Date;

import com.zqs.model.basics.REntity;
/**
 * 短信验证码
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 上午10:42:00
 */
public class MessageCode extends REntity{

	private static final long serialVersionUID = -7303602168884521855L;
	
	/** 手机号 */
	private String mobile;
	
	/** 短信类别 */
	private int type;
	
	/** 验证码 */
	private String code;
	
	/** 开始时间 */
	private Date beginTime;
	
	/** 过期时间 */
	private Date endTime;
	
	/** 状态{@link EBaseStatus.java}*/
	private int status;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
