package com.jxkj.readapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.anye.greendao.gen.CollectionBookBeanDao;
import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.jxkj.readapp.bean.CollectionBookBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by An on 2017/8/18.
 * 数据库操作类,将书架的内容添加到数据库
 */

public class BookCaseDBManager {
    private static final String dbName = "book_case_db";
    private static BookCaseDBManager mBookCaseDBManager = null;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    private BookCaseDBManager (Context context){
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
    }
    public static BookCaseDBManager getInstance(Context context){
        synchronized (BookCaseDBManager.class) {
            if(mBookCaseDBManager == null) {
                mBookCaseDBManager = new BookCaseDBManager(context);
            }
        }
        return mBookCaseDBManager;
    }


    private SQLiteDatabase getReadDatabase() {//获取可读数据库
        if(openHelper==null) {
            openHelper= new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase sl = openHelper.getReadableDatabase();
        return sl;
    }
    private SQLiteDatabase getWriteDatabase() {//获取可写的数据库
        if(openHelper==null) {
            openHelper= new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        return openHelper.getWritableDatabase();
    }

    private  DaoSession getWriteSession() {
        DaoMaster daoMaster = new DaoMaster(getWriteDatabase());
        return daoMaster.newSession();
    }

    private DaoSession getReadSession() {
        DaoMaster daoMaster = new DaoMaster(getReadDatabase());
        return daoMaster.newSession();
    }

    /**存入数据
     * @param bean
     */
    public void insertData(CollectionBookBean bean) {
        CollectionBookBeanDao dao = getWriteSession().getCollectionBookBeanDao();
        dao.insert(bean);
    }

    /**
     * 删除所有数据
     */
    public void deleteAllData() {
        CollectionBookBeanDao dao = getWriteSession().getCollectionBookBeanDao();
        dao.deleteAll();
    }

    /**删除某一条数据
     * @param bean
     */
    public void deleteBook(CollectionBookBean bean) {
        CollectionBookBeanDao dao = getWriteSession().getCollectionBookBeanDao();
        dao.delete(bean);
    }

    /**查询所有数据
     * @return
     */
    public List<CollectionBookBean> queryAllData() {
        CollectionBookBeanDao dao = getReadSession().getCollectionBookBeanDao();
        QueryBuilder<CollectionBookBean> qb = dao.queryBuilder();
        return qb.list();
    }

    /**判断某个数据是否存在数据库中
     * @param bean
     * @return
     */
    public boolean isQuery(CollectionBookBean bean) {
        CollectionBookBeanDao dao = getReadSession().getCollectionBookBeanDao();
        QueryBuilder<CollectionBookBean> qb = dao.queryBuilder();
        qb.where(CollectionBookBeanDao.Properties.Path.eq(bean.getPath()));
        List<CollectionBookBean> list = qb.list();
        if(list !=null && list.size()>0) {
            return true;
        }else {
            return false;
        }
    }

    public void removeDB() {
        try {
            if (openHelper != null) {
                openHelper.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        mBookCaseDBManager = null;
    }

}
