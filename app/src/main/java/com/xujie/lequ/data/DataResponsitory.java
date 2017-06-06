package com.xujie.lequ.data;

import com.xujie.lequ.app.Constant;
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
    public void getData(String type, int page, int size, LoadDataCallBack callback) {
        if (type.equals(Constant.GANHUO_FULI)){
            getGirls(type, page, size, callback);
        }
    }

    @Override
    public void getGirls(String type, int page, int size, LoadDataCallBack callback) {
        mRemoteDataSource.getGirls(type, page, size, callback);
    }
}
