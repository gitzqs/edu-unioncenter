package com.zqs.service.code.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zqs.dao.code.IMessageCodeMapper;
import com.zqs.model.basics.ReturnCode;
import com.zqs.model.basics.ReturnObject;
import com.zqs.model.basics.e.EBaseStatus;
import com.zqs.model.code.MessageCode;
import com.zqs.service.code.ICodeService;
import com.zqs.utils.date.DateFormatUtils;
import com.zqs.utils.json.JacksonUtils;
import com.zqs.utils.message.MessageUtils;
import com.zqs.utils.string.StringUtils;
@Service("codeService")
public class DefaultCodeServiceImpl implements ICodeService {
	
	private Logger logger = LoggerFactory.getLogger(DefaultCodeServiceImpl.class);
	
	@Autowired
	private IMessageCodeMapper messageCodeMapper;
	
	@Override
	public String generate(MessageCode mc) {
		ReturnObject returnObject = new ReturnObject();
		String returnCode = ReturnCode.SUCCESS_CODE;
		String returnMsg = ReturnCode.SUCCESS_MSG;
		
		//1、获取参数
		String mobile = mc.getMobile();  //手机号
		int type = mc.getType();  //短信类别
		//2、验证参数
		if(!StringUtils.isEmpty(mobile) && type != 0){
			try{
				//3、发送验证码
				String validateCode = MessageUtils.sendMessage(mobile);
				if(!StringUtils.isEmpty(validateCode)){
					//4、生成记录
					Date date = new Date();  //当前时间
					mc.setCode(validateCode);
					mc.setBeginTime(date);
					mc.setEndTime(DateFormatUtils.add(date, 5, 30));
					mc.setStatus(EBaseStatus.ACTIVE);
					messageCodeMapper.save(mc);
				}else{
					returnCode = ReturnCode.SEND_MESSAGE_FAIL_CODE;
					returnMsg = ReturnCode.SEND_MESSAGE_FAIL_MSG;
				}
			}catch(Exception e){
				logger.error("发送短信验证码失败,[{}]",e);
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
