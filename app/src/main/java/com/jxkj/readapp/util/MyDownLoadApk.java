package com.jxkj.readapp.util;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * 已弃用 2017/05/04
 */
@Deprecated
public class MyDownLoadApk {

	public static void MyDownLoad(Context context , String apkUrl) {
		DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//      Uri uri = Uri.parse("http://cloud.jixiangkeji.com/wapxiazai/jixiang1.1.4.apk");
		Uri uri = Uri.parse(apkUrl);
        Request request = new Request(uri);

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
        	 String downPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/download";
             File filePath = new File(downPath);
             if (!filePath.exists()) {
     			filePath.mkdirs();
     		}
            //设置移动网络以及WiFi都可以
        }
        request.setAllowedNetworkTypes(Request.NETWORK_MOBILE
                | Request.NETWORK_WIFI);
         // 设置下载路径和文件名
        request.setDestinationInExternalPublicDir("download", "jixiang.apk");
        request.setDescription("极享新版本");
        request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setMimeType("application/vnd.android.package-archive");
         // 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();
        // 设置为可见和可管理
        request.setVisibleInDownloadsUi(true);
        long refernece = dManager.enqueue(request);
        // 把当前下载的ID保存起来
        SharedPreferences sPreferences = context.getSharedPreferences("newapk", 0);
        sPreferences.edit().putLong("jixiangapk", refernece).commit();
		
	}	
	
}
