package com.xujie.lequ.ui.splash;

import com.xujie.lequ.base.BasePresenter;
import com.xujie.lequ.base.BaseView;
import com.xujie.lequ.base.IPresenter;
import com.xujie.lequ.base.IView;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public interface SplashContract {

    interface View extends IView{
        void showGirl(String girlUrl);

        void showGirl();
    }

    interface Presenter extends IPresenter<View> {

    }

}
