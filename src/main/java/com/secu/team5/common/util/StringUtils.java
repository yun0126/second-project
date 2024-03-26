package com.secu.team5.common.util;

public class StringUtils {
	//확장자 찾는 메소드
	public static String getExt(String name) {
		int idx = name.lastIndexOf(".");
		if(idx == -1) {
			return "";
		}
		return name.substring(idx);
	}
}
