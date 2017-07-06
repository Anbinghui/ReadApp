package com.jxkj.readapp.common.share;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

import com.just.library.AgentWeb;
import com.jxkj.readapp.util.ToastUtils;

/**
 * Created by An on 2017/7/5.
 */

public class AndroidInterface {
    private AgentWeb agentWeb;
    private Context context;

    public AndroidInterface(AgentWeb agentWeb,Context context) {
        this.agentWeb = agentWeb;
        this.context = context;
    }

    private Handler deliver = new Handler(Looper.getMainLooper());

    @JavascriptInterface
    public void getAndroid() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                ToastUtils.makeShortText(context,"调用android方法");
            }
        });



    }

}
