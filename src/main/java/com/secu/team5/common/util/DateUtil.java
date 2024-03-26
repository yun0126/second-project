package com.secu.team5.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	
	//... 은 자바스크립트마냥 넣어도 그만 안넣어도 그만이다.
	public static String getToDate(String...formats) {
		String format = "yyyy-MM-dd HH:mm:ss.SSS";
		if(formats != null && formats.length == 1) {
			format = formats[0];
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		
		return sdf.format(cal.getTime());
	}
	
}
