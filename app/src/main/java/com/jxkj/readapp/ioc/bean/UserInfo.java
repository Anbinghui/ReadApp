package com.jxkj.readapp.ioc.bean;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.jxkj.readapp.util.SpUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Transient;

import java.util.HashMap;

/**
 * 用户信息载体vo，与单次登录绑定
 */
@Entity(indexes = {
        @Index(value = "desc, mobile DESC", unique = true)
})
public class UserInfo {
    @Id(autoincrement = true)
    private Long id;
    public static String token;
    private String desc;
    public String nickname;
    public String member_nickname;
    public String mobile;//手机号
    public String headportraits;//头像url
    public String member_id;
    @Transient
    public Context context;
    public String uid;
    private double longitude;
    private double latitude;
    private Boolean isagent;//是否是代理商 0不是,1是
    private String sel_id;//
    public static UserInfo bean;


    public String getMember_nickname() {
        return member_nickname;
    }

    public void setMember_nickname(String member_nickname) {
        this.member_nickname = member_nickname;
    }

    public String getSel_id() {
        return sel_id;
    }

    public void setSel_id(String sel_id) {
        this.sel_id = sel_id;
    }

    public Boolean getIsagent() {
        return isagent;
    }

    public void setIsagent(Boolean isagent) {
        this.isagent = isagent;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private String sel_password;//是否设置过支付密码

    public static UserInfo instance(Context context) {
        if (bean == null) {
            bean = new UserInfo(context.getApplicationContext());
        }
        return bean;
    }


    public static String getMockToken() {
        return getToken();
    }

    //TODO complete this
    public static String getToken() {
        if (TextUtils.isEmpty(token)) {
            token = "";
        }
        return token;
    }


    public String getUid() {
        return uid;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    /**
     * 判断用户是否登陆
     *
     * @return
     */
    public boolean isLogin() {

        if (bean == null || TextUtils.isEmpty(bean.token)
             ) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 退出登录
     */
    public static void logout(Context context) {
        clear(context);
        bean = null;
        removeCookie(context);
    }

    /**
     * 清除该应用程序用到的所有cookies
     *
     * @param context
     */
    private static void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 删除帐号信息
     *
     * @param context
     */
    public static void clear(Context context) {
        SpUtil sp = SpUtil
                .init(context, SpUtil.USER_INFO, Context.MODE_PRIVATE);
        sp.clear();
    //    UserDBManager.getInstance(context).closeDB();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSel_password() {
        return sel_password;
    }

    public void setSel_password(String sel_password) {
        this.sel_password = sel_password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Generated(hash = 1737438538)
    public UserInfo(Long id, String desc, String nickname, String member_nickname,
                    String mobile, String headportraits, String member_id, String uid,
                    double longitude, double latitude, Boolean isagent, String sel_id,
                    String sel_password) {
        this.id = id;
        this.desc = desc;
        this.nickname = nickname;
        this.member_nickname = member_nickname;
        this.mobile = mobile;
        this.headportraits = headportraits;
        this.member_id = member_id;
        this.uid = uid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isagent = isagent;
        this.sel_id = sel_id;
        this.sel_password = sel_password;
    }

    private UserInfo(Context context) {
        this.context = context;
        SpUtil sp = SpUtil
                .init(context, SpUtil.USER_INFO, Context.MODE_PRIVATE);
        token = sp.getString(SpUtil.TOKEN);
        uid = sp.getString(SpUtil.UID);
        mobile = sp.getString(SpUtil.MOBILE);
        member_id = sp.getString(SpUtil.MEMBERID);
        headportraits = sp.getString(SpUtil.HEADERURL);
        isagent = sp.getBoolean(SpUtil.ISAGENT);
        member_nickname = sp.getString(SpUtil.MEMBER_NICKNAME);
        nickname = sp.getString(SpUtil.NICKNAME);
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setHeadportraits(String headportraits) {
        this.headportraits = headportraits;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadportraits() {
        return headportraits;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //登录时将信息保存到偏好设置里面
    public static void save(Context context) {
        if (bean == null) {
            instance(context);
        }
        SpUtil sp = SpUtil
                .init(context, SpUtil.USER_INFO, Context.MODE_PRIVATE);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(SpUtil.TOKEN,bean.getToken());
        map.put(SpUtil.UID,bean.getUid());
        map.put(SpUtil.MOBILE,bean.getMobile());
        map.put(SpUtil.HEADERURL,bean.getHeadportraits());
        map.put(SpUtil.ISAGENT,bean.getIsagent());
        map.put(SpUtil.MEMBERID,bean.getMember_id());
        map.put(SpUtil.MEMBER_NICKNAME,bean.getMember_nickname());
        map.put(SpUtil.NICKNAME,bean.getNickname());
        sp.put(map);

    }
}
