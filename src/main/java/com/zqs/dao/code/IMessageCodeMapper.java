package com.zqs.dao.code;

import java.util.Map;

import com.zqs.dao.basics.IBaseMapper;
import com.zqs.model.code.MessageCode;

public interface IMessageCodeMapper extends IBaseMapper<MessageCode>{
	
	/**
	 * 获取当前有效验证码
	 * 
	 * @param 
	 * @return MessageCode
	 */
	MessageCode load(Map<String,Object> map);
}
