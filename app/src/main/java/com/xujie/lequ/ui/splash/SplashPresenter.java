package com.xujie.lequ.ui.splash;

import com.xujie.lequ.base.BasePresenter;
import com.xujie.lequ.data.DataResponsitory;
import com.xujie.lequ.data.DataSouce;
import com.xujie.lequ.data.bean.GirlsBean;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private DataResponsitory mResponsitory = new DataResponsitory();

    @Override
    public void start() {
        mResponsitory.getGirls(1, 1, new DataSouce.LoadDataCallBack(){


            @Override
            public void onSuccess(Object object) {
                GirlsBean girlsBean = (GirlsBean) object;
                mView.showGirl(girlsBean.getResults().get(0).getUrl());
            }

            @Override
            public void onDataNotAvailable() {
                mView.showGirl();
            }

        });
    }
}
