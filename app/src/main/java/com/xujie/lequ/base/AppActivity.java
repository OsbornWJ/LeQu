package com.xujie.lequ.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.xujie.lequ.app.ActivityManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public abstract class AppActivity extends BaseActivity {

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

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        if (null == getSupportFragmentManager().getFragments()){
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment){
                addFragment(firstFragment);
            }
        }
        ActivityManager.getInstance().addActivity(this);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().finishActivity(this);
        unbinder.unbind();
        super.onDestroy();
    }
}
