
package com.zqs.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;

/**
 * 时间格式转换
 * 
 * @author qiushi.zhou  
 * @date 2016年10月21日 下午2:27:43
 */
public class DateFormatUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);
    
    public static final String y = "yyyy";
    public static final String ym = "yyyy-MM";
    public static final String ymd = "yyyy-MM-dd";
    public static final String ymd_h = "yyyy-MM-dd HH";
    public static final String ymd_hm = "yyyy-MM-dd HH:mm";
    public static final String ymd_hms = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMM = "yyyyMM";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMddHHmmssS = "yyyyMMddHHmmssS";
    
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
     * 将yyyy-MM-dd HH:mm:ss格式变为 yyyy-MM-dd格式
     * 
     * @param date
     * @return Date
     */
    public static Date date2Date(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(ymd);
    	Date returnDate = null;
    	try {
    		returnDate = format.parse(format.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return returnDate;
    }
    
    /**
     * 将yyyy-MM-dd HH:mm:ss格式变为 yyyy-MM-dd格式
     * 
     * @param 
     * @return String
     */
    public static String date2Day(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(ymd);
    	return format.format(date);
    }
    
    /**
     * 将yyyy-MM-dd HH:mm:ss格式变为yyyy格式
     * 
     * @param date
     * @return String
     */
    public static String date2Year(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(y);
    	
    	return format.format(date);
    }
        
    /**
     * 将yyyy-MM-dd HH:mm:ss 转变为 yyyy-MM
     * 
     * @param 
     * @return String
     */
    public static String date2Month(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(ym);
    	return format.format(date);
    }
    
    /**
     * 获取yyyyMM格式时间
     * 
     * @param 
     * @return String
     */
    public static String date2YearMonth(Date date){
    	if(date!=null){
    		SimpleDateFormat format = new SimpleDateFormat(yyyyMM);
        	return format.format(date);
    	}
    	return null;
    }
    
    public static String date2YearMonth2(String time){
    	
    	return format(parse(time,"yyyy-MM-dd"),yyyyMM);
    }
    
    
    /**
     * 获取yyyyMMddHHmmss时间格式
     * 
     * @param 
     * @return String
     */
    public static String randomTime(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(yyyyMMddHHmmss);
    	return format.format(new Date());
    }
    
    
    /**
     * 获取时间段里的年月
     * @param startDate
     * @param endDate
     * @return  String []
     */
    public static List<String> getDateInterval (Date startDate,Date endDate){
    	if(startDate==null || endDate==null){
    		return null;
    	}
    	Calendar c =Calendar.getInstance();
    	c.setTime(startDate);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    	List<String> interval= new ArrayList<String>();
    	while(c.getTime().before(endDate)){
    		String str = sdf.format(c.getTime());
    		interval.add(str);
    		c.add(Calendar.MONTH, 1);//进行当前日期月份加1
    	}
    	return interval;
    }
    
    /**
     * 将当前时间拆分成年、月、日
     * 
     * @param 
     * @return List<Integer>
     */
    public static int[] date2List(Date date){
    	int[] arry = new int[3];
    	
    	SimpleDateFormat format = new SimpleDateFormat(ymd);
    	String dateStr = format.format(date);
    	String[] str = dateStr.split("-");
    	if(str !=null && str.length >0){
    		arry[0] = Integer.parseInt(str[0]);
    		arry[1] = Integer.parseInt(str[0]);
    		arry[2] = Integer.parseInt(str[0]);
    	}
    	return arry;
    }
    
    /**
     * 时间转换成UTC格式
     * 
     * @param time1 : '2016-11-23'
     * @param time2 : '05:05'
     * @return Long
     */
    @SuppressWarnings("deprecation")
	public static Long time2UTC(String time1,String time2){
    	Long result = null;
    	
    	try{
    		String temp1[] = time1.split("-");
        	String temp2[] = time2.split(":");
        	result = Date.UTC(Integer.parseInt(temp1[0]), Integer.parseInt(temp1[1]), 
        			Integer.parseInt(temp1[2]), Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]), 0);
    	}catch(Exception e){
    		logger.error("time error,[{}]",e);
    	}
    	
    	return result;
    	
    }
    
    /**
     * 将date时间转换成yyyy-MM-dd HH:mm:ss字符串
     * 
     * @param 
     * @return String
     */
    public static String date2String(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(ymd_hms);
    	return format.format(date);
    }
    
   
    /**
     * 时间转为UTC
     * @param date
     * @return Long
     */
    @SuppressWarnings("deprecation")
	public static Long convertToUTC(Date date){
    	Long UTCTimes = null;
    	if(date!=null){
    		Calendar cal = Calendar.getInstance();
        	cal.setTime(date);
        	UTCTimes = Date.UTC(cal.get(Calendar.YEAR),  cal.get(Calendar.MONTH), 
        			 cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
    	}
    	return UTCTimes;
    }
    
    
    /**
     * 功能描述：返回时
     * @param date 日期
     * @return 返回分钟
     */
    public static int getHour(Date date) {
    	int hour =-1;
    	if(date!=null){
    		Calendar  calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		hour = calendar.get(Calendar.HOUR_OF_DAY);
    	}
        return hour;
    }
    
    /**
     * 功能描述：返回分
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
    	int minute =-1;
    	if(date!=null){
    		Calendar  calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		minute = calendar.get(Calendar.MINUTE);
    	}
        return minute;
    }
    
    /**
     * 计算传入时间与当前时间是否在15分钟之内
     * 
     * @param 
     * @return boolean
     */
    public static boolean calctimeDifference(String time){
    	SimpleDateFormat format = new SimpleDateFormat(ymd_hms);
    	boolean result = false;
    	if(time != null){
    		try {
    			Date date = format.parse(time);
    			long dif = (new Date()).getTime() - date.getTime();
    			long minute = dif/(1000*60);
    			result = (minute < 15);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	return result;
    }
    
    
    /**
     * 计算时间返回int（发送报表邮件专用）
     * 
     * @param 
     * @return int
     */
    public static int timeQuartzCalc(Date date){
    	int result = 0;
    	
    	Date now = date;
    	Date date1_1 = parse(date2Day(date) + " 12:30:00","yyyy-MM-dd HH:mm:ss");
    	Date date1_2 = parse(date2Day(date) + " 13:30:00","yyyy-MM-dd HH:mm:ss");
    	Date date2_1 = parse(date2Day(date) + " 20:30:00","yyyy-MM-dd HH:mm:ss");
    	Date date2_2 = parse(date2Day(date) + " 21:30:00","yyyy-MM-dd HH:mm:ss");
    	Date date3_1 = parse(date2Day(date) + " 06:00:00","yyyy-MM-dd HH:mm:ss");
    	Date date3_2 = parse(date2Day(date) + " 07:30:00","yyyy-MM-dd HH:mm:ss");
    	
    	if(now.after(date1_1) && now.before(date1_2)){
    		result = 2;
    	}else if(now.after(date2_1) && now.before(date2_2)){
    		result = 3;
    	}else if(now.after(date3_1) && now.before(date3_2)){
    		result = 1;
    	}
    	
    	return result;
    }
    
    /**
     * 计算定时任务分类（日发电量）
     * 
     * @param 
     * @return int
     */
    public static int dayCalcPower(Date date){
    	int result = 0;
    	
    	Date now = date;
    	Date date1_1 = parse(date2Day(date) + " 09:10:00","yyyy-MM-dd HH:mm:ss");
    	Date date1_2 = parse(date2Day(date) + " 09:30:00","yyyy-MM-dd HH:mm:ss");
    	Date date2_1 = parse(date2Day(date) + " 15:10:00","yyyy-MM-dd HH:mm:ss");
    	Date date2_2 = parse(date2Day(date) + " 15:30:00","yyyy-MM-dd HH:mm:ss");
    	Date date3_1 = parse(date2Day(date) + " 21:10:00","yyyy-MM-dd HH:mm:ss");
    	Date date3_2 = parse(date2Day(date) + " 21:30:00","yyyy-MM-dd HH:mm:ss");
    	Date date4_1 = parse(date2Day(date) + " 03:10:00","yyyy-MM-dd HH:mm:ss");
    	Date date4_2 = parse(date2Day(date) + " 03:30:00","yyyy-MM-dd HH:mm:ss");
    	Date date5_1 = parse(date2Day(date) + " 05:10:00","yyyy-MM-dd HH:mm:ss");
    	Date date5_2 = parse(date2Day(date) + " 05:30:00","yyyy-MM-dd HH:mm:ss");
    	
    	if(now.after(date1_1) && now.before(date1_2)){
    		result = 3;
    	}else if(now.after(date2_1) && now.before(date2_2)){
    		result = 4;
    	}else if(now.after(date3_1) && now.before(date3_2)){
    		result = 5;
    	}else if(now.after(date4_1) && now.before(date4_2)){
    		result = 1;
    	}else if(now.after(date5_1) && now.before(date5_2)){
    		result = 2;
    	}
    	
    	return result;
    }
    
    /**
     * 计算定时任务分类（月发电量）
     * 
     * @param 
     * @return int
     */
    public static int monthCalcPower(Date date){
    	int result = 0;
    	
    	Date now = date;
    	Date date1_1 = parse(date2Day(date) + " 09:35:00","yyyy-MM-dd HH:mm:ss");
    	Date date1_2 = parse(date2Day(date) + " 09:45:00","yyyy-MM-dd HH:mm:ss");
    	Date date2_1 = parse(date2Day(date) + " 15:35:00","yyyy-MM-dd HH:mm:ss");
    	Date date2_2 = parse(date2Day(date) + " 15:45:00","yyyy-MM-dd HH:mm:ss");
    	Date date3_1 = parse(date2Day(date) + " 21:35:00","yyyy-MM-dd HH:mm:ss");
    	Date date3_2 = parse(date2Day(date) + " 21:45:00","yyyy-MM-dd HH:mm:ss");
    	Date date4_1 = parse(date2Day(date) + " 03:35:00","yyyy-MM-dd HH:mm:ss");
    	Date date4_2 = parse(date2Day(date) + " 03:45:00","yyyy-MM-dd HH:mm:ss");
    	Date date5_1 = parse(date2Day(date) + " 05:35:00","yyyy-MM-dd HH:mm:ss");
    	Date date5_2 = parse(date2Day(date) + " 05:45:00","yyyy-MM-dd HH:mm:ss");
    	
    	if(now.after(date1_1) && now.before(date1_2)){
    		result = 3;
    	}else if(now.after(date2_1) && now.before(date2_2)){
    		result = 4;
    	}else if(now.after(date3_1) && now.before(date3_2)){
    		result = 5;
    	}else if(now.after(date4_1) && now.before(date4_2)){
    		result = 1;
    	}else if(now.after(date5_1) && now.before(date5_2)){
    		result = 2;
    	}
    	
    	return result;
    }
    
    /**
     * 计算定时任务分类（年发电量）
     * 
     * @param 
     * @return int
     */
    public static int yearCalcPower(Date date){
    	int result = 0;
    	
    	Date now = date;
    	Date date1_1 = parse(date2Day(date) + " 09:46:00","yyyy-MM-dd HH:mm:ss");
    	Date date1_2 = parse(date2Day(date) + " 09:55:00","yyyy-MM-dd HH:mm:ss");
    	Date date2_1 = parse(date2Day(date) + " 15:46:00","yyyy-MM-dd HH:mm:ss");
    	Date date2_2 = parse(date2Day(date) + " 15:55:00","yyyy-MM-dd HH:mm:ss");
    	Date date3_1 = parse(date2Day(date) + " 21:46:00","yyyy-MM-dd HH:mm:ss");
    	Date date3_2 = parse(date2Day(date) + " 21:55:00","yyyy-MM-dd HH:mm:ss");
    	Date date4_1 = parse(date2Day(date) + " 03:46:00","yyyy-MM-dd HH:mm:ss");
    	Date date4_2 = parse(date2Day(date) + " 03:55:00","yyyy-MM-dd HH:mm:ss");
    	Date date5_1 = parse(date2Day(date) + " 05:46:00","yyyy-MM-dd HH:mm:ss");
    	Date date5_2 = parse(date2Day(date) + " 05:55:00","yyyy-MM-dd HH:mm:ss");
    	
    	if(now.after(date1_1) && now.before(date1_2)){
    		result = 3;
    	}else if(now.after(date2_1) && now.before(date2_2)){
    		result = 4;
    	}else if(now.after(date3_1) && now.before(date3_2)){
    		result = 5;
    	}else if(now.after(date4_1) && now.before(date4_2)){
    		result = 1;
    	}else if(now.after(date5_1) && now.before(date5_2)){
    		result = 2;
    	}
    	
    	return result;
    }
    
    /**
     * 计算年月日
     * 
     * @param 
     * @return String
     */
    public static String dateCalc(int index){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_MONTH, index);
    	Date date = cal.getTime();
    	return date2Day(date);
    }
    
    /**
     * 计算年月
     * 
     * @param 
     * @return String
     */
    public static String dateCalcMonth(int index){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MONDAY, index);
    	Date date = cal.getTime();
    	return date2Month(date);
    }
    
    /**
     * 计算年
     * 
     * @param 
     * @return String
     */
    public static String dateCalcYear(int index){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.YEAR, index);
    	Date date = cal.getTime();
    	return date2Year(date);
    }
    
    
    /**
     * 获取某个月的每一天
     * @param time
     * @return
     */
    public static String[] getMonthDate(String time){
    	Date date = DateFormatUtils.parse(time, DateFormatUtils.ym);
    	Calendar cal =Calendar.getInstance();
    	cal.setTime(date);
    	String year = cal.get(Calendar.YEAR)+"";
    	int month = cal.get(Calendar.MONTH)+1;
    	String monthStr =(month<10)?("0"+month):(month+"");
    	int count=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    	String [] monthDate = new String[count];
    	for(int i=0;i<count;i++){
    		String dateStr = i+1+"";
    		if(i+1<10){
    			dateStr = "0"+dateStr;
    		}
    		String s = year+"-"+monthStr+"-"+dateStr;
    		monthDate[i]=s;
    	}
    	return monthDate;
    }
    
    /**
     * 获取某年每一月
     * @param time
     * @return
     */
    public static String[] getYearMonth(String time){
    	Date date = DateFormatUtils.parse(time, DateFormatUtils.y);
    	Calendar cal =Calendar.getInstance();
    	cal.setTime(date);
    	String year = cal.get(Calendar.YEAR)+"";
    	String [] monthDate = new String[12];
    	for(int i=0;i<12;i++){
    		String monthStr =(i+1<10)?"0"+(i+1):(i+1+"");
    		String s = year+"-"+monthStr;
    		monthDate[i]=s;
    	}
    	return monthDate;
    }
    
    /**
     * 获取最大或最小小时
     * @param data
     * @return
     */
    public static int getLimitHour(Map<String,Object> data,int limitFlag){
    	int limitHour =-1;
    	if(data!=null&&!data.isEmpty()){
    		int limit=-1;
    		if(limitFlag==0){
    			limit=24;
    		}else{
    			limit=0;
    		}
    		for(String key:data.keySet()){
    			int cur = Integer.parseInt(key.substring(0,key.indexOf(":")));
    			if(limitFlag==0){
    				if(cur<=limit){
    					limit=cur;
    				}
        		}else{
        			if(cur>=limit){
    					limit=cur;
    				}
        		}
    		}
    		limitHour=limit;
    	}
    	return limitHour;
    }
    
    /**获取时间点(小时)
     * @return
     */
    public static String[] getDayHour(int minHour,int maxHour){
    	if(minHour!=-1&&maxHour!=-1&&minHour<=maxHour){
    		String [] hours = new String[maxHour-minHour+1];
    		for(int i=0;i<=maxHour-minHour;i++){
    			int hour = minHour+i;
    			StringBuilder sb = new StringBuilder();
    			if(hour<10){
    				hours[i]=sb.append("0").append(hour+"").append(":00").toString();
    			}else{
    				hours[i]=sb.append(hour+"").append(":00").toString();
    			}
    		}
    		return hours;
    	}
    	return null;
    }
    
    /**获取时间点(分钟)
     * @return
     */
    public static String[] getHourMinitue(Map<String,Object> data){
    	if(data!=null&&!data.isEmpty()){
    		List<String> list = new ArrayList<String>();
    			for(Object obj : data.values()){
    				if(obj!=null){
    					@SuppressWarnings("unchecked")
						Map<String,Object> map = (Map<String,Object>)obj;
    					for(String s:map.keySet()){
    						if(s!=null&&s.toString().indexOf(":00")>-1){
    							if(list.contains(s)==false){
    								list.add(s.toString());
    							}
    						}
    					}
    				}
    			}
    		return (String[]) list.toArray(new String[list.size()]);
    	}
    	return null;
    }
    
    /**
     * 取数据时间段内的年份
     * @param data
     * @return
     */
    public static String[] getTotalYear(List<Map<String,Object>> data){
    	//取最大，最小年份
    	Date maxYear =  null;
    	Date minYear =  null;
    	for(Map<String,Object> map:data){
    		Object obj = map.get("time");
    		if(obj!=null){
    			maxYear =  DateFormatUtils.parse(obj+"", DateFormatUtils.y);
    	    	minYear =  DateFormatUtils.parse(obj+"", DateFormatUtils.y);
    	    	break;
    		}
    	}
    	for(Map<String,Object> map:data){
    		Object obj = map.get("time");
    		if(obj!=null){
    			Date curdate = DateFormatUtils.parse(obj+"", DateFormatUtils.y);
    			if(curdate.after(maxYear)||curdate.equals(maxYear)){
        			maxYear=curdate;
        		}
        		if(curdate.before(minYear)||curdate.equals(minYear)){
        			minYear=curdate;
        		}
    		}
    	}	
    	
    	//补足年份
    	Calendar maxCal = Calendar.getInstance();
    	maxCal.setTime(maxYear);
    	Calendar minCal = Calendar.getInstance();
    	minCal.setTime(minYear);
    	int max = maxCal.get(Calendar.YEAR);
    	int min = minCal.get(Calendar.YEAR);
    	String [] years = new String[max-min+1];
    	for(int i=0;i<=max-min;i++){
    		years[i]=min+i+"";
    	}
		return years;
    }
    
    /**
     * 时间加减
     * 
     * @param 
     * @return String
     */
    public static String hourTime(String time,int index){
    	
    	String nowDate = date2Day(new Date())+" "+time+":00";
    	Date date = parse(nowDate,ymd_hms);
    	
    	return format(new Date(date.getTime() + index),"HH:mm");
    }
    
    public static int timeCompare(String time1,String time2){
    	int index = 0;
    	Date date1 = parse(date2Day(new Date())+" "+time1+":00",ymd_hms);
    	Date date2 = parse(date2Day(new Date())+" "+time2+":00",ymd_hms);
    	int result = (int) (date2.getTime() - date1.getTime());
    	if(result >=0){
    		index = 1;
    	}else{
    		index = 0;
    	}
    	
    	return index;
    }
    
    /**
     * 计算2值相距几个5分钟
     * 
     * @param 
     * @return int
     */
    public static int minuteSub(String time_pre,String time_cur){
    	int result = 0;
    	
    	Date date1 = parse(date2Day(new Date())+" "+time_pre+":00",ymd_hms);
    	Date date2 = parse(date2Day(new Date())+" "+time_cur+":00",ymd_hms);
    	result = (int) ((date2.getTime() - date1.getTime())/300000) ;
    	return result;
    }
    
    /**
     * 判断2个时间哪个离时间节点更近
     * 
     * @param 
     * @return boolean
     */
    public static boolean judgeMinuteSub(String time_pre,String time_cur,String time_st){
    	
    	Date date_pre = parse(date2Day(new Date())+" "+time_pre+":00",ymd_hms);
    	Date date_cur = parse(date2Day(new Date())+" "+time_cur+":00",ymd_hms);
    	Date date_st = parse(date2Day(new Date())+" "+time_st+":00",ymd_hms);
    	
    	int index_pre = (int) ((date_st.getTime() - date_pre.getTime())/60000) ;
    	int index_cur = (int) ((date_cur.getTime() - date_st.getTime())/60000) ;
    	
    	return (index_cur <= index_pre);
    }
    
    /**
     * 天数相加
     * 
     * @param 
     * @return String
     */
    public static String dayAdd(String time,int index){
    	
    	String r = null;
    	int result = Integer.parseInt(time) + index;
    	if(result < 10){
    		r = "0" + result;
    	}else{
    		r = ""+result;
    	}
    	
    	return r;
    }
    
    
    /**
     * 时区换算
     * 
     * @param 
     * @return String
     */
    public static Date timezoneCalc(double index){
    	
    	int timeS = (int) Math.floor(index);
    	double m = index - timeS ;
    	
    	Calendar cal = Calendar.getInstance();
    	if(m == 0.0){
    		cal.add(Calendar.HOUR, 8 - timeS);
    	}else{
    		cal.add(Calendar.MINUTE, (int)(m * 60));
    		cal.add(Calendar.HOUR, 7 - timeS);
    	}
    	return cal.getTime();
    }
    
    /**
     * 时间+天数后的时间
     * 
     * @param time 形如"yyyy-MM-dd"时间
     * @param index 天数
     * @return String
     */
    public static String timeAddTime(String time, int index){
    	Date date = parse(time,ymd);
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(date);
    	cal.add(Calendar.DAY_OF_YEAR, index);
    	
    	return format(cal.getTime(), ymd);
    }
    
    /**
     * 计算2时间相差多少天
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalDay(Date start,Date end){
    	long startMilliseconds = start.getTime();
    	long endMilliseconds = end.getTime();
    	long interval = (endMilliseconds-startMilliseconds)/1000/3600/24;
    	return (int)interval;
    }   
    
    /**
     * 计算2个时间内的每一天
     * @param start
     * @param end
     * @return
     */
    public static List<String> getEveryDayBetweenDates(Date start,Date end){
    	List<String> result = new ArrayList<String>();
    	Calendar sCal = Calendar.getInstance();
    	sCal.setTime(start);
    	Calendar eCal = Calendar.getInstance();
    	eCal.setTime(end);
    	while(sCal.before(eCal)||sCal.equals(eCal)){
    		String temp = DateFormatUtils.format(sCal.getTime(), DateFormatUtils.ymd);
    		result.add(temp);
    		sCal.add(Calendar.DAY_OF_YEAR, 1);
    	}
    	return result;
    }
    
    public static void main(String args[]){
    	System.out.println(timeAddTime("2017-03-01",-0));
    }
    
}
