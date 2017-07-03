package com.zqs.model.course;

import com.zqs.model.basics.REntity;
/**
 * 课程具体节
 * 
 * @author qiushi.zhou  
 * @date 2017年7月3日 下午4:20:36
 */
public class CourseDetail extends REntity{

	private static final long serialVersionUID = -8781974961618329113L;
	
	/** 课程id */
	private int courseId;
	
	/** 节-标题 */
	private String title;
	
	/** 节-描述 */
	private String description;
	
	/** 节-视频地址名称 */
	private String url;
	
	/** 观看过人数 */
	private int viewNum;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	
	
}
