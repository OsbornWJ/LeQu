package com.xujie.lequ.data.remote;

import com.xujie.lequ.data.DataSouce;
import com.xujie.lequ.data.bean.GirlsBean;
import com.xujie.lequ.http.GankRetrofit;
import com.xujie.lequ.http.service.GankService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author wj
 * @date 2017/6/5
 * @discription
 */
public class RemoteDataSource implements DataSouce{

    //获取福利  girls
    @Override
    public void getGirls(int page, int size, final LoadDataCallBack callback) {
        GankRetrofit.getRetorfit()
                .create(GankService.GirlsService.class)
                .getGirls(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(GirlsBean girlsBean) {
                        callback.onSuccess(girlsBean);
                    }
                });

    }
}
