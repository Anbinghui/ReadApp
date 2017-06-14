package com.jxkj.readapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 判断字符串是否是數字
	 * 
	 * @author lvliuyan
	 * */

	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private final static Pattern phone = Pattern
			.compile("^((13[0-9])|(15[0,0-9])|(18[0,0-9]))\\d{8}$");

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	// 打印所有的 intent extra 数据
	public static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
		}
		return sb.toString();
	}

	// 获取七十到八十的随机数
	public static String getRandom() {
		int randomMax = 80;
		int randomMim = 75;
		int random = 0;
		while (random < randomMax && randomMim > random) {
			random = (int) Math.round(Math.random() * (randomMax - random)
					+ random);
		}
		return random + "";
	}

	/**
	 * 判断数组非空
	 * 
	 * @author lvliuyan
	 * */

	public static boolean noArrayNull(String... str) {
		boolean isNull = true;
		for (int i = 0; i < str.length; i++) {
			if (StringUtil.isEmpty(str[i])) {
				isNull = false;
			}
		}
		return isNull;
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Calendar calender) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

		int w = calender.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 获取当前日期累加一周<br>
	 * 
	 * @param dt
	 * @return 当前日期开始一周时间
	 */
	public static String[] getDate() {
		Calendar calender = Calendar.getInstance();
		String[] list = new String[7];
		for (int i = 0; i < list.length; i++) {
			list[i] = calender.get(calender.MONTH) + 1 + "月"
					+ calender.get(calender.DATE) + "日  "
					+ StringUtil.getWeekOfDate(calender);

			calender.add(Calendar.DATE, 1);
		}

		return list;
	}

	/***
	 * 获取当天的日期
	 * @return
     */
	public static String getTodayDateStr() {
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 判断是否是手机号
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		boolean isValid = false;
		String expression = "^1[3|5|8][0-9]{9}$";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * 判断字符串是否包含中文
	 * 
	 * @author lvliuyan
	 * */
	public static final boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	private static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	public static int strToInt(String number) {
		int n = 0;
		try {
			n = Integer.valueOf(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return n;
	}

	public static BigDecimal strToBigDecimal(String number) {
		BigDecimal n = null;
		try {
			n = new BigDecimal(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return n;
	}

	public static double strToDouble(String number) {
		double n = 0l;
		try {
			n = Double.valueOf(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return n;
	}

	/**
	 * 根据毫秒值返回字符串.e.g."0天0小时0分钟0秒"
	 * 
	 * @param millisSecond
	 * @return
	 */
	public static String millisToString(long millisSecond) {
		int s = 1000;
		int m = 60 * s;
		int h = 60 * m;
		int d = 24 * h;
		StringBuffer sb = new StringBuffer();
		if (millisSecond / d > 0) {
			sb.append(millisSecond / d);
			sb.append("天");
		}
		sb.append(millisSecond % d / h);
		sb.append("小时");
		sb.append(millisSecond % d % h / m);
		sb.append("分钟");
		sb.append(millisSecond % d % h % m / s);
		sb.append("秒");
		return sb.toString();
	}

	/***
	 * 将输入金额num转换为汉字大写格式
	 * 
	 * @param num
	 *            输入金额（小于10000000）
	 * @return 金额的大写格式
	 */
	public static String transferPriceToHanzi(String number) {
		BigDecimal num;
		if (TextUtils.isEmpty(number.trim())) {
			return "零";
		} else if (number.startsWith("-")) {
			return "输入金额不能为负数";
		} else {
			num = new BigDecimal(number.trim());
		}
		String title = "人民币:";
		String[] upChinese = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌",
				"玖", };
		String[] upChinese2 = { "分", "角", "圆", "拾", "佰", "仟", "萬", "拾", "佰",
				"仟", "亿", "拾", "佰", "仟", "兆" };
		StringBuffer result = new StringBuffer();
		int count = 0;
		int zeroflag = 0;
		boolean mantissa = false;
		if (num.compareTo(BigDecimal.ZERO) < 0) {
			// 输入值小于零
			return "输入金额不能为负数";
		}
		if (num.compareTo(BigDecimal.ZERO) == 0) {
			// 输入值等于零
			return "零";
		}
		if (String.valueOf(num).length() > 12) { // 输入值过大转为科学计数法本方法无法转换
			return "您输入的金额过大";
		}
		BigDecimal temp = num.multiply(BigDecimal.TEN.pow(2));
		BigDecimal[] divideAndRemainder = temp
				.divideAndRemainder(BigDecimal.TEN.pow(2));
		if (divideAndRemainder[1].compareTo(BigDecimal.ZERO) == 0) {
			// 金额为整时
			if (temp.compareTo(BigDecimal.ZERO) == 0)
				return "您输入的金额过小";
			// 输入额为e:0.0012小于分计量单位时
			result.insert(0, "整");
			temp = temp.divide(BigDecimal.TEN.pow(2));
			count = 2;
			mantissa = true;
		}
		while (temp.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] divideAndRemainder2 = temp
					.divideAndRemainder(BigDecimal.TEN);
			BigDecimal t = divideAndRemainder2[1];
			// 取得最后一位
			if (t.compareTo(BigDecimal.ZERO) != 0) {
				// 最后一位不为零时
				if (zeroflag >= 1) {
					// 对该位前的单个或多个零位进行处理
					if (((!mantissa) && count == 1)) {
						// 不是整数金额且分为为零
					} else if (count > 2 && count - zeroflag < 2) {
						// 输入金额为400.04小数点前后都有零

						result.insert(1, "零");
					} else if (count > 6 && count - zeroflag < 6 && count < 10) {
						// 万位后为零且万位为零
						if (count - zeroflag == 2) {
							// 输入值如400000
							result.insert(0, "萬");
						} else {
							result.insert(0, "萬零");
							// 输入值如400101
						}
					} else if (count > 10 && count - zeroflag < 10) {
						if (count - zeroflag == 2) {
							result.insert(0, "亿");
						} else {
							result.insert(0, "亿零");
						}
					} else if (((count - zeroflag) == 2)) {
						// 个位为零
					} else if (count > 6 && count - zeroflag == 6 && count < 10) { // 以万位开始出现零如4001000
						result.insert(0, "萬");
					} else if (count == 11 && zeroflag == 1) {
						result.insert(0, "亿");
					} else {
						result.insert(0, "零");
					}
				}
				result.insert(0, upChinese[t.intValue()] + upChinese2[count]);
				zeroflag = 0;
			} else {
				if (count == 2) {
					result.insert(0, upChinese2[count]);
					// 个位为零补上"圆"字
				}
				zeroflag++;
			}
			BigDecimal[] divideAndRemainder3 = temp
					.divideAndRemainder(BigDecimal.TEN);
			temp = divideAndRemainder3[0];
			System.out.println("count=" + count + "---zero=" + zeroflag
					+ "----" + result.toString());
			count++;
			if (count > 14)
				break;
		}
		return title + result.toString();
	}

	// /**
	// * 判断字符串是否为手机号
	// *
	// * @author 吕柳燕
	// * @param phoneNumber
	// * 判断的字符串
	// * */
	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;
		/*
		 * 可接受的电话格式有：
		 */
		String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
		/*
		 * 可接受的电话格式有：
		 */
		String expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);

		Pattern pattern2 = Pattern.compile(expression2);
		Matcher matcher2 = pattern2.matcher(inputStr);
		if (matcher.matches() || matcher2.matches()) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * 判断字符串是否为邮箱
	 * 
	 * @author 吕柳燕
	 * @param email
	 *            判断的字符串
	 * */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static String removeAllSpace(String str) {
		String tmpstr = str.replace(" ", "");
		tmpstr = tmpstr.replace("+86", "");
		tmpstr = tmpstr.replace("-", "");
		return tmpstr;
	}

	/**
	 * 判断当前是否有网络
	 * 
	 * @author lvliuyan
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 根据手机分辨率从 px(像素) 单位 转成 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机分辨率从 dp 单位 转成 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 是否是身份证号
	 */
	public static boolean isCard(String s_aStr) {
		String str = "\\d{17}[0-9a-zA-Z]|\\d{14}[0-9a-zA-Z]";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(s_aStr);
		return m.matches();
	}

	public static void ToastTool(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}

	public static float getDensity(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.density;
	}

	public static float getDensityWdith(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.widthPixels;
	}

	public static void dismiss(PopupWindow p) {
		if (p != null && p.isShowing()) {
			p.dismiss();
		}
	}

	public static void call(Context context, String phone) {
		if (StringUtil.isPhoneNumber(phone)) {
			Intent phoneintent = new Intent(Intent.ACTION_CALL,
					Uri.parse("tel:" + phone));
			context.startActivity(phoneintent);
		} else {
			StringUtil.ToastTool(context, "手机格式不对");
		}
	}

	public static void startUrl(Context context, String url) {
		Uri uri = Uri.parse(url);
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(it);
	}

	public static String getAppSecret(Context context) {
		ApplicationInfo applicationInfo = null;
		String appSecret = null;
		try {
			applicationInfo = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
			appSecret = applicationInfo.metaData.getString("app_secret");
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return appSecret;
	}

	/**
	 * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 */
	public static boolean isEmpty(CharSequence input) {
		if (input == null || "".equals(input) || "null".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 */
	public static boolean isEmail(CharSequence email) {
		if (isEmpty(email))
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 判断是不是一个合法的手机号码
	 */
	public static boolean isPhone(CharSequence phoneNum) {
		if (isEmpty(phoneNum))
			return false;
		return phone.matcher(phoneNum).matches();
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * String转long
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * String转double
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static double toDouble(String obj) {
		try {
			return Double.parseDouble(obj);
		} catch (Exception e) {
		}
		return 0D;
	}

	/**
	 * 字符串转布尔
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * byte[]数组转换为16进制的字符串。
	 * 
	 * @param data
	 *            要转换的字节数组。
	 * @return 转换后的结果。
	 */
	public static final String byteArrayToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder(data.length * 2);
		for (byte b : data) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase(Locale.getDefault());
	}

	/**
	 * 16进制表示的字符串转换为字节数组。
	 * 
	 * @param s
	 *            16进制表示的字符串
	 * @return byte[] 字节数组
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] d = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return d;
	}

	public static void dissPw(PopupWindow pw) {
		if (pw != null && pw.isShowing()) {
			pw.dismiss();
		}
	}

	public static void setDrawbleLeft(Context context, TextView view, int rsd) {
		Drawable drawable = context.getResources().getDrawable(rsd);
		// / 这一步必须要做,否则不会显示.
		drawable.setBounds(0, 0, drawable.getMinimumWidth(),
				drawable.getMinimumHeight());
		view.setCompoundDrawables(drawable, null, null, null);
	}

	public static void setDrawbleTop(Context context, TextView view, int rsd) {
		Drawable drawable = context.getResources().getDrawable(rsd);
		// / 这一步必须要做,否则不会显示.
		drawable.setBounds(0, 0, drawable.getMinimumWidth(),
				drawable.getMinimumHeight());
		view.setCompoundDrawables(null, drawable, null, null);
	}

	public static String removeBiFuhao(String str) {
		String tmpstr = str.replace(" ", "");
		tmpstr = tmpstr.replace("￥", "");
		tmpstr = tmpstr.replace("$", "");
		tmpstr = tmpstr.replace("元", "");
		return tmpstr;
	}

	public static void hideSoft(Activity context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(context.getWindow().getDecorView()
				.getWindowToken(), 0);
	}

	/**
	 * 给参数返回指定小数点后几位的四舍五入
	 * 
	 * @param sourceData
	 *            传入的要舍取的元数据
	 * @param str
	 *            取舍的格式（主要用到"#.0"的格式，此为小数点后1位；"#.00"为小数点后2位，以此类推）
	 * @return 舍取后的 数据
	 */
	public static double getDouble(double sourceData, String sf) {
		DecimalFormat df = new DecimalFormat(sf);
		String str = df.format(sourceData);
		return Double.parseDouble(str);
	}

	/**
	 * 给参数返回指定小数点后 a 位的四舍五入
	 * 
	 * @param sourceData
	 *            要取舍的原数据
	 * @param a
	 *            小数点 后的 位数（如：10：小数点后1位；100：小数据后2位以此类推）
	 * @return 舍取后的 数据
	 */
	public static float getFloatRound(double sourceData, int a) {
		int i = (int) Math.round(sourceData * a); // 小数点后 a 位前移，并四舍五入
		float f2 = (float) (i / (float) a); // 还原小数点后 a 位
		return f2;
	}

	/**
	 * 获取url中的 key=1类的 参数
	 * @param url
	 * @return
	 */
	public static Map<String , String> getUrlHeader(String url){
		Map<String , String> map = new HashMap<>();
		String[] split = url.split("\\?");
		if (split.length > 1){
			String str = split[1];
			int i = 0;
			int start = 0;
			while (i != -1){
				i = str.indexOf("&" , start);
				String s;
				if (i != -1) {
					s = str.substring(start, i);
				}else{
					s = str.substring(start , str.length());
				}
				int i1 = s.indexOf("=");
				if (i1 != -1){
					map.put(s.substring(0 , i1) , s.substring(i1 + 1 , s.length()));
				}
				start = i + 1;
			}
		}
		return map;
	}

	/**
	 * 用 map 拼接 url
	 * @param url
	 * @param map
	 * @return
	 */
	public static String getUrlWithHeader(String url , Map<String , String> map) {
		if (map.size() != 0) {
			StringBuilder builder = new StringBuilder();
			builder.append(url).append("?");
			Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}

			String str = builder.toString();
			int i = str.lastIndexOf("&");
			if (i != -1) {
				return str.substring(0, i);
			}
		}
		return url;
	}

	public static void main(String args[]) {
		System.out.println(getTodayDateStr());
	}
}
