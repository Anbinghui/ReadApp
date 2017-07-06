package com.jxkj.readapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.readapp.adapter.MainAdapter;
import com.jxkj.readapp.bean.DemoBean;
import com.jxkj.readapp.common.base.BaseActivity;
import com.jxkj.readapp.common.base.BaseWebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rcl_main)
    RecyclerView rclMain;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    public static final String TAG ="MainActivity";
    private MainAdapter adapter;
    private List<DemoBean> datas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        rclMain = (RecyclerView) findViewById(R.id.rcl_main);
        Log.i(TAG,"initView");
        datas.add(new DemoBean(0,"网页测试"));
        datas.add(new DemoBean(1,"本地网页测试"));
        rclMain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(datas);
        Log.i(TAG,datas.size()+"");
        rclMain.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int id = datas.get(position).getId();
                switch (id) {
                    case 0:
                        BaseWebActivity.jumpto(mContext,"file:///android_asset/h5test.html");
                        break;
                    case 1:
                        String url = "http://172.16.2.178/wap/tmpl/product_detail.html?goods_id=118609&con=app&key=member30f15722b1ab0e2e8130f81bef8e1893";
                        BaseWebActivity.jumpto(mContext,url);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    public void initData() {


    }
}
