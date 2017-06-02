package com.xujie.lequ.app;

import android.app.Application;

import com.xujie.lequ.app.exception.LocalFileHandler;
import com.xujie.lequ.util.LogUtil;
import com.xujie.lequ.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public class LeQuApp extends Application {

    private static LeQuApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        //配置是否显示log
        LogUtil.isDebug = true;

        //配置时候显示toast
        ToastUtil.isShow = true;

        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));
    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        return client;
    }

    public static LeQuApp getmInstance() {
        return mInstance;
    }
}
