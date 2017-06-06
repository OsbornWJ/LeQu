package com.xujie.lequ.ui.home;

import com.xujie.lequ.app.Constant;
import com.xujie.lequ.base.BasePresenter;
import com.xujie.lequ.data.DataResponsitory;
import com.xujie.lequ.data.DataSouce;
import com.xujie.lequ.data.bean.GirlsBean;

/**
 * @author wj
 * @date 2017/6/6
 * @discription
 */
public class GirlsPresenter extends BasePresenter<GirlsContract.View> implements GirlsContract.Presenter {

    private DataResponsitory mResponsitory = new DataResponsitory();

    @Override
    public void start() {
        getGirls(1, 20, true);
    }

    @Override
    public void getGirls(int page, int size, final boolean isRefresh) {
        mResponsitory.getData(Constant.GANHUO_FULI, page, size, new DataSouce.LoadDataCallBack() {
            @Override
            public void onSuccess(Object object) {
                GirlsBean girlsBean = (GirlsBean) object;
                if (isRefresh){
                    mView.refresh(girlsBean.getResults());
                } else {
                    mView.load(girlsBean.getResults());
                }
                mView.showNormal();
            }

            @Override
            public void onDataNotAvailable() {
                if (isRefresh){
                    mView.showError();
                }
            }
        });
    }
}
