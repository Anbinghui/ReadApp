package com.jxkj.readapp.manager;

/**
 * Created by An on 2017/8/18.
 * 书架管理类
 */


public class BookCaseManager {
    private static BookCaseManager mBookCaseManager = null;
    private BookCaseManager (){}
    public static BookCaseManager getInstance(){
        synchronized (BookCaseManager.class) {
            if(mBookCaseManager == null) {
                mBookCaseManager = new BookCaseManager();
            }
        }
        return mBookCaseManager;
    }



}
