package com.jxkj.readapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.readapp.activity.AppBarDemoActivity;
import com.jxkj.readapp.activity.BookCaseListActivity;
import com.jxkj.readapp.activity.ColDemoActivity;
import com.jxkj.readapp.activity.DemoActivity;
import com.jxkj.readapp.activity.MineBehaviorDemoActivity;
import com.jxkj.readapp.activity.ScanLocalBookActivity;
import com.jxkj.readapp.adapter.MainAdapter;
import com.jxkj.readapp.bean.DemoBean;
import com.jxkj.readapp.common.base.BaseActivity;
import com.jxkj.readapp.common.base.BaseWebActivity;
import com.jxkj.readapp.receiver.BootCompleteReceiver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rcl_main)
    RecyclerView rclMain;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    public static final String TAG ="MainActivity";
    private MainAdapter adapter;
    private List<DemoBean> datas = new ArrayList<>();
    private PhotoView mPhotoView;
    PhotoViewAttacher mAttacher;

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("测试页面");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {

        mPhotoView = (PhotoView) findViewById(R.id.photoView);
        mPhotoView.setImageResource(R.drawable.ic_launcher);


        rclMain = (RecyclerView) findViewById(R.id.rcl_main);
        Log.i(TAG,"initView");
        datas.add(new DemoBean(0,"网页测试"));
        datas.add(new DemoBean(1,"本地网页测试"));
        datas.add(new DemoBean(2,"CoordinatorLayout测试页面1"));
        datas.add(new DemoBean(3,"CoordinatorLayout测试页面2"));
        datas.add(new DemoBean(4,"appbar测试1"));
        datas.add(new DemoBean(5,"appbar测试2"));
        datas.add(new DemoBean(6,"本地书籍"));
        datas.add(new DemoBean(7,"我的书架"));
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
                    case 2:
                        startActivity(new Intent(mContext, DemoActivity.class));
                        break;
                    case 3:
                       startActivity(new Intent(mContext, ColDemoActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(mContext, AppBarDemoActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(mContext, MineBehaviorDemoActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(mContext, ScanLocalBookActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(mContext, BookCaseListActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(new BootCompleteReceiver(),filter);
    }

    @Override
    public void initData() {


    }

    class Cat{

    }
    class Box{
        Cat hidCat;
    }
    static class Docker{
        static Box container;
    }
}
