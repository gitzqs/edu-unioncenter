package com.zqs.dao.user;

import com.zqs.dao.basics.IBaseMapper;
import com.zqs.model.user.User;

public interface IUserMapper extends IBaseMapper<User>{
	
	/**
	 * 验证用户是否注册
	 * 
	 * @param 
	 * @return int
	 */
	int isExists(String mobile);
	
	/**
	 * 获取用户信息
	 * 
	 * @param 
	 * @return User
	 */
	User load(String mobile);
}
