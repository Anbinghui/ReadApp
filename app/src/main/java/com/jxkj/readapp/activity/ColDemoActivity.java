package com.jxkj.readapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.readapp.R;
import com.jxkj.readapp.adapter.BusAdapter;
import com.jxkj.readapp.bean.TestBean;
import com.jxkj.readapp.fragment.ColDemoFragment;
import com.jxkj.readapp.view.DisInterceptNestedRecyclerView;
import com.jxkj.readapp.view.IndicatorView;
import com.jxkj.readapp.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ColDemoActivity extends FragmentActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rcl_business)
    DisInterceptNestedRecyclerView rclBusiness;
    @BindView(R.id.tv_business_location)
    TextView tvBusinessLocation;
    @BindView(R.id.tv_business_tabel1)
    TextView tvBusinessTabel1;
    @BindView(R.id.tv_business_tabel2)
    TextView tvBusinessTabel2;
    @BindView(R.id.tv_business_tabel3)
    TextView tvBusinessTabel3;
    @BindView(R.id.tv_business_tabel4)
    TextView tvBusinessTabel4;
    @BindView(R.id.ll_business_tabel)
    LinearLayout llBusinessTabel;
    @BindView(R.id.iv_business_line)
    IndicatorView ivBusinessLine;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.pager_Dol)
    NoScrollViewPager pagerDol;
    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.activity_col_demo)
    LinearLayout activityColDemo;
    private List<Fragment> fragments = new ArrayList<>();
    private ColAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_col_demo);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fragments.add(new ColDemoFragment());
        fragments.add(new ColDemoFragment());
        fragments.add(new ColDemoFragment());
        fragments.add(new ColDemoFragment());
        adapter = new ColAdapter(getSupportFragmentManager(), fragments);
        rclBusiness.setLayoutManager(new GridLayoutManager(this, 4));
        BusAdapter busAdapter = new BusAdapter(TestBean.getDataList());
        rclBusiness.setAdapter(busAdapter);
        pagerDol.setAdapter(adapter);
        pagerDol.setCurrentItem(0);
        pagerDol.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ivBusinessLine.setOffset(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                setTextColor();
                switch (position) {
                    case 0:
                        tvBusinessTabel1.setTextColor(Color.parseColor("#f40e47"));
                        break;
                    case 1:
                        tvBusinessTabel2.setTextColor(Color.parseColor("#f40e47"));
                        break;
                    case 2:
                        tvBusinessTabel3.setTextColor(Color.parseColor("#f40e47"));
                        break;
                    case 3:
                        tvBusinessTabel4.setTextColor(Color.parseColor("#f40e47"));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 字体颜色
     */
    private void setTextColor() {
        tvBusinessTabel1.setTextColor(Color.parseColor("#666666"));
        tvBusinessTabel2.setTextColor(Color.parseColor("#666666"));
        tvBusinessTabel3.setTextColor(Color.parseColor("#666666"));
        tvBusinessTabel4.setTextColor(Color.parseColor("#666666"));
    }

    @OnClick({R.id.tv_business_tabel1, R.id.tv_business_tabel2, R.id.tv_business_tabel3, R.id.tv_business_tabel4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_business_tabel1:
                pagerDol.setCurrentItem(0);
                break;
            case R.id.tv_business_tabel2:
                pagerDol.setCurrentItem(1);
                break;
            case R.id.tv_business_tabel3:
                pagerDol.setCurrentItem(2);
                break;
            case R.id.tv_business_tabel4:
                pagerDol.setCurrentItem(3);
                break;

        }
    }

    class ColAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public ColAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragments = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
