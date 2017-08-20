package com.jxkj.readapp.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.readapp.R;
import com.jxkj.readapp.adapter.BusAdapter;
import com.jxkj.readapp.bean.TestBean;
import com.jxkj.readapp.view.DisInterceptNestedScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineBehaviorDemoActivity extends AppCompatActivity {

    @BindView(R.id.uc_zoomiv)
    ImageView ucZoomiv;
    @BindView(R.id.user_head_container)
    LinearLayout userHeadContainer;
    @BindView(R.id.frag_uc_nickname_tv)
    TextView fragUcNicknameTv;
    @BindView(R.id.frag_uc_msg_tv)
    TextView fragUcMsgTv;
    @BindView(R.id.frag_uc_follow_tv)
    TextView fragUcFollowTv;
    @BindView(R.id.frag_uc_interest_tv)
    TextView fragUcInterestTv;
    @BindView(R.id.func_order)
    TextView funcOrder;
    @BindView(R.id.func_integral)
    TextView funcIntegral;
    @BindView(R.id.func_payment)
    TextView funcPayment;
    @BindView(R.id.func_invite)
    TextView funcInvite;
    @BindView(R.id.func_stages)
    TextView funcStages;
    @BindView(R.id.func_insurance)
    TextView funcInsurance;
    @BindView(R.id.func_ticket)
    TextView funcTicket;
    @BindView(R.id.func_container)
    LinearLayout funcContainer;
    @BindView(R.id.middle_layout)
    DisInterceptNestedScrollView middleLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.rcl_mine)
    RecyclerView rclMine;
    @BindView(R.id.activity_mine_behavior_demo)
    CoordinatorLayout activityMineBehaviorDemo;
    @BindView(R.id.collBar)
    CollapsingToolbarLayout collBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_behavior_demo);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        BusAdapter adapter = new BusAdapter(TestBean.getDataList());
        rclMine.setLayoutManager(new LinearLayoutManager(this));
        rclMine.setAdapter(adapter);
        collBar.setTitle("标题栏");
    }
}
