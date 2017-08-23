package com.jxkj.readapp.activity.read;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxkj.readapp.R;
import com.jxkj.readapp.bean.CollectionBookBean;
import com.jxkj.readapp.common.base.BaseActivity;
import com.jxkj.readapp.util.StatusBarUtils;
import com.jxkj.readapp.view.page.PageFactory;
import com.jxkj.readapp.view.page.PageView;

import butterknife.BindView;

/**
 * 阅读页面
 * 问题:下一页的时候判断数据是否需要重新读取
 * 2.监听滑动时间
 */
public class ReadActivity extends BaseActivity {

    @BindView(R.id.read_pageview)
    PageView readPageview;

    @BindView(R.id.iv_isnight)
    ImageView ivIsnight;
    @BindView(R.id.tv_isnight)
    TextView tvIsnight;
    @BindView(R.id.ll_isninght)
    LinearLayout llIsninght;
    @BindView(R.id.ll_text_size)
    LinearLayout llTextSize;
    @BindView(R.id.ll_text_progress)
    LinearLayout llTextProgress;
    @BindView(R.id.ll_read_foot)
    LinearLayout llReadFoot;
    @BindView(R.id.activity_read)
    RelativeLayout activityRead;
    private String path;
    private String name;
    private PageFactory pageFactory;
    private CollectionBookBean book;

    @Override
    public int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initViews() {
        book = (CollectionBookBean) getIntent().getSerializableExtra("book");
        hideSettingBar();
        readPageview.setSystemUiVisibility(View.INVISIBLE);
        pageFactory = PageFactory.getInstance(readPageview,book);
        pageFactory.nextPage();
        pageFactory.setFontSize(45);
        pageFactory.setNightMode(false);
    }

    @Override
    public void initData() {
        readPageview.setOnClickCallBack(new PageView.OnClickListener() {
            @Override
            public void onLeftClick() {
                pageFactory.perPage();
            }

            @Override
            public void onRightClick() {
                pageFactory.nextPage();
            }

            @Override
            public void onCenterClick() {
                showSettingBar();
            }
        });

    }

    private void showSettingBar() {
        visible(llReadFoot,mCommonToolbar);
        StatusBarUtils.hideStatusBar(this,false);
    }

    private void hideSettingBar() {
        gone(llReadFoot,mCommonToolbar);
        StatusBarUtils.hideStatusBar(this,true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        pageFactory.saveBookmark();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pageFactory.close();
    }
}
