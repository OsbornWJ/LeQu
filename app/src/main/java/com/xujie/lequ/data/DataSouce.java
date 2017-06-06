package com.xujie.lequ.data;

/**
 * @author wj
 * @date 2017/6/5
 * @discription
 */
public interface DataSouce {

    interface LoadDataCallBack{

        void onSuccess(Object object);

        void onDataNotAvailable();
    }

    void getGirls(String type, int page, int size, LoadDataCallBack callback);

}
