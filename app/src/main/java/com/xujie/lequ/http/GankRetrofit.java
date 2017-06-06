package com.xujie.lequ.http;

import com.xujie.lequ.app.Constant;
import com.xujie.lequ.app.LeQuApp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wj
 * @date 2017/6/5
 * @discription
 */
public class GankRetrofit {

    private static Retrofit retrofit;

    public static Retrofit getRetorfit(){
        if (retrofit == null) {
            synchronized (GankRetrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.GANHUO_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(LeQuApp.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit;
    }

}
