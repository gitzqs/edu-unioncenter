package com.zqs.model.basics.e;
/**
 * 用户帐号状态
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 上午10:35:25
 */
public interface EUserStatus {
	
	/** 失效 */
	int UN_ACTIVE = 0;
	
	/** 生效 */
	int ACTIVE = 1;
	
	/** 冻结 */
	int FROZEN = 2;
}
