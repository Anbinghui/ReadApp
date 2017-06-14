package com.jxkj.readapp.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 弹吐司文字
 * 
 * @author Administrator
 * 
 */
public class ToastUtils {

	public static void ShowToast(Context context, String str) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}

	private static Toast t;
	private static int duration;

	private static void makeText(Context context, String msg, int duration) {
		if (ToastUtils.duration != duration) {
			if (t != null) {
				t.cancel();
			}
			t = Toast.makeText(context, msg, duration);
		} else {
			if (t == null) {
				t = Toast.makeText(context, msg, duration);
			} else {
				t.setText(msg);
			}
		}
		ToastUtils.duration = duration;
		t.show();
	}

	public static void makeText(Context context, int resId, int duration) {
		makeText(context, context.getResources().getString(resId), duration);
	}

	public static void makeShortText(Context context, String msg) {
		makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
	}

	public static void makeShortText(Context context, int resId) {
		makeText(context, resId, Toast.LENGTH_SHORT);
	}

	public static void makeLongText(Context context, String msg) {
		makeText(context, msg, Toast.LENGTH_LONG);
	}

	public static void makeLongText(Context context, int resId) {
		makeText(context, resId, Toast.LENGTH_LONG);
	}
}
