
package com.zqs.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.zqs.model.code.MessageCode;

/**
 * 时间格式转换
 * 
 * @author qiushi.zhou  
 * @date 2016年10月21日 下午2:27:43
 */
public class DateFormatUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);
    
    public static final String y = "yyyy";
    public static final String ymd = "yyyy-MM-dd";
    public static final String ymd_hms = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    
    private static ThreadLocal<Map<String, DateFormat>> dateFormat = new ThreadLocal<Map<String, DateFormat>>();
    
    
    public static DateFormat getDateFormat(String pattern) {
        Map<String, DateFormat> dfMap = dateFormat.get();
        if (dfMap == null) {
            dfMap = new HashMap<String, DateFormat>();
            dateFormat.set(dfMap);
        }
        if (StringUtils.isEmpty(pattern)) throw new IllegalArgumentException("date format patter must not be null or empty!");

        DateFormat df = dfMap.get(pattern);
        if (df == null) {
            df = new SimpleDateFormat(pattern);
            dfMap.put(pattern, df);
        }
        return dfMap.get(pattern);
    }
    
    public static String format(Date date, String pattern) {
        DateFormat df = getDateFormat(pattern);
        return df.format(date);
    }
    
    public static Date parse(String source, String pattern) {
        DateFormat df = getDateFormat(pattern);
        try {
            return df.parse(source);
        } catch (ParseException e) {
            logger.error("can't parse source: [{}] to date! catched ParseException:\n\t{}", new Object[]{source, e.getLocalizedMessage()});
        } catch (Exception e) {
            logger.error("can't parse source: [{}] to date! catched Exception:\n\t{}", new Object[]{source, e.getLocalizedMessage()});
        }
        return null;
    }
    
    /**
     * 时间+addIndex个分钟或小时
     * 
     * @param index 1:年;2:月;3:日;4:时;5:分;6:秒
     * @return Date
     */
    public static Date add(Date date, int index, int addIndex){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int type = Calendar.YEAR;
    	switch(index){
    	case 2 :
    		type = Calendar.MONTH;
    		break;
    	case 3 :
    		type = Calendar.DAY_OF_YEAR;
    		break;
    	case 4 :
    		type = Calendar.HOUR_OF_DAY;
    		break;
    	case 5 :
    		type = Calendar.MINUTE;
    		break;
    	case 6 :
    		type = Calendar.SECOND;
    		break;
    	}
    	cal.add(type, addIndex);
    	
    	return cal.getTime();
    }
    
    public static void main(String args[]){
    	MessageCode mc = new MessageCode();
    	System.out.println(mc.getEndTime());
    }
}
