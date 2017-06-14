package com.jxkj.readapp.util;

import android.content.Context;
import android.widget.AbsListView;

import com.bumptech.glide.Glide;

/**
 * Created by jinkang on 2016/12/30.
 */

public class GlidePauseOnScroll implements AbsListView.OnScrollListener {

    private Context mContext;
    private final boolean pauseOnScroll;
    private final boolean pauseOnFling;

    public GlidePauseOnScroll(Context context , boolean pauseOnScroll, boolean pauseOnFling) {
        this.pauseOnScroll = pauseOnScroll;
        this.pauseOnFling = pauseOnFling;
        mContext = context;
    }


    public void resume() {
        Glide.with(mContext).resumeRequests();
    }

    public void pause() {
        Glide.with(mContext).pauseRequests();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                resume();
                break;
            case  AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                if (pauseOnScroll) {
                    pause();
                }
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                if (pauseOnFling) {
                    pause();
                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
