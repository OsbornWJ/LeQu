package com.xujie.lequ.ui.home;

import com.xujie.lequ.base.IPresenter;
import com.xujie.lequ.base.IView;
import com.xujie.lequ.data.bean.GirlsBean;

import java.util.List;

/**
 * @author wj
 * @date 2017/6/6
 * @discription
 */
public class GirlsContract {

    interface View extends IView {

        void refresh(List<GirlsBean.ResultsEntity> datas);

        void load(List<GirlsBean.ResultsEntity> datas);

        void showError();

        void showNormal();
    }

    interface Presenter extends IPresenter<View> {
        void getGirls(int page, int size,  boolean isRefresh);
    }

}
