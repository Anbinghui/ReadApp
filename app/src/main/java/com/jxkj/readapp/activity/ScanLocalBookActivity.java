package com.jxkj.readapp.activity;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jxkj.readapp.R;
import com.jxkj.readapp.adapter.LocalBookAdapter;
import com.jxkj.readapp.bean.CollectionBookBean;
import com.jxkj.readapp.common.base.BaseActivity;
import com.jxkj.readapp.common.share.Constant;
import com.jxkj.readapp.db.BookCaseDBManager;
import com.jxkj.readapp.util.AppUtils;
import com.jxkj.readapp.util.DialogUtil;
import com.jxkj.readapp.util.FileUtils;
import com.jxkj.readapp.util.ToastUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

public class ScanLocalBookActivity extends BaseActivity {


    @BindView(R.id.rcl_local_book)
    RecyclerView rclLocalBook;
    @BindView(R.id.activity_scan_local_book)
    CoordinatorLayout activityScanLocalBook;
    private LocalBookAdapter adapter;
    private List<CollectionBookBean> books = new ArrayList<>();
    private RxPermissions mRxPermissons;
    private BookCaseDBManager dbManager;
    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("本地书籍");
        mCommonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_local_book;
    }

    @Override
    public void initViews() {
        rclLocalBook.setLayoutManager(new LinearLayoutManager(mContext));
        rclLocalBook.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        adapter = new LocalBookAdapter(books);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rclLocalBook.setAdapter(adapter);
        dbManager = BookCaseDBManager.getInstance(mContext);
        mRxPermissons = new RxPermissions(this);
    }

    @Override
    public void initData() {
        mRxPermissons.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    queryBook();
                } else {
                    ToastUtils.makeShortText(mContext, "请开启阅读权限");
                    return;
                }
            }
        });

        rclLocalBook.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //单击事件
                DialogUtil.getInstance().showBasicCallback(mContext, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        CollectionBookBean bean  = books.get(position);
                        if(dbManager.isQuery(bean)) {
                            ToastUtils.makeShortText(mContext,"已存在书架中");
                        }else {
                            ToastUtils.makeShortText(mContext,"加入成功");
                            dbManager.insertData(bean);
                        }
                    }
                },"是否加入书架","加入","否");
            }
        });

    }

    /**
     * 查询本地txt文件
     */
    private void queryBook() {

        showWaitDialog();
        books.clear();
        String[] projection = new String[]{MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.SIZE};

        String bookPath = FileUtils.createRootPath(AppUtils.getAppContext());
        //查询后缀名为txt的文件
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://media/external/file"),
                projection,
                MediaStore.Files.FileColumns.DATA + " not like ? and ("
                        + MediaStore.Files.FileColumns.DATA + " like ? or "
                        + MediaStore.Files.FileColumns.DATA + " like ? or "
                        + MediaStore.Files.FileColumns.DATA + " like ? or "
                        + MediaStore.Files.FileColumns.DATA + " like ? )",
                new String[]{"%" + bookPath + "%",
                        "%" + Constant.SUFFIX_TXT,
                        "%" + Constant.SUFFIX_PDF,
                        "%" + Constant.SUFFIX_EPUB,
                        "%" + Constant.SUFFIX_CHM},
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int idindex = cursor.getColumnIndex(MediaStore.Files.FileColumns._ID);
            int dataindex = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int sizeindex = cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE);
            List<CollectionBookBean> list = new ArrayList<>();
            do {
                String path = cursor.getString(dataindex);
                int dot = path.lastIndexOf("/");
                String name = path.substring(dot + 1);
                if (name.lastIndexOf(".") > 0) {
                    name = name.substring(0, name.lastIndexOf("."));
                    CollectionBookBean book = new CollectionBookBean();
                    book.set_id(name);
                    book.setPath(path);
                    book.setTitle(name);
                    book.setIsFromSD(true);
                    book.setLastChapter(FileUtils.formatFileSizeToString(cursor.getLong(sizeindex)));
//                    book._id = name;
//                    book.path = path;
//                    book.title = name;
//                    book.isFromSD = true;
//                    book.lastChapter = FileUtils.formatFileSizeToString(cursor.getLong(sizeindex));
                    list.add(book);
                }

            } while (cursor.moveToNext());
            cursor.close();
            books.addAll(list);
            adapter.notifyDataSetChanged();
            dismissWaitDialog();
        } else {
            dismissWaitDialog();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.removeDB();
    }
}
