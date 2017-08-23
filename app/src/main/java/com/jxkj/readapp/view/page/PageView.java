package com.jxkj.readapp.view.page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by An on 2017/8/22.
 */

public class PageView extends View {
    private Bitmap mBitmap;
    private int touchSlop;
    private OnScrollListener  mOnScrollListener;
    private OnClickListener mOnClickListener;
    private int  clickX;
    private int currentX;
    private boolean moved;




    public PageView(Context context) {
        super(context);
    }

    public PageView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        setOnClickListener(view -> {
            if(mOnClickListener !=null && Math.abs(clickX-currentX) >touchSlop) {
                int width = getWidth();
                if(clickX>width*2/3) {
                    mOnClickListener.onRightClick();
                }else if(clickX<width/3) {
                    mOnClickListener.onLeftClick();
                }else {
                    mOnClickListener.onCenterClick();
                }
            }
        });
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            clickX = (int) event.getX();
        }else if(event.getAction()== MotionEvent.ACTION_MOVE) {
            if(Math.abs(event.getX()-clickX) <touchSlop) {
                moved = true;
            }
        }else if(event.getAction()==MotionEvent.ACTION_UP) {
            currentX = (int) event.getX();
            if(moved && mOnScrollListener!=null) {
                if(clickX>currentX) {
                    mOnScrollListener.onLeftScroll();
                }else {
                    mOnScrollListener.onRightScroll();
                }
            }
            moved = false;
        }

        return super.onTouchEvent(event);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawBitmap(mBitmap,0,0,null);
        canvas.restore();
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    public void setmBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void setOnClickCallBack(OnClickListener listener) {
        if (listener != null) {
            mOnClickListener = listener;
        }
    }

    public void setScrollListener(OnScrollListener scrollListener) {
        if (scrollListener != null) {
            mOnScrollListener = scrollListener;
        }
    }



    public interface  OnScrollListener{
        //滑动监听回调
        void onLeftScroll();
        void onRightScroll();

    }

    public interface OnClickListener{
        //点击监听回调
        void onLeftClick();
        void onRightClick();
        void onCenterClick();
    }


}
