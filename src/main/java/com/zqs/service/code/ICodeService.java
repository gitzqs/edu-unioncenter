package com.zqs.service.code;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.transaction.annotation.Transactional;

import com.zqs.model.code.MessageCode;
/**
 * 验证码相关
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 下午1:22:06
 */
@Path("/code")
public interface ICodeService {
	
	/**
	 * 短信验证码(30分钟过期)
	 * 
	 * @param 
	 * @return String
	 */
	@POST
	@Path("/generate")
	@Transactional
	String generate(MessageCode code);
}
