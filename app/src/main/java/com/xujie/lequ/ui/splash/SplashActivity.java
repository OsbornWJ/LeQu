package com.xujie.lequ.ui.splash;

import android.view.View;

import com.xujie.lequ.R;
import com.xujie.lequ.base.AppActivity;
import com.xujie.lequ.base.BaseFragment;

public class SplashActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return SplashFragment.getInstance();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.splash_fragment;
    }

    @Override
    public void onClick(View view) {

    }
}
