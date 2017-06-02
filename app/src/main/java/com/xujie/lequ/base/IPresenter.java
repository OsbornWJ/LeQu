package com.xujie.lequ.base;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public interface IPresenter<T extends IView> {

    void attachView(T view);

    void dettachView();

    void start();

}
