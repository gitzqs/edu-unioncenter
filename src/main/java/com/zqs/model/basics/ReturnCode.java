package com.zqs.model.basics;
/**
 * 返回代码
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 下午12:54:04
 */
public interface ReturnCode {
	
	/** 操作成功 */
	String SUCCESS_CODE = "0000";
	String SUCCESS_MSG = "操作成功";
	
	/** 发生不可预期错误 */
	String UNKNOWN_ERROR_CODE = "9999";
	String UNKNOWN_ERROR_MSG = "发生不可预期错误";
	
	/** 参数不完整或为空 */
	String EMPTY_PARAMS_CODE = "1000";
	String ENPTY_PARAMS_MSG = "参数不完整或为空";
	
	/** 数据库操作失败 */
	String DATABASE_OPERATION_FAILED_CODE = "1001";
	String DATABASE_OPERATION_FAILED_MSG = "数据库操作失败";
	
	/** 发送短信验证码失败 */
	String SEND_MESSAGE_FAIL_CODE = "2001";
	String SEND_MESSAGE_FAIL_MSG = "发送短信验证码失败";
	
	/** 验证码过期 */
	String MESSAGE_OVERDUE_CODE = "2002";
	String MESSAGE_OVERDUE_MSG = "验证码过期";
	
	/** 验证码不正确 */
	String INCORRECT_MESSAGE_CODE = "2003";
	String INCORRECT_MESSAGE_MSG = "验证码不正确";
	
	/** 验证码未发送 */
	String NO_MESSAGE_CODE = "2004";
	String NO_MESSAGE_MSG = "验证码未发送";
	
	/** 用户已存在 */
	String USER_EXISTS_CODE = "3001";
	String USER_EXISTS_MSG = "用户已存在";
	
	/** 两次输入密码不一致 */
	String TWICE_PWD_CODE = "3002";
	String TWICE_PWD_MSG = "两次输入密码不一致";
	
	/** 用户不存在 */
	String USER_NOT_EXISTS_CODE = "3003";
	String USER_NOT_EXISTS_MSG = "用户不存在";
	
	/** 账户已失效 */
	String USER_UN_ACTIVE_CODE = "3004";
	String USER_UN_ACTIVE_MSG = "账户已失效";
	
	/** 账户已冻结  */
	String USER_FROZEN_CODE = "3005";
	String USER_FROZEN_MSG = "账户已冻结";
	
	/** 密码错误 */
	String ERROR_PASSWORD_CODE = "3006";
	String ERROR_PASSWORD_MSG = "密码错误";
}
