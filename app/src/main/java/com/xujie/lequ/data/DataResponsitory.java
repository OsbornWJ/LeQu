package com.xujie.lequ.data;

import com.xujie.lequ.data.remote.RemoteDataSource;

/**
 * @author wj
 * @date 2017/6/5
 * @discription
 */
public class DataResponsitory implements DataSouce {

    private RemoteDataSource mRemoteDataSource;

    public DataResponsitory(){
        mRemoteDataSource = new RemoteDataSource();
    }

    //获取福利  girls
    @Override
    public void getGirls(int page, int size, LoadDataCallBack callback) {
        mRemoteDataSource.getGirls(page, size, callback);
    }
}
