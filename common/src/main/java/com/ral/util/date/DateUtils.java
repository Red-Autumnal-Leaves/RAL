package com.ral.util.date;

import com.ral.util.codec.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 
 * @author victor
 * @desc 时间辅助类
 */
public final class DateUtils {
	
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS);

	private DateUtils(){
		
	}
	
	/**
	 * 
	 * @param date 时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String ConvertToStr(Date date){
		String dataStr = sdf.format(date);
		return dataStr;
	}
	
	/**
	 * 
	 * @param date 时间
	 * @param pattern 时间格式
	 * @return 指定字符串,默认返回yyyy-MM-dd HH:mm:ss格式
	 */
	public static String ConvertToStr(Date date,String pattern){
		if(!StringUtils.isNullOrEmpty(pattern)){
			return convertToStr(date,pattern);
		}
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @param str 时间字符串
	 * @param pattern 格式串
	 * @return 返回时间类型
	 * @throws ParseException 
	 */
	public static Date ConvertToDate(String str,String pattern) throws ParseException{
		if(!StringUtils.isNullOrEmpty(pattern)){
			return convertToDate(str, pattern);
		}
		return sdf.parse(str);
	}
	
	/**
	 * 
	 * @param big 大时间
	 * @param small 小时间
	 * @param sss 毫秒
	 * @return 是已经超过指定毫秒
	 */
	public static boolean isExceedTimes(Date big, Date small,long sss){
		return  big.getTime() - small.getTime() > sss;
	}
	
	/**
	 * 
	 * @param prefix 前缀
	 * @param postfix 后缀
	 * @return 返回年月日时分秒 拼接前缀后缀字符串
	 */
	public static String getDateStr(String prefix , String postfix){
		  Calendar nowtime = new GregorianCalendar();  
		  StringBuffer buffer = new StringBuffer(prefix == null ? "" :prefix);
		  buffer.append(nowtime.get(Calendar.YEAR));
		  buffer.append(nowtime.get(Calendar.MONTH ) +1);
		  buffer.append(nowtime.get(Calendar.DATE));
		  buffer.append(nowtime.get(Calendar.HOUR_OF_DAY));
		  buffer.append(nowtime.get(Calendar.MINUTE));
		  buffer.append(nowtime.get(Calendar.SECOND));
		  buffer.append(nowtime.get(Calendar.MILLISECOND));
		  return buffer.append(postfix == null ? "" :postfix).toString();
	}
	
	/**
	 * 
	 * @param date 时间
	 * @param days 添加天数
	 * @return 新时间
	 */
	public static Date addDay(Date date,int days){
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE,days);
		return calendar.getTime();
	}
	
	/**
	 * 添加或者减少毫秒数
	 * @param date 时间
	 * @param times 添加毫秒数，减少为负数
	 * @return 
	 */
	public static Date subTimes(Date date,long times){
		long dateTime = date.getTime() + times;
		return new Date(dateTime);
	}
	
	
	/**
	 * 获取UTC时间
	 * @param date 
	 * @return
	 */
    public static Date getUTCTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return cal.getTime();
    }
    
    /**
     * UTC时间转为指定时区时间
     *
     * @param utc  UTC时间
     * @param hour 相差小时
     * @return 指定时区时间
     */
    public static Date utc2Local(Date utc, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(utc);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }
    
    /**
     * UTC时间转为GMT+8时间
     *
     * @param utc UTC时间
     * @return GMT+8时间
     */
    public static Date utc2LocalGMT8(Date utc) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(utc);
        cal.add(Calendar.HOUR_OF_DAY, 8);
        return cal.getTime();
    }
	
    /**
     * 判断时间是否为过去时间
     * @param date 时间
     * @return
     */
	public static boolean isOld(Date date){
		return date.getTime() - new Date().getTime() < 0;
	}
	
	private static String convertToStr(Date date,String pattern){
		return new SimpleDateFormat(pattern).format(date);
	}


	public static int calculateDifferenceSeconds(Date date1,Date date2){
		long diff = date1.getTime() - date2.getTime();
		return (int)diff/1000;
	}


	private static Date convertToDate(String source,String pattern) throws ParseException{
		return new SimpleDateFormat(pattern).parse(source);
	}
}
