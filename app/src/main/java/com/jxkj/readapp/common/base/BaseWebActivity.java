package com.jxkj.readapp.common.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.jxkj.readapp.R;
import com.jxkj.readapp.common.share.AndroidInterface;

public class BaseWebActivity extends AppCompatActivity {

    protected AgentWeb mAgentWeb;
    private LinearLayout linearLayout;
    private Toolbar mToolbar;
    private TextView mTitleTextView;
    private AlertDialog mAlertDialog;
    private String webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_web);
        linearLayout = (LinearLayout) this.findViewById(R.id.activity_base_web);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        mTitleTextView = (TextView) this.findViewById(R.id.toolbar_title);
        this.setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linearLayout,new LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()
                .ready()
                .go(null);

        mAgentWeb.getJsInterfaceHolder().addJavaObject("android",new AndroidInterface(mAgentWeb,this));
        mAgentWeb.getLoader().loadUrl(getUrl());


    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mTitleTextView != null)
                mTitleTextView.setText(title);
        }
    };

    private void showDialog() {
        if(mAlertDialog==null) {
            mAlertDialog = new AlertDialog.Builder(this)
                    .setMessage("确定关闭页面吗?")
                    .setNegativeButton("留在本页", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(mAlertDialog!=null) {
                                mAlertDialog.dismiss();
                            }
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(mAlertDialog!=null) {
                                mAlertDialog.dismiss();
                            }
                            BaseWebActivity.this.finish();
                        }
                    }).create();
        }
        mAlertDialog.show();

    }

    public String getUrl() {
        return webUrl =getIntent().getStringExtra("weburl");
    }

    public static void jumpto(Context context,String webViewUrl) {
        Intent intent = new Intent(context,BaseWebActivity.class);
        intent.putExtra("weburl",webViewUrl);
        context.startActivity(intent);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       if(mAgentWeb.handleKeyEvent(keyCode,event)) {
           return true;
       }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAgentWeb.getWebLifeCycle().onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
    }


}
