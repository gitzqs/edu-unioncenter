package com.zqs.service.course;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.zqs.model.course.CourseInfo;

@Path("/course")
public interface ICourseService {
	
	/**
	 * 根据courseId获取具体信息
	 * 
	 * @param 
	 * @return String
	 */
	@POST
	@Path("/loadCourse")
	String loadCourse(CourseInfo course);
}
