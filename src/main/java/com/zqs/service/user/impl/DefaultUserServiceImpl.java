package com.zqs.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zqs.dao.code.IMessageCodeMapper;
import com.zqs.dao.user.IUserMapper;
import com.zqs.model.basics.ReturnCode;
import com.zqs.model.basics.ReturnObject;
import com.zqs.model.basics.e.EBaseStatus;
import com.zqs.model.basics.e.EMessageCodeType;
import com.zqs.model.basics.e.EUserStatus;
import com.zqs.model.code.MessageCode;
import com.zqs.model.user.User;
import com.zqs.service.user.IUserService;
import com.zqs.utils.date.DateFormatUtils;
import com.zqs.utils.json.JacksonUtils;
import com.zqs.utils.string.StringUtils;
@Service("userService")
public class DefaultUserServiceImpl implements IUserService {
	
	private Logger logger = LoggerFactory.getLogger(DefaultUserServiceImpl.class);
	
	@Autowired
	private IUserMapper userMapper;
	@Autowired
	private IMessageCodeMapper messageCodeMapper;
	
	@Override
	public String register(User user) {
		ReturnObject returnObject = new ReturnObject();
		String returnCode = ReturnCode.SUCCESS_CODE;
		String returnMsg = ReturnCode.SUCCESS_MSG;
		
		//1、获取参数
		String mobile = user.getUserMobile();  //手机号
		String pwd = user.getUserPwd();  //密码
		String pwdAgain = user.getUserPwdAgain();  //重复密码
		String messageCode = user.getMessageCode();  //短信验证码
		Date date = new Date();  //当前时间
		//2、验证参数
		if(!StringUtils.isEmpty(mobile) && !StringUtils.isEmpty(pwd)
				&& !StringUtils.isEmpty(messageCode)){
			try{
				//3、验证用户是否已经注册
				int isExists = userMapper.isExists(mobile);
				if(isExists == 0){
					//4、验证2次密码是否正确
					if(pwd.equals(pwdAgain)){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("mobile", mobile);
						map.put("type", EMessageCodeType.REGISTER_CODE);
						MessageCode mc = messageCodeMapper.load(map);
						//5、验证验证码是否过期
						if(mc != null){
							if( date.before(mc.getEndTime())){
								//6.1、验证验证码是否正确
								if(messageCode.equals(mc.getCode())){
									//7、新增用户
									user.setUserPwd(DigestUtils.md5Hex(pwd));
									user.setRegTime(date);
									user.setErrorNum(5);
									user.setStatus(EUserStatus.ACTIVE);
									userMapper.save(user);
									//8、更新 验证码状态
									mc.setStatus(EBaseStatus.UN_ACTIVE);
									messageCodeMapper.update(mc);
								}else{
									returnCode = ReturnCode.INCORRECT_MESSAGE_CODE;
									returnMsg = ReturnCode.INCORRECT_MESSAGE_MSG;
								}
							}else{
								//6.2、验证码已过期，更新状态
								mc.setStatus(EBaseStatus.UN_ACTIVE);
								messageCodeMapper.update(mc);
								returnCode = ReturnCode.MESSAGE_OVERDUE_CODE;
								returnMsg = ReturnCode.MESSAGE_OVERDUE_MSG;
							}							
						}else{
							returnCode = ReturnCode.NO_MESSAGE_CODE;
							returnMsg = ReturnCode.NO_MESSAGE_MSG;
						}

					}else{
						returnCode = ReturnCode.TWICE_PWD_CODE;
						returnMsg = ReturnCode.TWICE_PWD_MSG;
					}
				}else{
					returnCode = ReturnCode.USER_EXISTS_CODE;
					returnMsg = ReturnCode.USER_EXISTS_MSG;
				}
			}catch(Exception e){
				logger.error("注册用户出错,[{}]",e);
				returnCode = ReturnCode.DATABASE_OPERATION_FAILED_CODE;
				returnMsg = ReturnCode.DATABASE_OPERATION_FAILED_MSG;
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			
			
		}else{
			returnCode = ReturnCode.EMPTY_PARAMS_CODE;
			returnMsg = ReturnCode.ENPTY_PARAMS_MSG;
		}
		returnObject.setReturnCode(returnCode);
		returnObject.setReturnMsg(returnMsg);
		return JacksonUtils.object2json(returnObject);
	}

	@Override
	public String login(User user) {
		ReturnObject returnObject = new ReturnObject();
		String returnCode = ReturnCode.SUCCESS_CODE;
		String returnMsg = ReturnCode.SUCCESS_MSG;
		
		//1、获取参数
		String mobile = user.getUserMobile();  //手机号
		String pwd = user.getUserPwd();  //密码
		Date date = new Date();  //当前时间
		//2、验证参数
		if(!StringUtils.isEmpty(mobile)
				&& !StringUtils.isEmpty(pwd)){
			try{
				//3、获取帐号信息
				User ur = userMapper.load(mobile);
				if(ur != null){
					//4、验证帐号状态
					if(ur.getStatus() != EUserStatus.UN_ACTIVE){
						if(ur.getStatus() == EUserStatus.ACTIVE 
								|| (ur.getStatus() == EUserStatus.FROZEN
								  && ur.getFrozenTime().before(date))){
							if(ur.getStatus() == EUserStatus.FROZEN
								  && ur.getFrozenTime().before(date)){
								//帐号待解冻
								if(pwd.equals(ur.getUserPwd())){
									//成功登录
									user = new User();
									user.setUserMobile(mobile);
									user.setErrorNum(5);
									user.setLastLoginTime(date);
									user.setStatus(EUserStatus.ACTIVE);
									userMapper.update(user);
								}else{
									//密码错误
									user = new User();
									user.setUserMobile(mobile);
									user.setErrorNum(4);
									user.setStatus(EUserStatus.ACTIVE);
									userMapper.update(user);
									returnCode = ReturnCode.ERROR_PASSWORD_CODE;
									returnMsg = ReturnCode.ERROR_PASSWORD_MSG 
											+ ",再输入错误4次，帐号将冻结3个小时";
								}
							}else{
								//帐号正常
								if(pwd.equals(ur.getUserPwd())){
									//成功登录
									user = new User();
									user.setUserMobile(mobile);
									user.setErrorNum(5);
									user.setLastLoginTime(date);
									userMapper.update(user);
									
								}else{
									//验证当前错误次数
									if( ur.getLastErrorTime() != null 
											&& DateFormatUtils.add(ur.getLastErrorTime(), 4, 24).before(date)){
										user = new User();
										user.setUserMobile(mobile);
										user.setErrorNum(4);
										user.setLastErrorTime(date);
										userMapper.update(user);
										returnCode = ReturnCode.ERROR_PASSWORD_CODE;
										returnMsg = ReturnCode.ERROR_PASSWORD_MSG 
												+ ",再输入错误4次，帐号将冻结3个小时";
									}else{
										if(ur.getErrorNum() == 1){
											user = new User();
											user.setUserMobile(mobile);
											user.setErrorNum(ur.getErrorNum() - 1);
											user.setFrozenTime(DateFormatUtils.add(date, 4, 3));
											user.setLastErrorTime(date);
											user.setStatus(EUserStatus.FROZEN);
											userMapper.update(user);
											returnCode = ReturnCode.USER_FROZEN_CODE;
											returnMsg = ReturnCode.USER_FROZEN_MSG 
													+ ",将于" + DateFormatUtils.format(DateFormatUtils.add(date, 4, 3), DateFormatUtils.ymd_hms)
													+ "解除冻结";
										}else{
											user = new User();
											user.setUserMobile(mobile);
											user.setErrorNum(ur.getErrorNum() - 1);
											user.setLastErrorTime(date);
											userMapper.update(user);
											returnCode = ReturnCode.ERROR_PASSWORD_CODE;
											returnMsg = ReturnCode.ERROR_PASSWORD_MSG 
													+ ",再输入错误" + (ur.getErrorNum() - 1) + "次，帐号将冻结3个小时";
										}										
									}

								}
							}
						}else{
							returnCode = ReturnCode.USER_FROZEN_CODE;
							returnMsg = ReturnCode.USER_FROZEN_MSG
									+ ",将于" + DateFormatUtils.format(ur.getFrozenTime(), DateFormatUtils.ymd_hms)
									+ "解除冻结";
						}
					}else{
						returnCode = ReturnCode.USER_UN_ACTIVE_CODE;
						returnMsg = ReturnCode.USER_UN_ACTIVE_MSG;
					}
				}else{
					returnCode = ReturnCode.USER_NOT_EXISTS_CODE;
					returnMsg = ReturnCode.USER_NOT_EXISTS_MSG;
				}				
			}catch(Exception e){
				logger.error("登录失败,[{}]",e);
				returnCode = ReturnCode.DATABASE_OPERATION_FAILED_CODE;
				returnMsg = ReturnCode.DATABASE_OPERATION_FAILED_MSG;
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}else{
			returnCode = ReturnCode.EMPTY_PARAMS_CODE;
			returnMsg = ReturnCode.ENPTY_PARAMS_MSG;
		}
		returnObject.setReturnCode(returnCode);
		returnObject.setReturnMsg(returnMsg);
		return JacksonUtils.object2json(returnObject);
	}

}
