package com.jxkj.readapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.readapp.R;
import com.jxkj.readapp.bean.CollectionBookBean;

import java.util.List;

/**
 * Created by An on 2017/8/17.
 */

public class LocalBookAdapter extends BaseQuickAdapter<CollectionBookBean,BaseViewHolder> {
    public LocalBookAdapter(List<CollectionBookBean> data) {
        super(R.layout.item_local_book,data);
    }

    @Override
    protected void convert(BaseViewHolder helper,CollectionBookBean item) {
        String name = item.getTitle();
        String size = item.getLastChapter();
        helper.setText(R.id.tv_book_name,name)
                .setText(R.id.tv_book_size,size);
    }
}
