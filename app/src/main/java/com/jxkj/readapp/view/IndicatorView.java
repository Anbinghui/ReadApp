package com.jxkj.readapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.jxkj.readapp.R;


/**
 * Created by jinkang on 2017/3/21.
 */

public class IndicatorView extends View {

    private Paint mPaint;
    private float mScale = 0.4f;
    private int mPerWidth;
    private int mOffset = 0;
    private Path mPath;

    public IndicatorView(Context context) {
        super(context);
        init(context , null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context , attrs);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context , attrs);
    }

    private void init(Context context , AttributeSet attrs){
        int count = 4;
        int color = 0xff000000;
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs , R.styleable.IndicatorView);
            count = a.getInt(R.styleable.IndicatorView_indicatorViewCount , 4);
            color = a.getColor(R.styleable.IndicatorView_indicatorViewColor , Color.RED);
            mScale = a.getFloat(R.styleable.IndicatorView_indicatorViewScale , 0.4f);
            a.recycle();
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPerWidth = getScreenWidth(context) / count;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPath == null){
            int paddingWidth = (int) (mPerWidth * (1 - mScale) / 2);
            mPath = new Path();
            int height = getHeight();
            mPath.moveTo(paddingWidth , height);
            mPath.lineTo(mPerWidth - paddingWidth , height);
            mPath.lineTo(mPerWidth - paddingWidth , height / 2);
            mPath.arcTo(new RectF(mPerWidth - height - paddingWidth , 0 , mPerWidth - paddingWidth, height) , 0 , -90 );
            mPath.lineTo(height + paddingWidth, 0);
            mPath.arcTo(new RectF(paddingWidth , 0 , height + paddingWidth , height) , -90 , -90 );
            mPath.close();
        }
        canvas.translate(mOffset , 0);
//        if (mPath == null){
//            mPath = new Path();
//            int height = getHeight();
//            mPath.moveTo(mOffset , height);
//            mPath.lineTo(mOffset + mPerWidth , height);
////            mPath.lineTo(mOffset + mPerWidth , height / 2);
//            mPath.arcTo(new RectF(mOffset + mPerWidth - height * 2 , 0 , mOffset + mPerWidth , height * 2) , 0 , -90 );
//            mPath.lineTo(mOffset + height , 0);
//            mPath.arcTo(new RectF(mOffset , 0 , mOffset + height * 2 , height * 2) , -90 , -90 );
////            mPath.close();
//        }
//        Path path = new Path();
//        path.arcTo(new RectF(0 , 50 , 100 , 150) , -90 , -90);
        canvas.drawPath(mPath , mPaint);
    }

    public void setOffset(int position , float offset){
        mOffset = (int) (mPerWidth * (position + offset));
        invalidate();
    }


    //获取屏幕的宽度
    private int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }
}
