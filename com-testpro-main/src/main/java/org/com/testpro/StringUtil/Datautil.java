package org.com.testpro.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Datautil {

	public final static String formatDefult = "yyyy-MM-dd";

	/**
	 * 
	 * @param format日期格式
	 *            
	 * @param date 日期字符串需满足日期格式
	 *            
	 * @return Date类型数据
	 */
	public static Date StringForDate(String format, String date) {
		Date parse = null;
		try {
			parse = new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			log.error("文本转化为日期失败转化模板是{}，参数是{} ", format, date);
			e.printStackTrace();
		}
		return parse;
	}

	/**
	 * 默认是yyyy-MM-dd
	 * 
	 * @param date 日期时间
	 *           
	 * @return Date类型数据
	 */
	public static Date StringForDate(String date) {
		Date parse = null;
		try {
			parse = new SimpleDateFormat(formatDefult).parse(date);
		} catch (ParseException e) {
			log.error("文本转化为日期失败转化模板是{}，参数是{} ", formatDefult, date);
			e.printStackTrace();
		}
		return parse;
	}

	/**
	 * 
	 * @param format 日期格式
	 * @param date 日期字符串需满足日期格式
	 * @return
	 */
	public static Long StringForLong(String format, String date) {
		return StringForDate(format, date).getTime();
	}

	/**
	 * 
	 * 
	 * @param date 字符日期
	 * @return Long数据
	 */
	public static Long StringForLong(String date) {
		return StringForDate(formatDefult, date).getTime();
	}

	/**
	 *  
	 * @param date
	 * @return 返回默认加一天的时间默认格式为yyyy-MM-dd
	 */
	public static Long StringDatecountToLong(String date) {
		Date stringForDate = StringForDate(formatDefult, date);
		 Calendar cal= Calendar.getInstance();
		 cal.setTime(stringForDate);
		 cal.add(Calendar.DATE, 1);
		return cal.getTime().getTime();
	}
	/**
	 * 
	 * @param date
	 * @param format
	 * @param daysNumber
	 * @return 获取指定的时间及格式加几天的结果
	 */
	public static Long StringDatecountToLong(String date,String format,int daysNumber) {
		Date stringForDate = StringForDate(format, date);
		 Calendar cal= Calendar.getInstance();
		 cal.setTime(stringForDate);
		 cal.add(Calendar.DATE, daysNumber);
		return cal.getTime().getTime();
	}
	
	  /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String DateToTimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
	
	
    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStampTODate(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString);
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
	
    
    public static String LongTimeStampTODate(Long timestamp, String formats) {
  
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
	
}
