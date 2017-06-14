package com.jxkj.readapp.util;

/**
 * Created by Administrator on 2017/4/21.
 */
public class NumUtil {
    public static String NumberFormat(float f, int m) {
        return String.format("%." + m + "f", f);
    }

    public static String NumberFormatInt(float f, int m) {
        String str= String.format("%." + m + "f", f);
        return str.substring(0,str.length()-3);
    }

    public static float NumberFormatFloat(float f, int m) {
        String strfloat = NumberFormat(f, m);
        return Float.parseFloat(strfloat);
    }

    public static int NumberFormatInt(int f, int m) {
        String strfloat = NumberFormat(f, m);
        return f;
    }

}
