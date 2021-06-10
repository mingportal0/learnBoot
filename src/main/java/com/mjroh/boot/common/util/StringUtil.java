package com.mjroh.boot.common.util;

public class StringUtil {

	/**
	  * @Method Name : checkNull
	  * @작성일 : 2021. 6. 9.
	  * @작성자 : mjroh
	  * @Method 설명 : 인자값이 null 인경우 공백을 리턴한다.
	  * @param str : 입력스트링
	  * @return
	 */
	public static String checkNull(String str) {
		if (str == null || "null".equals(str.trim()))
			return "";
		return str;
	}
	
	/**
	 * 
	  * @Method Name : checkReplaceStr
	  * @작성일 : 2021. 6. 9.
	  * @작성자 : mjroh
	  * @Method 설명 : 인자값이 null 인경우 defaultStr을 리턴한다.
	  * @param str : 입력스트링
	  * @param defaultStr : 기본값
	  * @return
	 */
	public static String checkReplaceStr(String str, String defaultStr) {
		if (str == null || str.equals("") || str.equals("null"))
			return defaultStr;
		else
			return str.trim();
	}
	
	/**
	 * 
	  * @Method Name : parseInt
	  * @작성일 : 2021. 6. 9.
	  * @작성자 : mjroh
	  * @Method 설명 : 파라미터로 들어온 String을 int로 변환시켜주는 Method
	  * @param str : 입력스트링
	  * @param defaultData : 기본값
	  * @return
	 */
	public static int parseInt(String str, int defaultData) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defaultData;
		}
	}
	
	/**
	 * 
	  * @Method Name : parseInt
	  * @작성일 : 2021. 6. 9.
	  * @작성자 : mjroh
	  * @Method 설명 : 파라미터로 들어온 String을 int로 변환시켜주는 Method
	  * @param str : 입력스트링
	  * @return
	 */
	public static int parseInt(String str) {
		return parseInt(str, 0);
	}
	
	/**
	 * 
	  * @Method Name : booleanValue
	  * @작성일 : 2021. 6. 9.
	  * @작성자 : mjroh
	  * @Method 설명 : 파라미터로 들어온 String을 boolean으로 변환시켜주는 Method
	  * @param str : 입력스트링
	  * @param initValue : 기본값
	  * @return
	 */
	public static boolean booleanValue(String str, boolean initValue) {
		try {
			return Boolean.parseBoolean(str);
		} catch (Exception e) {
			return initValue;
		}
	}

	/**
	 * 
	  * @Method Name : booleanValue
	  * @작성일 : 2021. 6. 9.
	  * @작성자 : mjroh
	  * @Method 설명 : 파라미터로 들어온 String을 boolean으로 변환시켜주는 Method
	  * @param str : 입력스트링
	  * @return
	 */
	public static boolean booleanValue(String str) {
		return booleanValue(str, false);
	}
}
