package com.zqs.user;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zqs.model.user.User;
import com.zqs.service.user.IUserService;

/**
 * 用户相关测试
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 下午2:46:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml","classpath:spring/spring-mvc.xml"})
public class TestUserService {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 用户注册
	 * 
	 * @param 
	 * @return void
	 */
//	@Test
	public void register() throws Exception{
		User user = new User();
		user.setUserMobile("15205155720");
		user.setUserPwd("123456");
		user.setUserPwdAgain("123456");
		user.setMessageCode("710583");
		System.out.println(userService.register(user));
	}
	
	/**
	 * 用户登录
	 * 
	 * @param 
	 * @return void
	 */
	@Test
	public void login() throws Exception{
		User user = new User();
		user.setUserMobile("15205155720");
		user.setUserPwd(DigestUtils.md5Hex("123456"));
		System.out.println(userService.login(user));
	}
}
