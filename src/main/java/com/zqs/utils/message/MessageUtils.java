package com.zqs.utils.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zqs.utils.json.JacksonUtils;

/**
 * 发送短信验证码。成功返回验证码;失败返回null
 * 
 * @author qiushi.zhou  
 * @date 2017年3月31日 上午9:08:13
 */
public class MessageUtils {
	
	private static Logger logger = LoggerFactory.getLogger(MessageUtils.class);
	
	/** 验证码长度  */
	private static String VALIDATE_LENGTH = "6";
    private static String APPKEY = "a5226ea29dce5817050e8b5705aea1eb";  
    private static String SECRET = "b684a9c06796"; 
    private static String URL = "https://api.netease.im/sms/sendcode.action";

    public static final void main(String[] args) throws IOException {
        String res = sendMessage("1520515572");

        System.out.println(res);
    }
    
    public static String sendMessage(String mobile){
    	String result = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("mobile", mobile));
        params.add(new BasicNameValuePair("codeLen", VALIDATE_LENGTH));

        //UTF-8编码,解决中文问题
        HttpEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        String res = null;
		try {
			res = NIMPost.postNIMServer(URL, entity, APPKEY, SECRET);
		} catch (IOException e) {
			e.printStackTrace();
		}
        @SuppressWarnings("unchecked")
		Map<String,Object> rm = (Map<String,Object>) JacksonUtils.json2map(res);
        if(rm != null && rm.get("code") != null){
        	if(rm.get("code").toString().equals("200")
        			&& rm.get("obj") != null){
        		result = rm.get("obj").toString();
        	}else{
        		if(rm.get("msg") != null){
        			logger.error("发送验证码失败:"+rm.get("msg").toString());
        		}
        	}
        }
        return result;
    }
}
