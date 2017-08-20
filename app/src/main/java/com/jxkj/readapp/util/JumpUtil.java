package com.jxkj.readapp.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

/**
 * Created by An on 2017/7/11.
 */

public class JumpUtil {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("unchecked") void transitionTo(Intent intent, Activity context) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(context, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(context, pairs);
        context.startActivity(intent,transitionActivityOptions.toBundle());
    }

}
