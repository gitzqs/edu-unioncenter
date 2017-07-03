package com.zqs.model.course;

import java.util.Date;

import com.zqs.model.basics.REntity;
/**
 * 课程概览信息
 * 
 * @author qiushi.zhou  
 * @date 2017年7月3日 下午4:13:29
 */
public class CourseInfo extends REntity{

	private static final long serialVersionUID = 3072320582091905713L;
	
	/** 课程名称 */
	private String name;
	
	/** 节数 */
	private int nodeNumber;
	
	/** 单价 */
	private double unitPrice;
	
	/** 课程类型 */
	private int categoryId;
	
	/** 级别 */
	private int level;
	
	/** 开始时间 */
	private Date beginTime;
	
	/** 结束时间 */
	private Date endTime;
	
	/** 状态 */
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
