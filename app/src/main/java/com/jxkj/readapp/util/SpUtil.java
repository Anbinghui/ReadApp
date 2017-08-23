package com.jxkj.readapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * sharedPreference信息保存类
 *
 * @author zhang.zk
 */
public class SpUtil {
    //更新apk
    public static final String DOWNLOAD_APK = "download_apk";
    public static final String BOOK_INFO = "bookinfo";
    public static final String BOOKBEGIN = "bookbegin";
    public static final String BOOKEND = "bookend";
    public static final String BOOK_MARK_END = "book_mark_end";
    public static final String BOOK_MARK_BEGIN = "book_mark_begin";
    public static final String BOOK_IS_NIGHT = "book_is_night";

    //保存书籍的信息
    public static final String FONTSIZE="fontsize";

    public static final String ACCOUNT_INFO = "account_info";
    public static final String IS_FIRST_TOUPDATEAGENT = "is_first_toupdateagent";// 跳转到填写资料ActivityDetailActivity
    public static final String IS_FIRST_SHOWGUDIE = "is_first_showgudie";// 地图指示遮盖
    public static final String IS_FIRST_SHOWGUDIE_SWIPEMASK = "is_first_showgudie_swipemask";// 地图上方滑动蒙板
    public static final String IS_FIRST_LOGIN = "is_first";
    public static final String IS_NEWVERSION = "is_newversion";
    public static final String USER_INFO = "user_info";


    //用户基本信息
    public static final String TOKEN = "token";
    public static final String UID = "uid";
    public static final String MOBILE = "mobile";
    public static final String HEADERURL = "headportraits";
    public static final String MEMBERID = "member_id";
    public static final String ISAGENT = "isagent";
    public static final String MEMBER_NICKNAME = "member_nickname";
    public static final String NICKNAME = "nickname";

    //下载任务id
    public static final String DOWNLOAD_ID = "download_id";
    //最新版本
    public static final String LATEST_VERSION = "latest_version";


    private SharedPreferences sp = null;
    private static SpUtil spUtil = null;

    public static SpUtil init(Context context, String sp_name, int mode) {
        spUtil = new SpUtil(context, sp_name, mode);
        return spUtil;
    }

    private SpUtil(Context context, String sp_name, int mode) {
        super();
        sp = context.getSharedPreferences(sp_name, mode);
    }

    /**
     * 保存String
     *
     * @param key
     * @param value
     * @return
     */
    public boolean put(String key, String value) {
        Editor edit = sp.edit();
        edit.putString(key, value);
        return edit.commit();
    }

    /**
     * 保存boolean
     *
     * @param key
     * @param value
     * @return
     */
    public boolean put(String key, boolean value) {
        Editor edit = sp.edit();
        edit.putBoolean(key, value);
        return edit.commit();
    }

    /**
     * 保存float
     *
     * @param key
     * @param value
     * @return
     */
    public boolean put(String key, float value) {
        Editor edit = sp.edit();
        edit.putFloat(key, value);
        return edit.commit();
    }

    /**
     * 保存long
     *
     * @param key
     * @param value
     * @return
     */
    public boolean put(String key, long value) {
        Editor edit = sp.edit();
        edit.putLong(key, value);
        return edit.commit();
    }

    /**
     * 保存int
     *
     * @param key
     * @param value
     * @return
     */
    public boolean put(String key, int value) {
        Editor edit = sp.edit();
        edit.putInt(key, value);
        return edit.commit();
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, true);
    }

    public String getString(String key) {
        String val;
        try {
            val=sp.getString(key, "");
        } catch (Exception e) {//fk
            val=sp.getInt(key, 0)+"";
        }
        return val;
    }

    public float getFloat(String key) {
        return sp.getFloat(key, 0.0f);
    }

    public long getLong(String key) {
        return sp.getLong(key, 0);
    }

    /**
     * 批量保存
     *
     */
    public void put(HashMap<String, Object> map) {
        if (map != null && map.size() > 0) {
            Editor edit = sp.edit();
            Set<String> set = map.keySet();
            for (String key : set) {
                Object value = map.get(key);
                if (value instanceof String && !TextUtils.isEmpty(value+"")) {
                    edit.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    edit.putInt(key, (Integer) value);
                } else if (value instanceof Boolean) {
                    edit.putBoolean(key, (Boolean) value);
                } else if (value instanceof Float) {
                    edit.putFloat(key, (Float) value);
                } else if (value instanceof Long) {
                    edit.putLong(key, (Long) value);
                } else {
                    LogUtil.d("保存 >>跳过了一些不属于 string,int,boolean,float,long 类型的值.不支持的类型: "+key+"->"+value);
                    // + value.getClass().getSimpleName()
                }
            }
            boolean b = edit.commit();
        }
    }

    public boolean contains(String key) {
        return sp.contains(key);
    }

    public void clear() {
        sp.edit().clear().commit();
    }
}
