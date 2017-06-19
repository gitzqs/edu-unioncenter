package com.zqs.model.basics;

import java.io.Serializable;
/**
 * 返回参数
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 下午1:20:32
 */
public class ReturnObject implements Serializable{

	private static final long serialVersionUID = 4826586883118903632L;
	
	/** 返回代码 */
	private String returnCode;
	
	/** 返回信息 */
	private String returnMsg;
	
	/** 返回数据 */
	private Object returnData;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Object getReturnData() {
		return returnData;
	}

	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}
	
	
}
