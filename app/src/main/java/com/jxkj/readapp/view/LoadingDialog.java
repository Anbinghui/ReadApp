package com.jxkj.readapp.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.jxkj.readapp.R;
import com.jxkj.readapp.ioc.module.ApiModule;
import com.jxkj.readapp.util.ToastUtils;

import java.lang.ref.WeakReference;

/**
 * Created by An on 2017/8/17.
 * version:${VERSION}
 */

public class LoadingDialog  extends Dialog {

    private DialogTimeCount timer;

    private ImageView animationIV;
    private AnimationDrawable animationDrawable;

    public LoadingDialog(Context context) {
        super(context,R.style.LoadingDialog);
        init(context);
    }

    public LoadingDialog(Context context,int theme) {
        super(context,theme);

    }

    private void init(Context context) {
        timer = new DialogTimeCount(this);
        setContentView(R.layout.dialog_loading);
        animationIV = (ImageView) findViewById(R.id.animationIV);
        animationIV.setImageResource(R.drawable.animation);
        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
    }

    @Override
    public void show() {
        super.show();
        timer.cancel();
        timer.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        timer.cancel();
    }

    public void startGif() {
        animationDrawable.start();
    }
    public void stopGif() {
        animationDrawable.stop();
    }
    private static class DialogTimeCount extends CountDownTimer {

        private WeakReference<LoadingDialog> dialogWeakReference;

        DialogTimeCount(LoadingDialog dialog) {
            super(ApiModule.TIME_OUT, 1000);
            dialogWeakReference = new WeakReference<>(dialog);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            if (BuildConfig.DEBUG) {
//                Log.i("TAG", "LoadingDialogTick: " + millisUntilFinished/1000);
//            }
        }

        @Override
        public void onFinish() {
            LoadingDialog dialog = dialogWeakReference.get();
            if (dialog != null){
                ToastUtils.makeShortText(dialog.getContext() , "请求网络超时");
                dialog.dismiss();
            }
        }
    }

}
