package com.zqs.service.user;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.transaction.annotation.Transactional;

import com.zqs.model.user.User;
/**
 * 用户相关
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 下午1:21:43
 */
@Path("/user")
public interface IUserService {
	
	/**
	 * 注册
	 * 
	 * @param 
	 * @return String
	 */
	@POST
	@Path("/register")
	@Transactional
	String register(User user);
	
	/**
	 * 登录
	 * 
	 * @param 
	 * @return String
	 */
	@POST
	@Path("/login")
	@Transactional
	String login(User user);
}
