package com.jxkj.readapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * webview 属性
 * 
 * @author 韩庆凯
 * @since 2015年12月24日 11:21:14
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebViewUtil {

	/**
	 * 清除缓存
	 * 
	 * @param context
	 */
	public static void clearCoicke(Context context) {
		CookieSyncManager.createInstance(context);
		CookieSyncManager.getInstance().startSync();
		CookieManager.getInstance().removeAllCookie();
	}

	/**
	 * 清理cache 和历史记录:
	 * 
	 * @param context
	 * @param webView
	 */
	public static void clearHistory(Context context, WebView webView) {
		webView.clearCache(true);
		webView.clearHistory();
	}

	/**
	 * 加载页面，设置属性
	 * 
	 * @param context
	 * @param webView
	 * @param url
	 */
	public static void loadingUrlSetting(Context context, WebView webView,
										 String url) {
		webView.loadUrl(url);
		webView.getSettings().setAllowFileAccess(true);

		// 如果访问的页面中有Javascript，则webview必须设置支持Javascript
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setAppCacheEnabled(true);
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setDatabaseEnabled(true);
		webView.setHorizontalScrollBarEnabled(false);// 水平不显示
		webView.setVerticalScrollBarEnabled(false); // 垂直不显示

	}

	/**
	 * 跳转浏览器
	 */
	public static void toBrowser(WebView webView, final Boolean b) {

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return b;
			}
		});
	}

	/**
	 * 屏蔽掉长按事件 因为webview长按时将会调用系统的复制控件
	 */
	public static void offOnLong(WebView webView) {
		webView.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				return true;
			}

		});
	}

	/**
	 * 加入 flash支持:
	 * 
	 * @param webView
	 * @param url
	 */
	public static void flash(WebView webView, String url) {
		String temp = "<html><body bgcolor=\"" + "black"

		+ "\"> <br/><embed src=\"" + url + "\" width=\"" + "100%"

		+ "\" height=\"" + "90%" + "\" scale=\"" + "noscale"

		+ "\" type=\"" + "application/x-shockwave-flash"

		+ "\"> </embed></body></html>";

		String mimeType = "text/html";

		String encoding = "utf-8";

		webView.loadDataWithBaseURL("null", temp, mimeType, encoding, "");
	}

	/**
	 * 播放音，视频时需在activity中调用
	 * 
	 * @param webView
	 */
	public static void destoryWeb(WebView webView) {
		webView.destroy();
	}

}
