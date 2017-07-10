package com.zqs.utils.string;

import com.zqs.model.course.CourseInfo;


/**
 * 字符串处理
 * 
 * @author qiushi.zhou  
 * @date 2017年6月19日 下午1:15:46
 */
public class StringUtils {
	
	/**
	 * 验证字符串是否为空
	 * 
	 * @param 
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim()) || str.length() == 0;
	}
	
	/**
	 * 判断是否为数字。 是true，否false
	 * 
	 * @param 
	 * @return boolean
	 */
	public static boolean isNumber(String str){
		
		if(!StringUtils.isEmpty(str)){
			for(int i=0; i<str.length(); i++){
				if(!Character.isDigit(str.charAt(i))){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String args[]){
		CourseInfo ci = new CourseInfo();
		System.out.println(ci.getUnitPrice());
	}
}
