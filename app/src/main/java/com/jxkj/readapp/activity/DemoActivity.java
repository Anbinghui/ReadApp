package com.jxkj.readapp.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.readapp.R;
import com.jxkj.readapp.bean.TestBean;

import java.util.List;

import butterknife.ButterKnife;

public class DemoActivity extends AppCompatActivity {

    Toolbar demoTool;
    AppBarLayout appbar;
    RecyclerView rclDemo;
    CoordinatorLayout activityDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
        initViews();
    }

    public void initViews() {
        rclDemo = (RecyclerView) findViewById(R.id.rcl_demo);
        demoTool = (Toolbar) findViewById(R.id.demo_tool);


        demoTool.setTitle("测试页面");
        rclDemo.setLayoutManager(new LinearLayoutManager(this));
        DemoAdapter adapter = new DemoAdapter(TestBean.getDataList());
        rclDemo.setAdapter(adapter);
    }



    class DemoAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder> {

        public DemoAdapter(List<TestBean> data) {
            super(R.layout.item_main,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TestBean item) {

        }
    }
}
