package com.jxkj.readapp.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.jxkj.readapp.R;
import com.jxkj.readapp.application.ReadApplication;
import com.jxkj.readapp.ioc.api.Api;
import com.jxkj.readapp.ioc.api.ApiService;
import com.jxkj.readapp.ioc.component.ActivityComponent;
import com.jxkj.readapp.ioc.component.AppComponent;
import com.jxkj.readapp.view.LoadingDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected AppComponent appComponent;
    protected Api api;
    protected Context mContext;
    protected ApiService apiService;
    protected ActivityComponent activityComponent;
    private LoadingDialog mLoadingDialog;
    public Toolbar mCommonToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        if(getLayoutId() !=-1) {
            setContentView(getLayoutId());
        }
        mContext = this;
        ButterKnife.bind(this);
        appComponent = ReadApplication.getInstance().getAppComponent();
        api = appComponent.getApi();
        apiService = appComponent.getApi().getApiService();
        activityComponent = appComponent.activityComponent();
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (mCommonToolbar != null) {
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }
        initViews();
        initData();
    }

    public abstract void initToolBar();

    public  int getLayoutId() {
        return -1;
    }

    public abstract void initViews();
    public abstract void initData();

    public void showWaitDialog() {
        if (mLoadingDialog != null) {
            if(mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
            mLoadingDialog =null;
        }

        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.startGif();
        if(!isFinishing()) {
            mLoadingDialog.show();
        }
    }


    public void dismissWaitDialog() {
        if(mLoadingDialog!=null && mLoadingDialog.isShowing()) {
            mLoadingDialog.stopGif();
            mLoadingDialog.dismiss();
            mLoadingDialog=null;
        }

    }
    public void finish(View view) {
        finish();
    }
    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }


}
