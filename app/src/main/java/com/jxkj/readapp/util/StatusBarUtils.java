package com.jxkj.readapp.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jxkj.readapp.R;

import static com.jxkj.readapp.util.DeviceUtils.getStatusBarHeight;


/**
 * Created by An on 2017/8/8.
 * 现在可用的 :
 * setColor,设置状态栏颜色
 * setTranslucent ,设置状态栏半透明
 * setTransparent,设置状态栏全透明
 * setTranslucentForImageView,activity里面设置头部是图片的用图片作为状态栏,要在图片布局上设置fitsysem,setClipToPadding
 * 暂不支持fragment里面用图片作为头部,可以手动设置,比较麻烦
 */

public class StatusBarUtils {

    private static final int FAKE_STATUS_BAR_VIEW_ID = R.id.statusbarutil_fake_status_bar_view;
    private static final int FAKE_TRANSLUCENT_VIEW_ID = R.id.statusbarutil_translucent_view;
    private static final int TAG_KEY_HAVE_SET_OFFSET = -123;
    public static final int DEFAULT_STATUS_BAR_ALPHA = 112; //状态栏toum9igndu

    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(calculateStatusColor(color, 0));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View fakeStatusBarView = decorView.findViewById(FAKE_STATUS_BAR_VIEW_ID);
            if (fakeStatusBarView != null) {
                if (fakeStatusBarView.getVisibility() == View.GONE) {
                    fakeStatusBarView.setVisibility(View.VISIBLE);
                }
                fakeStatusBarView.setBackgroundColor(calculateStatusColor(color, 0));
            } else {
                decorView.addView(createStatusBarView(activity, color, 0));
            }
           // setRootView(activity);
        }

    }


    public static  void hideStatusBar(Activity activity,boolean isHide) {
        if(isHide) {
            WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(layoutParams);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }else {
            WindowManager.LayoutParams params = activity.getWindow().getAttributes();
            params.flags &=WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(params);
        }
    }

    public static void addMarginTopEqualStatusBarHeight(@NonNull View view, Context context) {
        if(view==null) {
            LogUtil.i("状态栏判断","view为空");
            return;
        }
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            return;
        }else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            Object haveSetOffset = view.getTag(TAG_KEY_HAVE_SET_OFFSET);
            if (haveSetOffset != null && (Boolean) haveSetOffset) return;
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin,
                    layoutParams.topMargin + getStatusBarHeight(context),
                    layoutParams.rightMargin,
                    layoutParams.bottomMargin);
             view.setTag(TAG_KEY_HAVE_SET_OFFSET, true);
        }

    }

    /**设置状态栏字体颜色为深色
     * @param activity
     */
    public static void setDeepTextColor(Activity activity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 生成一个和状态栏大小相同的半透明矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @param alpha    透明值
     * @return 状态栏矩形条
     */
    private static View createStatusBarView(Activity activity, @ColorInt int color, int alpha) {
        // 绘制一个和状态栏一样高的矩形
        View statusBarView = new View(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(calculateStatusColor(color, alpha));
        statusBarView.setId(FAKE_STATUS_BAR_VIEW_ID);
        return statusBarView;
    }

    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private static int calculateStatusColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

//    //设置状态栏半透明
//    public static void setTranslucent(Activity activity,int id) {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //设置状态栏透明
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(id)).getChildAt(0);
//            rootView.setFitsSystemWindows(true);
//            rootView.setClipToPadding(true);
//        }
//
//    }

    public static void setTranslucent(Activity activity, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        setTransparent(activity);
        addTranslucentView(activity, statusBarAlpha);
    }

    /**设置状态栏全透明
     * @param activity
     */
    public static void setTransparent(Activity activity) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        transparentStatusBar(activity);
        setRootView(activity);


    }

    /**
     * 设置根布局参数
     */
    private static void setRootView(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }

    /**使状态栏透明
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

    /**为fragment头部是图片的设置
     * @param activity
     * @param needOffsetView 需要向下偏移的 View
     *                       暂时没有效果,等改正后再使用
     */
    public static void setTranslucentForImageViewInFragment(Activity activity, View needOffsetView) {
        setTranslucentForImageView(activity,DEFAULT_STATUS_BAR_ALPHA,needOffsetView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            clearPreviousSetting(activity);
        }

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void clearPreviousSetting(Activity activity) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        View fakeStatusBarView = decorView.findViewById(FAKE_STATUS_BAR_VIEW_ID);
        if (fakeStatusBarView != null) {
            decorView.removeView(fakeStatusBarView);
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setPadding(0, 0, 0, 0);
        }
    }


    /**为头部是图片的设置状态栏
     * @param activity
     * @param statusBarAlpha
     * @param needOffsetView
     */
    public static void setTranslucentForImageView(Activity activity, @IntRange(from = 0, to = 255) int statusBarAlpha,
                                                  View needOffsetView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        setTransparentForWindow(activity);
        addTranslucentView(activity, statusBarAlpha);
        if (needOffsetView != null ) {
            Object haveSetOffset = needOffsetView.getTag(TAG_KEY_HAVE_SET_OFFSET);
            if (haveSetOffset != null && (Boolean) haveSetOffset) {
                return;
            }

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) needOffsetView.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + getStatusBarHeight(activity),
                    layoutParams.rightMargin, layoutParams.bottomMargin);
            needOffsetView.setTag(TAG_KEY_HAVE_SET_OFFSET, true);
        }

    }

    /**添加半透明矩形条
     * @param activity
     * @param statusBarAlpha 透明值
     */
    private static void addTranslucentView(Activity activity, int statusBarAlpha) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        View fakeTranslucentView = contentView.findViewById(FAKE_TRANSLUCENT_VIEW_ID);
        if (fakeTranslucentView != null) {
            if (fakeTranslucentView.getVisibility() == View.GONE) {
                fakeTranslucentView.setVisibility(View.VISIBLE);
            }
            fakeTranslucentView.setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0));
        } else {
            contentView.addView(createTranslucentStatusBarView(activity, statusBarAlpha));
        }
    }

    /**
     * 创建半透明矩形 View
     *
     * @param alpha 透明值
     * @return 半透明 View
     */
    private static View createTranslucentStatusBarView(Activity activity, int alpha) {
        // 绘制一个和状态栏一样高的矩形
        View statusBarView = new View(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
        statusBarView.setId(FAKE_TRANSLUCENT_VIEW_ID);
        return statusBarView;
    }

    /**
     * 设置透明
     */
    private static void setTransparentForWindow(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private static View creatView(Activity activity, int color) {
        //获得状态栏的宽高
        int  resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusHeight = activity.getResources().getDimensionPixelSize(resourceId);


        //绘制一个和状态栏一样高的矩形
        View view = new View(activity);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,statusHeight);
        view.setLayoutParams(param);
        view.setBackgroundColor(color);
        return view;
    }

}
