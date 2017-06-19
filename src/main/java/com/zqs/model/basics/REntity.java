package com.zqs.model.basics;

import java.io.Serializable;
import java.util.Date;
/**
 * 基础状态
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 上午10:27:49
 */
public class REntity implements Serializable{

	private static final long serialVersionUID = -7110684881491129218L;
	
	private int id;
	
	/** 创建人id */
	private int creatorId;
	
	/** 创建时间 */
	private Date createdTime;
	
	/** 最近修改人id */
	private int lastOperatorId;
	
	/** 最近修改时间 */
	private Date lastOperatedTime;

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getLastOperatorId() {
		return lastOperatorId;
	}

	public void setLastOperatorId(int lastOperatorId) {
		this.lastOperatorId = lastOperatorId;
	}

	public Date getLastOperatedTime() {
		return lastOperatedTime;
	}

	public void setLastOperatedTime(Date lastOperatedTime) {
		this.lastOperatedTime = lastOperatedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
