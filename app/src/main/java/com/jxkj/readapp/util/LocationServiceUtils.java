package com.jxkj.readapp.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

/**
 * Created by Administrator on 2016/5/13.
 */
public class LocationServiceUtils {

    public static boolean isOpenLocService(final Context context) {

        boolean isGps = false; //判断GPS定位是否启动
        if (context != null) {
            LocationManager locationManager
                    = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null) {
                isGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            }

            if (isGps) {
                return true;
            }
        }

        return false;
    }

    public static boolean isHasLocationRequest(Context context) {
        PackageManager pm = context.getPackageManager();
        boolean finePermission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.ACCESS_FINE_LOCATION", "com.jxkj.plum"));
        boolean normalPermission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.ACCESS_COARSE_LOCATION", "com.jxkj.plum"));
        if (finePermission || normalPermission) {
            return true;
        } else {
            return false;
        }
    }
}
