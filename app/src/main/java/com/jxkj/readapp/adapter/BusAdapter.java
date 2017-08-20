package com.jxkj.readapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.readapp.R;
import com.jxkj.readapp.bean.TestBean;

import java.util.List;

/**
 * Created by An on 2017/7/14.
 */

public class BusAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder> {
    public BusAdapter(List<TestBean> data) {
        super(R.layout.item_main,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {

    }
}
