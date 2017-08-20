package com.jxkj.readapp.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.readapp.R;
import com.jxkj.readapp.adapter.BookCaseAdapter;
import com.jxkj.readapp.bean.CollectionBookBean;
import com.jxkj.readapp.common.base.BaseActivity;
import com.jxkj.readapp.db.BookCaseDBManager;
import com.jxkj.readapp.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookCaseListActivity extends BaseActivity {

    @BindView(R.id.rcl_book_case)
    RecyclerView rclBookCase;

    private BookCaseDBManager dbManager;
    private BookCaseAdapter caseAdapter;
    private List<CollectionBookBean> collectionBooks = new ArrayList<>();
    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("我的书架");
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
        mCommonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.makeShortText(mContext,"点击啊");
                finish();
            }
        });
    }

    @Override
    public void initViews() {
        rclBookCase.setLayoutManager(new LinearLayoutManager(mContext));
        caseAdapter = new BookCaseAdapter(collectionBooks);
        dbManager = BookCaseDBManager.getInstance(mContext);
        caseAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rclBookCase.setAdapter(caseAdapter);

        getCollData();
    }

    private void getCollData() {
        List<CollectionBookBean> list = dbManager.queryAllData();
        collectionBooks.clear();
        collectionBooks.addAll(list);
        caseAdapter.notifyDataSetChanged();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_case_list;
    }

    @Override
    public void initData() {

    }
}
