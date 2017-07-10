package com.zqs.service.course.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zqs.dao.course.ICourseInfoMapper;
import com.zqs.model.basics.ReturnCode;
import com.zqs.model.basics.ReturnObject;
import com.zqs.model.basics.e.EBaseStatus;
import com.zqs.model.course.CourseInfo;
import com.zqs.service.course.ICourseService;
import com.zqs.utils.json.JacksonUtils;
import com.zqs.utils.string.StringUtils;
@Service("courseService")
public class DefaultCourseServiceImpl implements ICourseService {
	
	private Logger logger = LoggerFactory.getLogger(DefaultCourseServiceImpl.class);
	@Autowired
	private ICourseInfoMapper courseInfoMapper;
	@Override
	public String loadCourse(CourseInfo course) {
		ReturnObject returnObject = new ReturnObject();
		String returnCode = ReturnCode.SUCCESS_CODE;
		String returnMsg = ReturnCode.SUCCESS_MSG;
		
		if(course.getId() != 0){
			
		}else{
			returnCode = ReturnCode.EMPTY_PARAMS_CODE;
			returnMsg = ReturnCode.ENPTY_PARAMS_MSG;
		}
		return null;
	}
	
}
