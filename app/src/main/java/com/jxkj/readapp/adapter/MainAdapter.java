package com.jxkj.readapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.readapp.R;
import com.jxkj.readapp.bean.DemoBean;

import java.util.List;

/**
 * Created by An on 2017/7/5.
 */

public class MainAdapter extends BaseQuickAdapter<DemoBean,BaseViewHolder> {
    public MainAdapter(List<DemoBean> data) {
        super(R.layout.item_main,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DemoBean item) {
        helper.setText(R.id.tv_main_title,item.getTitle());
    }
}
