package com.jxkj.readapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jxkj.readapp.activity.HooLiganActivity;

/**
 * Created by An on 2017/7/12.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            HooLiganActivity.startHooLigan();
        }else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            HooLiganActivity.killHooLigan();
        }
     }
}
