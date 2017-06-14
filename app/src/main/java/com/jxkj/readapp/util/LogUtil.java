package com.jxkj.readapp.util;

import android.support.annotation.NonNull;

import com.jiongbull.jlog.JLog;


/**
 * Created by 韩庆凯
 * on 2016年4月20日 09:26:06
 * Updated by zhangming
 * on 2016年11月10日 11:8:18
 */
public class LogUtil {

    public static void i(String msg) {
        JLog.i(msg);
    }

    public static void i(String tag, String msg) {
        JLog.i(tag, msg);
    }

    public static void d(String msg) {
        JLog.d(msg);
    }

    public static void d(String tag, String msg) {
        JLog.d(tag, msg);
    }

    public static void w(String msg) {
        JLog.w(msg);
    }

    public static void w(String tag, String msg) {
        JLog.w(tag, msg);
    }

    public static void v(String msg) {
        JLog.v(msg);
    }

    public static void v(String tag, String msg) {
        JLog.v(tag, msg);
    }

    public static void e(String msg) {
        JLog.e(msg);
    }

    public static void e(String tag, String message, Throwable t) {
        JLog.e(tag, t, message);
    }

    public static void e(Throwable t, String message) {
        JLog.e(t, message);
    }

    public static void e(String tag, @NonNull String message) {
        JLog.e(tag, message);
    }

    public static void de(String tag, @NonNull String message) {
     //   if(BuildConfig.DEBUG)
        JLog.e(tag, message);
    }

    public static void e(String tag, @NonNull Throwable t) {
        JLog.e(tag, t);
    }

    public static void e(@NonNull Throwable t) {
        JLog.e(t);
    }

    public static void json(String msg) {
        JLog.json(msg);
    }

    public static void json(String tag, String msg) {
        JLog.json(tag, msg);
    }

    public static void dd(String tag, String msg) {
        if (msg.length() > 4000) {
            for (int i = 0; i < msg.length(); i += 4000) {
                if (i + 4000 < msg.length())
                    d("segment " + i, msg.substring(i, i + 4000));
                else
                    d("segment " + i, msg.substring(i, msg.length()));
            }
        } else
            d("segments ", msg);
    }
}