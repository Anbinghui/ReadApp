package com.jxkj.readapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.readapp.R;
import com.jxkj.readapp.bean.CollectionBookBean;
import com.jxkj.readapp.util.StringUtil;

import java.util.List;

/**
 * Created by An on 2017/8/18.
 */

public class BookCaseAdapter extends BaseQuickAdapter<CollectionBookBean,BaseViewHolder> {
    public BookCaseAdapter(List<CollectionBookBean> data) {
        super(R.layout.item_book_case,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBookBean item) {
        String name = item.getTitle();
        String author = item.getAuthor();
        String size = item.getLastChapter();
        if(StringUtil.isEmpty(author)) {
            author = "匿名";
        }
        helper.setText(R.id.tv_case_name,name)
                .setText(R.id.tv_case_author,author)
                .setText(R.id.tv_case_size,size);

    }
}
