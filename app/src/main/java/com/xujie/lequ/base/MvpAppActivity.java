package com.xujie.lequ.base;

import android.os.Bundle;

import com.xujie.lequ.app.ActivityManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public abstract class MvpAppActivity<T extends IPresenter> extends BaseActivity implements IView {

    public T presenter;

    private Unbinder unbinder;

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    public abstract T initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        if (null == getSupportFragmentManager().getFragments()){
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment){
                addFragment(firstFragment);
            }
        }
        ActivityManager.getInstance().addActivity(this);
        presenter = initPresenter();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().finishActivity(this);
        if (null != presenter) presenter.dettachView();
        unbinder.unbind();
        super.onDestroy();
    }
}
