package com.jxkj.readapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检验邮箱电话号码
 * 
 * @author Administrator
 * 
 */
public class CheckEmailPhoneID {

	/**
	 * 验证电话号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(14[^4,\\D])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		// 正则表达式
		/*
		 * String regex = "^[A-Za-z]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";
		 * return email.matches(regex);
		 */

		// 不适用正则
		if (email == null || "".equals(email))
			return false;
		if (!containsOneWord('@', email) || !containsOneWord('.', email))
			return false;
		String prefix = email.substring(0, email.indexOf("@"));
		String middle = email.substring(email.indexOf("@") + 1,
				email.indexOf("."));
		String subfix = email.substring(email.indexOf(".") + 1);
		System.out.println("prefix=" + prefix + "  middle=" + middle
				+ "  subfix=" + subfix);

		if (prefix == null || prefix.length() > 40 || prefix.length() == 0)
			return false;

		if (middle == null || middle.length() > 40 || middle.length() == 0)
			return false;

		return true;
	}

	// 判断字符串只包含指定的一个字符c
	private static boolean containsOneWord(char c, String word) {
		char[] array = word.toCharArray();
		int count = 0;
		for (Character ch : array) {
			if (c == ch) {
				count++;
			}
		}
		return count == 1;
	}

	// 检查一个字符串是否全部是字母
	private static boolean isAllWords(String prefix) {
		char[] array = prefix.toCharArray();
		for (Character ch : array) {
			if (ch < 'A' || ch > 'z' || (ch < 'a' && ch > 'Z'))
				return false;
		}
		return true;
	}

	// 检查一个字符串是否包含字母和数字
	private static boolean isAllWordsAndNo(String middle) {
		char[] array = middle.toCharArray();
		for (Character ch : array) {
			if (ch < '0' || ch > 'z')
				return false;
			else if (ch > '9' && ch < 'A')
				return false;
			else if (ch > 'Z' && ch < 'a')
				return false;
		}
		return true;
	}

	/**
	 * 判断身份证
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isID(String id) {

		// 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
		Pattern idNum = Pattern
				.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		// 通过Pattern获得Matcher
		Matcher idNumMatcher = idNum.matcher(id);
		if (idNumMatcher.matches()) {
			return true;
		}

		return false;
	}

}
