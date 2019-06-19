package com.project.util;

/**
 * 字符串判断工具
 * @author Administrator
 *
 */
public class StringUtil {
	
	/**
	 * 是否为空
	 * @param s
	 */
	public static boolean isEmpty(String s) {
		if(s != null && !"".equals(s.trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 是否不为空
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		if(s == null || "".equals(s.trim()) ) {
			return false;
		}
		return true;
	}
	
}
