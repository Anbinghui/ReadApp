package com.jxkj.readapp.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import com.bumptech.glide.Glide;


/**
 * Created by jinkang on 2016/12/30.
 */

public class GlideRecyclerPauseOnScroll extends RecyclerView.OnScrollListener {

    private Context mContext;
    private final boolean pauseOnScroll;
    private final boolean pauseOnFling;

    public GlideRecyclerPauseOnScroll(Context context , boolean pauseOnScroll, boolean pauseOnFling) {
        this.pauseOnScroll = pauseOnScroll;
        this.pauseOnFling = pauseOnFling;
        mContext = context;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                resume();
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
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

    public void resume() {
        Glide.with(mContext).resumeRequests();
    }

    public void pause() {
        Glide.with(mContext).pauseRequests();
    }
}
