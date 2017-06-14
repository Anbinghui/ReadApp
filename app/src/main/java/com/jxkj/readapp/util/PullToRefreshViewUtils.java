package com.jxkj.readapp.util;

import android.content.Context;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import com.jxkj.readapp.R;

public class PullToRefreshViewUtils {
	public static int BOTH = 0;
	public static int PULL_FROM_END = 1;
	public static int PULL_FROM_START = 2;

	public static void setText(PullToRefreshListView xlv_shows,
							   Context context, int type) {
		switch (type) {
		case 0:
			xlv_shows.setMode(PullToRefreshBase.Mode.BOTH);
			break;
		case 1:
			xlv_shows.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
			break;
		case 2:
			xlv_shows.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
			break;
		default:
			break;
		}

		xlv_shows.getLoadingLayoutProxy(false, true).setPullLabel(
				context.getString(R.string.xlistview_footer_hint_normal));
		xlv_shows.getLoadingLayoutProxy(false, true).setRefreshingLabel(
				context.getString(R.string.xlistview_header_hint_loading));
		xlv_shows.getLoadingLayoutProxy(false, true).setReleaseLabel(
				context.getString(R.string.xlistview_footer_hint_ready));
	}

    public static void setWebViewText(PullToRefreshWebView xlv_shows,
									  Context context, int type) {
        switch (type) {
            case 0:
                xlv_shows.setMode(PullToRefreshBase.Mode.BOTH);
                break;
            case 1:
                xlv_shows.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                break;
            case 2:
                xlv_shows.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                break;
            default:
                break;
        }

        xlv_shows.getLoadingLayoutProxy(false, true).setPullLabel(
                context.getString(R.string.xlistview_footer_hint_normal));
        xlv_shows.getLoadingLayoutProxy(false, true).setRefreshingLabel(
                context.getString(R.string.xlistview_header_hint_loading));
        xlv_shows.getLoadingLayoutProxy(false, true).setReleaseLabel(
                context.getString(R.string.xlistview_footer_hint_ready));
    }

	public static void setText(PullToRefreshScrollView pullToRefreshScrollView,
							   Context context, int type) {
		switch (type) {
			case 0:
				pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
				break;
			case 1:
				pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
				break;
			case 2:
				pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
				break;
			default:
				break;
		}

		pullToRefreshScrollView.getLoadingLayoutProxy(false, true).setPullLabel(
				context.getString(R.string.xlistview_footer_hint_normal));
		pullToRefreshScrollView.getLoadingLayoutProxy(false, true).setRefreshingLabel(
				context.getString(R.string.xlistview_header_hint_loading));
		pullToRefreshScrollView.getLoadingLayoutProxy(false, true).setReleaseLabel(
				context.getString(R.string.xlistview_footer_hint_ready));
	}
}
