package com.jxkj.readapp.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.jxkj.readapp.R;
import com.jxkj.readapp.adapter.BusAdapter;
import com.jxkj.readapp.bean.TestBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppBarDemoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rcl_appbar)
    RecyclerView rclAppbar;
    @BindView(R.id.activity_app_bar_demo)
    CoordinatorLayout activityAppBarDemo;
    @BindView(R.id.collapsing_toolbar1)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.tabs)
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_demo);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        collapsingToolbar.setTitle("滚动测试");
        tabs.addTab(tabs.newTab().setText("第一"));
        tabs.addTab(tabs.newTab().setText("第二"));
        tabs.addTab(tabs.newTab().setText("第三"));
        BusAdapter adapter = new BusAdapter(TestBean.getDataList());
        rclAppbar.setLayoutManager(new LinearLayoutManager(this));
        rclAppbar.setAdapter(adapter);
    }
}
