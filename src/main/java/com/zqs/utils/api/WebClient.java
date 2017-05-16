package com.zqs.utils.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

/**
 * Created with IntelliJ IDEA.
 * User: chenyunjie
 * Date: 14-5-17
 * Time: 下午4:47
 * To change this template use File | Settings | File Templates.
 */
public class WebClient {
	private static String appkey = "111";
	private static String appid  = "22";
	private static String domain = "33";

	public static void setAppkey(String value){
		appkey = value;
	}
	
	public static void setAppid(String value){
		appid = value;
	}
	
	public static void setDomain(String domain) {
		WebClient.domain = domain;
	}

	/**
	 * 此方法强调在客户端调用时应采用统�?的封装类如WebClient去统�?调用,方法注解参数�?
	 * @param url
	 * @param parameter
	 * @param responseClazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T callRest(String url, Object parameter, Class<T> responseClazz) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(url);
        JSONObject jsonObject = JSONObject.fromObject(parameter);
        jsonObject.put("appid",appid );
        jsonObject.put("domain", domain );
        jsonObject.put("sign",getSign(jsonObject) );
        
        StringEntity input = new StringEntity(jsonObject.toString(),HTTP.UTF_8 );
        input.setContentType("application/json;charset=utf-8");
        postRequest.setEntity(input);
        HttpResponse response = httpClient.execute(postRequest);
        BufferedReader br = new BufferedReader( new InputStreamReader((response.getEntity().getContent())));
        String output;
        StringBuffer bufferedString = new StringBuffer();
        while ((output = br.readLine()) != null) {
            bufferedString.append(output);
        }
        String jsonResponseString = bufferedString.toString();
        if ("".equals(jsonResponseString) || jsonResponseString == null) {
            return null;
        }
//      System.out.println("response string : " + jsonResponseString.toString());
        JSONObject jsonObjectResponse = JSONObject.fromObject(jsonResponseString.toString());
        httpClient.getConnectionManager().shutdown();
        return (T)JSONObject.toBean(jsonObjectResponse, responseClazz, getListTypeClass(responseClazz));
    }
    
    
    private static Map<String, Class> getListTypeClass(Class clazz) {
        Map<String,Class> map = new HashMap<String, Class>();
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            Class fieldClazz = f.getType();
            if (fieldClazz.isPrimitive()){
                continue;
            }
            if (fieldClazz.getName().startsWith("java.lang")) {
                continue;
            }
            if (fieldClazz.isAssignableFrom(List.class)) {
                Type fc = f.getGenericType();
                if (fc == null) continue;
                if (fc instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) fc;
                    Class genericClazz = (Class) pt.getActualTypeArguments()[0];
                    map.put(f.getName(), genericClazz);
                }
            }
        }
        return map;
    }
    
    /**
     * �?易转换类
     * @param s
     * @return
     */
    private static Map<String,String> parserToMap(JSONObject json){
		Map<String,String> map = new HashMap<String,String>();
		Iterator<String> keys=json.keys();
		while(keys.hasNext()){
			String key=(String) keys.next();
			String value=json.get(key).toString();
			if(value.startsWith("{")&&value.endsWith("}")){
				//map.put(key, parserToMap(value));
			}else{
				map.put(key, value);
			}
		}
		return map;
	}
    
    private static String getSign(JSONObject json) {
    	Map<String,String> parasMap = parserToMap( json );
    	return getSign( appkey, parasMap );
    }
    
    private static String getSign(String secret, Map<String, String> params) {
		if (secret == null || "".equals(secret) || params == null) {
			throw new RuntimeException(
					"secret or params is null or blank, please check...");
		}

		StringBuilder sb = new StringBuilder().append(secret);
		String result = null;
		try {
			Set<String> sortedKeys = new TreeSet<String>();
			sortedKeys.addAll(params.keySet());
			for (String key : sortedKeys) {
				sb.append(key).append(params.get(key));
			}
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = byteTohex(md.digest(sb.toString().getBytes("utf-8")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	private static String byteTohex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				sb.append("0").append(stmp);
			} else {
				sb.append(stmp);
			}
		}
		return sb.toString().toUpperCase();

	}
	
	public static String doPostByJson(String url, JSONObject ob) {

		String result = null;

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		// 参数转化
		StringEntity se = null;
		try {
			if (ob != null) {
				se = new StringEntity(ob.toString());
			}
			httppost.setEntity(se);

			// 执行
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static void main(String args[]){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mobileNumber", "18811012138");
		String postStatus;
		try {
			postStatus = callRest("http://localhost:8080/Test/services/rest/test/a",map,String.class);
			System.out.println(postStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
