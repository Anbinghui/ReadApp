package com.jxkj.readapp.mvp.base;

/**
 * Created by An on 2017/8/17.
 */

public class BaseContract {
    interface BasePresenter<T> {
        void attachView(T view);
        void deleteView();
    }
    interface  BaseView{
        void toast(String str);
        void showError(Throwable t);
        void complete();
    }
}
