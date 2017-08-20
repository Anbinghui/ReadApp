package com.jxkj.readapp.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by An on 2017/7/15.
 */

public class MyBehavior extends AppBarLayout.Behavior {
    private static final String TAG = "overScroll";//图片
    private static final String TAG_TOOLBAR = "toolbar";//标题栏
    private static final String TAG_MIDDLE = "middle";//信息
    private int scrollY;//偏移量
    private int childHeight;//子控件高度
    private int dependHeight;//空间高度
    private int heardSize = -1;
    private int minHeard = -1;
    private int mParentHeight;
    private int mTargetViewHeight;
    private int mMiddleHeight;
    private float mTotalDy;
    private float mLastScale;
    private int mLastBottom;
    private Toolbar mToolBar;
    private View mTargetView;
    private boolean isAnimate;
    private ViewGroup middleLayout;//个人信息布局
    private static final float TARGET_HEIGHT  = 1500;
    private boolean isRecovering = false;//是否正在自动回弹中
    public MyBehavior(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
        boolean handled =  super.onLayoutChild(parent, abl, layoutDirection);
        if(mToolBar==null) {
            mToolBar = (Toolbar) parent.findViewWithTag(TAG_TOOLBAR);
        }
        if(middleLayout==null) {
            middleLayout = (ViewGroup) parent.findViewWithTag(TAG_MIDDLE);
        }
        if(mTargetView==null) {
            mTargetView = parent.findViewWithTag(TAG);
            if(mTargetView!=null) {
                inital(abl);
            }
        }
        return handled;
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        isAnimate = true;
        if(target instanceof DisInterceptNestedScrollView) return true;
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        if(!isRecovering) {
            if(mTargetView!=null && ((dy<0 && child.getBottom()>=mParentHeight) || (dy>0 && child.getBottom()>mParentHeight))){
                scale(child,target,dy);
                return;
            }
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        if(velocityY>100) {
            isAnimate = false;
        }
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
        recovery(abl);
        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }

    private void recovery(AppBarLayout abl) {//这里面主要控制动画,贼几把烦
        if(isRecovering) return;
        if(mTotalDy>0) {
            isRecovering = true;
            mTotalDy = 0;
            if(isAnimate){
                ValueAnimator anim = ValueAnimator.ofFloat(mLastScale,1f).setDuration(200);
                anim.addUpdateListener(
                        new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {

                                float value = (float) animation.getAnimatedValue();
                                ViewCompat.setScaleX(mTargetView, value);
                                ViewCompat.setScaleY(mTargetView, value);
                                abl.setBottom((int) (mLastBottom - (mLastBottom - mParentHeight) * animation.getAnimatedFraction()));
                                middleLayout.setTop((int) (mLastBottom -
                                        (mLastBottom - mParentHeight) * animation.getAnimatedFraction() - mMiddleHeight));
//                                middleLayout.setBottom((int) (mLastBottom - (mLastBottom - mParentHeight) * animation.getAnimatedFraction()));

//                                if (onProgressChangeListener != null) {
//                                    float progress = Math.min((value - 1) / MAX_REFRESH_LIMIT, 1);//计算0~1的进度
//                                    onProgressChangeListener.onProgressChange(progress, true);

                            }
                        }
                );
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isRecovering = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                anim.start();
            }else {
                ViewCompat.setScaleX(mTargetView, 1f);
                ViewCompat.setScaleY(mTargetView, 1f);
                abl.setBottom(mParentHeight);
                middleLayout.setTop(mParentHeight - mMiddleHeight);
//                middleLayout.setBottom(mParentHeight);
                isRecovering = false;

            }

        }

    }

    private void scale(AppBarLayout child, View target, int dy) {
        mTotalDy += -dy;
        mTotalDy = Math.min(mTotalDy,TARGET_HEIGHT);
        mLastScale = Math.max(1f,1f+mTotalDy/TARGET_HEIGHT);
        ViewCompat.setScaleX(mTargetView,mLastScale);
        ViewCompat.setScaleY(mTargetView,mLastScale);
        mLastBottom = mParentHeight+ (int) (mTargetViewHeight/2*(mLastScale-1));
        child.setBottom(mLastBottom);
        target.setScaleY(0);
        middleLayout.setTop(mLastBottom- mMiddleHeight);


    }

    private void inital(AppBarLayout abl) {
        abl.setClipChildren(false);
        mParentHeight = abl.getHeight();
        mTargetViewHeight = mTargetView.getHeight();
        mMiddleHeight = middleLayout.getHeight();

    }
}
