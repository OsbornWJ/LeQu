package com.xujie.lequ.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView {

    protected T mPresenter;

    protected BaseActivity mActivity;

    protected Unbinder unbinder;

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取fragment布局Id
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mActivity = null;
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment){
        if (null != fragment){
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment(){
        getHoldingActivity().removeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = mInflater.inflate(getLayoutId(), container, false);
        initPresenter();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) mPresenter.attachView(this);
        unbinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.dettachView();
    }

    protected abstract void initPresenter();
}
