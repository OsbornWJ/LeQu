package com.xujie.lequ.base;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void dettachView() {
        this.mView = null;
    }
}
