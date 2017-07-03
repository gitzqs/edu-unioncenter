package com.zqs.model.course;

import com.zqs.model.basics.REntity;
/**
 * 课程分类
 * 
 * @author qiushi.zhou  
 * @date 2017年7月3日 下午4:14:42
 */
public class CourseCategory extends REntity{

	private static final long serialVersionUID = 776609483465486812L;
	
	/** 课程分类名称 */
	private String name;
	
	/** 分类描述 */
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
